/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.EspecializacaoDao;
import excecao.ServicoException;
import java.util.List;
import modelo.Especializacao;

/**
 *
 * @author leandro
 */
/*classe de serviço de especialização para verificar as regras de negocio */
public class EspecializacaoServico extends EspecializacaoDao{
    /*construtor da classe invoca os métodos da classe pai, que contém todos os métodos utilizaveis*/
    public EspecializacaoServico() {
        super();
    }
    
    /*método para salvar uma especialização */
    public void salvar(Especializacao especializacao) throws ServicoException {
        /*verifica se recebeu a classe instancia ou os campos validos*/
        /*se não recebeu, lança exceção*/
        if (especializacao == null) {
            throw new ServicoException("Informe a entidade");
        } else if (especializacao.getNome() == null) {
            throw new ServicoException("Informe o nome da especialização");
        }
        /*se não houve problema, salva a especialização*/
        super.salvar(especializacao);
    }
    
    /*método para atualizar uma especialização */
    public void atualizar(Especializacao especializacao) throws ServicoException {
        /*verifica se recebeu a classe instancia ou os campos validos*/
        /*se não recebeu, lança exceção*/
        if (especializacao == null) {
            throw new ServicoException("Informe a entidade");
        } else if (especializacao.getNome() == null) {
            throw new ServicoException("Informe o nome da especialização");
        }
        /*se não houve problema, atualiza a especialização*/
        super.atualizar(especializacao);
    }
    
    /*método para remover uma especialização*/
    public Especializacao remover(Integer codigo) throws ServicoException {
        /*verifica se recebeu um codigo valido*/
        if (codigo == null || codigo <= 0) {
            /*se não recebeu, lança exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*se não houve problema, busca a especialização*/
        Especializacao especializacao = super.buscaPorID(codigo);
        /*remove a especialização*/
        super.remover(codigo);
        /*e retorna ela*/
        return especializacao;
    }
    
    /*método para buscar a especialização*/
    public Especializacao buscaPorID(Integer codigo) throws ServicoException {
        /*verifica se recebeu um código valido*/
        if (codigo == null || codigo <= 0) {
            /*caso não tenha recebida, lança exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*caso não teve erro, busca a especialização pelo seu codigo*/
        return super.buscaPorID(codigo);
    }
    
    /*método para listar todas as especializações*/
    public List<Especializacao> buscaTodos() {
        /*retorna todas as especializações cadastradas*/
        return super.buscaTodos();
    }
    
    /*método para buscar uma especialização pelo seu nome*/
    public Especializacao buscaPeloNome(String nome) throws ServicoException {
        /*verifica se recebeu um nome valido*/
        if (nome == null || nome.isEmpty()) {
            /*caso não tenha recebido, lança exceção*/
            throw new ServicoException("Informe o nome da especialização");
        }
        /*se não houve problema, retorna a especialização buscada*/
        return super.buscaPeloNome(nome);
    }
    
    /*método para buscar uma lista de especializações pelo status*/
    public List<Especializacao> buscaPorStatus(boolean status){
        /*retorna a lista de especializações*/
        return super.buscaPorStatus(status);
    }
}
