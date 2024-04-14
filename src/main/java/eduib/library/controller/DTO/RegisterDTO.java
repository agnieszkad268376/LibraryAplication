package eduib.library.controller.DTO;

import eduib.library.commonTypes.UserRole;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It is for adding user to database
 */
public class RegisterDTO {
    private String password;
    private String userName;
    private UserRole role;
    private String email;

    /**
     * Construct RegisterDTO object
     * @param password user's password (String)
     * @param userName (String)
     * @param role user's role (UserRole)
     * @param email user's email (String)
     */
    public RegisterDTO(String password, String userName, UserRole role, String email) {
        this.password = password;
        this.userName = userName;
        this.role = role;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
