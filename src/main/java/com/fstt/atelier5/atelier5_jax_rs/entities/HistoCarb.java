package com.fstt.atelier5.atelier5_jax_rs.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "histo_carb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HistoCarb implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private double prix;

    // Plusieurs historiques peuvent pointer vers UNE station
    @ManyToOne
    @JoinColumn(name = "station_id")
    @JsonbTransient // Important: évite la boucle (Station -> HistoCarb -> Station...)
    private Station station;

    // Plusieurs historiques peuvent pointer vers UN carburant
    @ManyToOne
    @JoinColumn(name = "carburant_id")
    private Carburant carburant; // On garde celui-ci pour voir les détails du carburant
}