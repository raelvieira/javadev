package br.com.mobicare.collaborator.modules.user.service;

import br.com.mobicare.collaborator.modules.user.controller.json.request.LoginRequest;
import br.com.mobicare.collaborator.modules.user.controller.json.response.LoginResponse;

public interface LoginService {
    LoginResponse execute(LoginRequest request);
}
