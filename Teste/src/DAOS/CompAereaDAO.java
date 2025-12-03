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
import model.CompAerea;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import model.Administrador;

/**
 *
 * @author vinic
 */
public class CompAereaDAO {
    CompAerea[] compsAerea = new CompAerea[5];

    public CompAereaDAO() {}

    /*
    public CompAerea buscarRetornarCompSigla(String sigla){
        boolean vazio = true;
        for(int i=0; i<compsAerea.length; i++){
            if(compsAerea[i]!=null && compsAerea[i].getAbreviacao().toUpperCase().equals(sigla)){
                return compsAerea[i];
            }
        }
        return null;
    }
    */
    
    public CompAerea buscarRetornarCompSigla(String sigla) {
        String sql = "select * from companhiaaerea where abreviacao = ?";

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, sigla);
 
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    CompAerea comp = new CompAerea();
                    comp.setId(rs.getString("id"));
                    comp.setNome(rs.getString("nome"));
                    comp.setAbreviacao(rs.getString("abreviacao"));
                    comp.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                    comp.setDataModificacao(rs.getDate("dataModificacao").toLocalDate());
                    return comp;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    /*public boolean buscarCompPorSiglaString(String sigla){
        boolean vazio = true;
        String selecaoComps = "";
        for(int i=0; i<compsAerea.length; i++){
            if(compsAerea[i]!=null && compsAerea[i].getAbreviacao().toUpperCase().equals(sigla)){
                selecaoComps += compsAerea[i].toString();
                vazio = false;
                return true;
            }
        }
        return false;
    }
    */
    
    public boolean buscarCompPorSiglaString(String sigla){
        String sql = "select * from companhiaaerea where abreviacao = ?";
        String resultado = "";
        boolean vazio = true;

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, sigla);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultado += rs.getString("id")
                            + " - "
                            + rs.getString("nome")
                            + " - "
                            + rs.getString("abreviacao");
                    vazio = false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            return false;
        }

        return true;
    }
    
    /*
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
*/
    
    public String mostrarTodos() {
        String sql = "select nome, abreviacao from companhiaaerea";
        String todasCompanhias = "";
        boolean vazio = true;

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                todasCompanhias += rs.getString("nome")
                        + " - "
                        + rs.getString("abreviacao")
                        + " | ";
                vazio = false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            todasCompanhias = "Nao existe nenhuma companhia cadastrada";
        }

        return todasCompanhias;
    }
    /*
    public boolean adicionaComp(CompAerea comp){
        int posicao = this.posicaoLivre();
        if(posicao != -1){
            compsAerea[posicao] = comp;
            return true;
        } else{
            return false;
        }
    }
    */
    
    public boolean adicionaComp(CompAerea comp) {
        String sql = "insert into companhiaaerea (id, nome, abreviacao) values (?,?,?)";

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, comp.getId());
            stmt.setString(2, comp.getNome());
            stmt.setString(3, comp.getAbreviacao());
            
            stmt.execute();
            System.out.println("Nova companhia adicionada.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
       
    /*
    private int posicaoLivre(){
        for(int i=0; i<compsAerea.length; i++){
            if(compsAerea[i] == null){
                return i;
            }
        }
        return -1;
    }
    */
}
