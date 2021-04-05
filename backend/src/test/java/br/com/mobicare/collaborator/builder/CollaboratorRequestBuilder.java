package br.com.mobicare.collaborator.builder;

import br.com.mobicare.collaborator.modules.collaborator.controller.json.request.CollaboratorRequest;

import java.time.LocalDate;

public class CollaboratorRequestBuilder {
    CollaboratorRequest request;

    public static CollaboratorRequestBuilder builder() {
        CollaboratorRequestBuilder builder = new CollaboratorRequestBuilder();
        builder.request = new CollaboratorRequest();
        builder.request.setName("Name");
        builder.request.setCpf("11111111111");
        builder.request.setBirthday(LocalDate.now());
        builder.request.setEmail("email@email.com");
        builder.request.setPhone(9840028922L);
        builder.request.setIdSector(1);

        return builder;
    }

    public CollaboratorRequest build() {
        return this.request;
    }
}
