/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.agendamento;

import excecao.ServicoException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Agendamento;
import modelo.Consulta;
import modelo.Medico;
import modelo.Paciente;
import modelo.Procedimento;
import modelo.Prontuario;
import servico.AgendamentoServico;
import servico.ConsultaServico;
import servico.ProcedimentoServico;
import servico.ProntuarioServico;
import util.CamposTextoUtil;
import visao.TelaPrincipal;
import visao.medico.TelaRetornoMedico;
import visao.paciente.TelaRetornoPaciente;

/**
 *
 * @author weth767
 */
public class TelaSalvaAgendamento extends javax.swing.JDialog {

    /**
     * Creates new form TelaSalvaAgendamento
     */
    public Paciente paciente;
    public Medico medico;
    private Time hora;
    private Date data;
    private TelaPrincipal telaPrincipal;

    public TelaSalvaAgendamento(java.awt.Frame parent, boolean modal, Time hora, Date data,
            TelaPrincipal telaPrincipal) {
        super(parent, modal);
        initComponents();
        this.telaPrincipal = telaPrincipal;
        this.hora = hora;
        this.data = data;
        setProcedimentos();
    }

    /*método para salvar o agendamento*/
    public void salvarAgendamento() {
        if (medico == null || paciente == null) {
            JOptionPane.showMessageDialog(this, "Informe o médico/paciente da consulta ou procedimento a ser "
                    + "agendado", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (campoPreco.getText().isEmpty() || campoPreco.getText().matches("[a-zA-Z]")) {
            JOptionPane.showMessageDialog(this, "Informe o preço da consulta e procedimentos do agendamento",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        /*cria a consulta, que depois será alterada, no momento da consulta*/
        Consulta consulta = new Consulta();
        consulta.setAvaliacao("Não foi feita a avaliação ainda");
        consulta.setStatus(false);
        /*agora instancia o serviço da consulta e do agendamento*/
        ConsultaServico consultaServico = new ConsultaServico();
        AgendamentoServico agendamentoServico = new AgendamentoServico();
        try {
            /*insere a consulta*/
            consultaServico.salvar(consulta);
            /*depois de inseri-la, busca ela e a preparada para ser inserida no agendamento*/
            consulta = consultaServico.buscaUltimaConsulta();
            /*instancia o agendamento*/
            Agendamento agendamento = new Agendamento();
            agendamento.setConsulta(consulta);
            agendamento.setData(data);
            agendamento.setHora(hora);
            agendamento.setMedico(medico);
            agendamento.setPaciente(paciente);
            /*verifica se o foi digitado o preço da consulta e procedimentos, se foi*/
            if (!(campoPreco.getText().isEmpty())) {
                /*pega o valor, retira os itens indesejados dele*/
                float valor = Float.parseFloat(campoPreco.getText().replaceAll("R", "")
                        .replaceAll("\\$", "").replaceAll(" ", "")
                        .replaceAll("\\.", "").replaceAll(",", "."));
                /*e passa para o agendamento*/
                agendamento.setPreco(valor);
            }
            /*pega os procedimentos selecionadas*/
            List<Procedimento> procedimentos = new ArrayList();
            for (int i = 0; i < listaProcedimentos.getSelectedValuesList().size(); i++) {
                /*busca os procedimentos pelo o nome e vai inserindo na lista*/
                procedimentos.add(new ProcedimentoServico()
                        .buscaPeloNome(listaProcedimentos.getSelectedValuesList().get(i)));
            }
            /*que depois é fornecedida a classe de agendamento*/
            agendamento.setProcedimentos(procedimentos);
            /*e por fim o agendamento é inserido*/
            agendamentoServico.salvar(agendamento);
            /*instancia o serviço e busca o prontuario pelo paciente*/
            ProntuarioServico prontuarioServico = new ProntuarioServico();
            Prontuario prontuario = prontuarioServico.buscaPeloPaciente(paciente);
            /*verifica não encontrou*/
            if(prontuario == null){
                /*instancia o prontuario e passa os valores*/
                prontuario = new Prontuario();
                prontuario.setProcedimentos(procedimentos);
                List<Medico> medicos = new ArrayList();
                List<Consulta> consultas = new ArrayList();
                medicos.add(medico);
                consultas.add(consulta);
                prontuario.setPaciente(paciente);
                prontuario.setConsultas(consultas);
                prontuario.setMedicos(medicos);
                prontuario.setData(new Date());
                /*e salva o prontuario*/
                prontuarioServico.salvar(prontuario);
            /*se encontrou o procedimento, pega as listas*/
            }else{
                /*pega os valores que ja tem para adicionar mais*/
                List<Medico> medicos = prontuario.getMedicos();
                List<Consulta> consultas = prontuario.getConsultas();
                List<Procedimento> procs = prontuario.getProcedimentos();
                /*adiciona mais aos que ja tem*/
                medicos.add(medico);
                consultas.add(consulta);
                procs.addAll(procedimentos);
                /*depois seta o paciente e a data novamente e as listas*/
                prontuario.setPaciente(paciente);
                prontuario.setData(new Date());
                prontuario.setConsultas(consultas);
                prontuario.setMedicos(medicos);
                prontuario.setProcedimentos(procedimentos);
                /*e depois atualiza o prontuario*/
                prontuarioServico.atualizar(prontuario);
            }
            /*e uma mensagem de sucesso é mostrada*/
            JOptionPane.showMessageDialog(this, "Agendamento realizado com sucesso", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            CamposTextoUtil.limpaTodosCampos(rootPane);
        } catch (ServicoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR);
        }
    }

    /*método para setar os procedimentos na lista*/
    public void setProcedimentos() {
        DefaultListModel modelo = new DefaultListModel();
        /*busca todas os procedimentos*/
        List<Procedimento> procedimentos = new ProcedimentoServico().buscaTodos();
        /*adiciona na lista*/
        for (Procedimento p : procedimentos) {
            modelo.addElement(p.getNome());
        }
        listaProcedimentos.setModel(modelo);
    }

    /*método para ajustar o dinheiro no formato mais compreensível*/
    public void ajusteFormatoDinheiro(JTextField campo) {
        String t = campo.getText().replaceAll("\\.", ",");
        /*converte o valor para float*/
        float value = Float.parseFloat(t.replaceAll("\\.", "").replaceAll(",", ".").replaceAll("R", "")
                .replaceAll("\\$", ""));
        /*e o formata*/
        campo.setText(String.format("R$ %.2f", value));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoPreco = new javax.swing.JTextField();
        labelPreco = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaProcedimentos = new javax.swing.JList<>();
        labelProcedimentos = new javax.swing.JLabel();
        btSalvarAgendamento = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        labelPaciente = new javax.swing.JLabel();
        campoNomePaciente = new javax.swing.JTextField();
        labelMedico = new javax.swing.JLabel();
        campoNomeMedico = new javax.swing.JTextField();
        btBuscaPaciente = new javax.swing.JButton();
        btBuscaMedico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Cadastro de Agendamento");

        campoPreco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoPrecoFocusLost(evt);
            }
        });

        labelPreco.setText("Preço");

        jScrollPane1.setViewportView(listaProcedimentos);

        labelProcedimentos.setText("Procedimentos");

        btSalvarAgendamento.setText("Salvar Agendamento");
        btSalvarAgendamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarAgendamentoActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        labelPaciente.setText("Paciente");

        campoNomePaciente.setEditable(false);

        labelMedico.setText("Médico");

        campoNomeMedico.setEditable(false);

        btBuscaPaciente.setText("Busca Paciente");
        btBuscaPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscaPacienteActionPerformed(evt);
            }
        });

        btBuscaMedico.setText("Busca Médico");
        btBuscaMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscaMedicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(labelProcedimentos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btSalvarAgendamento)
                        .addGap(18, 18, 18)
                        .addComponent(btCancelar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelPaciente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btBuscaPaciente))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMedico)
                            .addComponent(labelPreco))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(campoNomeMedico))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBuscaMedico)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelProcedimentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPaciente)
                    .addComponent(campoNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscaPaciente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMedico)
                    .addComponent(btBuscaMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPreco))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalvarAgendamento)
                    .addComponent(btCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoPrecoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPrecoFocusLost
        if (!(campoPreco.getText().isEmpty()) || campoPreco.getText().matches("[a-zA-Z")) {
            ajusteFormatoDinheiro(campoPreco);
        }
    }//GEN-LAST:event_campoPrecoFocusLost

    private void btSalvarAgendamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarAgendamentoActionPerformed
        salvarAgendamento();
    }//GEN-LAST:event_btSalvarAgendamentoActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btBuscaPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscaPacienteActionPerformed
        TelaRetornoPaciente telaRetornoPaciente = new TelaRetornoPaciente(telaPrincipal,
                true, this);
        telaRetornoPaciente.setVisible(true);
        if (paciente != null) {
            campoNomePaciente.setText(paciente.getNome());
        }
    }//GEN-LAST:event_btBuscaPacienteActionPerformed

    private void btBuscaMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscaMedicoActionPerformed
        TelaRetornoMedico telaRetornoMedico = new TelaRetornoMedico(telaPrincipal, true, this);
        telaRetornoMedico.setVisible(true);
        if (medico != null) {
            campoNomeMedico.setText(medico.getNome());
        }
    }//GEN-LAST:event_btBuscaMedicoActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaSalvaAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSalvaAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSalvaAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSalvaAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaSalvaAgendamento dialog = new TelaSalvaAgendamento(new javax.swing.JFrame(), true, null, null, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscaMedico;
    private javax.swing.JButton btBuscaPaciente;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btSalvarAgendamento;
    private javax.swing.JTextField campoNomeMedico;
    private javax.swing.JTextField campoNomePaciente;
    private javax.swing.JTextField campoPreco;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelMedico;
    private javax.swing.JLabel labelPaciente;
    private javax.swing.JLabel labelPreco;
    private javax.swing.JLabel labelProcedimentos;
    private javax.swing.JList<String> listaProcedimentos;
    // End of variables declaration//GEN-END:variables
}
