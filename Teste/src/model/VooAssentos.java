/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

//import model.Passageiro;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author vinic
 */
public class VooAssentos {
    //private static int serial;
    private String idAssento;
    private String idVoo;
    private int valor;
    private String idPassageiro;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public VooAssentos(){
       
    }

    public String getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(String idAssento) {
        this.idAssento = idAssento;
    }

    public String getIdVoo() {
        return idVoo;
    }

    public void setIdVoo(String idVoo) {
        this.idVoo = idVoo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getIdPassageiro() {
        return idPassageiro;
    }

    public void setIdPassageiro(String idPassageiro) {
        this.idPassageiro = idPassageiro;
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
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idAssento);
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
        final VooAssentos other = (VooAssentos) obj;
        return Objects.equals(this.idAssento, other.idAssento);
    }
        
    
    @Override
    public String toString() {
        String vooAssento = "";
        vooAssento += "\nID = " + this.idAssento;
        vooAssento += "\nVoo = " + this.idVoo;
        vooAssento += "\nPassageiro = " + this.idPassageiro;
        vooAssento += "\n";
        return vooAssento;
    }
    
}
