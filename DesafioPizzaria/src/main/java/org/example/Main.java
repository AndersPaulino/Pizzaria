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
    public static void menu(){
        System.out.println("Menu");
        System.out.println("1 - Cadastrar novo cliente!");
        System.out.println("2 - Novo Pedido!");
        System.out.println("3 - Pedidos!");
        System.out.println("4 - Dados do Cliente");
        System.out.println("5 - Entregar");
        System.out.println("0 - Sair");
        System.out.println("Digite o número da opção desejada:");
    }
    public static void cadastrar() {
        List<Endereco> enderecos = new ArrayList<>();
        scanner.nextLine();
        System.out.println("Cadastrar");

        System.out.println("Digite o Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("Digite nome do Bairro: ");
        String bairro = scanner.nextLine();

        System.out.println("Digite o nome da Rua: ");
        String rua = scanner.nextLine();

        System.out.println("Digite o numero da Casa: ");
        int numero = scanner.nextInt();

        enderecos.add(new Endereco(bairro, rua, numero));
        clientes.add(new Cliente(nome, cpf, enderecos));
    }

    public static void pedido() {
        scanner.nextLine();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado. É necessário cadastrar um cliente antes de fazer um pedido.");
            return;
        }

        System.out.println("Lista de Clientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
        }

        System.out.println("Digite o número do cliente para fazer o pedido (ou 0 para sair):");
        int numeroClienteEscolhido = scanner.nextInt();

        if (numeroClienteEscolhido == 0) {
            return;
        }

        if (numeroClienteEscolhido >= 1 && numeroClienteEscolhido <= clientes.size()) {
            scanner.nextLine();
            Cliente clienteEscolhido = clientes.get(numeroClienteEscolhido - 1);
            System.out.println("Cliente selecionado: " + clienteEscolhido.getNome());

            boolean fazerMaisPedido = true;
            while (fazerMaisPedido) {
                System.out.println("Digite o nome do prato:");
                String nomePrato = scanner.nextLine();
                System.out.println("Digite o valor do pedido:");
                double valorPedido = scanner.nextDouble();

                Pedido pedido = new Pedido(nomePrato, clienteEscolhido, StatusPedido.PENDENTE, valorPedido);
                pedidos.add(pedido);
                System.out.println("Pedido adicionado com sucesso!");

                System.out.println("Deseja fazer mais um pedido? (Digite 'sim' ou 'nao')");
                scanner.nextLine();
                String resposta = scanner.nextLine().toLowerCase();

                if (resposta.equals("nao")) {
                    fazerMaisPedido = false;
                }
            }

            System.out.println("Pedido(s) criado(s) com sucesso!");
        } else {
            System.out.println("Número de cliente inválido.");
        }
    }



    public static void verPedidos(){
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }

        System.out.println("Lista de Pedidos:");
        for (int i = 0; i < pedidos.size(); i++){
            Pedido pedido = pedidos.get(i);
            System.out.println("  ____________________________   ");
            System.out.println(" | " + (i + 1) + ". Número do Pedido: " + pedido.getNumeroPedido());
            System.out.println(" | " +"   Cliente: " + pedido.getCliente().getNome());
            System.out.println(" | " +"   Prato: " + pedido.getNome());
            System.out.println(" | " +"   Valor: " + pedido.getValor());
            System.out.println(" | " +"   Status: " + pedido.getStatus());
            System.out.println("  ____________________________  ");
        }

        System.out.println("Digite o número do pedido que deseja alterar o status (ou 0 para sair):");
        int numeroPedidoAlterar = scanner.nextInt();

        if (numeroPedidoAlterar == 0) {
            return;
        }

        if (numeroPedidoAlterar >= 1 && numeroPedidoAlterar <= pedidos.size()) {
            Pedido pedidoAlterar = pedidos.get(numeroPedidoAlterar - 1);
            System.out.println("Pedido selecionado: " + pedidoAlterar.getNumeroPedido());
            System.out.println("Status atual: " + pedidoAlterar.getStatus());

            // Mostrar opções do enum StatusPedido
            System.out.println("Escolha o novo status:");
            StatusPedido[] statusOpcoes = StatusPedido.values();
            for (int i = 0; i < statusOpcoes.length; i++) {
                System.out.println((i + 1) + ". " + statusOpcoes[i]);
            }

            int opcaoStatus = scanner.nextInt();
            if (opcaoStatus >= 1 && opcaoStatus <= statusOpcoes.length) {
                StatusPedido novoStatus = statusOpcoes[opcaoStatus - 1];
                pedidoAlterar.setStatus(novoStatus);
                System.out.println("Status do pedido alterado com sucesso para: " + novoStatus);
            } else {
                System.out.println("Opção inválida.");
            }
        } else {
            System.out.println("Número de pedido inválido.");
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
                List<Endereco> enderecosPessoa = clienteEscolhido.getEnderecos();
                for (Endereco endereco : enderecosPessoa) {
                    System.out.println("Bairro: " + endereco.getBairro());
                    System.out.println("Rua: " + endereco.getRua());
                    System.out.println("Número: " + endereco.getNumero());
                    System.out.println("--------------------");
                }
            } else if (opcao == 2) {
                scanner.nextLine();
                // Editar endereço
                System.out.println("Digite o número do endereço que deseja editar:");
                int numeroEnderecoEscolhido = scanner.nextInt();

                if (numeroEnderecoEscolhido >= 1 && numeroEnderecoEscolhido <= clienteEscolhido.getEnderecos().size()) {
                    scanner.nextLine();
                    System.out.println("Digite o novo bairro:");
                    String novoBairro = scanner.nextLine();
                    System.out.println("Digite a nova rua:");
                    String novaRua = scanner.nextLine();
                    System.out.println("Digite o novo número:");
                    int novoNumero = scanner.nextInt();

                    // Atualizar o endereço do cliente
                    List<Endereco> enderecosPessoa = clienteEscolhido.getEnderecos();
                    Endereco enderecoAtualizado = new Endereco(novoBairro, novaRua, novoNumero);
                    enderecosPessoa.set(numeroEnderecoEscolhido - 1, enderecoAtualizado);

                    System.out.println("Endereço atualizado com sucesso!");
                } else {
                    System.out.println("Número de endereço inválido.");
                }
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static void entregar() {
        List<Cliente> clientesComPedidoProntaEntrega = new ArrayList<>();

        // Verifica os pedidos em PRONTA_ENTREGA e adiciona os clientes correspondentes na lista temporária
        for (Pedido pedido : pedidos) {
            if (pedido.getStatus() == StatusPedido.PRONTA_ENTREGA) {
                Cliente clienteDoPedido = pedido.getCliente();
                if (!clientesComPedidoProntaEntrega.contains(clienteDoPedido)) {
                    clientesComPedidoProntaEntrega.add(clienteDoPedido);
                }
            }
        }

        if (clientesComPedidoProntaEntrega.isEmpty()) {
            System.out.println("Nenhum pedido em PRONTA_ENTREGA encontrado.");
            return;
        }

        // Exibe a lista de clientes com pedidos em PRONTA_ENTREGA
        System.out.println("Clientes com pedido em PRONTA_ENTREGA:");
        for (int i = 0; i < clientesComPedidoProntaEntrega.size(); i++) {
            Cliente cliente = clientesComPedidoProntaEntrega.get(i);
            System.out.println((i + 1) + ". " + cliente.getNome());
        }

        System.out.println("Digite o número do cliente para marcar o pedido como entregue (ou 0 para sair):");
        int numeroClienteEscolhido = scanner.nextInt();

        if (numeroClienteEscolhido == 0) {
            return;
        }

        if (numeroClienteEscolhido >= 1 && numeroClienteEscolhido <= clientesComPedidoProntaEntrega.size()) {
            Cliente clienteEscolhido = clientesComPedidoProntaEntrega.get(numeroClienteEscolhido - 1);
            System.out.println("Cliente selecionado: " + clienteEscolhido.getNome());

            // Marca todos os pedidos como entregues para o cliente escolhido
            List<Pedido> pedidosEntregues = new ArrayList<>();
            for (Pedido pedido : pedidos) {
                if (pedido.getCliente().equals(clienteEscolhido) && pedido.getStatus() == StatusPedido.PRONTA_ENTREGA) {
                    pedido.setStatus(StatusPedido.ENTREGUE);
                    pedidosEntregues.add(pedido);
                }
            }

            try {
                String fileName = "pedido_" + clienteEscolhido.getNome() + ".txt";
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

                for (Pedido pedido : pedidosEntregues) {
                    writer.write("Cliente: " + pedido.getCliente().getNome());
                    writer.newLine();
                    writer.write("Prato: " + pedido.getNome());
                    writer.newLine();
                    writer.write("Valor: " + pedido.getValor());
                    writer.newLine();
                    writer.write("Status: " + pedido.getStatus());
                    writer.newLine();
                    writer.write("--------------------");
                    writer.newLine();
                }

                writer.close();
                System.out.println("Arquivo TXT gerado com sucesso: " + fileName);
            } catch (IOException e) {
                System.out.println("Erro ao gerar o arquivo TXT.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Número de cliente inválido.");
        }
    }
}