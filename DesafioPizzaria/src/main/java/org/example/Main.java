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

    public static void verPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }

        System.out.println("Lista de Pedidos:");
        for (Pedido pedido : pedidos) {
            System.out.println("Cliente: " + pedido.getCliente().getNome());
            System.out.println("Prato: " + pedido.getNome());
            System.out.println("Valor: " + pedido.getValor());
            System.out.println("Status: " + pedido.getStatus());
            System.out.println("--------------------");
        }
    }
    public static void buscar() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }

        System.out.println("Lista de Clientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
        }

        System.out.println("Digite o número do cliente que deseja visualizar/editar (ou 0 para sair):");
        int numeroClienteEscolhido = scanner.nextInt();

        if (numeroClienteEscolhido == 0) {
            return;
        }

        if (numeroClienteEscolhido >= 1 && numeroClienteEscolhido <= clientes.size()) {
            Cliente clienteEscolhido = clientes.get(numeroClienteEscolhido - 1);
            System.out.println("Cliente selecionado: " + clienteEscolhido.getNome());
            System.out.println("CPF: " + clienteEscolhido.getCpf());

            // Opção de listar endereços ou editar endereço
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Listar endereços");
            System.out.println("2 - Editar endereço");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                // Listar endereços
                List<Endereco> enderecosPessoa = clienteEscolhido.getEndereços();
                for (Endereco endereco : enderecosPessoa) {
                    System.out.println("Bairro: " + endereco.getBairro());
                    System.out.println("Rua: " + endereco.getRua());
                    System.out.println("Número: " + endereco.getNumero());
                    System.out.println("--------------------");
                }
            } else if (opcao == 2) {
                // Editar endereço
                System.out.println("Digite o novo bairro:");
                String novoBairro = scanner.next();
                System.out.println("Digite a nova rua:");
                String novaRua = scanner.next();
                System.out.println("Digite o novo número:");
                int novoNumero = scanner.nextInt();

                // Atualizar o endereço do cliente
                List<Endereco> enderecosPessoa = clienteEscolhido.getEndereços();
                Endereco enderecoAtualizado = new Endereco(novoBairro, novaRua, novoNumero);
                enderecosPessoa.clear();
                enderecosPessoa.add(enderecoAtualizado);

                System.out.println("Endereço atualizado com sucesso!");
            } else {
                System.out.println("Opção inválida.");
            }
        } else {
            System.out.println("Número de cliente inválido.");
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