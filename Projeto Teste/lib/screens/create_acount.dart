import 'package:appbd/assets/colors.dart';
import 'package:appbd/classes/user.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/container.dart';
import 'package:flutter/src/widgets/framework.dart';

import 'main_page.dart';

class CreateAcoount extends StatefulWidget {
  const CreateAcoount({super.key});

  @override
  State<CreateAcoount> createState() => _CreateAcoountState();
}

class _CreateAcoountState extends State<CreateAcoount> {
  @override
  final TextEditingController emailController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  final TextEditingController nameController = TextEditingController();
  bool isLoading = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: CustomTheme.corDeFundo,
      body: Container(
        padding: EdgeInsets.all(20.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.only(bottom: 8.0),
              child: TextField(
                controller: nameController,
                decoration: InputDecoration(
                  hintText: 'Nome',
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
            Padding(
              padding: const EdgeInsets.only(bottom: 8.0),
              child: TextField(
                controller: emailController,
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
              controller: passwordController,
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
            ElevatedButton(
                style: ButtonStyle(
                    backgroundColor:
                        MaterialStateProperty.all<Color>(CustomTheme.buttons)),
                onPressed: () async {
                  if (passwordController.text != "" &&
                      emailController.text != "" &&
                      nameController.text != "") {
                    setState(() {
                      isLoading = true;
                    });
                    User? usuario = await User.createAcoount(
                            nameController.text,
                            emailController.text,
                            passwordController.text)
                        .then(
                      (user) => Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => MainPage(
                                  usuario: user,
                                )),
                      ),
                    );
                  } else {}
                },
                child: isLoading
                    ? Center(child: CircularProgressIndicator())
                    : Text('Criar conta')),
          ],
        ),
      ),
    );
  }
}
