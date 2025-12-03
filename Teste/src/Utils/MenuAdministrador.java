/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DAOS.AdministradorDAO;
import DAOS.BoardingPassDAO;
import DAOS.CheckinDAO;
import DAOS.CompAereaDAO;
import DAOS.DespachoBagagemDAO;
import DAOS.PassageiroDAO;
import DAOS.TicketDAO;
import DAOS.VooAssentosDAO;
import DAOS.VooDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import model.Administrador;
import model.CompAerea;
import model.Passageiro;
import model.Voo;
import teste.Util;

/**
 *
 * @author vitor
 */
public class MenuAdministrador {
    
    private Scanner scanner;
    private VooDAO vooDAO;
    private PassageiroDAO passageiroDAO;
    private VooAssentosDAO vooAssentosDAO;
    private TicketDAO ticketDAO;
    private AdministradorDAO admDAO;
    private BoardingPassDAO boardingPassDAO;
    private CheckinDAO checkinDAO;
    private DespachoBagagemDAO despachosDAO;
    private CompAereaDAO compAereaDAO;
    private GeradorRelatorios geradorRelatorios = new GeradorRelatorios();
    
    public MenuAdministrador(
            Scanner scanner, VooDAO vooDAO, PassageiroDAO passageiroDAO, CompAereaDAO compAereaDAO,
            VooAssentosDAO vooAssentosDAO, TicketDAO ticketDAO, AdministradorDAO admDAO,
            BoardingPassDAO boardingPassDAO, CheckinDAO checkinDAO, DespachoBagagemDAO despachosDAO)
    {
        this.scanner = scanner;
        this.passageiroDAO = passageiroDAO;
        this.compAereaDAO = compAereaDAO;
        this.vooDAO = vooDAO;
        this.passageiroDAO = passageiroDAO;
        this.vooAssentosDAO = vooAssentosDAO;
        this.ticketDAO = ticketDAO;
        this.admDAO = admDAO;
        this.boardingPassDAO = boardingPassDAO;
        this.checkinDAO = checkinDAO;
        this.despachosDAO = despachosDAO;
    }
    
    public int ShowMenuAdministrador() {

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
    
     public void programaAdministrador() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.ShowMenuAdministrador();
            switch (opcaoUsuario) {

                case 1:
                    int opcaoTratarPassageiro = menuTratarPassageiro();
                    while (opcaoTratarPassageiro != 9) {
                        switch (opcaoTratarPassageiro) {
                            case 1:
                                System.out.println("Todos os passageiros:");
                                System.out.println(passageiroDAO.mostrarTodos());
                                break;
                            case 2:
                                System.out.println("Recuperar senha de passageiro. Insira o documento");
                                String documentoPassageiro = scanner.nextLine();
                                Passageiro passageiro = passageiroDAO.buscarPassageiroPorDocumento(documentoPassageiro);
                                if (passageiro != null) {
                                    System.out.println("Sua senha e: " + passageiro.getSenha());
                                } else 
                                    System.out.println("Documnto inserido nao corresponde a nenhum passageiro.");
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
                                System.out.println(vooDAO.mostrarTodos());
                                break;
                            case 2: {
                                System.out.println("2 - Cadastrar voo.");
                                System.out.println(compAereaDAO.mostrarTodos());
                                System.out.println("Digite a sigla da companhia aerea responsavel pelo voo: ");
                                String compAereaSelecionada = scanner.nextLine().toUpperCase();
                                if (compAereaSelecionada != null && compAereaDAO.buscarCompPorSiglaString(compAereaSelecionada)) {
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
                                    vooDAO.adicionaVoo(novoVoo);
                                } else{
                                    System.out.println("Companhia aerea nao encontrada");
                                }

                                break;
                            }
                            case 3: {
                                System.out.println("3 - Alterar estado do voo");
                                System.out.println(vooDAO.mostrarTodos());
                                System.out.println("\nDigite o id do voo que deseja alterar o estado: ");
                                Voo vooSelecionado = vooDAO.buscarRetornarVooPorID(scanner.nextLine());
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
                                System.out.println(compAereaDAO.mostrarTodos());
                                break;
                            case 2:
                                System.out.println("\n--- Cadastrar Nova Companhia Aerea ---");
                                CompAerea novaComp = new CompAerea();

                                System.out.println("Digite o nome da companhia:");
                                novaComp.setNome(scanner.nextLine());

                                System.out.println("Digite a abreviacao (sigla) da companhia:");
                                novaComp.setAbreviacao(scanner.nextLine());

                                if (compAereaDAO.adicionaComp(novaComp)) {
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
                    System.out.println(admDAO.mostrarTodosAdministradores());
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

                    if (admDAO.adicionaAdministrador(novoAdm)) {
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

                                String relatorioOrigem = geradorRelatorios.gerarRelatorioPassageirosPorOrigem(siglaOrigem);
                                System.out.println(relatorioOrigem);
                                break;
                            case 2:
                                System.out.println("\n--- Relatorio 2: Passageiros por Destino ---");
                                System.out.println("Digite a cidade do aeroporto de DESTINO (Ex: uberlandia): ");
                                String siglaDestino = scanner.nextLine();

                                String relatorioDestino = geradorRelatorios.gerarRelatorioPassageirosPorDestino(siglaDestino);
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

                                int total = geradorRelatorios.calcularArrecadacao(siglaComp, dataInicio, dataFim);

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
