package org.example;

import org.example.entity.Cliente;
import org.example.entity.Endereco;
import org.example.entity.Pedido;
import org.example.entity.StatusPedido;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Cliente> clientes = new ArrayList<>();
    static List<Endereco> enderecos = new ArrayList<>();

    static List<Pedido> pedidos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 1;

        while (opcao != 0) {
            menu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    pedido();
                    break;
                case 3:
                    verPedidos();
                    break;
                case 4:
                    buscar();
                    break;
                case 5:
                    entregar();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
    public static void menu() {
        System.out.println("Menu");
        System.out.println("1 - Cadastrar novo cliente!");
        System.out.println("2 - Novo Pedido!");
        System.out.println("3 - Pedidos!");
        System.out.println("4 - Dados do Cliente");
        System.out.println("5 - Entregar");
        System.out.println("0 - Sair");
        System.out.println("Digite o número da opção desejada:");
    }
    public static void cadastrar(){
        System.out.println("Cadastrar");
        System.out.println("Digite o Nome!");
        String nome = scanner.next();
        System.out.println("Digite o Cpf!");
        String cpf = scanner.next();

        System.out.println("Digite nome do Bairro!");
        String bairro = scanner.next();
        System.out.println("Digite o nome da Rua!");
        String rua = scanner.next();
        System.out.println("Digite o numero da Casa!");
        int numero = scanner.nextInt();

        enderecos.add(new Endereco(bairro,rua, numero));

        clientes.add(new Cliente(nome,cpf,enderecos));
    }
    public static void pedido() {
        System.out.println("Novo Pedido");
        System.out.println("Digite o nome do cliente:");
        String nomeCliente = scanner.next();
        Cliente clienteEncontrado = null;
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeCliente)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            System.out.println("Digite o nome do prato:");
            String nomePrato = scanner.next();
            System.out.println("Digite o valor do pedido:");
            double valorPedido = scanner.nextDouble();

            Pedido pedido = new Pedido(nomePrato, clienteEncontrado, StatusPedido.PENDENTE, valorPedido);
            pedidos.add(pedido);
            System.out.println("Pedido criado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void verPedidos(){

    }
    public static void buscar() {
        System.out.println("Digite o nome do cliente para buscar:");
        String nomeBusca = scanner.next();
        boolean clienteEncontrado = false;
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeBusca)) {
                System.out.println("Cliente encontrado:");
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("CPF: " + cliente.getCpf());
                List<Endereco> enderecosPessoa = cliente.getEndereços();
                for (Endereco endereco : enderecosPessoa) {
                    System.out.println("Bairro: " + endereco.getBairro());
                    System.out.println("Rua: " + endereco.getRua());
                    System.out.println("Numero: " + endereco.getNumero());
                }
                clienteEncontrado = true;
                break;
            }
        }
        if (!clienteEncontrado) {
            System.out.println("Pessoa não encontrada.");
        }
    }
    public static void entregar() {
        System.out.println("Digite o nome do cliente para marcar como entregue:");
        String nomeCliente = scanner.next();

        for (Pedido pedido : pedidos) {
            if (pedido.getCliente().getNome().equalsIgnoreCase(nomeCliente)) {
                pedido.setStatus(StatusPedido.ENTREGUE);
                System.out.println("Pedido entregue com sucesso!");

                try {
                    String fileName = "pedido_" + pedido.getNome() + ".txt";
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

                    writer.write("Cliente: " + pedido.getCliente().getNome());
                    writer.newLine();
                    writer.write("Prato: " + pedido.getNome());
                    writer.newLine();
                    writer.write("Valor: " + pedido.getValor());
                    writer.newLine();
                    writer.write("Status: " + pedido.getStatus());
                    writer.newLine();

                    writer.close();
                    System.out.println("Arquivo TXT gerado com sucesso: " + fileName);
                } catch (IOException e) {
                    System.out.println("Erro ao gerar o arquivo TXT.");
                    e.printStackTrace();
                }

                return;
            }
        }

        System.out.println("Cliente não encontrado ou pedido não existente.");
    }

}