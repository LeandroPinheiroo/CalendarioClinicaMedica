/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import com.google.protobuf.ServiceException;
import dao.AgendamentoDao;
import exception.RegraDeNegocioException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import model.Agendamento;

/**
 *
 * @author leandro
 */
public class AgendamentoService {

    private AgendamentoDao dao;

    public AgendamentoService() {
        this.dao = new AgendamentoDao();
    }

    public Agendamento getByCodigo(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");

        }
        return dao.getId(codigo);
    }

    public void salvar(Agendamento entidade) throws RegraDeNegocioException {
        if (entidade == null) {
            throw new RegraDeNegocioException("Informe a entidade");
        } else if (entidade.getData() == null) {
            throw new RegraDeNegocioException("Informe a data do agendamento");
        } else if (entidade.getHora() == null) {
            throw new RegraDeNegocioException("Informe a hora do agendamento");
        } else if (entidade.getMedico() == null) {
            throw new RegraDeNegocioException("Informe o médico");
        } else if (entidade.getPaciente() == null) {
            throw new RegraDeNegocioException("Informe o paciente");
        } else if (entidade.getPreco() < 0) {
            throw new RegraDeNegocioException("O preço não deve ser menor que zero");
        } else if (entidade.getProcedimentos() == null) {
            throw new RegraDeNegocioException("Informe os procedimentos");
        } else if (entidade.getConsulta() == null) {
            throw new RegraDeNegocioException("O preço não deve ser menor que zero");
        }
        dao.salvar(entidade);
    }

    public Agendamento remover(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Agendamento agendamento = this.getId(codigo);
        dao.remover(codigo);
        return agendamento;
    }

    public Agendamento getId(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Agendamento agendamento = dao.getId(codigo);
        return agendamento;
    }

    public List<Agendamento> getAll() {
        return dao.getAll();
    }

    public List<Agendamento> getByDataHora(Date data, Time hora) throws RegraDeNegocioException, ServiceException {
        if (data == null) {
            throw new RegraDeNegocioException("Informe a data.");
        } else if (hora == null) {
            throw new RegraDeNegocioException("Informe a hora.");
        }
        return dao.getByDataHora(data, hora);
    }

    public List<Agendamento> getByData(Date data) throws ServiceException, RegraDeNegocioException {
        if (data == null) {
            throw new RegraDeNegocioException("Informe a data.");
        }
        return dao.getByData(data);
    }

    public List<Agendamento> getByDataRange(Date menorData, Date maiorData) throws ServiceException, RegraDeNegocioException {
        if (menorData == null) {
            throw new RegraDeNegocioException("Informe a menor data.");
        } else if (maiorData == null) {
            throw new RegraDeNegocioException("Informe a maior data.");
        }
        return dao.getByDataRange(menorData, maiorData);
    }
    
}
