package visao.secretaria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import excecao.ServicoException;
import modelo.Cidade;
import modelo.Secretaria;
import servico.CidadeServico;
import servico.SecretariaServico;
import util.*;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import static javax.swing.SwingConstants.CENTER;
import modelo.Permissao;
import modelo.Secretaria;

/**
 *
 * @author weth767
 */
public class TelaAtualizacaoSecretaria extends javax.swing.JInternalFrame {
    
    private Secretaria secretaria;
    private TelaConsultaSecretaria telaConsultaSecretaria;
    
    public TelaAtualizacaoSecretaria(TelaConsultaSecretaria telaConsultaSecretaria, Secretaria secretaria) {
        initComponents();
        campoComplemento.setHorizontalAlignment(CENTER);
        comboEstado.setSelectedItem(secretaria.getCidade().getEstado());
        setCidades(comboEstado.getSelectedItem().toString());
        comboCidade.setSelectedItem(secretaria.getCidade().getNome());
        this.secretaria = secretaria;
        this.telaConsultaSecretaria = telaConsultaSecretaria;
        setSecretaria();
    }
    
    /*método para setar os dados da secretária*/
    public void setSecretaria(){
        campoNome.setText(secretaria.getNome());
        campoCPF.setText(secretaria.getCpf());
        campoRG.setText(secretaria.getRg());
        campoBairro.setText(secretaria.getBairro());
        campoCEP.setText(secretaria.getCep());
        campoRua.setText(secretaria.getRua());
        campoNumero.setText(secretaria.getNumero());
        campoComplemento.setText(secretaria.getComplemento());
        campoEmail.setText(secretaria.getEmail());
        campoCelular.setText(secretaria.getCelular());
        checkStatus.setSelected(secretaria.isStatus());
        campoLogin.setText(secretaria.getLogin());
        campoSenha.setText(secretaria.getSenha());
    }
    
    /*método para atualizar a secretaria*/
    public void atualizarSecretaria(){
        /*instancia a secretaria e os services necessários*/
        Secretaria s = secretaria;
        SecretariaServico ps = new SecretariaServico();
        CidadeServico cds = new CidadeServico();
        /*seta os valores para a secretaria*/
        s.setNome(campoNome.getText().trim());
        /*verifica se o CPF é invalido, caso seja, mostra mensagem de erro e sai do método*/
        if(ValidacaoCPF.validaCPF(campoCPF.getText()) == false){
            JOptionPane.showMessageDialog(this, "CPF inválido","Erro",JOptionPane.ERROR_MESSAGE);
            campoCPF.setText("");
            return;
        }
        /*caso não seja, pega o cpf*/
        s.setCpf(campoCPF.getText().replaceAll("\\.", "").replaceAll("-", ""));
        /*verifica se o RG não veio vazio*/
        if(campoRG.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "RG inválido","Erro",JOptionPane.ERROR_MESSAGE);
            campoRG.setText("");
            return;
        }
        s.setRg(campoRG.getText());
        /*busca a cidade pelo seu nome e estado*/
        try {
            Cidade c = cds.buscaPorNomeEstado(comboCidade.getSelectedItem().toString(),
                    comboEstado.getSelectedItem().toString());
            s.setCidade(c);
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
        s.setCep(campoCEP.getText().replaceAll("-", "").trim());
        /*coloca o bairro, rua e outros dados referentes ao endereço*/
        s.setBairro(campoBairro.getText());
        s.setRua(campoRua.getText());
        s.setNumero(campoNumero.getText());
        s.setComplemento(campoComplemento.getText());
        /*verifica se recebeu um email valido*/
        if(ValidacaoEmail.validaEmail(campoEmail.getText()) == false){
            JOptionPane.showMessageDialog(this, "E-mail inválido","Erro",JOptionPane.ERROR_MESSAGE);
            campoEmail.setText("");
            return;
        }
        /*pega o número de celular*/
        if(campoCelular.getText().isEmpty() || campoCelular.getText().length() < 11){
            JOptionPane.showMessageDialog(this, "Número de celular inválido","Erro",JOptionPane.ERROR_MESSAGE);
            campoEmail.setText("");
            return;
        }
        s.setCelular(campoCelular.getText().replaceAll("\\.", "").replaceAll("-", "")
        .replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", ""));
          /*pega o login da secretária*/
        if(campoLogin.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "login da secretária deve ser fornecido","Erro",JOptionPane.ERROR_MESSAGE);
            campoLogin.setText("");
            return;
        }
        s.setLogin(campoLogin.getText());
        /*pega a senha da secretaria*/
        String senha = new String(campoSenha.getPassword());
        String erro = ValidacaoSenha.validaForcaSenha(senha);
        if(erro != null){
            JOptionPane.showMessageDialog(this, erro,"Erro",JOptionPane.ERROR_MESSAGE);
            campoSenha.setText("");
            return;
        }
        s.setSenha(senha); 
        /*pega a permissão*/
        s.setPermissao(Permissao.PACIENTE);
        /*e coloca o status como true já que inicialmente está habilitado*/
        s.setStatus(checkStatus.isSelected());
        try {
            /*atualiza uma secretaria*/
            ps.atualizar(s);
            /*mostra mensagem de sucesso*/
            JOptionPane.showMessageDialog(this,"Secretária atualizada com sucesso",
                    "Sucesso",JOptionPane.INFORMATION_MESSAGE);
            /*se houve erro*/
        } catch (ServicoException e) {
            /*mostra mensagem de erro*/
            JOptionPane.showMessageDialog(this,e.getMessage(),
                    "Erro",JOptionPane.WARNING_MESSAGE);
        }
    }
    /*método para setar os campos de rua e bairro pelo cep da secretária*/
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
        btAtualizaPaciente = new javax.swing.JButton();
        btCancela = new javax.swing.JButton();
        btBuscaCep = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        checkStatus = new javax.swing.JCheckBox();
        campoLogin = new javax.swing.JTextField();
        labelLogin = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        campoSenha = new javax.swing.JPasswordField();

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

