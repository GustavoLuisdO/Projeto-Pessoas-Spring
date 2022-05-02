package com.ot.pessoa.dao;

import com.ot.pessoa.domain.Pessoa;
import com.ot.pessoa.domain.Telefone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class GlobalDaoImpl implements GlobalDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void create(Object obj) {
        try {
            em.persist(obj);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    @Transactional
    @Override
    public void update(Object obj) {
        try {
            em.merge(obj);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    @Transactional
    @Override
    public void deletePessoa(Long id) {
        try {
            em.remove(em.getReference(Pessoa.class, id));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    @Override
    public void deleteTelefone(Long id) {
        try {
            em.remove(em.getReference(Telefone.class, id));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    @Override
    public Pessoa findByIdPessoa(Long id) {
        try {
            Pessoa pessoa = em.find(Pessoa.class, id);
            return pessoa;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Telefone findByIdTelefone(Long id) {
        try {
            Telefone telefone = em.find(Telefone.class, id);
            return telefone;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> findAllPessoas() {
        try {
            List<Object> list;
            list = em.createQuery("select p from Pessoa p").getResultList();
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<Telefone> findByPessoa(Long id, Pessoa pessoa) {
        try {
            List<Telefone> list;
            pessoa = findByIdPessoa(id);
            list = em.createQuery("select telefone from Telefone telefone inner join Pessoa pessoa on telefone.dono.id = pessoa.id where pessoa.id = "+ pessoa.getId()).getResultList();
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            em.close();
        }
    }
}