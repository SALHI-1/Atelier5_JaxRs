package com.fstt.atelier5.atelier5_jax_rs.services;

import com.fstt.atelier5.atelier5_jax_rs.entities.Carburant;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CarburantService {

    @PersistenceContext(unitName = "atelier-pu")
    private EntityManager em;

    public List<Carburant> findAll() {
        return em.createQuery("SELECT c FROM Carburant c", Carburant.class).getResultList();
    }

    public Carburant findById(Long id) {
        return em.find(Carburant.class, id);
    }

    @Transactional
    public Carburant create(Carburant carburant) {
        em.persist(carburant);
        return carburant;
    }

    @Transactional
    public Carburant update(Long id, Carburant carburant) {
        Carburant existing = em.find(Carburant.class, id);
        if (existing != null) {
            existing.setNom(carburant.getNom());
            existing.setDescription(carburant.getDescription());
            return em.merge(existing);
        }
        return null;
    }

    @Transactional
    public void delete(Long id) {
        Carburant existing = em.find(Carburant.class, id);
        if (existing != null) {
            em.remove(existing);
        }
    }
}