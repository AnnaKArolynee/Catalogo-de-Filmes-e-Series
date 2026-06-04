public class Filme {

    private int id;
    private String tipo;

    private String titulo;
    private String criadorDiretor;
    private String genero;
    private int ano;

    private int duracaoTemporadas;

    private double avaliacao;

    public Filme() {
    }

    public Filme(int id,
                  String tipo,
                  String titulo,
                  String criadorDiretor,
                  String genero,
                  int ano,
                  int duracaoTemporadas,
                  double avaliacao) {

        this.id = id;
        this.tipo = tipo;
        this.titulo = titulo;
        this.criadorDiretor = criadorDiretor;
        this.genero = genero;
        this.ano = ano;
        this.duracaoTemporadas = duracaoTemporadas;
        this.avaliacao = avaliacao;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCriadorDiretor() {
        return criadorDiretor;
    }

    public String getGenero() {
        return genero;
    }

    public int getAno() {
        return ano;
    }

    public int getDuracaoTemporadas() {
        return duracaoTemporadas;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    @Override
    public String toString() {

        if (tipo.equalsIgnoreCase("Filme")) {

            return String.format(
                "ID: %d | Filme | Título: %s | Diretor: %s | Gênero: %s | Ano: %d | Duração: %d min | Avaliação: %.1f",
                id,
                titulo,
                criadorDiretor,
                genero,
                ano,
                duracaoTemporadas,
                avaliacao
            );

        } else {

            return String.format(
                "ID: %d | Série | Título: %s | Criador: %s | Gênero: %s | Ano: %d | Temporadas: %d | Avaliação: %.1f",
                id,
                titulo,
                criadorDiretor,
                genero,
                ano,
                duracaoTemporadas,
                avaliacao
            );
        }
    }
}

