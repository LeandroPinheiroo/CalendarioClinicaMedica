/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecao.ServicoException;
import javax.persistence.NoResultException;
import model.Procedimento;

/**
 *
 * @author leandro
 */
/*classe de dao do procedimento que extende do dao generico os métodos padrões*/
public class ProcedimentoDao extends GenericDao<Procedimento, Integer>{
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected ProcedimentoDao() {
        super(Procedimento.class);
    }
    
    /*método para buscar um procedimento pelo seu nome*/
    protected Procedimento buscaPeloNome(String nome) throws ServicoException{
        /*tenta buscar o procedimento pelo seu nome*/
        try{
           /*caso consiga, retorna o procedimento*/
           return em.createQuery("select p from Procedimento p where p.nome like :nome",Procedimento.class)
                .setParameter("nome", nome+"%").getSingleResult(); 
        }catch(NoResultException no){
            /*se não encontrar, retorna null*/
            return null;
        }
    }
}
