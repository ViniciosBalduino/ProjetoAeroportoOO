/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author vitor
 */
public class TicketDAO {

    Ticket[] tickets = new Ticket[20];

    public TicketDAO(VooDAO voos, PassageiroDAO passageiros, VooAssentosDAO vooAssentos) {
        Ticket tk1 = new Ticket(passageiros.buscarRetornarPassageiroPorID(1), voos.buscarRetornarVooPorID("Gal-V1"), vooAssentos.buscarAssentoPorVooEPassageiro("Gal-V1", 1));
        this.adicionaTicket(tk1);

        Ticket tk2 = new Ticket(passageiros.buscarRetornarPassageiroPorID(2), voos.buscarRetornarVooPorID("Gal-V1"), vooAssentos.buscarAssentoPorVooEPassageiro("Gal-V1", 2));
        this.adicionaTicket(tk2);

        Ticket tk3 = new Ticket(passageiros.buscarRetornarPassageiroPorID(3), voos.buscarRetornarVooPorID("Gal 2-V2"), vooAssentos.buscarAssentoPorVooEPassageiro("Gal 2-V2", 3));
        this.adicionaTicket(tk3);

    }

    public boolean adicionaTicket(Ticket ticket) {
        int posicao = posicaoLivre();
        if (posicao != -1) {
            tickets[posicao] = ticket;
            return true;
        } else {
            return false;
        }
    }

    public Ticket buscaTicket(String codigoTicket) {
        for (Ticket ticket : tickets) {
            //System.out.println(ticket.getCodigoTicket());
            if (ticket.getCodigoTicket().toLowerCase().trim().equals(codigoTicket.toLowerCase().trim())) {
                System.out.println("ticket encontrado");
                return ticket;
            } 
        }
        System.out.println("Ticket nao encontrado.");
        return null;
    }
    
    public Ticket buscaTicketPassageiroVoo(int codigoPassageiro, String codigoVoo) {
        //Ticket ticketEncontrado = null;
        for (Ticket ticket : tickets) {
            if (ticket.getPassageiro().getId() == codigoPassageiro && ticket.getVoo().getId().toLowerCase().equals(codigoVoo)) {
                System.out.println("Ticket encontrado");
                return ticket;
            } else {
                System.out.println("Ticket nao encontrado.");
                return null;
            }
        }
        return null;
    }

    public String mostrarTicketsPorPassageiro(int idPassageiro) {
        String ticketsPassageiro = "";
        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getPassageiro().getId() == idPassageiro) {
                ticketsPassageiro += ticket;
            }
        }
        return ticketsPassageiro;
    }

    public String mostrarTicketsValidosPorPassageiro(int idPassageiro) {
        String ticketsPassageiro = "";
        LocalDate dataAtual = LocalDate.now();
        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getPassageiro().getId() == idPassageiro && ticket.getVoo().getData().isAfter(dataAtual)) {
                ticketsPassageiro += ticket;
            }
        }
        return ticketsPassageiro;
    }

    public String mostrarTodos() {
        boolean vazio = true;
        String todosTickets = "";
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null) {
                todosTickets += tickets[i].toString() + "\n---x----x---x---x---x---x---\n";
                vazio = false;
            }
        }
        if (vazio) {
            todosTickets += "Nenhum ticket foi gerado.";
        }
        return todosTickets;
    }

    private int posicaoLivre() {
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
