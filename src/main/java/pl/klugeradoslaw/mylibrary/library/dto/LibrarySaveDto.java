package pl.klugeradoslaw.mylibrary.library.dto;

import lombok.Getter;
import lombok.Setter;
import pl.klugeradoslaw.mylibrary.user.User;


@Getter
@Setter
public class LibrarySaveDto {
    private String name;
    private User user;
}
