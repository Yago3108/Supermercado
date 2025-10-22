package model;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private boolean ehAdmin;

    public Usuario(String nome, String cpf, boolean ehAdmin) {
        this.nome = nome;
        this.cpf = cpf;
        this.ehAdmin = ehAdmin;
    }

    public Usuario(int id, String nome, String cpf, boolean ehAdmin) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.ehAdmin = ehAdmin;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public boolean isEhAdmin() { return ehAdmin; }
    public void setEhAdmin(boolean ehAdmin) { this.ehAdmin = ehAdmin; }
}