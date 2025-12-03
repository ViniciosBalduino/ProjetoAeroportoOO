/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import model.Administrador;
import Utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;

/**
 *
 * @author vinic
 */
public class AdministradorDAO {

    public AdministradorDAO() {
        
    }    
    
    public Administrador buscarLoginAdministrador(String login, String senha) {
        String sql = "select * from administrador where login = ? and senha = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    Administrador admin = new Administrador();
                    admin.setId(rs.getInt("id"));
                    admin.setNome(rs.getString("nome"));
                    admin.setLogin(rs.getString("login"));
                    admin.setSenha(rs.getString("senha"));
                    admin.setNascimento(rs.getDate("nascimento").toLocalDate());
                    admin.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                    admin.setDataModificacao(rs.getDate("dataModificacao").toLocalDate());

                    return admin;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    
    public String buscarAdministradorPorID(int id) {
        String sql = "select nome, documento from administrador where id = ?";
        String resultado = "";
        boolean vazio = true;

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultado += rs.getString("nome")
                            + " - "
                            + rs.getString("documento");
                    vazio = false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            resultado = "Nao existe administrador com este ID cadastrado";
        }

        return resultado;
    }

    
    public Administrador buscarRetornarAdministradorPorID(int id) {
        String sql = "select * from administrador where id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    Administrador admin = new Administrador();

                    admin.setId(rs.getInt("id"));
                    admin.setNome(rs.getString("nome"));
                    admin.setDocumento(rs.getString("documento"));
                    admin.setLogin(rs.getString("login"));
                    admin.setSenha(rs.getString("senha"));

                    admin.setDataCriacao(rs.getDate("datacriacao").toLocalDate());
                    admin.setDataModificacao(rs.getDate("datamodificacao").toLocalDate());

                    return admin;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    public String mostrarTodosAdministradores() {
        String sql = "select nome, documento from administrador";
        String todosAdministradores = "";
        boolean vazio = true;

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                todosAdministradores += rs.getString("nome")
                        + " - "
                        + rs.getString("documento")
                        + " | ";
                vazio = false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            todosAdministradores = "Nao existe nenhum administrador cadastrado";
        }

        return todosAdministradores;
    }

    
    public boolean adicionaAdministrador(Administrador admin) {
        String sql = "insert into administrador (nome, documento, login, senha, nascimento) values (?,?,?,?,?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, admin.getNome());
            stmt.setString(2, admin.getDocumento());
            stmt.setString(3, admin.getLogin());
            stmt.setString(4, admin.getSenha());
            stmt.setDate(5, java.sql.Date.valueOf(admin.getNascimento()));

            stmt.execute();
            System.out.println("Novo administrador adicionado.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}
