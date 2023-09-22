package pl.klugeradoslaw.mylibrary.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.klugeradoslaw.mylibrary.library.Library;
import pl.klugeradoslaw.mylibrary.user.dto.UserAccountDto;
import pl.klugeradoslaw.mylibrary.user.dto.UserInfoDto;
import pl.klugeradoslaw.mylibrary.user.dto.UserResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDtoMapper {
    static UserInfoDto mapToUserInfoDto(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        List<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toList());
        return new UserInfoDto(email, password, roles);
    }
    static UserResponseDto mapToUserResponseDto(User user) {
        long id = user.getId();
        String email = user.getEmail();
        String name = user.getName();
        List<String> libraries = user.getLibraries().stream().map(Library::getName).collect(Collectors.toList());
        return new UserResponseDto(id, email, name, libraries);
    }
    static UserAccountDto mapToUserAccountDto(User user) {
        long id = user.getId();
        String email = user.getEmail();
        String name = user.getName();
        String password = user.getPassword();
        return new UserAccountDto(id, email, name, password);
    }
}
