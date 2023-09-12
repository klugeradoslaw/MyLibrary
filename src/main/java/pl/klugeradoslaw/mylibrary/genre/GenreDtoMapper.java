package pl.klugeradoslaw.mylibrary.genre;

import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.genre.dto.GenreDto;

@Service
public class GenreDtoMapper {
    public GenreDto mapToDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }

    public Genre mapToEntity(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setId(genreDto.getId());
        genre.setName(genreDto.getName());
        return genre;
    }
}
