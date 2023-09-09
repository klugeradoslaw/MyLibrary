package pl.klugeradoslaw.mylibrary.book;

import pl.klugeradoslaw.mylibrary.book.dto.BookDto;
import pl.klugeradoslaw.mylibrary.rating.Rating;

public class BookDtoMapper {
    public static BookDto mapToDto(Book book) {
        double avgRating = book.getRatings().stream()
                .mapToDouble(Rating::getRating)
                .average().orElse(0);
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setGenre(book.getGenre().toString());
        bookDto.setAvgRating(avgRating);
        bookDto.setIsbn(book.getIsbn());
        return bookDto;
    }
}
