/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.ProntuarioDao;
import excecao.ServicoException;
import java.util.Date;
import java.util.List;
import model.Paciente;
import model.Prontuario;

/**
 *
 * @author leandro
 */
/*classe de serviço de prontuario para verificar as regras de negocio da classe*/
public class ProntuarioServico extends ProntuarioDao{
    /*construtor da classe invoca os métodos da classe pai, que contém todos os métodos utilizaveis*/
    public ProntuarioServico() {
        super();
    }
    
    /*método para salvar um prontuario*/
    public void salvar(Prontuario prontuario) throws ServicoException {
        /*verifica se a classe foi recebida nula ou se algum dos campos não foi preenchidos*/
        /*se aconteceu, lança exceção com o erro*/
        if (prontuario == null) {
            throw new ServicoException("Informe a entidade");
        } else if (prontuario.getConsultas() == null) {
            throw new ServicoException("Informe as consultas");
        } else if (prontuario.getData() == null) {
            throw new ServicoException("Informe a data");
        } else if (prontuario.getMedicos() == null) {
            throw new ServicoException("Informe os médicos");
        } else if (prontuario.getPaciente() == null) {
            throw new ServicoException("Informe o paciente");
        } else if (prontuario.getProcedimentos() == null) {
            throw new ServicoException("Informe os procedimentos");
        }
        /*se não houve problema, salva o prontuario*/
        super.salvar(prontuario);
    }
    
    /*método para atualizar um prontuario*/
    public void atualizar(Prontuario prontuario) throws ServicoException {
        /*verifica se a classe foi recebida nula ou se algum dos campos não foi preenchidos*/
        /*se aconteceu, lança exceção com o erro*/
        if (prontuario == null) {
            throw new ServicoException("Informe a entidade");
        } else if (prontuario.getConsultas() == null) {
            throw new ServicoException("Informe as consultas");
        } else if (prontuario.getData() == null) {
            throw new ServicoException("Informe a data");
        } else if (prontuario.getMedicos() == null) {
            throw new ServicoException("Informe os médicos");
        } else if (prontuario.getPaciente() == null) {
            throw new ServicoException("Informe o paciente");
        } else if (prontuario.getProcedimentos() == null) {
            throw new ServicoException("Informe os procedimentos");
        }
        /*se não houve problema, atualiza o prontuario*/
        super.atualizar(prontuario);
    }
    
    /*método para remover um prontuario*/
    public Prontuario remover(Integer codigo) throws ServicoException {
        /*verifica se recebeu um codigo valido*/
        if (codigo == null || codigo <= 0) {
            /*se não recebeu, lança exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*se não houve problema, busca o prontuario*/
        Prontuario prontuario = super.buscaPorID(codigo);
        /*remove o prontuario*/
        super.remover(codigo);
        /*e remove o prontuario removido*/
        return prontuario;
    }
    
    /*método para buscar um prontuario pelo seu codigo*/
    public Prontuario buscaPorID(Integer codigo) throws ServicoException {
        /*verifica se recebeu um codigo valido*/
        if (codigo == null || codigo <= 0) {
            /*caso não recebeu, lança exceção com o erro*/
            throw new ServicoException("Informe um código válido.");
        }
        /*caso não houve problema, retorna o prontuario buscado*/
        return super.buscaPorID(codigo);
    }
    
    /*método para buscar todos os prontuarios cadastrados*/
    public List<Prontuario> buscaTodos() {
        /*retorna a lista de prontuarios*/
        return super.buscaTodos();
    }
    
    /*método para buscar uma lista de prontuarios por uma data*/
    public List<Prontuario> buscaPorData(Date data) throws ServicoException {
        /*verifica se recebeu a data nula*/
        if (data == null) {
            /*caso sim, lança exceção*/
            throw new ServicoException("Informe a data");
        }
        /*se não houve problema, retorna a lista de prontuarios*/
        return super.buscaPorData(data);
    }
    
    /*método para buscar uma lista de prontuarios por um range de datas*/
    public List<Prontuario> buscaPorIntervaloData(Date menorData, Date maiorData) throws ServicoException {
        /*verifica se alguma das datas está nula, caso estiver lança exceção com o erro*/
        if (menorData == null) {
            throw new ServicoException("Informe a primeira data");
        } if (maiorData == null) {
            throw new ServicoException("Informe a segunda data");
        }
        /*se não houve problema, busca a lista de prontuarios*/
        return super.buscaPorIntervaloData(menorData, maiorData);
    }
    
    /*método para buscar um prontuario pelo seu paciente*/
    public Prontuario buscaPeloPaciente(Paciente paciente) throws ServicoException{
        /*verifica se recebeu o paciente nulo*/
        if(paciente == null){
            throw new ServicoException("Informe o paciente");
        }
        /*se não houve problema, retorna o prontuario*/
        return super.buscaPeloPaciente(paciente);
    }
    
    /*método para buscar uma lista de prontuarios pelo status*/
    public List<Prontuario> buscaPorStatus(boolean status){
        /*retorna a lista de prontuarios*/
        return super.buscaPorStatus(status);
    }
}
