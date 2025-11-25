package net.javaguides.tendermanagementms.dto;

public class LoginDTO {

    private String email;

    public String getEmail() {
        return email;
    }

    public LoginDTO() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
}
