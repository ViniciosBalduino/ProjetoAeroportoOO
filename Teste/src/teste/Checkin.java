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
public class Checkin {
    private static int serial;
    private int id;
    private Ticket ticket;
    private String documento;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    
    public Checkin(Ticket ticket, String documento){
        this.id = Checkin.serial++;
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.dataModificacao = LocalDate.now();

    }

    public String getDocumento() {
        return documento;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }
    
    @Override
    public String toString(){
        String checkin = "";
        checkin += "\nID = " + this.id;
        checkin += "\nDocumento = " + this.documento;
        checkin += "\nTicket: " + this.ticket;
        return checkin;
    }
}
