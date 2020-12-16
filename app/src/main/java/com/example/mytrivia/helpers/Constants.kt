package com.example.mytrivia.helpers

/**
 * @author Axel Sanchez
 */
object Constants {

    const val EASY = "easy"
    const val MEDIUM = "medium"
    const val HARD = "hard"

    const val MULTIPLE_CHOICE = "multiple"
    const val TRUE_FALSE = "boolean"

    const val GENERAL_KNOWLEDGE = 9
    const val ENTERTAINMENT_BOOKS = 10
    const val VEHICLES = 28
    const val ANIMALS = 27
    const val CELEBRITIES = 26
    const val ART = 25
    const val POLITICS = 24
    const val HISTORY = 23
    const val GEOGRAPHY = 22
    const val SPORTS = 21
    const val MYTHOLOGY = 20
    const val SCIENCE_GADGETS = 30
    const val SCIENCE_MATHEMATICS = 19
    const val SCIENCE_COMPUTERS = 18
    const val SCIENCE_AND_NATURE = 17
    const val ENTERTAINMENT_CARTOON_AND_ANIMATIONS = 32
    const val ENTERTAINMENT_JAPANESE_ANIME_AND_MANGA = 31
    const val ENTERTAINMENT_COMICS = 29
    const val ENTERTAINMENT_VIDEO_GAMES = 15
    const val ENTERTAINMENT_BOARD_GAMES = 16
    const val ENTERTAINMENT_TELEVISION = 14
    const val ENTERTAINMENT_MUSICALS_AND_THEATRES = 13
    const val ENTERTAINMENT_MUSIC = 12
    const val ENTERTAINMENT_FILMS = 11

    fun categoryConverterToNumber(stringCategory: String?): String{
        stringCategory?.let {
            when(stringCategory){
                "General Knowledge" -> return GENERAL_KNOWLEDGE.toString()
                "Entertainment: Books" -> return ENTERTAINMENT_BOOKS.toString()
                "Entertainment: Film" -> return ENTERTAINMENT_FILMS.toString()
                "Entertainment: Music" -> return ENTERTAINMENT_MUSIC.toString()
                "Entertainment: Musicals y Theatres" -> return ENTERTAINMENT_MUSICALS_AND_THEATRES.toString()
                "Entertainment: Television" -> return ENTERTAINMENT_TELEVISION.toString()
                "Entertainment: Video Games" -> return ENTERTAINMENT_VIDEO_GAMES.toString()
                "Entertainment: Board Games" -> return ENTERTAINMENT_BOARD_GAMES.toString()
                "Entertainment: Japanese Anime & Manga" -> return ENTERTAINMENT_JAPANESE_ANIME_AND_MANGA.toString()
                "Entertainment: Cartoon & Animations" -> return ENTERTAINMENT_CARTOON_AND_ANIMATIONS.toString()
                "Entertainment: Comics" -> return ENTERTAINMENT_COMICS.toString()
                "Science & Nature" -> return SCIENCE_AND_NATURE.toString()
                "Science: Computers" -> return SCIENCE_COMPUTERS.toString()
                "Science: Mathematics" -> return SCIENCE_MATHEMATICS.toString()
                "Science: Gadgets" -> return SCIENCE_GADGETS.toString()
                "Mythology" -> return MYTHOLOGY.toString()
                "Sports" -> return SPORTS.toString()
                "Geography" -> return GEOGRAPHY.toString()
                "History" -> return HISTORY.toString()
                "Politics" -> return POLITICS.toString()
                "Art" -> return ART.toString()
                "Celebrities" -> return CELEBRITIES.toString()
                "Animals" -> return ANIMALS.toString()
                "Vehicles" -> return VEHICLES.toString()
                else -> return "0"
            }
        }?:return ""
    }

    fun categoryConverterToText(intCategory: Int?): String{
        intCategory?.let {
            when(intCategory){
                GENERAL_KNOWLEDGE -> return "General Knowledge"
                ENTERTAINMENT_BOOKS -> return "Entertainment: Books"
                ENTERTAINMENT_FILMS -> return "Entertainment: Film"
                ENTERTAINMENT_MUSIC -> return "Entertainment: Music"
                ENTERTAINMENT_MUSICALS_AND_THEATRES -> return "Entertainment: Musicals y Theatres"
                ENTERTAINMENT_TELEVISION -> return "Entertainment: Television"
                ENTERTAINMENT_VIDEO_GAMES -> return "Entertainment: Video Games"
                ENTERTAINMENT_BOARD_GAMES -> return "Entertainment: Board Games"
                ENTERTAINMENT_JAPANESE_ANIME_AND_MANGA -> return "Entertainment: Japanese Anime & Manga"
                ENTERTAINMENT_CARTOON_AND_ANIMATIONS -> return "Entertainment: Cartoon & Animations"
                ENTERTAINMENT_COMICS -> return "Entertainment: Comics"
                SCIENCE_AND_NATURE -> return "Science & Nature"
                SCIENCE_COMPUTERS -> return "Science: Computers"
                SCIENCE_MATHEMATICS -> return "Science: Mathematics"
                SCIENCE_GADGETS -> return "Science: Gadgets"
                MYTHOLOGY -> return "Mythology"
                SPORTS -> return "Sports"
                GEOGRAPHY -> return "Geography"
                HISTORY -> return "History"
                POLITICS -> return "Politics"
                ART -> return "Art"
                CELEBRITIES -> return "Celebrities"
                ANIMALS -> return "Animals"
                VEHICLES -> return "Vehicles"
                else -> return ""
            }
        }?:return ""
    }
}