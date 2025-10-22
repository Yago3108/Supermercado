package view;

import controller.*;
import model.ProdutoDAO;
import model.Usuario;
import model.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame { 
	private static final long serialVersionUID = 1L;
	private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    private Usuario usuarioLogado;

    // Instancia as Views
    private final LoginView loginView = new LoginView();
    private final CadastroUsuarioView cadastroUsuarioView = new CadastroUsuarioView();
    private final CadastroProdutoView cadastroProdutoView = new CadastroProdutoView();
    private final CompraView compraView = new CompraView();


    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public Main() {
        setTitle("Sistema de Supermercado");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Adiciona as views ao painel principal 
        mainPanel.add(loginView, "login");
        mainPanel.add(cadastroUsuarioView, "cadastroUsuario");
        mainPanel.add(cadastroProdutoView, "cadastroProduto");
        mainPanel.add(compraView, "compra");

        add(mainPanel);

 
        new LoginController(loginView, this, usuarioDAO);
        new CadastroUsuarioController(cadastroUsuarioView, this, usuarioDAO);
        
    
        
        exibirTela("login"); // Tela inicial
    }
    

    public void logarUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        
        if (usuario.isEhAdmin()) {
          
            new CadastroProdutoController(cadastroProdutoView, this, produtoDAO);
            exibirTela("cadastroProduto");
        } else {
             new CompraController(compraView, this, produtoDAO, usuario); 
             exibirTela("compra");
        }
    }
    
    public void deslogar() {
        this.usuarioLogado = null; 
        exibirTela("login");
    }
    
    public void exibirTela(String nomeTela) {
        cardLayout.show(mainPanel, nomeTela);
        pack();
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}