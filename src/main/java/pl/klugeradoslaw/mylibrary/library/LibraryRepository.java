package pl.klugeradoslaw.mylibrary.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    List<Library> getLibrariesByUser_Id (long userId);

    @Transactional
    void deleteAllByUser_id(long userId);
}
