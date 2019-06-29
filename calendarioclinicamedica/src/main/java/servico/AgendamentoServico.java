/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.AgendamentoDao;
import excecao.ServicoException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import modelo.Agendamento;

/**
 *
 * @author leandro
 */
/*classe de serviço de agendamento para verificar as regras de negocio */
public class AgendamentoServico extends AgendamentoDao{
    /*construtor da classe invoca os métodos da classe pai, que contém todos os métodos utilizaveis*/
    public AgendamentoServico() {
        super();
    }
    
    /*método para salvar um agendamento*/
    public void salvar(Agendamento agendamento) throws ServicoException {
        /*verifica se o agendamento não está nulo*/
        if (agendamento == null) {
            throw new ServicoException("Informe a entidade");
            /*se não tem a data*/
        } else if (agendamento.getData() == null) {
            throw new ServicoException("Informe a data do agendamento");
            /*se não tem a hora*/
        } else if (agendamento.getHora() == null) {
            throw new ServicoException("Informe a hora do agendamento");
            /*se não tem o médico*/
        } else if (agendamento.getMedico() == null) {
            throw new ServicoException("Informe o médico");
            /*se não tem o paciente*/
        } else if (agendamento.getPaciente() == null) {
            throw new ServicoException("Informe o paciente");
            /*se for um preço invalido*/
        } else if (agendamento.getPreco() < 0) {
            throw new ServicoException("O preço não deve ser menor que zero");
            /*se tem uma lista de procedimentos*/
        } else if (agendamento.getProcedimentos() == null) {
            throw new ServicoException("Informe os procedimentos");
            /*e se tem uma consulta atrelada*/
        } else if (agendamento.getConsulta() == null) {
            throw new ServicoException("Deve ter uma consulta inserida ao agendamento");
        }
        /*se não ocorreu nenhum desses erros, salva o agendamento*/
        super.salvar(agendamento);
    }
    
    /*método para atualizar um agendamento*/
    public void atualizar(Agendamento agendamento) throws ServicoException{
        /*verifica se o agendamento não está nulo*/
        if (agendamento == null) {
            throw new ServicoException("Informe a entidade");
            /*se não tem a data*/
        } else if (agendamento.getData() == null) {
            throw new ServicoException("Informe a data do agendamento");
            /*se não tem a hora*/
        } else if (agendamento.getHora() == null) {
            throw new ServicoException("Informe a hora do agendamento");
            /*se não tem o médico*/
        } else if (agendamento.getMedico() == null) {
            throw new ServicoException("Informe o médico");
            /*se não tem o paciente*/
        } else if (agendamento.getPaciente() == null) {
            throw new ServicoException("Informe o paciente");
            /*se for um preço invalido*/
        } else if (agendamento.getPreco() < 0) {
            throw new ServicoException("O preço não deve ser menor que zero");
            /*se tem uma lista de procedimentos*/
        } else if (agendamento.getProcedimentos() == null) {
            throw new ServicoException("Informe os procedimentos");
            /*e se tem uma consulta atrelada*/
        } else if (agendamento.getConsulta() == null) {
            throw new ServicoException("Deve ter uma consulta inserida ao agendamento");
        }
        /*se não ocorreu nenhum desses erros, atualiza o agendamento*/
        super.atualizar(agendamento);
    }
    
    /*método para remover uma agendamento, recebendo o código do id*/
    public Agendamento remover(Integer codigo) throws ServicoException {
        if (codigo == null || codigo <= 0) {
            /*se receber um codigo invalido, levanta exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*busca o agendamento*/
        Agendamento agendamento = super.buscaPorID(codigo);
        /*remove ele*/
        super.remover(agendamento.getId());
        /*retorna o agendamento removido*/
        return agendamento;
    }
    
    /*método para buscar o agendamento por id*/
    public Agendamento buscaPorID(Integer codigo) throws ServicoException {
        /*verifica se recebeu um código valido*/
        if (codigo == null || codigo <= 0) {
            /*se não recebeu, retorna uma exceção*/
            throw new ServicoException("Informe um código válido.");
        }
        /*senão retorna o agendamento pelo id*/
        return super.buscaPorID(codigo);
    }
    
    /*método para buscar todos os agendamentos cadastrados */
    public List<Agendamento> buscaTodos() {
        /*retorna todos*/
        return super.buscaTodos();
    }
    
    /*método para buscar uma lista de agendamentos por uma hora e uma data*/
    public List<Agendamento> buscaPorDataHora(Date data, Time hora) throws ServicoException{
        /*verifica se recebe uma data e hora valida*/
        if (data == null) {
            /*se não recebeu, retorna a exceção*/
            throw new ServicoException("Informe a data.");
        }if (hora == null) {
            throw new ServicoException("Informe a hora.");
        }
        /*se não houve problema, retorna a lista*/
        return super.buscaPorDataHora(data, hora);
    }
    
    /*método para buscar uma lista de agendamentos pela data*/
    public List<Agendamento> buscaPorData(Date data) throws ServicoException {
        /*verifica se a data não está nula*/
        if (data == null) {
            /*se estiver, levanta a exceção*/
            throw new ServicoException("Informe a data.");
        }
        /*se não houver problema, busca os agendamentos*/
        return super.buscaPorData(data);
    }
    
    /*método para buscar uma lista de agendamentos por um range de datas*/
    public List<Agendamento> buscaPorIntervaloDatas(Date menorData, Date maiorData) throws ServicoException {
        /*verifica se alguma das datas está nula*/
        if (menorData == null) {
            /*se estiver, levanta exceção*/
            throw new ServicoException("Informe a menor data.");
        }if (maiorData == null) {
            throw new ServicoException("Informe a maior data.");
        }
        /*se não houver problema, retorna os agendamentos*/
        return super.buscaPorIntervaloDatas(menorData, maiorData);
    }
    
    /*método para buscar uma lista de agendamentos pelo status*/
    public List<Agendamento> buscaPorStatus(boolean status){
        /*retorna pelo status*/
        return super.buscaPorStatus(status);
    }
}
