/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.ConvenioDao;
import excecao.ServicoException;
import java.util.List;
import modelo.Convenio;

/**
 *
 * @author leandro
 */
/*classe de serviço de convenio para verificar as regras de negocio */
public class ConvenioServico extends ConvenioDao{
    /*construtor da classe invoca os métodos da classe pai, que contém todos os métodos utilizaveis*/
    public ConvenioServico() {
        super();
    }

    /*método para salvar um convenio*/
    public void salvar(Convenio convenio) throws ServicoException {
        /*verifica se recebeu a classe instanciada ou se recebeu os campos validos
        e caso não recebeu, lança exceção*/
        if (convenio == null) {
            throw new ServicoException("Informe a entidade");
        } else if (convenio.getNome() == null) {
            throw new ServicoException("Informe o nome do convênio");
        }
        /*se não houve problema, salva o convenio*/
        super.salvar(convenio);
    }
    
    /*método para atualizar um convenio*/
    public void atualizar(Convenio convenio) throws ServicoException {
        /*verifica se recebeu a classe instanciada ou se recebeu os campos validos
        e caso não recebeu, lança exceção*/
        if (convenio == null) {
            throw new ServicoException("Informe a entidade");
        } else if (convenio.getNome() == null) {
            throw new ServicoException("Informe o nome do convênio");
        }
        /*se não houve problema, atualiza o convenio*/
        super.atualizar(convenio);
    }
    
    /*método para remover um convenio*/
    public Convenio remover(Integer codigo) throws ServicoException {
        /*verifica se recebeu código invalido*/
        if (codigo == null || codigo <= 0) {
            /*se acontecer, lança exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*busca o convenio*/
        Convenio convenio = super.buscaPorID(codigo);
        /*remove o convenio*/
        super.remover(codigo);
        /*retorna o convenio*/
        return convenio;
    }
    
    /*método para buscar um convenio pelo seu código*/
    public Convenio buscaPorID(Integer codigo) throws ServicoException {
        /*verifica se recebeu algum código invalido*/
        if (codigo == null || codigo <= 0) {
            /*se recebeu, lança exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*se não houve problema, retorna o convenio buscado*/
        return super.buscaPorID(codigo);
    }
    
    /*método para buscar todos os convenios cadastrados*/
    public List<Convenio> buscarTodos() {
        /*retorna a lista de convenios*/
        return super.buscaTodos();
    }
    
    /*método para buscar um convenio pelo seu nome*/
    public Convenio buscaPorNome(String nome) throws ServicoException {
        /*verifica se recebeu um nome valido*/
        if (nome == null || nome.isEmpty()) {
            /*caso aconteça, lança exceção*/
            throw new ServicoException("Informe o nome do convênio");
        }
        /*retorna o convenio*/
        return super.buscaPorNome(nome);
    }
    
    /*método para buscar uma lista de convenio pelo status*/
    public List<Convenio> buscaPorStatus(boolean status){
        /*retorna a lista de convenios*/
        return super.buscaPorStatus(status);
    }
}