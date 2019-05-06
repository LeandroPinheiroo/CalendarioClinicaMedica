/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.protobuf.ServiceException;
import java.util.List;
import model.Secretaria;

/**
 *
 * @author leandro
 */
public class SecretariaDao extends GenericDao<Secretaria, Integer> {
    
    public List<Secretaria> getByNome(String nome) throws ServiceException {
        return em.createQuery("select s from Secretaria s where m.nome like :nome", Secretaria.class)
                .setParameter("nome", nome).getResultList();
    }
    public Secretaria getByCpf(String cpf) throws ServiceException {
        return em.createQuery("select s from Secretaria s where s.cpf = :cpf", Secretaria.class)
                .setParameter("cpf", cpf).getSingleResult();
    }
    
}
