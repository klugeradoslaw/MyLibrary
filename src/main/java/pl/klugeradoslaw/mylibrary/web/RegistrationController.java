package pl.klugeradoslaw.mylibrary.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.klugeradoslaw.mylibrary.user.UserService;
import pl.klugeradoslaw.mylibrary.user.dto.UserRegistrationDto;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserRegistrationDto userRegistrationDto) {
        return userService.register(userRegistrationDto);
    }
}
