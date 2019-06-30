/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.agendamento;

import excecao.ServicoException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Agendamento;
import modelo.Procedimento;
import servico.AgendamentoServico;
import visao.TelaPrincipal;

/**
 *
 * @author weth767
 */
public class TelaAgendamentoDia extends javax.swing.JDialog {

    /**
     * Creates new form TelaAgendamentoDia
     */
    private DefaultTableModel modelo;
    private String minutos[] = {"00", "15", "30", "45"};
    private Date data;
    private TelaPrincipal telaPrincipal;

    public TelaAgendamentoDia(java.awt.Frame parent, boolean modal, Date data, TelaPrincipal telaPrincipal) {
        super(parent, modal);
        initComponents();
        this.data = data;
        this.modelo = (DefaultTableModel) tabelaAtendimentoDia.getModel();
        this.telaPrincipal = telaPrincipal;
        tabelaAtendimentoDia.setShowVerticalLines(true);
        tabelaAtendimentoDia.setShowHorizontalLines(true);
        geraHorasTabela();
        setAgendamentosDoDia();
    }

    /*método para abrir a tela de salvamento de agendamento*/
    public void preparaSalvamentoAgendamento() {
        /*pega a linha selecionada*/
        int linha = tabelaAtendimentoDia.getSelectedRow();
        /*verifica se alguma linha da tabela foi selecionada, se foi permite ir para a tela de salvamento*/
        if (linha != -1) {
            if(modelo.getValueAt(linha, 2) != null){
                JOptionPane.showMessageDialog(this, "Já há um agendamento cadastrado para esse horário");
                return;
            }
            /*busca o horario da linha selecionada*/
            String h = modelo.getValueAt(linha, 0).toString().trim();
            if (Integer.parseInt(h.split(":")[0]) < 10) {
                /*se a hora for menor que 10, insere o 0 na frente para ficar no formato de hora aceito
                pelo sql*/
                h = "0" + h;
            }
            h = h + ":00";
            TelaSalvaAgendamento tsa = new TelaSalvaAgendamento(telaPrincipal, true, Time.valueOf(h), data, telaPrincipal);
            tsa.setVisible(true);
            setAgendamentosDoDia();
        }
    }

    /*método para setar na tabela os agendamentos do dia*/
    public void setAgendamentosDoDia() {
        /*instancia o serviço */
        AgendamentoServico as = new AgendamentoServico();
        try {
            /*passa os agendamentos para a tabela*/
            List<Agendamento> agendamentos = as.buscaPorData(data);
            for (int i = 0; i < tabelaAtendimentoDia.getRowCount(); i++) {
                /*e vai passando o restante das informações para a tabela*/
                for (Agendamento a : agendamentos) {
                    /*pega a hora e converte para o formato de time do sql*/
                    String hr = converteFormatoHora(modelo.getValueAt(i, 0).toString());
                    /*verifica se a hora do agendamento verificado é igual a de uma das 
                    linhas da tabela*/
                    if (a.getHora().toString().equals(hr)) {
                        /*se for igual, seta as informações na tabela*/
                        modelo.setValueAt(a.getId(), i, 2);
                        modelo.setValueAt(a.getPaciente().getNome(), i, 3);
                        modelo.setValueAt(getProcedimentos(a.getProcedimentos()), i, 4);
                        modelo.setValueAt(a.getMedico().getNome(), i, 5);
                    }
                }
            }
        } catch (ServicoException ex) {
            /*se der erro, printa erro no console*/
            System.out.println(ex.getMessage());
        }
    }
    
    /*método para deixar a string de hora no formato aceito pelo sql time*/
    public String converteFormatoHora(String hora){
        int h = Integer.parseInt(hora.split(":")[0]);
        /*verifica se a hora for menor que 10, insere o 0 na frente */
        if(h < 10){
            hora = "0" + hora;
        }
        /*e por fim insere os segundos no final*/
        hora += ":00";
        return hora;
    }

    /*método para pegar os nomes dos procedimentos e transformar em lista*/
    public List<String> getProcedimentos(List<Procedimento> procedimentos) {
        List<String> procs = new ArrayList();
        for (Procedimento p : procedimentos) {
            procs.add(p.getNome());
        }
        return procs;
    }

