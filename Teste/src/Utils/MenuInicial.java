/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DAOS.AdministradorDAO;
import DAOS.BoardingPassDAO;
import DAOS.CheckinDAO;
import DAOS.FuncionarioDAO;
import DAOS.PassageiroDAO;
import DAOS.TicketDAO;
import DAOS.VooAssentosDAO;
import DAOS.VooDAO;
import java.time.LocalDate;
import java.util.Scanner;
import model.Administrador;
import model.Checkin;
import model.Funcionario;
import model.Passageiro;
import model.Ticket;
import model.Voo;
import model.VooAssentos;
import static teste.Teste.clearScreen;
import teste.Util;

/**
 *
 * @author vitor
 */
public class MenuInicial {
    
    private Scanner scanner;
    private MenuAdministrador menuADM;
    private AdministradorDAO admDAO;
    private MenuFuncionario menuFUNC;
    private MenuPassageiro menuPASS;
    private PassageiroDAO passDAO;
    private CheckinDAO checkDAO;
    private BoardingPassDAO boardingDAO;
    private FuncionarioDAO funcDAO;
    private VooAssentosDAO assentosDAO;
    private TicketDAO ticketDAO;
    private VooDAO voos;
    
    public MenuInicial(Scanner scanner, MenuAdministrador menuADM, AdministradorDAO admDAO,
            MenuFuncionario menuFUNC, MenuPassageiro menuPASS, PassageiroDAO passDAO, VooDAO voos,
            CheckinDAO checkDAO, BoardingPassDAO boardingDAO, FuncionarioDAO funcDAO,
            VooAssentosDAO vooAssentosDAO, TicketDAO ticketDAO, CheckinDAO checkinDAO){
        this.scanner = scanner;
        this.menuADM = menuADM;
        this.admDAO = admDAO;
        this.menuFUNC = menuFUNC;
        this.menuPASS = menuPASS;
        this.passDAO = passDAO;
        this.checkDAO = checkDAO;
        this.boardingDAO = boardingDAO;
        this.funcDAO = funcDAO;
        this.ticketDAO = ticketDAO;
        this.assentosDAO = vooAssentosDAO;
        this.voos = voos;
    }
    
