/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author vinic
 */
public class VooAssentosDAO {
    VooAssentos[] vooAssentos = new VooAssentos[5];

    public VooAssentosDAO(VooDAO voos, PassageiroDAO passageiros) {
        
        VooAssentos v1 = new VooAssentos(voos.buscarRetornarVooPorID("Gal-V1"), passageiros.buscarRetornarPassageiroPorID(1));
        v1.setIdPassageiro(passageiros.buscarRetornarPassageiroPorID(1).getId());
        this.adicionaVooAssentos(v1);
      
        VooAssentos v2 = new VooAssentos(voos.buscarRetornarVooPorID("Gal-V1"), passageiros.buscarRetornarPassageiroPorID(2));
        v2.setIdPassageiro(passageiros.buscarRetornarPassageiroPorID(2).getId());
        this.adicionaVooAssentos(v2);
        
        VooAssentos v3 = new VooAssentos(voos.buscarRetornarVooPorID("Gal 2-V2"), passageiros.buscarRetornarPassageiroPorID(3));
        v1.setIdPassageiro(passageiros.buscarRetornarPassageiroPorID(3).getId());
        this.adicionaVooAssentos(v3);
    }

    public int contarAssentosPorVoo(String idVoo){
        int assentosDisponiveis = 0;
        for(int i=0; i < vooAssentos.length; i++){
            if(vooAssentos[i].getIdVoo() == idVoo)
                assentosDisponiveis++;
        }
        return assentosDisponiveis;
    }
    
    public VooAssentos buscarAssentoPorVooEPassageiro(String idVoo, int idPassageiro) {
        for (int i = 0; i < vooAssentos.length; i++) {
            if (vooAssentos[i] != null &&
                vooAssentos[i].getIdVoo().equals(idVoo) &&
                vooAssentos[i].getIdPassageiro() == idPassageiro) {
                return vooAssentos[i];
            }
        }
        return null;
    }
    
    public String buscarvooAssentosPorID(String idAssento){
        boolean vazio = true;
        String selecaoAssentos = "";
        for(int i=0; i<vooAssentos.length; i++){
            if(vooAssentos[i]!=null && vooAssentos[i].getIdAssento().toUpperCase().equals((idAssento).toUpperCase())){
                selecaoAssentos += vooAssentos[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            selecaoAssentos = "Nao existe assento com este ID cadastrado";
        }
        return selecaoAssentos;
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosPassageiros = "";
        for(int i=0; i<vooAssentos.length; i++){
            if(vooAssentos[i] != null){
                todosPassageiros += vooAssentos[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            todosPassageiros += "Nao existe nenhum assento neste voo cadastrado";
        } 
        return todosPassageiros;
    }
    
    boolean adicionaVooAssentos(VooAssentos vooAssento){
        int posicao = this.posicaoLivre();
        if(posicao != -1){
            vooAssentos[posicao] = vooAssento;
            return true;
        } else{
            return false;
        }
    }
        
    private int posicaoLivre(){
        for(int i=0; i<vooAssentos.length; i++){
            if(vooAssentos[i] == null){
                return i;
            }
        }
        return -1;
    }
}
