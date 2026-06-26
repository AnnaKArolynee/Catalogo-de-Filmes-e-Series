import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FilmeManager manager = new FilmeManager();
        Scanner leitor = new Scanner(System.in);

        demonstrarPolimorfismo();

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
                    System.out.println("Encerrando o programa. Até logo!");
                    leitor.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Gerenciamento de Filmes ===");
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

    private static void adicionarFilme(FilmeManager manager, Scanner leitor) {
        leitor.nextLine();
        System.out.println("\n--- Adicionar Filme ---");

        String titulo = lerTextoObrigatorio(leitor, "Título: ");
        String diretor = lerTextoObrigatorio(leitor, "Diretor: ");
        String genero = lerTextoObrigatorio(leitor, "Gênero: ");
        int ano = lerIntNaoNegativo(leitor, "Ano: ");
        int duracao = lerIntNaoNegativo(leitor, "Duração (minutos): ");
        double avaliacao = lerAvaliacao(leitor, "Avaliação (0.0 - 10.0): ");

        try {
            manager.adicionarFilme(new Filme(0, titulo, diretor, genero, ano, duracao, avaliacao));
            System.out.println("Filme adicionado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar filme: " + e.getMessage());
        }
    }

    private static void editarFilme(FilmeManager manager, Scanner leitor) {
        System.out.println("\n--- Editar Filme ---");
        System.out.print("Digite o ID do filme a ser editado: ");
        int id = lerInt(leitor);
        Midia midia = manager.buscarPorId(id);

        if (midia == null || !(midia instanceof Filme filme)) {
            System.out.println("Filme não encontrado.");
            return;
        }

        leitor.nextLine();
        System.out.println("Título atual: " + filme.getTitulo());
        String titulo = lerTextoObrigatorio(leitor, "Novo título: ");
        System.out.println("Diretor atual: " + filme.getDiretor());
        String diretor = lerTextoObrigatorio(leitor, "Novo diretor: ");
        System.out.println("Gênero atual: " + filme.getGenero());
        String genero = lerTextoObrigatorio(leitor, "Novo gênero: ");
        System.out.println("Ano atual: " + filme.getAno());
        int ano = lerIntNaoNegativo(leitor, "Novo ano: ");
        System.out.println("Duração atual: " + filme.getDuracaoMinutos() + " minutos");
        int duracao = lerIntNaoNegativo(leitor, "Nova duração (minutos): ");
        System.out.println("Avaliação atual: " + filme.getAvaliacao());
        double avaliacao = lerAvaliacao(leitor, "Nova avaliação (0.0 - 10.0): ");

        try {
            if (manager.editarFilme(id, titulo, diretor, genero, ano, duracao, avaliacao)) {
                System.out.println("Filme atualizado com sucesso.");
            } else {
                System.out.println("Erro ao atualizar o filme.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao editar filme: " + e.getMessage());
        }
    }

    private static void removerFilme(FilmeManager manager, Scanner leitor) {
        System.out.println("\n--- Remover Filme ---");
        System.out.print("Digite o ID do filme a ser removido: ");
        int id = lerInt(leitor);

        if (manager.removerFilme(id)) {
            System.out.println("Filme removido com sucesso.");
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    private static void listarFilmes(FilmeManager manager) {
        System.out.println("\n--- Lista de Filmes ---");
        List<Midia> filmes = manager.listarFilmes();
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
            return;
        }
        for (Midia filme : filmes) {
            System.out.println(filme.exibirDetalhes());
        }
    }

    private static void demonstrarPolimorfismo() {
        List<Midia> midias = new ArrayList<>();
        midias.add(new Filme(0, "Matrix", "Irmãs Wachowski", "Ficção científica", 1999, 136, 9.0));
        midias.add(new Filme(0, "A Origem", "Christopher Nolan", "Ação", 2010, 148, 8.8));

        System.out.println("\n--- Demonstração de Polimorfismo ---");
        for (Midia midia : midias) {
            System.out.println(midia.exibirDetalhes());
        }
    }

    private static String lerTextoObrigatorio(Scanner leitor, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = leitor.nextLine().trim();
            if (!texto.isBlank()) {
                return texto;
            }
            System.out.println("Entrada inválida. O texto não pode ficar vazio.");
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
                System.out.print("Valor inválido. Digite um número inteiro: ");
            }
        }
    }

    private static int lerIntNaoNegativo(Scanner leitor, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            int valor = lerInt(leitor);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("Valor inválido. Não pode ser negativo.");
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
                System.out.print("Valor inválido. Digite um número decimal: ");
            }
        }
    }

    private static double lerAvaliacao(Scanner leitor, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            double valor = lerDouble(leitor);
            if (valor >= 0.0 && valor <= 10.0) {
                return valor;
            }
            System.out.println("Avaliação inválida. Digite um valor entre 0.0 e 10.0.");
        }
    }
}
