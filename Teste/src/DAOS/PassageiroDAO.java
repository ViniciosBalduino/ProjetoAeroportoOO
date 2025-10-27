/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import model.Passageiro;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author vinic
 */
public class PassageiroDAO {
    public Passageiro[] passageiros = new Passageiro[5];

    public PassageiroDAO() {
        
        Passageiro p1 = new Passageiro();
        p1.setNome("Marineide da Silva");
        p1.setNascimento(LocalDate.of(1996, 4, 26));
        p1.setDocumento("16332165499");
        p1.setLogin("MarineideGatinha");
        p1.setSenha("Marineide123");
        this.adicionaPassageiro(p1);
        
        Passageiro p2 = new Passageiro();
        p2.setNome("Flavio Sauro");
        p2.setNascimento(LocalDate.of(1996, 4, 26));
        p2.setDocumento("96385274165");
        p2.setLogin("123");
        p2.setSenha("123");
        this.adicionaPassageiro(p2);
        
        Passageiro p3 = new Passageiro();
        p3.setNome("Felicia Cerebro e Pink");
        p3.setNascimento(LocalDate.of(1996, 4, 26));
        p3.setDocumento("74185296332");
        p3.setLogin("FeliciaCP");
        p3.setSenha("OQueVamosFazerHojeCerebro");
        this.adicionaPassageiro(p3);
    }    
    
    public Passageiro buscarLoginPassageiro(String login, String senha){
        for(int i=0; i<passageiros.length; i++){
            if(passageiros[i]!=null && passageiros[i].getLogin().equals(login) && 
                    passageiros[i].getSenha().equals(senha)){
                return passageiros[i];
            }
        }
        return null;
    }
    
    public String buscarPassageiroPorID(int id){
        boolean vazio = true;
        String selecaoPassageiros = "";
        for(int i=0; i<passageiros.length; i++){
            if(passageiros[i]!=null && passageiros[i].getId() == id){
                selecaoPassageiros += passageiros[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            selecaoPassageiros = "Nao existe passageiro com este ID cadastrado";
        }
        return selecaoPassageiros;
    }
    
    public Passageiro buscarRetornarPassageiroPorID(int id){
        for(int i=0; i<passageiros.length; i++){
            if(passageiros[i]!=null && passageiros[i].getId() == id){
                return passageiros[i];
            }
        }
        return null;
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosPassageiros = "";
        for(int i=0; i<passageiros.length; i++){
            if(passageiros[i] != null){
                todosPassageiros += passageiros[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            todosPassageiros += "Nao existe nenhum passageiro cadastrado";
        } 
        return todosPassageiros;
    }
    
    public boolean adicionaPassageiro(Passageiro passageiro){
        int posicao = this.posicaoLivre();
        if(posicao != -1){
            passageiros[posicao] = passageiro;
            return true;
        } else{
            return false;
        }
    }
        
    private int posicaoLivre(){
        for(int i=0; i<passageiros.length; i++){
            if(passageiros[i] == null){
                return i;
            }
        }
        return -1;
    }
}
