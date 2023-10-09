import java.util.ArrayList;
import java.util.Scanner;

public class Screens {
    Scanner sc = new Scanner(System.in);

    Usuario loginScreen(Usuario user) {
        limpaTelaWindows();
        System.out.println("  ____ _            __  __                                   \r\n" + //
                " / ___(_)_ __   ___|  \\/  | __ _ _ __   __ _  __ _  ___ _ __ \r\n" + //
                "| |   | | '_ \\ / _ \\ |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|\r\n" + //
                "| |___| | | | |  __/ |  | | (_| | | | | (_| | (_| |  __/ |   \r\n" + //
                " \\____|_|_| |_|\\___|_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|   \r\n" + //
                "                                             |___/           ");

        System.out.println("LOGIN:");
        user.email = sc.nextLine();
        System.out.println("SENHA:");
        user.senha = sc.nextLine();
        return user;
    }

    int criarOuLogar() {
        limpaTelaWindows();
        int escolha;
        System.out.println("  ____ _            __  __                                   \r\n" + //
                " / ___(_)_ __   ___|  \\/  | __ _ _ __   __ _  __ _  ___ _ __ \r\n" + //
                "| |   | | '_ \\ / _ \\ |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|\r\n" + //
                "| |___| | | | |  __/ |  | | (_| | | | | (_| | (_| |  __/ |   \r\n" + //
                " \\____|_|_| |_|\\___|_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|   \r\n" + //
                "                                             |___/           ");

        System.out.println("Escolha uma opção:");
        System.out.println("1. Login");
        System.out.println("2. Criar Conta");
        escolha = sc.nextInt();
        sc.nextLine();
        return escolha;
    }

    Usuario criarConta(Usuario user) {
        limpaTelaWindows();
        System.out.println("  ____ _            __  __                                   \r\n" + //
                " / ___(_)_ __   ___|  \\/  | __ _ _ __   __ _  __ _  ___ _ __ \r\n" + //
                "| |   | | '_ \\ / _ \\ |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|\r\n" + //
                "| |___| | | | |  __/ |  | | (_| | | | | (_| | (_| |  __/ |   \r\n" + //
                " \\____|_|_| |_|\\___|_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|   \r\n" + //
                "                                             |___/           ");

        System.out.println("Nome:");
        user.nome = sc.nextLine();
        System.out.println("Email");
        user.email = sc.nextLine();
        System.out.println("Senha");
        user.senha = sc.nextLine();
        return user;
    }

    int mainMenu() {
        limpaTelaWindows();
        int escolha;
        System.out.println("  ____ _            __  __                                   \r\n" + //
                " / ___(_)_ __   ___|  \\/  | __ _ _ __   __ _  __ _  ___ _ __ \r\n" + //
                "| |   | | '_ \\ / _ \\ |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|\r\n" + //
                "| |___| | | | |  __/ |  | | (_| | | | | (_| | (_| |  __/ |   \r\n" + //
                " \\____|_|_| |_|\\___|_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|   \r\n" + //
                "                                             |___/           ");

        System.out.println("Escolha uma opção:");
        System.out.println("1. Filme");
        System.out.println("2. Conta");
        System.out.println("3. ADM");
        System.out.println("4. Sair");
        escolha = sc.nextInt();
        sc.nextLine();
        return escolha;
    }

    void limpaTelaWindows() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    int displayFilmes(ArrayList<Filme> filmes) {
        limpaTelaWindows();
        int escolha;
        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            System.out.println("Filme #" + (i + 1));
            System.out.println("Nome: " + filme.getNome());
            System.out.println("Rating: " + filme.getRating());
            System.out.println("Data de lançamento: " + filme.releasDate);
            System.out.println();
        }
        System.out.println("Digite um número para ver os reviews, ou 0 para voltar ao menu: ");
        escolha = sc.nextInt();
        sc.nextLine();
        return escolha;
    }

    int displayComments(ArrayList<Integer> revs) {
        limpaTelaWindows();
        int escolha;
        for (int i = 0; i < revs.size(); i++) {
            Review rev = revs.get(i);
            System.out.println("rev #" + (i + 1));
            System.out.println(rev.content);
            System.out.println();
        }
        escolha = sc.nextInt();
        sc.nextLine();
        return escolha;
    }

    int exibirUser(Usuario user) {
        int escolha = 0;
        limpaTelaWindows();
        System.out.println("Email: " + user.email);
        System.out.println("Nome: " + user.nome);
        System.out.println("Alterar Senha - 1\nVoltar - 2");
        escolha = sc.nextInt();
        sc.nextLine();
        return escolha;
    }

    void editarSenha(Usuario user) {
        System.out.println("Digite a Nova senha: ");
        String senha = sc.nextLine();
        sc.nextLine();
        user.editPass(senha, user.userId);
    }

}
