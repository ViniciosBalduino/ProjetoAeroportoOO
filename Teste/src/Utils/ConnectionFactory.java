/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DAOS.PassageiroDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import model.Passageiro;
/**
 *
 * @author vitor
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
        try{
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "admin123");
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "America/Sao_Paulo");
            properties.setProperty("allowPublicKeyRetrieval","true");
            
            String con = "jdbc:mysql://localhost:3306/poo_aeroporto";
            return DriverManager.getConnection(con, properties);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
