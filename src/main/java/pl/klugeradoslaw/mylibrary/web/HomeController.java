package pl.klugeradoslaw.mylibrary.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {

    @GetMapping
    ResponseEntity<String> hello() {
        log.info("Message for EVERYONE.");
        return ResponseEntity.ok("HELLO, YOU CAN SEE IT ALWAYS.");
    }
}
