package pl.klugeradoslaw.mylibrary.genre;

import pl.klugeradoslaw.mylibrary.genre.dto.GenreDto;

public class GenreDtoMapper {
    public static GenreDto mapToDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }

    public static Genre mapToEntity(GenreDto genreDto) {
        Genre genre = new Genre();
        genreDto.setName(genreDto.getName());
        return genre;
    }
}
