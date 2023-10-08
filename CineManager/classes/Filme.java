import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Filme {
    String nome;
    int filmeId;
    double rating;
    Date releasDate;
    Review reviews[];

    public Filme(String nome, int filmeId, double rating, Date releasDate, Review[] reviews) {
        this.nome = nome;
        this.rating = rating;
        this.filmeId = filmeId;
        this.releasDate = releasDate;
        this.reviews = reviews;
    }

    ConnectSQL sqlKeys = new ConnectSQL();

    public void editarFilme(String nome, double rating, Date releasDate) {
        if (nome == null) {
            nome = getNome();
        }
        if (rating == 0.0) {
            rating = getRating();
        }
        if (releasDate == null) {
            releasDate = getReleasDate();
        }

        this.nome = nome;
        this.rating = rating;
        this.releasDate = releasDate;

        String sql = "UPDATE filmes SET nome = '" + nome + "', rating = " + rating + ", release_date = '" + releasDate
                + "' WHERE filmeid = '" + this.filmeId + "'";

        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Filme editado com sucesso.");
            } else {
                System.out.println("Não foi possível editar o filme.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarFilme(String nome, double rating, Date releaseDate) {
        String sql = "INSERT INTO filmes (filmeId, nome, rating, release_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                PreparedStatement statement = connection.prepareStatement(sql)) {

            int filmeId = obterProximoFilmeId(connection);

            statement.setInt(1, filmeId);
            statement.setString(2, nome);
            statement.setDouble(3, rating);
            statement.setDate(4, releaseDate);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Filme adicionado com sucesso!");
            } else {
                System.out.println("Não foi possível adicionar o filme.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    int obterProximoFilmeId(Connection connection) throws SQLException {
        String sql = "SELECT MAX(filmeId) FROM filmes";
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int idMaximo = resultSet.getInt(1);
                return idMaximo + 1;
            } else {
                return 1;
            }
        }
    }

    public String deleteFilme(String filmeId) {
        String sql = "DELETE FROM filmes WHERE filmeid = '" + filmeId + "'";

        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                return "Filme " + filmeId + " excluído com sucesso.";
            } else {
                return "Filme " + filmeId + " não encontrado.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao excluir o filme " + filmeId + ".";
        }
    }

    public String showFilme(String filmeId) {
        String str = "";
        str += "Nome: " + this.nome + "\n";
        str += "Rating: " + this.rating + "\n";
        str += "Lançamento: " + this.releasDate + "\n";
        str += "Avaliações: " + this.reviews + "\n";
        return str;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getReleasDate() {
        return releasDate;
    }

    public void setReleasDate(Date releasDate) {
        this.releasDate = releasDate;
    }

    public Review[] getReviews() {
        return reviews;
    }

    public void setReviews(Review[] reviews) {
        this.reviews = reviews;
    }
}
