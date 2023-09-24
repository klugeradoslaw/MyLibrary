package pl.klugeradoslaw.mylibrary.book;

import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.book.dto.BookDto;
import pl.klugeradoslaw.mylibrary.book.dto.BookSaveDto;
import pl.klugeradoslaw.mylibrary.genre.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GenreService genreService;

    public BookService(BookRepository bookRepository, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
    }

    public Optional<BookDto> findBookById(Long id) {
        return bookRepository.findById(id).map(BookDtoMapper::map);
    }

    public List<BookDto> findBooksByTitle(String title) {
        return bookRepository.findAllByTitleContainingIgnoreCase(title)
                .stream()
                .map(BookDtoMapper::map)
                .collect(Collectors.toList());
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookDtoMapper::map)
                .collect(Collectors.toList());
    }

    public BookDto addBook(BookSaveDto bookSaveDto) {
        Book book = new Book();
        book.setTitle(bookSaveDto.getTitle());
        book.setAuthor(bookSaveDto.getAuthor());
        book.setGenre(genreService.getGenreByName(bookSaveDto.getGenre()));
        book.setIsbn(bookSaveDto.getIsbn());
        book.setRatings(new ArrayList<>());
        bookRepository.save(book);
        return BookDtoMapper.map(book);
    }

    public void updateBook(Long bookId, BookDto bookDto) {
        Book bookById = bookRepository.findById(bookId).orElseThrow();
        bookById.setTitle(bookDto.getTitle());
        bookById.setAuthor(bookDto.getTitle());
        bookById.setGenre(genreService.getGenreByName(bookDto.getGenre()));
        bookById.setIsbn(bookDto.getIsbn());
        bookRepository.save(bookById);
    }

    public boolean existByIsbn(Long isbn) {
        return bookRepository.existsBookByIsbn(isbn);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}

