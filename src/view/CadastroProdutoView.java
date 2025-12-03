package view;

import net.miginfocom.swing.MigLayout; // Certifique-se de ter a biblioteca MigLayout no seu projeto
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
       
        setLayout(new MigLayout("insets 20, center", "[grow, center]", "[][grow, fill][]")); 

        JPanel painelFormulario = new JPanel(new MigLayout("wrap 2, fillx", "[right]10[grow, fill]", ""));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Produto"));

        painelFormulario.add(new JLabel("Nome:"), "right");
        campoNome = new JTextField(20);
        painelFormulario.add(campoNome, "growx");

        painelFormulario.add(new JLabel("Preço:"), "right");
        campoPreco = new JTextField(20);
        painelFormulario.add(campoPreco, "growx");
        
        painelFormulario.add(new JLabel("Estoque:"), "right");
        campoEstoque = new JTextField(20);
        painelFormulario.add(campoEstoque, "growx");

       
        String[] colunas = {"ID", "Nome", "Preço", "Estoque"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaProdutos = new JTable(modeloTabela);
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);

        JPanel painelBotoes = new JPanel(new MigLayout("fillx, align center", "grow", ""));
        btnCadastrar = new JButton("Cadastrar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        btnLimpar = new JButton("Limpar");
        btnDeslogar = new JButton("Deslogar");

    
        painelBotoes.add(btnCadastrar, "growx");
        painelBotoes.add(btnEditar, "growx");
        painelBotoes.add(btnRemover, "growx");
        painelBotoes.add(btnLimpar, "growx");
        painelBotoes.add(btnDeslogar, "growx");
        
   
        add(painelFormulario, "wrap, growx, w 600::"); 
        add(scrollPane, "wrap, grow, push, w 600::"); 
        add(painelBotoes, "growx, w 600::"); 

 
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

    // Getters para os campos e botões... (Métodos mantidos)
    public String getNome() { return campoNome.getText(); }
    public String getPreco() { return campoPreco.getText(); }
    public String getEstoque() { return campoEstoque.getText(); }
    public void setNome() {  campoNome.setText(""); }
    public void setPreco() {  campoPreco.setText(""); }
    public void setEstoque() { campoEstoque.setText(""); }
    public JTable getTabelaProdutos() { return tabelaProdutos; }
    public DefaultTableModel getModeloTabela() { return modeloTabela; }
    public JButton getBtnCadastrar() { return btnCadastrar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnRemover() { return btnRemover; }
    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnDeslogar() { return btnDeslogar; }
}