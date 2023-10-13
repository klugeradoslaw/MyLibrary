package pl.klugeradoslaw.mylibrary.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.klugeradoslaw.mylibrary.genre.GenreService;
import pl.klugeradoslaw.mylibrary.genre.dto.GenreDto;

import java.net.URI;

@RestController
@RequestMapping("/admin")
@Slf4j
public class GenreAdminController {
    private final GenreService genreService;

    public GenreAdminController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/genre")
    public ResponseEntity<?> addGenre(@RequestBody GenreDto genreDto, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            GenreDto addedGenre = genreService.addGenre(genreDto);
            URI addedGenreUri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(addedGenre.getId())
                    .toUri();
            log.info("Genre added to database.");
            return ResponseEntity.created(addedGenreUri).body(addedGenre);
        } else {
            log.info("Access denied.");
            return new ResponseEntity<>("error", HttpStatus.FORBIDDEN);
        }
    }
}
