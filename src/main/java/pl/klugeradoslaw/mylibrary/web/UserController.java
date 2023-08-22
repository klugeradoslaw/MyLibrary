package pl.klugeradoslaw.mylibrary.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class UserController {

    @GetMapping("/home")
    ResponseEntity<String> hello() {
        log.info("Message for EVERYONE.");
        return ResponseEntity.ok("HELLO, YOU CAN SEE IT ALWAYS.");
    }

    @GetMapping("/secured")
    ResponseEntity<String> helloSecured() {
        log.info("Message for logged users or admins.");
        return ResponseEntity.ok("HELLO, YOU CAN SEE IT ONLY IF U ARE USER OR ADMIN.");
    }
}
