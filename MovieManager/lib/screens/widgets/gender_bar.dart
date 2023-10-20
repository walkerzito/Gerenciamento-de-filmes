import 'package:appbd/assets/colors.dart';
import 'package:flutter/material.dart';

class GenreBar extends StatefulWidget {
  final List<String> genres;
  final Function(String) onGenreSelected;

  GenreBar({required this.genres, required this.onGenreSelected});

  @override
  _GenreBarState createState() => _GenreBarState();
}

class _GenreBarState extends State<GenreBar> {
  int selectedGenreIndex = 0;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: MediaQuery.of(context).size.width,
      height: MediaQuery.of(context).size.height * 0.1,
      child: ListView.builder(
        scrollDirection: Axis.horizontal,
        itemCount: widget.genres.length,
        itemBuilder: (context, index) {
          final isSelected = selectedGenreIndex == index;
          return TextButton(
            onPressed: () {
              setState(() {
                selectedGenreIndex = index;
              });
              widget.onGenreSelected(widget.genres[index]);
            },
            child: Padding(
              padding: const EdgeInsets.all(10.0),
              child: Container(
                padding:
                    const EdgeInsets.symmetric(horizontal: 10.0, vertical: 5),
                decoration: BoxDecoration(
                  color: isSelected ? CustomTheme.buttons : Colors.grey,
                  borderRadius: BorderRadius.circular(20.0),
                ),
                child: Center(
                  child: Text(
                    widget.genres[index],
                    style: TextStyle(
                      color: isSelected ? Colors.white : Colors.black,
                    ),
                  ),
                ),
              ),
            ),
          );
        },
      ),
    );
  }
}
