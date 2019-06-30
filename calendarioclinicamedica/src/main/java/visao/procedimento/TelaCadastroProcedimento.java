/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.procedimento;

import visao.procedimento.*;
import excecao.ServicoException;
import javax.swing.*;
import modelo.Procedimento;
import servico.EspecializacaoServico;
import servico.ProcedimentoServico;
import util.CamposTextoUtil;

/**
 *
 * @author weth767
 */
public class TelaCadastroProcedimento extends javax.swing.JInternalFrame {

    
    public TelaCadastroProcedimento() {
        initComponents();
    }
    
    /*método para salvar um procedimento*/
    public void salvarProcedimento(){
        /*instancia o procedimento e o serviço*/
        Procedimento procedimento = new Procedimento();
        ProcedimentoServico ps = new ProcedimentoServico();
        /*verifica se o campo de nome está vazio para obrigar a preencher o nome do procedimento*/
        if(campoNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"O nome do procedimento deve ser informado");
            return;
        }
        procedimento.setNome(campoNome.getText());
       /*verifica se o foi digitado o preço do procedimento, se foi*/
        if(!(campoPreco.getText().isEmpty())) {
            /*pega o valor, retira os itens indesejados dele*/
            float valor = Float.parseFloat(campoPreco.getText().replaceAll("R", "")
                    .replaceAll("\\$", "").replaceAll(" ", "")
                    .replaceAll("\\.", "").replaceAll(",", "."));
            /*e passa para o procedimento*/
            procedimento.setPreco(valor);
        }
        procedimento.setDescricao(campoDescricao.getText());
        procedimento.setStatus(true);
        try{
            /*salva o procedimento*/
            ps.salvar(procedimento);
            /*e mostra mensagem de sucesso*/
            JOptionPane.showMessageDialog(this, "Procedimento salvo com sucesso","Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            CamposTextoUtil.limpaTodosCampos(rootPane);
            campoDescricao.setText("");
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
        btSalvaConvenio = new javax.swing.JButton();
        btLimpaCampos = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        labelPreco = new javax.swing.JLabel();
        campoPreco = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Cadastro de Especialização");
        setToolTipText("Tela de Cadastro de Especialização");

        labelName.setText("Nome");

        campoNome.setToolTipText("Nome da especialização");

        labelDescription.setText("Descrição");

        campoDescricao.setColumns(20);
        campoDescricao.setRows(5);
        campoDescricao.setToolTipText("Descrição sobre a especialização");
        jScrollPane1.setViewportView(campoDescricao);

        btSalvaConvenio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add-24.png"))); // NOI18N
        btSalvaConvenio.setToolTipText("Cadastrar uma especialização");
        btSalvaConvenio.setBorderPainted(false);
        btSalvaConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvaConvenioActionPerformed(evt);
            }
        });

        btLimpaCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/clean-24.png"))); // NOI18N
        btLimpaCampos.setToolTipText("Limpar Campos");
        btLimpaCampos.setBorderPainted(false);
        btLimpaCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimpaCamposActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancel-24.png"))); // NOI18N
        btCancelar.setToolTipText("Cancelar Operação");
        btCancelar.setBorderPainted(false);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelDescription)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btSalvaConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btLimpaCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelPreco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(labelPreco)
                    .addComponent(campoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalvaConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLimpaCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvaConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvaConvenioActionPerformed
        salvarProcedimento();
    }//GEN-LAST:event_btSalvaConvenioActionPerformed

    private void btLimpaCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimpaCamposActionPerformed
        CamposTextoUtil.limpaTodosCampos(rootPane);
        campoDescricao.setText("");
    }//GEN-LAST:event_btLimpaCamposActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void campoPrecoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoPrecoFocusLost
       if(!(campoPreco.getText().isEmpty()) || campoPreco.getText().matches("[a-zA-Z")){
           ajusteFormatoDinheiro(campoPreco);
       }
    }//GEN-LAST:event_campoPrecoFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btLimpaCampos;
    private javax.swing.JButton btSalvaConvenio;
    private javax.swing.JTextArea campoDescricao;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoPreco;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelPreco;
    // End of variables declaration//GEN-END:variables
}
