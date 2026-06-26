import java.util.ArrayList;
import java.util.List;

public class FilmeManager {
    private final List<Midia> midias;
    private int proximoId;

    public FilmeManager() {
        this.midias = new ArrayList<>();
        this.proximoId = 1;
    }

    public void adicionarFilme(Midia midia) {
        if (midia instanceof Filme filme) {
            Filme filmeComId = new Filme(proximoId++, filme.getTitulo(), filme.getDiretor(), filme.getGenero(), filme.getAno(), filme.getDuracaoMinutos(), filme.getAvaliacao());
            midias.add(filmeComId);
        } else {
            throw new IllegalArgumentException("Tipo de mídia não suportado para cadastro.");
        }
    }

    public boolean editarFilme(int id, String titulo, String diretor, String genero, int ano, int duracaoMinutos, double avaliacao) {
        Midia midia = buscarPorId(id);
        if (midia == null) {
            return false;
        }
        midia.atualizarDados(titulo, diretor, genero, ano, duracaoMinutos, avaliacao);
        return true;
    }

    public boolean removerFilme(int id) {
        Midia midia = buscarPorId(id);
        if (midia == null) {
            return false;
        }
        return midias.remove(midia);
    }

    public List<Midia> listarFilmes() {
        return new ArrayList<>(midias);
    }

    public Midia buscarPorId(int id) {
        for (Midia midia : midias) {
            if (midia.getId() == id) {
                return midia;
            }
        }
        return null;
    }
}
