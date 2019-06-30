/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecao.ServicoException;
import java.util.List;
import javax.persistence.NoResultException;
import modelo.Medico;

/**
 *
 * @author leandro
 */
/*classe de dao do médico que extende do dao generico os métodos padrões*/
public class MedicoDao extends GenericDao<Medico, Integer> {
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected MedicoDao() {
        super(Medico.class);
    }
    /*método para buscar o médico por seu nome */
    protected List<Medico> buscaPeloNome(String nome) throws ServicoException {
        /*retorna a lista de médicos pelo nome fornecido*/
        return em.createQuery("select m from Medico m where m.nome like :nome", Medico.class)
                .setParameter("nome", nome+"%").getResultList();
    }
    /*método para buscar o médico pela crm dele*/
    protected Medico buscaPeloCRM(String crm) throws ServicoException {
        /*tenta retornar o médico pela crm fornecida*/
        try{
            return em.createQuery("select m from Medico m where m.crm = :crm", Medico.class)
                .setParameter("crm", crm).getSingleResult();
        }catch(NoResultException no){
            /*se não encontrar, retorna null*/
            return null;
        }
    }
    /*método para buscar o médico pela cpf dele*/
    protected Medico buscaPeloCPF(String cpf) throws ServicoException {
        /*tenta retornar o médico pela cpf fornecida*/
        try{
            return em.createQuery("select m from Medico m where m.cpf = :cpf", Medico.class)
                .setParameter("cpf", cpf).getSingleResult();
        }catch(NoResultException no){
            /*se não encontrar, retorna null*/
            return null;
        }
    } 
}
