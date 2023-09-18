package pl.klugeradoslaw.mylibrary.web;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.klugeradoslaw.mylibrary.book.BookService;
import pl.klugeradoslaw.mylibrary.book.dto.BookDto;


@RestController
@RequestMapping(
        path = "/books",
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
    public ResponseEntity<BookDto> findBookById (@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
