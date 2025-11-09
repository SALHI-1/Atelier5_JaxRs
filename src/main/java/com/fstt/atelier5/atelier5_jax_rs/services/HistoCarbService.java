package com.fstt.atelier5.atelier5_jax_rs.services;

import com.fstt.atelier5.atelier5_jax_rs.entities.Carburant;
import com.fstt.atelier5.atelier5_jax_rs.entities.HistoCarb;
import com.fstt.atelier5.atelier5_jax_rs.entities.Station;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class HistoCarbService {

    @PersistenceContext(unitName = "atelier-pu")
    private EntityManager em;

    // Obtenir tous les prix pour une station donnée
    public List<HistoCarb> findByStationId(Long stationId) {
        return em.createQuery("SELECT h FROM HistoCarb h WHERE h.station.id = :stationId", HistoCarb.class)
                .setParameter("stationId", stationId)
                .getResultList();
    }

    // Ajouter un nouveau prix
    @Transactional
    public HistoCarb addPrice(Long stationId, Long carburantId, HistoCarb histoCarb) {
        Station station = em.find(Station.class, stationId);
        Carburant carburant = em.find(Carburant.class, carburantId);

        if (station == null || carburant == null) {
            throw new IllegalArgumentException("Station ou Carburant non trouvé");
        }

        histoCarb.setStation(station);
        histoCarb.setCarburant(carburant);
        em.persist(histoCarb);
        return histoCarb;
    }
}