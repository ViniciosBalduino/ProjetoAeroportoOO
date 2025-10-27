/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author vinic
 */
public class BoardingPass {
    private static int serial;
    private int id;
    private Passageiro passageiro;
    private Voo voo;
    private String idAssento;
    private String codigoTicket;
    
    public BoardingPass(Passageiro passageiro, Voo voo, Ticket ticket, VooAssentos assento){
        this.id = ++BoardingPass.serial;
        this.voo = voo;
        this.passageiro = passageiro;
        this.idAssento = assento.getIdAssento();
        this.codigoTicket = ticket.getCodigoTicket();
    }

    public String getCodigoTicket() {
        return codigoTicket;
    }
    
    public Passageiro getPassageiro(){
        return passageiro;
    }
    
    public Voo getVoo(){
        return voo;
    }
    
    public String getIdAssento(){
        return idAssento;
    }
    
    @Override
    public String toString(){
        String boardingPass = "-----------------------------";
        boardingPass += "\nID = " + this.id;
        boardingPass += "\nPassageiro = " + this.passageiro.getNome();
        boardingPass += "\nVoo = " + this.voo.getOrigem() + "-->" + this.voo.getDestino();
        boardingPass += "\nID Assento = " + this.idAssento;
        boardingPass += "\nCodigo Ticket = " + this.codigoTicket;
        boardingPass += "\n-----------------------------";
        
        return boardingPass;
    }
}
