/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teste;

import java.time.LocalDate;
import java.util.Scanner;

public class Teste {

    AeroportoDAO aeroportos = new AeroportoDAO();
    AdministradorDAO administradores = new AdministradorDAO();
    FuncionarioDAO funcionarios = new FuncionarioDAO();
    CompAereaDAO companhias = new CompAereaDAO();
    VooDAO voos = new VooDAO(companhias);
    PassageiroDAO passageiros = new PassageiroDAO();
    VooAssentosDAO vooAssentos = new VooAssentosDAO(voos, passageiros);
    TicketDAO tickets = new TicketDAO();
    CheckinDAO checkins = new CheckinDAO();
    DespachoBagagemDAO despachos = new DespachoBagagemDAO();
    BoardingPassDAO boardingPasses = new BoardingPassDAO();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //CompAereaDAO companhias = new CompAereaDAO();
        //VooDAO voos = new VooDAO(companhias);
        //System.out.println(voos.mostrarTodos());
        //voos.mostrarTodos();
        new Teste();

    }

    public Teste() {

        int opcaoUsuario = 0;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.menuInicial();
            switch (opcaoUsuario) {
                case 1:
                    System.out.println("\n---- Logando como Passageiro ----");
                    System.out.println("Login: ");
                    String login = scanner.nextLine();
                    System.out.println("Senha:");
                    String senha = scanner.nextLine();
                    Passageiro passageiroLogado = passageiros.buscarLoginPassageiro(login, senha);

                    if (passageiroLogado != null) {
                        System.out.println("\n----- Passageiro logado -----\n");
                        Util.setPassageiroLogado(passageiroLogado);
                        System.out.println("Passageiro logado e: " + Util.getPassageiroLogado().toString());
                        programaPassageiro();
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
                    Funcionario funcionarioLogado = funcionarios.buscarLoginFuncionario(loginFunc, senhaFunc);
                    
                    if(funcionarioLogado != null){
                        System.out.println("\n---- Funcionario logado ----");
                        Util.setFuncionarioLogado(funcionarioLogado);
                        System.out.println("Funcionario logado e: " + Util.getFuncionarioLogado().toString());
                        programaFuncionario();
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
                    Administrador administradorLogado = administradores.buscarLoginAdministrador(loginAdm, senhaAdm);

                    if (administradorLogado != null) {
                        System.out.println("\n----- Administrador logado -----\n");
                        Util.setAdministradorLogado(administradorLogado);
                        System.out.println("Administrador logado e: " + Util.getAdministradorLogado().toString());
                        programaAdministrador();
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

                    if (passageiros.adicionaPassageiro(novoPassageiro)) {
                        System.out.println("\nPassageiro Cadastrado com sucesso");
                    } else {
                        System.out.println("\nInfelizmente não comportamos mais nenhum usuário. Outro dia voce volta.");
                    }
                    break;
                
                case 6:{
                    System.out.println("\n---- Procurando Voos ----");
                    System.out.println(voos.mostrarTodos());
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
                                    if( !(vooAssentos.contarAssentosPorVoo(vooPretendido.getId()) > vooPretendido.getCapacidade()) ){
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
                                                Passageiro novoPassageiroLogado = passageiros.buscarLoginPassageiro(novoLogin, novaSenha);
                                                while (novoPassageiroLogado == null) {
                                                    System.out.println("\nLogin Invalido. Tente novamente");
                                                    System.out.println("Login: ");
                                                    novoLogin = scanner.nextLine();
                                                    System.out.println("Senha:");
                                                    novaSenha = scanner.nextLine();
                                                    novoPassageiroLogado = passageiros.buscarLoginPassageiro(novoLogin, novaSenha);
                                                }
                                                if (novoPassageiroLogado != null) {
                                                    VooAssentos novoAssento = new VooAssentos(vooPretendido, novoPassageiroLogado);
                                                    vooAssentos.adicionaVooAssentos(novoAssento);
                                                    Ticket novoTicket = new Ticket(novoPassageiroLogado, vooPretendido);
                                                    tickets.adicionaTicket(novoTicket);
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

                                                if (passageiros.adicionaPassageiro(novoPassageiroCompra)) {
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
                                                Passageiro novoPassageiroLogado = passageiros.buscarLoginPassageiro(novoLogin, novaSenha);
                                                while (novoPassageiroLogado == null) {
                                                    System.out.println("\nLogin Invalido. Tente novamente");
                                                    System.out.println("Login: ");
                                                    novoLogin = scanner.nextLine();
                                                    System.out.println("Senha:");
                                                    novaSenha = scanner.nextLine();
                                                    novoPassageiroLogado = passageiros.buscarLoginPassageiro(novoLogin, novaSenha);
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

                                                if (passageiros.adicionaPassageiro(novoPassageiroCompra)) {
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
                                                Passageiro novoPassageiroLogado = passageiros.buscarLoginPassageiro(novoLogin, novaSenha);
                                                while (novoPassageiroLogado == null) {
                                                    System.out.println("\nLogin Invalido. Tente novamente");
                                                    System.out.println("Login: ");
                                                    novoLogin = scanner.nextLine();
                                                    System.out.println("Senha:");
                                                    novaSenha = scanner.nextLine();
                                                    novoPassageiroLogado = passageiros.buscarLoginPassageiro(novoLogin, novaSenha);
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

                                                if (passageiros.adicionaPassageiro(novoPassageiroCompra)) {
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
                    tickets.buscaTicket(codigoTicket);
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
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private int menuInicial() {

        String menu = "";
        menu += "\n=========================================\n";
        menu += "====== SEJA BEM VINDO AO AEROPORTO ======";
        menu += "\n=========================================\n";
        menu += "\n1 - Entrar como passageiro.";
        menu += "\n2 - Entrar como funcionario.";
        menu += "\n3 - Entrar como administrador.";
        menu += "\n4 - Fazer seu cadastro.";
        menu += "\n6 - Procurar voos.";
        menu += "\n7 - Ver todos os voos.";
        menu += "\n8 - Conferir reserva.";
        menu += "\n9 - Sair do sistema.\n";
        menu += "\nQual sua opcao? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
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

    private int menuPassageiro() {

        String menu = "";
        menu += "\n=========================================\n";
        menu += "== SEJA BEM VINDO AO MENU DE PASSAGEIRO =";
        menu += "\n=========================================\n";
        menu += "\n1 - Para comprar uma passagem.";
        menu += "\n2 - Listar seu historico de passagens.";
        menu += "\n3 - Alterar alguma passagem.";
        menu += "\n4 - Fazer check-in.";
        menu += "\n5 - Alterar algum dado pessoal.";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }

    private int menuAdministrador() {

        String menu = "";
        menu += "\n=========================================\n";
        menu += "= SEJA BEM VINDO AO MENU DE CORPORATIVO =";
        menu += "\n=========================================\n";
        menu += "\n1 - Tratamento de passageiros.";
        menu += "\n2 - Tratamento de voos.";
        menu += "\n3 - Tratamento de companhias aereas.";
        menu += "\n4 - Alterar algum dado pessoal.";
        menu += "\n5 - Listar todos os administradores.";
        menu += "\n6 - Cadastrar novo administrador.";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }
    
    private int menuFuncionario(){
        
        String menu = "";
        menu += "\n=========================================\n";
        menu += "== SEJA BEM VINDO AO MENU DE FUNCIONARIO =";
        menu += "\n=========================================\n";
        menu += "\n1 - Registrar entrada no aeroporto.";
        menu += "\n2 - Registrar entrada no aviao.";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";
        
        System.out.println("menu");
        
        return Integer.parseInt(scanner.nextLine());
    }

    private void programaPassageiro() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.menuPassageiro();
            switch (opcaoUsuario) {
                case 1:
                    System.out.println(voos.mostrarTodos());
                    String idVoo = scanner.nextLine();
                    Voo vooEscolhido = voos.buscarRetornarVooPorID(idVoo);
                    if (!(vooAssentos.contarAssentosPorVoo(vooEscolhido.getId()) > vooEscolhido.getCapacidade())) {
                        System.out.println("\nVoo sem assentos disponiveis, por favor busque outro.");
                        break;
                    }
                    Passageiro pLogado = Util.getPassageiroLogado();
                    Ticket novoTicket = new Ticket(pLogado, vooEscolhido);
                    VooAssentos assentoNovo = new VooAssentos(vooEscolhido, pLogado);
                    System.out.println("PASSAGEM COMPRADA COM SUCESSO.");
                    break;

                case 2:
                    System.out.println(tickets.mostrarTicketsPorPassageiro(Util.getFuncionarioLogado().getId()));
                    System.out.println("2 - Listar seu historico de passagens");
                    break;

                case 4:
                    //Faz Check-In
                    System.out.println("Esses sao seus tickets disponiveis para check-in: ");
                    Passageiro passLogado = Util.getPassageiroLogado();
                    tickets.mostrarTicketsPorPassageiro(passLogado.getId());
                    System.out.println("Digite o codigo do ticket escolhido: ");
                    String codigoTicket = scanner.nextLine();
                    Ticket ticketEscolhido = tickets.buscaTicket(codigoTicket);
                    VooAssentos assentoCheckIn = vooAssentos.buscarAssentoPorVooEPassageiro(ticketEscolhido.getVoo().getId(), passLogado.getId());
                    if (ticketEscolhido.getVoo().getData().isBefore(LocalDate.now().plusDays(1))){
                        Checkin novoCheckin = new Checkin(ticketEscolhido, passLogado.getDocumento());
                        checkins.adicionaCheckin(novoCheckin);
                        BoardingPass novoBoardingPass = new BoardingPass(passLogado, ticketEscolhido.getVoo(), ticketEscolhido, assentoCheckIn);
                        boardingPasses.adicionaBoardingPass(novoBoardingPass);
                        System.out.println("Check-in realizado! Seu cartão de embarque:\n" + novoBoardingPass.toString());
                    }
                    break;

                case 5:
                    System.out.println("5 - Alterar algum dado pessoal.");
                    break;

                case 9:
                    System.out.println("sair");
                    break;
                default:
                    System.out.println("escola uma opcao valida");
                    break;
            }

        }
    }

    private void programaAdministrador() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.menuAdministrador();
            switch (opcaoUsuario) {
                case 1:

                    System.out.println("1 - Tratamento de passageiros");
                    int opcaoTratarPassageiro = menuTratarPassageiro();
                    while (opcaoTratarPassageiro != 9) {
                        switch (opcaoTratarPassageiro) {
                            case 1:
                                System.out.println("Todos os passageiros:");
                                passageiros.mostrarTodos();
                                break;
                            case 2:
                                System.out.println("Recuperar senha de passageiro. Insira o documento");
                                String documentoPassageiro = scanner.nextLine();
                                for(Passageiro passageiro: passageiros.passageiros){
                                    if(passageiro.getDocumento().equals(documentoPassageiro)){
                                        System.out.println("Sua senha e: " + passageiro.getSenha());
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("3 - Excluir um passageiro."); //Excluir
                                break;
                            case 4:
                                System.out.println("4 - Cancelar voo do passageiro."); //Excluir
                                break;
                            case 9:
                                System.out.println("Saindo do tratamento de passageiros.");
                                break;
                            default:
                                System.out.println("escolha uma opcao valida");
                                break;
                        }
                        opcaoTratarPassageiro = menuTratarPassageiro();
                    }
                    break;

                case 2:
                    System.out.println("2 - Tratamento de voos");
                    int opcaoTratarVoo = menuTratarVoos();
                    while (opcaoTratarVoo != 9) {
                        switch (opcaoTratarVoo) {
                            case 1:
                                System.out.println("1 - Mostrar todos os voos.");
                                break;
                            case 2:
                                System.out.println("2 - Cadastrar voo.");
                                break;
                            case 3:
                                System.out.println("3 - Alterar data voo.");
                                break;
                            case 4:
                                System.out.println("4 - Cancelar voo.");
                                break;
                            case 5:
                                System.out.println("5 - Alterar voo do passageiro."); //Excluir
                                break;
                            case 9:
                                System.out.println("Saindo do tratamento de voos.");
                                break;
                            default:
                                System.out.println("escolha uma opcao valida");
                                break;
                        }
                        opcaoTratarVoo = menuTratarVoos();
                    }
                    break;

                case 3:
                    System.out.println("3 - Tratamento de companhias aereas"); //Excluir
                    int opcaoTratarCompanias = menuTratarCompAereas();
                    while (opcaoTratarCompanias != 9) {
                        switch (opcaoTratarCompanias) {
                            case 1:
                                System.out.println("1 - Mostrar todas as companhias.");
                                break;
                            case 2:
                                System.out.println("2 - Cadastrar companhia.");
                                break;
                            case 3:
                                System.out.println("3 - Alterar dados companhia.");
                                break;
                            case 4:
                                System.out.println("4 - Excluir companhia.");
                                break;
                            case 9:
                                System.out.println("Saindo do tratamento de companhias.");
                                break;
                            default:
                                System.out.println("escolha uma opcao valida");
                                break;
                        }
                        opcaoTratarCompanias = menuTratarCompAereas();
                    }
                    break;

                case 4:
                    System.out.println("4 - Alterar algum dado pessoal");

                    break;
                case 5:
                    System.out.println("5 - Listando os administradores");

                    break;
                case 6:
                    System.out.println("6 - Cadastrando novo administrador");
                    Administrador novoAdm = new Administrador();
                    System.out.println("Digite seu nome: ");
                    novoAdm.setNome(scanner.nextLine());

                    System.out.println("Digite sua data Nascimento: (YYYY-MM-DD)");
                    novoAdm.setNascimento(LocalDate.parse(scanner.nextLine()));

                    System.out.println("Digite seu documento: ");
                    novoAdm.setDocumento(scanner.nextLine());

                    System.out.println("Cadastre seu login: ");
                    novoAdm.setLogin(scanner.nextLine());

                    System.out.println("Cadastre sua senha: ");
                    novoAdm.setSenha(scanner.nextLine());

                    if (administradores.adicionaAdministrador(novoAdm)) {
                        System.out.println("\nAdministrador Cadastrado com sucesso");
                    } else {
                        System.out.println("\nInfelizmente não comportamos mais nenhum administrador.");
                    }

                    break;
                case 9:
                    System.out.println("sair");
                    break;
                default:
                    System.out.println("escolha uma opcao valida");
                    break;
            }

        }
    }
    
    private void programaFuncionario(){
        int opcaoUsuario = 10;
        
        while(opcaoUsuario != 9){
            opcaoUsuario = this.menuFuncionario();
            switch(opcaoUsuario){
                case 1:
                    System.out.println("Registrar entrada no aeroporto, insira documento: ");
                    break;
                case 2:
                    System.out.println("Registrar entrada no aviao, insira documento: ");
                    break;
                case 9:
                    System.out.println("Saindo do menu de funcionario.");
                    break;
                default:
                    System.out.println("Escolha uma opcao valida.");
                    break;
            }
        
        }
        
    }

    private int menuTratarPassageiro() {
        String menu = "";
        menu += "\n=========================================\n";
        menu += "  SEJA BEM VINDO AO TRATAMENTO DE USUARIOS ";
        menu += "\n=========================================\n";
        menu += "\n1 - Mostrar todos os passageiros.";
        menu += "\n2 - Recuperar senha de passageiro.";
        menu += "\n3 - Excluir um passageiro.";
        menu += "\n4 - Cancelar voo do passageiro.";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }

    private int menuTratarVoos() {
        String menu = "";
        menu += "\n=========================================\n";
        menu += "  SEJA BEM VINDO AO TRATAMENTO DE VOOS ";
        menu += "\n=========================================\n";
        menu += "\n1 - Mostrar todos os voos.";
        menu += "\n2 - Cadastrar voo.";
        menu += "\n3 - Alterar data voo.";
        menu += "\n4 - Cancelar voo.";
        menu += "\n5 - Alterar voo do passageiro.";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }

    private int menuTratarCompAereas() {
        String menu = "";
        menu += "\n=========================================\n";
        menu += "SEJA BEM VINDO AO TRATAMENTO DE COMPANHIAS";
        menu += "\n=========================================\n";
        menu += "\n1 - Mostrar todas as companhias.";
        menu += "\n2 - Cadastrar companhia.";
        menu += "\n3 - Alterar dados da companhia.";
        menu += "\n4 - Excluir companhia (Somente se nao houver voos da companhia cadastrados).";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }

}
