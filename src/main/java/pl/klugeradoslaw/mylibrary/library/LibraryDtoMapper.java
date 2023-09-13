package pl.klugeradoslaw.mylibrary.library;

import pl.klugeradoslaw.mylibrary.library.dto.LibraryDto;
import pl.klugeradoslaw.mylibrary.library.dto.LibrarySaveDto;

import java.util.ArrayList;

public class LibraryDtoMapper {
    static LibraryDto map(Library library) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setId(library.getId());
        libraryDto.setUser(library.getUser());
        libraryDto.setName(library.getName());
        libraryDto.setMyBooks(library.getMyBooks());
        return libraryDto;
    }

    static Library map(LibrarySaveDto librarySaveDto) {
        Library newLibrary = new Library();
        newLibrary.setName(librarySaveDto.getName());
        newLibrary.setMyBooks(new ArrayList<>());
        return newLibrary;
    }
}
