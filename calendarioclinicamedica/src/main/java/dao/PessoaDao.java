/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Pessoa;

/**
 *
 * @author leandro
 */
/*classe de dao da pessoa que extende do dao generico os métodos padrões*/
public class PessoaDao extends GenericDao<Pessoa, Integer>{
    /*no construtor da classe, passa o tipo de classe para o pai para adaptar os metodos de acordo com a classe*/
    protected PessoaDao() {
        super(Pessoa.class);
    }
}
