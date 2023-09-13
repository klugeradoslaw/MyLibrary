package pl.klugeradoslaw.mylibrary.library;

import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.library.dto.LibraryDto;
import org.springframework.security.core.Authentication;
import pl.klugeradoslaw.mylibrary.library.dto.LibrarySaveDto;
import pl.klugeradoslaw.mylibrary.user.User;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final LibraryDtoMapper libraryDtoMapper;


    public LibraryService(LibraryRepository libraryRepository, LibraryDtoMapper libraryDtoMapper) {
        this.libraryRepository = libraryRepository;
        this.libraryDtoMapper = libraryDtoMapper;
    }

    public LibraryDto addLibrary (LibrarySaveDto librarySaveDto) {
        Library libraryToSave = LibraryDtoMapper.map(librarySaveDto);
        Library savedLibrary = libraryRepository.save(libraryToSave);
        return LibraryDtoMapper.map(savedLibrary);

    }

}
