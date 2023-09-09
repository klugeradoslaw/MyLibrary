package pl.klugeradoslaw.mylibrary.book;

import jakarta.persistence.*;
import pl.klugeradoslaw.mylibrary.genre.Genre;
import pl.klugeradoslaw.mylibrary.rating.Rating;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;
    @OneToMany(mappedBy = "book")
    private List<Rating> ratings;
    private int isbn;

}
