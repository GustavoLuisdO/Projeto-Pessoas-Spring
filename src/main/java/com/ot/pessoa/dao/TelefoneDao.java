package com.ot.pessoa.dao;

import com.ot.pessoa.domain.Telefone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(noRollbackFor=RuntimeException.class)
public class TelefoneDao {

    @PersistenceContext
    private EntityManager em;

    public void create(Telefone telefone) {
        try {
            em.persist(telefone);
        }
        catch (UnexpectedRollbackException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Telefone telefone) {
        try {
            em.merge(telefone);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            em.remove(em.getReference(Telefone.class, id));
        }
        catch (Exception e){
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
            list = em.createQuery("select t from Telefone t").getResultList();
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}