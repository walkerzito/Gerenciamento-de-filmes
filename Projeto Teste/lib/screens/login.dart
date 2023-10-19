// ignore_for_file: prefer_const_constructors, dead_code

import 'package:appbd/sql_instance.dart';
import 'package:flutter/material.dart';
import 'package:postgres/postgres.dart';

import '../assets/colors.dart';
import '../classes/user.dart';
import 'create_acount.dart';
import 'main_page.dart';

class LoginForm extends StatefulWidget {
  @override
  State<LoginForm> createState() => _LoginFormState();
}

class _LoginFormState extends State<LoginForm> {
  User? user;

  @override
  Widget build(BuildContext context) {
    TextEditingController email = TextEditingController();
    TextEditingController senha = TextEditingController();
    bool isLoading = false;
    return Scaffold(
      backgroundColor: CustomTheme.corDeFundo,
      body: Container(
        padding: EdgeInsets.all(20.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Padding(
              padding: EdgeInsets.all(10),
              child: Image.asset("lib/assets/MM.png"),
            ),
            Padding(
              padding: const EdgeInsets.only(bottom: 8.0),
              child: TextField(
                controller: email,
                decoration: InputDecoration(
                  hintText: 'Email',
                  hintStyle: TextStyle(color: Colors.white),
                  contentPadding:
                      EdgeInsets.symmetric(horizontal: 16, vertical: 12),
                  filled: true,
                  fillColor: Color.fromRGBO(255, 255, 255, 0.2),
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(20.0),
                  ),
                ),
              ),
            ),
            TextField(
              controller: senha,
              decoration: InputDecoration(
                hintText: 'Senha',
                hintStyle: TextStyle(color: Colors.white),
                contentPadding:
                    EdgeInsets.symmetric(horizontal: 16, vertical: 12),
                filled: true,
                fillColor: Color.fromRGBO(255, 255, 255, 0.2),
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(20.0),
                ),
              ),
              obscureText: true,
            ),
            SizedBox(height: 20.0),
            TextButton(
              style: ButtonStyle(
                  backgroundColor:
                      MaterialStateProperty.all<Color>(CustomTheme.buttons)),
              onPressed: () async {
                final bool emailValid = RegExp(
                        r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\.[a-zA-Z]+")
                    .hasMatch(email.text);
                if (emailValid) {
                  PostgreSQLResult? userData = await SQLInstance.executeQuerry(
                      query:
                          "SELECT userid, name, is_admin FROM USERS WHERE email = '${email.text}' AND password = '${senha.text}';");
                  if (userData.isNotEmpty) {
                    setState(() {
                      isLoading = true;
                    });
                    for (PostgreSQLResultRow row in userData) {
                      user = User(
                        userid: row[0],
                        email: email.text,
                        name: row[1],
                        password: senha.text,
                        is_admin: row[2],
                      );
                    }
                    // ignore: use_build_context_synchronously
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => MainPage(
                                usuario: user,
                              )),
                    );
                  }
                }
              },
              child: isLoading
                  ? CircularProgressIndicator()
                  : Text(
                      'Login',
                      style: TextStyle(color: Colors.white),
                    ),
            ),
            SizedBox(height: 20.0),
            ElevatedButton(
              style: ButtonStyle(
                  backgroundColor:
                      MaterialStateProperty.all<Color>(CustomTheme.buttons)),
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (context) => const CreateAcoount()),
                );
              },
              child: Text('Criar Conta'),
            ),
          ],
        ),
      ),
    );
  }
}
