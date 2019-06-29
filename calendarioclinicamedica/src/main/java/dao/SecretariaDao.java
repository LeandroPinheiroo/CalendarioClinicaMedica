/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.protobuf.ServiceException;
import excecao.ServicoException;
import java.util.List;
import javax.persistence.NoResultException;
import modelo.Secretaria;

/**
 *
 * @author leandro
 */
/*classe de dao do prontuario que extende do dao generico os métodos padrões*/
public class SecretariaDao extends GenericDao<Secretaria, Integer> {
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected SecretariaDao() {
        super(Secretaria.class);
    }
    /*método para buscar uma lista de secretárias pelo nome*/
    protected List<Secretaria> buscaPeloNome(String nome) throws ServicoException {
        /*retorna a lista de secretarias pelo nome*/
        return em.createQuery("select s from Secretaria s where m.nome like :nome", Secretaria.class)
                .setParameter("nome", nome+"%").getResultList();
    }
    
    /*método para buscar uma secretaria pelo seu cpf*/
    protected Secretaria buscaPorCPF(String cpf) throws ServicoException {
        /*tenta buscar a secretaria pelo cpf*/
        try{
            /*se encontrar, retorna a secretaria*/
            return em.createQuery("select s from Secretaria s where s.cpf = :cpf", Secretaria.class)
                    .setParameter("cpf", cpf).getSingleResult();
        }catch(NoResultException no){
            /*caso não encontre, retorna null*/
            return null;
        }
    }
    
}
