/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecao.ServicoException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import model.Agendamento;

/**
 *
 * @author leandro
 */
/*classe de dao do agendamento que extende do dao generico os métodos padrões*/
public class AgendamentoDao extends GenericDao<Agendamento, Integer> {
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected AgendamentoDao() {
        super(Agendamento.class);
    }
    /*método para buscar agendamentos pela data e hora*/
    protected List<Agendamento> buscaPorDataHora(Date data, Time hora) throws ServicoException {
        /*retorna a lista de agendamentos pela data e hora*/
        return em.createQuery("select a from Agendamento a where a.data= :data and a.hora = :hora", Agendamento.class)
                .setParameter("data", data).setParameter("hora", hora).getResultList();
    }
    /*método para buscar um agendamento por uma data específica*/
    protected List<Agendamento> buscaPorData(Date data) throws ServicoException {
        /*retorna a lista de agendamentos pela data*/
        return em.createQuery("select a from Agendamento a where a.data= :data", Agendamento.class)
                .setParameter("data", data).getResultList();
    }
    /*método para buscar uma lista de agendamentos por um intervalo de datas*/
    protected List<Agendamento> buscaPorIntervaloDatas(Date menorData, Date maiorData) throws ServicoException {
        /*retorna a lista de agendamentos por um intervalo de datas*/
        return em.createQuery("\"select a from Agendamento a where a.data between :menorData and :maiorData order by a.hora", Agendamento.class)
                .setParameter("lessPaidDay",menorData).setParameter("higherPaidDay", maiorData).getResultList();
    }
}
