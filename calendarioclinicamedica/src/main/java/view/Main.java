/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.google.protobuf.ServiceException;
import dao.PacienteDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Paciente;

/**
 *
 * @author leandro
 */
public class Main {

    public static void main(String[] args) {
        Paciente p = new Paciente();
        p.setBairro("Bairro");
        p.setCelular("CElular");
        p.setCpf("13698113651");
        PacienteDao pdao = new PacienteDao();
        pdao.salvar(p);
        Paciente p2 = null;

        
        try {
            p2 = pdao.getByCpf("13698113651");
        } catch (ServiceException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(p2.toString());
        
    }

}
