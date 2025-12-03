/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import DAOS.PassageiroDAO;
import Utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Voo;
import model.VooAssentos;
import model.Passageiro;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import model.Administrador;

/**
 *
 * @author vinic
 */
public class VooAssentosDAO {
    //VooAssentos[] vooAssentos = new VooAssentos[100];
    
    //private VooDAO voos;
    //private PassageiroDAO passageiros;

    public VooAssentosDAO(VooDAO voos, PassageiroDAO passageiros) {
    
    }

    /*
    public int contarAssentosPorVoo(String idVoo){
        int assentosDisponiveis = 0;
        for(int i=0; i < vooAssentos.length; i++){
            if(vooAssentos[i] != null && vooAssentos[i].getIdVoo().toLowerCase().equals(idVoo.toLowerCase()))
                assentosDisponiveis++;
        }
        return assentosDisponiveis;
    }
    */
    
    public int contarAssentosPorVoo(String idVoo) {
        String sql = "select count (*) from vooassentos where idVoo = ? ";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idVoo);
            //stmt.setString(2, idPassageiro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    int qtassentos = rs.getInt("count(*)");
                    
                    return qtassentos;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
    
    /*
    public VooAssentos buscarAssentoPorVooEPassageiro(String idVoo, int idPassageiro) {
        for (int i = 0; i < vooAssentos.length; i++) {
            if (vooAssentos[i] != null &&
                vooAssentos[i].getIdVoo().toLowerCase().equals(idVoo.toLowerCase()) &&
                vooAssentos[i].getIdPassageiro() == idPassageiro) {
                return vooAssentos[i];
            }
        }
        return null;
    }*/
    
    public VooAssentos buscarAssentoPorVooEPassageiro(String idVoo, String idPassageiro) {
        String sql = "select * from vooassentos where idVoo = ? and idPassageiro = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idVoo);
            stmt.setString(2, idPassageiro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    VooAssentos voo = new VooAssentos();

                    voo.setIdAssento(rs.getString("id"));
                    voo.setIdVoo(rs.getString("idVoo"));
                    voo.setIdPassageiro(rs.getString("idPassageiro"));
                    voo.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                    voo.setDataModificacao(rs.getDate("dataModificacao").toLocalDate());
                    
                    return voo;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    /*
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
    }*/
    
    public String buscarvooAssentosPorID(String idAssento){
        String sql = "select id, idVoo, idPassageiro from vooassentos where id = ?";
        String resultado = "";
        boolean vazio = true;

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idAssento);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultado += rs.getString("id")
                            + " - "
                            + rs.getString("idVoo")
                            + " - "
                            + rs.getString("idPassageiro");
                    vazio = false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            resultado = "Nao existe assento com este ID cadastrado";
        }

        return resultado;
    }
    
    /*
    public String mostrarTodosAssentosPorVoo(Voo voo){
        boolean vazio = true;
        String todosPassageiros = "";
        for(int i=0; i<vooAssentos.length; i++){
            if(vooAssentos[i] != null && vooAssentos[i].getIdVoo().toLowerCase().equals(voo.getId().toLowerCase())){
                todosPassageiros += vooAssentos[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            todosPassageiros += "Nao existe nenhum assento neste voo cadastrado";
        } 
        return todosPassageiros;
    }
    */
    public String mostrarTodosAssentosPorVoo(String idVoo){
        String sql = "select id, idVoo, idPassageiro from vooassentos where idVoo = ?";
        String resultado = "";
        boolean vazio = true;
        //List<VooAssentos> listaAssentos = new ArrayList<>();
        
        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, idVoo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultado += rs.getString("id")
                            + " - "
                            + rs.getString("idVoo")
                            + " - "
                            + rs.getString("idPassageiro");
                    vazio = false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            resultado = "Nao existe assento ocupado neste voo";
        }
        return resultado;
    }
    
    /*
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
    */
    
    public String mostrarTodos(){
        String sql = "select id, idVoo, idPassageiro from vooassentos";
        String todosAssentos = "";
        boolean vazio = true;

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                todosAssentos += rs.getString("nome")
                        + " - "
                        + rs.getString("documento")
                        + " | ";
                vazio = false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            todosAssentos = "Nao existe nenhum assento cadastrado";
        }

        return todosAssentos;
    }
    /*
    public boolean adicionaVooAssentos(VooAssentos vooAssento){
        int posicao = this.posicaoLivre();
        if(posicao != -1){
            vooAssentos[posicao] = vooAssento;
            return true;
        } else{
            return false;
        }
    }*/
    
    public boolean adicionaVooAssentos(VooAssentos vooAssento) {
        String sql = "insert into vooassentos (id, idVoo, idPassageiro) values (?,?,?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, vooAssento.getIdAssento());
            stmt.setString(2, vooAssento.getIdVoo());
            stmt.setString(3, vooAssento.getIdPassageiro());
            
            stmt.execute();
            System.out.println("Novo assento cadastrado.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    
    /*    
    private int posicaoLivre(){
        for(int i=0; i<vooAssentos.length; i++){
            if(vooAssentos[i] == null){
                return i;
            }
        }
        return -1;
    }*/
}
