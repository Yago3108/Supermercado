package view;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JPanel {
    private final JTextField campoNome;
    private final JTextField campoCpf;
    private final JButton btnEntrar;
    private final JButton btnCadastrar;

    public LoginView() {
      
  
        setLayout(new MigLayout(
            "wrap 2, align center, center, gap 10 10, insets 50", 
            "[]10[250, fill]"    
        ));

        JLabel lblTitulo = new JLabel("Login");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        add(lblTitulo, "span 2, align center, wrap 20"); 


        add(new JLabel("NOME:"), "right"); 
        campoNome = new JTextField(15);
        add(campoNome, "h 30!"); 
        
  

        add(new JLabel("CPF:"), "right"); 
        campoCpf = new JTextField(15);
        add(campoCpf, "h 30!"); 
        
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnEntrar = new JButton("Entrar");
        btnCadastrar = new JButton("Cadastrar");
        buttonPanel.add(btnEntrar);
        buttonPanel.add(btnCadastrar);
        
        add(buttonPanel, "span 2, align center, gaptop 15");
    }

    public String getNome() {
        return campoNome.getText();
    }
    
    public String getCpf() {
        return campoCpf.getText();
    }
    public void setTfNome(String nome) {
    	campoNome.setText(nome);
    }
    public void setTfCpf(String cpf) {
    	campoCpf.setText(cpf);
    }
    public void addEntrarListener(ActionListener listener) {
        btnEntrar.addActionListener(listener);
    }

    public void addCadastrarListener(ActionListener listener) {
        btnCadastrar.addActionListener(listener);
    }
}