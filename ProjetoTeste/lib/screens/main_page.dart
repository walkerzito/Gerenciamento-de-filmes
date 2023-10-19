// ignore_for_file: use_build_context_synchronously, prefer_const_constructors

import 'package:appbd/assets/colors.dart';
import 'package:appbd/classes/user.dart';
import 'package:appbd/screens/widgets/gender_bar.dart';
import 'package:appbd/screens/widgets/movie_list.dart';
import 'package:flutter/material.dart';
import 'package:postgres/postgres.dart';

import '../classes/movie.dart';
import '../sql_instance.dart';

class MainPage extends StatefulWidget {
  User? usuario;
  MainPage({this.usuario, super.key});

  @override
  State<MainPage> createState() => MainPageState();
}

class MainPageState extends State<MainPage> {
  List<String> genres = [];
  List<Movie> movies = [];
  PostgreSQLResult? genresResult;
  String? selectedGenre = "";
  TextEditingController? image = TextEditingController();
  TextEditingController? createGenre = TextEditingController();
  TextEditingController? name = TextEditingController();
  // ignore: non_constant_identifier_names
  DateTime? release_date = DateTime.now();

  @override
  void initState() {
    super.initState();
    loadGenresAndMovies();
  }

  Future<void> loadGenresAndMovies() async {
    const genresQuery = """
    SELECT DISTINCT genre
    FROM MOVIES;
  """;

    genresResult = await SQLInstance.executeQuerry(query: genresQuery);

    String moviesQuery = """
    SELECT movieid, image, genre, name, rating, release_date, fk_reviews
    FROM MOVIES
    WHERE genre = '$selectedGenre'
    ORDER BY name;
  """;

    if (genresResult != null && genresResult!.isNotEmpty) {
      setState(() {
        genres = genresResult!.map((row) => row[0] as String).toList();
        selectedGenre = genres.first;
      });

      PostgreSQLResult moviesResult =
          await SQLInstance.executeQuerry(query: moviesQuery);

      if (moviesResult.isNotEmpty) {
        setState(() {
          movies = moviesResult
              .map((row) => Movie(
                    id: row[0] as int,
                    image: row[1] as String,
                    genre: row[2] as String,
                    name: row[3] as String,
                    rating: double.parse(row[4].toString()),
                    releaseDate: row[5] as DateTime,
                    reviewId: row[6] as int,
                  ))
              .toList();
        });
      }
    }
  }

  Future<void> changGenre() async {
    movies.clear();
    String moviesQuery = """
      SELECT movieid, image, genre, name, rating, release_date, fk_reviews
      FROM MOVIES
      WHERE genre = '$selectedGenre'
      ORDER BY name;
    """;

    PostgreSQLResult moviesResult =
        await SQLInstance.executeQuerry(query: moviesQuery);

    if (moviesResult.isNotEmpty) {
      setState(() {
        movies = moviesResult
            .map((row) => Movie(
                  id: row[0] as int,
                  image: row[1] as String,
                  genre: row[2] as String,
                  name: row[3] as String,
                  rating: double.parse(row[4].toString()),
                  releaseDate: row[5] as DateTime,
                  reviewId: row[6] as int,
                ))
            .toList();
      });
    }
  }

