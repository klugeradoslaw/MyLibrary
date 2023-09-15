package pl.klugeradoslaw.mylibrary.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.klugeradoslaw.mylibrary.user.User;
import pl.klugeradoslaw.mylibrary.user.UserService;
import pl.klugeradoslaw.mylibrary.user.dto.UserResponseDto;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
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
    @GetMapping("/admin/user")
    public ResponseEntity<User> getUserByEmail (@RequestParam String email){
        return ResponseEntity.ok(userService.findUserByEmail(email).orElseThrow());
    }
}
