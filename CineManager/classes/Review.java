import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.ArrayList;

import org.postgresql.jdbc.PgArray;

public class Review implements SQLData {
    int userId;
    int reviewId;
    Date dataDePostagem;
    String content;
    private String sqlType;

    public Review(int userId, Date dataDePostagem, String content, String reviewId) {
        this.content = reviewId;
        this.userId = userId;
        this.dataDePostagem = dataDePostagem;
        this.content = content;
    }

    ConnectSQL sqlKeys = new ConnectSQL();

    public void criarReview(int userId, Date dataDePostagem, String content) {
        int reviewId;
        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password)) {
            reviewId = obterProximoReviewId(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        String sql = "INSERT INTO reviews (reviewid, userid, dataDePostagem, conteudo) VALUES (" + reviewId + ", '"
                + userId
                + "', '" + dataDePostagem + "', '" + content + "')";

        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Review criada com sucesso!");
                this.userId = userId;
                this.dataDePostagem = dataDePostagem;
                this.content = content;
                this.reviewId = reviewId;
            } else {
                System.out.println("Não foi possível criar a review.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int obterProximoReviewId(Connection connection) throws SQLException {
        String sql = "SELECT MAX(reviewId) FROM reviews";
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

    public void editarReview(String content) {
        this.content = content;

        String sql = "UPDATE reviews SET content = '" + content + "' WHERE reviewid = '" + reviewId + "'";

        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Review editada com sucesso.");
            } else {
                System.out.println("Não foi possível editar a review.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String deleteReview() {
        String sql = "DELETE FROM reviews WHERE reviewid = '" + this.reviewId + "'";

        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                return "Review excluída com sucesso.";
            } else {
                return "Review não encontrada.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao excluir a review " + reviewId + ".";
        }
    }

    public void obterDadosReview(String reviewId) {
        String sql = "SELECT * FROM reviews WHERE reviewid = '" + reviewId + "'";

        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                this.userId = resultSet.getInt("userid");
                this.dataDePostagem = resultSet.getDate("dataDePostagem");
                this.content = resultSet.getString("conteudo");
                this.reviewId = resultSet.getInt("reviewid");
            } else {
                System.out.println("Review não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void getAllRevielsFromId(int movieId) {
        // ArrayList<Integer> comId = new ArrayList<>();
        // try {
        // Connection connection = DriverManager.getConnection(sqlKeys.url,
        // sqlKeys.user, sqlKeys.password);
        // Statement statement = connection.createStatement();
        // ResultSet rs = statement.executeQuery("SELECT * FROM movies");
        // Integer[] intArray = {};
        // while (rs.next()) {
        // PgArray pgArray = (PgArray) rs.getArray("fk_review");
        // intArray = (Integer[]) pgArray.getArray();
        // }
        // for (int i : intArray) {
        // comId.add(i);
        // }
        // try {
        // Connection conexao = DriverManager.getConnection(sqlKeys.url, sqlKeys.user,
        // sqlKeys.password);
        // Statement state = conexao.createStatement();
        // ResultSet result = state.executeQuery("SELECT * FROM reviews WHERE id = ?");
        // for (int i = 0; i < comId.size(); i++) {
        // result.getInt((int) comId.get(i));
        // }
        // while (result.next()) {
        // int id = resultSet.getInt("id");
        // String nome = resultSet.getString("nome");
        // double salario = resultSet.getDouble("salario");

        // // Faça o que desejar com os valores, como imprimir na tela
        // System.out.println("ID: " + id);
        // System.out.println("Nome: " + nome);
        // System.out.println("Salário: " + salario);
        // }
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // } catch (Exception e) {
        // System.out.println(e);
        // return comId;
        // }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDataDePostagem() {
        return dataDePostagem;
    }

    public void setDataDePostagem(Date dataDePostagem) {
        this.dataDePostagem = dataDePostagem;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getSQLTypeName() throws SQLException {
        return sqlType;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        userId = stream.readInt();
        reviewId = stream.readInt();
        dataDePostagem = stream.readDate();
        content = stream.readString();
        sqlType = typeName;
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeInt(userId);
        stream.writeInt(reviewId);
        stream.writeDate(dataDePostagem);
        stream.writeString(content);
    }

}
