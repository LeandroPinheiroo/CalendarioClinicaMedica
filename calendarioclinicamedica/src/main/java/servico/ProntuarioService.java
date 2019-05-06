/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import com.google.protobuf.ServiceException;
import dao.ProntuarioDao;
import exception.RegraDeNegocioException;
import java.util.Date;
import java.util.List;
import model.Prontuario;

/**
 *
 * @author leandro
 */
public class ProntuarioService {

    private ProntuarioDao dao;

    public ProntuarioService() {
        dao = new ProntuarioDao();
    }

    public Prontuario getByCodigo(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        return dao.getId(codigo);
    }

    public void salvar(Prontuario entidade) throws RegraDeNegocioException {
        if (entidade == null) {
            throw new RegraDeNegocioException("Informe a entidade");
        } else if (entidade.getConsultas() == null) {
            throw new RegraDeNegocioException("Informe as consultas");
        } else if (entidade.getData() == null) {
            throw new RegraDeNegocioException("Informe a data");
        } else if (entidade.getMedicos() == null) {
            throw new RegraDeNegocioException("Informe os médicos");
        } else if (entidade.getPaciente() == null) {
            throw new RegraDeNegocioException("Informe o paciente");
        } else if (entidade.getProcedimentos() == null) {
            throw new RegraDeNegocioException("Informe os procedimentos");
        }
        dao.salvar(entidade);
    }

    public Prontuario remover(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Prontuario prontuario = this.getId(codigo);
        dao.remover(codigo);
        return prontuario;
    }

    public Prontuario getId(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Prontuario prontuario = dao.getId(codigo);
        return prontuario;
    }

    public List<Prontuario> getAll() {
        return dao.getAll();
    }

    public List<Prontuario> getByData(Date data) throws ServiceException, RegraDeNegocioException {
        if (data == null) {
            throw new RegraDeNegocioException("Informe a data");
        }
        return dao.getByData(data);
    }

    public List<Prontuario> getByDataRange(Date menorData, Date maiorData) throws ServiceException, RegraDeNegocioException {
        if (menorData == null) {
            throw new RegraDeNegocioException("Informe a menor data");
        } else if (maiorData == null) {
            throw new RegraDeNegocioException("Informe a maior data");
        }
        return dao.getByDataRange(menorData, maiorData);
    }
}
