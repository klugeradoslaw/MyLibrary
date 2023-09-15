package pl.klugeradoslaw.mylibrary.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.klugeradoslaw.mylibrary.book.Book;
import pl.klugeradoslaw.mylibrary.user.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class LibraryDto {
    private Long id;
    @JsonIgnore
    private User user;
    private String name;
    private List<Book> myBooks;
}
