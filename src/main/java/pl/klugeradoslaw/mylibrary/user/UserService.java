package pl.klugeradoslaw.mylibrary.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.klugeradoslaw.mylibrary.user.dto.UserInfoDto;
import pl.klugeradoslaw.mylibrary.user.dto.UserRegistrationDto;
import pl.klugeradoslaw.mylibrary.user.dto.UserResponseDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_AUTHORITY = "ROLE_ADMIN";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserInfoDto> findUserInfoByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserDtoMapper::mapToUserInfoDto);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserResponseDto> findUserDtoByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserDtoMapper::mapToUserResponseDto);
    }

    public Optional<UserResponseDto> findUserDtoById(Long id) {
        return userRepository.findById(id)
                .map(UserDtoMapper::mapToUserResponseDto);
    }

    @Transactional
    public void register(UserRegistrationDto userRegistrationDto) {
            User user = new User();
            user.setEmail(userRegistrationDto.getEmail());
            user.setName(userRegistrationDto.getName());
            user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword())); //{bcrypt}
            UserRole userRole = userRoleRepository.findByName(USER_ROLE)
                    .orElseThrow(() -> new NoSuchElementException("ERROR: Role is not found.)"));
            user.getRoles().add(userRole);
            userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public void deleteUser(String email) {
        userRepository.delete(findUserByEmail(email).orElseThrow(() -> new NoSuchElementException("ERROR: User is not found.)")));
    }

    public List<UserResponseDto> getListOfUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(UserDtoMapper::mapToUserResponseDto).collect(Collectors.toList());
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}