    /*método para gerir todas as horas e datas na tabela*/
    public void geraHorasTabela() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        /*o primeiro for, valida todas as horas validas*/
        for (int i = 7; i < 19; i++) {
            /*o segundo for, é para os minutos*/
            for (int j = 0; j < minutos.length; j++) {
                /*concatena os minutos com as horas*/
                String h = Integer.toString(i) + ":" + minutos[j];
                Object dados[] = {h, formatador.format(data)};
                /*e insere na tabela*/
                modelo.addRow(dados);
            }
        }
    }
    
    /*método para apagar um agendamento*/
    public void removerAgendamento(){
        /*pega a linha selecionada*/
        int linha = tabelaAtendimentoDia.getSelectedRow();
        /*caso for = -1*/
        if(linha != -1){
            /*busca o id do agendamento*/
            int id = Integer.parseInt(modelo.getValueAt(linha, 2).toString());
            /*verifica se o usuário deseja realmente excluir o agendamento*/
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente apagar esse agendamento?",
                    "Aviso",JOptionPane.WARNING_MESSAGE);
            /*se a resposta for sim*/
            if(resposta == JOptionPane.YES_OPTION){
                try {
                    /*tenta remover o agendamento*/
                    new AgendamentoServico().remover(id);
                    JOptionPane.showMessageDialog(this, "Agendamento Removido com sucesso");
                    setAgendamentosDoDia();
                } catch (ServicoException ex) {
                    /*se houver problema, mostra o erro*/
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAtendimentoDia = new javax.swing.JTable();
        btnApagaAgendamento = new javax.swing.JButton();
        btnSalvaAgendamento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tabelaAtendimentoDia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hora", "Data", "ID", "Paciente", "Procedimento", "Médico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaAtendimentoDia);
        if (tabelaAtendimentoDia.getColumnModel().getColumnCount() > 0) {
            tabelaAtendimentoDia.getColumnModel().getColumn(0).setResizable(false);
            tabelaAtendimentoDia.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabelaAtendimentoDia.getColumnModel().getColumn(1).setResizable(false);
            tabelaAtendimentoDia.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabelaAtendimentoDia.getColumnModel().getColumn(2).setResizable(false);
            tabelaAtendimentoDia.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabelaAtendimentoDia.getColumnModel().getColumn(3).setResizable(false);
            tabelaAtendimentoDia.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabelaAtendimentoDia.getColumnModel().getColumn(4).setResizable(false);
            tabelaAtendimentoDia.getColumnModel().getColumn(4).setPreferredWidth(300);
            tabelaAtendimentoDia.getColumnModel().getColumn(5).setResizable(false);
            tabelaAtendimentoDia.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        btnApagaAgendamento.setText("Apaga Agendamento");
        btnApagaAgendamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagaAgendamentoActionPerformed(evt);
            }
        });

        btnSalvaAgendamento.setText("Salvar Agendamento");
        btnSalvaAgendamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaAgendamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(284, Short.MAX_VALUE)
                .addComponent(btnSalvaAgendamento)
                .addGap(18, 18, 18)
                .addComponent(btnApagaAgendamento)
                .addGap(283, 283, 283))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApagaAgendamento)
                    .addComponent(btnSalvaAgendamento))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvaAgendamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaAgendamentoActionPerformed
        preparaSalvamentoAgendamento();
    }//GEN-LAST:event_btnSalvaAgendamentoActionPerformed

    private void btnApagaAgendamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagaAgendamentoActionPerformed
        removerAgendamento();
    }//GEN-LAST:event_btnApagaAgendamentoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaAgendamentoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAgendamentoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAgendamentoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAgendamentoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaAgendamentoDia dialog = new TelaAgendamentoDia(new javax.swing.JFrame(), true, null, null);
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
    private javax.swing.JButton btnApagaAgendamento;
    private javax.swing.JButton btnSalvaAgendamento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaAtendimentoDia;
    // End of variables declaration//GEN-END:variables
}
