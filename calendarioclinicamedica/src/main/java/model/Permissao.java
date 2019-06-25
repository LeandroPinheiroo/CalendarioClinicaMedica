/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author leandro
 */
public enum Permissao {
    ADMINISTRADOR("Administrador",'A'),SECRETARIA("Secretária",'S'),MEDICO("Médico",'M'),PACIENTE("Paciente",'P');
    
    private String descricao;
    private Character sigla;

    private Permissao(String descricao, Character sigla){
        this.descricao = descricao;
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public Character getSigla() {
        return sigla;
    }
}
