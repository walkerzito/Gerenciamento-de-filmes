public class App {
    public static void main(String[] args) {
        Usuario user = new Usuario(null, null, null, false);
        user = user.login("123", "124");
        System.out.println(user.nome);
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
