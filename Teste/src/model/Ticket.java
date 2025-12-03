/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
/**
 *
 * @author vinic
 */
public class Ticket {
    
    private int id;
    private int valor;
    private String idVoo;
    private String nomePassageiro;
    private String idVooAssento;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getIdVoo() {
        return idVoo;
    }

    public void setIdVoo(String idVoo) {
        this.idVoo = idVoo;
    }

    public String getNomePassageiro() {
        return nomePassageiro;
    }

    public void setNomePassageiro(String nomePassageiro) {
        this.nomePassageiro = nomePassageiro;
    }

    public String getIdVooAssento() {
        return idVooAssento;
    }

    public void setIdVooAssento(String idVooAssento) {
        this.idVooAssento = idVooAssento;
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
    public String toString(){
        
        String ticket = "\n--------------------------------";
        ticket += "\nCodigo = " + this.id;
        ticket += "\nVoo = " + this.idVoo;
        ticket += "\nPassageiro = " + this.nomePassageiro;
        ticket += "\nValor = " + this.valor;
        ticket += "\nCompra efetuada em = " + this.dataCriacao;
        ticket += "\n--------------------------------\n";
        
        return ticket;
    }
    
}
