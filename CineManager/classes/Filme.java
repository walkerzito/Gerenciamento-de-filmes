import java.sql.Date;

public class Filme {
    String nome;
    double rating;
    Date releasDate;
    Review reviews[];

    public Filme(String nome, double rating, Date releasDate, Review[] reviews) {
        this.nome = nome;
        this.rating = rating;
        this.releasDate = releasDate;
        this.reviews = reviews;
    }

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
    }

    public String deleteFilme(String nome) {
        return nome;
    }

    public String showFilme(String nome) {
        String str = "";
        str = ("Nome: " + this.nome + "\n");
        str = str + ("Rating: " + this.rating + "\n");
        str = str + ("Lançamento: " + this.releasDate + "\n");
        str = str + ("Avaliações: " + this.reviews + "\n");
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
