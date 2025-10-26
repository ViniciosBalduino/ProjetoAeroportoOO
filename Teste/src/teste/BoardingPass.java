/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author vinic
 */
public class BoardingPass {
    private static int serial;
    private int id;
    private Passageiro passageiro;
    private Voo voo;
    private String codigoAssento;
    private String codigoTicket;
    
    public BoardingPass(Passageiro passageiro, Voo voo, Ticket ticket, VooAssentos assento){
        this.id = BoardingPass.serial++;
        this.voo = voo;
        this.passageiro = passageiro;
        this.codigoAssento = assento.getIdAssento();
        this.codigoTicket = ticket.getCodigoTicket();
    }
    
    @Override
    public String toString(){
        String boardingPass = "-----------------------------";
        boardingPass += "\nID = " + this.id;
        boardingPass += "\nPassageiro = " + this.passageiro.getNome();
        boardingPass += "\nVoo = " + this.voo.getOrigem() + "-->" + this.voo.getDestino();
        boardingPass += "\nCodigo Assento = " + this.codigoAssento;
        boardingPass += "\nCodigo Ticket = " + this.codigoTicket;
        boardingPass += "-----------------------------";
        
        return boardingPass;
    }
}
