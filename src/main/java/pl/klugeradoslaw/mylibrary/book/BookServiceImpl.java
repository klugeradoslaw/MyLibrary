package pl.klugeradoslaw.mylibrary.book;

import pl.klugeradoslaw.mylibrary.book.dto.BookDto;

import java.util.Optional;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<BookDto> findBookById(Long id) {
        return Optional.empty();
    }

}
