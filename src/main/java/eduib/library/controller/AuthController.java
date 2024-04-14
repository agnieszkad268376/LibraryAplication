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

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO requestBody){
        RegisterResponseDTO registerResponseDTO = authService.register(requestBody);
        return new ResponseEntity<>(registerResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO requestBody){
        LoginResponseDTO dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/show")
    public List<RegisterResponseDTO> showUsers(){
        return authService.showUsers();
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/showUser/{id}")
    public RegisterResponseDTO showUser(@PathVariable long id){
        return authService.showUser(id);
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updateUserName/{userId}/{newUserName}")
    public RegisterResponseDTO updateUserName(@PathVariable long userId, @PathVariable String newUserName){
        authService.updateUserName(userId, newUserName);
        return authService.showUser(userId);
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updateEmail/{userId}/{newEmail}")
    public ResponseEntity<Void> updateEmail(@PathVariable long userId, @PathVariable String newEmail){
        authService.updateEmail(userId, newEmail);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updateUserName/{userId}/{newPassword}")
    public ResponseEntity<Void> changePassword(@PathVariable long userId, @PathVariable String newPassword){
        authService.changePassword(userId, newPassword);
        return ResponseEntity.noContent().build();
    }


}
