package pl.klugeradoslaw.mylibrary.user;

import pl.klugeradoslaw.mylibrary.user.dto.UserInfoDto;
import pl.klugeradoslaw.mylibrary.user.dto.UserResponseDto;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDtoMapper {
    static UserInfoDto mapToUserInfoDto(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserInfoDto(email, password, roles);
    }
    static UserResponseDto mapToUserResponseDto(User user) {
        long id = user.getId();
        String email = user.getEmail();
        String name = user.getName();
        return new UserResponseDto(id, email, name);
    }
}
