package com.fstt.atelier5.atelier5_jax_rs.services;

import com.fstt.atelier5.atelier5_jax_rs.entities.Station;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped // Bean CDI
public class StationService {

    @PersistenceContext(unitName = "atelier-pu") // Injection de l'EntityManager
    private EntityManager em;

    public List<Station> findAll() {
        return em.createQuery("SELECT s FROM Station s", Station.class).getResultList();
    }

    public Station findById(Long id) {
        return em.find(Station.class, id);
    }

    @Transactional // Nécessaire pour les écritures (create, update, delete)
    public Station create(Station station) {
        em.persist(station);
        return station;
    }

    @Transactional
    public Station update(Long id, Station station) {
        Station existing = em.find(Station.class, id);
        if (existing != null) {
            existing.setNom(station.getNom());
            existing.setVille(station.getVille());
            existing.setAdresse(station.getAdresse());
            return em.merge(existing);
        }
        return null;
    }

    @Transactional
    public void delete(Long id) {
        Station station = em.find(Station.class, id);
        if (station != null) {
            em.remove(station);
        }
    }
}