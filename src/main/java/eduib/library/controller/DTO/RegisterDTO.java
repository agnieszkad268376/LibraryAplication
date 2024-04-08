package eduib.library.controller.DTO;

import eduib.library.commonTypes.UserRole;

public class RegisterDTO {
    private String password;
    private String userName;
    private UserRole role;
    private String email;

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
