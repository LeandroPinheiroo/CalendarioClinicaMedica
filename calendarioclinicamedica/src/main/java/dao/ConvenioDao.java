/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecao.ServicoException;
import javax.persistence.NoResultException;
import modelo.Convenio;

/**
 *
 * @author leandro
 */
/*classe de dao do convenio que extende do dao generico os métodos padrões*/


public class ConvenioDao extends GenericDao<Convenio, Integer>{
    
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected ConvenioDao() {
        super(Convenio.class);
    }
    /*método para retornar o convenio pelo nome*/
    protected Convenio buscaPorNome(String nome) throws ServicoException {
        /*tenta retornar o convenio pelo nome fornecido*/
        try{
            return em.createQuery("select c from Convenio c where c.nome = :nome", Convenio.class)
                    .setParameter("nome", nome).getSingleResult();
        }catch(NoResultException no){
            /*se não encontrar nada, retorna null*/
            return null;
        }
    }
}
