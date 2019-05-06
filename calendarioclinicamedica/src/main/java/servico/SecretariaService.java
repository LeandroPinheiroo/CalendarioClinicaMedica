/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import com.google.protobuf.ServiceException;
import dao.SecretariaDao;
import exception.RegraDeNegocioException;
import java.util.List;
import model.Secretaria;

/**
 *
 * @author leandro
 */
public class SecretariaService {

    private SecretariaDao dao;

    public SecretariaService() {
        dao = new SecretariaDao();
    }

    public Secretaria getByCodigo(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        return dao.getId(codigo);
    }

    public void salvar(Secretaria entidade) throws RegraDeNegocioException {
        if (entidade == null) {
            throw new RegraDeNegocioException("Informe a entidade");
        } else if (entidade.getNome() == null) {
            throw new RegraDeNegocioException("Informe o nome da secretaria");
        } else if (entidade.getCpf() == null) {
            throw new RegraDeNegocioException("Informe o CPF da secretaria");
        } else if (entidade.getCelular() == null) {
            throw new RegraDeNegocioException("Informe o celular da secretaria");
        } else if (entidade.getLogin() == null) {
            throw new RegraDeNegocioException("Informe o login da secretaria");
        } else if (entidade.getSenha() == null) {
            throw new RegraDeNegocioException("Informe a senha da secretaria");
        } else if (entidade.getPermissao() == null) {
            throw new RegraDeNegocioException("Informe a permição da secretaria");
        } else if (entidade.getEmail() == null) {
            throw new RegraDeNegocioException("Informe o E-mail da secretaria");
        }
        dao.salvar(entidade);
    }

    public Secretaria remover(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Secretaria secretaria = this.getId(codigo);
        dao.remover(codigo);
        return secretaria;
    }

    public Secretaria getId(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Secretaria secretaria = dao.getId(codigo);
        return secretaria;
    }

    public List<Secretaria> getAll() {
        return dao.getAll();
    }

    public List<Secretaria> getByNome(String nome) throws ServiceException, RegraDeNegocioException {
        if (nome == null) {
            throw new RegraDeNegocioException("Informe o nome da secretaria");
        }
        return dao.getByNome(nome);
    }

    public Secretaria getByCpf(String crm) throws ServiceException, RegraDeNegocioException {
        if (crm == null) {
            throw new RegraDeNegocioException("Informe o CPF da secretaria");
        }
        return dao.getByCpf(crm);
    }

}
