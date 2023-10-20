class Review {
  final int reviewId;
  final int userId;
  final DateTime postDate;
  final String contentString;
  final double rating;

  Review({
    required this.reviewId,
    required this.userId,
    required this.postDate,
    required this.contentString,
    required this.rating,
  });

  factory Review.fromMap(Map<String, dynamic> map) {
    return Review(
      reviewId: map['reviewid'],
      userId: map['fk_userid'],
      postDate: DateTime.parse(map['post_date']),
      contentString: map['content_string'],
      rating: map['rating'].toDouble(),
    );
  }
}
