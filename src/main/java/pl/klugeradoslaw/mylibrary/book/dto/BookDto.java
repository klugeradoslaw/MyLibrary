package pl.klugeradoslaw.mylibrary.book.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private String genre;
    private double avgRating;
    private Long isbn;
}
