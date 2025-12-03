/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author vitor
 */
public class Funcionario {
    
    private int id;
    private String nome;
    private LocalDate nascimento;
    private String documento;
    private String login;
    private String senha;
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
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
