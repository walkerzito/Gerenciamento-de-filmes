// ignore_for_file: unused_import

import 'login.dart';

bool isValidEmail(String email) {
  if (email.isEmpty) {
    return false;
  }
  final emailRegex = RegExp(r'^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$');
  return emailRegex.hasMatch(email);
}