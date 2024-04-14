package eduib.library.controller;


import eduib.library.controller.DTO.LoginDTO;
import eduib.library.controller.DTO.LoginResponseDTO;
import eduib.library.controller.DTO.RegisterDTO;
import eduib.library.controller.DTO.RegisterResponseDTO;
import eduib.library.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class handling Auth endpoints.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * Constructs an AuthController object.
     * @param authService  AuthService class
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    /**
     * Registers a new user.
     * @param requestBody RegisterDTO with required data.
     * @return ResponseEntity with RegisterResponseDTO and HttpStatus.CREATED.
     */
    @PostMapping("/register")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO requestBody){
        RegisterResponseDTO registerResponseDTO = authService.register(requestBody);
        return new ResponseEntity<>(registerResponseDTO, HttpStatus.CREATED);
    }

    /**
     * Logs in a user.
     * @param requestBody LoginDTO with required data
     * @return ResponseEntity with LoginResponseDTO and HttpStatus.CREATED.
     */
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO requestBody){
        LoginResponseDTO dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all users
     * @return List of users
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/show")
    public List<RegisterResponseDTO> showUsers(){
        return authService.showUsers();
    }

    /**
     * Returs user with given id
     * @param id The ID of the user to retrieve.
     * @return RegisterResponseDTO of the user.
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/showUser/{id}")
    public RegisterResponseDTO showUser(@PathVariable long id){
        return authService.showUser(id);
    }

    /**
     * Deletes a user with given id
     * @param id user's id (long)
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     */
    @PreAuthorize("permitAll()")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates the username of a user with given id
     * @param userId  user's id (long)
     * @param newUserName  new username. (String)
     * @return RegisterResponseDTO of the updated user.
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updateUserName/{userId}/{newUserName}")
    public RegisterResponseDTO updateUserName(@PathVariable long userId, @PathVariable String newUserName){
        authService.updateUserName(userId, newUserName);
        return authService.showUser(userId);
    }

    /**
     * Updates the email of a user with given id
     * @param userId user's id (long)
     * @param newEmail  new email. (String)
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updateEmail/{userId}/{newEmail}")
    public ResponseEntity<Void> updateEmail(@PathVariable long userId, @PathVariable String newEmail){
        authService.updateEmail(userId, newEmail);
        return ResponseEntity.noContent().build();
    }

    /**
     * Changes the password of a user with given id
     * @param userId user's id
     * @param newPassword  new password.
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updateUserName/{userId}/{newPassword}")
    public ResponseEntity<Void> changePassword(@PathVariable long userId, @PathVariable String newPassword){
        authService.changePassword(userId, newPassword);
        return ResponseEntity.noContent().build();
    }


}
