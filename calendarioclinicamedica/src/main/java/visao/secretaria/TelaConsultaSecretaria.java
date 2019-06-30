package visao.secretaria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import excecao.ServicoException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Secretaria;
import servico.SecretariaServico;
import util.ConversorData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import visao.TelaPrincipal;

/**
 * @author weth767
 */
public class TelaConsultaSecretaria extends javax.swing.JInternalFrame {
    
     
    private TelaPrincipal telaPrincipal;  
    private DefaultTableModel modelo;

    public TelaConsultaSecretaria(TelaPrincipal telaPrincipal) {
        initComponents();
        this.telaPrincipal = telaPrincipal;
        this.modelo = (DefaultTableModel) tabelaSecretarias.getModel();
        modelo.setRowCount(0);
    }

    /*método para setar todos as secretarias cadastrados verificando o filtro de busca*/
    public void setDadosTabela() {
        modelo.setRowCount(0);
        SecretariaServico ss = new SecretariaServico();
        /*verifica se o filtro é sem filtro*/
        List<Secretaria> secretarias = null;
        Secretaria secretaria = null;
        /*verifica cada filtro e vai pegando os dados conforme o filtro e mostra mensagem de erro caso erros acontecem*/
        /*o primeiro é o sem filtro, que retorna todos*/
        if (comboSearch.getSelectedItem().toString().equals("Sem Filtragem")) {
            secretarias = ss.buscaTodos();
            /*o segundo é pelo nome*/
        } else if (comboSearch.getSelectedItem().toString().equals("Nome")) {
            try {
                secretarias = ss.buscaPeloNome(campoFiltro.getText().trim());
            } catch (ServicoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            /*pelo id*/
        } else if (comboSearch.getSelectedItem().toString().equals("ID")) {
            String text = campoFiltro.getText();
            if (text.matches("\\d+")) {
                try {
                    secretaria = ss.buscaPorID(Integer.parseInt(text));
                } catch (ServicoException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
            /*pelo cpf*/
        } else if (comboSearch.getSelectedItem().toString().equals("CPF")) {
            try {
                secretaria = ss.buscaPorCPF(campoFiltro.getText());
            } catch (ServicoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            /*e pelo status*/
        } else if (comboSearch.getSelectedItem().toString().equals("Status")) {
            if (campoFiltro.getText().toLowerCase().equals("true") || campoFiltro.getText().toLowerCase().equals("false")) {
                secretarias = ss.buscaPorStatus(Boolean.valueOf(campoFiltro.getText()));
            }
        }
        /*depois verifica se é uma secretaria só para os métodos de cpf e id ou uma lista para nome, stauts e sem filtro*/
        if (secretaria != null) {
            Object data[] = {secretaria.getId(), secretaria.getNome(), secretaria.getCpf(), secretaria.getRg(),
                secretaria.getEmail(),ConversorData.converetDataUSAtParaBR(secretaria.getDataCadastro().toString()), 
                secretaria.getCelular(), secretaria.getCidade().getEstado(), 
                secretaria.getCidade().getNome(), secretaria.getCep(), 
                secretaria.getBairro(), secretaria.getRua(), secretaria.getNumero(), 
                secretaria.getComplemento(),
                secretaria.isStatus()};
            modelo.addRow(data);
        } else if (secretarias != null) {
            for (Secretaria s : secretarias) {
                Object data[] = {s.getId(), s.getNome(), s.getCpf(), s.getRg(),
                s.getEmail(),ConversorData.converetDataUSAtParaBR(s.getDataCadastro().toString()), 
                s.getCelular(), s.getCidade().getEstado(), 
                s.getCidade().getNome(), s.getCep(), 
                s.getBairro(), s.getRua(), s.getNumero(), 
                s.getComplemento(),
                s.isStatus()};
                modelo.addRow(data);
            }
        }
    }

    /*método para remover uma secretaria*/
    public void removerSecretaria() {
        SecretariaServico ss = new SecretariaServico();
        /*verifica se tem uma linha selecionada*/
        if (tabelaSecretarias.getSelectedRow() != -1) {
            Secretaria s = null;
            /*pega o id*/
            int id = (Integer) modelo.getValueAt(tabelaSecretarias.getSelectedRow(), 0);
            /*busca o cliente*/
            try {
                s = ss.buscaPorID(id);
                /*verifica se o usuário realmente deseja apagar o secretaria*/
                int answer = JOptionPane.showConfirmDialog(this, "Deseja realmente apagar a Secretaria: " + 
                        s.getNome()+ ", do CPF: " + s.getCpf(), "Aviso", JOptionPane.WARNING_MESSAGE);
                /*se a resposta for sim*/
                if (answer == JOptionPane.YES_OPTION) {
                    /*removerSecretaria então o cliente*/
                    ss.remover(s.getId());
                    setDadosTabela();
                }
                /*caso der erro, mostra mensagem*/
            } catch (ServicoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*método para atualizar a secretaria*/
    public void atualizarSecretaria() {
        /*verifica se alguma linha foi selecionada*/
        if (tabelaSecretarias.getSelectedRow() != -1) {
            /*se foi, instancia o serviço*/
            SecretariaServico ss = new SecretariaServico();
            Secretaria s = null;
            /*pega a linha selecionada*/
            int line = tabelaSecretarias.getSelectedRow();
            /*tenta buscar a secretaria pelo id*/
            try {
                s = ss.buscaPorID((Integer) modelo.getValueAt(line, 0));
            } /*se houver erro, mostra mensagem ao usuário*/ catch (ServicoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            /*se foi encontrado a secretaria*/
            if (s != null) {
                /*envia para a tela de atualização*/
                TelaAtualizacaoSecretaria tas = new TelaAtualizacaoSecretaria(this, s);
                tas.setVisible(true);
                this.setVisible(false);
                telaPrincipal.getContentPane().add(tas);
            }
        }
    }
    
    //public void generateReport(){
        /*verifica se há uma linha selecionada da tabela*/
        //if (tabelaPacientes.getSelectedRow() != -1) {
            /*pega a linha selecionada*/
            //int line = tabelaPacientes.getSelectedRow();
            /*instancia a classe de relatorio*/
            //MonthlyRecord record = new MonthlyRecord();
            /*verifica se o relatorio a ser gerado, deve ser mostrado em tela*/
            //int answer = JOptionPane.showConfirmDialog(this, "Mostrar em tela?","Dúvida",JOptionPane.YES_NO_OPTION,
            //        JOptionPane.QUESTION_MESSAGE);
            /*recebe as datas*/
            //String d1 = JOptionPane.showInputDialog("Digite a primeira data: ");
            //String d2 = JOptionPane.showInputDialog("Digite a segunda data: ");
            /*instancia o conversor*/
            //DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            //Date date1 = null;
            //Date date2 = null;
            //try{
                /*tenta converter as datas, se for possível converter*/
                //date1 = df.parse(d1);
                //date2 = df.parse(d2);
                /*faz a conversão, agora se der erro, indica que as datas não são validas*/
            //}catch(ParseException ex){
                /*nesse caso, mostra mensagem de erro e sai do método*/
                //JOptionPane.showMessageDialog(this, "ERRO, datas fornecedidas não são válidas!!", "Erro", 
                  //      JOptionPane.ERROR_MESSAGE);
                //return;
            //}
            //boolean visualize = false;
            /*verifica a resposta, caso for sim, mostrará em tela*/
            //if (answer == JOptionPane.YES_OPTION) {
                //visualize = true;
            //}
            /*tenta gerar o relatório*/
            //try {
                //record.generateRecord(visualize, connection.Con.getInstance(), 
                       // new ClientService().get(Integer.parseInt(modelo.getValueAt(line,0).toString()))
                //,date1,date2);
            //} catch (Exception ex) {
              //  JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            //}
        //}
    //}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaSecretarias = new javax.swing.JTable();
        labelFilter = new javax.swing.JLabel();
        campoFiltro = new javax.swing.JTextField();
        btnBusca = new javax.swing.JButton();
        btnCleanTable = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        comboSearch = new javax.swing.JComboBox<>();
        btnReport = new javax.swing.JButton();

        setClosable(true);
        setTitle("Consulta de Secretárias");
        setMaximumSize(null);
        setRequestFocusEnabled(false);

        tabelaSecretarias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF", "RG", "E-mail", "Data de Cadastro", "Celular", "Estado", "Cidade", "CEP", "Bairro", "Rua", "Número", "Complemento", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaSecretarias.setToolTipText("Tabela das secretárias");
        tabelaSecretarias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaSecretarias.setSurrendersFocusOnKeystroke(true);
        tabelaSecretarias.getTableHeader().setResizingAllowed(false);
        tabelaSecretarias.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaSecretarias);
        if (tabelaSecretarias.getColumnModel().getColumnCount() > 0) {
            tabelaSecretarias.getColumnModel().getColumn(0).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(0).setPreferredWidth(70);
            tabelaSecretarias.getColumnModel().getColumn(1).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(1).setPreferredWidth(250);
            tabelaSecretarias.getColumnModel().getColumn(2).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabelaSecretarias.getColumnModel().getColumn(3).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabelaSecretarias.getColumnModel().getColumn(4).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(4).setPreferredWidth(250);
            tabelaSecretarias.getColumnModel().getColumn(5).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(5).setPreferredWidth(130);
            tabelaSecretarias.getColumnModel().getColumn(6).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(6).setPreferredWidth(130);
            tabelaSecretarias.getColumnModel().getColumn(7).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(7).setPreferredWidth(150);
            tabelaSecretarias.getColumnModel().getColumn(8).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(8).setPreferredWidth(250);
            tabelaSecretarias.getColumnModel().getColumn(9).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(9).setPreferredWidth(100);
            tabelaSecretarias.getColumnModel().getColumn(10).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(10).setPreferredWidth(220);
            tabelaSecretarias.getColumnModel().getColumn(11).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(11).setPreferredWidth(220);
            tabelaSecretarias.getColumnModel().getColumn(12).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(12).setPreferredWidth(70);
            tabelaSecretarias.getColumnModel().getColumn(13).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(13).setPreferredWidth(150);
            tabelaSecretarias.getColumnModel().getColumn(14).setResizable(false);
            tabelaSecretarias.getColumnModel().getColumn(14).setPreferredWidth(50);
        }
        tabelaSecretarias.getAccessibleContext().setAccessibleName("");

        labelFilter.setText("Filtro:");

        campoFiltro.setToolTipText("Informação de filtragem das secretárias");

        btnBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search-24.png"))); // NOI18N
        btnBusca.setToolTipText("Consulta de médicos");
        btnBusca.setBorderPainted(false);
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        btnCleanTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/clean-24.png"))); // NOI18N
        btnCleanTable.setToolTipText("Limpar as linhas da tabela(não apaga os dados)");
        btnCleanTable.setBorderPainted(false);
        btnCleanTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanTableActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancel-24.png"))); // NOI18N
        btnCancel.setToolTipText("Cancelar Operação");
        btnCancel.setBorderPainted(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/update-24.png"))); // NOI18N
        btnUpdate.setToolTipText("Atualizar secretária");
        btnUpdate.setBorderPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete-24.png"))); // NOI18N
        btnRemove.setToolTipText("Apagar uma secretária");
        btnRemove.setBorderPainted(false);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        comboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sem Filtragem", "ID", "Nome", "CPF", "Status" }));
        comboSearch.setToolTipText("Filtro de busca das secretárias");

        btnReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/report-24.png"))); // NOI18N
        btnReport.setToolTipText("Gerar Relatório de Pacientes");
        btnReport.setBorderPainted(false);
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCleanTable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(labelFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusca)
                .addContainerGap(199, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBusca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelFilter)
                        .addComponent(campoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCleanTable, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        atualizarSecretaria();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        setDadosTabela();
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        removerSecretaria();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnCleanTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanTableActionPerformed
        modelo.setRowCount(0);
    }//GEN-LAST:event_btnCleanTableActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        //generateReport();
    }//GEN-LAST:event_btnReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCleanTable;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField campoFiltro;
    private javax.swing.JComboBox<String> comboSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFilter;
    private javax.swing.JTable tabelaSecretarias;
    // End of variables declaration//GEN-END:variables
}
