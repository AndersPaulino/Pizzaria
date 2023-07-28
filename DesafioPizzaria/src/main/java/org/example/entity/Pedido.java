package org.example.entity;

public class Pedido {
    private String nome;
    private Cliente cliente;
    private StatusPedido status;
    private double valor;

    public Pedido() {}

    public Pedido(String nome, Cliente cliente, StatusPedido status, double valor) {
        this.nome = nome;
        this.cliente = cliente;
        this.status = status;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
