package com.ot.pessoa.dao;

import com.ot.pessoa.domain.Pessoa;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PessoaDao {

    @PersistenceContext
    private EntityManager em;

    public void create(Pessoa pessoa) {
        try {
            em.persist(pessoa);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Pessoa pessoa) {
        try {
            em.merge(pessoa);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            em.remove(em.getReference(Pessoa.class, id));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Pessoa findById(Long id) {
        try {
            Pessoa pessoa = em.find(Pessoa.class, id);
            return pessoa;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Pessoa> findAll() {
        try {
            List<Pessoa> list;
            list = em.createQuery("select p from Pessoa p").getResultList();
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}