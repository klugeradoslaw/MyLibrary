package pl.klugeradoslaw.mylibrary.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUser_EmailAndBook_Id(String userEmail, Long bookId);
}
