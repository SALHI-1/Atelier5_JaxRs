package com.fstt.atelier5.atelier5_jax_rs.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carburant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Carburant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    // Un carburant peut être dans plusieurs historiques
    @OneToMany(mappedBy = "carburant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonbTransient // Empêche les boucles infinies
    private List<HistoCarb> historiques;

}