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

    System.out.println("\n=== CATÁLOGO ===");

    System.out.println("1 - Adicionar filme ou série");

    System.out.println("2 - Editar filme ou série");

    System.out.println("3 - Remover filme ou série");

    System.out.println("4 - Consultar catálogo");

    System.out.println("5 - Sair");

    System.out.print("Escolha: ");
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

    System.out.println("\n1 - Filme");
    System.out.println("2 - Série");

    System.out.print("Escolha: ");

    int escolha = lerInt(leitor);

    String tipo;

    if (escolha == 1) {
        tipo = "Filme";
    } else {
        tipo = "Serie";
    }

    System.out.print("Título: ");
    String titulo = leitor.nextLine();

    if (tipo.equals("Filme")) {

        System.out.print("Diretor: ");

    } else {

        System.out.print("Criador: ");
    }

    String criadorDiretor = leitor.nextLine();

    System.out.print("Gênero: ");
    String genero = leitor.nextLine();

    System.out.print("Ano: ");
    int ano = lerInt(leitor);

    int valor;

    if (tipo.equals("Filme")) {

        System.out.print("Duração em minutos: ");

    } else {

        System.out.print("Quantidade de temporadas: ");
    }

    valor = lerInt(leitor);

    System.out.print("Avaliação: ");
    double avaliacao = lerDouble(leitor);

    Filme filme = new Filme(
        0,
        tipo,
        titulo,
        criadorDiretor,
        genero,
        ano,
        valor,
        avaliacao
    );

    manager.adicionarFilme(filme);

    System.out.println(tipo + " adicionado(a) com sucesso.");
}

private static void editarFilme(FilmeManager manager,
                                Scanner leitor) {

    System.out.println("\n--- Editar Filme/Série ---");

    System.out.print("Digite o ID: ");

    int id = lerInt(leitor);

    Filme filme = manager.buscarPorId(id);

    if (filme == null) {

        System.out.println("Filme/Série não encontrado.");
        return;
    }

    leitor.nextLine();

    System.out.println("\n1 - Filme");
    System.out.println("2 - Série");

    System.out.print("Escolha o tipo: ");

    int escolha = lerInt(leitor);

    String tipo;

    if (escolha == 1) {

        tipo = "Filme";

    } else {

        tipo = "Serie";
    }

    System.out.print("Novo título: ");
    String titulo = leitor.nextLine();

    if (tipo.equals("Filme")) {

        System.out.print("Novo diretor: ");

    } else {

        System.out.print("Novo criador: ");
    }

    String criadorDiretor = leitor.nextLine();

    System.out.print("Novo gênero: ");
    String genero = leitor.nextLine();

    System.out.print("Novo ano: ");
    int ano = lerInt(leitor);

    int duracaoTemporadas;

    if (tipo.equals("Filme")) {

        System.out.print("Nova duração em minutos: ");

    } else {

        System.out.print("Nova quantidade de temporadas: ");
    }

    duracaoTemporadas = lerInt(leitor);

    System.out.print("Nova avaliação: ");
    double avaliacao = lerDouble(leitor);

    if (manager.editarFilme(
            id,
            tipo,
            titulo,
            criadorDiretor,
            genero,
            ano,
            duracaoTemporadas,
            avaliacao
    )) {

        System.out.println(
            tipo + " atualizado(a) com sucesso."
        );

    } else {

        System.out.println(
            "Erro ao atualizar."
        );
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