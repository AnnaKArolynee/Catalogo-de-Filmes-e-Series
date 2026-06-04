public class Filme {
    private int id;
    private String titulo;
    private String diretor;
    private String genero;
    private int ano;
    private int duracaoMinutos;
    private double avaliacao;

    public Filme() {
    }

    public Filme(int id, String titulo, String diretor, String genero, int ano, int duracaoMinutos, double avaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.diretor = diretor;
        this.genero = genero;
        this.ano = ano;
        this.duracaoMinutos = duracaoMinutos;
        this.avaliacao = avaliacao;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void atualizarDados(String titulo, String diretor, String genero, int ano, int duracaoMinutos, double avaliacao) {
        this.titulo = titulo;
        this.diretor = diretor;
        this.genero = genero;
        this.ano = ano;
        this.duracaoMinutos = duracaoMinutos;
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Título: %s | Diretor: %s | Gênero: %s | Ano: %d | Duração: %d min | Avaliação: %.1f",
                id, titulo, diretor, genero, ano, duracaoMinutos, avaliacao);
    }
}
