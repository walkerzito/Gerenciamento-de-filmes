import 'package:appbd/sql_instance.dart';
import 'package:postgres/postgres.dart';

class User {
  int? userid = 0;
  String? email = "";
  String? name = "";
  String? password = "";
  bool? is_admin = false;
  User({
    required this.userid,
    required this.email,
    required this.name,
    required this.password,
    required this.is_admin,
  });

  factory User.fromMap(Map<String, dynamic> map) {
    return User(
      userid: map['userid'],
      email: map['email'],
      name: map['name'],
      password: map['password'],
      is_admin: map['is_admin'],
    );
  }

  static Future<User> createAcoount(
    String name,
    String email,
    String password,
  ) async {
    PostgreSQLResult maxUIDquerry =
        await SQLInstance.executeQuerry(query: 'SELECT MAX(userId) FROM users');
    int maxUID = 0;
    for (PostgreSQLResultRow row in maxUIDquerry) {
      maxUID = row[0];
    }
    maxUID++;
    SQLInstance.executeQuerry(
        query:
            "INSERT INTO users (userid, name, email, password, is_admin) VALUES ($maxUID,'$name','$email','$password',${false})");
    User usuario = User(
      email: email,
      is_admin: false,
      name: name,
      password: password,
      userid: maxUID,
    );
    return usuario;
  }
}
