package pl.klugeradoslaw.mylibrary.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {

    List<Book> findAllByTitleContainingIgnoreCase (String title);


}
