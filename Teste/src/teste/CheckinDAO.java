/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author vitor
 */
public class CheckinDAO {
    Checkin[] check_ins = new Checkin[20];
    
    public boolean adicionaCheckin(Checkin checkin){
        int posicao = posicaoLivre();
        if(posicao != -1){
            check_ins[posicao] = checkin;
            return true;
        } else {
            return false;
        }
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosCheckin = "";
        for(int i=0; i<check_ins.length; i++){
            if(check_ins[i] != null){
                todosCheckin += check_ins[i].toString() + "---x----x---x---x---x---x---";
                vazio = false;
            }
        }
        if(vazio){
            todosCheckin += "Nenhum Check-in feito.";
        } 
        return todosCheckin;
    }
    
    private int posicaoLivre(){
        for(int i=0; i<check_ins.length; i++){
            if(check_ins[i] == null){
                return i;
            }
        }
        return -1;
    }
}
