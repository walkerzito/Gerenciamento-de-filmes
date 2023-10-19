class Movie {
  final int id;
  final String image;
  final String genre;
  final String name;
  final double rating;
  final DateTime releaseDate;
  final int reviewId;

  Movie({
    required this.id,
    required this.image,
    required this.genre,
    required this.name,
    required this.rating,
    required this.releaseDate,
    required this.reviewId,
  });
}
