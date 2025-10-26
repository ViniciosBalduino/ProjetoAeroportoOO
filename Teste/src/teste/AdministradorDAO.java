/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;

/**
 *
 * @author vinic
 */
public class AdministradorDAO {
    Administrador[] admins = new Administrador[5];

    public AdministradorDAO() {
        
        Administrador ad1 = new Administrador();
        ad1.setNome("Vinicios Balduino");
        ad1.setNascimento(LocalDate.of(1996, 4, 26));
        ad1.setDocumento("99988877766");
        ad1.setLogin("VBalduino");
        ad1.setSenha("Admin");
        this.adicionaAdministrador(ad1);
        
        Administrador ad2 = new Administrador();
        ad2.setNome("Vitor Ferreira");
        ad2.setNascimento(LocalDate.of(1996, 4, 26));
        ad2.setDocumento("11122233344");
        ad2.setLogin("VFerreira");
        ad2.setSenha("Admin");
        this.adicionaAdministrador(ad2);
        
        Administrador ad3 = new Administrador();
        ad3.setNome("VAdministrador GEN");
        ad3.setNascimento(LocalDate.of(1996, 4, 26));
        ad3.setDocumento("00000000000");
        ad3.setLogin("ADMIN");
        ad3.setSenha("ADMIN");
        this.adicionaAdministrador(ad3);
    }    
    
    public Administrador buscarLoginAdministrador(String login, String senha){
        for(int i=0; i<admins.length; i++){
            if(admins[i]!=null && admins[i].getLogin().equals(login) && 
                    admins[i].getSenha().equals(senha)){
                return admins[i];
            }
        }
        return null;
    }
    
    public String buscarAdministradorPorID(int id){
        boolean vazio = true;
        String selecaoAdministradores = "";
        for(int i=0; i<admins.length; i++){
            if(admins[i]!=null && admins[i].getId() == id){
                selecaoAdministradores += admins[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            selecaoAdministradores = "Nao existe administrador com este ID cadastrado";
        }
        return selecaoAdministradores;
    }
    
    public Administrador buscarRetornarAdministradorPorID(int id){
        for(int i=0; i<admins.length; i++){
            if(admins[i]!=null && admins[i].getId() == id){
                return admins[i];
            }
        }
        return null;
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosAdministradores = "";
        for(int i=0; i<admins.length; i++){
            if(admins[i] != null){
                todosAdministradores += admins[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            todosAdministradores += "Nao existe nenhum administrador cadastrado";
        } 
        return todosAdministradores;
    }
    
    boolean adicionaAdministrador(Administrador admin){
        int posicao = this.posicaoLivre();
        if(posicao != -1){
            admins[posicao] = admin;
            return true;
        } else{
            return false;
        }
    }
        
    private int posicaoLivre(){
        for(int i=0; i<admins.length; i++){
            if(admins[i] == null){
                return i;
            }
        }
        return -1;
    }
}
