package pl.klugeradoslaw.mylibrary.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.klugeradoslaw.mylibrary.library.LibraryService;
import pl.klugeradoslaw.mylibrary.library.dto.LibraryDto;
import pl.klugeradoslaw.mylibrary.user.UserService;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/library")
public class LibraryController {
    private final LibraryService libraryService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public LibraryController(LibraryService libraryService, UserService userService, ObjectMapper objectMapper) {
        this.libraryService = libraryService;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<?> addLibrary(@RequestParam String libraryName, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        LibraryDto savedLibraryDto = libraryService.addLibrary(currentUserEmail, libraryName);
        URI addedLibraryUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLibraryDto.getId())
                .toUri();
        log.info("Added new library.");
        return ResponseEntity.created(addedLibraryUri).body(savedLibraryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLibrary(@PathVariable Long id, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        if (libraryService.getOwnerByLibraryId(id).equals(currentUserEmail) ||
                authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            libraryService.deleteLibrary(id);
            log.info("Deleted library with id ={}", id);
            return ResponseEntity.ok("Library deleted successfully.");
        } else {
            return ResponseEntity.ok("You dont have permission to delete this library!");
        }
    }

    @GetMapping
    public ResponseEntity<List<LibraryDto>> getLibrariesByEmail(@RequestParam String email) {
        List<LibraryDto> listOfLibrariesByEmail = libraryService.getListOfLibrariesByEmail(email);
        return ResponseEntity.ok(listOfLibrariesByEmail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable Long id) {
        return libraryService.findLibraryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/my")
    public ResponseEntity<List<LibraryDto>> getMyLibraries(Authentication authentication) {
        String currentUserEmail = authentication.getName();
        List<LibraryDto> listOfLibrariesByEmail = libraryService.getListOfLibrariesByEmail(currentUserEmail);
        return ResponseEntity.ok(listOfLibrariesByEmail);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLibrary(@PathVariable Long id, @RequestBody JsonMergePatch patch, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        LibraryDto libraryDto = libraryService.findLibraryById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (libraryDto.getUserEmail().equals(currentUserEmail) ||
                authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            try {
                LibraryDto libraryPatched = applyPatch(libraryDto, patch);
                libraryService.updateLibrary(id, libraryPatched);
                log.info("Updated library with id ={}", id);
                return ResponseEntity.ok(libraryService.findLibraryById(id));
            } catch (JsonPatchException | JsonProcessingException e) {
                return ResponseEntity.internalServerError().build();
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            log.info("Update library with id ={} failed. Permission denied.", id);
            return ResponseEntity.ok("You dont have permission to update this library!");
        }
    }

    @PutMapping("/{libraryId}/book/{bookId}")
    public ResponseEntity<?> addBookToLibrary(@PathVariable Long bookId, @PathVariable Long libraryId) {
        libraryService.addBookToLibrary(bookId, libraryId);
        log.info("Book ID={} added to library ID={}.", bookId, libraryId);
        return ResponseEntity.ok("Added book to library.");
    }

    private LibraryDto applyPatch(LibraryDto libraryToUpdateDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode libraryNode = objectMapper.valueToTree(libraryToUpdateDto);
        JsonNode libraryPatchedNode = patch.apply(libraryNode);
        return objectMapper.treeToValue(libraryPatchedNode, LibraryDto.class);
    }

}
