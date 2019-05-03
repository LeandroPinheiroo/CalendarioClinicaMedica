/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.protobuf.ServiceException;
import model.Especializacao;

/**
 *
 * @author leandro
 */
public class EspecializacaoDao extends GenericDao<Especializacao, Integer> {

    public Especializacao getByNome(String nome) throws ServiceException {
        return em.createQuery("select e from Especializacao e where e.nome = :nome", Especializacao.class)
                .setParameter("nome", nome).getSingleResult();
    }
}
