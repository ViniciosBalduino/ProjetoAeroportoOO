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
import model.Voo;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author vinic
 */
public class VooDAO {

    private Voo construirVoo(ResultSet rs) throws SQLException {
        Voo v = new Voo(rs.getString("companhia"));
        v.setId(rs.getString("id"));
        v.setOrigem(rs.getString("origem"));
        v.setDestino(rs.getString("destino"));
        v.setData(rs.getDate("data").toLocalDate());
        v.setDuracao(rs.getTime("duracao").toLocalTime());
        v.setHorario(rs.getTime("horario").toLocalTime());
        v.setSiglaCompanhia(rs.getString("siglacompanhia"));
        v.setCapacidade(rs.getInt("capacidade"));
        v.setEstado(rs.getString("estado"));
        v.setDataCriacao(rs.getDate("datacriacao").toLocalDate());
        v.setDataModificacao(rs.getDate("datamodificacao").toLocalDate());
        return v;
    }

    public String buscarVooPorIDString(String idVoo) {
        String sql = "select * from voo where id = ?";
        String resultado = "nao existe voo com este id cadastrado";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idVoo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Voo v = construirVoo(rs);
                    resultado = v.toString();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public Voo buscarVooPorDestinoString(String destinoVoo) {
        String sql = "select * from voo where destino = ? limit 1";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, destinoVoo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirVoo(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Voo buscarVooPorPartidaString(String partidaVoo) {
        String sql = "select * from voo where origem = ? limit 1";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, partidaVoo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirVoo(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Voo buscarVooPorData(String data) {
        String sql = "select * from voo where data = ? limit 1";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(data));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirVoo(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Voo buscarRetornarVooPorID(String idVoo) {
        String sql = "select * from voo where id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idVoo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirVoo(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String mostrarTodos() {
        String sql = "select * from voo";
        String resultado = "";
        boolean vazio = true;

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Voo v = construirVoo(rs);
                resultado += v.toString() + "--------------------------";
                vazio = false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            resultado = "nao existe nenhum voo cadastrado";
        }

        return resultado;
    }

    public boolean adicionaVoo(Voo voo) {
        String sql = "insert into voo (id, origem, destino, data, duracao, horario, siglacompanhia, capacidade, estado) "
                + "values (?,?,?,?,?,?,?,?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, voo.getId());
            stmt.setString(2, voo.getOrigem());
            stmt.setString(3, voo.getDestino());
            stmt.setDate(4, java.sql.Date.valueOf(voo.getData()));
            stmt.setTime(5, java.sql.Time.valueOf(voo.getDuracao()));
            stmt.setTime(6, java.sql.Time.valueOf(voo.getHorario()));
            stmt.setString(6, voo.getSiglaCompanhia());
            stmt.setInt(7, voo.getCapacidade());
            stmt.setString(8, voo.getEstado());

            stmt.execute();
            System.out.println("Novo voo adicionado.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}
