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
import model.Funcionario;

/**
 *
 * @author vitor
 */
public class FuncionarioDAO {

    public FuncionarioDAO() {

    }

    public Funcionario buscarLoginFuncionario(String login, String senha) {
        String sql = "select * from funcionario where login = ? and senha = ? limit 1";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirFuncionario(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public boolean adicionaFuncionario(Funcionario func) {
        String sql = "insert into funcionario (nome, nascimento, documento, login, senha, datacriacao, datamodificacao) "
                + "values (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, func.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(func.getNascimento()));
            stmt.setString(3, func.getDocumento());
            stmt.setString(4, func.getLogin());
            stmt.setString(5, func.getSenha());
            stmt.setDate(6, java.sql.Date.valueOf(func.getDataCriacao()));
            stmt.setDate(7, java.sql.Date.valueOf(func.getDataModificacao()));

            int linhas = stmt.executeUpdate();
            System.out.println("Novo funcionario adicionado.");
            return linhas > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostrarTodos() {
        String sql = "select * from funcionario";
        String todosFuncionarios = "";
        boolean vazio = true;

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = construirFuncionario(rs);
                todosFuncionarios += f.toString();
                vazio = false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (vazio) {
            todosFuncionarios = "nao existe nenhum funcionario cadastrado";
        }

        return todosFuncionarios;
    }

    private Funcionario construirFuncionario(ResultSet rs) throws SQLException {
        Funcionario f = new Funcionario();
        f.setId(rs.getInt("id"));
        f.setNome(rs.getString("nome"));
        java.sql.Date nascimentoSql = rs.getDate("nascimento");
        if (nascimentoSql != null) {
            f.setNascimento(nascimentoSql.toLocalDate());
        }
        f.setDocumento(rs.getString("documento"));
        f.setLogin(rs.getString("login"));
        f.setSenha(rs.getString("senha"));
        java.sql.Date dc = rs.getDate("datacriacao");
        if (dc != null) {
            f.setDataCriacao(dc.toLocalDate());
        }
        java.sql.Date dm = rs.getDate("datamodificacao");
        if (dm != null) {
            f.setDataModificacao(dm.toLocalDate());
        }
        return f;
    }

}
