/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


public class Passageiro {
    private static int serial;
    private int id;
    private String nome;
    private LocalDate nascimento;
    private String documento;
    private String login;
    private String Senha;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    
    
    public Passageiro(){
        this.id = ++Passageiro.serial;
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
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
        this.dataModificacao = LocalDate.now();
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
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

