/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecao.ServicoException;
import java.util.List;
import javax.persistence.NoResultException;
import model.Cidade;

/**
 *
 * @author leandro
 */
/*classe de dao de cidades que extende do dao generico os métodos padrões*/
public class CidadeDao extends GenericDao<Cidade, Integer> {
    
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected CidadeDao() {
        super(Cidade.class);
    }
    /*método para buscar uma cidade pelo nome da cidade e o estado dela*/
    protected Cidade buscaPorNomeEstado(String cidade, String estado) throws ServicoException {
        /*tenta retorna a cidade pela cidade e pelo estado fornecido*/
        try{
            return em.createQuery("select c from Cidade c where c.nome = :cidade and c.estado = :estado", Cidade.class)
                .setParameter("cidade", cidade).setParameter("estado", estado).getSingleResult();
        }catch(NoResultException no){
            /*caso não encontre, retorna null*/
            return null;
        }
    }
    /*método para buscar uma lista de cidades pelo estado*/
    protected List<Cidade> buscaPorEstado(String estado) throws ServicoException {
        /*retorna a lista de cidades pelo estado fornecido*/
        return em.createQuery("select c from Cidade c where c.estado = :estado", Cidade.class)
                .setParameter("estado", estado).getResultList();
    }
}
