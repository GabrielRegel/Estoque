package controleEstoque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Produto> estoque = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    registrarEntrada();
                    break;
                case 2:
                    registrarSaida();
                    break;
                case 3:
                    listarEstoque();
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Registrar entrada de produto");
        System.out.println("2. Registrar saída (venda)");
        System.out.println("3. Listar estoque");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void registrarEntrada() {
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço unitário: ");
        double preco = scanner.nextDouble();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();

        estoque.add(new Produto(codigo, nome, preco, quantidade));
        System.out.println("Produto registrado com sucesso!");
    }

    private static void registrarSaida() {
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        System.out.print("Quantidade vendida: ");
        int quantidade = scanner.nextInt();

        for (Produto produto : estoque) {
            if (produto.getCodigo().equals(codigo)) {
                if (produto.getQuantidadeDisponivel() >= quantidade) {
                    produto.setQuantidadeDisponivel(produto.getQuantidadeDisponivel() - quantidade);
                    System.out.println("Venda registrada!");
                } else {
                    System.out.println("Erro: Quantidade insuficiente em estoque!");
                }
                return;
            }
        }
        System.out.println("Erro: Produto não encontrado!");
    }

    private static void listarEstoque() {
        if (estoque.isEmpty()) {
            System.out.println("Estoque vazio!");
            return;
        }

        double valorTotal = 0;
        System.out.println("\n=== ESTOQUE ===");
        for (Produto produto : estoque) {
            double valorItem = produto.getPrecoUnitario() * produto.getQuantidadeDisponivel();
            System.out.printf(
                "Código: %s | Nome: %s | Preço: R$%.2f | Quantidade: %d | Total: R$%.2f%n",
                produto.getCodigo(),
                produto.getNome(),
                produto.getPrecoUnitario(),
                produto.getQuantidadeDisponivel(),
                valorItem
            );
            valorTotal += valorItem;
        }
        System.out.printf("VALOR TOTAL DO ESTOQUE: R$%.2f%n", valorTotal);
    }
}