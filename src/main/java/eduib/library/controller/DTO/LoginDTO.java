package eduib.library.controller.DTO;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It for Login to check login and password
 */
public class LoginDTO {
    private String userName;
    private String password;

    /**
     * Construct LoginDTO object
     * @param userName
     * @param password
     */
    public LoginDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
