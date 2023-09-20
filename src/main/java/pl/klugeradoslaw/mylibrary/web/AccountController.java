package pl.klugeradoslaw.mylibrary.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.klugeradoslaw.mylibrary.user.User;
import pl.klugeradoslaw.mylibrary.user.UserService;
import pl.klugeradoslaw.mylibrary.user.dto.UserRegistrationDto;

@Slf4j
@Controller
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserRegistrationDto userRegistrationDto) {
        if (!userService.existsByEmail(userRegistrationDto.getEmail())) {
            userService.register(userRegistrationDto);
            log.info("User added to database.");
            return ResponseEntity.ok("Your account has been successfully created!");
        } else {
            return ResponseEntity.badRequest().body("E-mail is already taken.");
        }
    }
        // TO DO:
        // when deleting User, method have to delete added by this user ratings and other......

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        User userById = userService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (userById.getEmail().equals(currentUserEmail) ||
                authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            userService.deleteUser(id);
            return ResponseEntity.ok("User " + currentUserEmail + " deleted successfully.");
        } else {
            return ResponseEntity.ok("You dont have permission to delete this user!");
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestParam String email, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        User userByEmail = userService.findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (userByEmail.getEmail().equals(currentUserEmail) ||
                authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            userService.deleteUser(email);
            return ResponseEntity.ok("User " + currentUserEmail + " deleted successfully.");
        } else {
            return ResponseEntity.ok("You dont have permission to delete this user!");
        }
    }
}
