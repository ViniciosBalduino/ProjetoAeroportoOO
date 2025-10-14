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
    CompAereaDAO companhias = new CompAereaDAO();
    VooDAO voos = new VooDAO(companhias);
    PassageiroDAO passageiros = new PassageiroDAO();
    VooAssentosDAO vooAssentos = new VooAssentosDAO(voos, passageiros);
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       
        //CompAereaDAO companhias = new CompAereaDAO();
        //VooDAO voos = new VooDAO(companhias);
        //System.out.println(voos.mostrarTodos());
        //voos.mostrarTodos();
        new Teste();

    }

    public Teste() {

        int opcaoUsuario = 10;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.menuInicial();
            switch (opcaoUsuario) {
                case 0:
                    System.out.println("\n---- Logando como usuario ----");
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
                        System.out.println("\nLogin Invalido. Tente novamente");
                    }
                    break;

                case 1:
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
                        System.out.println("\nLogin Invalido. Tente novamente");
                    }
                    break;

                case 2:
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
                    
                    if(passageiros.adicionaPassageiro(novoPassageiro)){
                        System.out.println("\nPassageiro Cadastrado com sucesso");
                    } else{
                        System.out.println("\nInfelizmente não comportamos mais nenhum usuário. Outro dia voce volta.");
                    }
                    break;

                case 3:
                    System.out.println("\n---- Consultando Voos ----");
                    System.out.println(voos.mostrarTodos());
                    System.out.println("\nPrescione  a tecla \"ENTER\" para retornar ao menu anterior");
                    scanner.nextLine();                   
                    break;
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

    private int menuInicial() {

        String menu = "";
        menu += "\n=========================================\n";
        menu += "====== SEJA BEM VINDO AO AEROPORTO ======";
        menu += "\n=========================================\n";
        menu += "\n0 - Entrar como usuario.";
        menu += "\n1 - Entrar como administrador.";
        menu += "\n2 - Fazer seu cadastro.";
        menu += "\n3 - Consultar voos.";
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
        menu += "\n0 - Para comprar uma passagem.";
        menu += "\n1 - Listar seu historico de passagens.";
        menu += "\n2 - Alterar alguma passagem.";
        menu += "\n3 - Alterar algum dado pessoal.";
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
        menu += "\n0 - Para comprar uma passagem.";
        menu += "\n1 - Listar seu historico de passagens.";
        menu += "\n2 - Alterar alguma passagem.";
        menu += "\n3 - Alterar algum dado pessoal.";
        menu += "\n9 - Para sair do menu\n";
        menu += "\nQual sua opcao ? R: ";

        System.out.print(menu);

        return Integer.parseInt(scanner.nextLine());
    }
   
    
    private void programaPassageiro() {
        int opcaoUsuario = 10;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.menuPassageiro();
            switch (opcaoUsuario) {
                case 0:

                    System.out.println("0 - Para comprar uma passagem");
                    break;

                case 1:
                    System.out.println("1 - Listar seu historico de passagens");
                    break;

                case 2:
                    System.out.println("2 - Alterar alguma passagem");

                    break;

                case 3:
                    System.out.println("3 - Alterar algum dado pessoal");
                    
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
                case 0:

                    System.out.println("0 - Para comprar uma passagem");
                    break;

                case 1:
                    System.out.println("1 - Listar seu historico de passagens");
                    break;

                case 2:
                    System.out.println("2 - Alterar alguma passagem");

                    break;

                case 3:
                    System.out.println("3 - Alterar algum dado pessoal");
                    
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
}
