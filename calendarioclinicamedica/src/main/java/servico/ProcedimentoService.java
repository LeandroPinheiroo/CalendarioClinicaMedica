/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.ProcedimentoDao;
import exception.RegraDeNegocioException;
import java.util.List;
import model.Procedimento;

/**
 *
 * @author leandro
 */
public class ProcedimentoService {

    private ProcedimentoDao dao;

    public ProcedimentoService() {
        dao = new ProcedimentoDao();
    }

    public Procedimento getByCodigo(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        return dao.getId(codigo);
    }

    public void salvar(Procedimento entidade) throws RegraDeNegocioException {
        if (entidade == null) {
            throw new RegraDeNegocioException("Informe a entidade");
        } else if (entidade.getNome() == null) {
            throw new RegraDeNegocioException("Informe o nome do procedimento");
        } else if (entidade.getPreco() <= 0) {
            throw new RegraDeNegocioException("O preço do procedimento não procedimento");
        }
        dao.salvar(entidade);
    }

    public Procedimento remover(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Procedimento procedimento = this.getId(codigo);
        dao.remover(codigo);
        return procedimento;
    }

    public Procedimento getId(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Procedimento procedimento = dao.getId(codigo);
        return procedimento;
    }

    public List<Procedimento> getAll() {
        return dao.getAll();
    }
}
