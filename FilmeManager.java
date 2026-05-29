import java.util.ArrayList;
import java.util.List;

public class FilmeManager {
    private final List<Filme> filmes;
    private int proximoId;

    public FilmeManager() {
        this.filmes = new ArrayList<>();
        this.proximoId = 1;
    }

    public void adicionarFilme(Filme filme) {
        filme = new Filme(proximoId++, filme.getTitulo(), filme.getDiretor(), filme.getGenero(), filme.getAno(), filme.getDuracaoMinutos(), filme.getAvaliacao());
        filmes.add(filme);
    }

    public boolean editarFilme(int id, String titulo, String diretor, String genero, int ano, int duracaoMinutos, double avaliacao) {
        Filme filme = buscarPorId(id);
        if (filme == null) {
            return false;
        }
        filme.atualizarDados(titulo, diretor, genero, ano, duracaoMinutos, avaliacao);
        return true;
    }

    public boolean removerFilme(int id) {
        Filme filme = buscarPorId(id);
        if (filme == null) {
            return false;
        }
        return filmes.remove(filme);
    }

    public List<Filme> listarFilmes() {
        return new ArrayList<>(filmes);
    }

    public Filme buscarPorId(int id) {
        for (Filme filme : filmes) {
            if (filme.getId() == id) {
                return filme;
            }
        }
        return null;
    }
}
