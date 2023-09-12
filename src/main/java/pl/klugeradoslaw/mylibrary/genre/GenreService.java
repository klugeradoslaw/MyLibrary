package pl.klugeradoslaw.mylibrary.genre;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.klugeradoslaw.mylibrary.genre.dto.GenreDto;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreDtoMapper genreDtoMapper;

    public GenreService(GenreRepository genreRepository, GenreDtoMapper genreDtoMapper) {
        this.genreRepository = genreRepository;
        this.genreDtoMapper = genreDtoMapper;
    }

    public GenreDto addGenre(GenreDto genreDto) {
        Genre genreToSave = genreDtoMapper.mapToEntity(genreDto);
        Genre savedGenre = genreRepository.save(genreToSave);
        return genreDtoMapper.mapToDto(savedGenre);
    }
}
