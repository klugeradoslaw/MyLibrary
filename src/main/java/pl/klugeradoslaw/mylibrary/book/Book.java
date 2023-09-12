package pl.klugeradoslaw.mylibrary.book;

import jakarta.persistence.*;
import lombok.Data;
import pl.klugeradoslaw.mylibrary.genre.Genre;
import pl.klugeradoslaw.mylibrary.rating.Rating;

import java.util.List;

@Entity
@Data
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
    private Long isbn;
    @OneToMany(mappedBy = "book")
    private List<Rating> ratings;
}
