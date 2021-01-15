package com.example.mytrivia.helpers

/**
 * @author Axel Sanchez
 */
enum class Constants(val valueString: String, val valueInt: Int) {

    EASY("easy", 0),
    MEDIUM("medium", 0),
    HARD("hard", 0),

    MULTIPLE_CHOICE("multiple", 0),
    TRUE_FALSE("boolean", 0),

    GENERAL_KNOWLEDGE("General Knowledge", 9),
    ENTERTAINMENT_BOOKS("Entertainment: Books",10),
    VEHICLES("Vehicles", 28),
    ANIMALS("Animals", 27),
    CELEBRITIES("Celebrities", 26),
    ART("Art", 25),
    POLITICS("Politics", 24),
    HISTORY("History", 23),
    GEOGRAPHY("Geography", 22),
    SPORTS("Sports", 21),
    MYTHOLOGY("Mythology", 20),
    SCIENCE_GADGETS("Science: Gadgets", 30),
    SCIENCE_MATHEMATICS("Science: Mathematics", 19),
    SCIENCE_COMPUTERS("Science: Computers", 18),
    SCIENCE_AND_NATURE("Science & Nature", 17),
    ENTERTAINMENT_CARTOON_AND_ANIMATIONS("Entertainment: Cartoon & Animations", 32),
    ENTERTAINMENT_JAPANESE_ANIME_AND_MANGA("Entertainment: Japanese Anime & Manga", 31),
    ENTERTAINMENT_COMICS("Entertainment: Comics", 29),
    ENTERTAINMENT_VIDEO_GAMES("Entertainment: Video Games", 15),
    ENTERTAINMENT_BOARD_GAMES("Entertainment: Board Games", 16),
    ENTERTAINMENT_TELEVISION("Entertainment: Television", 14),
    ENTERTAINMENT_MUSICALS_AND_THEATRES("Entertainment: Musicals y Theatres", 13),
    ENTERTAINMENT_MUSIC("Entertainment: Music", 12),
    ENTERTAINMENT_FILMS("Entertainment: Film", 11)


}

val listCategories = listOf(
    Constants.GENERAL_KNOWLEDGE, Constants.ENTERTAINMENT_BOOKS, Constants.VEHICLES,
    Constants.ANIMALS, Constants.CELEBRITIES, Constants.ART, Constants.ENTERTAINMENT_FILMS,
    Constants.POLITICS, Constants.HISTORY, Constants.GEOGRAPHY, Constants.SPORTS, Constants.MYTHOLOGY,
    Constants.SCIENCE_GADGETS, Constants.SCIENCE_MATHEMATICS, Constants.SCIENCE_COMPUTERS,
    Constants.SCIENCE_AND_NATURE, Constants.ENTERTAINMENT_CARTOON_AND_ANIMATIONS,
    Constants.ENTERTAINMENT_JAPANESE_ANIME_AND_MANGA, Constants.ENTERTAINMENT_COMICS,
    Constants.ENTERTAINMENT_VIDEO_GAMES, Constants.ENTERTAINMENT_BOARD_GAMES,
    Constants.ENTERTAINMENT_TELEVISION, Constants.ENTERTAINMENT_MUSICALS_AND_THEATRES,
    Constants.ENTERTAINMENT_MUSIC
)