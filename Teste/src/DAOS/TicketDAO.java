/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import model.Ticket;
import Utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author vitor
 */
public class TicketDAO {


    public boolean adicionaTicket(Ticket ticket) {
        String sql = "insert into ticket (id, valor, idvoo, nomepassageiro, idvooassento, datacriacao, datamodificacao) "
                + "values (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, ticket.getId());
            stmt.setInt(2, ticket.getValor());
            stmt.setString(3, ticket.getIdVoo());
            stmt.setString(4, ticket.getNomePassageiro());
            stmt.setString(5, ticket.getIdVooAssento());
            stmt.setDate(6, java.sql.Date.valueOf(ticket.getDataCriacao()));
            stmt.setDate(7, java.sql.Date.valueOf(ticket.getDataModificacao()));

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean removeTicketPosCheckIn(String idTicket) {
        String sql = "delete from ticket where id = ?";

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idTicket);

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket buscaTicket(String idTicket) {
        String sql = "select * from ticket where id = ? limit 1";

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idTicket);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirTicket(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Ticket buscaTicketPassageiroVoo(String nomePassageiro, String idVoo) {
        String sql = "select * from ticket where nomepassageiro = ? and idvoo = ? limit 1";

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nomePassageiro);
            stmt.setString(2, idVoo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirTicket(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String mostrarTicketsPorPassageiro(String nomePassageiro) {
        String sql = "select * from ticket where nomepassageiro = ?";
        String resultado = "";
        boolean vazio = true;

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nomePassageiro);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultado += construirTicket(rs).toString() + "\n";
                    vazio = false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            resultado = "nenhum ticket encontrado para este passageiro.";
        }

        return resultado;
    }

    public String mostrarTodos() {
        String sql = "select * from ticket";
        String todos = "";
        boolean vazio = true;

        try (Connection con = new Utils.ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                todos += construirTicket(rs).toString() + "\n---x----x---x---x---x---x---\n";
                vazio = false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            todos = "nenhum ticket foi gerado.";
        }

        return todos;
    }

    private Ticket construirTicket(ResultSet rs) throws SQLException {
        Ticket t = new Ticket();

        t.setId(rs.getString("id"));
        t.setValor(rs.getInt("valor"));
        t.setIdVoo(rs.getString("idvoo"));
        t.setNomePassageiro(rs.getString("nomepassageiro"));
        t.setIdVooAssento(rs.getString("idvooassento"));

        java.sql.Date dc = rs.getDate("datacriacao");
        if (dc != null) {
            t.setDataCriacao(dc.toLocalDate());
        }

        java.sql.Date dm = rs.getDate("datamodificacao");
        if (dm != null) {
            t.setDataModificacao(dm.toLocalDate());
        }

        return t;
    }
}
