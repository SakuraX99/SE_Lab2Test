package fudan.se.lab2.controller.request;

import java.util.Set;

/**
 * @author LBW
 */
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String institude;
    private String region;
    private Set<String> authorities;

    public RegisterRequest() {}

    public RegisterRequest(String username, String password, String email, String institude, String region, Set<String> authorities) {
        this.username = username;
        this.password= password;
        this.email = email;
        this.institude = institude;
        this.region = region;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstitude() {
        return institude;
    }

    public void setInstitude(String institude) {
        this.institude = institude;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}

