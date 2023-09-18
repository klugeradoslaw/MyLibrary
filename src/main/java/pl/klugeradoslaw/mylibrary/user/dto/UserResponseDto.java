package pl.klugeradoslaw.mylibrary.user.dto;
import lombok.Getter;
import pl.klugeradoslaw.mylibrary.library.Library;
import pl.klugeradoslaw.mylibrary.library.dto.LibraryDto;

import java.util.List;

@Getter
public class UserResponseDto {
    private final long id;
    private final String email;
    private final String name;
    private final List<String> libraries;

    public UserResponseDto(long id, String email, String name, List<String> libraries) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.libraries = libraries;
    }
}
