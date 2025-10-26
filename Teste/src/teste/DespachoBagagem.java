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
public class DespachoBagagem {
    private static int serial;
    private int id;
    private Ticket ticket;
    private String documento;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    
    public DespachoBagagem(Ticket ticket, String documento){
        this.id = ++DespachoBagagem.serial;
        this.documento = documento;
        this.ticket = ticket;
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = LocalDate.now();
    }
    
    public int getId(){
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getDocumento() {
        return documento;
    }
}
