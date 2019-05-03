/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.protobuf.ServiceException;
import model.Convenio;

/**
 *
 * @author leandro
 */
public class ConvenioDao extends GenericDao<Convenio, Integer>{
    
     public Convenio getByNome(String nome) throws ServiceException {
        return em.createQuery("select c from Convenio c where c.nome = :nome", Convenio.class)
                .setParameter("nome", nome).getSingleResult();
    }
}
