package pl.klugeradoslaw.mylibrary.library;

import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.book.Book;
import pl.klugeradoslaw.mylibrary.book.BookRepository;
import pl.klugeradoslaw.mylibrary.library.dto.LibraryDto;
import pl.klugeradoslaw.mylibrary.library.dto.LibrarySaveDto;
import pl.klugeradoslaw.mylibrary.user.User;
import pl.klugeradoslaw.mylibrary.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryDtoMapper {

    private final UserService userService;
    private final BookRepository bookRepository;


    public LibraryDtoMapper(UserService userService, BookRepository bookRepository) {
        this.userService = userService;
        this.bookRepository = bookRepository;
    }

    static LibraryDto map(Library library) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setId(library.getId());
        libraryDto.setUserEmail(library.getUser().getEmail());
        libraryDto.setName(library.getName());
        libraryDto.setMyBooks(library.getMyBooks().stream().map(Book::getTitle).collect(Collectors.toList()));
        return libraryDto;
    }

    static Library map(LibrarySaveDto librarySaveDto) {
        Library newLibrary = new Library();
        newLibrary.setName(librarySaveDto.getName());
        newLibrary.setUser(librarySaveDto.getUser());
        newLibrary.setMyBooks(new ArrayList<>());
        return newLibrary;
    }

    public Library map(LibraryDto libraryDto) {
        Library newLibrary = new Library();
        newLibrary.setId(libraryDto.getId());
        newLibrary.setName(libraryDto.getName());
        User user = userService.findUserByEmail(libraryDto.getUserEmail()).orElseThrow();
        newLibrary.setUser(user);
        List<Book> books = libraryDto.getMyBooks().stream().map(bookRepository::findBookByTitle).toList();
        newLibrary.setMyBooks(books);
        return newLibrary;
    }

}
