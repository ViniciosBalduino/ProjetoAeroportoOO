/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author vitor
 */
public class TicketDAO {
    Ticket[] tickets = new Ticket[20];
    
    public boolean adicionaTicket(Ticket ticket){
        int posicao = posicaoLivre();
        if(posicao != -1){
            tickets[posicao] = ticket;
            return true;
        } else {
            return false;
        }
    }
    
    public Ticket buscaTicket(String codigoTicket){
        Ticket ticketEncontrado = null;
        for(Ticket ticket: tickets){
            if(ticket.getCodigoTicket() == codigoTicket){
                System.out.println(ticket.toString());
            }
            else{
                System.out.println("Ticket nao encontrado.");
                return null;
            }
        }
        return ticketEncontrado;
    }
    
    public String mostrarTicketsPorPassageiro(int idPassageiro){
        String ticketsPassageiro = "";
        for(Ticket ticket: tickets){
            if(ticket.getId() == idPassageiro)
                ticketsPassageiro += ticket;
        }
        return ticketsPassageiro;
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosTickets = "";
        for(int i=0; i<tickets.length; i++){
            if(tickets[i] != null){
                todosTickets += tickets[i].toString() + "---x----x---x---x---x---x---";
                vazio = false;
            }
        }
        if(vazio){
            todosTickets += "Nenhum ticket foi gerado.";
        } 
        return todosTickets;
    }
    
    private int posicaoLivre(){
        for(int i=0; i<tickets.length; i++){
            if(tickets[i] == null){
                return i;
            }
        }
        return -1;
    }
}
