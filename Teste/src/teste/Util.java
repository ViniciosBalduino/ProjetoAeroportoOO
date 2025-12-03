/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import model.Passageiro;
import model.Funcionario;
import model.Administrador;
import java.time.LocalDate;

public class Util {
    private static Passageiro passageiroLogado = null;
    private static Administrador administradorLogado = null;
    private static Funcionario funcionarioLogado = null;
    
    private static LocalDate diaAtual = LocalDate.now();

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
    
    public static Funcionario getFuncionarioLogado(){
        return funcionarioLogado;
    }
    
    public static void setFuncionarioLogado(Funcionario funcionarioLogado){
        Util.funcionarioLogado = funcionarioLogado;
    }

    public static LocalDate getDiaAtual() {
        return diaAtual;
    }
    
    public static int getDiaDoMes() {
        return diaAtual.getDayOfMonth();
    }
}