/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.protobuf.ServiceException;
import java.util.List;
import model.Paciente;

/**
 *
 * @author leandro
 */
public class PacienteDao extends GenericDao<Paciente, Integer> {
    
    public List<Paciente> getByNome(String nome) throws ServiceException {
        return em.createQuery("select p from Paciente p where p.nome like :nome", Paciente.class)
                .setParameter("nome", nome).getResultList();
    }
    public Paciente getByCpf(String cpf) throws ServiceException {
        return em.createQuery("select p from Paciente p where p.cpf = :cpf", Paciente.class)
                .setParameter("cpf", cpf).getSingleResult();
    }
    
    
}
