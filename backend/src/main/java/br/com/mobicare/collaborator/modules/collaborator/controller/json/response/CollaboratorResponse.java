package br.com.mobicare.collaborator.modules.collaborator.controller.json.response;

import br.com.mobicare.collaborator.models.Collaborator;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CollaboratorResponse {
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    public static CollaboratorResponse toResponse(Collaborator collaborator) {
        return new CollaboratorResponse(
                collaborator.getId(),
                collaborator.getName(),
                collaborator.getEmail(),
                Period.between(collaborator.getBirthday(), LocalDate.now()).getYears()
        );
    }
}
