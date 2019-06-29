/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.especializacao;


import visao.convenio.*;
import excecao.ServicoException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import modelo.Convenio;
import modelo.Especializacao;
import servico.ConvenioServico;
import servico.EspecializacaoServico;
import visao.TelaPrincipal;

/**
 *
 * @author weth767
 */
public class TelaConsultaEspecializacao extends javax.swing.JInternalFrame {

    private TelaPrincipal telaPrincipal;
    private DefaultTableModel model;
    
    public TelaConsultaEspecializacao(TelaPrincipal telaPrincipal) {
        initComponents();
        this.telaPrincipal = telaPrincipal;
        this.model = (DefaultTableModel) tabelaEspecializacao.getModel(); 
        model.setRowCount(0);
    }

    /*método para setar as linhas da tabela*/
    public void setDadosTabela(){
        /*zera as linhas da tabela, para não acumular*/
        model.setRowCount(0);
        /*instancia o serviço*/
        EspecializacaoServico es = new EspecializacaoServico();
        Especializacao especializacao = null;
        List<Especializacao> especializacoes = null;
        /*verifica os filtros*/
        if(comboSearch.getSelectedItem().toString().equals("Sem Filtragem")){
            especializacoes = es.buscaTodos();
        }
        else if(comboSearch.getSelectedItem().toString().equals("ID")){
            /*verifica se não recebeu um id inválido*/
            if(textFilter.getText().matches("\\d+")){
                /*senão recebeu, busca a especialização*/
                try {
                    especializacao = es.buscaPorID(Integer.parseInt(textFilter.getText()));
                } catch (ServicoException e) {
                    /*se houver erro, mostra mensagem na tela*/
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        /*busca a especialização por nome*/
        else if(comboSearch.getSelectedItem().toString().equals("Nome")){
            try {
                especializacao = es.buscaPeloNome(textFilter.getText());
            } catch (ServicoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        /*busca os especializações por status*/
        else if(comboSearch.getSelectedItem().toString().equals("Status")){
            if(textFilter.getText().toLowerCase().equals("true") || textFilter.getText().toLowerCase().equals("false")){
                especializacoes = es.buscaPorStatus(Boolean.valueOf(textFilter.getText()));
            }
        }
        /*verifica qual a foi preenchido, se foi a lista ou apenas um valor*/
        if(especializacao != null){
            /*seta a linha na tabela*/
            Object data[] = {especializacao.getId(),especializacao.getNome(),especializacao.getDescricao(),
                especializacao.isStatus()};
            model.addRow(data);
        }
        /*caso foi linhas*/
        else if(especializacoes != null){
            for(Especializacao e : especializacoes){
                /*seta as linhas na tabela*/
                Object data[] = {e.getId(), e.getNome(), e.getDescricao(), e.isStatus()};
                model.addRow(data);
            }
        }
    }

    /*método para atualizar uma especialização*/
    public void atualizarEspecializacao(){
        /*verifica se selecionou alguma lina da tabela*/
        if(tabelaEspecializacao.getSelectedRow() != -1){
            /*se sim guarda a linha*/
            int line = tabelaEspecializacao.getSelectedRow();
            /*instancia o serviço*/
            EspecializacaoServico es = new EspecializacaoServico();
            /*busca a especialização*/
            try {
                Especializacao especializacao = es.buscaPorID((Integer) model.getValueAt(line , 0));
                /*depois envia para a tela de atualização*/
                TelaAtualizacaoEspecializacao tae = new TelaAtualizacaoEspecializacao(this,especializacao);
                /*deixa a tela essa tela visivel e deixa a de consulta invisivel*/
                tae.setVisible(true);
                this.setVisible(false);
                /*depois adiciona a tela principal*/
                telaPrincipal.getContentPane().add(tae);
            } catch (ServicoException e) {
                /*se houver erro, mostra mensagem*/
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*método para remover uma especialização*/
    public void removerEspecializacao(){
        /*verifica se selecionou alguma linha da tabela*/
        if(tabelaEspecializacao.getSelectedRow() != -1){
            /*guarda a linha selecionada*/
            int line = tabelaEspecializacao.getSelectedRow();
            /*instancia o serviço*/
            EspecializacaoServico es = new EspecializacaoServico();
            /*busca o convênio de acordo o id na coluna 0 da linha selecionada*/
            try {
                Especializacao e = es.buscaPorID((Integer) model.getValueAt(line , 0 ));
                /*depois verifica se o usuário deseja realmente apagar essa especialização*/
                int anwser = JOptionPane.showConfirmDialog(this, "Deseja realmente apagar a especiaçização: "
                +e.getNome(), "Aviso", JOptionPane.WARNING_MESSAGE);
                /*caso a resposta for sim, apaga a categoria*/
                if(anwser == JOptionPane.YES_OPTION){
                    es.remover(e.getId());
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
        tabelaEspecializacao = new javax.swing.JTable();

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

        tabelaEspecializacao.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaEspecializacao.setToolTipText("Tabela de Convênios");
        tabelaEspecializacao.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaEspecializacao.setSurrendersFocusOnKeystroke(true);
        tabelaEspecializacao.getTableHeader().setResizingAllowed(false);
        tabelaEspecializacao.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaEspecializacao);
        if (tabelaEspecializacao.getColumnModel().getColumnCount() > 0) {
            tabelaEspecializacao.getColumnModel().getColumn(0).setResizable(false);
            tabelaEspecializacao.getColumnModel().getColumn(0).setPreferredWidth(105);
            tabelaEspecializacao.getColumnModel().getColumn(1).setResizable(false);
            tabelaEspecializacao.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabelaEspecializacao.getColumnModel().getColumn(2).setResizable(false);
            tabelaEspecializacao.getColumnModel().getColumn(2).setPreferredWidth(250);
            tabelaEspecializacao.getColumnModel().getColumn(3).setResizable(false);
            tabelaEspecializacao.getColumnModel().getColumn(3).setPreferredWidth(120);
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
        atualizarEspecializacao();
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
        removerEspecializacao();
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
    private javax.swing.JTable tabelaEspecializacao;
    private javax.swing.JTextField textFilter;
    // End of variables declaration//GEN-END:variables
}
