/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author devai
 */
public abstract class GenericDao<T, ID extends Serializable> implements IGenericDao<T, ID> {

    protected EntityManager em;
    private Class<T> entidade;

    public GenericDao() {
        this.em = Conexao.getConnection();

        entidade = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public void salvar(T entidade) {
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
    }

    @Override
    public boolean remover(ID id) {

        T aux = getId(id);

        if (aux != null) {
            em.getTransaction().begin();
            em.remove(aux);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public T getId(ID id) {
        return em.find(entidade, id);
    }

    @Override
    public List<T> getAll() {

        String jpql = "select e from "
                + entidade.getName() + " e ";
        return em.createQuery(jpql).getResultList();
    }

}
