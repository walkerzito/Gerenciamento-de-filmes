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
        return escolha;
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
        return escolha;
    }

    void limpaTelaWindows() {
        System.out.print("\033[H\033[2J");
    }

}
