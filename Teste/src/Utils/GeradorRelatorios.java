/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.time.LocalDate;
import model.Passageiro;
import model.Voo;
import model.VooAssentos;

/**
 *
 * @author vitor
 */
public class GeradorRelatorios {
    //Relatorio 1: Passageiros que deixaram um determinado aeroporto.
    public String gerarRelatorioPassageirosPorOrigem(String origem) {
        
        return "0";
    }
    
    //Relatorio 2: Passageiros que chegaram em um determinado aeroporto.
    public String gerarRelatorioPassageirosPorDestino(String destino) {
        
        return "1";
    }
    
    //Relatorio 3: Total arrecadado por uma companhia em um periodo.
    public int calcularArrecadacao(String siglaCompanhia, LocalDate inicio, LocalDate fim) {
        
        return 4;
    }
}
