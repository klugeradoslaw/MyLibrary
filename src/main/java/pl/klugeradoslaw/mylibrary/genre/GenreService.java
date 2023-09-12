package pl.klugeradoslaw.mylibrary.genre;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.klugeradoslaw.mylibrary.genre.dto.GenreDto;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    public GenreDto addGenre(GenreDto genreDto) {
        Genre genreToSave = GenreDtoMapper.mapToEntity(genreDto);
        Genre savedGenre = genreRepository.save(genreToSave);
        return GenreDtoMapper.mapToDto(savedGenre);

    }
}
