package util;

import javax.swing.*;
import java.awt.*;

public class CamposTextoUtil {
    /*método para limpar todos os textfields de um container(tela)*/
    public static void limpaTodosCampos(Container container) {
        /*busca a lista de componentes da tela*/
        Component components[] = container.getComponents();
        /*um for varre todos os componentes encontrados*/
        for (Component component : components) {
            /*verifica os que são formatted textfield, ou textfield ou um container*/
            /*e os reseta*/
            if (component instanceof JFormattedTextField) {
                JFormattedTextField field = (JFormattedTextField) component;
                field.setValue(null);
            } else if (component instanceof JTextField) {
                JTextField field = (JTextField) component;
                field.setText("");
            } else if (component instanceof Container) {
                limpaTodosCampos((Container) component);
            }
        }
    }
}
