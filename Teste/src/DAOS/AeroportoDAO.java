/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Aeroporto;

/**
 *
 * @author vinic
 */
public class AeroportoDAO {

    public AeroportoDAO() {
        
    }    
    
    public String buscarAeroportoPorSiglaString(String sigla){
        String sql = "select nome from aeroporto where abreviacao = ?";
        String aeroportoComEssaSigla = "";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);){
            
            stmt.setString(1, sigla);
            try(ResultSet rs = stmt.executeQuery();){
                while(rs.next()){
                    aeroportoComEssaSigla += rs.getString("nome") + " | ";
                }
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        if(aeroportoComEssaSigla.equalsIgnoreCase("")){
            return "Nenhum aeroporto encontrado";
        }
        return aeroportoComEssaSigla;
    }
    
    public Aeroporto buscarRetornarAeroportoPorSigla(String sigla){
        String sql = "select * from aeroporto where abreviacao = ?";
        Aeroporto aeroportoComEssaSigla = new Aeroporto();
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);){
            
            stmt.setString(1, sigla);
            try(ResultSet rs = stmt.executeQuery();){
                if(rs.next()){
                    aeroportoComEssaSigla.setNome(rs.getString("nome"));
                    aeroportoComEssaSigla.setCidade(rs.getString("cidade"));
                    aeroportoComEssaSigla.setAbreviacao(rs.getString("abreviacao"));
                    aeroportoComEssaSigla.setId(rs.getInt("id"));
                    aeroportoComEssaSigla.setDataModificacao(rs.getDate("dataModificacao").toLocalDate());
                    aeroportoComEssaSigla.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                    
                    return aeroportoComEssaSigla;
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }
    
    public String mostrarTodosAeroportos(){
        String sql = "select nome, abreviacao from aeroporto";
        String todosAeroportos = "";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();){
            
            while(rs.next()){
                todosAeroportos += rs.getString("nome") + "-" + rs.getString("abreviacao") + " | ";
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return todosAeroportos;
    }
    
    public boolean adicionaAeroporto(Aeroporto aeroporto){
        String sql = "insert into aeroporto (nome, cidade, abreviacao) values (?,?,?)";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);){
            
            stmt.setString(1, aeroporto.getNome());
            stmt.setString(2, aeroporto.getCidade());
            stmt.setString(3, aeroporto.getAbreviacao());
            stmt.execute();
            System.out.println("Novo aeroporto adicionado.");
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return true;
    }
    
}
