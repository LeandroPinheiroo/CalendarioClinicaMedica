/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.NoResultException;
import modelo.Consulta;

/**
 *
 * @author leandro
 */
/*classe de dao da consulta que extende do dao generico os métodos padrões*/
public class ConsultaDao extends GenericDao<Consulta, Integer> {
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected ConsultaDao() {
        super(Consulta.class);
    }
    
    /*método para buscar a ultima consulta inserida*/
    protected Consulta buscaUltimaConsulta(){
        /*tenta realizar a busca*/
        try{
            /*se encontrar, retorna ela*/
            return em.createQuery("select c from Consulta c order by c.id desc",Consulta.class).setMaxResults(1)
                    .getSingleResult();
         /*caso não consiga encontra, retorna null*/
        }catch(NoResultException no){
            return null;
        }
    }
    
}
