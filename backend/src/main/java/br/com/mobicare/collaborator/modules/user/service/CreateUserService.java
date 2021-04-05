package br.com.mobicare.collaborator.modules.user.service;

import br.com.mobicare.collaborator.modules.user.controller.json.request.UserRequest;
import br.com.mobicare.collaborator.modules.user.controller.json.response.UserResponse;

public interface CreateUserService {
    UserResponse execute(UserRequest request);
}
