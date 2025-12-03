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
import model.Passageiro;

/**
 *
 * @author vinic
 */
public class PassageiroDAO {

    public PassageiroDAO() {

    }
    
    public Passageiro buscarPassageiroPorDocumento(String documento) {
        String sql = "select * from passageiro where documento = ? limit 1";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, documento);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirPassageiro(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public Passageiro buscarLoginPassageiro(String login, String senha) {
        String sql = "select * from passageiro where login = ? and senha = ? limit 1";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirPassageiro(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String mostrarTodos() {
        String sql = "select * from passageiro";
        String resultado = "";
        boolean vazio = true;

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Passageiro p = construirPassageiro(rs);
                resultado += p.toString();
                vazio = false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            resultado = "nao existe nenhum passageiro cadastrado";
        }

        return resultado;
    }

    public boolean adicionaPassageiro(Passageiro passageiro) {
        String sql = "insert into passageiro (nome, nascimento, documento, login, senha) values (?,?,?,?,?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {

            stmt.setString(1, passageiro.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(passageiro.getNascimento()));
            stmt.setString(3, passageiro.getDocumento());
            stmt.setString(4, passageiro.getLogin());
            stmt.setString(5, passageiro.getSenha());
            stmt.execute();
            System.out.println("passageiro cadastrado.");
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Passageiro construirPassageiro(ResultSet rs) throws SQLException {
        Passageiro p = new Passageiro();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setNascimento(rs.getDate("nascimento").toLocalDate());
        p.setDocumento(rs.getString("documento"));
        p.setLogin(rs.getString("login"));
        p.setSenha(rs.getString("senha"));
        p.setDataCriacao(rs.getDate("datacriacao").toLocalDate());
        p.setDataModificacao(rs.getDate("datamodificacao").toLocalDate());
        return p;
    }

}
