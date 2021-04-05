package br.com.mobicare.collaborator.modules.user.service.impl;

import br.com.mobicare.collaborator.config.security.TokenService;
import br.com.mobicare.collaborator.exceptions.ForbiddenException;
import br.com.mobicare.collaborator.modules.user.controller.json.request.LoginRequest;
import br.com.mobicare.collaborator.modules.user.controller.json.response.LoginResponse;
import br.com.mobicare.collaborator.modules.user.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authManager;

    private final TokenService tokenService;

    public LoginServiceImpl(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @Override
    public LoginResponse execute(LoginRequest request) {
        final var loginData = request.getUsernamePasswordAuthenticationToken();

        try {
            Authentication authenticate = authManager.authenticate(loginData);
            final String token = tokenService.getToken(authenticate);
            return LoginResponse.toResponse(token, "Bearer");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new ForbiddenException("A autenticação falhou. Por favor, verifique seus dados!");
        }
    }
}
