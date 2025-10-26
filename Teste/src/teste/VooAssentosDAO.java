/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author vinic
 */
public class VooAssentosDAO {
    VooAssentos[] vooAssentos = new VooAssentos[100];
    
    private VooDAO voos;
    private PassageiroDAO passageiros;

    public VooAssentosDAO(VooDAO voos, PassageiroDAO passageiros) {
        
        this.voos = voos;
        this.passageiros = passageiros;
        
        VooAssentos v1 = new VooAssentos(voos.buscarRetornarVooPorID("Gal-V1"), passageiros.buscarRetornarPassageiroPorID(1));
        //v1.setIdPassageiro(passageiros.buscarRetornarPassageiroPorID(1).getId());
        //System.out.println(v1);
        this.adicionaVooAssentos(v1);
      
        VooAssentos v2 = new VooAssentos(voos.buscarRetornarVooPorID("Gal-V1"), passageiros.buscarRetornarPassageiroPorID(2));
        //v2.setIdPassageiro(passageiros.buscarRetornarPassageiroPorID(2).getId());
        this.adicionaVooAssentos(v2);
        
        VooAssentos v3 = new VooAssentos(voos.buscarRetornarVooPorID("Gal 2-V2"), passageiros.buscarRetornarPassageiroPorID(3));
        //v1.setIdPassageiro(passageiros.buscarRetornarPassageiroPorID(3).getId());
        this.adicionaVooAssentos(v3);
        //System.out.println(vooAssentos[0]);
    }

    public int contarAssentosPorVoo(String idVoo){
        int assentosDisponiveis = 0;
        for(int i=0; i < vooAssentos.length; i++){
            if(vooAssentos[i] != null && vooAssentos[i].getIdVoo().toLowerCase().equals(idVoo.toLowerCase()))
                assentosDisponiveis++;
        }
        return assentosDisponiveis;
    }
    
    public VooAssentos buscarAssentoPorVooEPassageiro(String idVoo, int idPassageiro) {
        for (int i = 0; i < vooAssentos.length; i++) {
            if (vooAssentos[i] != null &&
                vooAssentos[i].getIdVoo().toLowerCase().equals(idVoo.toLowerCase()) &&
                vooAssentos[i].getIdPassageiro() == idPassageiro) {
                return vooAssentos[i];
            }
        }
        return null;
    }
    
    public String buscarvooAssentosPorID(String idAssento){
        boolean vazio = true;
        String selecaoAssentos = "";
        for(int i=0; i<vooAssentos.length; i++){
            if(vooAssentos[i]!=null && vooAssentos[i].getIdAssento().toUpperCase().equals((idAssento).toUpperCase())){
                selecaoAssentos += vooAssentos[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            selecaoAssentos = "Nao existe assento com este ID cadastrado";
        }
        return selecaoAssentos;
    }
    
    public String mostrarTodosAssentosPorVoo(Voo voo){
        boolean vazio = true;
        String todosPassageiros = "";
        for(int i=0; i<vooAssentos.length; i++){
            if(vooAssentos[i] != null && vooAssentos[i].getIdVoo().toLowerCase().equals(voo.getId().toLowerCase())){
                todosPassageiros += vooAssentos[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            todosPassageiros += "Nao existe nenhum assento neste voo cadastrado";
        } 
        return todosPassageiros;
    }
    
    //Relatorio 1: Passageiros que deixaram um determinado aeroporto.
    public String gerarRelatorioPassageirosPorOrigem(String origem) {
        String relatorio = "--- Passageiros que sairam de " + origem.toUpperCase() + " ---\n";
        boolean encontrou = false;

        // Itera por todos os assentos vendidos
        for (int i = 0; i < vooAssentos.length; i++) {
            if (vooAssentos[i] != null) {
                VooAssentos assento = vooAssentos[i];
                // Busca o Voo deste assento
                Voo voo = this.voos.buscarRetornarVooPorID(assento.getIdVoo()); //

                // Verifica se o Voo existe e se a origem bate
                if (voo != null && voo.getOrigem().equalsIgnoreCase(origem)) {
                    // Busca o Passageiro deste assento
                    Passageiro p = this.passageiros.buscarRetornarPassageiroPorID(assento.getIdPassageiro()); //

                    if (p != null) {
                        relatorio += "ID: " + p.getId() + ", Nome: " + p.getNome()
                                + ", Documento: " + p.getDocumento()
                                + " (Voo: " + voo.getId() + ")\n";
                        encontrou = true;
                    }
                }
            }
        }

        if (!encontrou) {
            return "Nenhum passageiro encontrado para esta origem.";
        }
        return relatorio;
    }
    
    public String gerarRelatorioPassageirosPorDestino(String destino) {
        String relatorio = "--- Passageiros que chegaram em " + destino.toUpperCase() + " ---\n";
        boolean encontrou = false;

        for (int i = 0; i < vooAssentos.length; i++) {
            if (vooAssentos[i] != null) {
                VooAssentos assento = vooAssentos[i];
                Voo voo = this.voos.buscarRetornarVooPorID(assento.getIdVoo());

                // A unica mudanca e aqui:
                if (voo != null && voo.getDestino().equalsIgnoreCase(destino)) {
                    Passageiro p = this.passageiros.buscarRetornarPassageiroPorID(assento.getIdPassageiro());

                    if (p != null) {
                        relatorio += "ID: " + p.getId() + ", Nome: " + p.getNome()
                                + ", Documento: " + p.getDocumento()
                                + " (Voo: " + voo.getId() + ")\n";
                        encontrou = true;
                    }
                }
            }
        }

        if (!encontrou) {
            return "Nenhum passageiro encontrado para este destino.";
        }
        return relatorio;
    }
    
    public int calcularArrecadacao(String siglaCompanhia, LocalDate inicio, LocalDate fim) {
        int totalArrecadado = 0;

        for (int i = 0; i < vooAssentos.length; i++) {
            if (vooAssentos[i] != null) {
                VooAssentos assento = vooAssentos[i];
                Voo voo = this.voos.buscarRetornarVooPorID(assento.getIdVoo());

                if (voo != null) {
                    boolean companhiaMatch = voo.getCompanhia().getAbreviacao().equalsIgnoreCase(siglaCompanhia);

                    LocalDate dataVenda = assento.getDataCriacao();

                    // Verifica se a data esta no intervalo (inclusivo)
                    boolean dataMatch = (!dataVenda.isBefore(inicio)) && (!dataVenda.isAfter(fim));

                    if (companhiaMatch && dataMatch) {
                        totalArrecadado += assento.getValor();
                    }
                }
            }
        }
        return totalArrecadado;
    }
    
    public String mostrarTodos(){
        boolean vazio = true;
        String todosPassageiros = "";
        for(int i=0; i<vooAssentos.length; i++){
            if(vooAssentos[i] != null){
                todosPassageiros += vooAssentos[i].toString();
                vazio = false;
            }
        }
        if(vazio){
            todosPassageiros += "Nao existe nenhum assento neste voo cadastrado";
        } 
        return todosPassageiros;
    }
    
    boolean adicionaVooAssentos(VooAssentos vooAssento){
        int posicao = this.posicaoLivre();
        if(posicao != -1){
            vooAssentos[posicao] = vooAssento;
            return true;
        } else{
            return false;
        }
    }
        
    private int posicaoLivre(){
        for(int i=0; i<vooAssentos.length; i++){
            if(vooAssentos[i] == null){
                return i;
            }
        }
        return -1;
    }
}
