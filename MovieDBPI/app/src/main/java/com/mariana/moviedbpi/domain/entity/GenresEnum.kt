package com.mariana.moviedbpi.domain.entity

enum class GenresEnum(
    val id : Int,
    val description : String
    ) {
        ACTION(28, "Ação"),
        ADVENTURE(12, "Aventura"),
        ANIMATION(16, "Animação"),
        COMEDY(35, "Comédia"),
        CRIME(80, "Crime"),
        DOCUMENTARY(99, "Documentário"),
        DRAMA(18, "Drama"),
        FAMILY(10751, "Família"),
        FANTASY(14, "Fantasia"),
        HISTORY(36, "História"),
        HORROR(27, "Terror"),
        MUSICAL(10402, "Música"),
        MYSTERY(9648, "Mistério"),
        ROMANCE(10749, "Romance"),
        SCIENCE_FICTION(878, "Ficção científica"),
        TV_CINEMA(10770, "Cinema TV"),
        THRILLER(53, "Thriller"),
        WAR(10752, "Guerra"),
        WESTERN(37, "Faroeste"),
        ALL(0, "Novo")
}

