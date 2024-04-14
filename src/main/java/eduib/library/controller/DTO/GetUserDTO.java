package eduib.library.controller.DTO;

/**
 * Class represents a Data Transfer Object used for transferring data from database to java object.
 * It for getting user from database
 */
public class GetUserDTO {
    private long id;
    private String userName;
    private String email;

    /**
     * Contruct a GetUserDTO object
     * @param id user id (long)
     * @param userName (String)
     * @param email users email (String)
     */
    public GetUserDTO(long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
