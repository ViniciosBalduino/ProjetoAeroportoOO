/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import model.DespachoBagagem;

/**
 *
 * @author vitor
 */
public class DespachoBagagemDAO {
    DespachoBagagem[] despachos = new DespachoBagagem[60];
    
    public boolean adicionaDespachoBagagem(DespachoBagagem despacho){
        int posicao = posicaoLivre();
        if(posicao != -1){
            despachos[posicao] = despacho;
            return true;
        } else {
            return false;
        }
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosDespachos = "";
        for(int i=0; i<despachos.length; i++){
            if(despachos[i] != null){
                todosDespachos += despachos[i].toString() + "---x----x---x---x---x---x---";
                vazio = false;
            }
        }
        if(vazio){
            todosDespachos += "Nenhum despacho foi realizado.";
        } 
        return todosDespachos;
    }
    
    private int posicaoLivre(){
        for(int i=0; i<despachos.length; i++){
            if(despachos[i] == null){
                return i;
            }
        }
        return -1;
    }
}
