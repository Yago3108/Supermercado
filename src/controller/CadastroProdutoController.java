package controller;

import view.CadastroProdutoView;
import view.Main;

import javax.swing.*;

import model.Produto;
import model.ProdutoDAO;

import java.util.List;

public class CadastroProdutoController {
    private final CadastroProdutoView view;
    private final Main app;
    private final ProdutoDAO produtoDAO;

    public CadastroProdutoController(CadastroProdutoView view, Main app, ProdutoDAO produtoDAO) {
        this.view = view;
        this.app = app;
        this.produtoDAO = produtoDAO;
        
        this.view.getBtnCadastrar().addActionListener(e -> cadastrarProduto());
        this.view.getBtnEditar().addActionListener(e -> editarProduto());
        this.view.getBtnRemover().addActionListener(e -> removerProduto());
        this.view.getBtnLimpar().addActionListener(e -> limparCampos());
        this.view.getBtnDeslogar().addActionListener(e -> app.exibirTela("login"));
        
        carregarProdutos();
    }
    
    private void carregarProdutos() {
        List<Produto> produtos = produtoDAO.listarTodos();
        view.getModeloTabela().setRowCount(0); // Limpa a tabela
        for (Produto p : produtos) {
            view.getModeloTabela().addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQuantidadeEstoque()});
        }
    }
    
    private void cadastrarProduto() {
        try {
            String nome = view.getNome();
            double preco = Double.parseDouble(view.getPreco());
            int estoque = Integer.parseInt(view.getEstoque());
            
            if (nome.isEmpty() || preco <= 0 || estoque < 0) {
                JOptionPane.showMessageDialog(view, "Dados do produto inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Produto novoProduto = new Produto(nome, preco, estoque);
            if (produtoDAO.inserir(novoProduto)) {
                JOptionPane.showMessageDialog(view, "Produto cadastrado com sucesso!");
                limparCampos();
                carregarProdutos();
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao cadastrar produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Preço e estoque devem ser números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarProduto() {
        int linhaSelecionada = view.getTabelaProdutos().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um produto para editar.");
            return;
        }
        
        try {
            int id = (int) view.getModeloTabela().getValueAt(linhaSelecionada, 0);
            String nome = view.getNome();
            double preco = Double.parseDouble(view.getPreco());
            int estoque = Integer.parseInt(view.getEstoque());
            
            Produto produtoAtualizado = new Produto(id, nome, preco, estoque);
            if (produtoDAO.atualizar(produtoAtualizado)) {
                JOptionPane.showMessageDialog(view, "Produto atualizado com sucesso!");
                limparCampos();
                carregarProdutos();
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao atualizar produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Preço e estoque devem ser números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerProduto() {
        int linhaSelecionada = view.getTabelaProdutos().getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um produto para remover.");
            return;
        }

        int id = (int) view.getModeloTabela().getValueAt(linhaSelecionada, 0);
        int confirmacao = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja remover este produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            if (produtoDAO.remover(id)) {
                JOptionPane.showMessageDialog(view, "Produto removido com sucesso!");
                limparCampos();
                carregarProdutos();
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao remover produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limparCampos() {
        view.getBtnLimpar().addActionListener(e -> {
            view.getTabelaProdutos().clearSelection();
        });
    }
}