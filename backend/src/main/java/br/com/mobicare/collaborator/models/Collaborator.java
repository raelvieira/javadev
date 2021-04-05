package br.com.mobicare.collaborator.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Data
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(name = "phone", length = 11)
    private Long phone;

    @Column(name = "email", length = 150, unique = true)
    private String email;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "id_sector", nullable = false, referencedColumnName = "id")
    private Sector sector;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PreRemove
    protected void preRemove() {
        this.deletedAt = LocalDateTime.now();
    }

    public Integer getAge() {
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }
}
