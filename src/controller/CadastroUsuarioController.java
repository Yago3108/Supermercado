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
        this.view.addVoltarListener(e ->{
        	app.exibirTela("login");
        	view.setNome("");
        	view.setCPF("");
        	view.setAdmin();
        } 
        		);
    }
    
    private void salvarUsuario() {
        String nome = view.getNome();
        String cpf = view.getCpf();
        boolean ehAdmin = view.isEhAdmin();
        
        if (nome.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nome e CPF são obrigatórios.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(cpf.length()!=11) {
            JOptionPane.showMessageDialog(view, "CPF menor que 11 dígitos", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
        	double CPF=Double.parseDouble(cpf);
        	Usuario novoUsuario = new Usuario(nome, cpf, ehAdmin);
            if (usuarioDAO.inserir(novoUsuario)) {
                JOptionPane.showMessageDialog(view, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                view.setNome("");
                view.setCPF("");
                view.setAdmin();
                app.exibirTela("login");
             
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao cadastrar usuário. Verifique se o CPF já existe.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(view, "O CPF deve conter apenas números", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
    }
}