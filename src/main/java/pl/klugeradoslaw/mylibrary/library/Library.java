package pl.klugeradoslaw.mylibrary.library;

import jakarta.persistence.*;
import pl.klugeradoslaw.mylibrary.book.Book;
import pl.klugeradoslaw.mylibrary.user.User;

import java.util.List;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "library",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id")
    )
    private List<Book> myLibrary;
}
