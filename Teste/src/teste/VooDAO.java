/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author vinic
 */
public class VooDAO {
    Voo[] voos = new Voo[15];

    public VooDAO(CompAereaDAO companhias) {
                        
        Voo v1 = new Voo(companhias.buscarRetornarCompSigla(("Gal").toUpperCase()));
        v1.setOrigem("Uberaba");
        v1.setDestino("Uberlandia");
        v1.setData(LocalDate.of(2025, 10, 30));
        v1.setDuracao(LocalTime.of(1, 0));
        v1.setCapacidade(5);
        this.adicionaVoo(v1);
        
        /*Voo v4 = new Voo(companhias.buscarRetornarCompSigla(("Gal").toUpperCase()));
        v1.setOrigem("Uberaba");
        v1.setDestino("Uberlandia");
        v1.setData(LocalDate.of(2025, 10, 10));
        v1.setDuracao(LocalTime.of(1, 0));
        v1.setCapacidade(5);
        this.adicionaVoo(v4);*/
               
        Voo v2 = new Voo(companhias.buscarRetornarCompSigla(("Gal 2").toUpperCase()));
        v2.setOrigem("Delta");
        v2.setDestino("Araguari");
        v2.setData(LocalDate.of(2025, 10, 10));
        v2.setDuracao(LocalTime.of(0, 10));
        v2.setCapacidade(3);
        this.adicionaVoo(v2);
        
        Voo v3 = new Voo(companhias.buscarRetornarCompSigla(("Gal 3").toUpperCase()));
        v3.setOrigem("Ponte Alta");
        v3.setDestino("Peiropolis");
        v3.setData(LocalDate.of(2025, 10, 30));
        v3.setDuracao(LocalTime.of(01, 0));
        v3.setCapacidade(1);
        this.adicionaVoo(v3);
        
    }    
    
    public String buscarVooPorIDString(String IDVoo){
        boolean vazio = true;
        String selecaoVoos = "";
        for(int i=0; i<voos.length; i++){
            if(voos[i]!=null && voos[i].getId().toUpperCase().equals((IDVoo).toUpperCase())){
                selecaoVoos += voos[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            selecaoVoos = "Nao existe voo com este ID cadastrado";
        }
        return selecaoVoos;
    }
    
    public Voo buscarVooPorDestinoString(String DestinoVoo){
        for(int i=0; i<voos.length; i++){
            if(voos[i]!=null && voos[i].getDestino().toUpperCase().equals((DestinoVoo).toUpperCase())){
                return voos[i];
            }
        }
        return null;
    }
    
        public Voo buscarVooPorPartidaString(String PartidaVoo){
        for(int i=0; i<voos.length; i++){
            if(voos[i]!=null && voos[i].getOrigem().toUpperCase().equals((PartidaVoo).toUpperCase())){
                return voos[i];
            }
        }
        return null;
    }
        
    public Voo buscarVooPorData(String data){
        for(int i=0; i<voos.length; i++){
            if(voos[i]!=null && voos[i].getData().toString().equals(data))
                return voos[i];
        }
        return null;
    }
    
    public Voo buscarRetornarVooPorID(String IDVoo){
        for(int i=0; i<voos.length; i++){
            if(voos[i]!=null && voos[i].getId().toUpperCase().equals((IDVoo).toUpperCase())){
                return voos[i];
            }
        }
        return null;
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosVoos = "";
        for(int i=0; i<voos.length; i++){
            if(voos[i] != null){
                todosVoos += voos[i].toString() + "--------------------------";
                vazio = false;
            }
        }
        if(vazio){
            todosVoos += "Nao existe nenhum voo cadastrado";
        } 
        return todosVoos;
    }
    
    boolean adicionaVoo(Voo voo){
        int posicao = this.posicaoLivre();
        if(posicao != -1){
            voos[posicao] = voo;
            return true;
        } else{
            return false;
        }
    }
        
    private int posicaoLivre(){
        for(int i=0; i<voos.length; i++){
            if(voos[i] == null){
                return i;
            }
        }
        return -1;
    }
}
