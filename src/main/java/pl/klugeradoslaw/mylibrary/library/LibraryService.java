package pl.klugeradoslaw.mylibrary.library;

import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.library.dto.LibraryDto;
import pl.klugeradoslaw.mylibrary.library.dto.LibrarySaveDto;
import pl.klugeradoslaw.mylibrary.user.User;
import pl.klugeradoslaw.mylibrary.user.UserService;

import java.util.Optional;


@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final LibraryDtoMapper libraryDtoMapper;
    private final UserService userService;


    public LibraryService(LibraryRepository libraryRepository, LibraryDtoMapper libraryDtoMapper, UserService userService) {
        this.libraryRepository = libraryRepository;
        this.libraryDtoMapper = libraryDtoMapper;
        this.userService = userService;
    }

    public LibraryDto addLibrary (String userEmail, String name) {
        LibrarySaveDto librarySaveDto = new LibrarySaveDto();
        User user = userService.findUserByEmail(userEmail).orElseThrow();
        librarySaveDto.setUser(user);
        librarySaveDto.setName(name);
        Library libraryToSave = libraryDtoMapper.map(librarySaveDto);
        Library savedLibrary = libraryRepository.save(libraryToSave);
        return libraryDtoMapper.map(savedLibrary);
    }
}
