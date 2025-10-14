/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Util {
    private static Passageiro passageiroLogado = null;
    private static Administrador administradorLogado = null;
    
    private static LocalDateTime diaAtual = LocalDateTime.of(2023, Month.MARCH, 01, 22, 05);

    public static Passageiro getPassageiroLogado() {
        return passageiroLogado;
    }

    public static void setPassageiroLogado(Passageiro passageiroLogado) {
        Util.passageiroLogado = passageiroLogado;
    }
  
    public static Administrador getAdministradorLogado() {
        return administradorLogado;
    }

    public static void setAdministradorLogado(Administrador administradorLogado) {
        Util.administradorLogado = administradorLogado;
    }

    public static LocalDateTime getDiaAtual() {
        return diaAtual;
    }
    
    public static int getDiaDoMes() {
        return diaAtual.getDayOfMonth();
    }

    public static void incrementaDias(int dias) {
        diaAtual.plusDays(dias);
    }
    
    public static void incrementaMes(int numeroMeses) {
        diaAtual.plusMonths(numeroMeses);
    }
}