/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author vinic
 */
public class Voo {

    private String id;
    private String origem;
    private String destino;
    private LocalDate data;
    private LocalTime duracao;
    private LocalTime horario;
    private String siglaCompanhia;
    private int capacidade;
    private String estado;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public Voo(String companhia){
        this.siglaCompanhia = companhia;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getDuracao() {
        return duracao;
    }

    public void setDuracao(LocalTime duracao) {
        this.duracao = duracao;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
    

    public String getSiglaCompanhia() {
        return siglaCompanhia;
    }

    public void setSiglaCompanhia(String siglaCompanhia) {
        this.siglaCompanhia = siglaCompanhia;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Voo other = (Voo) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        String voo = "";
        voo += "\nID = " + this.id;
        voo += "\nCompanhia = " + this.siglaCompanhia;
        voo += "\nOrigem = " + this.origem;
        voo += "\nDestino = " + this.destino;
        voo += "\nDia do voo = " + this.data;
        voo += "\nHorario = " + this.horario;
        voo += "\nDuracao = " + this.duracao;
        voo += "\nCapacidade do voo = " + this.capacidade;
        voo += "\nStatus = " + this.estado;
        voo += "\n";
        return voo;
    }

}
