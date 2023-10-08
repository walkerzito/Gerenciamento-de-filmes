import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario {
    int userId;
    String nome;
    String email;
    String senha;
    boolean isAdmin;

    public Usuario(String nome, String email, String senha, boolean isAdmin) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }

    ConnectSQL sqlKeys = new ConnectSQL();

    public Usuario login(String email, String senha) {
        String sql = "SELECT * FROM users WHERE email = '" + email + "' AND senha = '" + senha + "'";
        Usuario usr = new Usuario(null, null, null, false);
        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                System.out.println("Login bem-sucedido!");
                return obterDadosUsuario(email);
            } else {
                System.out.println("Credenciais inválidas. Tente novamente.");
                return usr;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return usr;
        }
    }

    public void editPass(String senha) {
        this.senha = senha;
    }

    private Usuario obterDadosUsuario(String email) {
        String sql = "SELECT * FROM users WHERE email = '" + email + "'";
        Usuario usr = new Usuario(null, null, null, false);
        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                usr = new Usuario(resultSet.getString("nome"), resultSet.getString("email"),
                        resultSet.getString("senha"), resultSet.getBoolean("isAdmin"));
                return usr;
            } else {
                System.out.println("Usuário não encontrado.");
                return usr;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return usr;
        }
    }

    public void criarConta(String nome, String email, String senha) {

        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                Statement statement = connection.createStatement()) {
            int userId = obterProximoUserId(connection);
            String sql = "INSERT INTO users (userId, nome, email, senha, isAdmin) VALUES ('"
                    + userId + "', '" + nome
                    + "', '" + email + "', '" + senha + "', " + false + ")";

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Conta criada com sucesso!");
                this.userId = userId;
                this.nome = nome;
                this.email = email;
                this.senha = senha;
                this.isAdmin = false;
            } else {
                System.out.println("Não foi possível criar a conta.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int obterProximoUserId(Connection connection) throws SQLException {
        String sql = "SELECT MAX(userId) FROM users";
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

    public String deleteUser(String email) {
        String sql = "DELETE FROM users WHERE email = '" + email + "'";

        try (Connection connection = DriverManager.getConnection(sqlKeys.url, sqlKeys.user, sqlKeys.password);
                Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                return "Usuário " + email + " excluído com sucesso.";
            } else {
                return "Usuário " + email + " não encontrado.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao excluir o usuário " + email + ".";
        }
    }

    // public String showUser(String email) {
    // String sql = "SELECT * FROM users WHERE email = '" + email + "'";

    // try (Connection connection = DriverManager.getConnection(sqlKeys.url,
    // sqlKeys.user, sqlKeys.password);
    // Statement statement = connection.createStatement();
    // ResultSet resultSet = statement.executeQuery(sql)) {

    // if (resultSet.next()) {
    // this.nome = resultSet.getString("nome");
    // this.email = resultSet.getString("email");
    // }

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }

    // String str = "";
    // str = ("Nome: " + this.nome + "\n");
    // str = str + ("Email: " + this.email + "\n");
    // return str;
    // }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
