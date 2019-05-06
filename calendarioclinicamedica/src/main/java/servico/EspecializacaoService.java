/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import com.google.protobuf.ServiceException;
import dao.EspecializacaoDao;
import exception.RegraDeNegocioException;
import java.util.List;
import model.Especializacao;

/**
 *
 * @author leandro
 */
public class EspecializacaoService {

    private EspecializacaoDao dao;

    public EspecializacaoService() {
        dao = new EspecializacaoDao();
    }

    public Especializacao getByCodigo(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        return dao.getId(codigo);
    }

    public void salvar(Especializacao entidade) throws RegraDeNegocioException {
        if (entidade == null) {
            throw new RegraDeNegocioException("Informe a entidade");
        } else if (entidade.getNome() == null) {
            throw new RegraDeNegocioException("Informe o nome da especialização");
        }
        dao.salvar(entidade);
    }

    public Especializacao remover(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Especializacao especializacao = this.getId(codigo);
        dao.remover(codigo);
        return especializacao;
    }

    public Especializacao getId(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Especializacao especializacao = dao.getId(codigo);
        return especializacao;
    }

    public List<Especializacao> getAll() {
        return dao.getAll();
    }

    public Especializacao getByNome(String nome) throws ServiceException, RegraDeNegocioException {
        if (nome == null) {
            throw new RegraDeNegocioException("Informe o nome da especialização");
        }
        return dao.getByNome(nome);
    }
}
