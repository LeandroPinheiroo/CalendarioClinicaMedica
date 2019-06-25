/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.CidadeDao;
import excecao.ServicoException;
import java.util.List;
import model.Cidade;

/**
 *
 * @author leandro
 */
/*classe de serviço de cidade para verificar as regras de negocio */
public class CidadeServico extends CidadeDao{
    /*construtor da classe invoca os métodos da classe pai, que contém todos os métodos utilizaveis*/
    public CidadeServico() {
        super();
    }
    
    /*método para salvar uma cidade*/
    public void salvar(Cidade cidade) throws ServicoException {
        /*verifica a instancia da classe*/
        if (cidade == null) {
            throw new ServicoException("Informe a entidade");
        /*e/ou verifica os outros campos*/
        } if (cidade.getEstado() == null) {
            throw new ServicoException("Informe o estado");
        } if (cidade.getNome() == null) {
            throw new ServicoException("Informe o nome da cidade");
        } if (cidade.getUf() == null) {
            throw new ServicoException("Informe o UF da cidade");
        }
        /*se não houve problema, salva a cidade*/
        super.salvar(cidade);
    }
    
    /*método para atualizar uma cidade*/
    public void atualizar(Cidade cidade) throws ServicoException {
        /*verifica a instancia da classe*/
        if (cidade == null) {
            throw new ServicoException("Informe a entidade");
        /*e/ou verifica os outros campos*/
        } if (cidade.getEstado() == null) {
            throw new ServicoException("Informe o estado");
        } if (cidade.getNome() == null) {
            throw new ServicoException("Informe o nome da cidade");
        } if (cidade.getUf() == null) {
            throw new ServicoException("Informe o UF da cidade");
        }
        /*se não houve problema, atualiza a cidade*/
        super.atualizar(cidade);
    }

    /*método para remover uma cidade*/
    public Cidade remover(Integer codigo) throws ServicoException {
        /*verifica se recebeu um código válido*/
        if (codigo == null || codigo <= 0) {
            /*lança uma exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*se não houve problema, busca a cidade*/
        Cidade cidade = super.buscaPorID(codigo);
        /*remove a cidade*/
        super.remover(codigo);
        /*e a retorna*/
        return cidade;
    }
    /*método para buscar uma cidade pelo seu id*/ 
    public Cidade buscaPorID(Integer codigo) throws ServicoException {
        /*verifica se recebeu um id válido*/
        if (codigo == null || codigo <= 0) {
            /*se não foi, retorna a exceção*/
            throw new ServicoException("Informe um código válido.");

        }
        /*se não houve problema, retorna a cidade recebida pelo ID*/
        return super.buscaPorID(codigo);
    }
    
    /*método para buscar uma lista de cidades*/
    public List<Cidade> buscaTodos() {
        /*retorna a lista de cidades*/
        return super.buscaTodos();
    }
    
    /*método para buscar uma cidade pelo seu nome e seu estado*/
    public Cidade buscaPorNomeEstado(String cidade, String estado) throws ServicoException {
        /*verifica se recebeu a cidade e o estado e caso não receba, lança exceção dos erros*/
        if (cidade == null || cidade.isEmpty()) {
            throw new ServicoException("Informe a cidade.");
        } else if (estado == null || estado.isEmpty()) {
            throw new ServicoException("Informe o estado.");
        }
        /*caso não houve problema, mostra mensagem de erro*/
        return super.buscaPorNomeEstado(cidade, estado);
    }
    
    /*método para buscar uma lista de cidades por um estado*/
    public List<Cidade> buscaPorEstado(String estado) throws ServicoException {
        /*verifica se recebeu um estado válido*/
        if (estado == null || estado.isEmpty()) {
            /*se houve problema, lança uma exceção*/
            throw new ServicoException("Informe o estado.");
        }
        /*caso não houve problema, retorna a lista pelo estado*/
        return super.buscaPorEstado(estado);
    }
    
    /*método para buscar uma lista de cidades pelo status*/
    public List<Cidade> buscaPorStatus(boolean status){
        /*retorna então a lista de cidades pelo status*/
        return super.buscaPorStatus(status);
    }
}
