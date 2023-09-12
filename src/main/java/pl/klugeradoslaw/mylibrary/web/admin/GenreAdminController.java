package pl.klugeradoslaw.mylibrary.web.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.klugeradoslaw.mylibrary.genre.GenreService;
import pl.klugeradoslaw.mylibrary.genre.dto.GenreDto;

import java.net.URI;

@Controller
@RequestMapping("/admin")
public class GenreAdminController {
    private final GenreService genreService;

    public GenreAdminController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/genre")
    public ResponseEntity<?> addGenre(@RequestBody GenreDto genreDto) {
        GenreDto addedGenre = genreService.addGenre(genreDto);
//        URI addedGenreUri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(addedGenre.getId())
//                .toUri();
        return ResponseEntity.ok(addedGenre);
    }
}
