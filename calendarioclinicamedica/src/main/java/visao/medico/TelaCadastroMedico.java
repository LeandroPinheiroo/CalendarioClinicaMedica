package visao.medico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import excecao.ServicoException;
import modelo.Cidade;
import servico.CidadeServico;
import servico.MedicoServico;
import util.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.SwingConstants.CENTER;
import modelo.Especializacao;
import modelo.Medico;
import modelo.Permissao;
import servico.EspecializacaoServico;
import servico.MedicoServico;

/**
 *
 * @author weth767
 */
public class TelaCadastroMedico extends javax.swing.JInternalFrame {
    
    public TelaCadastroMedico() {
        initComponents();
        campoComplemento.setHorizontalAlignment(CENTER);
        setCidades(comboEstado.getSelectedItem().toString());
        comboCidade.setSelectedIndex(290);
        setEspecializacoes();
    }
    /*método para salvar o medico*/
    public void salvarMedico(){
        /*instancia o medico e os services necessários*/
        Medico m = new Medico();
        MedicoServico ms = new MedicoServico();
        CidadeServico cds = new CidadeServico();
        /*seta os valores para o medico*/
        m.setNome(campoNome.getText().trim());
        /*verifica se o CPF é invalido, caso seja, mostra mensagem de erro e sai do método*/
        if(ValidacaoCPF.validaCPF(campoCPF.getText()) == false){
            JOptionPane.showMessageDialog(this, "CPF inválido","Erro",JOptionPane.ERROR_MESSAGE);
            campoCPF.setText("");
            return;
        }
        /*caso não seja, pega o cpf*/
        m.setCpf(campoCPF.getText().replaceAll("\\.", "").replaceAll("-", ""));
        /*verifica se o RG não veio vazio*/
        if(campoRG.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "RG inválido","Erro",JOptionPane.ERROR_MESSAGE);
            campoRG.setText("");
            return;
        }
        m.setRg(campoRG.getText());
        /*busca a cidade pelo seu nome e estado*/
        try {
            Cidade c = cds.buscaPorNomeEstado(comboCidade.getSelectedItem().toString(),
                    comboEstado.getSelectedItem().toString());
            m.setCidade(c);
        } catch (ServicoException ex) {
            JOptionPane.showMessageDialog(this, "Erro em realizar a busca","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        }
        /*verifica se o cep não veio vazio*/
        if(campoCEP.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "CEP inválido","Erro",JOptionPane.ERROR_MESSAGE);
            campoCEP.setText("");
            return;
        }
        m.setCep(campoCEP.getText().replaceAll("-", "").trim());
        /*coloca o bairro, rua e outros dados referentes ao endereço*/
        m.setBairro(campoBairro.getText());
        m.setRua(campoRua.getText());
        m.setNumero(campoNumero.getText());
        m.setComplemento(campoComplemento.getText());
        /*verifica se recebeu um email valido*/
        if(ValidacaoEmail.validaEmail(campoEmail.getText()) == false){
            JOptionPane.showMessageDialog(this, "E-mail inválido","Erro",JOptionPane.ERROR_MESSAGE);
            campoEmail.setText("");
            return;
        }
        m.setEmail(campoEmail.getText());
        /*pega o número de celular*/
        if(campoCelular.getText().isEmpty() || campoCelular.getText().length() < 11){
            JOptionPane.showMessageDialog(this, "Número de celular inválido","Erro",JOptionPane.ERROR_MESSAGE);
            campoEmail.setText("");
            return;
        }
        m.setCelular(campoCelular.getText().replaceAll("\\.", "").replaceAll("-", "")
        .replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", ""));
        /*pega a permissão*/
        m.setPermissao(Permissao.MEDICO);
        /*pega o crm do médico*/
        if(campoCRM.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "CRM do médico deve ser fornecido","Erro",JOptionPane.ERROR_MESSAGE);
            campoCRM.setText("");
            return;
        }
        m.setCrm(campoCRM.getText());
        /*pega o login do médico*/
        if(campoLogin.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "login do médico deve ser fornecido","Erro",JOptionPane.ERROR_MESSAGE);
            campoLogin.setText("");
            return;
        }
        m.setLogin(campoLogin.getText());
        /*pega a senha do médico*/
        String senha = new String(campoSenha.getPassword());
        String erro = ValidacaoSenha.validaForcaSenha(senha);
        if(erro != null){
            JOptionPane.showMessageDialog(this, erro,"Erro",JOptionPane.ERROR_MESSAGE);
            campoSenha.setText("");
            return;
        }
        m.setSenha(senha); 
        /*pega as especializações selecionadas*/
        List<Especializacao> especializacoes = new ArrayList();
        for(int i = 0; i < listaEspecializacoes.getSelectedValuesList().size(); i++){
            try {
                especializacoes.add(new EspecializacaoServico()
                        .buscaPeloNome(listaEspecializacoes.getSelectedValuesList().get(i)));
            } catch (ServicoException ex) {
                System.out.println(ex.getMessage());
            }
        }
        m.setEspecializacoes(especializacoes);
        /*pega a data atual como data de cadastro*/
        m.setDataCadastro(new java.util.Date());
        /*e coloca o status como true já que inicialmente está habilitado*/
        m.setStatus(true);
        try {
            /*salva um paciente*/
            ms.salvar(m);
            /*mostra mensagem de sucesso*/
            JOptionPane.showMessageDialog(this,"Médico salvo com sucesso",
                    "Sucesso",JOptionPane.INFORMATION_MESSAGE);
            CamposTextoUtil.limpaTodosCampos(rootPane);
            /*se houve erro*/
        } catch (ServicoException e) {
            /*mostra mensagem de erro*/
            JOptionPane.showMessageDialog(this,e.getMessage(),
                    "Erro",JOptionPane.WARNING_MESSAGE);
        }
    }
    /*método para setar os campos de rua e bairro pelo cep do cliente*/
    public void setEndereco(String cep){
        try {
            /*seta a rua e o bairro de acordo com o cep*/
            campoRua.setText(BuscaEndereco.getStreet(cep));
            campoBairro.setText(BuscaEndereco.getNeighborhood(cep));
        } catch (IOException e) {
            /*senão mostra erro*/
            JOptionPane.showMessageDialog(this,e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*método para setar as especializações na lista*/
    public void setEspecializacoes(){
        DefaultListModel modelo = new DefaultListModel();
        /*busca todas as especializações*/
        List<Especializacao> especializacoes = new EspecializacaoServico().buscaTodos();
        /*adiciona na lista*/
        for(Especializacao e: especializacoes){
            modelo.addElement(e.getNome());
        }
        listaEspecializacoes.setModel(modelo);
    }
    
    /*método para setar todas as cidades daquele estado no combo de cidades*/
    public void setCidades(String estado){
        /*remove os itens já existentes no combo*/
        comboCidade.removeAllItems();
        /*utiliza o service da cidade*/
        CidadeServico cs = new CidadeServico();
        try {
            /*busca as cidades pelo estado*/
            List<Cidade> cidades = cs.buscaPorEstado(estado);
            /*e vai jogando no combo*/
            for(Cidade c : cidades){
                comboCidade.addItem(c.getNome());
            }
            /*se houver erro*/
        } catch (ServicoException e) {
            /*mostra mensagem de erro*/
            JOptionPane.showMessageDialog(this,e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        labelNome = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        labelCpf = new javax.swing.JLabel();
        campoCPF = new javax.swing.JFormattedTextField();
        labelRg = new javax.swing.JLabel();
        campoRG = new javax.swing.JTextField();
        labelCidade = new javax.swing.JLabel();
        comboCidade = new javax.swing.JComboBox<>();
        labelEstado = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        labelRua = new javax.swing.JLabel();
        campoRua = new javax.swing.JTextField();
        labelNumero = new javax.swing.JLabel();
        campoNumero = new javax.swing.JFormattedTextField();
        labelCep = new javax.swing.JLabel();
        campoCEP = new javax.swing.JFormattedTextField();
        labelBairro = new javax.swing.JLabel();
        campoBairro = new javax.swing.JTextField();
        labelComplemento = new javax.swing.JLabel();
        campoComplemento = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        campoEmail = new javax.swing.JTextField();
        labelCelular = new javax.swing.JLabel();
        campoCelular = new javax.swing.JFormattedTextField();
        btSalvaPaciente = new javax.swing.JButton();
        btLimpaCampos = new javax.swing.JButton();
        btCancela = new javax.swing.JButton();
        btBuscaCep = new javax.swing.JButton();
        labelCRM = new javax.swing.JLabel();
        campoCRM = new javax.swing.JTextField();
        labelLogin = new javax.swing.JLabel();
        campoLogin = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        campoSenha = new javax.swing.JPasswordField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaEspecializacoes = new javax.swing.JList<>();
        labelEspecializacoes = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel2.setText("jLabel2");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setClosable(true);
        setTitle("Cadastro de Médico");

        labelNome.setText("Nome");

        campoNome.setToolTipText("Nome do paciente");

        labelCpf.setText("CPF");

        try {
            campoCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCPF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoCPF.setToolTipText("CPF do paciente");

        labelRg.setText("RG");

        campoRG.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoRG.setToolTipText("RG do paciente");

        labelCidade.setText("Cidade");

        comboCidade.setToolTipText("Cidade onde se localiza a moradia do paciente");

        labelEstado.setText("Estado");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins" }));
        comboEstado.setSelectedIndex(12);
        comboEstado.setToolTipText("Estado onde se localiza a moradia do cliente");
        comboEstado.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboEstadoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        labelRua.setText("Rua");

        labelNumero.setText("Número");

        campoNumero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("######"))));
        campoNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoNumero.setToolTipText("Número da casa do paciente");
        campoNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNumeroKeyTyped(evt);
            }
        });

        labelCep.setText("CEP");

        try {
            campoCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCEP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoCEP.setToolTipText("CEP onde se localiza a moradia do paciente");

        labelBairro.setText("Bairro");

        campoBairro.setToolTipText("Bairro onde se localiza a moradia do cliente");

        labelComplemento.setText("Complemento");

        campoComplemento.setToolTipText("Complemento da casa do paciente");

        labelEmail.setText("E-mail");

        campoEmail.setToolTipText("E-mail do paciente");

        labelCelular.setText("Celular");

        try {
            campoCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) # ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCelular.setToolTipText("Celular do paciente");

        btSalvaPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add-24.png"))); // NOI18N
        btSalvaPaciente.setToolTipText("Cadastrar Paciente");
        btSalvaPaciente.setBorderPainted(false);
        btSalvaPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvaPacienteActionPerformed(evt);
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

        btCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancel-24.png"))); // NOI18N
        btCancela.setToolTipText("Cancelar Operação");
        btCancela.setBorderPainted(false);
        btCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelaActionPerformed(evt);
            }
        });

        btBuscaCep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search-24.png"))); // NOI18N
        btBuscaCep.setToolTipText("Busca dados de endereço pelo CEP");
        btBuscaCep.setBorderPainted(false);
        btBuscaCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscaCepActionPerformed(evt);
            }
        });

        labelCRM.setText("CRM");

        labelLogin.setText("Login");

        labelSenha.setText("Senha");

        jScrollPane2.setViewportView(listaEspecializacoes);

        labelEspecializacoes.setText("Especializações");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(btSalvaPaciente)
                                .addGap(18, 18, 18)
                                .addComponent(btLimpaCampos)
                                .addGap(18, 18, 18)
                                .addComponent(btCancela))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelSenha)
                                .addGap(41, 41, 41)
                                .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelEspecializacoes)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCep)
                            .addComponent(labelNumero)
                            .addComponent(labelEmail)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelRg)
                                        .addComponent(labelNome))
                                    .addGap(5, 5, 5))
                                .addComponent(labelCidade, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(labelCRM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelCelular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelCpf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelBairro)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(campoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(campoRG, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(campoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btBuscaCep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelRua)
                                .addGap(18, 18, 18)
                                .addComponent(campoRua, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelComplemento))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(campoCRM, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(campoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelLogin)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpf)
                    .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEstado)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCidade)
                    .addComponent(labelBairro)
                    .addComponent(campoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCep))
                    .addComponent(btBuscaCep, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelRua)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumero)
                    .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelComplemento)
                    .addComponent(campoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(labelCelular)
                    .addComponent(campoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCRM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCRM)
                    .addComponent(labelLogin)
                    .addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSenha)
                        .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelEspecializacoes))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancela, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btSalvaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btLimpaCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvaPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvaPacienteActionPerformed
        salvarMedico();
    }//GEN-LAST:event_btSalvaPacienteActionPerformed

    private void btLimpaCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimpaCamposActionPerformed
        CamposTextoUtil.limpaTodosCampos(rootPane);
    }//GEN-LAST:event_btLimpaCamposActionPerformed

    private void btCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelaActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelaActionPerformed

    private void btBuscaCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscaCepActionPerformed
        if(!(campoCEP.getText().isEmpty())){
            setEndereco(campoCEP.getText());
        }
    }//GEN-LAST:event_btBuscaCepActionPerformed

    private void comboEstadoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboEstadoPopupMenuWillBecomeInvisible
        setCidades(comboEstado.getSelectedItem().toString());
    }//GEN-LAST:event_comboEstadoPopupMenuWillBecomeInvisible

    private void campoNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNumeroKeyTyped
        if(!(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')){
            evt.consume();
        }
    }//GEN-LAST:event_campoNumeroKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscaCep;
    private javax.swing.JButton btCancela;
    private javax.swing.JButton btLimpaCampos;
    private javax.swing.JButton btSalvaPaciente;
    private javax.swing.JTextField campoBairro;
    private javax.swing.JFormattedTextField campoCEP;
    private javax.swing.JFormattedTextField campoCPF;
    private javax.swing.JTextField campoCRM;
    private javax.swing.JFormattedTextField campoCelular;
    private javax.swing.JTextField campoComplemento;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoLogin;
    private javax.swing.JTextField campoNome;
    private javax.swing.JFormattedTextField campoNumero;
    private javax.swing.JTextField campoRG;
    private javax.swing.JTextField campoRua;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JComboBox<String> comboCidade;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCRM;
    private javax.swing.JLabel labelCelular;
    private javax.swing.JLabel labelCep;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelCpf;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEspecializacoes;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelRg;
    private javax.swing.JLabel labelRua;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JList<String> listaEspecializacoes;
    // End of variables declaration//GEN-END:variables
}
