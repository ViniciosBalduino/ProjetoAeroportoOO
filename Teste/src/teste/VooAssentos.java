/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author vinic
 */
public class VooAssentos {
    private static int serial;
    private String idAssento;
    private String idVoo;
    private int valor;
    private int idPassageiro;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public VooAssentos(Voo voo, Passageiro passageiro){
        int i = 0;
        if((serial)%6 == 0){
            i++;
        }
        this.idAssento = voo.getId() + "F" + i + "A" +(((serial++)%6)+1);
        this.idVoo = voo.getId();
        this.idPassageiro = passageiro.getId();
        this.valor = serial%2 == 0 ? 100 : 50;
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = LocalDate.now();
    }
    
    
    public String getIdVoo() {
        return idVoo;
    }
    
    public int getValor() {
        return valor;
    }

    public String getIdAssento() {
        return idAssento;
    }

    public int getIdPassageiro() {
        return idPassageiro;
    }

    public void setIdPassageiro(int idPassageiro) {
        this.idPassageiro = idPassageiro;
        this.dataModificacao = LocalDate.now();
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idAssento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VooAssentos other = (VooAssentos) obj;
        return Objects.equals(this.idAssento, other.idAssento);
    }
    
    @Override
    public String toString() {
        String vooAssento = "";
        vooAssento += "\nID = " + this.idAssento;
        vooAssento += "\nVoo = " + this.idVoo;
        vooAssento += "\nPassageiro = " + this.idPassageiro;
        vooAssento += "\n";
        return vooAssento;
    }
    
}
