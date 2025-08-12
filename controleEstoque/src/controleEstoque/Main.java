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
                    cadastrarProduto();
                    break;
                case 2:
                    registrarEntrada();
                    break;
                case 3:
                    registrarSaida();
                    break;
                case 4:
                    listarEstoque();
                    break;
                case 5:
                    pesquisarProduto();
                    break;
                case 6:
                    editarValor();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== CONTROLE DE ESTOQUE ===");
        System.out.println("1. Cadastrar novo produto");
        System.out.println("2. Registrar entrada no estoque");
        System.out.println("3. Registrar saída do estoque");
        System.out.println("4. Listar estoque completo");
        System.out.println("5. Pesquisar produto");
        System.out.println("6. Editar valor do produto");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarProduto() {
        System.out.println("\n--- CADASTRO DE PRODUTO ---");
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        
        if (buscarProduto(codigo) != null) {
            System.out.println("Erro: Produto já cadastrado!");
            return;
        }
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço unitário: ");
        double preco = scanner.nextDouble();
        System.out.print("Quantidade inicial: ");
        int quantidade = scanner.nextInt();

        estoque.add(new Produto(codigo, nome, preco, quantidade));
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void registrarEntrada() {
        System.out.println("\n--- ENTRADA NO ESTOQUE ---");
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        Produto produto = buscarProduto(codigo);
        
        if (produto == null) {
            System.out.println("Erro: Produto não encontrado!");
            return;
        }
        
        System.out.print("Quantidade a adicionar: ");
        int quantidade = scanner.nextInt();
        
        if (quantidade <= 0) {
            System.out.println("Erro: Quantidade deve ser positiva!");
            return;
        }
        
        produto.setQuantidadeDisponivel(produto.getQuantidadeDisponivel() + quantidade);
        System.out.printf("Entrada registrada! Novo estoque: %d unidades%n", produto.getQuantidadeDisponivel());
    }

    private static void registrarSaida() {
        System.out.println("\n--- SAÍDA DO ESTOQUE ---");
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        Produto produto = buscarProduto(codigo);
        
        if (produto == null) {
            System.out.println("Erro: Produto não encontrado!");
            return;
        }
        
        System.out.print("Quantidade a remover: ");
        int quantidade = scanner.nextInt();
        
        if (quantidade <= 0) {
            System.out.println("Erro: Quantidade deve ser positiva!");
            return;
        }
        
        if (produto.getQuantidadeDisponivel() < quantidade) {
            System.out.println("Erro: Estoque insuficiente!");
            System.out.printf("Estoque atual: %d unidades%n", produto.getQuantidadeDisponivel());
            return;
        }
        
        double precoVenda = produto.calcularPrecoComDesconto(quantidade);
        double valorTotal = precoVenda * quantidade;
        
        produto.setQuantidadeDisponivel(produto.getQuantidadeDisponivel() - quantidade);
        
        System.out.println("\n--- RESUMO DA VENDA ---");
        System.out.printf("Produto: %s (%s)%n", produto.getNome(), produto.getCodigo());
        System.out.printf("Quantidade: %d unidades%n", quantidade);
        
        if (quantidade >= 10) {
            System.out.printf("Preço unitário (com 10%% de desconto): R$%.2f%n", precoVenda);
        } else {
            System.out.printf("Preço unitário: R$%.2f%n", produto.getPrecoUnitario());
        }
        
        System.out.printf("Valor total: R$%.2f%n", valorTotal);
        System.out.printf("Novo estoque: %d unidades%n", produto.getQuantidadeDisponivel());
    }

    private static void listarEstoque() {
        System.out.println("\n--- ESTOQUE COMPLETO ---");
        if (estoque.isEmpty()) {
            System.out.println("Estoque vazio!");
            return;
        }

        double valorTotal = 0;
        for (Produto produto : estoque) {
            produto.apresentar();
            valorTotal += produto.getPrecoUnitario() * produto.getQuantidadeDisponivel();
        }
        System.out.printf("\nVALOR TOTAL DO ESTOQUE: R$%.2f%n", valorTotal);
    }

    private static void pesquisarProduto() {
        System.out.println("\n--- PESQUISA DE PRODUTO ---");
        System.out.print("Digite código ou nome do produto: ");
        String termo = scanner.nextLine().toLowerCase();
        
        List<Produto> resultados = new ArrayList<>();
        
        for (Produto produto : estoque) {
            if (produto.getCodigo().toLowerCase().contains(termo) || 
                produto.getNome().toLowerCase().contains(termo)) {
                resultados.add(produto);
            }
        }
        
        if (resultados.isEmpty()) {
            System.out.println("Nenhum produto encontrado!");
        } else {
            System.out.println("\n--- RESULTADOS ---");
            for (Produto p : resultados) {
                p.apresentar();
            }
        }
    }

    private static void editarValor() {
        System.out.println("\n--- EDIÇÃO DE PREÇO ---");
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        Produto produto = buscarProduto(codigo);
        
        if (produto == null) {
            System.out.println("Erro: Produto não encontrado!");
            return;
        }
        
        System.out.printf("Preço atual de %s: R$%.2f%n", produto.getNome(), produto.getPrecoUnitario());
        System.out.print("Novo preço: ");
        double novoPreco = scanner.nextDouble();
        
        if (novoPreco <= 0) {
            System.out.println("Erro: Preço deve ser positivo!");
            return;
        }
        
        produto.setPrecoUnitario(novoPreco);
        System.out.println("Preço atualizado com sucesso!");
    }

    private static Produto buscarProduto(String codigo) {
        for (Produto produto : estoque) {
            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return produto;
            }
        }
        return null;
    }
}