/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import com.google.protobuf.ServiceException;
import dao.CidadeDao;
import exception.RegraDeNegocioException;
import java.util.List;
import model.Cidade;

/**
 *
 * @author leandro
 */
public class CidadeService {

    private CidadeDao dao;

    public CidadeService() {
        this.dao = new CidadeDao();
    }

    public Cidade getByCodigo(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");

        }
        return dao.getId(codigo);
    }

    public void salvar(Cidade entidade) throws RegraDeNegocioException {
        if (entidade == null) {
            throw new RegraDeNegocioException("Informe a entidade");
        } else if (entidade.getEstado() == null) {
            throw new RegraDeNegocioException("Informe o estado");
        } else if (entidade.getNome() == null) {
            throw new RegraDeNegocioException("Informe o nome da cidade");
        } else if (entidade.getUf() == null) {
            throw new RegraDeNegocioException("Informe o UF da cidade");
        }
        dao.salvar(entidade);
    }

    public Cidade remover(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Cidade cidade = this.getId(codigo);
        dao.remover(codigo);
        return cidade;
    }

    public Cidade getId(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Cidade cidade = dao.getId(codigo);
        return cidade;
    }

    public List<Cidade> getAll() {
        return dao.getAll();
    }

    public Cidade getByNomeEstado(String cidade, String estado) throws ServiceException, RegraDeNegocioException {
        if (cidade == null) {
            throw new RegraDeNegocioException("Informe a cidade.");
        } else if (estado == null) {
            throw new RegraDeNegocioException("Informe o estado.");
        }
        return dao.getByNomeEstado(cidade, estado);
    }

    public List<Cidade> getByEstado(String estado) throws ServiceException, RegraDeNegocioException {
        if (estado == null) {
            throw new RegraDeNegocioException("Informe o estado.");
        }
        return dao.getByEstado(estado);
    }
}
