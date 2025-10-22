package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JPanel {
    private final JTextField campoNome;
    private final JTextField campoCpf;
    private final JButton btnEntrar;
    private final JButton btnCadastrar;

    public LoginView() {
      
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        JLabel lblTitulo = new JLabel("Login");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);


        gbc.gridwidth = 1;

        // Name Field
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST; 
        add(new JLabel("NOME:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        campoNome = new JTextField(15);
        add(campoNome, gbc);
        
  
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("CPF:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        campoCpf = new JTextField(15);
        add(campoCpf, gbc);

     
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnEntrar = new JButton("Entrar");
        btnCadastrar = new JButton("Cadastrar");
        buttonPanel.add(btnEntrar);
        buttonPanel.add(btnCadastrar);
        
        add(buttonPanel, gbc);
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