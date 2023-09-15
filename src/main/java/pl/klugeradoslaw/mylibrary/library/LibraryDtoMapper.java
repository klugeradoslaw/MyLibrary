package pl.klugeradoslaw.mylibrary.library;

import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.library.dto.LibraryDto;
import pl.klugeradoslaw.mylibrary.library.dto.LibrarySaveDto;
import pl.klugeradoslaw.mylibrary.user.User;
import pl.klugeradoslaw.mylibrary.user.UserService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryDtoMapper {

    private final UserService userService;

    public LibraryDtoMapper(UserService userService) {
        this.userService = userService;
    }

    public LibraryDto map(Library library) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setId(library.getId());
        libraryDto.setUser(library.getUser());
        libraryDto.setName(library.getName());
        libraryDto.setMyBooks(library.getMyBooks());
        return libraryDto;
    }

    public Library map(LibrarySaveDto librarySaveDto) {
        Library newLibrary = new Library();
        newLibrary.setName(librarySaveDto.getName());
        newLibrary.setUser(librarySaveDto.getUser());
        newLibrary.setMyBooks(new ArrayList<>());
        return newLibrary;
    }
}
