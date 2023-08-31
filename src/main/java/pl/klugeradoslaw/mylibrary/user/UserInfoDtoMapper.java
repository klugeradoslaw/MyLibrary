package pl.klugeradoslaw.mylibrary.user;

import pl.klugeradoslaw.mylibrary.user.dto.UserInfoDto;
import java.util.Set;
import java.util.stream.Collectors;

public class UserInfoDtoMapper {
    static UserInfoDto map(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserInfoDto(email, password, roles);
    }
}
