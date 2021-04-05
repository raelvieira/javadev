package br.com.mobicare.collaborator.modules.collaborator.service.impl;

import br.com.mobicare.collaborator.exceptions.NotFoundException;
import br.com.mobicare.collaborator.models.Collaborator;
import br.com.mobicare.collaborator.modules.collaborator.repository.CollaboratorRepository;
import br.com.mobicare.collaborator.modules.collaborator.service.UpdateCollaboratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UpdateCollaboratorServiceImpl implements UpdateCollaboratorService {

    private final CollaboratorRepository repository;

    public UpdateCollaboratorServiceImpl(CollaboratorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        final Collaborator collaborator = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Nenhum colaborador cadastrado para este ID!")
        );

        collaborator.setDeletedAt(LocalDateTime.now());
        this.repository.save(collaborator);
    }
}
