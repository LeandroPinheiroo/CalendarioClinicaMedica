/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import com.google.protobuf.ServiceException;
import dao.ConvenioDao;
import exception.RegraDeNegocioException;
import java.util.List;
import model.Cidade;
import model.Convenio;

/**
 *
 * @author leandro
 */
public class ConvenioService {

    private ConvenioDao dao;

    public ConvenioService() {
        dao = new ConvenioDao();
    }

    public Convenio getByCodigo(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        return dao.getId(codigo);
    }

    public void salvar(Convenio entidade) throws RegraDeNegocioException {
        if (entidade == null) {
            throw new RegraDeNegocioException("Informe a entidade");
        } else if (entidade.getNome() == null) {
            throw new RegraDeNegocioException("Informe o nome do convênio");
        }
        dao.salvar(entidade);
    }

    public Convenio remover(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Convenio convenio = this.getId(codigo);
        dao.remover(codigo);
        return convenio;
    }

    public Convenio getId(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Convenio convenio = dao.getId(codigo);
        return convenio;
    }

    public List<Convenio> getAll() {
        return dao.getAll();
    }

    public Convenio getByNome(String nome) throws ServiceException, RegraDeNegocioException {
        if (nome == null) {
            throw new RegraDeNegocioException("Informe o nome do convênio");
        }
        return dao.getByNome(nome);
    }
}