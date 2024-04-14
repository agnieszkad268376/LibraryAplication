package eduib.library.controller.DTO;

import eduib.library.commonTypes.UserRole;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It for getting responde after adding user to database
 */
public class RegisterResponseDTO {

    private long id;
    private String userName;
    private UserRole role;

    /**
     * Construcr RegisterResponseDTO object
     * @param id user's id (long)
     * @param userName (String)
     * @param role user's role (UserRole)
     */
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
