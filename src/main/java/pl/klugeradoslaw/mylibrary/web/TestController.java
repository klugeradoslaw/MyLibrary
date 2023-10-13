package pl.klugeradoslaw.mylibrary.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.klugeradoslaw.mylibrary.user.UserService;
import pl.klugeradoslaw.mylibrary.user.dto.UserResponseDto;

import java.util.List;

@Slf4j
@RestController
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/secured")
    public ResponseEntity<String> helloSecured() {
        log.info("Message for logged users or admins.");
        return ResponseEntity.ok("HELLO, YOU CAN SEE IT ONLY IF U ARE USER OR ADMIN.");
    }
    @GetMapping("/secured/account")
    public ResponseEntity<String> helloSecuredUser(Authentication auth) {
        return ResponseEntity.ok("Hello, user: " + auth.getName());
    }
    @GetMapping("/secured/users")
    public ResponseEntity<List<UserResponseDto>> getListOfUsers() {
        return ResponseEntity.ok(userService.getListOfUsers());
    }
}
