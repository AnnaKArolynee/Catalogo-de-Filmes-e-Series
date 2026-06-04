import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Conexao.criarTabela();

        FilmeManager manager = new FilmeManager();

        Scanner leitor = new Scanner(System.in);

        while (true) {

            exibirMenu();

            int opcao = lerOpcao(leitor);

            switch (opcao) {

                case 1:
                    adicionarFilme(manager, leitor);
                    break;

                case 2:
                    editarFilme(manager, leitor);
                    break;

                case 3:
                    removerFilme(manager, leitor);
                    break;

                case 4:
                    listarFilmes(manager);
                    break;

                case 5:
                    System.out.println("Encerrando o programa.");
                    leitor.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void exibirMenu() {

        System.out.println("\n=== GERENCIAMENTO DE FILMES ===");
        System.out.println("1 - Adicionar filme");
        System.out.println("2 - Editar filme");
        System.out.println("3 - Remover filme");
        System.out.println("4 - Listar filmes");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao(Scanner leitor) {

        try {

            return leitor.nextInt();

        } catch (InputMismatchException e) {

            leitor.nextLine();
            return -1;
        }
    }

    private static void adicionarFilme(FilmeManager manager,
                                       Scanner leitor) {

        leitor.nextLine();

        System.out.println("\n--- Adicionar Filme ---");

        System.out.print("Título: ");
        String titulo = leitor.nextLine();

        System.out.print("Diretor: ");
        String diretor = leitor.nextLine();

        System.out.print("Gênero: ");
        String genero = leitor.nextLine();

        System.out.print("Ano: ");
        int ano = lerInt(leitor);

        System.out.print("Duração (minutos): ");
        int duracao = lerInt(leitor);

        System.out.print("Avaliação: ");
        double avaliacao = lerDouble(leitor);

        Filme filme = new Filme(
            0,
            titulo,
            diretor,
            genero,
            ano,
            duracao,
            avaliacao
        );

        manager.adicionarFilme(filme);

        System.out.println("Filme adicionado com sucesso.");
    }

    private static void editarFilme(FilmeManager manager,
                                    Scanner leitor) {

        System.out.println("\n--- Editar Filme ---");

        System.out.print("Digite o ID do filme: ");

        int id = lerInt(leitor);

        Filme filme = manager.buscarPorId(id);

        if (filme == null) {

            System.out.println("Filme não encontrado.");
            return;
        }

        leitor.nextLine();

        System.out.print("Novo título: ");
        String titulo = leitor.nextLine();

        System.out.print("Novo diretor: ");
        String diretor = leitor.nextLine();

        System.out.print("Novo gênero: ");
        String genero = leitor.nextLine();

        System.out.print("Novo ano: ");
        int ano = lerInt(leitor);

        System.out.print("Nova duração: ");
        int duracao = lerInt(leitor);

        System.out.print("Nova avaliação: ");
        double avaliacao = lerDouble(leitor);

        if (manager.editarFilme(
                id,
                titulo,
                diretor,
                genero,
                ano,
                duracao,
                avaliacao
        )) {

            System.out.println("Filme atualizado com sucesso.");

        } else {

            System.out.println("Erro ao atualizar filme.");
        }
    }

    private static void removerFilme(FilmeManager manager,
                                     Scanner leitor) {

        System.out.println("\n--- Remover Filme ---");

        System.out.print("Digite o ID do filme: ");

        int id = lerInt(leitor);

        if (manager.removerFilme(id)) {

            System.out.println("Filme removido com sucesso.");

        } else {

            System.out.println("Filme não encontrado.");
        }
    }

    private static void listarFilmes(FilmeManager manager) {

        System.out.println("\n--- LISTA DE FILMES ---");

        List<Filme> filmes = manager.listarFilmes();

        if (filmes.isEmpty()) {

            System.out.println("Nenhum filme cadastrado.");
            return;
        }

        for (Filme filme : filmes) {

            System.out.println(filme);
        }
    }

    private static int lerInt(Scanner leitor) {

        while (true) {

            try {

                int valor = leitor.nextInt();
                leitor.nextLine();

                return valor;

            } catch (InputMismatchException e) {

                leitor.nextLine();

                System.out.print(
                    "Digite um número inteiro válido: "
                );
            }
        }
    }

    private static double lerDouble(Scanner leitor) {

    while (true) {

        try {

            double valor = leitor.nextDouble();
            leitor.nextLine();

            return valor;

        } catch (InputMismatchException e) {

            leitor.nextLine();

            System.out.print(
                "Digite um número decimal válido: "
            );
        }
    }
}
}