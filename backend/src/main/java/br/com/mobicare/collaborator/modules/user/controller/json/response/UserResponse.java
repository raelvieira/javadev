package br.com.mobicare.collaborator.modules.user.controller.json.response;

import br.com.mobicare.collaborator.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse {
    private Integer id;
    private String name;
    private String email;

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}