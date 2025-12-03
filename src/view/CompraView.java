package view;

import net.miginfocom.swing.MigLayout; // Certifique-se de ter a biblioteca MigLayout no seu projeto
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
        setLayout(new MigLayout(
            "fill, insets 10, hidemode 3", 
            "[grow, fill]10[grow, fill]", 
            "[grow, fill][][grow, fill]"
        ));

        JPanel painelProdutos = new JPanel(new BorderLayout());
        painelProdutos.setBorder(new TitledBorder("Produtos Disponíveis"));
        String[] colunasDisponiveis = {"ID", "Nome", "Preço", "Estoque"};
        modeloTabelaDisponiveis = new DefaultTableModel(colunasDisponiveis, 0);
        tabelaProdutosDisponiveis = new JTable(modeloTabelaDisponiveis);
        tabelaProdutosDisponiveis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        painelProdutos.add(new JScrollPane(tabelaProdutosDisponiveis), BorderLayout.CENTER);
        
        add(painelProdutos, "cell 0 0, grow, push"); 

        JPanel painelCarrinho = new JPanel(new BorderLayout());
        painelCarrinho.setBorder(new TitledBorder("Carrinho de Compras"));
        String[] colunasCarrinho = {"Nome", "Preço", "Quantidade"}; 
        modeloTabelaCarrinho = new DefaultTableModel(colunasCarrinho, 0);
        tabelaCarrinho = new JTable(modeloTabelaCarrinho);
        tabelaCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        painelCarrinho.add(new JScrollPane(tabelaCarrinho), BorderLayout.CENTER);
        add(painelCarrinho, "cell 1 0, grow, push, wrap"); 
        JPanel painelAcoesCarrinho = new JPanel(new MigLayout("fillx, insets 0", "[grow, fill]10[grow, fill]", ""));
        btnAdicionar = new JButton("Adicionar ao Carrinho");
        btnRemover = new JButton("Remover do Carrinho");
        painelAcoesCarrinho.add(btnAdicionar, "growx");
        painelAcoesCarrinho.add(btnRemover, "growx");


        add(painelAcoesCarrinho, "span 2, growx, wrap 10"); 
        

        JPanel painelRodape = new JPanel(new MigLayout("fillx, insets 0", "[grow, right]20[fill]", ""));
        
        lblTotal = new JLabel("Total: R$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        
    
        JPanel painelBotoesFinal = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnFinalizarCompra = new JButton("Finalizar Compra");
        btnDeslogar = new JButton("Deslogar");
        painelBotoesFinal.add(btnFinalizarCompra);
        painelBotoesFinal.add(btnDeslogar);
        

        painelRodape.add(lblTotal, "align right, pushx");
        painelRodape.add(painelBotoesFinal, "align right");

    
        add(painelRodape, "span 2, growx, pushy 0"); 

    }
    
    public void atualizarTabelaProdutos(List<Produto> produtos) {
        modeloTabelaDisponiveis.setRowCount(0); 
        
        for (Produto p : produtos) {
            modeloTabelaDisponiveis.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQuantidadeEstoque()});
        }
    }
 
    public void atualizarTabelaCarrinho(Map<Produto, Integer> itensComQuantidade) {
        modeloTabelaCarrinho.setRowCount(0); 
 
        for (Map.Entry<Produto, Integer> entry : itensComQuantidade.entrySet()) {
            Produto p = entry.getKey();
            int quantidade = entry.getValue();
            modeloTabelaCarrinho.addRow(new Object[]{p.getNome(), p.getPreco(), quantidade}); 
        }
    }
    
    public void atualizarTabelaCarrinho(List<Produto> produtos) {
        modeloTabelaCarrinho.setRowCount(0);
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
    public JButton getBtnAdicionar() { return btnAdicionar; }
    public JButton getBtnRemover() { return btnRemover; }
    public JButton getBtnFinalizarCompra() { return btnFinalizarCompra; }
    public JButton getBtnDeslogar() { return btnDeslogar; }
    public JTable getTabelaProdutosDisponiveis() { return tabelaProdutosDisponiveis; }
    public JTable getTabelaCarrinho() { return tabelaCarrinho; }
}