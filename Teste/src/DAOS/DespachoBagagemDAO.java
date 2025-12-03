/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Administrador;
import model.DespachoBagagem;

/**
 *
 * @author vitor
 */
public class DespachoBagagemDAO {
    //DespachoBagagem[] despachos = new DespachoBagagem[60];
    
    
    /*
    public boolean adicionaDespachoBagagem(DespachoBagagem despacho){
        int posicao = posicaoLivre();
        if(posicao != -1){
            despachos[posicao] = despacho;
            return true;
        } else {
            return false;
        }
    }
    */
    
     public boolean adicionaDespachoBagagem(DespachoBagagem despacho) {
        String sql = "insert into despachobagagem (id, idTicket) values (?,?)";

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, despacho.getId());
            stmt.setString(2, despacho.getTicket());

            stmt.execute();
            System.out.println("Nova bagagem despachada.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    
     /*
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
    */
    
    public String mostrarTodos() {
        String sql = "select id, idTicket from despachobagagem";
        String todasBagagens = "";
        boolean vazio = true;

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                todasBagagens += rs.getString("id")
                        + " - "
                        + rs.getString("idTicket")
                        + " | ";
                vazio = false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            todasBagagens = "Nao existe nenhuma bagagem cadastrada";
        }

        return todasBagagens;
    }
    
    /*
    private int posicaoLivre(){
        for(int i=0; i<despachos.length; i++){
            if(despachos[i] == null){
                return i;
            }
        }
        return -1;
    }
    */
}
