/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import visao.agendamento.TelaAgendamento;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import visao.convenio.TelaCadastroConvenio;
import visao.convenio.TelaConsultaConvenio;
import visao.especializacao.TelaCadastroEspecializacao;
import visao.especializacao.TelaConsultaEspecializacao;
import visao.medico.TelaCadastroMedico;
import visao.medico.TelaConsultaMedico;
import visao.paciente.TelaCadastroPaciente;
import visao.paciente.TelaConsultaPaciente;
import visao.procedimento.TelaCadastroProcedimento;
import visao.procedimento.TelaConsultaProcedimento;
import visao.secretaria.TelaCadastroSecretaria;
import visao.secretaria.TelaConsultaSecretaria;

/**
 *
 * @author weth767
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    private JDesktopPane painel;
    
    
    public TelaPrincipal() {
        initComponents();
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        this.setMaximumSize(DimMax);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        painel = new JDesktopPane();
        setContentPane(painel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuPaciente = new javax.swing.JMenu();
        menuItemCadastroPaciente = new javax.swing.JMenuItem();
        menuItemConsultaPaciente = new javax.swing.JMenuItem();
        menuAgendamento = new javax.swing.JMenu();
        menuItemAgendaConsulta = new javax.swing.JMenuItem();
        menuConvenio = new javax.swing.JMenu();
        menuItemCadastraConvenio = new javax.swing.JMenuItem();
        menuItemConsultaConvenio = new javax.swing.JMenuItem();
        menuEspecializacao = new javax.swing.JMenu();
        menuItemCadastraEspecializacao = new javax.swing.JMenuItem();
        menuItemConsultaEspecializacao = new javax.swing.JMenuItem();
        menuMedico = new javax.swing.JMenu();
        menuItemCadastraMedico = new javax.swing.JMenuItem();
        menuItemConsultaMedico = new javax.swing.JMenuItem();
        menuSecretaria = new javax.swing.JMenu();
        menuItemCadastraSecretaria = new javax.swing.JMenuItem();
        menuItemConsultaSecretaria = new javax.swing.JMenuItem();
        menuProcedimento = new javax.swing.JMenu();
        menuItemCadastraProcedimento = new javax.swing.JMenuItem();
        menuItemConsultaProcedimento = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Software Gerente de Clínica");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        menuPaciente.setText("Paciente");

        menuItemCadastroPaciente.setText("Cadastro de Paciente");
        menuItemCadastroPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroPacienteActionPerformed(evt);
            }
        });
        menuPaciente.add(menuItemCadastroPaciente);

        menuItemConsultaPaciente.setText("Consulta de Paciente");
        menuItemConsultaPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultaPacienteActionPerformed(evt);
            }
        });
        menuPaciente.add(menuItemConsultaPaciente);

        jMenuBar1.add(menuPaciente);

        menuAgendamento.setText("Agendamento");

        menuItemAgendaConsulta.setText("Agendar Consulta/Procedimento");
        menuItemAgendaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAgendaConsultaActionPerformed(evt);
            }
        });
        menuAgendamento.add(menuItemAgendaConsulta);

        jMenuBar1.add(menuAgendamento);

        menuConvenio.setText("Convênio");

        menuItemCadastraConvenio.setText("Cadastro de Convênio");
        menuItemCadastraConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastraConvenioActionPerformed(evt);
            }
        });
        menuConvenio.add(menuItemCadastraConvenio);

        menuItemConsultaConvenio.setText("Consulta de Convênio");
        menuItemConsultaConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultaConvenioActionPerformed(evt);
            }
        });
        menuConvenio.add(menuItemConsultaConvenio);

        jMenuBar1.add(menuConvenio);

        menuEspecializacao.setText("Especialização Médica");

        menuItemCadastraEspecializacao.setText("Cadastro de Especialização");
        menuItemCadastraEspecializacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastraEspecializacaoActionPerformed(evt);
            }
        });
        menuEspecializacao.add(menuItemCadastraEspecializacao);

        menuItemConsultaEspecializacao.setText("Consulta de Especialização");
        menuItemConsultaEspecializacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultaEspecializacaoActionPerformed(evt);
            }
        });
        menuEspecializacao.add(menuItemConsultaEspecializacao);

        jMenuBar1.add(menuEspecializacao);

        menuMedico.setText("Médico");

        menuItemCadastraMedico.setText("Cadastro de Médico");
        menuItemCadastraMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastraMedicoActionPerformed(evt);
            }
        });
        menuMedico.add(menuItemCadastraMedico);

        menuItemConsultaMedico.setText("Consulta de Médico");
        menuItemConsultaMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultaMedicoActionPerformed(evt);
            }
        });
        menuMedico.add(menuItemConsultaMedico);

        jMenuBar1.add(menuMedico);

        menuSecretaria.setText("Secretária");

        menuItemCadastraSecretaria.setText("Cadastro de Secretária");
        menuItemCadastraSecretaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastraSecretariaActionPerformed(evt);
            }
        });
        menuSecretaria.add(menuItemCadastraSecretaria);

        menuItemConsultaSecretaria.setText("Consulta de Secretária");
        menuItemConsultaSecretaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultaSecretariaActionPerformed(evt);
            }
        });
        menuSecretaria.add(menuItemConsultaSecretaria);

        jMenuBar1.add(menuSecretaria);

        menuProcedimento.setText("Procedimento");

        menuItemCadastraProcedimento.setText("Cadastro de Procedimento ");
        menuItemCadastraProcedimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastraProcedimentoActionPerformed(evt);
            }
        });
        menuProcedimento.add(menuItemCadastraProcedimento);

        menuItemConsultaProcedimento.setText("Consulta de Procedimento");
        menuItemConsultaProcedimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultaProcedimentoActionPerformed(evt);
            }
        });
        menuProcedimento.add(menuItemConsultaProcedimento);

        jMenuBar1.add(menuProcedimento);

        jMenu3.setText("jMenu3");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("jMenu4");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemAgendaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAgendaConsultaActionPerformed
       TelaAgendamento telaAgendamento = new TelaAgendamento(this);
       telaAgendamento.setVisible(true);
       painel.add(telaAgendamento);
    }//GEN-LAST:event_menuItemAgendaConsultaActionPerformed

    private void menuItemCadastroPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroPacienteActionPerformed
        TelaCadastroPaciente tcp = new TelaCadastroPaciente();
        tcp.setVisible(true);
        painel.add(tcp);
    }//GEN-LAST:event_menuItemCadastroPacienteActionPerformed

    private void menuItemConsultaPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultaPacienteActionPerformed
        TelaConsultaPaciente tcp = new TelaConsultaPaciente(this);
        tcp.setVisible(true);
        painel.add(tcp);
    }//GEN-LAST:event_menuItemConsultaPacienteActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema?",
                "Verificação de saída",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(resposta == JOptionPane.YES_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void menuItemCadastraConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastraConvenioActionPerformed
        TelaCadastroConvenio tcc = new TelaCadastroConvenio();
        tcc.setVisible(true);
        painel.add(tcc);
    }//GEN-LAST:event_menuItemCadastraConvenioActionPerformed

    private void menuItemConsultaConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultaConvenioActionPerformed
        TelaConsultaConvenio tcc = new TelaConsultaConvenio(this);
        tcc.setVisible(true);
        painel.add(tcc);
    }//GEN-LAST:event_menuItemConsultaConvenioActionPerformed

    private void menuItemCadastraEspecializacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastraEspecializacaoActionPerformed
        TelaCadastroEspecializacao tce = new TelaCadastroEspecializacao();
        tce.setVisible(true);
        painel.add(tce);
    }//GEN-LAST:event_menuItemCadastraEspecializacaoActionPerformed

    private void menuItemConsultaEspecializacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultaEspecializacaoActionPerformed
        TelaConsultaEspecializacao tce = new TelaConsultaEspecializacao(this);
        tce.setVisible(true);
        painel.add(tce);
    }//GEN-LAST:event_menuItemConsultaEspecializacaoActionPerformed

    private void menuItemCadastraMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastraMedicoActionPerformed
        TelaCadastroMedico tcm = new TelaCadastroMedico();
        tcm.setVisible(true);
        painel.add(tcm);
    }//GEN-LAST:event_menuItemCadastraMedicoActionPerformed

    private void menuItemConsultaMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultaMedicoActionPerformed
        TelaConsultaMedico tcm = new TelaConsultaMedico(this);
        tcm.setVisible(true);
        painel.add(tcm);
    }//GEN-LAST:event_menuItemConsultaMedicoActionPerformed

    private void menuItemCadastraSecretariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastraSecretariaActionPerformed
        TelaCadastroSecretaria tcs = new TelaCadastroSecretaria();
        tcs.setVisible(true);
        painel.add(tcs);
    }//GEN-LAST:event_menuItemCadastraSecretariaActionPerformed

    private void menuItemConsultaSecretariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultaSecretariaActionPerformed
        TelaConsultaSecretaria tcs = new TelaConsultaSecretaria(this);
        tcs.setVisible(true);
        painel.add(tcs);
    }//GEN-LAST:event_menuItemConsultaSecretariaActionPerformed

    private void menuItemCadastraProcedimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastraProcedimentoActionPerformed
        TelaCadastroProcedimento tcp = new TelaCadastroProcedimento();
        tcp.setVisible(true);
        painel.add(tcp);
    }//GEN-LAST:event_menuItemCadastraProcedimentoActionPerformed

    private void menuItemConsultaProcedimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultaProcedimentoActionPerformed
        TelaConsultaProcedimento tcp = new TelaConsultaProcedimento(this);
        tcp.setVisible(true);
        painel.add(tcp);
    }//GEN-LAST:event_menuItemConsultaProcedimentoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuAgendamento;
    private javax.swing.JMenu menuConvenio;
    private javax.swing.JMenu menuEspecializacao;
    private javax.swing.JMenuItem menuItemAgendaConsulta;
    private javax.swing.JMenuItem menuItemCadastraConvenio;
    private javax.swing.JMenuItem menuItemCadastraEspecializacao;
    private javax.swing.JMenuItem menuItemCadastraMedico;
    private javax.swing.JMenuItem menuItemCadastraProcedimento;
    private javax.swing.JMenuItem menuItemCadastraSecretaria;
    private javax.swing.JMenuItem menuItemCadastroPaciente;
    private javax.swing.JMenuItem menuItemConsultaConvenio;
    private javax.swing.JMenuItem menuItemConsultaEspecializacao;
    private javax.swing.JMenuItem menuItemConsultaMedico;
    private javax.swing.JMenuItem menuItemConsultaPaciente;
    private javax.swing.JMenuItem menuItemConsultaProcedimento;
    private javax.swing.JMenuItem menuItemConsultaSecretaria;
    private javax.swing.JMenu menuMedico;
    private javax.swing.JMenu menuPaciente;
    private javax.swing.JMenu menuProcedimento;
    private javax.swing.JMenu menuSecretaria;
    // End of variables declaration//GEN-END:variables
}
