package br.com.mobicare.collaborator.modules.user.controller.json.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String type;

    public static LoginResponse toResponse(String token, String type) {
        return new LoginResponse(token, type);
    }
}