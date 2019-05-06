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
    MEDICO("Médico"),SECRETARIA("Secretária"),GERAL("Geral");
    
    private String nome;

    private Permissao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }   
}
