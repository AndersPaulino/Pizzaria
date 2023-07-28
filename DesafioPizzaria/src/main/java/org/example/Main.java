package org.example;

import org.example.entity.Cliente;
import org.example.entity.Endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Cliente> clientes = new ArrayList<>();
    static List<Endereco> enderecos = new ArrayList<>();
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
                    buscar();
                    break;
                case 4:
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
        System.out.println("3 - Dados do Cliente");
        System.out.println("4 - Entregar");
        System.out.println("0 - Sair");
        System.out.println("Digite o número da opção desejada:");
    }
    public static void cadastrar(){

    }
    public static void pedido(){

    }
    public static void buscar(){

    }
    public static void entregar(){

    }
}