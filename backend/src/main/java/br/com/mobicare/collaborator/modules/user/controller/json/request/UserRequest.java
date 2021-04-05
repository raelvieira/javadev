package br.com.mobicare.collaborator.modules.user.controller.json.request;

import br.com.mobicare.collaborator.models.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserRequest {
    @NotEmpty(message = "Você deve informar um nome para criar um usuário!")
    @Size(min = 4, max = 100, message = "O nome deve conter entre 4 e 100 caracteres")
    private String name;

    @Email(message = "Por favor, informe um email válido!")
    private String email;

    @NotEmpty(message = "Você deve informar uma senha para criar um usuário!")
    @Size(min = 1, max = 255)
    private String password;

    @NotEmpty(message = "Você deve informar ao menos um perfil para criar um usuário!")
    private List<String> profileName;

    public User toModel() {
        final var encryptedPassword = new BCryptPasswordEncoder().encode(this.password);

        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(encryptedPassword);

        return user;
    }
}