package pl.klugeradoslaw.mylibrary.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.klugeradoslaw.mylibrary.book.BookRepository;
import pl.klugeradoslaw.mylibrary.book.BookService;
import pl.klugeradoslaw.mylibrary.book.dto.BookDto;
import pl.klugeradoslaw.mylibrary.book.dto.BookSaveDto;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping(
        path = "/book",
        produces = {
                MediaType.APPLICATION_JSON_VALUE
        }
)

public class BookController {
    private BookService bookService;
    private final ObjectMapper objectMapper;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, ObjectMapper objectMapper,
                          BookRepository bookRepository) {
        this.bookService = bookService;
        this.objectMapper = objectMapper;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findBookById(@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Get all books or containing word or some letters
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(required = false) String title) {
        if (title != null)
            return ResponseEntity.ok(bookService.findBooksByTitle(title));
        else
            return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookSaveDto book) {
        if (!bookService.existByIsbn(book.getIsbn())) {
            BookDto savedBookDto = bookService.addBook(book);
            URI addedBookUri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedBookDto.getId())
                    .toUri();
            return ResponseEntity.created(addedBookUri).body(savedBookDto);
        } else {
            return ResponseEntity.badRequest().body("Book with ISBN: " + book.getIsbn() + " already exists.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody JsonMergePatch patch, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            try {
                BookDto bookDto = bookService.findBookById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                BookDto bookPatched = applyPatch(bookDto, patch);
                return ResponseEntity.ok(bookPatched);
            } catch (JsonPatchException | JsonProcessingException e) {
                return ResponseEntity.internalServerError().build();
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.ok("You dont have permission to update this library!");
        }
    }

    private BookDto applyPatch(BookDto bookToUpdateDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode bookNode = objectMapper.valueToTree(bookToUpdateDto);
        JsonNode bookPatchedNode = patch.apply(bookNode);
        return objectMapper.treeToValue(bookPatchedNode, BookDto.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted");
        } else {
            return ResponseEntity.ok("Only admin can delete books!");
        }
    }
}

