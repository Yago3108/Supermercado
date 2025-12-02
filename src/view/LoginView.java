package view;

import net.miginfocom.swing.MigLayout; // Certifique-se de ter a biblioteca MigLayout no seu projeto
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JPanel {
    private final JTextField campoNome;
    private final JTextField campoCpf;
    private final JButton btnEntrar;
    private final JButton btnCadastrar;

    public LoginView() {
      
        // Layout Constraints:
        // "wrap 2, align center, gap 10 10": Configura 2 colunas, centraliza o *bloco* do formulário.
        // Column Constraints:
        // "[]": Deixa a primeira coluna (rótulos) do tamanho do seu conteúdo.
        // "[250, fill]": Define a segunda coluna (campos) com largura fixa de 250 pixels e faz os componentes preencherem essa largura.
        // O comando 'grow' foi removido para evitar o esticamento.
        setLayout(new MigLayout(
            "wrap 2, align center, gap 10 10, insets 50", // insets adiciona um espaçamento de segurança
            "[]10[250, fill]" // Coluna 1 (Labels): auto-size; Coluna 2 (Campos): 250px fixo, preenche.
        ));

        JLabel lblTitulo = new JLabel("Login");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        // Adiciona o título ocupando 2 colunas e centralizado
        add(lblTitulo, "span 2, align center, wrap 20"); // wrap 20: adiciona um espaçamento maior após o título


        // Name Field
        add(new JLabel("NOME:"), "right"); // Alinha o rótulo à direita da Coluna 1
        campoNome = new JTextField(15);
        add(campoNome, "h 30!"); // Preenche a Coluna 2 (devido ao [250, fill] acima), com altura fixa.
        
  
        // CPF Field
        add(new JLabel("CPF:"), "right"); // Alinha o rótulo à direita da Coluna 1
        campoCpf = new JTextField(15);
        add(campoCpf, "h 30!"); // Preenche a Coluna 2, com altura fixa.
        
     
        // Painel de Botões - span 2 para ocupar as duas colunas
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnEntrar = new JButton("Entrar");
        btnCadastrar = new JButton("Cadastrar");
        buttonPanel.add(btnEntrar);
        buttonPanel.add(btnCadastrar);
        
        add(buttonPanel, "span 2, align center, gaptop 15"); // Ocupa 2 colunas, centraliza e adiciona um espaçamento no topo.
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