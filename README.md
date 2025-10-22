# SUPERMERCADO PDS 
aluno:Yago Schramm de Souza
turma:I6
# Descrição do projeto
Um sistema de supermercado que contém um banco de dados do MySql e utiliza o modelo MVC como arquitetura do projeto 
# Criação do Banco de dados
CREATE DATABASE supermercado;
 USE supermercado;

 CREATE TABLE usuarios (
 id INT AUTO_INCREMENT PRIMARY KEY,
 nome VARCHAR(255) NOT NULL,
 cpf VARCHAR(14) NOT NULL UNIQUE,
 eh_admin BOOLEAN NOT NULL ); 

CREATE TABLE produtos ( 
id INT AUTO_INCREMENT PRIMARY KEY, 
nome VARCHAR(255) NOT NULL,
 preco DECIMAL(10, 2) NOT NULL,
 quantidade_estoque INT NOT NULL );
