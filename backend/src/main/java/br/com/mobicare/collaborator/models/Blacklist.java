package br.com.mobicare.collaborator.models;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Blacklist implements Persistable<Integer> {
    @Id
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    private String cpf;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Override
    public boolean isNew() {
        return true;
    }
}
