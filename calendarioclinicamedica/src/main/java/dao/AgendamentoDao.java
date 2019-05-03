/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.protobuf.ServiceException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import model.Agendamento;

/**
 *
 * @author leandro
 */
public class AgendamentoDao extends GenericDao<Agendamento, Integer> {

    protected List<Agendamento> getByDataHora(Date data, Time hora) throws ServiceException {
        return em.createQuery("select a from Agendamento a where a.data= :data and a.hora = :hora", Agendamento.class)
                .setParameter("data", data).setParameter("hora", hora).getResultList();
    }

    protected List<Agendamento> getByData(Date data) throws ServiceException {
        return em.createQuery("select a from Agendamento a where a.data= :data", Agendamento.class)
                .setParameter("data", data).getResultList();
    }
    
    protected List<Agendamento> getByDataRange(Date menorData, Date maiorData) throws ServiceException {
        return em.createQuery("\"select a from Agendamento a where a.paidDay between :menorData and :maiorData", Agendamento.class)
                .setParameter("lessPaidDay",menorData).setParameter("higherPaidDay", maiorData).getResultList();
    }
}
