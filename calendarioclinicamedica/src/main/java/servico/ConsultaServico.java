/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.ConsultaDao;
import excecao.ServicoException;
import java.util.List;
import model.Consulta;

/**
 *
 * @author leandro
 */
/*classe de serviço de consulta para verificar as regras de negocio */
public class ConsultaServico extends ConsultaDao{
    /*construtor da classe invoca os métodos da classe pai, que contém todos os métodos utilizaveis*/
    public ConsultaServico() {
        super();
    }
    
    /*método para salvar uma consulta*/
    public void salvar(Consulta consulta) throws ServicoException {
        /*verifica se a classe recebida está instanciada e se possui os campos necessários*/
        /*se não possuir, lança exceção*/
        if (consulta == null) {
            throw new ServicoException("Informe a entidade");
        } else if (consulta.getAvaliacao() == null) {
            throw new ServicoException("Informe a avaliação");
        }
        /*se não houve problema, salva a consulta*/
        super.salvar(consulta);
    }
    
    /*método para atualizar uma consulta*/
    public void atualizar(Consulta consulta) throws ServicoException {
        /*verifica se a classe recebida está instanciada e se possui os campos necessários*/
        /*se não possuir, lança exceção*/
        if (consulta == null) {
            throw new ServicoException("Informe a entidade");
        } else if (consulta.getAvaliacao() == null) {
            throw new ServicoException("Informe a avaliação");
        }
        /*se não houve problema, atualiza a consulta*/
        super.atualizar(consulta);
    }
    
    /*método para remover uma consulta */
    public Consulta remover(Integer codigo) throws ServicoException {
        /*verifica se recebeu uma código valido*/
        if (codigo == null || codigo <= 0) {
            /*caso não recebeu, lança exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*busca a consulta*/
        Consulta consulta = super.buscaPorID(codigo);
        /*então remove a consulta*/
        super.remover(codigo);
        /*e por fim, remove a consulta*/
        return consulta;
    }
    
    /*método para buscar uma consulta pelo ID*/
    public Consulta buscaPorID(Integer codigo) throws ServicoException {
        /*verifica se recebeu um código válido*/
        if (codigo == null || codigo <= 0) {
            /*se não recebeu, lança uma exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*caso não houve problema, retorna a consulta*/
        return super.buscaPorID(codigo);
    }
    
    /*método para buscar todas as consultas cadastradas*/
    public List<Consulta> buscaTodos() {
        /*retorna a lista de consultas*/
        return super.buscaTodos();
    }
    
    /*método para buscar as consultas por seu status*/
    public List<Consulta> buscaPorStatus(boolean status){
        /*retorna a lista de consultas*/
        return super.buscaPorStatus(status);
    }
}