import 'package:flutter_test/flutter_test.dart';
import 'package:appbd/classes/movie.dart';
import 'create_acount.dart';

void main() {
  test('Teste de Construção da Classe Movie', () {
    final movie = Movie(
      id: 1,
      image: 'image_path',
      genre: 'Ação',
      name: 'Nome do Filme',
      rating: 4.5,
      releaseDate: DateTime(2023, 10, 1),
      reviewId: 123,
    );

    expect(movie.id, 1);
    expect(movie.image, 'image_path');
    expect(movie.genre, 'Ação');
    expect(movie.name, 'Nome do Filme');
    expect(movie.rating, 4.5);
    expect(movie.releaseDate, DateTime(2023, 10, 1));
    expect(movie.reviewId, 123);
  });

  // testWidgets('Teste de Funcionalidade CreateAcoount',
  //     (WidgetTester tester) async {
  //   await tester.pumpWidget(MaterialApp(
  //     home: const CreateAcoount(),
  //   ));

  //   final nameTextField = find.byType(TextField).at(0);
  //   final emailTextField = find.byType(TextField).at(1);
  //   final passwordTextField = find.byType(TextField).at(2);


  //   await tester.enterText(nameTextField, 'Nome de Teste');
  //   await tester.enterText(emailTextField, 'teste@email.com');
  //   await tester.enterText(passwordTextField, 'senha123');


  //   final createAccountButton = find.text('Criar conta');


  //   await tester.tap(createAccountButton);
  //   await tester.pump();
  // });
}
