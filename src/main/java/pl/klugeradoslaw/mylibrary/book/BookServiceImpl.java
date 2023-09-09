package pl.klugeradoslaw.mylibrary.book;

import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.book.dto.BookDto;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public Optional<BookDto> findBookById(Long id) {
        return bookRepository.findById(id).map(BookDtoMapper::mapToDto);
    }

}
