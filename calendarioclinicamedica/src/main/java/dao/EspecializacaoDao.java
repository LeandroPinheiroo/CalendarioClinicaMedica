/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecao.ServicoException;
import javax.persistence.NoResultException;
import model.Especializacao;

/**
 *
 * @author leandro
 */
/*classe de dao da especialização que extende do dao generico os métodos padrões*/
public class EspecializacaoDao extends GenericDao<Especializacao, Integer> {
    
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected EspecializacaoDao() {
        super(Especializacao.class);
    }
    /*método para buscar uma especialização pelo seu nome*/
    protected Especializacao buscaPeloNome(String nome) throws ServicoException {
        /*tenta retornar a especialização pelo nome fornecedido*/
        try{
            return em.createQuery("select e from Especializacao e where e.nome = :nome", Especializacao.class)
                .setParameter("nome", nome).getSingleResult();
        }catch(NoResultException no){
            /*caso não encontrar retorna null*/
            return null;
        }
    }
}
