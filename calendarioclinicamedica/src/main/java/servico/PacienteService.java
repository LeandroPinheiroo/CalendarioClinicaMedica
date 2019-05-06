/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import com.google.protobuf.ServiceException;
import dao.PacienteDao;
import exception.RegraDeNegocioException;
import java.util.List;
import model.Paciente;

/**
 *
 * @author leandro
 */
public class PacienteService {

    private PacienteDao dao;

    public PacienteService() {
        dao = new PacienteDao();
    }

    public Paciente getByCodigo(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        return dao.getId(codigo);
    }

    public void salvar(Paciente entidade) throws RegraDeNegocioException {
        if (entidade == null) {
            throw new RegraDeNegocioException("Informe a entidade");
        } else if (entidade.getNome() == null) {
            throw new RegraDeNegocioException("Informe o nome do paciente");
        } else if (entidade.getCpf() == null) {
            throw new RegraDeNegocioException("Informe o CPF do paciente");
        } else if (entidade.getCelular() == null) {
            throw new RegraDeNegocioException("Informe o celular do paciente");
        } else if (entidade.getEmail() == null) {
            throw new RegraDeNegocioException("Informe o E-mail do paciente");
        }
        dao.salvar(entidade);
    }

    public Paciente remover(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Paciente paciente = this.getId(codigo);
        dao.remover(codigo);
        return paciente;
    }

    public Paciente getId(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Paciente paciente = dao.getId(codigo);
        return paciente;
    }

    public List<Paciente> getAll() {
        return dao.getAll();
    }

    public List<Paciente> getByNome(String nome) throws ServiceException, RegraDeNegocioException {
        if (nome == null) {
            throw new RegraDeNegocioException("Informe o nome do paciente");
        }
        return dao.getByNome(nome);
    }

    public Paciente getByCpf(String crm) throws ServiceException, RegraDeNegocioException {
        if (crm == null) {
            throw new RegraDeNegocioException("Informe o CPF do paciente");
        }
        return dao.getByCpf(crm);
    }
}
