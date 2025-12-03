package controller;
import javax.swing.JOptionPane;

import model.Usuario;
import model.UsuarioDAO;
import view.LoginView;
import view.Main; 

public class LoginController {
    private final LoginView loginView;
    private final Main app; 
    private final UsuarioDAO usuarioDAO;
    

    public LoginController(LoginView loginView, Main app, UsuarioDAO usuarioDAO) {
        this.loginView = loginView;
        this.app = app;
        this.usuarioDAO=usuarioDAO;
        this.loginView.addEntrarListener(e -> autenticarUsuario());
        this.loginView.addCadastrarListener(e -> app.exibirTela("cadastroUsuario"));
    }
    private void autenticarUsuario() {
    	try {
        String cpf = loginView.getCpf();              
        double cpfint=Double.parseDouble(cpf);
        Usuario usuario = usuarioDAO.buscarPorCpf(cpf);
        if(cpf.length()!=11) {
        	 JOptionPane.showMessageDialog(loginView, "CPF deve conter 11 números.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
             loginView.setTfCpf("");
             loginView.setTfNome("");
        	return;
        }
        if (usuario != null) {
       
        	app.logarUsuario(usuario);
            JOptionPane.showMessageDialog(loginView, "Login realizado com sucesso! Bem-vindo(a), " + usuario.getNome() + ".", "Bem-vindo", JOptionPane.INFORMATION_MESSAGE);
            loginView.setTfCpf("");
            loginView.setTfNome("");
        } else {
            JOptionPane.showMessageDialog(loginView, "Usuário não encontrado.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            loginView.setTfCpf("");
            loginView.setTfNome("");
        }}catch(Exception e) {
        	  JOptionPane.showMessageDialog(loginView, "O CPF deve conter apenas números.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
 
}