/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.protobuf.ServiceException;
import java.util.List;
import model.Cidade;

/**
 *
 * @author leandro
 */
public class CidadeDao extends GenericDao<Cidade, Integer> {

    public Cidade getByNomeEstado(String city, String state) throws ServiceException {
        return em.createQuery("select c from Cidade c where c.nome = :cidade and c.estado = :estado", Cidade.class)
                .setParameter("cidade", city).setParameter("estado", state).getSingleResult();
    }

    public List<Cidade> getByEstado(String estado) throws ServiceException {
        return em.createQuery("select c from Cidade c where c.estado = :estado", Cidade.class)
                .setParameter("estado", estado).getResultList();
    }
}
