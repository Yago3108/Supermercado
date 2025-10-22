// CompraController.java (Refatorado)
package controller;

import view.CompraView;
import view.Main;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Carrinho;
import model.Produto;
import model.ProdutoDAO;
import model.Usuario;

import java.util.List;
import java.util.Map; 

public class CompraController {
    private final CompraView view;
    private final Main app; 
    private final ProdutoDAO produtoDAO;
    private final Carrinho carrinho;
    private final Usuario usuarioLogado; 

    public CompraController(CompraView view, Main app, ProdutoDAO produtoDAO, Usuario usuarioLogado) {
        this.view = view;
        this.app = app;
        this.produtoDAO = produtoDAO;
        this.carrinho = new Carrinho(); 
        this.usuarioLogado = usuarioLogado; 
        
        this.view.getBtnAdicionar().addActionListener(e -> adicionarAoCarrinho());
        this.view.getBtnRemover().addActionListener(e -> removerDoCarrinho());
        this.view.getBtnFinalizarCompra().addActionListener(e -> finalizarCompra());
        this.view.getBtnDeslogar().addActionListener(e -> deslogar());
        
        carregarProdutosDisponiveis();
    }
    
    private void carregarProdutosDisponiveis() {
        List<Produto> produtos = produtoDAO.listarTodos();
        view.atualizarTabelaProdutos(produtos);
    }
    
    private void adicionarAoCarrinho() {
        int linhaSelecionada = view.getTabelaProdutosDisponiveis().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um produto para adicionar ao carrinho.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

   
        int id = (int) view.getModeloTabela().getValueAt(linhaSelecionada, 0);
        String nome = (String) view.getModeloTabela().getValueAt(linhaSelecionada, 1);
        double preco = (double) view.getModeloTabela().getValueAt(linhaSelecionada, 2);
        int estoqueAtual = (int) view.getModeloTabela().getValueAt(linhaSelecionada, 3);
        

        if (estoqueAtual > 0) {
            Produto produto = new Produto(id, nome, preco, estoqueAtual);
            carrinho.adicionarProduto(produto);
            
        
            view.atualizarTabelaCarrinho(carrinho.getItensComQuantidade()); 
            view.setTotal(carrinho.calcularTotal());
            JOptionPane.showMessageDialog(view, nome + " adicionado ao carrinho.");
        } else {
            JOptionPane.showMessageDialog(view, "Estoque insuficiente para " + nome + ".", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void removerDoCarrinho() {
   
        int linhaSelecionada = view.getTabelaCarrinho().getSelectedRow();
        
      
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, 
                "Selecione um item no carrinho para remover.", 
                "Erro de Seleção", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
  
        DefaultTableModel modeloTabela = (DefaultTableModel) view.getTabelaCarrinho().getModel();
        String nomeProduto = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
        
     
        Produto produtoParaRemover = null;

        for (Produto p : carrinho.getItensComQuantidade().keySet()) {
            if (p.getNome().equals(nomeProduto)) {
                produtoParaRemover = p;
                break;
            }
        }
        
      
        if (produtoParaRemover != null) {
            carrinho.removerProduto(produtoParaRemover);
            modeloTabela.removeRow(linhaSelecionada);
            view.setTotal(carrinho.calcularTotal());
            
          
            JOptionPane.showMessageDialog(view, nomeProduto + " removido do carrinho.", "Remoção", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
             JOptionPane.showMessageDialog(view, 
                "Erro interno: Produto não encontrado no Model.", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void finalizarCompra() {
        if (carrinho.getItensComQuantidade().isEmpty()) {
            JOptionPane.showMessageDialog(view, "O carrinho está vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }


        boolean sucessoAtualizacao = true;
        for (Map.Entry<Produto, Integer> entry : carrinho.getItensComQuantidade().entrySet()) {
            Produto item = entry.getKey();
            int quantidade = entry.getValue();
            
            if (!produtoDAO.atualizarEstoque(item.getId(), quantidade)) {
                sucessoAtualizacao = false;
            }
        }
        
        if (!sucessoAtualizacao) {
            JOptionPane.showMessageDialog(view, "Erro ao processar a compra. Um erro de BD ocorreu durante a atualização do estoque.", "Erro de Compra", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        StringBuilder notaFiscal = new StringBuilder("--- Nota Fiscal Supermercado ---\n");
        notaFiscal.append("Cliente: ").append(usuarioLogado.getNome()).append("\n"); 
        notaFiscal.append("CPF: ").append(usuarioLogado.getCpf()).append("\n\n");    
        
        notaFiscal.append("Produtos Comprados:\n");
        for (Map.Entry<Produto, Integer> entry : carrinho.getItensComQuantidade().entrySet()) {
            Produto item = entry.getKey();
            int quantidade = entry.getValue();
            notaFiscal.append(String.format("- %d x %s (R$ %.2f) = R$ %.2f\n", 
                quantidade, item.getNome(), item.getPreco(), item.getPreco() * quantidade));
        }
        
        notaFiscal.append(String.format("\nTotal Pago: R$ %.2f\n", carrinho.calcularTotal()));
        notaFiscal.append("------------------------------");

        JOptionPane.showMessageDialog(view, notaFiscal.toString(), "Compra Realizada com Sucesso", JOptionPane.INFORMATION_MESSAGE);

        carrinho.limpar();
        view.atualizarTabelaCarrinho(carrinho.getItensComQuantidade());
        view.setTotal(0);
        carregarProdutosDisponiveis(); 
    }
    
    private void deslogar() {
        carrinho.limpar();
        app.deslogar(); 
    }
}