import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:sqlite:filmes.db";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
            return null;
        }
    }

    public static void criarTabela() {

        String sql = """
        CREATE TABLE IF NOT EXISTS filmes (

        id INTEGER PRIMARY KEY,

        tipo TEXT NOT NULL,

        titulo TEXT NOT NULL,

        criadorDiretor TEXT NOT NULL,

        genero TEXT NOT NULL,

        ano INTEGER,

        duracaoTemporadas INTEGER,

        avaliacao REAL
    );
""";


        try (Connection conn = conectar();
             var stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}