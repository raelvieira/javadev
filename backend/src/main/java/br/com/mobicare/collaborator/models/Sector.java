package br.com.mobicare.collaborator.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false, length = 150)
    private String description;
}
