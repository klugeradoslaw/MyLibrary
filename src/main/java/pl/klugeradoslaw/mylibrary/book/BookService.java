package pl.klugeradoslaw.mylibrary.book;

import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.book.dto.BookDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Optional<BookDto> findBookById(Long id) {
        return bookRepository.findById(id).map(BookDtoMapper::mapToDto);
    }

    public List<BookDto> findBooksByTitle (String title) {
        return bookRepository.findAllByTitleContainingIgnoreCase(title)
                .stream()
                .map(BookDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}

