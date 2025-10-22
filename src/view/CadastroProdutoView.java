package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroProdutoView extends JPanel {
    private final JTextField campoNome;
    private final JTextField campoPreco;
    private final JTextField campoEstoque;
    private final JButton btnCadastrar;
    private final JButton btnEditar;
    private final JButton btnRemover;
    private final JButton btnLimpar;
    private final JButton btnDeslogar;
    private final JTable tabelaProdutos;
    private final DefaultTableModel modeloTabela;

    public CadastroProdutoView() {
        setLayout(new BorderLayout(10, 10));

        // Formulário de cadastro
        JPanel painelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Produto"));

        painelFormulario.add(new JLabel("Nome:"));
        campoNome = new JTextField(20);
        painelFormulario.add(campoNome);

        painelFormulario.add(new JLabel("Preço:"));
        campoPreco = new JTextField(20);
        painelFormulario.add(campoPreco);
        
        painelFormulario.add(new JLabel("Estoque:"));
        campoEstoque = new JTextField(20);
        painelFormulario.add(campoEstoque);

        // Tabela de produtos
        String[] colunas = {"ID", "Nome", "Preço", "Estoque"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaProdutos = new JTable(modeloTabela);
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);

        // Botões de ação
        JPanel painelBotoes = new JPanel(new GridLayout(1, 5, 5, 5));
        btnCadastrar = new JButton("Cadastrar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        btnLimpar = new JButton("Limpar");
        btnDeslogar = new JButton("Deslogar");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnDeslogar);
        
        // Adiciona os painéis à view principal
        add(painelFormulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Listener para preencher os campos ao clicar na tabela
        tabelaProdutos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tabelaProdutos.getSelectedRow() != -1) {
                    int linha = tabelaProdutos.getSelectedRow();
                    campoNome.setText(modeloTabela.getValueAt(linha, 1).toString());
                    campoPreco.setText(modeloTabela.getValueAt(linha, 2).toString());
                    campoEstoque.setText(modeloTabela.getValueAt(linha, 3).toString());
                }
            }
        });
    }

    // Getters para os campos e botões
    public String getNome() { return campoNome.getText(); }
    public String getPreco() { return campoPreco.getText(); }
    public String getEstoque() { return campoEstoque.getText(); }
    public JTable getTabelaProdutos() { return tabelaProdutos; }
    public DefaultTableModel getModeloTabela() { return modeloTabela; }
    public JButton getBtnCadastrar() { return btnCadastrar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnRemover() { return btnRemover; }
    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnDeslogar() { return btnDeslogar; }
}