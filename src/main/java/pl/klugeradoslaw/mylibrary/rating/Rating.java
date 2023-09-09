package pl.klugeradoslaw.mylibrary.rating;

import jakarta.persistence.*;
import lombok.Data;
import pl.klugeradoslaw.mylibrary.book.Book;
import pl.klugeradoslaw.mylibrary.user.User;

@Entity
@Data
@Table(name = "book_rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private int rating;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
