package pl.klugeradoslaw.mylibrary.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.klugeradoslaw.mylibrary.user.UserService;
import pl.klugeradoslaw.mylibrary.user.dto.UserInfoDto;

@Slf4j
@RestController
@RequestMapping("/admin")
public class UserAdminController {

    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserInfoDto> getUserByEmail (@RequestParam String email){
        return ResponseEntity.ok(userService.findUserInfoByEmail(email).orElseThrow());
    }
}
