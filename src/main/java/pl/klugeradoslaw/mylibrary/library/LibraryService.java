package pl.klugeradoslaw.mylibrary.library;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.klugeradoslaw.mylibrary.book.BookService;
import pl.klugeradoslaw.mylibrary.book.dto.BookDto;
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
    private final UserService userService;
    private final LibraryDtoMapper libraryDtoMapper;
    private final BookService bookService;


    public LibraryService(LibraryRepository libraryRepository, UserService userService, LibraryDtoMapper libraryDtoMapper, BookService bookService) {
        this.libraryRepository = libraryRepository;
        this.userService = userService;
        this.libraryDtoMapper = libraryDtoMapper;
        this.bookService = bookService;
    }

    public LibraryDto addLibrary (String userEmail, String name) {
        LibrarySaveDto librarySaveDto = new LibrarySaveDto();
        User user = userService.findUserByEmail(userEmail).orElseThrow();
        librarySaveDto.setUser(user);
        librarySaveDto.setName(name);
        Library libraryToSave = LibraryDtoMapper.map(librarySaveDto);
        Library savedLibrary = libraryRepository.save(libraryToSave);
        return LibraryDtoMapper.map(savedLibrary);
    }

    public List<LibraryDto> getListOfLibrariesByEmail (String userEmail) {
        User userByEmail = userService.findUserByEmail(userEmail).orElseThrow();
        List<Library> librariesByUser_id = libraryRepository.getLibrariesByUser_Id(userByEmail.getId());
        List<LibraryDto> listOfLibraries = librariesByUser_id.stream().map(LibraryDtoMapper::map).toList();
        return listOfLibraries;
    }

    public void deleteLibrary (Long libraryId) {
            libraryRepository.deleteById(libraryId);
    }

    public String getOwnerByLibraryId(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow();
        return library.getUser().getEmail();
    }

    public Optional<LibraryDto> findLibraryById(Long libraryId) {
        return libraryRepository.findById(libraryId)
                .map(LibraryDtoMapper::map);
    }

    public void updateLibrary(Long libraryId, LibraryDto libraryUpdateDto) {
        Library libraryById = libraryRepository.findById(libraryId).orElseThrow();
        libraryById.setName(libraryUpdateDto.getName());
        libraryRepository.save(libraryById);
    }

    public void addBookToLibrary(Long bookId, Long libraryId) {
        BookDto bookToAddToLibrary = bookService.findBookById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LibraryDto libraryToUpdateDto = findLibraryById(libraryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<String> myBooks = libraryToUpdateDto.getMyBooks();
        String titleToAdd = bookToAddToLibrary.getTitle();
        myBooks.add(titleToAdd);
        libraryToUpdateDto.setMyBooks(myBooks);
        libraryRepository.save(libraryDtoMapper.map(libraryToUpdateDto));
    }

}
