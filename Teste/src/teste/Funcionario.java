/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;

/**
 *
 * @author vitor
 */
public class Funcionario {
    private static int serial;
    private int id;
    private String nome;
    private LocalDate nascimento;
    private String documento;
    private String login;
    private String senha;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public Funcionario(){
        this.id = ++Funcionario.serial;
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = LocalDate.now();
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.dataModificacao = LocalDate.now();
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        this.dataModificacao = LocalDate.now();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
        this.dataModificacao = LocalDate.now();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        this.dataModificacao = LocalDate.now();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String Senha) {
        this.senha = Senha;
        this.dataModificacao = LocalDate.now();
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        return this.id == other.id;
    }
    
    @Override
    public String toString() {
        String funcionario = "";
        funcionario += "\nID = " + this.id;
        funcionario += "\nNome = " + this.nome;
        funcionario += "\nNascimento = " + this.nascimento.getDayOfMonth() + "/" + this.nascimento.getMonth() + "/" + this.nascimento.getYear();
        funcionario += "\nData Cadastro = " + this.dataCriacao;
        funcionario += "\n";
        return funcionario;
    }

    public String recuperarLogin(){
        String funcionario = "";
        funcionario += "\nID = " + this.id;
        funcionario += "\nNome = " + this.nome;
        funcionario += "\nLogin = " + this.login;
        funcionario += "\n";
        return funcionario;
    }
    
    public String recuperarSenha(){
        String funcionario = "";
        funcionario += "\nID = " + this.id;
        funcionario += "\nNome = " + this.nome;
        funcionario += "\nLogin = " + this.login;
        funcionario += "\nSenha = " + this.senha;
        funcionario += "\n";
        return funcionario;
    }
}
