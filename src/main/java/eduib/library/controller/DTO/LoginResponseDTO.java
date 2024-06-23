package eduib.library.controller.DTO;

import eduib.library.commonTypes.UserRole;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It for getting response after loging
 */
public class LoginResponseDTO {
    private String token;
    private UserRole role;

    /**
     * Construct LoginDTO objeect
     * @param token JWT token (String)
     */
    public LoginResponseDTO(String token, UserRole role) {

        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
