package pl.klugeradoslaw.mylibrary.user.dto;

import lombok.Getter;
@Getter
public class UserResponseDto {
    private final long id;
    private final String email;
    private final String name;

    public UserResponseDto(long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
