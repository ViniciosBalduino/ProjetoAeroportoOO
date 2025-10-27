/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import model.Aeroporto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author vinic
 */
public class AeroportoDAO {
    Aeroporto[] aeroportos = new Aeroporto[5];

    public AeroportoDAO() {
        
        Aeroporto a1 = new Aeroporto();
        a1.setNome("Aeroporto Galeao");
        a1.setAbreviacao("GAL");
        a1.setCidade("Rio de Janeiro");
        this.adicionaAeroporto(a1);
        
        Aeroporto a2 = new Aeroporto();
        a2.setNome("Aeroporto Galeao 2");
        a2.setAbreviacao("Gal2");
        a2.setCidade("Rio de Janeiro 2");
        this.adicionaAeroporto(a2);
        
        Aeroporto a3 = new Aeroporto();
        a3.setNome("Aeroporto Galeao 3");
        a3.setAbreviacao("Gal3");
        a3.setCidade("Rio de Janeiro 3");
        this.adicionaAeroporto(a3);
    }    
    
    public String buscarAeroportoPorSiglaString(String sigla){
        boolean vazio = true;
        String selecaoAeroportos = "";
        for(int i=0; i<aeroportos.length; i++){
            if(aeroportos[i]!=null && aeroportos[i].getAbreviacao().toUpperCase().equals(sigla)){
                selecaoAeroportos += aeroportos[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            selecaoAeroportos = "Nao existe aeroporto com esta sigla cadastrado";
        }
        return selecaoAeroportos;
    }
    
    public Aeroporto buscarRetornarAeroportoPorSigla(String sigla){
        for(int i=0; i<aeroportos.length; i++){
            if(aeroportos[i]!=null && aeroportos[i].getAbreviacao().toUpperCase().equals(sigla)){
                return aeroportos[i];

            }
        }
        return null;
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosAeroportos = "";
        for(int i=0; i<aeroportos.length; i++){
            if(aeroportos[i] != null){
                todosAeroportos += aeroportos[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            todosAeroportos += "Nao existe nenhum aeroporto cadastrado";
        } 
        return todosAeroportos;
    }
    
    boolean adicionaAeroporto(Aeroporto aeroporto){
        int posicao = this.posicaoLivre();
        if(posicao != -1){
            aeroportos[posicao] = aeroporto;
            return true;
        } else{
            return false;
        }
    }
        
    private int posicaoLivre(){
        for(int i=0; i<aeroportos.length; i++){
            if(aeroportos[i] == null){
                return i;
            }
        }
        return -1;
    }
}
