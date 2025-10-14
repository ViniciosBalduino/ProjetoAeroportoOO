/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author vinic
 */
public class Voo {

    private static int serial;
    private String id;
    private String origem;
    private String destino;
    private LocalDate data;
    private LocalTime duracao;
    private CompAerea companhia;
    private int capacidade;
    private String estado;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public Voo(CompAerea companhia) {
        if (companhia != null) {
            this.id = companhia.getAbreviacao() + "-V" + ++serial;
            this.companhia = companhia;
        } else {
            this.id = String.valueOf(++serial);
            CompAerea compGen = new CompAerea();
            compGen.setNome("Companhia Generica, deve ser substituida assim que possivel");
            compGen.setAbreviacao("GEN");
            this.companhia = compGen;
        }
        this.dataCriacao = LocalDate.now();
    }

    public String getId() {
        return id;
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

    public CompAerea getCompanhia() {
        return companhia;
    }

    public void setCompanhia(CompAerea companhia) {
        this.companhia = companhia;
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
        voo += "\nCompanhia = " + this.companhia.getAbreviacao();
        voo += "\nOrigem em = " + this.origem;
        voo += "\nDestino em = " + this.destino;
        voo += "\nDia do voo = " + this.data;
        voo += "\nTempo do voo = " + this.duracao;
        voo += "\nStatus = " + this.estado;
        voo += "\n";
        return voo;
    }

}
