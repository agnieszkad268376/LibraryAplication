package eduib.library.controller.DTO;

import eduib.library.commonTypes.UserRole;

public class RegisterResponseDTO {

    private long id;
    private String userName;
    private UserRole role;

    public RegisterResponseDTO(long id, String userName, UserRole role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
