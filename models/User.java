// models/User.java
package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {
    private Integer id;
    private String username;
    private String password;  // 问题：密码未加密存储
    private String email;
    private boolean isActive;
    private Date createdAt;
    
    /**
     * Constructs a new User with the specified username, password, and email.
     *
     * <p>This constructor initializes the user as active and sets the creation date to the current time.</p>
     *
     * @param username the username for the new user
     * @param password the user's password (stored in plain text)
     * @param email the user's email address
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isActive = true;
        this.createdAt = new Date();
    }
    
    /**
     * Retrieves the unique identifier of the user.
     *
     * @return the user's unique identifier
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Sets the user's unique identifier.
     *
     * @param id the new unique identifier for the user.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Retrieves the username associated with this user.
     *
     * @return the user's username.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Sets the user's username.
     *
     * @param username the username to assign to the user
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Retrieves the user's password.
     *
     * <p>Note that the password is stored and returned as plain text.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Updates the user's password.
     *
     * @param password the new password to set for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Retrieves the user's email address.
     *
     * @return the email address associated with this user
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Updates the user's email address.
     *
     * @param email the new email address to assign
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Returns the active status of the user.
     *
     * @return true if the user's account is active; false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }
    
    /**
     * Sets the active status of the user.
     *
     * @param active true if the user should be active, false otherwise
     */
    public void setActive(boolean active) {
        isActive = active;
    }
    
    /**
     * Returns the date when the user was created.
     *
     * @return the creation date of the user
     */
    public Date getCreatedAt() {
        return createdAt;
    }
    
    /**
     * Sets the creation timestamp for the user.
     *
     * @param createdAt the date representing when the user was created
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    /**
     * Converts the user object's attributes to a map representation.
     *
     * <p>This method aggregates all properties of the user instance—including id, username,
     * password, email, account active status, and creation date—into a {@code Map<String, Object>}.
     * Note that the returned map includes the password in plain text, which may pose a security risk
     * if not handled appropriately.
     *
     * @return a map containing all user attributes
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        map.put("password", password);  // 问题：返回了密码
        map.put("email", email);
        map.put("isActive", isActive);
        map.put("createdAt", createdAt);
        return map;
    }
}
