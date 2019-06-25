/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.PacienteDao;
import excecao.ServicoException;
import java.util.List;
import model.Paciente;

/**
 *
 * @author leandro
 */
/*classe de serviço de paciente para verificar as regras de negocio da classe*/
public class PacienteService extends PacienteDao{
    /*construtor da classe invoca os métodos da classe pai, que contém todos os métodos utilizaveis*/
    public PacienteService() {
        super();
    }
    
    /*método para salvar um paciente*/
    public void salvar(Paciente paciente) throws ServicoException {
        /*verifica se recebeu a classe nula ou com os campos não preenchidos*/
        /*caso aconteça, lança exceção com o erro*/
        if (paciente == null) {
            throw new ServicoException("Informe a entidade");
        } if (paciente.getNome() == null || paciente.getNome().isEmpty()) {
            throw new ServicoException("Informe o nome do paciente");
        } if (paciente.getCpf() == null || paciente.getCpf().isEmpty()) {
            throw new ServicoException("Informe o CPF do paciente");
        } if (paciente.getCelular() == null || paciente.getCelular().isEmpty()) {
            throw new ServicoException("Informe o celular do paciente");
        } if (paciente.getEmail() == null || paciente.getEmail().isEmpty()) {
            throw new ServicoException("Informe o E-mail do paciente");
        }
        /*caso não houve problema, salva o paciente*/
        super.salvar(paciente);
    }
    
    /*método para atualizar o paciente*/
    public void atualizar(Paciente paciente) throws ServicoException {
        /*verifica se recebeu a classe nula ou com os campos não preenchidos*/
        /*caso aconteça, lança exceção com o erro*/
        if (paciente == null) {
            throw new ServicoException("Informe a entidade");
        } if (paciente.getNome() == null || paciente.getNome().isEmpty()) {
            throw new ServicoException("Informe o nome do paciente");
        } if (paciente.getCpf() == null || paciente.getCpf().isEmpty()) {
            throw new ServicoException("Informe o CPF do paciente");
        } if (paciente.getCelular() == null || paciente.getCelular().isEmpty()) {
            throw new ServicoException("Informe o celular do paciente");
        } if (paciente.getEmail() == null || paciente.getEmail().isEmpty()) {
            throw new ServicoException("Informe o E-mail do paciente");
        }
        /*caso não houve problema, atualiza o paciente*/
        super.atualizar(paciente);
    }
    
    /*método para remover um paciente pelo seu codigo*/
    public Paciente remover(Integer codigo) throws ServicoException {
        /*verifica se recebeu um codigo invalido*/
        if (codigo == null || codigo <= 0) {
            /*caso aconteça, lança exceção informando o erro*/
            throw new ServicoException("Informe um código válido.");
        }
        /*se não houve problema, busca o paciente*/
        Paciente paciente = super.buscaPorID(codigo);
        /*remove o paciente*/
        super.remover(codigo);
        /*retorna o paciente removido*/
        return paciente;
    }
    
    /*método para buscar um paciente pelo codigo*/
    public Paciente buscaPorID(Integer codigo) throws ServicoException {
        /*verifica se o codigo é invalido*/
        if (codigo == null || codigo <= 0) {
            /*se for, lança a exceção com o erro*/
            throw new ServicoException("Informe um código válido.");
        }
        /*se não houve problema, retorna o paciente buscado*/
        return super.buscaPorID(codigo);
    }
    
    /*método para buscar todos os pacientes cadastrados*/
    public List<Paciente> buscaTodos() {
        /*retorna a lista de pacientes*/
        return super.buscaTodos();
    }
    
    /*método para buscar uma lista de pacientes pelo nome*/
    public List<Paciente> buscaPeloNome(String nome) throws ServicoException {
        /*verifica se recebeu um nome nulo ou vazio*/
        if (nome == null || nome.isEmpty()) {
            /*se aconteceu, lança exceção informando o erro*/
            throw new ServicoException("Informe o nome do paciente");
        }
        /*se não houve problema, retorna a lista de pacientes*/
        return super.buscaPeloNome(nome);
    }
    
    /*método para buscar um paciente pelo seu cpf*/
    public Paciente buscaPeloCPF(String cpf) throws ServicoException {
        /*verifica se recebeu um cpf nulo ou vazio*/
        if (cpf == null || cpf.isEmpty()) {
            /*caso aconteça, lança a exceção com o erro*/
            throw new ServicoException("Informe o CPF do paciente");
        }
        /*se não houve problema, retorna o paciente buscado*/
        return super.buscaPeloCPF(cpf);
    }
    
    /*método para buscar uma lista de pacientes pelo seu status*/
    public List<Paciente> buscaPorStatus(boolean status){
        /*retorna a lista de pacientes*/
        return super.buscaPorStatus(status);
    }
}
