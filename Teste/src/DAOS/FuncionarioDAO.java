/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import model.Funcionario;
import java.time.LocalDate;

/**
 *
 * @author vitor
 */
public class FuncionarioDAO {
    Funcionario[] funcionarios = new Funcionario[5];
    
    public FuncionarioDAO() {
        Funcionario f1 = new Funcionario();
        f1.setNome("Ana Clara Souza");
        f1.setNascimento(LocalDate.of(1990, 10, 15));
        f1.setDocumento("11122233344");
        f1.setLogin("ana");
        f1.setSenha("ana123");
        this.adicionaFuncionario(f1);

        Funcionario f2 = new Funcionario();
        f2.setNome("Bruno Silva");
        f2.setNascimento(LocalDate.of(1985, 7, 30));
        f2.setDocumento("55566677788");
        f2.setLogin("bruno");
        f2.setSenha("bruno123");
        this.adicionaFuncionario(f2);

        Funcionario f3 = new Funcionario();
        f3.setNome("Carla Mendes");
        f3.setNascimento(LocalDate.of(2001, 1, 12));
        f3.setDocumento("99900011122");
        f3.setLogin("carla");
        f3.setSenha("carla123");
        this.adicionaFuncionario(f3);
        
        Funcionario f4 = new Funcionario();
        f4.setNome("joao");
        f4.setNascimento(LocalDate.of(2001, 1, 12));
        f4.setDocumento("99900011122");
        f4.setLogin("123");
        f4.setSenha("123");
        this.adicionaFuncionario(f4);
    }
    
    public Funcionario buscarLoginFuncionario(String login, String senha){
        for(int i=0; i<funcionarios.length; i++){
            if(funcionarios[i]!=null && funcionarios[i].getLogin().equals(login) && 
                    funcionarios[i].getSenha().equals(senha)){
                return funcionarios[i];
            }
        }
        return null;
    }
    
    boolean adicionaFuncionario(Funcionario func){
        int posicao = this.posicaoLivre();
        if(posicao != -1){
            funcionarios[posicao] = func;
            return true;
        } else {
            return false;
        }
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosFuncionarios = "";
        for(int i=0; i<funcionarios.length; i++){
            if(funcionarios[i] != null){
                todosFuncionarios += funcionarios[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            todosFuncionarios += "Nao existe nenhum funcionario cadastrado";
        } 
        return todosFuncionarios;
    }
    
    private int posicaoLivre(){
        for(int i=0; i<funcionarios.length; i++){
            if(funcionarios[i] == null){
                return i;
            }
        }
        return -1;
    }
}
