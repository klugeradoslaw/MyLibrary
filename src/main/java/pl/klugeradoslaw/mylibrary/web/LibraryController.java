package pl.klugeradoslaw.mylibrary.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.klugeradoslaw.mylibrary.library.LibraryDtoMapper;
import pl.klugeradoslaw.mylibrary.library.LibraryService;
import pl.klugeradoslaw.mylibrary.library.dto.LibraryDto;
import pl.klugeradoslaw.mylibrary.user.UserService;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {
    private final LibraryService libraryService;
    private final UserService userService;

    public LibraryController(LibraryService libraryService, UserService userService) {
        this.libraryService = libraryService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addLibrary (@RequestParam String libraryName, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        LibraryDto savedLibraryDto = libraryService.addLibrary(currentUserEmail, libraryName);
        URI addedLibraryUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLibraryDto.getId())
                .toUri();
        return ResponseEntity.created(addedLibraryUri).body(savedLibraryDto);
    }

    @GetMapping
    public ResponseEntity<List<LibraryDto>> getLibrariesByEmail (@RequestParam String email) {
        List<LibraryDto> listOfLibrariesByEmail = libraryService.getListOfLibrariesByEmail(email);
        return ResponseEntity.ok(listOfLibrariesByEmail);
    }
}
