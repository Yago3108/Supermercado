package view;

import net.miginfocom.swing.MigLayout; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroUsuarioView extends JPanel {
    private final JTextField campoNome;
    private final JTextField campoCpf;
    private final JCheckBox cbEhAdmin;
    private final JButton btnSalvar;
    private final JButton btnVoltar;

    public CadastroUsuarioView() {
        setLayout(new MigLayout(
            "wrap 2, gap 10 10, align center, center, insets 20", 
        
            "[right]10[::300, grow, fill]")); 

        JLabel lblTitulo = new JLabel("Cadastro de Usuário");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, "span 2, align center, wrap 10");    

        
        add(new JLabel("Nome:"), "right");
        campoNome = new JTextField(20);
        add(campoNome, "growx"); 

        add(new JLabel("CPF:"), "right");
        campoCpf = new JTextField(11);
        add(campoCpf, "growx"); 


        cbEhAdmin = new JCheckBox("É Administrador?");
        add(cbEhAdmin, "skip 1, wrap"); 

        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnSalvar = new JButton("Salvar");
        btnVoltar = new JButton("Voltar");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnVoltar);

        add(painelBotoes, "span 2, align center");
    }

    public String getNome() { return campoNome.getText(); }
    public String getCpf() { return campoCpf.getText(); }
    public void setNome(String nome) {
    	campoNome.setText(nome);
    }
    public void setCPF(String cpf) {
    	campoCpf.setText(cpf);
    }
    public void setAdmin() {
    	cbEhAdmin.setSelected(false);
    }
    public boolean isEhAdmin() { return cbEhAdmin.isSelected(); }

    public void addSalvarListener(ActionListener listener) { btnSalvar.addActionListener(listener); }
    public void addVoltarListener(ActionListener listener) { 
    	btnVoltar.addActionListener(listener); 
        this.setNome("");
        this.setCPF("");
        this.setAdmin();
    }
}