package pl.klugeradoslaw.mylibrary.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.klugeradoslaw.mylibrary.genre.GenreService;
import pl.klugeradoslaw.mylibrary.genre.dto.GenreDto;

import java.util.List;


@RestController
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAllGenres () {
        List<GenreDto> allGenres = genreService.getAllGenres();
        return ResponseEntity.ok(allGenres);
    }

}
