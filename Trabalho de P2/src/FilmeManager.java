import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeManager {

    public void adicionarFilme(Filme filme) {

        String sql = """
            INSERT INTO filmes
(tipo, titulo, criadorDiretor, genero, ano,
duracaoTemporadas, avaliacao)
VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, filme.getTipo());
            stmt.setString(2, filme.getTitulo());
            stmt.setString(3, filme.getCriadorDiretor());
            stmt.setString(4, filme.getGenero());
            stmt.setInt(5, filme.getAno());
            stmt.setInt(6, filme.getDuracaoTemporadas());
            stmt.setDouble(7, filme.getAvaliacao());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar filme: " + e.getMessage());
        }
    }

    public List<Filme> listarFilmes() {

        List<Filme> filmes = new ArrayList<>();

        String sql = "SELECT * FROM filmes";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Filme filme = new Filme(
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("titulo"),
                    rs.getString("criadorDiretor"),
                    rs.getString("genero"),
                    rs.getInt("ano"),
                    rs.getInt("duracaoTemporadas"),
                    rs.getDouble("avaliacao")
);

                filmes.add(filme);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar filmes: " + e.getMessage());
        }

        return filmes;
    }

    public boolean removerFilme(int id) {

        String sql = "DELETE FROM filmes WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover filme: " + e.getMessage());
            return false;
        }
    }

public boolean editarFilme(int id,
                           String tipo,
                           String titulo,
                           String criadorDiretor,
                           String genero,
                           int ano,
                           int duracaoTemporadas,
                           double avaliacao) {

    String sql = """
        UPDATE filmes
        SET tipo = ?,
            titulo = ?,
            criadorDiretor = ?,
            genero = ?,
            ano = ?,
            duracaoTemporadas = ?,
            avaliacao = ?
        WHERE id = ?
    """;

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, tipo);
        stmt.setString(2, titulo);
        stmt.setString(3, criadorDiretor);
        stmt.setString(4, genero);
        stmt.setInt(5, ano);
        stmt.setInt(6, duracaoTemporadas);
        stmt.setDouble(7, avaliacao);
        stmt.setInt(8, id);

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println(
            "Erro ao editar filme/série: "
            + e.getMessage()
        );

        return false;
    }
}

public Filme buscarPorId(int id) {

    String sql = "SELECT * FROM filmes WHERE id = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            return new Filme(
                rs.getInt("id"),
                rs.getString("tipo"),
                rs.getString("titulo"),
                rs.getString("criadorDiretor"),
                rs.getString("genero"),
                rs.getInt("ano"),
                rs.getInt("duracaoTemporadas"),
                rs.getDouble("avaliacao")
            );
        }

    } catch (SQLException e) {

        System.out.println(
            "Erro ao buscar filme/série: "
            + e.getMessage()
        );
    }

    return null;
}
}