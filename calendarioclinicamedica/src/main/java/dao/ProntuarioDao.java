/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecao.ServicoException;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import model.Paciente;
import model.Prontuario;

/**
 *
 * @author leandro
 */
/*classe de dao do prontuario que extende do dao generico os métodos padrões*/
public class ProntuarioDao extends GenericDao<Prontuario, Integer>{
    
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected ProntuarioDao() {
        super(Prontuario.class);
    }
    /*busca os prontuarios por data */
    protected List<Prontuario> buscaPorData(Date data) throws ServicoException {
        /*retorna a lista de prontuarios pela data fornecida*/
        return em.createQuery("select p from Prontuario p where p.data= :data", Prontuario.class)
                .setParameter("data", data).getResultList();
    }
    /*busca os prontuarios por um invervalo de datas*/
    protected List<Prontuario> buscaPorIntervaloData(Date menorData, Date maiorData) throws ServicoException {
        /*retorna a lista de prontuarios pelas datas fornecidas*/
        return em.createQuery("select p from Prontuario p where p.data between :menorData and :maiorData", Prontuario.class)
                .setParameter("menorData",menorData).setParameter("maiorData", maiorData).getResultList();
    }
    
    /*método para buscar um prontuario pelo paciente*/
    protected Prontuario buscaPeloPaciente(Paciente paciente) throws ServicoException{
        /*tenta buscuar o prontuario*/
        try{
            /*se conseguir, retorna o prontuario de acordo com o paciente*/
            return em.createQuery("select p from Prontuario p where p.paciente = :paciente",Prontuario.class)
                    .setParameter("paciente", paciente).getSingleResult();
        }catch(NoResultException no){
            /*se não encontrar retorna null*/
            return null;
        }
    }
    
}
