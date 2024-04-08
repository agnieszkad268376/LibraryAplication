package eduib.library.controller;


import eduib.library.controller.DTO.LoginDTO;
import eduib.library.controller.DTO.LoginResponseDTO;
import eduib.library.controller.DTO.RegisterDTO;
import eduib.library.controller.DTO.RegisterResponseDTO;
import eduib.library.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO requestBody){
        RegisterResponseDTO registerResponseDTO = authService.register(requestBody);
        return new ResponseEntity<>(registerResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody LoginDTO requestBody){
        LoginResponseDTO dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/show")
    public List<RegisterResponseDTO> showUsers(){
        return authService.showUsers();
    }

    @GetMapping("/showUser/{id}")
    public RegisterResponseDTO showUser(@PathVariable long id){
        return authService.showUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
