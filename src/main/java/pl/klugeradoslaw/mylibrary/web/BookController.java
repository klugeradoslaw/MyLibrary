package pl.klugeradoslaw.mylibrary.web;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.klugeradoslaw.mylibrary.book.BookService;
import pl.klugeradoslaw.mylibrary.book.dto.BookDto;
import pl.klugeradoslaw.mylibrary.book.dto.BookSaveDto;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(
        path = "/book",
        produces = {
                MediaType.APPLICATION_JSON_VALUE
        }
)

public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
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
        BookDto savedBookDto = bookService.addBook(book);
        URI addedBookUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBookDto.getId())
                .toUri();
        return ResponseEntity.created(addedBookUri).body(savedBookDto);
    }
}
