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
import model.BoardingPass;

/**
 *
 * @author vitor
 */
public class BoardingPassDAO {
    
    public boolean adicionaBoardingPass(BoardingPass boarding) {
        String sql = "insert into boardingpass (nomepassageiro, idvoo, idassento, numeroassento, idticket, datacriacao) "
                + "values (?, ?, ?, ?, ?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, boarding.getNomePassageiro());
            stmt.setString(2, boarding.getIdVoo());
            stmt.setString(3, boarding.getIdAssento());
            stmt.setInt(4, boarding.getNumeroAssento());
            stmt.setString(5, boarding.getIdTicket());
            stmt.setDate(6, java.sql.Date.valueOf(boarding.getDataCriacao()));

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public BoardingPass buscarPorIDTicket(String idTicket) {
        String sql = "select * from boardingpass where idticket = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idTicket);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BoardingPass b = new BoardingPass();
                    b.setId(rs.getInt("id"));
                    b.setNomePassageiro(rs.getString("nomepassageiro"));
                    b.setIdVoo(rs.getString("idvoo"));
                    b.setIdAssento(rs.getString("idassento"));
                    b.setNumeroAssento(rs.getInt("numeroassento"));
                    b.setIdTicket(rs.getString("idticket"));
                    b.setDataCriacao(rs.getDate("datacriacao").toLocalDate());
                    return b;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

   
    public BoardingPass buscarPorPassageiroEVoo(String nomePassageiro, String idVoo) {
        String sql = "select * from boardingpass where nomepassageiro = ? and idvoo = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nomePassageiro);
            stmt.setString(2, idVoo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BoardingPass b = new BoardingPass();
                    b.setId(rs.getInt("id"));
                    b.setNomePassageiro(rs.getString("nomepassageiro"));
                    b.setIdVoo(rs.getString("idvoo"));
                    b.setIdAssento(rs.getString("idassento"));
                    b.setNumeroAssento(rs.getInt("numeroassento"));
                    b.setIdTicket(rs.getString("idticket"));
                    b.setDataCriacao(rs.getDate("datacriacao").toLocalDate());
                    return b;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String mostrarTodos() {
        String sql = "select nomepassageiro, idvoo, idassento, numeroassento, idticket from boardingpass";
        String todos = "";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                todos
                        += rs.getString("nomepassageiro") + " - "
                        + rs.getString("idvoo") + " - "
                        + rs.getString("idassento") + " - "
                        + rs.getInt("numeroassento") + " - "
                        + rs.getString("idticket")
                        + " | ";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (todos.isEmpty()) {
            return "nenhum boarding pass foi gerado.";
        }

        return todos;
    }

}
