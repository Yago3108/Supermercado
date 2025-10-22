package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProdutoDAO {

    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco, quantidade_estoque) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover produto: " + e.getMessage());
            return false;
        }
    }
    
    public boolean atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade_estoque = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setInt(4, produto.getId());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarEstoque(int produtoId, int quantidadeAlteracao) {
        String sql = "UPDATE produtos SET quantidade_estoque = quantidade_estoque - ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection()) {
            if (conn == null) { 
            	return false;
            	} 

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, quantidadeAlteracao);
                stmt.setInt(2, produtoId);
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar estoque: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque: " + e.getMessage(), "Erro de BD", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        
        try (Connection conn = Conexao.getConnection()) {
            
     
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco de dados. Verifique o Conexao.java.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
                return produtos; 
            }
            
    
            try (PreparedStatement stmt = conn.prepareStatement(sql); 
                 ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade_estoque")
                    );
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos. Detalhes: " + e.getMessage(), "Erro de BD", JOptionPane.ERROR_MESSAGE);
        }
        return produtos;
    }
}