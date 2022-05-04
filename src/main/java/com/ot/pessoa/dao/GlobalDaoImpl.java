package com.ot.pessoa.dao;

import com.ot.pessoa.dao.exceptions.MyExceptions;
import com.ot.pessoa.domain.Pessoa;
import com.ot.pessoa.domain.Produto;
import com.ot.pessoa.domain.Telefone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class GlobalDaoImpl implements GlobalDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
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
    public boolean deletePessoa(Long id) {
        try {
            Pessoa pessoa = findByIdPessoa(id);
            if (pessoa.getTelefones().isEmpty()) {
                em.remove(em.getReference(Pessoa.class, id));
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
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
            list = em.createQuery("select p from Pessoa p order by p.nome").getResultList();
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
    public List<Telefone> findTelefoneByPessoa(Long id, Pessoa pessoa) {
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

    @Override
    public List<Produto> findProdutoByPessoa(Long id, Pessoa pessoa) {
        try {
            List<Produto> list;
            pessoa = findByIdPessoa(id);
            list = em.createQuery("select produto from Produto produto inner join Pessoa pessoa on produto.cliente.id = pessoa.id where pessoa.id = "+ pessoa.getId()).getResultList();
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
    public boolean validationPessoa(Pessoa pessoa) {
        try {
            String cpfRegex = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}";
            Pattern pattern = Pattern.compile(cpfRegex);
            Matcher matcher = pattern.matcher(pessoa.getCpf());
            if (matcher.matches() && pessoa.getNome() != null) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (MyExceptions e) {
            throw new MyExceptions(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validationTelefone(Telefone telefone) {
        try {
            String numeroRegex = "[0-9]{2}\\ [0-9]{5}\\-[0-9]{4}";
            Pattern pattern = Pattern.compile(numeroRegex);
            Matcher matcher = pattern.matcher(telefone.getNumero());
            if (matcher.matches() && telefone.getObs() != null) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}