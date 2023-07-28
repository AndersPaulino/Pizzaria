package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;

    List<Endereco> endereços = new ArrayList<>();

    public Cliente(String nome, String cpf, List<Endereco> endereços) {
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

    public List<Endereco> getEndereços() {
        return endereços;
    }

    public void setEndereços(List<Endereco> endereços) {
        this.endereços = endereços;
    }
}
