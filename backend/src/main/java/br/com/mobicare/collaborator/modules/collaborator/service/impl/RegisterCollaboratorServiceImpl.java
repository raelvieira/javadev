package br.com.mobicare.collaborator.modules.collaborator.service.impl;

import br.com.mobicare.collaborator.exceptions.BadRequestException;
import br.com.mobicare.collaborator.models.Collaborator;
import br.com.mobicare.collaborator.modules.collaborator.controller.json.request.CollaboratorRequest;
import br.com.mobicare.collaborator.modules.collaborator.controller.json.response.CollaboratorResponse;
import br.com.mobicare.collaborator.modules.collaborator.repository.BlacklistRepository;
import br.com.mobicare.collaborator.modules.collaborator.repository.CollaboratorRepository;
import br.com.mobicare.collaborator.modules.collaborator.service.RegisterCollaboratorService;
import br.com.mobicare.collaborator.modules.collaborator.service.RegisterValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterCollaboratorServiceImpl implements RegisterCollaboratorService {
    private final CollaboratorRepository repository;
    private final BlacklistRepository blacklistRepository;
    private final RegisterValidator registerValidator;

    public RegisterCollaboratorServiceImpl(CollaboratorRepository repository, BlacklistRepository blacklistRepository, RegisterValidator registerValidator) {
        this.repository = repository;
        this.blacklistRepository = blacklistRepository;
        this.registerValidator = registerValidator;
    }

    @Override
    @Transactional
    public CollaboratorResponse execute(CollaboratorRequest request) {
        final Collaborator collaborator = request.toModel();

        this.blacklistRepository.findByCpf(request.getCpf()).ifPresent(b -> {
            throw new BadRequestException("Este Colaborador está incluso na Blacklist!");
        });

        final var isValid = this.registerValidator.execute(collaborator);

        if(!isValid) {
            throw new BadRequestException("Este colaborador não atende os requisitos de idade da empresa!");
        }

        return CollaboratorResponse.toResponse(this.repository.save(collaborator));
    }
}
