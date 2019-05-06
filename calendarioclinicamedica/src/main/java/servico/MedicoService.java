/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import com.google.protobuf.ServiceException;
import dao.MedicoDao;
import exception.RegraDeNegocioException;
import java.util.List;
import model.Medico;

/**
 *
 * @author leandro
 */
public class MedicoService {

    private MedicoDao dao;

    public MedicoService() {
        dao = new MedicoDao();
    }

    public Medico getByCodigo(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        return dao.getId(codigo);
    }

    public void salvar(Medico entidade) throws RegraDeNegocioException {
        if (entidade == null) {
            throw new RegraDeNegocioException("Informe a entidade");
        } else if (entidade.getNome() == null) {
            throw new RegraDeNegocioException("Informe o nome do médico");
        } else if (entidade.getCrm() == null) {
            throw new RegraDeNegocioException("Informe o CRM");
        } else if (entidade.getCpf() == null) {
            throw new RegraDeNegocioException("Informe o CPF do médico");
        } else if (entidade.getCelular() == null) {
            throw new RegraDeNegocioException("Informe o celular do médico");
        } else if (entidade.getLogin() == null) {
            throw new RegraDeNegocioException("Informe o login do médico");
        } else if (entidade.getSenha() == null) {
            throw new RegraDeNegocioException("Informe a senha do médico");
        } else if (entidade.getPermissao() == null) {
            throw new RegraDeNegocioException("Informe a permição do médico");
        } else if (entidade.getEmail() == null) {
            throw new RegraDeNegocioException("Informe o E-mail do médico");
        }
        dao.salvar(entidade);
    }

    public Medico remover(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Medico medico = this.getId(codigo);
        dao.remover(codigo);
        return medico;
    }

    public Medico getId(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Medico medico = dao.getId(codigo);
        return medico;
    }

    public List<Medico> getAll() {
        return dao.getAll();
    }

    public Medico getByCrm(String crm) throws ServiceException, RegraDeNegocioException {
        if (crm == null) {
            throw new RegraDeNegocioException("Informe o CRM do médico");
        }
        return dao.getByCrm(crm);
    }

    public List<Medico> getByNome(String nome) throws ServiceException, RegraDeNegocioException {
        if (nome == null) {
            throw new RegraDeNegocioException("Informe o nome do médico");
        }
        return dao.getByNome(nome);
    }
}
