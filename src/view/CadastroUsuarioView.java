package view;

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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblTitulo = new JLabel("Cadastro de Usuário");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        campoNome = new JTextField(20);
        add(campoNome, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("CPF:"), gbc);

        gbc.gridx = 1;
        campoCpf = new JTextField(11);
        add(campoCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        cbEhAdmin = new JCheckBox("É Administrador?");
        add(cbEhAdmin, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        btnSalvar = new JButton("Salvar");
        add(btnSalvar, gbc);

        gbc.gridx = 1;
        btnVoltar = new JButton("Voltar");
        add(btnVoltar, gbc);
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
    public void addVoltarListener(ActionListener listener) { btnVoltar.addActionListener(listener); 
    this.setNome("");
    this.setCPF("");
    this.setAdmin();
    }
}