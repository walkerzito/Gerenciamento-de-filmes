import 'package:flutter_test/flutter_test.dart';
import 'package:appbd/classes/movie.dart';

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
}