    public int ShowMenuInicial() {

        String menu = "";
        menu += "\n=========================================\n";
        menu += "====== SEJA BEM VINDO AO AEROPORTO ======";
        menu += "\n=========================================\n";
        menu += "\n1 - Entrar como passageiro.";
        menu += "\n2 - Entrar como funcionario.";
        menu += "\n3 - Entrar como administrador.";
        menu += "\n4 - Fazer seu cadastro como passageiro.";
        menu += "\n6 - Procurar voos.";
        menu += "\n7 - Ver todos os voos.";
        menu += "\n8 - Conferir reserva.";
        menu += "\n9 - Sair do sistema.\n";
        menu += "\nQual sua opcao? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }
    
    public void programaInicial(){
        int opcaoUsuario = 0;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.ShowMenuInicial();
            switch (opcaoUsuario) {
                case 1:
                    System.out.println("\n---- Logando como Passageiro ----");
                    System.out.println("Login: ");
                    String login = scanner.nextLine();
                    System.out.println("Senha:");
                    String senha = scanner.nextLine();
                    Passageiro passageiroLogado = passDAO.buscarLoginPassageiro(login, senha);

                    if (passageiroLogado != null) {
                        System.out.println("\n----- Passageiro logado -----\n");
                        Util.setPassageiroLogado(passageiroLogado);
                        System.out.println("Passageiro logado e: " + Util.getPassageiroLogado().toString());
                        menuPASS.ShowMenuPassageiro();
                    } else {
                        System.out.println("\nLogin Invalido. Tente novamente.");
                    }
                    break;
                case 2: {
                    System.out.println("\n---- Logando como Funcionario ----");
                    System.out.println("Login: ");
                    String loginFunc = scanner.nextLine();
                    System.out.println("Senha:");
                    String senhaFunc = scanner.nextLine();
                    Funcionario funcionarioLogado = funcDAO.buscarLoginFuncionario(loginFunc, senhaFunc);

                    if (funcionarioLogado != null) {
                        System.out.println("\n---- Funcionario logado ----");
                        Util.setFuncionarioLogado(funcionarioLogado);
                        System.out.println("Funcionario logado e: " + Util.getFuncionarioLogado().toString());
                        menuFUNC.ShowMenuFuncionario();
                    } else {
                        System.out.println("\nLogin invalido. Tente novamente.");
                    }
                    break;
                }
                case 3:
                    System.out.println("\n---- Logando como admnistrador ----");
                    System.out.println("Login: ");
                    String loginAdm = scanner.nextLine();
                    System.out.println("Senha:");
                    String senhaAdm = scanner.nextLine();
                    Administrador administradorLogado = admDAO.buscarLoginAdministrador(loginAdm, senhaAdm);

                    if (administradorLogado != null) {
                        System.out.println("\n----- Administrador logado -----\n");
                        Util.setAdministradorLogado(administradorLogado);
                        System.out.println("Administrador logado e: " + Util.getAdministradorLogado().toString());
                        menuADM.ShowMenuAdministrador();
                    } else {
                        System.out.println("\nLogin Invalido. Tente novamente.");
                    }
                    break;

                case 4:
                    System.out.println("\n---- Fazendo seu cadastro ----");
                    Passageiro novoPassageiro = new Passageiro();
                    System.out.println("Digite seu nome: ");
                    novoPassageiro.setNome(scanner.nextLine());

                    System.out.println("Digite sua data Nascimento: (YYYY-MM-DD)");
                    novoPassageiro.setNascimento(LocalDate.parse(scanner.nextLine()));

                    System.out.println("Digite seu documento: ");
                    novoPassageiro.setDocumento(scanner.nextLine());

                    System.out.println("Cadastre seu login: ");
                    novoPassageiro.setLogin(scanner.nextLine());

                    System.out.println("Cadastre sua senha: ");
                    novoPassageiro.setSenha(scanner.nextLine());

                    if (passDAO.adicionaPassageiro(novoPassageiro)) {
                        System.out.println("\nPassageiro Cadastrado com sucesso");
                    } else {
                        System.out.println("\nInfelizmente não comportamos mais nenhum usuário. Outro dia voce volta.");
                    }
                    break;

                case 6: {
                    System.out.println("\n---- Procurando Voos ----");
                    //System.out.println(voos.mostrarTodos());
                    int opcaoBuscarVoo = 0;
                    while (opcaoBuscarVoo != 9) {
                        opcaoBuscarVoo = menuBuscarVoo();
                        switch (opcaoBuscarVoo) {
                            case 1: {
                                System.out.println("Buscar voo por destino\n");
                                System.out.println("Qual destino desejado? ");
                                Voo vooPretendido = voos.buscarVooPorDestinoString(scanner.nextLine());
                                if (vooPretendido != null) {
                                    System.out.println(vooPretendido.toString());
                                    if (assentosDAO.contarAssentosPorVoo(vooPretendido.getId()) > vooPretendido.getCapacidade()) {
                                        System.out.println("\nVoo sem assentos disponiveis, por favor busque outro.");
                                        break;
                                    }
                                    System.out.println("\nDeseja comprar a passagem?");
                                    System.out.println("Digite 1 para sim");
                                    System.out.println("Digite 2 para nao");
                                    int comprarPassagem = Integer.parseInt(scanner.nextLine());
                                    if (comprarPassagem == 1) {
                                        System.out.println("\nPassagem adquirida, faca login ou seu cadastro");
                                        System.out.println("Digite 1 para login");
                                        System.out.println("Digite 2 para cadastro");
                                        int formaLogin = Integer.parseInt(scanner.nextLine());
                                        while (formaLogin != 1 && formaLogin != 2) {
                                            System.out.println("\nEscolha uma opcao valida ");
                                            System.out.println("Digite 1 para login");
                                            System.out.println("Digite 2 para cadastro");
                                            formaLogin = Integer.parseInt(scanner.nextLine());
                                        }
                                        switch (formaLogin) {
                                            case 1:
                                                System.out.println("\n---- Logando como usuario ----");
                                                System.out.println("Login: ");
                                                String novoLogin = scanner.nextLine();
                                                System.out.println("Senha:");
                                                String novaSenha = scanner.nextLine();
                                                Passageiro novoPassageiroLogado = passDAO.buscarLoginPassageiro(novoLogin, novaSenha);
                                                while (novoPassageiroLogado == null) {
                                                    System.out.println("\nLogin Invalido. Tente novamente");
                                                    System.out.println("Login: ");
                                                    novoLogin = scanner.nextLine();
                                                    System.out.println("Senha:");
                                                    novaSenha = scanner.nextLine();
                                                    novoPassageiroLogado = passDAO.buscarLoginPassageiro(novoLogin, novaSenha);
                                                }
                                                if (novoPassageiroLogado != null) {
                                                    VooAssentos novoAssento = new VooAssentos(vooPretendido, novoPassageiroLogado);
                                                    assentosDAO.adicionaVooAssentos(novoAssento);
                                                    Ticket novoTicket = new Ticket(novoPassageiroLogado, vooPretendido, novoAssento);
                                                    ticketDAO.adicionaTicket(novoTicket);
                                                    System.out.println("\nPassagem adquirida com sucesso, a passagem ja se encontra na sua conta\n");
                                                } else {
                                                    System.out.println("Voce burlou alguma regra e quebrou o codigo, como?");
                                                }
                                                break;
                                            case 2:
                                                System.out.println("\n---- Fazendo seu cadastro ----");
                                                Passageiro novoPassageiroCompra = new Passageiro();
                                                System.out.println("Digite seu nome: ");
                                                novoPassageiroCompra.setNome(scanner.nextLine());

                                                System.out.println("Digite sua data Nascimento: (YYYY-MM-DD)");
                                                novoPassageiroCompra.setNascimento(LocalDate.parse(scanner.nextLine()));

                                                System.out.println("Digite seu documento: ");
                                                novoPassageiroCompra.setDocumento(scanner.nextLine());

                                                System.out.println("Cadastre seu login: ");
                                                novoPassageiroCompra.setLogin(scanner.nextLine());

                                                System.out.println("Cadastre sua senha: ");
                                                novoPassageiroCompra.setSenha(scanner.nextLine());

                                                if (passDAO.adicionaPassageiro(novoPassageiroCompra)) {
                                                    System.out.println("\nPassageiro Cadastrado com sucesso");
                                                    System.out.println("\nPassagem adquirida com sucesso, a passagem ja se encontra na sua conta\n");

                                                } else {
                                                    System.out.println("\nInfelizmente não comportamos mais nenhum usuário. Outro dia voce volta.");
                                                }
                                                break;
                                        }
                                    } else if (comprarPassagem == 2) {
                                        System.out.println("Por que voce quer ver passagens entao? Babaca!");
                                    }
                                } else {
                                    System.out.println("Voo nao encontrado!");
                                }
                                break;
                            }
                            case 2: {
                                System.out.println("Buscar voo por partida");
                                System.out.println("De onde vai sair? ");
                                Voo vooPretendido = voos.buscarVooPorPartidaString(scanner.nextLine());
                                if (vooPretendido != null) {
                                    System.out.println(vooPretendido.toString());
                                    System.out.println("\nDeseja comprar a passagem?");
                                    System.out.println("Digite 1 para sim");
                                    System.out.println("Digite 2 para nao");
                                    int comprarPassagem = Integer.parseInt(scanner.nextLine());
                                    if (comprarPassagem == 1) {
                                        System.out.println("\nPassagem adquirida, faca login ou seu cadastro");
                                        System.out.println("Digite 1 para login");
                                        System.out.println("Digite 2 para cadastro");
                                        int formaLogin = Integer.parseInt(scanner.nextLine());
                                        while (formaLogin != 1 && formaLogin != 2) {
                                            System.out.println("\nEscolha uma opcao valida ");
                                            System.out.println("Digite 1 para login");
                                            System.out.println("Digite 2 para cadastro");
                                            formaLogin = Integer.parseInt(scanner.nextLine());
                                        }
                                        switch (formaLogin) {
                                            case 1:
                                                System.out.println("\n---- Logando como usuario ----");
                                                System.out.println("Login: ");
                                                String novoLogin = scanner.nextLine();
                                                System.out.println("Senha:");
                                                String novaSenha = scanner.nextLine();
                                                Passageiro novoPassageiroLogado = passDAO.buscarLoginPassageiro(novoLogin, novaSenha);
                                                while (novoPassageiroLogado == null) {
                                                    System.out.println("\nLogin Invalido. Tente novamente");
                                                    System.out.println("Login: ");
                                                    novoLogin = scanner.nextLine();
                                                    System.out.println("Senha:");
                                                    novaSenha = scanner.nextLine();
                                                    novoPassageiroLogado = passDAO.buscarLoginPassageiro(novoLogin, novaSenha);
                                                }
                                                if (novoPassageiroLogado != null) {
                                                    System.out.println("\nPassagem adquirida com sucesso, a passagem ja se encontra na sua conta\n");
                                                } else {
                                                    System.out.println("Voce burlou alguma regra e quebrou o codigo, como?");
                                                }
                                                break;
                                            case 2:
                                                System.out.println("\n---- Fazendo seu cadastro ----");
                                                Passageiro novoPassageiroCompra = new Passageiro();
                                                System.out.println("Digite seu nome: ");
                                                novoPassageiroCompra.setNome(scanner.nextLine());

                                                System.out.println("Digite sua data Nascimento: (YYYY-MM-DD)");
                                                novoPassageiroCompra.setNascimento(LocalDate.parse(scanner.nextLine()));

                                                System.out.println("Digite seu documento: ");
                                                novoPassageiroCompra.setDocumento(scanner.nextLine());

                                                System.out.println("Cadastre seu login: ");
                                                novoPassageiroCompra.setLogin(scanner.nextLine());

                                                System.out.println("Cadastre sua senha: ");
                                                novoPassageiroCompra.setSenha(scanner.nextLine());

                                                if (passDAO.adicionaPassageiro(novoPassageiroCompra)) {
                                                    System.out.println("\nPassageiro Cadastrado com sucesso");
                                                    System.out.println("\nPassagem adquirida com sucesso, a passagem ja se encontra na sua conta\n");
                                                } else {
                                                    System.out.println("\nInfelizmente não comportamos mais nenhum usuário. Outro dia voce volta.");
                                                }
                                                break;
                                        }
                                    } else if (comprarPassagem == 2) {
                                        System.out.println("Por que voce quer ver passagens entao? Babaca!");
                                    }
                                } else {
                                    System.out.println("Voo nao encontrado!");
                                }
                                break;
                            }
                            case 3:
                                System.out.println("Buscar voo por data.");
                                System.out.println("Qual data pretendida: ");
                                Voo vooPretendido = voos.buscarVooPorData(scanner.nextLine());
                                if (vooPretendido != null) {
                                    System.out.println(vooPretendido.toString());
                                    System.out.println("\nDeseja comprar a passagem?");
                                    System.out.println("Digite 1 para sim");
                                    System.out.println("Digite 2 para nao");
                                    int comprarPassagem = Integer.parseInt(scanner.nextLine());
                                    if (comprarPassagem == 1) {
                                        System.out.println("\nPassagem adquirida, faca login ou seu cadastro");
                                        System.out.println("Digite 1 para login");
                                        System.out.println("Digite 2 para cadastro");
                                        int formaLogin = Integer.parseInt(scanner.nextLine());
                                        while (formaLogin != 1 && formaLogin != 2) {
                                            System.out.println("\nEscolha uma opcao valida ");
                                            System.out.println("Digite 1 para login");
                                            System.out.println("Digite 2 para cadastro");
                                            formaLogin = Integer.parseInt(scanner.nextLine());
                                        }
                                        switch (formaLogin) {
                                            case 1:
                                                System.out.println("\n---- Logando como usuario ----");
                                                System.out.println("Login: ");
                                                String novoLogin = scanner.nextLine();
                                                System.out.println("Senha:");
                                                String novaSenha = scanner.nextLine();
                                                Passageiro novoPassageiroLogado = passDAO.buscarLoginPassageiro(novoLogin, novaSenha);
                                                while (novoPassageiroLogado == null) {
                                                    System.out.println("\nLogin Invalido. Tente novamente");
                                                    System.out.println("Login: ");
                                                    novoLogin = scanner.nextLine();
                                                    System.out.println("Senha:");
                                                    novaSenha = scanner.nextLine();
                                                    novoPassageiroLogado = passDAO.buscarLoginPassageiro(novoLogin, novaSenha);
                                                }
                                                if (novoPassageiroLogado != null) {
                                                    System.out.println("\nPassagem adquirida com sucesso, a passagem ja se encontra na sua conta\n");
                                                } else {
                                                    System.out.println("Voce burlou alguma regra e quebrou o codigo, como?");
                                                }
                                                break;
                                            case 2:
                                                System.out.println("\n---- Fazendo seu cadastro ----");
                                                Passageiro novoPassageiroCompra = new Passageiro();
                                                System.out.println("Digite seu nome: ");
                                                novoPassageiroCompra.setNome(scanner.nextLine());

                                                System.out.println("Digite sua data Nascimento: (YYYY-MM-DD)");
                                                novoPassageiroCompra.setNascimento(LocalDate.parse(scanner.nextLine()));

                                                System.out.println("Digite seu documento: ");
                                                novoPassageiroCompra.setDocumento(scanner.nextLine());

                                                System.out.println("Cadastre seu login: ");
                                                novoPassageiroCompra.setLogin(scanner.nextLine());

                                                System.out.println("Cadastre sua senha: ");
                                                novoPassageiroCompra.setSenha(scanner.nextLine());

                                                if (passDAO.adicionaPassageiro(novoPassageiroCompra)) {
                                                    System.out.println("\nPassageiro Cadastrado com sucesso");
                                                    System.out.println("\nPassagem adquirida com sucesso, a passagem ja se encontra na sua conta\n");
                                                } else {
                                                    System.out.println("\nInfelizmente não comportamos mais nenhum usuário. Outro dia voce volta.");
                                                }
                                                break;
                                        }
                                    } else if (comprarPassagem == 2) {
                                        System.out.println("Por que voce quer ver passagens entao? Babaca!");
                                    }
                                } else {
                                    System.out.println("Voo nao encontrado!");
                                }
                                break;
                            case 9:
                                System.out.println("Cancelando a busca");
                                break;
                            default:
                                System.out.println("\nEscolha uma opcao valida");
                                break;
                        }
                    }
                    break;
                }
                case 7: {
                    clearScreen();
                    System.out.println("\n----- TODOS OS VOOS DISPONIVEIS -----\n");
                    String todosVoos = voos.mostrarTodos();
                    System.out.println(todosVoos);
                    break;
                }
                case 8:
                    System.out.println("Insira o codigo do ticket: ");
                    String codigoTicket = scanner.nextLine();
                    Ticket buscaTicket = ticketDAO.buscaTicket(codigoTicket);
                    if(buscaTicket == null){
                        System.out.println("Buscando cartoes de embarque");{
                        Checkin buscaCheckin = checkDAO.retornaCheckInIDTicket(codigoTicket);
                        if(buscaCheckin != null){
                            System.out.println(buscaCheckin.toString());
                            System.out.println(buscaCheckin.getEstado());
                        } else{
                            System.out.println("Nenhum ticket encontrado");
                        }
                        
                    }
                    }
                case 9:
                    System.out.println("Cancelando a interacao");
                    break;
                default:
                    System.out.println("\nEscolha uma opcao valida");
                    break;
            }
        }
        System.out.println("Saindo do sistema");
    }
    
    private int menuBuscarVoo() {

        String menu = "";
        menu += "\n=========================================\n";
        menu += "====== SEJA BEM AO MENU DE BUSCA DE VOO ======";
        menu += "\n=========================================\n";
        menu += "\n1 - Buscar voo por destino.";
        menu += "\n2 - Buscar voo por partida.";
        menu += "\n3 - Buscar voo por data.";
        menu += "\n9 - Sair do sistema.\n";
        menu += "\nQual sua opcao? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }
}
