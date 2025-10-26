/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;
/**
 *
 * @author vinic
 */
public class Ticket {
    private static int serial;
    private int id;
    private int valor;
    private Voo voo;
    private Passageiro passageiro;
    private VooAssentos vooAssentos;
    private String codigoTicket;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    
    public Ticket(Passageiro passageiro, Voo voo, VooAssentos vooAssentos){
        this.id = ++Ticket.serial;
        this.voo = voo;
        this.passageiro = passageiro;
        this.codigoTicket = "T-" + String.valueOf(id) + voo.getId() + "-00";
        this.vooAssentos = vooAssentos;
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = LocalDate.now();
        this.valor = serial % 2 == 0 ? 50 : 100;
    }
    
    public int getId(){
        return id;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }
    
    public Voo getVoo(){
        return voo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
        this.dataModificacao = LocalDate.now();
    }

    public String getCodigoTicket() {
        return codigoTicket;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }
    
    @Override
    public String toString(){
        
        String ticket = "\n--------------------------------";
        ticket += "\nCodigo = " + this.codigoTicket;
        ticket += "\nVoo = " + this.voo.getOrigem() + "-->" + this.voo.getDestino();
        ticket += "\nPassageiro = " + this.passageiro.getNome();
        ticket += "\nValor = " + this.valor;
        ticket += "\nCompra efetuada em = " + this.dataCriacao;
        ticket += "\n--------------------------------\n";
        
        return ticket;
    }
    
}
