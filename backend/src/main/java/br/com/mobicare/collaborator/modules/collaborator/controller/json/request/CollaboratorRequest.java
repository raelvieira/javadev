package br.com.mobicare.collaborator.modules.collaborator.controller.json.request;

import br.com.mobicare.collaborator.models.Collaborator;
import br.com.mobicare.collaborator.models.Sector;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CollaboratorRequest {

    @NotEmpty
    private String name;

    @NotNull
    @CPF
    private String cpf;

    @NotNull
    private Long phone;

    @Email
    private String email;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate birthday;

    @NotNull
    private Integer idSector;

    public Collaborator toModel() {
        Collaborator collaborator = new Collaborator();
        final Sector sector = new Sector();

        collaborator.setName(this.name);
        collaborator.setCpf(this.cpf);
        collaborator.setPhone(this.phone);
        collaborator.setEmail(this.email);
        collaborator.setBirthday(this.birthday);
        sector.setId(this.idSector);
        collaborator.setSector(sector);

        return collaborator;
    }
}
