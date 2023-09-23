package pl.klugeradoslaw.mylibrary.library;

import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.book.Book;
import pl.klugeradoslaw.mylibrary.library.dto.LibraryDto;
import pl.klugeradoslaw.mylibrary.library.dto.LibrarySaveDto;
import pl.klugeradoslaw.mylibrary.user.UserService;

import java.util.ArrayList;

@Service
public class LibraryDtoMapper {

    private final UserService userService;

    public LibraryDtoMapper(UserService userService) {
        this.userService = userService;
    }

    static LibraryDto map(Library library) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setId(library.getId());
        libraryDto.setUserEmail(library.getUser().getEmail());
        libraryDto.setName(library.getName());
        libraryDto.setMyBooks(library.getMyBooks().stream().map(Book::getTitle).toList());
        return libraryDto;
    }

    static Library map(LibrarySaveDto librarySaveDto) {
        Library newLibrary = new Library();
        newLibrary.setName(librarySaveDto.getName());
        newLibrary.setUser(librarySaveDto.getUser());
        newLibrary.setMyBooks(new ArrayList<>());
        return newLibrary;
    }
}
