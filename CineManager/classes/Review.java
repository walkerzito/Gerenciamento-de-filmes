import java.sql.Date;

public class Review {
    String userId;
    Date dataDePostagem;
    String content;
    String reviewId;

    public Review(String userId, Date dataDePostagem, String content, String reviewId) {
        this.content = reviewId;
        this.userId = userId;
        this.dataDePostagem = dataDePostagem;
        this.content = content;
    }

    public void editarReview(String content) {

        this.content = content;
    }

    public String deleteReview(String nome) {
        return nome;
    }

    public String showFilme(String nome) {
        String str = "";
        str = ("Nome: " + this.reviewId + "\n");
        str = str + ("Data: " + this.dataDePostagem + "\n");
        str = str + ("Review: " + this.content + "\n");
        return str;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

}
