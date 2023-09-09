package pl.klugeradoslaw.mylibrary.book;

import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.book.dto.BookDto;

import java.util.Optional;

@Service
public interface BookService {
    Optional<BookDto> findBookById(Long id);


}
