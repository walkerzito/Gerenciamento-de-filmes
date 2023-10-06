import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "392943";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexão bem-sucedida!");

            // Executar uma consulta
            String query = "SELECT * FROM \"cinemanager\".filme";
            try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    String coluna1 = resultSet.getString("coluna1");
                    int coluna2 = resultSet.getInt("coluna2");

                    System.out.println("Coluna 1: " + coluna1 + ", Coluna 2: " + coluna2);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
        }

        // String text = "";
        // Screens screens = new Screens();
        // while (text != "sair") {
        // screens.loginScreen();
        // }
        // Usuario novoUser = new Usuario("Vitor", "eeee", "123", false, new
        // ArrayList<>());
        // System.out.println(novoUser.getReviews());

        // long milissegundos = System.currentTimeMillis();
        // Date data = new Date(milissegundos);

        // List<Review> listReviews = novoUser.getReviews();

        // Review novoReview = new Review("eeee", data, "Bla Blaa", "a");
        // listReviews.add(novoReview);
        // novoReview = new Review("eeee", data, "Coisa Ruim", "a");
        // listReviews.add(novoReview);
        // novoReview = new Review("eeee", data, "Coisa BOA", "a");
        // listReviews.add(novoReview);

        // System.out.println(novoUser.showUser(""));

    }
}
