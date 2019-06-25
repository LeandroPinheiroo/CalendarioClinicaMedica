/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import com.google.protobuf.ServiceException;
import dao.SecretariaDao;
import excecao.ServicoException;
import java.util.List;
import model.Secretaria;

/**
 *
 * @author leandro
 */
/*classe de serviço de secretaria para verificar as regras de negocio da classe*/
public class SecretariaService extends SecretariaDao{
    /*construtor da classe invoca os métodos da classe pai, que contém todos os métodos utilizaveis*/
    public SecretariaService() {
        super();
    }
    
    /*método para salvar uma secretaria*/
    public void salvar(Secretaria secretaria) throws ServicoException {
        /*verifica se recebeu a classe nula ou algum campo não preenchido*/
        /*caso aconteça, lança exceção com determinado erro*/
        if (secretaria == null) {
            throw new ServicoException("Informe a entidade");
        } if (secretaria.getNome() == null) {
            throw new ServicoException("Informe o nome da secretaria");
        } if (secretaria.getCpf() == null) {
            throw new ServicoException("Informe o CPF da secretaria");
        } if (secretaria.getCelular() == null) {
            throw new ServicoException("Informe o celular da secretaria");
        } if (secretaria.getLogin() == null) {
            throw new ServicoException("Informe o login da secretaria");
        } if (secretaria.getSenha() == null) {
            throw new ServicoException("Informe a senha da secretaria");
        } if (secretaria.getPermissao() == null) {
            throw new ServicoException("Informe a permição da secretaria");
        } if (secretaria.getEmail() == null) {
            throw new ServicoException("Informe o E-mail da secretaria");
        }
        /*caso não houve problema, salva a secretária*/
        super.salvar(secretaria);
    }
    
    /*método para atualizar uma secretaria*/
    public void atualizar(Secretaria secretaria) throws ServicoException {
        /*verifica se recebeu a classe nula ou algum campo não preenchido*/
        /*caso aconteça, lança exceção com determinado erro*/
        if (secretaria == null) {
            throw new ServicoException("Informe a entidade");
        } if (secretaria.getNome() == null) {
            throw new ServicoException("Informe o nome da secretaria");
        } if (secretaria.getCpf() == null) {
            throw new ServicoException("Informe o CPF da secretaria");
        } if (secretaria.getCelular() == null) {
            throw new ServicoException("Informe o celular da secretaria");
        } if (secretaria.getLogin() == null) {
            throw new ServicoException("Informe o login da secretaria");
        } if (secretaria.getSenha() == null) {
            throw new ServicoException("Informe a senha da secretaria");
        } if (secretaria.getPermissao() == null) {
            throw new ServicoException("Informe a permição da secretaria");
        } if (secretaria.getEmail() == null) {
            throw new ServicoException("Informe o E-mail da secretaria");
        }
        /*caso não houve problema, atualiza a secretária*/
        super.atualizar(secretaria);
    }
    
    /*método para remover uma secretária*/
    public Secretaria remover(Integer codigo) throws ServicoException {
        /*verifica se recebeu um codigo valido*/
        if (codigo == null || codigo <= 0) {
            /*caso não tenha recebido, lança exceção avisando o erro*/
            throw new ServicoException("Informe um código válido.");
        }
        /*se não houve problema, busca a secretária*/
        Secretaria secretaria = super.buscaPorID(codigo);
        /*remove a secretaria*/
        super.remover(codigo);
        /*e retorna a secretaria removida*/
        return secretaria;
    }
    
    /*método para buscar uma secretaria pelo codigo*/
    public Secretaria getByCodigo(Integer codigo) throws ServicoException {
        /*verifica se recebeu um codigo invalido*/
        if (codigo == null || codigo <= 0) {
            /*se recebeu, lança exceção com o erro informando*/
            throw new ServicoException("Informe um código válido.");
        }
        /*caso deu tudo certo, retorna a secretaria buscada*/
        return super.buscaPorID(codigo);
    }
    
    /*método para buscar todas as secretarias cadastradas*/
    public List<Secretaria> buscaTodos() {
        /*retorna a lista de secretarias*/
        return super.buscaTodos();
    }
    
    /*método para buscar uma lista de secretarias pelo seu nome*/
    public List<Secretaria> buscaPeloNome(String nome) throws ServicoException {
        /*verifica se recebeu o nome nulo ou vazio*/
        if (nome == null || nome.isEmpty()) {
            /*caso aconteça, lança exceção avisando o erro*/
            throw new ServicoException("Informe o nome da secretaria");
        }
        /*senão houve problema, retorna a lista de secretarias pelo nome*/
        return super.buscaPeloNome(nome);
    }
    
    /*método para buscar uma secretaria pelo seu cpf*/
    public Secretaria buscaPorCPF(String cpf) throws ServicoException {
        /*verifica se recebeu uma cpf nulo ou vazio*/
        if (cpf == null || cpf.isEmpty()) {
            /*caso receba, lança exceção, avisando sobre o erro*/
            throw new ServicoException("Informe o CPF da secretaria");
        }
        /*se não houve erro, retorna a secretaria buscada*/
        return super.buscaPorCPF(cpf);
    }
    
    /*método para buscar uma lista de secretarias pelo status*/
    public List<Secretaria> buscaPorStatus(boolean status){
        /*retorna a lista de secretarias*/
        return super.buscaPorStatus(status);
    }

}
