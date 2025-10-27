/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teste;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Teste {

    AeroportoDAO aeroportos = new AeroportoDAO();
    AdministradorDAO administradores = new AdministradorDAO();
    FuncionarioDAO funcionarios = new FuncionarioDAO();
    CompAereaDAO companhias = new CompAereaDAO();
    VooDAO voos = new VooDAO(companhias);
    PassageiroDAO passageiros = new PassageiroDAO();
    VooAssentosDAO vooAssentos = new VooAssentosDAO(voos, passageiros);
    TicketDAO tickets = new TicketDAO(voos, passageiros, vooAssentos);
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
        /*
        CompAereaDAO companhias = new CompAereaDAO();
        VooDAO voos = new VooDAO(companhias);
        PassageiroDAO passageiros = new PassageiroDAO();
        VooAssentosDAO vooAssentos = new VooAssentosDAO(voos, passageiros);
        TicketDAO tickets = new TicketDAO(voos, passageiros, vooAssentos);
        System.out.println(tickets.mostrarTicketsValidosPorPassageiro(2));
        System.out.println("\ntodos\n");
        System.out.println(tickets.mostrarTicketsPorPassageiro(2));
        
        //System.out.println(voos.buscarRetornarVooPorID("Gal-v1"));
         */
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

                    if (funcionarioLogado != null) {
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
                                    if (vooAssentos.contarAssentosPorVoo(vooPretendido.getId()) > vooPretendido.getCapacidade()) {
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
                                                    Ticket novoTicket = new Ticket(novoPassageiroLogado, vooPretendido, novoAssento);
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
                    Ticket buscaTicket = tickets.buscaTicket(codigoTicket);
                    if(buscaTicket == null){
                        System.out.println("Buscando cartoes de embarque");{
                        Checkin buscaCheckin = checkins.retornaCheckInIDTicket(codigoTicket);
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
        menu += "\n4 - Fazer seu cadastro como passageiro.";
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
        menu += "\n3 - Fazer check-in.";
        menu += "\n4 - Alterar nome.";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }

    private int menuAdministrador() {

        String menu = "";
        menu += "\n=========================================\n";
        menu += "= SEJA BEM VINDO AO MENU DE ADMINISTRADOR =";
        menu += "\n=========================================\n";
        menu += "\n1 - Tratamento de passageiros.";
        menu += "\n2 - Tratamento de voos.";
        menu += "\n3 - Tratamento de companhias aereas.";
        menu += "\n5 - Listar todos os administradores.";
        menu += "\n6 - Cadastrar novo administrador.";
        menu += "\n7 - Relatórios Gerenciais.";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }

    private int menuFuncionario() {

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

    private void programaPassageiro() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.menuPassageiro();
            Passageiro pLogado = Util.getPassageiroLogado();
            switch (opcaoUsuario) {
                case 1: {
                    System.out.println(voos.mostrarTodos());
                    System.out.println("Digite a id do voo dsejado");
                    String idVoo = scanner.nextLine();
                    Voo vooEscolhido = voos.buscarRetornarVooPorID(idVoo);
                    //System.out.println(vooEscolhido);
                    //System.out.println(vooAssentos.mostrarTodosAssentosPorVoo(vooEscolhido));
                    if (vooAssentos.contarAssentosPorVoo(idVoo) >= vooEscolhido.getCapacidade()) {
                        System.out.println("\nVoo sem assentos disponiveis, por favor busque outro.");
                    } else {
                        VooAssentos assentoNovo = new VooAssentos(vooEscolhido, pLogado);
                        vooAssentos.adicionaVooAssentos(assentoNovo);
                        Ticket novoTicket = new Ticket(pLogado, vooEscolhido, assentoNovo);
                        tickets.adicionaTicket(novoTicket);
                        System.out.println("PASSAGEM COMPRADA COM SUCESSO.");
                    }
                    break;
                }

                case 2: {
                    System.out.println("2 - Listar seu historico de passagens");
                    //System.out.println(pLogado.getId());
                    //System.out.println(tickets.mostrarTodos());
                    System.out.println(tickets.mostrarTicketsPorPassageiro(pLogado.getId()));
                    break;
                }
                case 3: {
                    //Faz Check-In
                    System.out.println("Esses sao seus tickets disponiveis para check-in: ");
                    System.out.println(tickets.mostrarTicketsPorPassageiro(pLogado.getId()));
                    if (tickets.mostrarTicketsPorPassageiro(pLogado.getId()).equals("")) {
                        System.out.println("\nNao ha tickets disponiveis para fazer check-in.");
                        break;
                    }
                    System.out.println("Digite o codigo do ticket escolhido: ");
                    String codigoTicket = scanner.nextLine();
                    Ticket ticketEscolhido = tickets.buscaTicket(codigoTicket);
                    if (ticketEscolhido != null) {
                        VooAssentos assentoCheckIn = vooAssentos.buscarAssentoPorVooEPassageiro(ticketEscolhido.getVoo().getId(), pLogado.getId());
                        LocalDate hoje = LocalDate.now();
                        LocalDate diaVoo = ticketEscolhido.getVoo().getData();
                        if (diaVoo.equals(hoje) || diaVoo.equals(hoje.plusDays(1))) {
                            Checkin novoCheckin = new Checkin(ticketEscolhido, pLogado.getDocumento());
                            checkins.adicionaCheckin(novoCheckin);
                            BoardingPass novoBoardingPass = new BoardingPass(pLogado, ticketEscolhido.getVoo(), ticketEscolhido, assentoCheckIn);
                            boardingPasses.adicionaBoardingPass(novoBoardingPass);
                            System.out.println("Check-in realizado! Seu cartao de embarque:\n" + novoBoardingPass.toString());
                            tickets.removeTicketPosCheckIn(ticketEscolhido.getCodigoTicket());
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

    private void programaAdministrador() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.menuAdministrador();
            switch (opcaoUsuario) {

                case 1:
                    int opcaoTratarPassageiro = menuTratarPassageiro();
                    while (opcaoTratarPassageiro != 9) {
                        switch (opcaoTratarPassageiro) {
                            case 1:
                                System.out.println("Todos os passageiros:");
                                System.out.println(passageiros.mostrarTodos());
                                break;
                            case 2:
                                System.out.println("Recuperar senha de passageiro. Insira o documento");
                                boolean existePassagiro = false;
                                String documentoPassageiro = scanner.nextLine();
                                for (Passageiro passageiro : passageiros.passageiros) {
                                    if (passageiro != null && passageiro.getDocumento().equals(documentoPassageiro)) {
                                        System.out.println("Sua senha e: " + passageiro.getSenha());
                                        existePassagiro = true;
                                    }
                                }
                                if (!existePassagiro) {
                                    System.out.println("Documnto inserido nao corresponde a nenhum passageiro.");
                                }
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
                    int opcaoTratarVoo = menuTratarVoos();
                    while (opcaoTratarVoo != 9) {
                        switch (opcaoTratarVoo) {
                            case 1:
                                System.out.println("1 - Mostrar todos os voos.");
                                System.out.println(voos.mostrarTodos());
                                break;
                            case 2: {
                                System.out.println("2 - Cadastrar voo.");
                                System.out.println(companhias.mostrarTodos());
                                System.out.println("Digite a sigla da companhia aerea responsavel pelo voo: ");
                                CompAerea compAereaSelecionada = companhias.buscarRetornarCompSigla(scanner.nextLine().toUpperCase());
                                if (compAereaSelecionada != null) {
                                    Voo novoVoo = new Voo(compAereaSelecionada);
                                    System.out.println("Digite a origem do voo: ");
                                    novoVoo.setOrigem(scanner.nextLine());
                                    System.out.println("Digite o destino do voo: ");
                                    novoVoo.setDestino(scanner.nextLine());
                                    System.out.println("Digite a data do voo no seguinte formato YYYY-MM-DD: ");
                                    novoVoo.setData(LocalDate.parse(scanner.nextLine()));
                                    System.out.println("Digite a duracao do voo no seguinte formato HH:MM:SS: ");
                                    novoVoo.setDuracao(LocalTime.parse(scanner.nextLine()));
                                    System.out.println("Digite a capacidade do voo: ");
                                    novoVoo.setCapacidade(Integer.parseInt(scanner.nextLine()));
                                    novoVoo.setEstado("programado");
                                    voos.adicionaVoo(novoVoo);
                                } else{
                                    System.out.println("Companhia aerea nao encontrada");
                                }

                                break;
                            }
                            case 3: {
                                System.out.println("3 - Alterar estado do voo");
                                System.out.println(voos.mostrarTodos());
                                System.out.println("\nDigite o id do voo que deseja alterar o estado: ");
                                Voo vooSelecionado = voos.buscarRetornarVooPorID(scanner.nextLine());
                                if (vooSelecionado != null) {
                                    System.out.println("Digite 1 para inserir o estado \"Programado\"");
                                    System.out.println("Digite 2 para inserir o estado \"Embarque\"");
                                    System.out.println("Digite 3 para inserir o estado \"Decolado\"");
                                    System.out.println("Digite 4 para inserir o estado \"Atrasado\"");
                                    System.out.println("Digite 5 para inserir o estado \"Cancelado\"");
                                    int opcaoEstado = 0;
                                    while (opcaoEstado != 1 && opcaoEstado != 2 && opcaoEstado != 3 && opcaoEstado != 4 && opcaoEstado != 5) {
                                        opcaoEstado = Integer.parseInt(scanner.nextLine());
                                        switch (opcaoEstado) {
                                            case 1:
                                                vooSelecionado.setEstado("programado");
                                                break;
                                            case 2:
                                                vooSelecionado.setEstado("embarque");
                                                break;
                                            case 3:
                                                vooSelecionado.setEstado("decolado");
                                                break;
                                            case 4:
                                                vooSelecionado.setEstado("atrasado");
                                                break;
                                            case 5:
                                                vooSelecionado.setEstado("cancelado");
                                                break;
                                            default:
                                                System.out.println("Opcao invalida");
                                                System.out.println("Digite novamente");
                                                opcaoEstado = Integer.parseInt(scanner.nextLine());
                                        }
                                    }
                                }
                                break;
                            }
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
                    int opcaoTratarCompanias = menuTratarCompAereas();
                    while (opcaoTratarCompanias != 9) {
                        switch (opcaoTratarCompanias) {
                            case 1:
                                System.out.println("Mostrando todas as companhias.");
                                System.out.println(companhias.mostrarTodos());
                                break;
                            case 2:
                                System.out.println("\n--- Cadastrar Nova Companhia Aerea ---");
                                CompAerea novaComp = new CompAerea();

                                System.out.println("Digite o nome da companhia:");
                                novaComp.setNome(scanner.nextLine());

                                System.out.println("Digite a abreviacao (sigla) da companhia:");
                                novaComp.setAbreviacao(scanner.nextLine());

                                if (companhias.adicionaComp(novaComp)) {
                                    System.out.println("Companhia cadastrada com sucesso!");
                                } else {
                                    System.out.println("Erro: Nao ha mais espaco para cadastrar novas companhias.");
                                }
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
                case 5:
                    System.out.println("Listando os administradores");
                    System.out.println(administradores.mostrarTodos());
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
                case 7:
                    int opcaoRelatorio = menuRelatorios();
                    while (opcaoRelatorio != 9) {
                        switch (opcaoRelatorio) {
                            case 1:
                                System.out.println("\n--- Relatorio 1: Passageiros por Origem ---");
                                System.out.println("Digite a cidade do aeroporto de ORIGEM (Ex: uberaba): ");
                                String siglaOrigem = scanner.nextLine();

                                String relatorioOrigem = vooAssentos.gerarRelatorioPassageirosPorOrigem(siglaOrigem);
                                System.out.println(relatorioOrigem);
                                break;
                            case 2:
                                System.out.println("\n--- Relatorio 2: Passageiros por Destino ---");
                                System.out.println("Digite a cidade do aeroporto de DESTINO (Ex: uberlandia): ");
                                String siglaDestino = scanner.nextLine();

                                String relatorioDestino = vooAssentos.gerarRelatorioPassageirosPorDestino(siglaDestino);
                                System.out.println(relatorioDestino);
                                break;
                            case 3:
                                System.out.println("\n--- Relatorio 3: Arrecadacao por Companhia ---");
                                System.out.println("Digite a sigla da Companhia Aerea (Ex: Gal): ");
                                String siglaComp = scanner.nextLine();

                                System.out.println("Digite a DATA DE INICIO (YYYY-MM-DD): ");
                                LocalDate dataInicio = LocalDate.parse(scanner.nextLine());

                                System.out.println("Digite a DATA DE FIM (YYYY-MM-DD): ");
                                LocalDate dataFim = LocalDate.parse(scanner.nextLine());

                                int total = vooAssentos.calcularArrecadacao(siglaComp, dataInicio, dataFim);

                                System.out.printf("Total arrecadado pela companhia %s entre %s e %s foi: R$ %d\n",
                                        siglaComp.toUpperCase(), dataInicio, dataFim, total);
                                break;
                            case 9:
                                System.out.println("Saindo dos relatorios...");
                                break;
                            default:
                                System.out.println("escolha uma opcao valida");
                                break;
                        }
                        opcaoRelatorio = menuRelatorios();
                    }
                    break;
                case 9:
                    Util.setAdministradorLogado(null);
                    System.out.println("saindo do menu de administrador.");
                    break;
                default:
                    System.out.println("escolha uma opcao valida");
                    break;
            }

        }
    }

    private void programaFuncionario() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.menuFuncionario();
            switch (opcaoUsuario) {
                case 1:{
                    System.out.println("\n--- Registrar Entrada no Aeroporto ---");
                    System.out.println("Digite o codigo do Ticket do passageiro:");
                    String codTicketPortao = scanner.nextLine();

                    //System.out.println(boardingPasses.mostrarTodos());
                    //BoardingPass ticketPortao = boardingPasses.buscarPorIDTicket(codTicketPortao);
                    //Ticket ticketPortao = tickets.buscaTicket(codTicketPortao);
                    Checkin ticketPortao = checkins.retornaCheckInIDTicket(codTicketPortao);
                    
                    if (ticketPortao != null) {
                        BoardingPass boardingPass = boardingPasses.buscarPorPassageiroEVoo(
                                ticketPortao.getTicket().getPassageiro().getId(),
                                ticketPortao.getTicket().getVoo().getId()
                        );

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
                    break;}
                case 2:{
                    System.out.println("\n--- Registrar Embarque (Entrada no Aviao) ---");
                    System.out.println("Digite o codigo do Ticket do passageiro:");
                    String codTicketAviao = scanner.nextLine();

                    //Ticket ticketAviao = tickets.buscaTicket(codTicketAviao);
                    //System.out.println(boardingPasses.mostrarTodos());
                    //BoardingPass ticketAviao = boardingPasses.buscarPorIDTicket(codTicketAviao);
                    Checkin ticketAviao = checkins.retornaCheckInIDTicket(codTicketAviao);

                    if (ticketAviao != null) {
                        BoardingPass boardingPass = boardingPasses.buscarPorPassageiroEVoo(
                                ticketAviao.getTicket().getPassageiro().getId(),
                                ticketAviao.getTicket().getVoo().getId()
                        );

                        if (boardingPass != null && ticketAviao.getEstado() == "No aeroporto") {
                            System.out.println("EMBARQUE AUTORIZADO. Bom voo!");
                            System.out.println("Passageiro: " + boardingPass.getPassageiro().getNome());
                            vooAssentos.buscarvooAssentosPorID(boardingPass.getIdAssento());
                            System.out.println("Assento: " + vooAssentos.buscarvooAssentosPorID(boardingPass.getIdAssento()));
                            ticketAviao.setEstado("No embarque");
                        } else {
                            if(ticketAviao.getEstado() != "No aeroporto"){
                                System.out.println("EMBARQUE NEGADO. Passageiro nao passou pelo portao.");
                            } else{
                                System.out.println("EMBARQUE NEGADO. Passageiro nao fez check-in ou nao passou pelo portao.");
                            }
                        }
                    } else {
                        System.out.println("Ticket nao encontrado.");
                    }
                    break;}
                case 3:
                    System.out.println("\n--- Despachar Bagagem ---");
                    System.out.println("Digite o codigo do Ticket do passageiro:");
                    String codigoTicket = scanner.nextLine();

                    //Ticket ticketEscolhido = tickets.buscaTicket(codigoTicket);
                    Checkin ticketEscolhido = checkins.retornaCheckInIDTicket(codigoTicket);

                    if (ticketEscolhido != null) {
                        Passageiro p = ticketEscolhido.getTicket().getPassageiro();
                        System.out.println("Ticket encontrado:");
                        System.out.println("Passageiro: " + p.getNome());
                        System.out.println("Voo: " + ticketEscolhido.getTicket().getVoo().getId());
                        System.out.println("Confirmar despacho? (1-Sim / 2-Nao)");

                        int conf = Integer.parseInt(scanner.nextLine());
                        if (conf == 1) {
                            DespachoBagagem novoDespacho = new DespachoBagagem(ticketEscolhido.getTicket(), p.getDocumento());
                            if (despachos.adicionaDespachoBagagem(novoDespacho)) {
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

    private int menuTratarPassageiro() {
        String menu = "";
        menu += "\n=========================================\n";
        menu += "  SEJA BEM VINDO AO TRATAMENTO DE USUARIOS ";
        menu += "\n=========================================\n";
        menu += "\n1 - Mostrar todos os passageiros.";
        menu += "\n2 - Recuperar senha de passageiro.";
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
        menu += "\n3 - Alterar estado do voo.";
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
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }

    private int menuRelatorios() {
        String menu = "";
        menu += "\n=========================================\n";
        menu += "     SEJA BEM VINDO AOS RELATORIOS     ";
        menu += "\n=========================================\n";
        menu += "\n1 - Passageiros que deixaram um determinado aeroporto (Origem).";
        menu += "\n2 - Passageiros que chegaram em um determinado aeroporto (Destino).";
        menu += "\n3 - Valor arrecadado por companhia aerea em um periodo.";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }

}
