package pl.klugeradoslaw.mylibrary.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.klugeradoslaw.mylibrary.user.UserService;
import pl.klugeradoslaw.mylibrary.user.dto.UserRegistrationDto;

@Slf4j
@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
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
}