  void onGenreSelected(String genre) {
    setState(() {
      selectedGenre = genre;
    });
    changGenre();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: CustomTheme.corDeFundo,
      appBar: AppBar(
        title: Text("Cine Manager"),
        backgroundColor: CustomTheme.corDeFundo,
        actions: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: widget.usuario!.is_admin!
                ? PopupMenuButton<String>(
                    icon: Icon(Icons.menu),
                    onSelected: (value) {
                      if (value == 'create_movie') {
                        showDialog<void>(
                          context: context,
                          builder: (BuildContext context) =>
                              SingleChildScrollView(
                            child: AlertDialog(
                              backgroundColor: CustomTheme.corDeFundo,
                              title: const Text(
                                'Adicionar um filme',
                                style: TextStyle(color: Colors.white),
                              ),
                              content: SizedBox(
                                width: MediaQuery.of(context).size.width * 0.88,
                                child: Center(
                                  child: Column(
                                    children: [
                                      Padding(
                                        padding: const EdgeInsets.all(8.0),
                                        child: TextField(
                                          decoration: InputDecoration(
                                            hintText: 'Nome do Filme',
                                            hintStyle:
                                                TextStyle(color: Colors.white),
                                            contentPadding:
                                                EdgeInsets.symmetric(
                                                    horizontal: 16,
                                                    vertical: 12),
                                            filled: true,
                                            fillColor: Color.fromRGBO(
                                                255, 255, 255, 0.2),
                                            border: OutlineInputBorder(
                                              borderRadius:
                                                  BorderRadius.circular(20.0),
                                            ),
                                          ),
                                          controller: name,
                                        ),
                                      ),
                                      Padding(
                                        padding: const EdgeInsets.all(8.0),
                                        child: TextField(
                                          decoration: InputDecoration(
                                            hintText: 'URL para a imagem',
                                            hintStyle:
                                                TextStyle(color: Colors.white),
                                            contentPadding:
                                                EdgeInsets.symmetric(
                                                    horizontal: 16,
                                                    vertical: 12),
                                            filled: true,
                                            fillColor: Color.fromRGBO(
                                                255, 255, 255, 0.2),
                                            border: OutlineInputBorder(
                                              borderRadius:
                                                  BorderRadius.circular(20.0),
                                            ),
                                          ),
                                          controller: image,
                                        ),
                                      ),
                                      Padding(
                                        padding: const EdgeInsets.all(8.0),
                                        child: TextField(
                                          decoration: InputDecoration(
                                            hintText: 'Genero',
                                            hintStyle:
                                                TextStyle(color: Colors.white),
                                            contentPadding:
                                                EdgeInsets.symmetric(
                                                    horizontal: 16,
                                                    vertical: 12),
                                            filled: true,
                                            fillColor: Color.fromRGBO(
                                                255, 255, 255, 0.2),
                                            border: OutlineInputBorder(
                                              borderRadius:
                                                  BorderRadius.circular(20.0),
                                            ),
                                          ),
                                          controller: createGenre,
                                        ),
                                      ),
                                      Row(
                                        mainAxisAlignment:
                                            MainAxisAlignment.center,
                                        children: [
                                          Text(
                                            "Selecione a data:",
                                            style:
                                                TextStyle(color: Colors.white),
                                          ),
                                          IconButton(
                                            onPressed: () async {
                                              release_date =
                                                  await showDialog<DateTime>(
                                                context: context,
                                                builder:
                                                    (BuildContext context) =>
                                                        AlertDialog(
                                                  actions: [
                                                    DatePickerDialog(
                                                      initialCalendarMode:
                                                          DatePickerMode.day,
                                                      initialDate:
                                                          DateTime.now(),
                                                      firstDate: DateTime(1900),
                                                      lastDate: DateTime(
                                                          DateTime.now().year +
                                                              5),
                                                    ),
                                                  ],
                                                ),
                                              );
                                            },
                                            icon: Icon(
                                              Icons.calendar_month,
                                              color: Colors.white,
                                            ),
                                          ),
                                        ],
                                      ),
                                      Row(
                                        mainAxisAlignment:
                                            MainAxisAlignment.spaceBetween,
                                        children: [
                                          TextButton(
                                            onPressed: () => Navigator.pop(
                                                context, 'Cancel'),
                                            child: const Text('Cancel'),
                                          ),
                                          TextButton(
                                            onPressed: () async {
                                              PostgreSQLResult maxRIDquerry =
                                                  await SQLInstance.executeQuerry(
                                                      query:
                                                          'SELECT MAX(movieid) FROM MOVIES');
                                              int maxMID = 0;
                                              for (PostgreSQLResultRow row
                                                  in maxRIDquerry) {
                                                maxMID = row[0];
                                              }
                                              maxMID++;
                                              String finalImage;
                                              image!.text == ''
                                                  ? finalImage =
                                                      'https://www.biotecdermo.com.br/wp-content/uploads/2016/10/sem-imagem-10.jpg'
                                                  : finalImage = image!.text;
                                              await SQLInstance.executeQuerry(
                                                  query:
                                                      "INSERT INTO MOVIES (movieid, image, genre, name, rating, release_date, fk_reviews)VALUES($maxMID, '$finalImage', '${createGenre!.text}', '${name!.text}', ${0.0}, '${release_date!.year}-${release_date!.month}-${release_date!.day}', 1);");
                                              Navigator.pop(context, 'OK');
                                            },
                                            child: const Text('OK'),
                                          ),
                                        ],
                                      ),
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          ),
                        );
                      }
                    },
                    itemBuilder: (BuildContext context) {
                      return <PopupMenuEntry<String>>[
                        const PopupMenuItem<String>(
                          value: 'create_movie',
                          child: Text('Adicionar Filme'),
                        ),
                      ];
                    },
                  )
                : Container(),
          )
        ],
      ),
      body: Column(
        children: [
          GenreBar(genres: genres, onGenreSelected: onGenreSelected),
          MovieList(
              movies: movies
                  .where((movie) => movie.genre == selectedGenre)
                  .toList(),
              usuario: widget.usuario!),
        ],
      ),
    );
  }
}
