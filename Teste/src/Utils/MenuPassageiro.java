/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DAOS.BoardingPassDAO;
import DAOS.CheckinDAO;
import DAOS.PassageiroDAO;
import DAOS.TicketDAO;
import DAOS.VooAssentosDAO;
import DAOS.VooDAO;
import java.time.LocalDate;
import java.util.Scanner;
import model.BoardingPass;
import model.Checkin;
import model.Passageiro;
import model.Ticket;
import model.Voo;
import model.VooAssentos;
import teste.Util;

/**
 *
 * @author vitor
 */
public class MenuPassageiro {
    private Scanner scanner;
    private VooDAO vooDAO;
    private PassageiroDAO passageiroDAO;
    private VooAssentosDAO vooAssentosDAO;
    private TicketDAO ticketDAO;
    private BoardingPassDAO boardingPassDAO;
    private CheckinDAO checkinDAO;
    
    public MenuPassageiro(Scanner scanner,
            VooDAO vooDAO, PassageiroDAO passageiroDAO,
            VooAssentosDAO vooAssentosDAO, TicketDAO ticketDAO,
            BoardingPassDAO boardingPassDAO, CheckinDAO checkinDAO)
    {
        this.scanner = scanner;
        this.vooDAO = vooDAO;
        this.passageiroDAO = passageiroDAO;
        this.vooAssentosDAO = vooAssentosDAO;
        this.ticketDAO = ticketDAO;
        this.boardingPassDAO = boardingPassDAO;
        this.checkinDAO = checkinDAO;
    }
    
    public int ShowMenuPassageiro() {

        String menu = "";
        menu += "\n=========================================\n";
        menu += "== SEJA BEM VINDO AO MENU DE PASSAGEIRO =";
        menu += "\n=========================================\n";
        menu += "\n1 - Para comprar uma passagem.";
        menu += "\n2 - Listar seu historico de passagens.";
        menu += "\n3 - Fazer check-in.";
        menu += "\n4 - Alterar nome.";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }
    
    public void programaPassageiro() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.ShowMenuPassageiro();
            Passageiro pLogado = Util.getPassageiroLogado();
            switch (opcaoUsuario) {
                case 1: {
                    System.out.println(vooDAO.mostrarTodos());
                    System.out.println("Digite a id do voo dsejado");
                    String idVoo = scanner.nextLine();
                    Voo vooEscolhido = vooDAO.buscarRetornarVooPorID(idVoo);
                    //System.out.println(vooEscolhido);
                    //System.out.println(vooAssentos.mostrarTodosAssentosPorVoo(vooEscolhido));
                    if (vooAssentosDAO.contarAssentosPorVoo(idVoo) >= vooEscolhido.getCapacidade()) {
                        System.out.println("\nVoo sem assentos disponiveis, por favor busque outro.");
                    } else {
                        VooAssentos assentoNovo = new VooAssentos(vooEscolhido, pLogado);
                        vooAssentosDAO.adicionaVooAssentos(assentoNovo);
                        Ticket novoTicket = new Ticket(pLogado, vooEscolhido, assentoNovo);
                        ticketDAO.adicionaTicket(novoTicket);
                        System.out.println("PASSAGEM COMPRADA COM SUCESSO.");
                    }
                    break;
                }

                case 2: {
                    System.out.println("2 - Listar seu historico de passagens");
                    //System.out.println(pLogado.getId());
                    //System.out.println(tickets.mostrarTodos());
                    System.out.println(ticketDAO.mostrarTicketsPorPassageiro(pLogado.getId()));
                    break;
                }
                case 3: {
                    //Faz Check-In
                    System.out.println("Esses sao seus tickets disponiveis para check-in: ");
                    System.out.println(ticketDAO.mostrarTicketsPorPassageiro(pLogado.getId()));
                    if (ticketDAO.mostrarTicketsPorPassageiro(pLogado.getId()).equals("")) {
                        System.out.println("\nNao ha tickets disponiveis para fazer check-in.");
                        break;
                    }
                    System.out.println("Digite o codigo do ticket escolhido: ");
                    String codigoTicket = scanner.nextLine();
                    Ticket ticketEscolhido = ticketDAO.buscaTicket(codigoTicket);
                    if (ticketEscolhido != null) {
                        VooAssentos assentoCheckIn = vooAssentosDAO.buscarAssentoPorVooEPassageiro(ticketEscolhido.getVoo().getId(), pLogado.getId());
                        LocalDate hoje = LocalDate.now();
                        LocalDate diaVoo = ticketEscolhido.getVoo().getData();
                        if (diaVoo.equals(hoje) || diaVoo.equals(hoje.plusDays(1))) {
                            Checkin novoCheckin = new Checkin(ticketEscolhido, pLogado.getDocumento());
                            checkinDAO.adicionaCheckin(novoCheckin);
                            BoardingPass novoBoardingPass = new BoardingPass();
                            novoBoardingPass.setNomePassageiro(pLogado.getNome());
                            novoBoardingPass.setIdVoo(ticketEscolhido.getVoo().getId());
                            novoBoardingPass.setIdTicket(ticketEscolhido.getCodigoTicket());
                            novoBoardingPass.setIdAssento(assentoCheckIn.getIdAssento());
                            novoBoardingPass.setNumeroAssento(assentoCheckIn.getValor());
                            boardingPassDAO.adicionaBoardingPass(novoBoardingPass);
                            System.out.println("Check-in realizado! Seu cartao de embarque:\n" + novoBoardingPass.toString());
                            ticketDAO.removeTicketPosCheckIn(ticketEscolhido.getCodigoTicket());
                            ticketEscolhido = null;
                            //System.out.println("mostrando chckins");
                            //System.out.println(checkins.mostrarTodos());
                        } else {
                            System.out.println("Nao e possivel fazer check-in para este voo mais. Desculpe.");
                        }
                    } else {
                        System.out.println("Ticket invalido, busque o suporte.");
                    }

                    break;
                }

                case 4: {
                    System.out.println("5 - Alterar nome.");
                    System.out.println("Digite o novo nome: ");
                    pLogado.setNome(scanner.nextLine());
                    System.out.println("Nome alterado com sucesso.");
                    System.out.println(pLogado);
                    break;
                }

                case 9:
                    Util.setPassageiroLogado(null);
                    System.out.println("saindo do menu de passageiro.");
                    break;
                default:
                    System.out.println("escola uma opcao valida");
                    break;
            }

        }
    }
}
