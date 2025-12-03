/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DAOS.BoardingPassDAO;
import DAOS.CheckinDAO;
import DAOS.CompAereaDAO;
import DAOS.PassageiroDAO;
import DAOS.VooDAO;
import java.time.LocalDate;
import model.Passageiro;
import model.Voo;
import model.VooAssentos;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vitor
 */
public class GeradorRelatorios {
    //Relatorio 1: Passageiros que deixaram um determinado aeroporto.
    
    public boolean gerarRelatorioVoos() throws IOException{
        
        Path path = Paths.get("src/files/RelatorioVoos.txt");
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            System.err.println("already exists: " + e.getMessage());
        }
        VooDAO voos = new VooDAO();
        String resultado = "Relatorio de Voos do dia: "  + String.valueOf(LocalDate.now()) + "\n";
        resultado += voos.mostrarTodos();
        
        Files.write(path, resultado.getBytes(),StandardOpenOption.APPEND);
             
        return true;
    }
    
    //Relatorio 2: Passageiros que chegaram em um determinado aeroporto.
    public boolean gerarRelatorioPassageiros() throws IOException{
        
        Path path = Paths.get("src/files/RelatorioPassageiros.txt");
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            System.err.println("already exists: " + e.getMessage());
        }
        PassageiroDAO passageiros = new PassageiroDAO();
        
        String resultado = "Relatorio de Passageiros do dia: "  + String.valueOf(LocalDate.now()) + "\n";
        resultado += passageiros.mostrarTodos();
        
        Files.write(path, resultado.getBytes(),StandardOpenOption.APPEND);
             
        return true;
    }
    
    
    public boolean gerarRelatorioCompanhias() throws IOException{
        
        Path path = Paths.get("src/files/RelatorioCompanhias.txt");
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            System.err.println("already exists: " + e.getMessage());
        }
        CompAereaDAO companhias = new CompAereaDAO();
                
        String resultado = "Relatorio de Companhias do dia: "  + String.valueOf(LocalDate.now()) + "\n";
        resultado += companhias.mostrarTodos();
        
        Files.write(path, resultado.getBytes(),StandardOpenOption.APPEND);
        return true;
    }
    
    public boolean gerarRelatorioChekin() throws IOException{
        
        Path path = Paths.get("src/files/RelatorioCheckin.txt");
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            System.err.println("already exists: " + e.getMessage());
        }
        CheckinDAO chekins = new CheckinDAO();
                
        String resultado = "Relatorio de Checkin do dia: "  + String.valueOf(LocalDate.now()) + "\n";
        resultado += chekins.mostrarTodos();
        
        Files.write(path, resultado.getBytes(),StandardOpenOption.APPEND);
        return true;
    }
    
     public boolean gerarRelatorioBoarding() throws IOException{
        
        Path path = Paths.get("src/files/RelatorioBoarding.txt");
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            System.err.println("already exists: " + e.getMessage());
        }
         BoardingPassDAO boards = new BoardingPassDAO();
                
        String resultado = "Relatorio de BoardingPass do dia: "  + String.valueOf(LocalDate.now()) + "\n";
        resultado += boards.mostrarTodos();
        
        Files.write(path, resultado.getBytes(),StandardOpenOption.APPEND);
        return true;
    }
    
    //Relatorio 3: Total arrecadado por uma companhia em um periodo.
    public int calcularArrecadacao(String siglaCompanhia, LocalDate inicio, LocalDate fim) {
        
        return 4;
    }
}
