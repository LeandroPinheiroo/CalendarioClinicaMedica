/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.convenio;


import excecao.ServicoException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import modelo.Convenio;
import servico.ConvenioServico;
import visao.TelaPrincipal;

/**
 *
 * @author weth767
 */
public class TelaConsultaConvenio extends javax.swing.JInternalFrame {

    private TelaPrincipal telaPrincipal;
    private DefaultTableModel model;
    /**
     * Creates new form CategoryConsultScreen
     */
    public TelaConsultaConvenio(TelaPrincipal telaPrincipal) {
        initComponents();
        this.telaPrincipal = telaPrincipal;
        this.model = (DefaultTableModel) tabelaConvenio.getModel(); 
        model.setRowCount(0);
    }

    /*método para setar as linhas da tabela*/
    public void setDadosTabela(){
        /*zera as linhas da tabela, para não acumular*/
        model.setRowCount(0);
        /*instancia o serviço*/
        ConvenioServico cs = new ConvenioServico();
        Convenio convenio = null;
        List<Convenio> convenios = null;
        /*verifica os filtros*/
        if(comboSearch.getSelectedItem().toString().equals("Sem Filtragem")){
            convenios = cs.buscarTodos();
        }
        else if(comboSearch.getSelectedItem().toString().equals("ID")){
            /*verifica se não recebeu um id inválido*/
            if(textFilter.getText().matches("\\d+")){
                /*senão recebeu, busca o convenio*/
                try {
                    convenio = cs.buscaPorID(Integer.parseInt(textFilter.getText()));
                } catch (ServicoException e) {
                    /*se houver erro, mostra mensagem na tela*/
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        /*busca o convenio por nome*/
        else if(comboSearch.getSelectedItem().toString().equals("Nome")){
            try {
                convenio = cs.buscaPorNome(textFilter.getText());
            } catch (ServicoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        /*busca os convenios por status*/
        else if(comboSearch.getSelectedItem().toString().equals("Status")){
            if(textFilter.getText().toLowerCase().equals("true") || textFilter.getText().toLowerCase().equals("false")){
                convenios = cs.buscaPorStatus(Boolean.valueOf(textFilter.getText()));
            }
        }
        /*verifica qual a foi preenchido, se foi a lista ou apenas um valor*/
        if(convenio != null){
            /*seta a linha na tabela*/
            Object data[] = {convenio.getId(),convenio.getNome(),convenio.getDescricao(), convenio.isStatus()};
            model.addRow(data);
        }
        /*caso foi linhas*/
        else if(convenios != null){
            for(Convenio c : convenios){
                /*seta as linhas na tabela*/
                Object data[] = {c.getId(), c.getNome(), c.getDescricao(), c.isStatus()};
                model.addRow(data);
            }
        }
    }

    /*método para atualizar um convenio*/
    public void atualizarConvenio(){
        /*verifica se selecionou alguma lina da tabela*/
        if(tabelaConvenio.getSelectedRow() != -1){
            /*se sim guarda a linha*/
            int line = tabelaConvenio.getSelectedRow();
            /*instancia o serviço*/
            ConvenioServico cs = new ConvenioServico();
            /*busca o convenio*/
            try {
                Convenio convenio = cs.buscaPorID((Integer) model.getValueAt(line , 0));
                /*depois envia para a tela de consulta*/
                TelaAtualizacaoConvenio tac = new TelaAtualizacaoConvenio(this,convenio);
                /*deixa a tela essa tela visivel e deixa a de consulta invisivel*/
                tac.setVisible(true);
                this.setVisible(false);
                /*depois adiciona a tela principal*/
                telaPrincipal.getContentPane().add(tac);
            } catch (ServicoException e) {
                /*se houver erro, mostra mensagem*/
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*método para remover uma um convenio*/
    public void removerConvenio(){
        /*verifica se selecionou alguma linha da tabela*/
        if(tabelaConvenio.getSelectedRow() != -1){
            /*guarda a linha selecionada*/
            int line = tabelaConvenio.getSelectedRow();
            /*instancia o serviço*/
            ConvenioServico cs = new ConvenioServico();
            /*busca o convênio de acordo o id na coluna 0 da linha selecionada*/
            try {
                Convenio c = cs.buscaPorID((Integer) model.getValueAt(line , 0 ));
                /*depois verifica se o usuário deseja realmente apagar esse convênio*/
                int anwser = JOptionPane.showConfirmDialog(this, "Deseja realmente apagar o convênio: "
                +c.getNome(), "Aviso", JOptionPane.WARNING_MESSAGE);
                /*caso a resposta for sim, apaga a categoria*/
                if(anwser == JOptionPane.YES_OPTION){
                    cs.remover(c.getId());
                }
                /*se houver erro, mostra mensagem com o erro na tela*/
            } catch (ServicoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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

        labelFilter = new javax.swing.JLabel();
        textFilter = new javax.swing.JTextField();
        comboSearch = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnCleanTable = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConvenio = new javax.swing.JTable();

        setClosable(true);
        setTitle("Consulta de Convênios");
        setToolTipText("");

        labelFilter.setText("Filtro:");

        textFilter.setToolTipText("Informação de filtragem do Convênio");

        comboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sem Filtragem", "ID", "Nome", "Status" }));
        comboSearch.setToolTipText("Filtro de consultas de Convênio");

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

        tabelaConvenio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Descrição", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaConvenio.setToolTipText("Tabela de Convênios");
        tabelaConvenio.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaConvenio.setSurrendersFocusOnKeystroke(true);
        tabelaConvenio.getTableHeader().setResizingAllowed(false);
        tabelaConvenio.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaConvenio);
        if (tabelaConvenio.getColumnModel().getColumnCount() > 0) {
            tabelaConvenio.getColumnModel().getColumn(0).setResizable(false);
            tabelaConvenio.getColumnModel().getColumn(0).setPreferredWidth(105);
            tabelaConvenio.getColumnModel().getColumn(1).setResizable(false);
            tabelaConvenio.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabelaConvenio.getColumnModel().getColumn(2).setResizable(false);
            tabelaConvenio.getColumnModel().getColumn(2).setPreferredWidth(250);
            tabelaConvenio.getColumnModel().getColumn(3).setResizable(false);
            tabelaConvenio.getColumnModel().getColumn(3).setPreferredWidth(120);
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
                .addComponent(labelFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilter)
                    .addComponent(textFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        atualizarConvenio();
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
        removerConvenio();
    }//GEN-LAST:event_btnRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCleanTable;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> comboSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFilter;
    private javax.swing.JTable tabelaConvenio;
    private javax.swing.JTextField textFilter;
    // End of variables declaration//GEN-END:variables
}
