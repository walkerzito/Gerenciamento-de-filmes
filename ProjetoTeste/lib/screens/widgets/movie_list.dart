import 'package:appbd/assets/colors.dart';
import 'package:flutter/material.dart';

import '../../classes/movie.dart';
import '../../classes/user.dart';
import '../reviews_page.dart';

class MovieList extends StatelessWidget {
  final List<Movie> movies;
  User usuario;

  MovieList({required this.movies, required this.usuario});

  @override
  Widget build(BuildContext context) {
    movies.sort((a, b) => a.name.compareTo(b.name));
    print(movies.length);
    return Expanded(
      child: ListView.builder(
        itemCount: movies.length,
        itemBuilder: (context, index) {
          final movie = movies[index];
          return ElevatedButton(
            style: ButtonStyle(
              padding: MaterialStateProperty.all(EdgeInsets.all(0)),
              backgroundColor: MaterialStateProperty.all<Color>(
                CustomTheme.corDeFundo,
              ),
            ),
            onPressed: () {
              Navigator.of(context).push(
                MaterialPageRoute(
                  builder: (context) =>
                      MovieReviewsScreen(movie: movie, usuario: usuario),
                ),
              );
            },
            child: Stack(
              children: [
                Padding(
                  padding:
                      const EdgeInsets.symmetric(horizontal: 5.0, vertical: 10),
                  child: Container(
                    padding: EdgeInsets.all(5),
                    color: CustomTheme.corDeFundoCard,
                    child: Row(
                      children: [
                        Image.network(movie.image,
                            width: MediaQuery.of(context).size.width * 0.25),
                        Expanded(
                          child: ListTile(
                            title: Text(
                              movie.name,
                              style: TextStyle(color: Colors.white),
                            ),
                            subtitle: Text(
                              movie.genre,
                              style: TextStyle(color: Colors.white38),
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
                Positioned(
                  top: 13,
                  right: 9,
                  child: Row(
                    children: [
                      Text(movie.rating.toString()),
                      Padding(
                        padding: const EdgeInsets.all(1.0),
                        child: Icon(
                          Icons.star,
                          color: Colors.amber,
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}
