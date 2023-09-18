package pl.klugeradoslaw.mylibrary.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.klugeradoslaw.mylibrary.user.UserService;
import pl.klugeradoslaw.mylibrary.user.dto.UserResponseDto;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserByEmail (@RequestParam String email) {
        return ResponseEntity.ok(userService.findUserDtoByEmail(email).orElseThrow());
    }

}
