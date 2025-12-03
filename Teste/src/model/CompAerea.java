/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author vinic
 */
public class CompAerea {
    //private static int serial;
    private String id;
    private String nome;
    private String abreviacao;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    
     
    public CompAerea(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.dataModificacao = LocalDate.now();
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
        //this.dataModificacao = LocalDate.now();
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.abreviacao);
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
        final CompAerea other = (CompAerea) obj;
        return Objects.equals(this.abreviacao, other.abreviacao);
    }
    
    
    
    @Override
    public String toString() {
        String aeroporto = "";
        aeroporto += "\nID = " + this.id;
        aeroporto += "\nNome = " + this.nome;
        aeroporto += "\nSigla = " + this.abreviacao;
        aeroporto += "\nData Cadastro = " + this.dataCriacao;
        aeroporto += "\n";
        return aeroporto;
    }
}
