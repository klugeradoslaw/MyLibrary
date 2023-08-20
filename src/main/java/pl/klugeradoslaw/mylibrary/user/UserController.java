package pl.klugeradoslaw.mylibrary.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class UserController {

    @GetMapping("/")
    ResponseEntity<String> hello() {
        log.info("Message for logged user");
        return ResponseEntity.ok("HELLO, YOU SHOULD SEE THIS ONLY IF U R LOGGED USER.");
    }
}
