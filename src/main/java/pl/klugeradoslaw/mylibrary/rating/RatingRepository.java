package pl.klugeradoslaw.mylibrary.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUser_EmailAndBook_Id(String userEmail, Long bookId);

    @Query(value = "UPDATE book_rating SET user_id = null WHERE user_id = :id ;", nativeQuery = true)
    @Modifying
    @Transactional
    void whenUserIsDeleted (@Param("id") long id);

}
