/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.convenio;

import excecao.ServicoException;
import javax.swing.*;
import java.util.List;
import modelo.Convenio;
import servico.ConvenioServico;
import util.CamposTextoUtil;

/**
 *
 * @author weth767
 */
public class TelaCadastroConvenio extends javax.swing.JInternalFrame {

   
    public TelaCadastroConvenio() {
        initComponents();
    }
    
    /*método para salvar um convenio*/
    public void salvarConvenio(){
        /*instancia o convenio e o serviço*/
        Convenio convenio = new Convenio();
        ConvenioServico cs = new ConvenioServico();
        /*verifica se o campo de nome está vazio para obrigar a preencher o nome do convenio*/
        if(campoNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"O nome do convênio deve ser informado");
            return;
        }
        convenio.setNome(campoNome.getText());
        convenio.setDescricao(campoDescricao.getText());
        convenio.setStatus(true);
        try{
            /*salva a convenio*/
            cs.salvar(convenio);
            /*e mostra mensagem de sucesso*/
            JOptionPane.showMessageDialog(this, "Convênio salvo com sucesso","Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            CamposTextoUtil.limpaTodosCampos(rootPane);
            campoDescricao.setText("");
        } catch (ServicoException e) {
            /*caso houver erro, mostra mensagem de erro*/
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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

        labelName = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        labelDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescricao = new javax.swing.JTextArea();
        btSalvaConvenio = new javax.swing.JButton();
        btLimpaCampos = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cadastro de Convênio");
        setToolTipText("Tela de Cadastro de Convênio");

        labelName.setText("Nome");

        campoNome.setToolTipText("Nome do convênio");

        labelDescription.setText("Descrição");

        campoDescricao.setColumns(20);
        campoDescricao.setRows(5);
        campoDescricao.setToolTipText("Descrição sobre o convẽnio");
        jScrollPane1.setViewportView(campoDescricao);

        btSalvaConvenio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add-24.png"))); // NOI18N
        btSalvaConvenio.setToolTipText("Cadastrar um Convênio");
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
                        .addGap(81, 81, 81)
                        .addComponent(btSalvaConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btLimpaCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btCancelar)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalvaConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLimpaCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvaConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvaConvenioActionPerformed
        salvarConvenio();
    }//GEN-LAST:event_btSalvaConvenioActionPerformed

    private void btLimpaCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimpaCamposActionPerformed
        CamposTextoUtil.limpaTodosCampos(rootPane);
        campoDescricao.setText("");
    }//GEN-LAST:event_btLimpaCamposActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btLimpaCampos;
    private javax.swing.JButton btSalvaConvenio;
    private javax.swing.JTextArea campoDescricao;
    private javax.swing.JTextField campoNome;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelName;
    // End of variables declaration//GEN-END:variables
}
