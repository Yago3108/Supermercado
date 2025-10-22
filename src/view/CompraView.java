package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.Produto;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class CompraView extends JPanel {
    private final JTable tabelaProdutosDisponiveis;
    private final DefaultTableModel modeloTabelaDisponiveis;
    private final JTable tabelaCarrinho;
    private final DefaultTableModel modeloTabelaCarrinho;
    private final JButton btnAdicionar;
    private final JButton btnRemover;
    private final JButton btnFinalizarCompra;
    private final JButton btnDeslogar;
    private final JLabel lblTotal;

    public CompraView() {
        setLayout(new BorderLayout(10, 10));

        // Painel de Produtos Disponíveis
        JPanel painelProdutos = new JPanel(new BorderLayout());
        painelProdutos.setBorder(new TitledBorder("Produtos Disponíveis"));
        String[] colunasDisponiveis = {"ID", "Nome", "Preço", "Estoque"};
        modeloTabelaDisponiveis = new DefaultTableModel(colunasDisponiveis, 0);
        tabelaProdutosDisponiveis = new JTable(modeloTabelaDisponiveis);
        tabelaProdutosDisponiveis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        painelProdutos.add(new JScrollPane(tabelaProdutosDisponiveis), BorderLayout.CENTER);
        
        // Painel do Carrinho
        JPanel painelCarrinho = new JPanel(new BorderLayout());
        painelCarrinho.setBorder(new TitledBorder("Carrinho de Compras"));
        String[] colunasCarrinho = {"Nome", "Preço", "Quantidade"}; // <--- CORRIGIDO
        modeloTabelaCarrinho = new DefaultTableModel(colunasCarrinho, 0);
        tabelaCarrinho = new JTable(modeloTabelaCarrinho);
        tabelaCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        painelCarrinho.add(new JScrollPane(tabelaCarrinho), BorderLayout.CENTER);

        // Painel de Ações
        JPanel painelAcoes = new JPanel(new BorderLayout(10, 10));
        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 5, 5));
        btnAdicionar = new JButton("Adicionar ao Carrinho");
        btnRemover = new JButton("Remover do Carrinho");
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnRemover);
        JPanel painelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblTotal = new JLabel("Total: R$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        painelTotal.add(lblTotal);
        
        JPanel painelFinalizar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnFinalizarCompra = new JButton("Finalizar Compra");
        btnDeslogar = new JButton("Deslogar");
        painelFinalizar.add(btnFinalizarCompra);
        painelFinalizar.add(btnDeslogar);

        painelAcoes.add(painelBotoes, BorderLayout.NORTH);
        painelAcoes.add(painelTotal, BorderLayout.CENTER);
        painelAcoes.add(painelFinalizar, BorderLayout.SOUTH);

        // Adiciona os painéis à view principal
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelProdutos, painelCarrinho);
        splitPane.setResizeWeight(0.5);

        add(splitPane, BorderLayout.CENTER);
        add(painelAcoes, BorderLayout.SOUTH);
    }
    public void atualizarTabelaProdutos(List<Produto> produtos) {
        modeloTabelaDisponiveis.setRowCount(0); 
        
        for (Produto p : produtos) {
            modeloTabelaDisponiveis.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQuantidadeEstoque()});
        }
    }
    // Métodos para atualizar a view
    public void atualizarTabelaCarrinho(Map<Produto, Integer> itensComQuantidade) {
        modeloTabelaCarrinho.setRowCount(0); // Limpa a tabela
        // Iterar sobre o Map
        for (Map.Entry<Produto, Integer> entry : itensComQuantidade.entrySet()) {
            Produto p = entry.getKey();
            int quantidade = entry.getValue();
            modeloTabelaCarrinho.addRow(new Object[]{p.getNome(), p.getPreco(), quantidade}); // Adiciona a quantidade
        }
    }
    
    public void atualizarTabelaCarrinho(List<Produto> produtos) {
        modeloTabelaCarrinho.setRowCount(0); // Limpa a tabela
        for (Produto p : produtos) {
            modeloTabelaCarrinho.addRow(new Object[]{p.getNome(), p.getPreco()});
        }
    }
    
    public void setTotal(double total) {
        lblTotal.setText(String.format("Total: R$ %.2f", total));
    }
    public DefaultTableModel getModeloTabela() { 
        return modeloTabelaDisponiveis; 
    }
    // Getters para botões e tabelas
    public JButton getBtnAdicionar() { return btnAdicionar; }
    public JButton getBtnRemover() { return btnRemover; }
    public JButton getBtnFinalizarCompra() { return btnFinalizarCompra; }
    public JButton getBtnDeslogar() { return btnDeslogar; }
    public JTable getTabelaProdutosDisponiveis() { return tabelaProdutosDisponiveis; }
    public JTable getTabelaCarrinho() { return tabelaCarrinho; }
    
    

}