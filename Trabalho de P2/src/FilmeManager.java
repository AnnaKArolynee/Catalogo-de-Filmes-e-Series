import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeManager {

    public void adicionarFilme(Filme filme) {

        String sql = """
            INSERT INTO filmes
            (titulo, diretor, genero, ano, duracao, avaliacao)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getDiretor());
            stmt.setString(3, filme.getGenero());
            stmt.setInt(4, filme.getAno());
            stmt.setInt(5, filme.getDuracaoMinutos());
            stmt.setDouble(6, filme.getAvaliacao());

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
                        rs.getString("titulo"),
                        rs.getString("diretor"),
                        rs.getString("genero"),
                        rs.getInt("ano"),
                        rs.getInt("duracao"),
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

    public boolean editarFilme(int id, String titulo, String diretor,
                               String genero, int ano,
                               int duracao, double avaliacao) {

        String sql = """
            UPDATE filmes
            SET titulo = ?, diretor = ?, genero = ?,
                ano = ?, duracao = ?, avaliacao = ?
            WHERE id = ?
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, diretor);
            stmt.setString(3, genero);
            stmt.setInt(4, ano);
            stmt.setInt(5, duracao);
            stmt.setDouble(6, avaliacao);
            stmt.setInt(7, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao editar filme: " + e.getMessage());
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
                        rs.getString("titulo"),
                        rs.getString("diretor"),
                        rs.getString("genero"),
                        rs.getInt("ano"),
                        rs.getInt("duracao"),
                        rs.getDouble("avaliacao")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar filme: " + e.getMessage());
        }

        return null;
    }
}