package pl.klugeradoslaw.mylibrary.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class UserController {

    @GetMapping("/secured")
    public ResponseEntity<String> helloSecured() {
        log.info("Message for logged users or admins.");
        return ResponseEntity.ok("HELLO, YOU CAN SEE IT ONLY IF U ARE USER OR ADMIN.");
    }
}
