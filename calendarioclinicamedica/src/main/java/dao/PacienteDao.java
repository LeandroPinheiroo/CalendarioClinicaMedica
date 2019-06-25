/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecao.ServicoException;
import java.util.List;
import javax.persistence.NoResultException;
import model.Paciente;

/**
 *
 * @author leandro
 */
/*classe de dao do paciente que extende do dao generico os métodos padrões*/
public class PacienteDao extends GenericDao<Paciente, Integer> {
    
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected PacienteDao() {
        super(Paciente.class);
    }
    /*método para buscar uma lista de pacientes pelos nomes*/
    protected List<Paciente> buscaPeloNome(String nome) throws ServicoException {
        /*retorna a lista de acordo com o nome*/
        return em.createQuery("select p from Paciente p where p.nome like :nome", Paciente.class)
                .setParameter("nome", nome+"%").getResultList();
    }
    /*método para buscar um paciente pelo seu cpf*/
    protected Paciente buscaPeloCPF(String cpf) throws ServicoException {
        /*tenta retornar o paciente caso encontre*/
        try{
            return em.createQuery("select p from Paciente p where p.cpf = :cpf", Paciente.class)
                    .setParameter("cpf", cpf).getSingleResult();
        }catch(NoResultException no){
            /*se não encontrar, retorna null*/
            return null;
        }
    }
}
