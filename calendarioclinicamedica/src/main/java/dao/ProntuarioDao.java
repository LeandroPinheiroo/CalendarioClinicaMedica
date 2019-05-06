/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.protobuf.ServiceException;
import java.util.Date;
import java.util.List;
import model.Prontuario;

/**
 *
 * @author leandro
 */
public class ProntuarioDao extends GenericDao<Prontuario, Integer>{
    
    public List<Prontuario> getByData(Date data) throws ServiceException {
        return em.createQuery("select p from Prontuario p where p.data= :data", Prontuario.class)
                .setParameter("data", data).getResultList();
    }
    
    public List<Prontuario> getByDataRange(Date menorData, Date maiorData) throws ServiceException {
        return em.createQuery("select p from Prontuario p where p.data between :menorData and :maiorData", Prontuario.class)
                .setParameter("menorData",menorData).setParameter("maiorData", maiorData).getResultList();
    }
    
}
