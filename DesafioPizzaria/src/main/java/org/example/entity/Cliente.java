package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;

    List<Endereço> endereços = new ArrayList<>();

    public Cliente(String nome, String cpf, List<Endereço> endereços) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereços = endereços;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Endereço> getEndereços() {
        return endereços;
    }

    public void setEndereços(List<Endereço> endereços) {
        this.endereços = endereços;
    }
}
