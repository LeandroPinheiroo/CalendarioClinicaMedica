/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.protobuf.ServiceException;
import java.util.List;
import model.Medico;

/**
 *
 * @author leandro
 */
public class MedicoDao extends GenericDao<Medico, Integer> {
    
    public List<Medico> getByNome(String nome) throws ServiceException {
        return em.createQuery("select m from Medico m where m.nome like :nome", Medico.class)
                .setParameter("nome", nome).getResultList();
    }
    public Medico getByCrm(String crm) throws ServiceException {
        return em.createQuery("select m from Medico m where m.crm = :crm", Medico.class)
                .setParameter("crm", crm).getSingleResult();
    }
    
}
