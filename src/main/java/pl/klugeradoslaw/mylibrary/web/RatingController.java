package pl.klugeradoslaw.mylibrary.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.klugeradoslaw.mylibrary.rating.RatingService;

@Controller
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/rating")
    public ResponseEntity<?> addBookRating (@RequestParam long bookId, @RequestParam int rating, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        ratingService.addOrUpdate(currentUserEmail, bookId, rating);
        return ResponseEntity.ok("Thanks for rating the book!");
    }

}
