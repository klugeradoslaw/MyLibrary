package pl.klugeradoslaw.mylibrary.library.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.klugeradoslaw.mylibrary.book.Book;
import pl.klugeradoslaw.mylibrary.user.User;

import java.util.List;

@Getter
@Setter
public class LibraryDto {
    private Long id;
    private User user;
    private String name;
    private List<Book> myBooks;
}
