/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author leandro
 */
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Prontuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date data;
    @OneToOne
    private Paciente paciente;
    @ManyToMany
    @JoinTable(name = "Prontuario_Medico", joinColumns = @JoinColumn(name = "prontuario_id"),
            inverseJoinColumns = @JoinColumn(name = "medico_id"))
    private List<Medico> medicos;
    @ManyToMany
    @JoinTable(name = "Prontuario_Procedimento", joinColumns = @JoinColumn(name = "prontuario_id"),
            inverseJoinColumns = @JoinColumn(name = "procedimento_id"))
    private List<Procedimento> procedimentos;
    @ManyToMany
    @JoinTable(name = "Prontuario_Consulta", joinColumns = @JoinColumn(name = "prontuario_id"),
            inverseJoinColumns = @JoinColumn(name = "consulta_id"))
    private List<Consulta> consultas;

    public Prontuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prontuario other = (Prontuario) obj;
        return true;
    }

}
