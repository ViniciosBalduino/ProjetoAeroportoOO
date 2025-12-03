/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;


public class Passageiro {
    
    private int id;
    private String nome;
    private LocalDate nascimento;
    private String documento;
    private String login;
    private String Senha;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    
    
    public String recuperarLogin(){
        String passageiro = "";
        passageiro += "\nID = " + this.id;
        passageiro += "\nNome = " + this.nome;
        passageiro += "\nLogin = " + this.login;
        passageiro += "\n";
        return passageiro;
    }
    
    public String recuperarSenha(){
        String passageiro = "";
        passageiro += "\nID = " + this.id;
        passageiro += "\nNome = " + this.nome;
        passageiro += "\nLogin = " + this.login;
        passageiro += "\nSenha = " + this.Senha;
        passageiro += "\n";
        return passageiro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
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
        final Passageiro other = (Passageiro) obj;
        return this.id == other.id;
    }
    
    @Override
    public String toString() {
        String passageiro = "";
        passageiro += "\nID = " + this.id;
        passageiro += "\nNome = " + this.nome;
        passageiro += "\nNascimento = " + this.nascimento.getDayOfMonth() + "/" + this.nascimento.getMonth() + "/" + this.nascimento.getYear();
        passageiro += "\nData Cadastro = " + this.dataCriacao;
        passageiro += "\n";
        return passageiro;
    }
}

