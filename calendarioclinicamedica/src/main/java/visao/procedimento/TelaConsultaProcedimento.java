/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.procedimento;


import excecao.ServicoException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import modelo.Procedimento;
import servico.ProcedimentoServico;
import visao.TelaPrincipal;

/**
 *
 * @author weth767
 */
public class TelaConsultaProcedimento extends javax.swing.JInternalFrame {

    private TelaPrincipal telaPrincipal;
    private DefaultTableModel model;
    
    public TelaConsultaProcedimento(TelaPrincipal telaPrincipal) {
        initComponents();
        this.telaPrincipal = telaPrincipal;
        this.model = (DefaultTableModel) tabelaProcedimentos.getModel(); 
        model.setRowCount(0);
    }

    /*método para setar as linhas da tabela*/
    public void setDadosTabela(){
        /*zera as linhas da tabela, para não acumular*/
        model.setRowCount(0);
        /*instancia o serviço*/
        ProcedimentoServico ps = new ProcedimentoServico();
        Procedimento procedimento = null;
        List<Procedimento> procedimentos = null;
        /*verifica os filtros*/
        if(comboBusca.getSelectedItem().toString().equals("Sem Filtragem")){
            procedimentos = ps.buscaTodos();
        }
        else if(comboBusca.getSelectedItem().toString().equals("ID")){
            /*verifica se não recebeu um id inválido*/
            if(campoFiltro.getText().matches("\\d+")){
                /*senão recebeu, busca o procedimento*/
                try {
                    procedimento = ps.buscaPorID(Integer.parseInt(campoFiltro.getText()));
                } catch (ServicoException e) {
                    /*se houver erro, mostra mensagem na tela*/
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        /*busca o procedimento pelo nome*/
        else if(comboBusca.getSelectedItem().toString().equals("Nome")){
            try {
                procedimento = ps.buscaPeloNome(campoFiltro.getText());
            } catch (ServicoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        /*busca os procedimentos por status*/
        else if(comboBusca.getSelectedItem().toString().equals("Status")){
            if(campoFiltro.getText().toLowerCase().equals("true") || campoFiltro.getText().toLowerCase().equals("false")){
                procedimentos = ps.buscaPorStatus(Boolean.valueOf(campoFiltro.getText()));
            }
        }
        /*verifica qual a foi preenchido, se foi a lista ou apenas um valor*/
        if(procedimento != null){
            /*seta a linha na tabela*/
            Object data[] = {procedimento.getId(),procedimento.getNome(),procedimento.getDescricao(),
            ajusteFormatoDinheiro(Float.toString(procedimento.getPreco())),
            procedimento.isStatus()};
            model.addRow(data);
        }
        /*caso foi linhas*/
        else if(procedimentos != null){
            for(Procedimento p : procedimentos){
                /*seta as linhas na tabela*/
                Object data[] = {p.getId(), p.getNome(), p.getDescricao(), 
                    ajusteFormatoDinheiro(Float.toString(p.getPreco())),p.isStatus()};
                model.addRow(data);
            }
        }
    }

    /*método para atualizar um procedimento*/
    public void atualizarProcedimento(){
        /*verifica se selecionou alguma lina da tabela*/
        if(tabelaProcedimentos.getSelectedRow() != -1){
            /*se sim guarda a linha*/
            int line = tabelaProcedimentos.getSelectedRow();
            /*instancia o serviço*/
            ProcedimentoServico ps = new ProcedimentoServico();
            /*busca o procedimento*/
            try {
                Procedimento procedimento = ps.buscaPorID((Integer) model.getValueAt(line , 0));
                /*depois envia para a tela de atualização*/
                TelaAtualizacaoProcedimento tap = new TelaAtualizacaoProcedimento(this,procedimento);
                /*deixa a tela essa tela visivel e deixa a de consulta invisivel*/
                tap.setVisible(true);
                this.setVisible(false);
                /*depois adiciona a tela principal*/
                telaPrincipal.getContentPane().add(tap);
            } catch (ServicoException e) {
                /*se houver erro, mostra mensagem*/
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*método para remover um procedimento*/
    public void removerProcedimento(){
        /*verifica se selecionou alguma linha da tabela*/
        if(tabelaProcedimentos.getSelectedRow() != -1){
            /*guarda a linha selecionada*/
            int line = tabelaProcedimentos.getSelectedRow();
            /*instancia o serviço*/
            ProcedimentoServico ps = new ProcedimentoServico();
            /*busca o convênio de acordo o id na coluna 0 da linha selecionada*/
            try {
                Procedimento p = ps.buscaPorID((Integer) model.getValueAt(line , 0 ));
                /*depois verifica se o usuário deseja realmente apagar esse procedimento*/
                int anwser = JOptionPane.showConfirmDialog(this, "Deseja realmente apagar o procedimento: "
                +p.getNome(), "Aviso", JOptionPane.WARNING_MESSAGE);
                /*caso a resposta for sim, apaga o prontuario*/
                if(anwser == JOptionPane.YES_OPTION){
                    ps.remover(p.getId());
                }
                /*se houver erro, mostra mensagem com o erro na tela*/
            } catch (ServicoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /*método para ajustar o dinheiro no formato mais compreensível*/
    public String ajusteFormatoDinheiro(String texto) {
        /*converte o valor para float*/
        float value = Float.parseFloat(texto.replaceAll("\\.", ",").replaceAll(",", ".").replaceAll("R","")
                .replaceAll("\\$",""));
        /*e o formata*/
        return(String.format("R$ %.2f", value));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelFiltro = new javax.swing.JLabel();
        campoFiltro = new javax.swing.JTextField();
        comboBusca = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnCleanTable = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProcedimentos = new javax.swing.JTable();

        setClosable(true);
        setTitle("Consulta de Procedimentos");
        setToolTipText("");

        labelFiltro.setText("Filtro:");

        campoFiltro.setToolTipText("Informação de filtragem do Convênio");

        comboBusca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sem Filtragem", "ID", "Nome", "Status" }));
        comboBusca.setToolTipText("Filtro de consultas de Convênio");

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search-24.png"))); // NOI18N
        btnSearch.setToolTipText("Consulta de categorias");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete-24.png"))); // NOI18N
        btnRemove.setToolTipText("Apagar um Convênio");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnCleanTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/clean-24.png"))); // NOI18N
        btnCleanTable.setToolTipText("Limpar as linhas da tabela(não apaga os dados)");
        btnCleanTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanTableActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/update-24.png"))); // NOI18N
        btnUpdate.setToolTipText("Alterar um Convênio");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancel-24.png"))); // NOI18N
        btnCancel.setToolTipText("Cancelar Operação");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        tabelaProcedimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Descrição", "Preço", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProcedimentos.setToolTipText("Tabela de Convênios");
        tabelaProcedimentos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaProcedimentos.setSurrendersFocusOnKeystroke(true);
        tabelaProcedimentos.getTableHeader().setResizingAllowed(false);
        tabelaProcedimentos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaProcedimentos);
        if (tabelaProcedimentos.getColumnModel().getColumnCount() > 0) {
            tabelaProcedimentos.getColumnModel().getColumn(0).setResizable(false);
            tabelaProcedimentos.getColumnModel().getColumn(0).setPreferredWidth(105);
            tabelaProcedimentos.getColumnModel().getColumn(1).setResizable(false);
            tabelaProcedimentos.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabelaProcedimentos.getColumnModel().getColumn(2).setResizable(false);
            tabelaProcedimentos.getColumnModel().getColumn(2).setPreferredWidth(250);
            tabelaProcedimentos.getColumnModel().getColumn(3).setResizable(false);
            tabelaProcedimentos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabelaProcedimentos.getColumnModel().getColumn(4).setResizable(false);
            tabelaProcedimentos.getColumnModel().getColumn(4).setPreferredWidth(120);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCleanTable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel)
                        .addGap(421, 421, 421)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(labelFiltro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFiltro)
                    .addComponent(campoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCleanTable, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        atualizarProcedimento();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        setDadosTabela();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCleanTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanTableActionPerformed
        model.setRowCount(0);
    }//GEN-LAST:event_btnCleanTableActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        removerProcedimento();
    }//GEN-LAST:event_btnRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCleanTable;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField campoFiltro;
    private javax.swing.JComboBox<String> comboBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFiltro;
    private javax.swing.JTable tabelaProcedimentos;
    // End of variables declaration//GEN-END:variables
}
