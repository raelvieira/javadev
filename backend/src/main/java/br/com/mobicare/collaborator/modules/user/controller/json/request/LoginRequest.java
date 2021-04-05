package br.com.mobicare.collaborator.modules.user.controller.json.request;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @NotEmpty(message = "Você deve informar um email para acessar o sistema!")
    private String email;

    @NotEmpty(message = "Você deve informar uma senha para acessar o sistema!")
    private String password;

    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }
}