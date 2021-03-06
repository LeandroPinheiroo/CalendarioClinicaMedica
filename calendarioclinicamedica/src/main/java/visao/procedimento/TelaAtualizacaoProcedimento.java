/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.procedimento;


import excecao.ServicoException;
import javax.swing.*;
import modelo.Procedimento;
import servico.ProcedimentoServico;

/**
 *
 * @author weth767
 */
public class TelaAtualizacaoProcedimento extends javax.swing.JInternalFrame {

    private TelaConsultaProcedimento telaConsultaProcedimento;
    private Procedimento procedimento;
    
    public TelaAtualizacaoProcedimento(TelaConsultaProcedimento telaConsultaProcedimento, Procedimento procedimento) {
        initComponents();
        this.telaConsultaProcedimento = telaConsultaProcedimento;
        this.procedimento = procedimento;
        setDadosProcedimento();
    }
    
    /*método para setar os dados do procedimento nos campos*/
    public void setDadosProcedimento(){
        campoNome.setText(procedimento.getNome());
        campoPreco.setText(Float.toString(procedimento.getPreco()));
        ajusteFormatoDinheiro(campoPreco);
        campoDescricao.setText(procedimento.getDescricao());
        checkBoxStatus.setSelected(procedimento.isStatus());
    }
    
    /*método para atualizar um procedimento*/
    public void atualizarProcedimento(){
        /*instancia a procedimento e o serviço*/
        Procedimento procedimento = this.procedimento;
        ProcedimentoServico ps = new ProcedimentoServico();
        /*verifica se o campo de nome está vazio para obrigar a preencher o nome do procedimento*/
        if(campoNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"O nome do procedimento deve ser informado");
            return;
        }
        /*verifica se o foi digitado o preço do procedimento, se foi*/
        if(!(campoPreco.getText().isEmpty())) {
            /*pega o valor, retira os itens indesejados dele*/
            float valor = Float.parseFloat(campoPreco.getText().replaceAll("R", "")
                    .replaceAll("\\$", "").replaceAll(" ", "")
                    .replaceAll("\\.", "").replaceAll(",", "."));
            /*e passa para o procedimento*/
            procedimento.setPreco(valor);
        }
        procedimento.setNome(campoNome.getText());
        procedimento.setDescricao(campoDescricao.getText());
        procedimento.setStatus(checkBoxStatus.isSelected());
        try{
            /*atualiza a procedimento*/
            ps.atualizar(procedimento);
            /*e mostra mensagem de sucesso*/
            JOptionPane.showMessageDialog(this, "Procedimento atualizado com sucesso","Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (ServicoException e) {
            /*caso houver erro, mostra mensagem de erro*/
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*método para ajustar o dinheiro no formato mais compreensível*/
    public void ajusteFormatoDinheiro(JTextField campo) {
        String t = campo.getText().replaceAll("\\.", ",");
        /*converte o valor para float*/
        float value = Float.parseFloat(t.replaceAll("\\.", "").replaceAll(",", ".").replaceAll("R","")
                .replaceAll("\\$",""));
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

        labelName = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        labelDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescricao = new javax.swing.JTextArea();
        btnUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();
        checkBoxStatus = new javax.swing.JCheckBox();
        labelPreco = new javax.swing.JLabel();
        campoPreco = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Atualização de especialização");
        setToolTipText("Tela de Atualização de especialização");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        labelName.setText("Nome");

        campoNome.setToolTipText("Nome da especialização");

        labelDescription.setText("Descrição");

        campoDescricao.setColumns(20);
        campoDescricao.setRows(5);
        campoDescricao.setToolTipText("Descrição do que representa a especialização");
        jScrollPane1.setViewportView(campoDescricao);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/update-24.png"))); // NOI18N
        btnUpdate.setToolTipText("Atualizar especialização");
        btnUpdate.setBorderPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
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

        labelStatus.setText("Status");

        checkBoxStatus.setText("Disponível?");

        labelPreco.setText("Preço");

        campoPreco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoPrecoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelPreco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkBoxStatus))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelName)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(labelDescription))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStatus)
                    .addComponent(checkBoxStatus)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelPreco)
                        .addComponent(campoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        atualizarProcedimento();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        telaConsultaProcedimento.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        telaConsultaProcedimento.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosing

    private void campoPrecoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPrecoFocusLost
        if(!(campoPreco.getText().isEmpty()) || campoPreco.getText().matches("[a-zA-Z")){
            ajusteFormatoDinheiro(campoPreco);
        }
    }//GEN-LAST:event_campoPrecoFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextArea campoDescricao;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoPreco;
    private javax.swing.JCheckBox checkBoxStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelPreco;
    private javax.swing.JLabel labelStatus;
    // End of variables declaration//GEN-END:variables
}
