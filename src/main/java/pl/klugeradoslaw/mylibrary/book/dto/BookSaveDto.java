package pl.klugeradoslaw.mylibrary.book.dto;

import lombok.Data;

@Data
public class BookSaveDto {
    private String title;
    private String author;
    private String genre;
    private Long isbn;

}
