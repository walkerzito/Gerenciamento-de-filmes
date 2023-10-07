import java.util.Scanner;

public class Screens {
    Scanner sc = new Scanner(System.in);

    Usuario loginScreen() {
        Usuario user = new Usuario(null, null, null, false);
        System.out.println("Login: ");
        user.email = sc.nextLine();
        System.out.println("Senha: ");
        user.senha = sc.nextLine();
        return user;
    }
}
