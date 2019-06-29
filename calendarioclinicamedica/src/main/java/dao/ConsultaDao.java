/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Consulta;

/**
 *
 * @author leandro
 */
/*classe de dao da consulta que extende do dao generico os métodos padrões*/
public class ConsultaDao extends GenericDao<Consulta, Integer> {
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected ConsultaDao() {
        super(Consulta.class);
    }
}