        setClosable(true);
        setTitle("Atualização de Secretária");
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

        labelNome.setText("Nome");

        campoNome.setToolTipText("Nome da secretária");

        labelCpf.setText("CPF");

        try {
            campoCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCPF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoCPF.setToolTipText("CPF da secretária");

        labelRg.setText("RG");

        campoRG.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoRG.setToolTipText("RG da secretária");

        labelCidade.setText("Cidade");

        comboCidade.setToolTipText("Cidade onde se localiza a moradia do paciente");

        labelEstado.setText("Estado");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins" }));
        comboEstado.setSelectedIndex(12);
        comboEstado.setToolTipText("Estado onde se localiza a moradia do paciente");
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

        campoBairro.setToolTipText("Bairro onde se localiza a moradia da secretária");

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

        btAtualizaPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/update-24.png"))); // NOI18N
        btAtualizaPaciente.setToolTipText("Atualizar paciente");
        btAtualizaPaciente.setBorderPainted(false);
        btAtualizaPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizaPacienteActionPerformed(evt);
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

        jLabel1.setText("Status");

        checkStatus.setText("Status");

        labelLogin.setText("Login");

        labelSenha.setText("Senha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(labelNome)
                                    .addGap(28, 28, 28))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(labelCidade)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelRg)
                                    .addComponent(labelCep))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(btBuscaCep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelRua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoRua, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelCpf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(249, 249, 249)
                                        .addComponent(labelEstado))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(258, 258, 258)
                                        .addComponent(labelBairro)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(campoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(labelCelular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelEmail)
                                            .addComponent(labelLogin))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(26, 26, 26)
                                        .addComponent(checkStatus))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelNumero)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(194, 194, 194)
                                                .addComponent(labelComplemento))
                                            .addComponent(campoRG, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(labelSenha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(campoComplemento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btAtualizaPaciente)
                .addGap(18, 18, 18)
                .addComponent(btCancela)
                .addGap(278, 278, 278))
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
                    .addComponent(labelRg)
                    .addComponent(labelEstado)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCelular)
                    .addComponent(campoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLogin)
                    .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(checkStatus))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancela, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAtualizaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAtualizaPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizaPacienteActionPerformed
        atualizarSecretaria();
    }//GEN-LAST:event_btAtualizaPacienteActionPerformed

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

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        telaConsultaSecretaria.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizaPaciente;
    private javax.swing.JButton btBuscaCep;
    private javax.swing.JButton btCancela;
    private javax.swing.JTextField campoBairro;
    private javax.swing.JFormattedTextField campoCEP;
    private javax.swing.JFormattedTextField campoCPF;
    private javax.swing.JFormattedTextField campoCelular;
    private javax.swing.JTextField campoComplemento;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoLogin;
    private javax.swing.JTextField campoNome;
    private javax.swing.JFormattedTextField campoNumero;
    private javax.swing.JTextField campoRG;
    private javax.swing.JTextField campoRua;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JCheckBox checkStatus;
    private javax.swing.JComboBox<String> comboCidade;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCelular;
    private javax.swing.JLabel labelCep;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelCpf;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelRg;
    private javax.swing.JLabel labelRua;
    private javax.swing.JLabel labelSenha;
    // End of variables declaration//GEN-END:variables
}
