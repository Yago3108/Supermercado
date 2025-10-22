package controller;

import view.CadastroUsuarioView;
import view.Main;

import javax.swing.JOptionPane;

import model.Usuario;
import model.UsuarioDAO;

public class CadastroUsuarioController {
    private final CadastroUsuarioView view;
    private final Main app;
    private final UsuarioDAO usuarioDAO;

    public CadastroUsuarioController(CadastroUsuarioView view, Main app, UsuarioDAO usuarioDAO) {
        this.view = view;
        this.app = app;
        this.usuarioDAO = usuarioDAO;
        
        this.view.addSalvarListener(e -> salvarUsuario());
        this.view.addVoltarListener(e -> app.exibirTela("login"));
    }
    
    private void salvarUsuario() {
        String nome = view.getNome();
        String cpf = view.getCpf();
        boolean ehAdmin = view.isEhAdmin();
        
        if (nome.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nome e CPF são obrigatórios.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Usuario novoUsuario = new Usuario(nome, cpf, ehAdmin);
        if (usuarioDAO.inserir(novoUsuario)) {
            JOptionPane.showMessageDialog(view, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            app.exibirTela("login");
            view.setNome("");
            view.setCPF("");
            view.setAdmin();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao cadastrar usuário. Verifique se o CPF já existe.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }
}