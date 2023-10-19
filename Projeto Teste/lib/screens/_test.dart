import 'package:flutter_test/flutter_test.dart';
import 'until.dart';

void main() {
  test('Teste de e-mail válido', () {
    expect(isValidEmail('email@example.com'), isTrue);
  });

  test('Teste de e-mail inválido (vazio)', () {
    expect(isValidEmail(''), isFalse);
  });

  test('Teste de e-mail inválido (formato incorreto)', () {
    expect(isValidEmail('email@exemplo'), isFalse);
  });

  // Adicione mais testes conforme necessário
}
