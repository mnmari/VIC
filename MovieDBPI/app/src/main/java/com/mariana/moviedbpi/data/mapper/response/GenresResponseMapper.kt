package com.mariana.moviedbpi.data.mapper.response

import com.mariana.moviedbpi.data.model.response.AllGenresResponse
import com.mariana.moviedbpi.data.model.response.GenresResponse
import com.mariana.moviedbpi.domain.entity.AllGenres
import com.mariana.moviedbpi.domain.entity.Genres

class GenresResponseMapper {
    fun mapAllGenres(allGenresResponse : AllGenresResponse) = AllGenres(
        genres = allGenresResponse.genres.map { mapGenres(it) }
    )

    fun mapGenres(genresResponse : GenresResponse) = Genres(
        genreID = genresResponse.genreID,
        name = genresResponse.name)
}