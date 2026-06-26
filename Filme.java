public class Filme extends Midia {
    private int duracaoMinutos;

    public Filme(int id, String titulo, String diretor, String genero, int ano, int duracaoMinutos, double avaliacao) {
        super(id, titulo, diretor, genero, ano, avaliacao);
        validarDuracao(duracaoMinutos);
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        validarDuracao(duracaoMinutos);
        this.duracaoMinutos = duracaoMinutos;
    }

    protected void validarDuracao(int duracaoMinutos) {
        if (duracaoMinutos < 0) {
            throw new IllegalArgumentException("Duração não pode ser negativa.");
        }
    }

    @Override
    public void atualizarDados(String titulo, String diretor, String genero, int ano, int duracaoMinutos, double avaliacao) {
        super.atualizarDados(titulo, diretor, genero, ano, duracaoMinutos, avaliacao);
        validarDuracao(duracaoMinutos);
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("Filme: %s | Diretor: %s | Gênero: %s | Ano: %d | Duração: %d min | Avaliação: %.1f",
                getTitulo(), getDiretor(), getGenero(), getAno(), duracaoMinutos, getAvaliacao());
    }

    @Override
    public String toString() {
        return exibirDetalhes();
    }
}
