/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.MedicoDao;
import excecao.ServicoException;
import java.util.List;
import modelo.Medico;

/**
 *
 * @author leandro
 */
/*classe de serviço de médico para verificar as regras de negocio da classe*/
public class MedicoServico extends MedicoDao{
    /*construtor da classe invoca os métodos da classe pai, que contém todos os métodos utilizaveis*/
    public MedicoServico() {
       super();
    }
    
    /*método para salvar um médico */
    public void salvar(Medico medico) throws ServicoException {
        /*verifica se a classe não está nula e nem os campos necessários não foram preenchidos*/
        /*se houver problema, lança exceção de acordo com o erro*/
        if (medico == null) {
            throw new ServicoException("Informe a entidade");
        } else if (medico.getNome() == null || medico.getNome().isEmpty()) {
            throw new ServicoException("Informe o nome do médico");
        } else if (medico.getCrm() == null || medico.getCrm().isEmpty()) {
            throw new ServicoException("Informe o CRM");
        } else if (medico.getCpf() == null || medico.getCpf().isEmpty()) {
            throw new ServicoException("Informe o CPF do médico");
        } else if (medico.getCelular() == null || medico.getCelular().isEmpty()) {
            throw new ServicoException("Informe o celular do médico");
        } else if (medico.getLogin() == null || medico.getLogin().isEmpty()) {
            throw new ServicoException("Informe o login do médico");
        } else if (medico.getSenha() == null || medico.getSenha().isEmpty()) {
            throw new ServicoException("Informe a senha do médico");
        } else if (medico.getPermissao() == null) {
            throw new ServicoException("Informe a permição do médico");
        } else if (medico.getEmail() == null || medico.getEmail().isEmpty()) {
            throw new ServicoException("Informe o E-mail do médico");
        }
        /*se não houve problema, salva o médico*/
        super.salvar(medico);
    }
    
    /*método para atualizar o médico*/
    public void atualizar(Medico medico) throws ServicoException {
        /*verifica se a classe não está nula e nem os campos necessários não foram preenchidos*/
        /*se houver problema, lança exceção de acordo com o erro*/
        if (medico == null) {
            throw new ServicoException("Informe a entidade");
        } else if (medico.getNome() == null || medico.getNome().isEmpty()) {
            throw new ServicoException("Informe o nome do médico");
        } else if (medico.getCrm() == null || medico.getCrm().isEmpty()) {
            throw new ServicoException("Informe o CRM");
        } else if (medico.getCpf() == null || medico.getCpf().isEmpty()) {
            throw new ServicoException("Informe o CPF do médico");
        } else if (medico.getCelular() == null || medico.getCelular().isEmpty()) {
            throw new ServicoException("Informe o celular do médico");
        } else if (medico.getLogin() == null || medico.getLogin().isEmpty()) {
            throw new ServicoException("Informe o login do médico");
        } else if (medico.getSenha() == null || medico.getSenha().isEmpty()) {
            throw new ServicoException("Informe a senha do médico");
        } else if (medico.getPermissao() == null) {
            throw new ServicoException("Informe a permição do médico");
        } else if (medico.getEmail() == null || medico.getEmail().isEmpty()) {
            throw new ServicoException("Informe o E-mail do médico");
        }
        /*se não houve problema, atualiza o médico*/
        super.atualizar(medico);
    }
    
    /*método para remover um médico pelo seu codigo*/
    public Medico remover(Integer codigo) throws ServicoException {
        /*verifica se recebeu um codigo invalido*/
        if (codigo == null || codigo <= 0) {
            /*lança exceção com o erro abaixo*/
            throw new ServicoException("Informe um código válido.");
        }
        /*se não houve problema, busca o médico*/
        Medico medico = super.buscaPorID(codigo);
        /*remove o médico*/
        super.remover(codigo);
        /*e por fim retorna o médico removido*/
        return medico;
    }
    
    /*método para busca um médico por ID */
    public Medico buscaPorID(Integer codigo) throws ServicoException {
        /*verifica se recebeu um código valido*/
        if (codigo == null || codigo <= 0) {
            /*caso não tenha recebido, lança exceção com o erro*/
            throw new ServicoException("Informe um código válido.");
        }
        /*caso não houve problema, retorna o médico buscado*/
        return super.buscaPorID(codigo);
    }
    
    /*método para buscar todos os medicos cadastrados*/
    public List<Medico> buscaTodos() {
        /*retorna todos os médicos cadastrados*/
        return super.buscaTodos();
    }
    
    /*método para buscar um médico pelo seu CRM*/
    public Medico buscaPeloCRM(String crm) throws ServicoException {
        /*verifica se recebeu um crm valido, que não seja vazio ou nulo*/
        if (crm == null || crm.isEmpty()) {
            /*lança exceção com o erro abaixo*/
            throw new ServicoException("Informe o CRM do médico");
        }
        /*caso não houve problema, retorna o médico*/
        return super.buscaPeloCRM(crm);
    }
    
    /*método para buscar médicos pelo nome */
    public List<Medico> buscaPeloNome(String nome) throws ServicoException {
        /*verifica se recebeu um nome nulo ou vazio*/
        if (nome == null || nome.isEmpty()) {
            /*caso recebeu, lança exceção informando o erro*/
            throw new ServicoException("Informe o nome do médico");
        }
        /*se não houver problema, retorna a lista de médicos*/
        return super.buscaPeloNome(nome);
    }
    
    /*método para buscar uma lista de médicos pelo status*/
    public List<Medico> buscaPorStatus(boolean status){
        /*retorna a lista de médicos pelo seu status*/
        return super.buscaPorStatus(status);
    }
}
