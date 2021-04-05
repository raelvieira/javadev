package br.com.mobicare.collaborator.modules.collaborator.service;

import br.com.mobicare.collaborator.modules.collaborator.controller.json.response.CollaboratorResponse;

import java.util.List;

public interface FindCollaborator {
    List<CollaboratorResponse> findAllBySector();

    CollaboratorResponse findById(Integer id);

    CollaboratorResponse findByCpf(String cpf);
}
