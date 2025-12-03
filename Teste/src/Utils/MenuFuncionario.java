/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DAOS.BoardingPassDAO;
import DAOS.CheckinDAO;
import DAOS.DespachoBagagemDAO;
import DAOS.PassageiroDAO;
import DAOS.TicketDAO;
import DAOS.VooAssentosDAO;
import DAOS.VooDAO;
import java.util.Scanner;
import model.BoardingPass;
import model.Checkin;
import model.DespachoBagagem;
import model.Passageiro;
import model.Ticket;
import teste.Util;

/**
 *
 * @author vitor
 */
public class MenuFuncionario {

    private Scanner scanner;
    private VooDAO vooDAO;
    private PassageiroDAO passageiroDAO;
    private VooAssentosDAO vooAssentosDAO;
    private TicketDAO ticketDAO;
    private BoardingPassDAO boardingPassDAO;
    private CheckinDAO checkinDAO;
    private DespachoBagagemDAO despachosDAO;

    public MenuFuncionario(
            Scanner scanner, VooDAO vooDAO, PassageiroDAO passageiroDAO,
            VooAssentosDAO vooAssentosDAO, TicketDAO ticketDAO,
            BoardingPassDAO boardingPassDAO, CheckinDAO checkinDAO, DespachoBagagemDAO despachosDAO) {
        this.scanner = scanner;
        this.vooDAO = vooDAO;
        this.passageiroDAO = passageiroDAO;
        this.vooAssentosDAO = vooAssentosDAO;
        this.ticketDAO = ticketDAO;
        this.boardingPassDAO = boardingPassDAO;
        this.checkinDAO = checkinDAO;
        this.despachosDAO = despachosDAO;
    }

    public int ShowMenuFuncionario() {

        String menu = "";
        menu += "\n=========================================\n";
        menu += "== SEJA BEM VINDO AO MENU DE FUNCIONARIO =";
        menu += "\n=========================================\n";
        menu += "\n1 - Registrar entrada no aeroporto.";
        menu += "\n2 - Registrar entrada no aviao.";
        menu += "\n3 - Despachar bagagem";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.println(menu);

        return Integer.parseInt(scanner.nextLine());
    }

    public void programaFuncionario() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.ShowMenuFuncionario();
            switch (opcaoUsuario) {
                case 1: {
                    System.out.println("\n--- Registrar Entrada no Aeroporto ---");
                    System.out.println("Digite o codigo do Ticket do passageiro:");
                    String codTicketPortao = scanner.nextLine();

                    //System.out.println(boardingPasses.mostrarTodos());
                    //BoardingPass ticketPortao = boardingPasses.buscarPorIDTicket(codTicketPortao);
                    //Ticket ticketPortao = tickets.buscaTicket(codTicketPortao);
                    Checkin ticketPortao = checkinDAO.retornaCheckInIDTicket(codTicketPortao);

                    if (ticketPortao != null) {
                        /*BoardingPass boardingPass = boardingPassDAO.buscarPorPassageiroEVoo(
                                passageiroDAO.buscarPassageiroPorDocumento(ticketPortao.getDocumento()).getNome());
                        ticketPortao.getTicket().getVoo().getId()
                        );*/
                        
                        BoardingPass boardingPass = boardingPassDAO.buscarPorIDTicket(codTicketPortao);

                        if (boardingPass != null) {
                            System.out.println("ENTRADA AUTORIZADA.");
                            System.out.println(boardingPass.toString());
                            ticketPortao.setEstado("No aeroporto");
                        } else {
                            System.out.println("ENTRADA NEGADA. Passageiro nao fez check-in.");
                        }
                    } else {
                        System.out.println("Ticket nao encontrado.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("\n--- Registrar Embarque (Entrada no Aviao) ---");
                    System.out.println("Digite o codigo do Ticket do passageiro:");
                    String codTicketAviao = scanner.nextLine();

                    //Ticket ticketAviao = tickets.buscaTicket(codTicketAviao);
                    //System.out.println(boardingPasses.mostrarTodos());
                    //BoardingPass ticketAviao = boardingPasses.buscarPorIDTicket(codTicketAviao);
                    Checkin ticketAviao = checkinDAO.retornaCheckInIDTicket(codTicketAviao);

                    if (ticketAviao != null) {
                        /*BoardingPass boardingPass = boardingPassDAO.buscarPorPassageiroEVoo(
                                ticketAviao.getTicket().getPassageiro().getNome(),
                                ticketAviao.getTicket().getVoo().getId()
                        );*/
                        
                        BoardingPass boardingPass = boardingPassDAO.buscarPorIDTicket(codTicketAviao);

                        if (boardingPass != null && ticketAviao.getEstado() == "No aeroporto") {
                            System.out.println("EMBARQUE AUTORIZADO. Bom voo!");
                            System.out.println("Passageiro: " + boardingPass.getNomePassageiro());
                            vooAssentosDAO.buscarvooAssentosPorID(boardingPass.getIdAssento());
                            System.out.println("Assento: " + vooAssentosDAO.buscarvooAssentosPorID(boardingPass.getIdAssento()));
                            ticketAviao.setEstado("Embarcado");
                        } else {
                            if (ticketAviao.getEstado() != "No aeroporto") {
                                System.out.println("EMBARQUE NEGADO. Passageiro nao passou pelo portao.");
                            } else {
                                System.out.println("EMBARQUE NEGADO. Passageiro nao fez check-in ou nao passou pelo portao.");
                            }
                        }
                    } else {
                        System.out.println("Ticket nao encontrado.");
                    }
                    break;
                }
                case 3:
                    System.out.println("\n--- Despachar Bagagem ---");
                    System.out.println("Digite o codigo do Ticket do passageiro:");
                    String codigoTicket = scanner.nextLine();

                    //Ticket ticketEscolhido = tickets.buscaTicket(codigoTicket);
                    Checkin ticketEscolhido = checkinDAO.retornaCheckInIDTicket(codigoTicket);
                    Ticket tkEscolhido = ticketDAO.buscaTicket(ticketEscolhido.getIdTicket());

                    if (ticketEscolhido != null) {
                        //Passageiro p = ticketEscolhido.getTicket().getPassageiro();
                        System.out.println("Ticket encontrado:");
                        System.out.println("Passageiro: " + tkEscolhido.getNomePassageiro());
                        System.out.println("Voo: " + tkEscolhido.getIdVoo());
                        System.out.println("Confirmar despacho? (1-Sim / 2-Nao)");

                        int conf = Integer.parseInt(scanner.nextLine());
                        if (conf == 1) {
                            DespachoBagagem novoDespacho = new DespachoBagagem();
                            novoDespacho.setTicket(codigoTicket);
                            if (despachosDAO.adicionaDespachoBagagem(novoDespacho)) {
                                System.out.println("Bagagem despachada com sucesso. ID do Despacho: " + novoDespacho.getId());
                            } else {
                                System.out.println("Erro: Nao ha mais espaco para despachos.");
                            }
                        } else {
                            System.out.println("Despacho cancelado.");
                        }
                    } else {
                        System.out.println("Ticket nao encontrado.");
                    }
                    break;
                case 9:
                    Util.setFuncionarioLogado(null);
                    System.out.println("Saindo do menu de funcionario.");
                    break;
                default:
                    System.out.println("Escolha uma opcao valida.");
                    break;
            }
        }

    }

}
