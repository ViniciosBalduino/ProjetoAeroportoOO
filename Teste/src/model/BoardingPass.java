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
public class BoardingPass {
    private int id;
    private String nomePassageiro;
    private String idVoo;
    private String idAssento;
    private int numeroAssento;
    private String idTicket;
    private LocalDate dataCriacao;

    public String getNomePassageiro() {
        return nomePassageiro;
    }

    public void setNomePassageiro(String nomePassageiro) {
        this.nomePassageiro = nomePassageiro;
    }

    public String getIdVoo() {
        return idVoo;
    }

    public void setIdVoo(String idVoo) {
        this.idVoo = idVoo;
    }

    public String getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(String idAssento) {
        this.idAssento = idAssento;
    }

    public int getNumeroAssento() {
        return numeroAssento;
    }

    public void setNumeroAssento(int numeroAssento) {
        this.numeroAssento = numeroAssento;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString(){
        String boardingPass = "-----------------------------";
        boardingPass += "\nID = " + this.id;
        boardingPass += "\nPassageiro = " + this.nomePassageiro;
        boardingPass += "\nVoo = " + this.idVoo;
        boardingPass += "\nID Assento = " + this.idAssento;
        boardingPass += "\nNumero do Assento = " + this.numeroAssento;
        boardingPass += "\nID Ticket = " + this.idTicket;
        boardingPass += "\nData de Impressao = " + this.dataCriacao;
        boardingPass += "\n-----------------------------";
        
        return boardingPass;
    }
}
