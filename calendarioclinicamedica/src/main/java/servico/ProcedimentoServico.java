/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.ProcedimentoDao;
import excecao.ServicoException;
import java.util.List;
import modelo.Procedimento;

/**
 *
 * @author leandro
 */
/*classe de serviço de procedimento para verificar as regras de negocio da classe*/
public class ProcedimentoServico extends ProcedimentoDao{
     /*construtor da classe invoca os métodos da classe pai, que contém todos os métodos utilizaveis*/
    public ProcedimentoServico() {
        super();
    }
    
    /*método para salvar um procedimento*/
    public void salvar(Procedimento procedimento) throws ServicoException {
        /*verifica se a classe recebida está nula ou se os campos necessários não estão preenchidos*/
        /*caso aconteça, lança exceção de acordo com o erro*/
        if (procedimento == null) {
            throw new ServicoException("Informe a entidade");
        } else if (procedimento.getNome() == null || procedimento.getNome().isEmpty()) {
            throw new ServicoException("Informe o nome do procedimento");
        } else if (procedimento.getPreco() <= 0) {
            throw new ServicoException("O preço do procedimento não procedimento");
        }
        /*se não houve problema , salva o procedimento*/
        super.salvar(procedimento);
    }
    
    /*método para atualizar um procedimento*/
    public void atualizar(Procedimento procedimento) throws ServicoException {
        /*verifica se a classe recebida está nula ou se os campos necessários não estão preenchidos*/
        /*caso aconteça, lança exceção de acordo com o erro*/
        if (procedimento == null) {
            throw new ServicoException("Informe a entidade");
        } else if (procedimento.getNome() == null || procedimento.getNome().isEmpty()) {
            throw new ServicoException("Informe o nome do procedimento");
        } else if (procedimento.getPreco() <= 0) {
            throw new ServicoException("O preço do procedimento não procedimento");
        }
        /*se não houve problema , atualiza o procedimento*/
        super.atualizar(procedimento);
    }

    /*método para remover um procedimento*/
    public Procedimento remover(Integer codigo) throws ServicoException {
        /*verifica se recebeu um código valido*/
        if (codigo == null || codigo <= 0) {
            /*caso não recebeu, lança exceção informando o erro*/
            throw new ServicoException("Informe um código válido.");
        }
        /*caso não houve problema, busca o procedimento*/
        Procedimento procedimento = super.buscaPorID(codigo);
        /*remove o procedimento*/
        super.remover(codigo);
        /*retorna o procedimento removido*/
        return procedimento;
    }
    
    /*método para buscar um procedimento pelo seu codigo*/
    public Procedimento buscaPorID(Integer codigo) throws ServicoException {
        /*verifica se recebeu um codigo valido*/
        if (codigo == null || codigo <= 0) {
            /*se não recebeu, lança exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*se não houve erro, retorna o procedimento buscado*/
        return super.buscaPorID(codigo);
    }
    
    /*método para buscar todos os procedimentos cadastrados*/
    public List<Procedimento> buscaTodos() {
        /*retorna a lista de procedimentos*/
        return super.buscaTodos();
    }
    
    /*método para buscar um procedimento pelo seu nome*/
    public Procedimento buscaPeloNome(String nome) throws ServicoException{
        /*verifica se recebeu um nome nulo ou vazio*/
        if (nome == null || nome.isEmpty()) {
            /*se aconteceu, lança exceção informando o erro*/
            throw new ServicoException("Informe o nome do paciente");
        }
        /* se não houve problema, retorna o procedimento pelo seu nome*/
        return super.buscaPeloNome(nome);
    }
    
    /*método para buscar uma lista de procedimentos pelo status*/
    public List<Procedimento> buscaPorStatus(boolean status){
        /*retorna a lista de procedimentos*/
        return super.buscaPorStatus(status);
    }
}
