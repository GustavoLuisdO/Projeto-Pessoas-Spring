package com.ot.pessoa.dao;

import com.ot.pessoa.domain.Telefone;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TelefoneDao {

    @PersistenceContext
    private EntityManager em;

    public void create(Telefone telefone) {
        try {
            em.persist(telefone);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Telefone telefone) {
        try {
            em.merge(telefone);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            em.remove(em.getReference(Telefone.class, id));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Telefone findById(Long id) {
        try {
            Telefone telefone = em.find(Telefone.class, id);
            return telefone;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Telefone> findAll() {
        try {
            List<Telefone> list;
            list = em.createQuery("SELECT t FROM Telefone t").getResultList();
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}