package eduib.library.controller;

import eduib.library.controller.DTO.GetUserDTO;
import eduib.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Printable;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUserMe")
    // principal wyciąga konkretne dane z tokena
    public ResponseEntity<GetUserDTO> getUserMe(Principal principal){
        String userName = principal.getName();
        GetUserDTO userDTO = userService.getUserByUserName(userName);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
