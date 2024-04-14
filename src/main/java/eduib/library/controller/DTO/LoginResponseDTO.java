package eduib.library.controller.DTO;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It for getting response after loging
 */
public class LoginResponseDTO {
    private String token;

    /**
     * Construct LoginDTO objeect
     * @param token JWT token (String)
     */
    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
