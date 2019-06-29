/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.agendamento;

import visao.agendamento.TelaAgendamentoDia;
import java.awt.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import visao.TelaPrincipal;

/**
 *
 * @author weth767
 */
public class TelaAgendamento extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaAgendamento
     */
    private Map<String,String> meses = new HashMap();
    private Map<String,String> numeroMeses = new HashMap();
    private DefaultTableModel modelo;
    private Calendar calendario = new GregorianCalendar();
    private TelaPrincipal telaPrincipal;

    public TelaAgendamento(TelaPrincipal telaPrincipal) {
        initComponents();
        this.telaPrincipal = telaPrincipal;
        modelo = (DefaultTableModel) tabelaCalendario.getModel();
        tabelaCalendario.setRowHeight(80);
        tabelaCalendario.setShowGrid(true);
        tabelaCalendario.setShowHorizontalLines(true);
        tabelaCalendario.setShowVerticalLines(true);
        tabelaCalendario.setRowSelectionAllowed(false);
        tabelaCalendario.setColumnSelectionAllowed(false);
        geraMapMeses();
        geraDatas();
        //procuraDiaAtual();
        tabelaCalendario.setSelectionBackground(Color.YELLOW);
        tabelaCalendario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /*pega a data, concatena o dia, com o mes e ano*/
                String data = modelo.getValueAt(tabelaCalendario.getSelectedRow(), 
                        tabelaCalendario.getSelectedColumn()).toString();
                data += "/";
                String mesAno[] = labelMes.getText().split("/");
                data += numeroMeses.get(mesAno[0]);
                data += "/";
                data += mesAno[1];
                /*e passa para a tela de agendamento*/
                TelaAgendamentoDia tad = new TelaAgendamentoDia(telaPrincipal,true,data);
                tad.setVisible(true);
                add(tad);
            }
        });
    }
    
    /*método para buscar as coordenadas do dia atual e deixar a columa selecionada*/
    public void procuraDiaAtual(){
        /*dois for's para percorrer as linhas e as colunas*/
        for(int i = 0; i < tabelaCalendario.getRowCount(); i++){
            for(int j = 0; j < tabelaCalendario.getColumnCount(); j++){
                /*quando encontrar o dia, seta como selecionado*/
                if(modelo.getValueAt(i, j) != null){
                    if(modelo.getValueAt(i, j).toString().equals(Integer.toString(calendario.getTime().getDay()))){
                        tabelaCalendario.changeSelection(i, j, false, false);
                        tabelaCalendario.setRowSelectionInterval(i, i);
                        tabelaCalendario.setColumnSelectionInterval(j, j);
                    }
                }
            }
        }
    }
    
    /*método para cria o mapa com os meses do ano em ingles como chaves
    e os meses em portugues como objeto da chave*/
    public void geraMapMeses(){
        meses.put("January", "Janeiro");
        meses.put("February", "Fevereiro");
        meses.put("March", "Março");
        meses.put("April", "Abril");
        meses.put("May", "Maio");
        meses.put("June", "Junho");
        meses.put("July", "Julho");
        meses.put("August", "Agosto");
        meses.put("September", "Setembro");
        meses.put("October", "Outubro");
        meses.put("November", "Novembro");
        meses.put("December", "Dezembro");
        /*gera o mapa de meses para número também*/
        numeroMeses.put("Janeiro", "01");
        numeroMeses.put("Fevereiro", "02");
        numeroMeses.put("Março", "03");
        numeroMeses.put("Abril", "04");
        numeroMeses.put("Maio", "05");
        numeroMeses.put("Junho", "06");
        numeroMeses.put("Julho", "07");
        numeroMeses.put("Agosto", "08");
        numeroMeses.put("Setembro", "09");
        numeroMeses.put("Outubro", "10");
        numeroMeses.put("Novembro", "11");
        numeroMeses.put("Dezembro", "12");
    }
    
    /*método para gerar as datas e atualiza-las quando necessário*/
    public void geraDatas() {
        calendario.set(Calendar.DAY_OF_MONTH, 1);
        /*pega o nome do mes atual*/
        String mes = meses.get(calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
        /*pega o ano*/
        int ano = calendario.get(Calendar.YEAR);
        /*seta a informação no label*/
        labelMes.setText(mes + "/" + ano);
        /*pega o dia inicial*/
        int diaInicial = calendario.get(Calendar.DAY_OF_WEEK);
        /*o número de dias do mes*/
        int numeroDias = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        /*e a quantidade de semans*/
        int semanas = calendario.getActualMaximum(Calendar.WEEK_OF_MONTH);
        /*zera a quantidade de linhas para não influenciar*/
        modelo.setRowCount(0);
        /*e seta a quantidade de semanas como quantidade de linhas*/
        modelo.setRowCount(semanas);
        /*for que insere os dias na tabela*/
        int i = diaInicial - 1;
        for (int dia = 1; dia <= numeroDias; dia++) {
            modelo.setValueAt(dia, i / 7, i % 7);
            i = i + 1;
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
        tabelaCalendario = new javax.swing.JTable();
        labelMes = new javax.swing.JLabel();
        botaoMesProximo = new javax.swing.JButton();
        botaoMesAnterior = new javax.swing.JButton();

        setClosable(true);

        tabelaCalendario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCalendario.setGridColor(new java.awt.Color(13, 10, 1));
        jScrollPane1.setViewportView(tabelaCalendario);
        if (tabelaCalendario.getColumnModel().getColumnCount() > 0) {
            tabelaCalendario.getColumnModel().getColumn(0).setResizable(false);
            tabelaCalendario.getColumnModel().getColumn(1).setResizable(false);
            tabelaCalendario.getColumnModel().getColumn(2).setResizable(false);
            tabelaCalendario.getColumnModel().getColumn(3).setResizable(false);
            tabelaCalendario.getColumnModel().getColumn(4).setResizable(false);
            tabelaCalendario.getColumnModel().getColumn(5).setResizable(false);
            tabelaCalendario.getColumnModel().getColumn(6).setResizable(false);
        }

        labelMes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        labelMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        botaoMesProximo.setText("Próximo Mês");
        botaoMesProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMesProximoActionPerformed(evt);
            }
        });

        botaoMesAnterior.setText("Mês Anterior");
        botaoMesAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMesAnteriorActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoMesAnterior)
                        .addGap(178, 178, 178)
                        .addComponent(labelMes, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                        .addComponent(botaoMesProximo)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoMesProximo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoMesAnterior)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoMesAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMesAnteriorActionPerformed
        calendario.add(calendario.MONTH, -1);
        geraDatas();
    }//GEN-LAST:event_botaoMesAnteriorActionPerformed

    private void botaoMesProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMesProximoActionPerformed
        calendario.add(calendario.MONTH, +1);
        geraDatas();
    }//GEN-LAST:event_botaoMesProximoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoMesAnterior;
    private javax.swing.JButton botaoMesProximo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelMes;
    private javax.swing.JTable tabelaCalendario;
    // End of variables declaration//GEN-END:variables
}
