public class App {
    public static void main(String[] args) {
        Usuario user = new Usuario(-1, null, null, null, false);
        Filme filme = new Filme(null, 0, 0, null, null);
        Screens telas = new Screens();
        Review rev = new Review(0, null, null, null);
        int op = 0, op2 = 0;

        // Login e Criação de conta
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

        // Menu
        op = telas.mainMenu();
        switch (op) {
            case 1:
                op2 = telas.displayFilmes(filme.showFilmes());
                if (op2 == 0) {
                    op = telas.mainMenu();
                } else {
                    telas.displayComments(rev.getAllRevielsFromId(op2));
                }
                break;
            case 2:
                // Conta
                break;
            case 3:
                // Opções de Adm
                break;
            case 4:
                // sair
                break;

            default:
                op = telas.mainMenu();
                break;
        }

    }
}