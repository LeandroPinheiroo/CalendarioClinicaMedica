/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.prontuario;

import excecao.ServicoException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Medico;
import modelo.Procedimento;
import modelo.Prontuario;
import servico.PacienteServico;
import servico.ProntuarioServico;
import visao.TelaPrincipal;

/**
 *
 * @author weth767
 */
public class TelaConsultaProntuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaConsultaProntuario
     */
    private DefaultTableModel model;
    private TelaPrincipal telaPrincipal;

    public TelaConsultaProntuario(TelaPrincipal telaPrincipal) {
        initComponents();
        model = (DefaultTableModel) tabelaProntuarios.getModel();
        this.telaPrincipal = telaPrincipal;
    }

    /*método para setar os dados de prontuario na tabela*/
    public void setDadosTabela() {
        /*zera as linhas da tabela, para não acumular*/
        model.setRowCount(0);
        /*instancia o serviço*/
        ProntuarioServico ps = new ProntuarioServico();
        Prontuario prontuario = null;
        List<Prontuario> prontuarios = null;
        /*verifica os filtros*/
        if (comboBusca.getSelectedItem().toString().equals("Sem Filtragem")) {
            prontuarios = ps.buscaTodos();
        } else if (comboBusca.getSelectedItem().toString().equals("ID")) {
            /*verifica se não recebeu um id inválido*/
            if (campoFiltro.getText().matches("\\d+")) {
                /*senão recebeu, busca o prontuario*/
                try {
                    prontuario = ps.buscaPorID(Integer.parseInt(campoFiltro.getText()));
                } catch (ServicoException e) {
                    /*se houver erro, mostra mensagem na tela*/
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } /*busca o procedimento pelo cpf do paciente*/ else if (comboBusca.getSelectedItem().toString().equals("CPF Paciente")) {
            try {
                /*busca pelo cpf do paciente*/
                prontuario = ps.buscaPeloPaciente(new PacienteServico().buscaPeloCPF(campoFiltro.getText()
                        .replaceAll("-", "").replaceAll("\\.", "")));
                /*caso der erro, mostra erro*/
            } catch (ServicoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        /*verifica qual a foi preenchido, se foi a lista ou apenas um valor*/
        if (prontuario != null) {
            /*seta a linha na tabela*/
            Object dados[] = {prontuario.getId(), prontuario.getPaciente().getNome(),
                retornaNomesProcedimentos(prontuario.getProcedimentos()),
                retornaNomesMedicos(prontuario.getMedicos())};
            model.addRow(dados);
        } /*caso foi linhas*/ 
        else if (prontuarios != null) {
            for (Prontuario p : prontuarios) {
                /*seta as linhas na tabela*/
                Object dados[] = {p.getId(), p.getPaciente().getNome(),
                    retornaNomesProcedimentos(p.getProcedimentos()),
                    retornaNomesMedicos(p.getMedicos())};
                model.addRow(dados);
            }
        }
    }
      
    /*metodo para retorna a lista de medicos com somente os nomes dos medicos*/
    public List<String> retornaNomesMedicos(List<Medico> medicos) {
        /*cria a lista*/
        List<String> nomes = new ArrayList();
        if(medicos == null){
            return nomes;
        }
        /*pega os medicos e vai pondo na lista*/
        for (Medico m : medicos) {
            nomes.add(m.getNome());
        }
        /*e depois retorna*/
        return nomes;
    }

    /*metodo para retorna a lista de medicos com somente os nomes dos medicos*/
    public List<String> retornaNomesProcedimentos(List<Procedimento> procedimentos) {
        /*cria a lista*/
        List<String> nomes = new ArrayList();
        if(procedimentos == null){
            return nomes;
        }
        /*pega os medicos e vai pondo na lista*/
        for (Procedimento p : procedimentos) {
            nomes.add(p.getNome());
        }
        /*e depois retorna*/
        return nomes;
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
        tabelaProntuarios = new javax.swing.JTable();
        labelFiltro = new javax.swing.JLabel();
        campoFiltro = new javax.swing.JTextField();
        comboBusca = new javax.swing.JComboBox<>();
        btBusca = new javax.swing.JButton();

        setClosable(true);
        setTitle("Consulta de Prontuários");

        tabelaProntuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Paciente", "Procedimentos", "Médicos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProntuarios.setToolTipText("Tabela de Convênios");
        tabelaProntuarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaProntuarios.setSurrendersFocusOnKeystroke(true);
        tabelaProntuarios.getTableHeader().setResizingAllowed(false);
        tabelaProntuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaProntuarios);
        if (tabelaProntuarios.getColumnModel().getColumnCount() > 0) {
            tabelaProntuarios.getColumnModel().getColumn(0).setResizable(false);
            tabelaProntuarios.getColumnModel().getColumn(0).setPreferredWidth(90);
            tabelaProntuarios.getColumnModel().getColumn(1).setResizable(false);
            tabelaProntuarios.getColumnModel().getColumn(1).setPreferredWidth(250);
            tabelaProntuarios.getColumnModel().getColumn(2).setResizable(false);
            tabelaProntuarios.getColumnModel().getColumn(2).setPreferredWidth(1000);
            tabelaProntuarios.getColumnModel().getColumn(3).setResizable(false);
            tabelaProntuarios.getColumnModel().getColumn(3).setPreferredWidth(1000);
        }

        labelFiltro.setText("Filtro:");

        campoFiltro.setToolTipText("Informação de filtragem do Convênio");

        comboBusca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sem Filtragem", "ID", "CPF Paciente" }));
        comboBusca.setToolTipText("Filtro de consultas de Convênio");

        btBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search-24.png"))); // NOI18N
        btBusca.setToolTipText("Consulta de categorias");
        btBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(labelFiltro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btBusca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFiltro)
                    .addComponent(campoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscaActionPerformed
        setDadosTabela();
    }//GEN-LAST:event_btBuscaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBusca;
    private javax.swing.JTextField campoFiltro;
    private javax.swing.JComboBox<String> comboBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFiltro;
    private javax.swing.JTable tabelaProntuarios;
    // End of variables declaration//GEN-END:variables
}
