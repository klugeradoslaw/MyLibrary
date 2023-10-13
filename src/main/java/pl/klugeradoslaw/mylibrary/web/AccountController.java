package pl.klugeradoslaw.mylibrary.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.klugeradoslaw.mylibrary.user.User;
import pl.klugeradoslaw.mylibrary.user.UserService;
import pl.klugeradoslaw.mylibrary.user.dto.UserAccountDto;

import java.util.NoSuchElementException;

@Slf4j
@RestController
public class AccountController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    public AccountController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserAccountDto userRegistrationDto) {
        if (!userService.existsByEmail(userRegistrationDto.getEmail())) {
            userService.register(userRegistrationDto);
            log.info("User added to database.");
            return ResponseEntity.ok("Your account has been successfully created!");
        } else {
            return ResponseEntity.badRequest().body("E-mail is already taken.");
        }
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody JsonMergePatch patch, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        User userById = userService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (userById.getEmail().equals(currentUserEmail) ||
                authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            try {
                UserAccountDto userAccountDto = userService.findUserAccountDtoById(id).orElseThrow();
                UserAccountDto userPatched = applyPatch(userAccountDto, patch);
                userService.updateUser(userPatched);
                log.info("Updated User with id ={}", id);
                return ResponseEntity.ok(userService.findUserAccountDtoById(id));
            } catch (JsonPatchException | JsonProcessingException e) {
                return ResponseEntity.internalServerError().build();
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.ok("You dont have permission to delete this user!");
        }
    }

    private UserAccountDto applyPatch(UserAccountDto userAccountDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode userNode = objectMapper.valueToTree(userAccountDto);
        JsonNode userPatchedNode = patch.apply(userNode);
        return objectMapper.treeToValue(userPatchedNode, UserAccountDto.class);
    }


    // TO DO:
    // when deleting User, method have to delete added by this user ratings and other......
    // DONE

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        String emailUserToDelete = userService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getEmail();
        User userById = userService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (userById.getEmail().equals(currentUserEmail) ||
                authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            userService.deleteUser(id);
            log.info("Deleted user with id={}", id);
            return ResponseEntity.ok("User " + emailUserToDelete + " deleted successfully.");
        } else {
            return ResponseEntity.ok("You dont have permission to delete this user!");
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestParam String email, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        User userByEmail = userService.findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (userByEmail.getEmail().equals(currentUserEmail) ||
                authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            userService.deleteUser(email);
            log.info("Deleted user with email={}", email);
            return ResponseEntity.ok("User " + currentUserEmail + " deleted successfully.");
        } else {
            return ResponseEntity.ok("You dont have permission to delete this user!");
        }
    }
}
