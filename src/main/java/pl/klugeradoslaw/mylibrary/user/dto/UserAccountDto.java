package pl.klugeradoslaw.mylibrary.user.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
    private Long id;
    private String email;
    private String name;
    private String password;

}


