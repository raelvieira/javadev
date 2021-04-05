package br.com.mobicare.collaborator.modules.user.controller;

import br.com.mobicare.collaborator.modules.user.controller.json.request.LoginRequest;
import br.com.mobicare.collaborator.modules.user.controller.json.response.LoginResponse;
import br.com.mobicare.collaborator.modules.user.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ok(loginService.execute(request));
    }
}