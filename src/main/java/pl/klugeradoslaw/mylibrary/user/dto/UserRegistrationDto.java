package pl.klugeradoslaw.mylibrary.user.dto;
import lombok.Data;

@Data
public class UserRegistrationDto {
    private String email;
    private String name;
    private String password;

    public UserRegistrationDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public UserRegistrationDto() {
    }

}


