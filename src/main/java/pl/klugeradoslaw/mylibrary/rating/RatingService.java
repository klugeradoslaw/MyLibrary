package pl.klugeradoslaw.mylibrary.rating;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.klugeradoslaw.mylibrary.book.Book;
import pl.klugeradoslaw.mylibrary.book.BookRepository;
import pl.klugeradoslaw.mylibrary.user.User;
import pl.klugeradoslaw.mylibrary.user.UserRepository;

import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void addOrUpdate(String userEmail, Long bookId, int rating) {
        Rating ratingToSaveOrUpdate = ratingRepository.findByUser_EmailAndBook_Id(userEmail, bookId).orElseGet(Rating::new);
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ratingToSaveOrUpdate.setUser(user);
        ratingToSaveOrUpdate.setBook(book);
        ratingToSaveOrUpdate.setRating(rating);
        ratingRepository.save(ratingToSaveOrUpdate);
    }

    public Optional<Integer> getUserRatingForBook (String userEmail, Long bookId) {
        return ratingRepository.findByUser_EmailAndBook_Id(userEmail, bookId)
                .map(Rating::getRating);
    }

    // when user is removed, we dont want to delete rating form this user. Instead of removing rating, change user_id for null in book_rating
    public void changeRatingUserIdForNull(long id) {
        ratingRepository.whenUserIsDeleted(id);
    }
}
