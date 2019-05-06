/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.ConsultaDao;
import exception.RegraDeNegocioException;
import java.util.List;
import model.Consulta;

/**
 *
 * @author leandro
 */
public class ConsultaService {

    private ConsultaDao dao;

    public ConsultaService() {
        dao = new ConsultaDao();
    }

    public Consulta getByCodigo(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");

        }
        return dao.getId(codigo);
    }

    public void salvar(Consulta entidade) throws RegraDeNegocioException {
        if (entidade == null) {
            throw new RegraDeNegocioException("Informe a entidade");
        } else if (entidade.getAvaliacao() == null) {
            throw new RegraDeNegocioException("Informe a avaliação");
        }
        dao.salvar(entidade);
    }

    public Consulta remover(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Consulta consulta = this.getId(codigo);
        dao.remover(codigo);
        return consulta;
    }

    public Consulta getId(Integer codigo) throws RegraDeNegocioException {
        if (codigo == null || codigo <= 0) {
            throw new RegraDeNegocioException("Informe um código válido.");
        }
        Consulta consulta = dao.getId(codigo);
        return consulta;
    }
    public List<Consulta> getAll() {
        return dao.getAll();
    }
}