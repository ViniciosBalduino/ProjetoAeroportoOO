/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import model.CompAerea;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author vinic
 */
public class CompAereaDAO {
    CompAerea[] compsAerea = new CompAerea[5];

    public CompAereaDAO() {
        
        CompAerea c1 = new CompAerea();
        c1.setNome("Companhia Galeao");
        c1.setAbreviacao("Gal");
        this.adicionaComp(c1);
        
        CompAerea c2 = new CompAerea();
        c2.setNome("Companhia Galeao 2");
        c2.setAbreviacao("Gal 2");
        this.adicionaComp(c2);
        
        CompAerea c3 = new CompAerea();
        c3.setNome("Companhia Galeao 3");
        c3.setAbreviacao("Gal 3");
        this.adicionaComp(c3);
    }    
    
    public String buscarCompPorSiglaString(String sigla){
        boolean vazio = true;
        String selecaoComps = "";
        for(int i=0; i<compsAerea.length; i++){
            if(compsAerea[i]!=null && compsAerea[i].getAbreviacao().toUpperCase().equals(sigla)){
                selecaoComps += compsAerea[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            selecaoComps = "Nao existe companhia com esta sigla cadastrada";
        }
        return selecaoComps;
    }
    
    public CompAerea buscarRetornarCompSigla(String sigla){
        boolean vazio = true;
        for(int i=0; i<compsAerea.length; i++){
            if(compsAerea[i]!=null && compsAerea[i].getAbreviacao().toUpperCase().equals(sigla)){
                return compsAerea[i];
            }
        }
        return null;
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todasComps = "";
        for(int i=0; i<compsAerea.length; i++){
            if(compsAerea[i] != null){
                todasComps += compsAerea[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            todasComps += "Nao existe nenhuma companhia cadastrada";
        } 
        return todasComps;
    }
    
    public boolean adicionaComp(CompAerea comp){
        int posicao = this.posicaoLivre();
        if(posicao != -1){
            compsAerea[posicao] = comp;
            return true;
        } else{
            return false;
        }
    }
        
    private int posicaoLivre(){
        for(int i=0; i<compsAerea.length; i++){
            if(compsAerea[i] == null){
                return i;
            }
        }
        return -1;
    }
}
