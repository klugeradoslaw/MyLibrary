package pl.klugeradoslaw.mylibrary.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class LibraryDto {
    private Long id;
    private String userEmail;
    private String name;
    private List<String> myBooks;
}
