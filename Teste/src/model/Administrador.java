/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;


public class Administrador {
    
    private int id;
    private String nome;
    private LocalDate nascimento;
    private String documento;
    private String login;
    private String senha;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    
    
    public Administrador(){
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
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
        final Administrador other = (Administrador) obj;
        return this.id == other.id;
    }

    
    
    @Override
    public String toString() {
        String admin = "";
        admin += "\nID = " + this.id;
        admin += "\nNome = " + this.nome;
        admin += "\nNascimento = " + this.nascimento.getDayOfMonth() + "/" + this.nascimento.getMonth() + "/" + this.nascimento.getYear();
        admin += "\nData Cadastro = " + this.dataCriacao;
        admin += "\n";
        return admin;
    }
    
    public String recuperarDocumento(){
        String admin = "";
        admin += "\nID = " + this.id;
        admin += "\nNome = " + this.nome;
        admin += "\nDocumento = " + this.documento;
        admin += "\n";
        return admin;
    }

    public String recuperarLogin(){
        String admin = "";
        admin += "\nID = " + this.id;
        admin += "\nNome = " + this.nome;
        admin += "\nLogin = " + this.login;
        admin += "\n";
        return admin;
    }
    
    public String recuperarSenha(){
        String admin = "";
        admin += "\nID = " + this.id;
        admin += "\nNome = " + this.nome;
        admin += "\nLogin = " + this.login;
        admin += "\nSenha = " + this.senha;
        admin += "\n";
        return admin;
    }
    
    
}

