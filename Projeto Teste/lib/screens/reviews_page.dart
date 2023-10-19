import 'package:appbd/assets/colors.dart';
import 'package:appbd/sql_instance.dart';
import 'package:flutter/material.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
import 'package:postgres/postgres.dart';

import '../classes/movie.dart';
import '../classes/reviews.dart';
import '../classes/user.dart';

class MovieReviewsScreen extends StatefulWidget {
  final Movie movie;
  User usuario;

  MovieReviewsScreen({required this.movie, required this.usuario});

  @override
  _MovieReviewsScreenState createState() => _MovieReviewsScreenState();
}

class _MovieReviewsScreenState extends State<MovieReviewsScreen> {
  List<Review> reviews = [];
  TextEditingController textController = TextEditingController();
  late double _rating;

  @override
  void initState() {
    super.initState();
    loadReviews();
  }

  Future<void> loadReviews() async {
    final query = '''
      SELECT R.*
      FROM REVIEWS R
      INNER JOIN MOVIES_REVIEWS MR ON R.reviewid = MR.reviewid
      WHERE MR.movieid = ${widget.movie.id};
    ''';
    PostgreSQLResult reviewResult =
        await SQLInstance.executeQuerry(query: query);
    setState(() {
      reviews = reviewResult
          .map((row) => Review(
              reviewId: row[0] as int,
              userId: row[1] as int,
              postDate: row[2] as DateTime,
              contentString: row[3] as String,
              rating: double.parse(row[4].toString()) as double))
          .toList();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: CustomTheme.corDeFundo,
        title: Text(widget.movie.name),
      ),
      backgroundColor: CustomTheme.corDeFundo,
      body: Stack(
        children: [
          SingleChildScrollView(
            child: SizedBox(
              width: MediaQuery.of(context).size.width,
              height: MediaQuery.of(context).size.height,
              child: Column(
                children: [
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Center(
                        child: Image.network(
                      widget.movie.image,
                      width: MediaQuery.of(context).size.width * 0.8,
                    )),
                  ),
                  Expanded(
                    child: ListView.builder(
                      physics: NeverScrollableScrollPhysics(),
                      itemCount: reviews.length,
                      itemBuilder: (context, index) {
                        final review = reviews[index];
                        return Container(
                          padding: EdgeInsets.all(5),
                          color: CustomTheme.corDeFundoCard,
                          child: ListTile(
                            title: Text(
                              review.contentString,
                              style: TextStyle(color: Colors.white),
                            ),
                            subtitle: Row(
                              children: [
                                Padding(
                                  padding: const EdgeInsets.all(1.0),
                                  child: Icon(
                                    Icons.star,
                                    color: Colors.amber,
                                  ),
                                ),
                                Text(
                                  'Avaliação: ${review.rating}',
                                  style: TextStyle(color: Colors.white),
                                ),
                              ],
                            ),
                          ),
                        );
                      },
                    ),
                  ),
                ],
              ),
            ),
          ),
          Align(
            alignment: Alignment.bottomCenter,
            child: Container(
              color: CustomTheme.corDeFundoCard,
              child: TextField(
                controller: textController,
                cursorColor: Colors.white,
                style: TextStyle(color: Colors.white),
                decoration: InputDecoration(
                    suffixIcon: IconButton(
                      onPressed: () {
                        showDialog<String>(
                          context: context,
                          builder: (BuildContext context) => AlertDialog(
                            title: const Text('Dê uma nota'),
                            content: Container(
                              width: MediaQuery.of(context).size.width * 0.88,
                              height: MediaQuery.of(context).size.height * 0.1,
                              child: Center(
                                child: RatingBar.builder(
                                  initialRating: 0.0,
                                  minRating: 0.0,
                                  direction: Axis.horizontal,
                                  allowHalfRating: true,
                                  unratedColor: Colors.amber.withAlpha(50),
                                  itemCount: 5,
                                  itemSize: 30.0,
                                  itemPadding:
                                      EdgeInsets.symmetric(horizontal: 4.0),
                                  itemBuilder: (context, _) => Icon(
                                    Icons.star,
                                    color: Colors.amber,
                                  ),
                                  onRatingUpdate: (rating) {
                                    setState(() {
                                      _rating = rating;
                                    });
                                  },
                                  updateOnDrag: true,
                                ),
                              ),
                            ),
                            actions: <Widget>[
                              TextButton(
                                onPressed: () =>
                                    Navigator.pop(context, 'Cancel'),
                                child: const Text('Cancel'),
                              ),
                              TextButton(
                                onPressed: () async {
                                  PostgreSQLResult maxRIDquerry =
                                      await SQLInstance.executeQuerry(
                                          query:
                                              'SELECT MAX(reviewid) FROM REVIEWS');
                                  int maxRID = 0;
                                  for (PostgreSQLResultRow row
                                      in maxRIDquerry) {
                                    maxRID = row[0];
                                  }
                                  maxRID++;
                                  DateTime date = DateTime.now();
                                  await SQLInstance.executeQuerry(
                                      query:
                                          "INSERT INTO REVIEWS (reviewid, fk_userid, post_date, content_string, rating) VALUES ($maxRID, ${widget.usuario.userid}, '${date.year}-${date.month}-${date.day}', '${textController.text}', $_rating);");
                                  await SQLInstance.executeQuerry(
                                      query:
                                          "INSERT INTO MOVIES_REVIEWS (movieid, reviewid) VALUES (${widget.movie.id}, $maxRID);");
                                  Navigator.pop(context, 'OK');
                                },
                                child: const Text('OK'),
                              ),
                            ],
                          ),
                        );
                      },
                      icon: Icon(
                        Icons.send,
                        color: Color.fromARGB(255, 162, 154, 154),
                      ),
                    ),
                    labelText: 'Digite seu comentário',
                    labelStyle: TextStyle(color: Colors.white)),
              ),
            ),
          )
        ],
      ),
    );
  }
}
