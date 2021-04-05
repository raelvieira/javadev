package br.com.mobicare.collaborator.modules.collaborator.service.impl;

import br.com.mobicare.collaborator.exceptions.NotFoundException;
import br.com.mobicare.collaborator.modules.collaborator.controller.json.response.CollaboratorResponse;
import br.com.mobicare.collaborator.modules.collaborator.repository.CollaboratorRepository;
import br.com.mobicare.collaborator.modules.collaborator.service.FindCollaborator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindCollaboratorImpl implements FindCollaborator {
    private final CollaboratorRepository repository;

    public FindCollaboratorImpl(CollaboratorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollaboratorResponse> findAllBySector() {
        final var collaborators = this.repository.findAllGroupBySector();
        return collaborators.stream().map(CollaboratorResponse::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CollaboratorResponse findById(Integer id) {
        final var collaborator = this.repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado nenhum colaborador com este ID!")
        );
        return CollaboratorResponse.toResponse(collaborator);
    }

    @Override
    @Transactional(readOnly = true)
    public CollaboratorResponse findByCpf(String cpf) {
        final var collaborator = this.repository.findByCpf(cpf).orElseThrow(
                () -> new NotFoundException("Não foi encontrado nenhum colaborador com este CPF!")
        );
        return CollaboratorResponse.toResponse(collaborator);
    }
}
