/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teste;

import DAOS.*;
import Utils.MenuAdministrador;
import Utils.MenuFuncionario;
import Utils.MenuInicial;
import Utils.MenuPassageiro;
import model.*;
import java.util.Scanner;

public class Teste {

    AeroportoDAO aeroportos = new AeroportoDAO();
    AdministradorDAO administradores = new AdministradorDAO();
    FuncionarioDAO funcionarios = new FuncionarioDAO();
    CompAereaDAO companhias = new CompAereaDAO();
    VooDAO voos = new VooDAO();
    PassageiroDAO passageiros = new PassageiroDAO();
    VooAssentosDAO vooAssentos = new VooAssentosDAO(voos, passageiros);
    TicketDAO tickets = new TicketDAO();
    CheckinDAO checkins = new CheckinDAO();
    DespachoBagagemDAO despachos = new DespachoBagagemDAO();
    BoardingPassDAO boardingPasses = new BoardingPassDAO();
    Scanner scanner = new Scanner(System.in);
    
    MenuPassageiro menuPassageiro = new MenuPassageiro(scanner, voos, passageiros, vooAssentos, tickets, boardingPasses, checkins);
    MenuAdministrador menuAdministrador = new MenuAdministrador(scanner, voos, passageiros, companhias, vooAssentos, tickets, administradores, boardingPasses, checkins, despachos);
    MenuFuncionario menuFuncionario = new MenuFuncionario(scanner, voos, passageiros, vooAssentos, tickets, boardingPasses, checkins, despachos);
    MenuInicial menuInicial = new MenuInicial(scanner, menuAdministrador, administradores, menuFuncionario, menuPassageiro, passageiros, voos, checkins, boardingPasses, funcionarios, vooAssentos, tickets, checkins);
    
    public static void main(String[] args) {

        new Teste();
    }

    public Teste() {
        
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    

    

    

}
