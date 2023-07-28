package org.example.entity;

import java.util.List;

public class Pedido {
    private String nome;
    private List<Cliente> clientes;

    public Pedido() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
