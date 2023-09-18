package pl.klugeradoslaw.mylibrary.library;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.library.dto.LibraryDto;
import pl.klugeradoslaw.mylibrary.library.dto.LibrarySaveDto;
import pl.klugeradoslaw.mylibrary.user.User;
import pl.klugeradoslaw.mylibrary.user.UserService;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    public List<LibraryDto> getListOfLibrariesByEmail (String userEmail) {
        User userByEmail = userService.findUserByEmail(userEmail).orElseThrow();
        List<Library> librariesByUser_id = libraryRepository.getLibrariesByUser_Id(userByEmail.getId());
        List<LibraryDto> listOfLibraries = librariesByUser_id.stream().map(libraryDtoMapper::map).toList();
        return listOfLibraries;
    }

    public void deleteLibrary (Long libraryId) {
            libraryRepository.deleteById(libraryId);
    }

    public String getOwnerByLibraryId(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow();
        return library.getUser().getEmail();
    }
}
