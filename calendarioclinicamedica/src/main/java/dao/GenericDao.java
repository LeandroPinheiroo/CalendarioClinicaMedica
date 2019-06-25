/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import excecao.ServicoException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/*classe dao generica que reduzirá o tamanho dos outros daos*/
public abstract class GenericDao<T, I extends Serializable>{
    /*gerenciador de entidades*/
    protected EntityManager em;
    /*variavel da classe generica*/
    private Class<T> entityClass;
    /*construtor que passará a classe para a variavel e criará a conexão com o banco*/
    protected GenericDao(Class<T> entityClass){
        em = Conexao.getConexao();
        this.entityClass = entityClass;
    }
    /*método para salvar a classe generica*/
    protected void salvar(T entity) throws ServicoException {
        /*cria a transação para garantir o salvamento*/
        em.getTransaction().begin();
        /*salva a entidade*/
        em.merge(entity);
        /*e finaliza a transação confirmando o salvar*/
        em.getTransaction().commit();
    }
    /*método para atualizar a classe generica*/
    protected void atualizar(T entity) throws ServicoException{
        /*cria a transação para garantir a atualização*/
        em.getTransaction().begin();
        /*atualiza a entidade*/
        em.merge(entity);
        /*da um flush no banco*/
        em.flush();
        /*e finaliza a transação confirmando o update*/
        em.getTransaction().commit();
    }
    /*método para buscar todos os cadastrados da classe abstrata*/
    protected List<T> buscaTodos(){
        /*construi uma query de acordo com a classe que virá*/
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        query.from(entityClass);
        /*e retorna a lista*/
        return em.createQuery(query).getResultList();
    }
    /*método para buscar um item da classe generica de acordo com seu id*/
    protected T buscaPorID(I id) throws ServicoException{
        /*retorna essa item*/
        try {
            return em.find(entityClass, id);
        }catch (NoResultException re){
            return null;
        }
    }
    /*método para buscar uma lista de todos os itens da classe que possuirem status de acordo com o parametro*/
    protected List<T> buscaPorStatus(boolean status){
        /*retorna essa listagem*/
        String hql = "select t from " + entityClass.getName() + " t ";
        return em.createQuery(hql + "where t.status = :status", entityClass).setParameter("status",status)
                .getResultList();
    }
    /*método para remover um item da classe abstrata*/
    protected T remover(I id) throws ServicoException{
        /*busca a entidade pelo seu id*/
        T entity = buscaPorID(id);
        /*inicia a transação para garantir a remoção*/
        em.getTransaction().begin();
        /*remove a entidade*/
        em.remove(entity);
        /*da flush no banco*/
        em.flush();
        /*e finaliza a transação*/
        em.getTransaction().commit();
        /*no fim retorna a entidade*/
        return entity;
    }

}
