public class App {
    public static void main(String[] args) {
        Usuario user = new Usuario(-1, null, null, null, false);
        Screens telas = new Screens();
        int op = 0;

        while (op != 1 && op != 2) {
            op = telas.criarOuLogar();
        }

        if (op == 2) {
            telas.criarConta(user);
            user.criarConta(user.nome, user.email, user.senha);
        } else {
            while (user.userId == -1) {
                telas.loginScreen(user);
                user = user.login(user.email, user.senha);
            }
        }
        System.out.println(user.nome);
    }
}