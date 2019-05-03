/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CidadeDao;
import model.Cidade;

/**
 *
 * @author leandro
 */
public class Main {

    public static void main(String[] args) {
        Cidade c = new Cidade();
        CidadeDao dao = new CidadeDao();
        c.setEstado("MINAS");
        c.setNome("FORMIGA");
        c.setUf("MG");
        dao.salvar(c);
        

    }

}
