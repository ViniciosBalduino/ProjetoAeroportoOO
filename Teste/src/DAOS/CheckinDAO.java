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
import model.Checkin;

/**
 *
 * @author vitor
 */
public class CheckinDAO {

    public boolean adicionaCheckin(Checkin checkin) {
        String sql = "insert into checkin (idticket, documento, estado, datacriacao, datamodificacao) "
                + "values (?, ?, ?, ?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, checkin.getIdTicket());
            stmt.setString(2, checkin.getDocumento());
            stmt.setString(3, checkin.getEstado());
            stmt.setDate(4, java.sql.Date.valueOf(checkin.getDataCriacao()));
            stmt.setDate(5, java.sql.Date.valueOf(checkin.getDataModificacao()));

            int linhas = stmt.executeUpdate();
            System.out.println("Novo check-in adicionado.");
            return linhas > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Checkin retornaCheckInIDTicket(String idTicket) {
        String sql = "select * from checkin where idticket = ? limit 1";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idTicket);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirCheckin(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public Checkin buscarCheckinPorDocumento(String documento) {
        String sql = "select * from checkin where documento = ? limit 1";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, documento);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirCheckin(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public boolean atualizarCheckin(Checkin checkin) {
        String sql = "update checkin set idticket = ?, documento = ?, estado = ?, "
                + "datamodificacao = ? where id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, checkin.getIdTicket());
            stmt.setString(2, checkin.getDocumento());
            stmt.setString(3, checkin.getEstado());

            stmt.setDate(4, java.sql.Date.valueOf(checkin.getDataModificacao()));
            stmt.setInt(5, checkin.getId());

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostrarTodos() {
        String sql = "select * from checkin";
        boolean vazio = true;
        String todos = "";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Checkin c = construirCheckin(rs);
                todos += c.toString() + "\n---x----x---x---x---x---x---\n";
                vazio = false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            todos = "nenhum check-in feito.";
        }

        return todos;
    }

    private Checkin construirCheckin(ResultSet rs) throws SQLException {
        Checkin c = new Checkin();
        c.setId(rs.getInt("id"));
        c.setIdTicket(rs.getString("idticket"));
        c.setDocumento(rs.getString("documento"));
        c.setEstado(rs.getString("estado"));

        java.sql.Date dc = rs.getDate("datacriacao");
        if (dc != null) {
            c.setDataCriacao(dc.toLocalDate());
        }

        java.sql.Date dm = rs.getDate("datamodificacao");
        if (dm != null) {
            c.setDataModificacao(dm.toLocalDate());
        }

        return c;
    }
}
