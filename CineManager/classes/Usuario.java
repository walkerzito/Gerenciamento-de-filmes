import java.util.ArrayList;
import java.util.List;

public class Usuario {
    String nome;
    String email;
    String senha;
    boolean isAdmin;
    List<Review> reviews;

    public Usuario(String nome, String email, String senha, boolean isAdmin, ArrayList<Review> reviews) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.isAdmin = isAdmin;
        this.reviews = reviews;
    }

    public Usuario login(String login, String senha) {
        // verificar no banco se o user existe;
    }

    public void editPass(String senha) {
        this.senha = senha;
    }

    public String deleteUser(String nome) {
        return nome;
    }

    public String showUser(String email) {
        String str = "";
        str = ("Nome: " + this.nome + "\n");
        str = str + ("Email: " + this.email + "\n");
        for (int i = 0; i < this.reviews.size(); i++) {
            str = str + ("Review " + i + ": " + this.reviews.get(i).showFilme(str) + "\n");
        }
        return str;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

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
