public abstract class Midia {
    private int id;
    private String titulo;
    private String diretor;
    private String genero;
    private int ano;
    private double avaliacao;

    public Midia(int id, String titulo, String diretor, String genero, int ano, double avaliacao) {
        validarId(id);
        validarTitulo(titulo);
        validarAno(ano);
        validarAvaliacao(avaliacao);

        this.id = id;
        this.titulo = titulo;
        this.diretor = diretor;
        this.genero = genero;
        this.ano = ano;
        this.avaliacao = avaliacao;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public String getGenero() {
        return genero;
    }

    public int getAno() {
        return ano;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void atualizarDados(String titulo, String diretor, String genero, int ano, int duracaoMinutos, double avaliacao) {
        validarTitulo(titulo);
        validarAno(ano);
        validarAvaliacao(avaliacao);

        this.titulo = titulo;
        this.diretor = diretor;
        this.genero = genero;
        this.ano = ano;
        this.avaliacao = avaliacao;
    }

    protected void validarId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID inválido. O ID deve ser maior ou igual a zero.");
        }
    }

    protected void validarTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio.");
        }
    }

    protected void validarAno(int ano) {
        if (ano < 0) {
            throw new IllegalArgumentException("Ano não pode ser negativo.");
        }
    }

    protected void validarAvaliacao(double avaliacao) {
        if (avaliacao < 0.0 || avaliacao > 10.0) {
            throw new IllegalArgumentException("Avaliação deve estar entre 0.0 e 10.0.");
        }
    }

    public abstract String exibirDetalhes();

    @Override
    public String toString() {
        return String.format("ID: %d | Título: %s | Diretor: %s | Gênero: %s | Ano: %d | Avaliação: %.1f",
                id, titulo, diretor, genero, ano, avaliacao);
    }
}
