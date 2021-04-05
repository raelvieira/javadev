package br.com.mobicare.collaborator.modules.collaborator.service;

import br.com.mobicare.collaborator.modules.collaborator.controller.json.request.CollaboratorRequest;
import br.com.mobicare.collaborator.modules.collaborator.controller.json.response.CollaboratorResponse;

public interface RegisterCollaboratorService {
    CollaboratorResponse execute(CollaboratorRequest request);
}
