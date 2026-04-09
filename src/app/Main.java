package app;

import dao.ProdutoDAO;
import dao.ProdutoDAOImpl;
import model.Produto;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ProdutoDAO dao = new ProdutoDAOImpl();

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE GERENCIAMENTO ===");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Listar todos os produtos");
            System.out.println("3. Buscar produto por ID");
            System.out.println("4. Atualizar produto");
            System.out.println("5. Deletar produto");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> atualizar();
                case 5 -> deletar();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    static void cadastrar() {
        System.out.println("\n--- CADASTRAR PRODUTO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Produto p = new Produto(nome, descricao, preco, quantidade);
        dao.criar(p);
    }

    static void listar() {
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        List<Produto> produtos = dao.listarTodos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produtos.forEach(p -> System.out.printf(
                "ID: %d | %s | %s | R$%.2f | Qtd: %d%n",
                p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getQuantidade()
            ));
        }
    }

    static void buscar() {
        System.out.println("\n--- BUSCAR PRODUTO ---");
        System.out.print("Digite o ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto p = dao.buscarPorId(id);
        if (p != null) {
            System.out.printf("ID: %d | %s | %s | R$%.2f | Qtd: %d%n",
                p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getQuantidade());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    static void atualizar() {
        System.out.println("\n--- ATUALIZAR PRODUTO ---");
        System.out.print("Digite o ID do produto a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto p = dao.buscarPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.printf("Nome atual [%s] → novo nome (Enter para manter): ", p.getNome());
        String nome = scanner.nextLine();
        if (!nome.isBlank()) p.setNome(nome);

        System.out.printf("Descrição atual [%s] → nova descrição (Enter para manter): ", p.getDescricao());
        String descricao = scanner.nextLine();
        if (!descricao.isBlank()) p.setDescricao(descricao);

        System.out.printf("Preço atual [R$%.2f] → novo preço (0 para manter): ", p.getPreco());
        double preco = scanner.nextDouble();
        if (preco > 0) p.setPreco(preco);

        System.out.printf("Quantidade atual [%d] → nova quantidade (-1 para manter): ", p.getQuantidade());
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        if (quantidade >= 0) p.setQuantidade(quantidade);

        dao.atualizar(p);
    }

    static void deletar() {
        System.out.println("\n--- DELETAR PRODUTO ---");
        System.out.print("Digite o ID do produto a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto p = dao.buscarPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.printf("Tem certeza que deseja deletar '%s'? (s/n): ", p.getNome());
        String confirmacao = scanner.nextLine();
        if (confirmacao.equalsIgnoreCase("s")) {
            dao.deletar(id);
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}