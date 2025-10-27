/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author vitor
 */
public class BoardingPassDAO {
    BoardingPass[] boardingPasses = new BoardingPass[60];
    
    public boolean adicionaBoardingPass(BoardingPass boarding){
        int posicao = posicaoLivre();
        if(posicao != -1){
            boardingPasses[posicao] = boarding;
            return true;
        } else {
            return false;
        }
    }
    
    public BoardingPass buscarPorIDTicket(String isTicket) {
        for (int i = 0; i < boardingPasses.length; i++) {
            if (boardingPasses[i] != null &&
                boardingPasses[i].getCodigoTicket().toLowerCase().equals(isTicket.toLowerCase())){
                return boardingPasses[i];
            }
        }
        return null;
    }
    
    public BoardingPass buscarPorPassageiroEVoo(int idPassageiro, String idVoo) {
        
        for (int i = 0; i < boardingPasses.length; i++) {
            if (boardingPasses[i] != null &&
                boardingPasses[i].getPassageiro().getId() == idPassageiro &&
                boardingPasses[i].getVoo().getId().equals(idVoo)){
                return boardingPasses[i];
            }
        }
        return null;
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosBoardingPasses = "";
        for(int i=0; i<boardingPasses.length; i++){
            if(boardingPasses[i] != null){
                todosBoardingPasses += boardingPasses[i].toString() + "---x----x---x---x---x---x---";
                vazio = false;
            }
        }
        if(vazio){
            todosBoardingPasses += "Nenhum boarding pass foi gerado.";
        } 
        return todosBoardingPasses;
    }
    
    private int posicaoLivre(){
        for(int i=0; i<boardingPasses.length; i++){
            if(boardingPasses[i] == null){
                return i;
            }
        }
        return -1;
    }
}
