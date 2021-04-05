package br.com.mobicare.collaborator.builder;

import br.com.mobicare.collaborator.models.Collaborator;
import br.com.mobicare.collaborator.models.Sector;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CollaboratorBuilder {
    private Collaborator collaborator;

    public static CollaboratorBuilder builder() {
        CollaboratorBuilder builder = new CollaboratorBuilder();
        Sector sector = new Sector();

        builder.collaborator = new Collaborator();
        builder.collaborator.setId(1);
        builder.collaborator.setName("Name");
        builder.collaborator.setCpf("11111111111");
        builder.collaborator.setBirthday(LocalDate.now());
        builder.collaborator.setEmail("email@email.com");
        builder.collaborator.setPhone(9840028922L);
        builder.collaborator.setCreatedAt(LocalDateTime.now());

        sector.setId(1);
        sector.setDescription("TECNOLOGIA");
        builder.collaborator.setSector(sector);

        return builder;
    }

    public Collaborator build() {
        return this.collaborator;
    }
}
