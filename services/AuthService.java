package services;

import models.User;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    // 模拟数据库
    private static Map<String, User> usersDb = new HashMap<>();
    
    /**
     * Registers a new user with the provided credentials.
     * 
     * <p>This method creates a new User object using the specified username, password, and email. It assigns the user
     * a unique ID based on the current number of registered users and adds the user to an in-memory database.</p>
     * 
     * <p><strong>Note:</strong> Duplicate username checks are not performed.</p>
     * 
     * @param username the username for the new user
     * @param password the user's password
     * @param email the user's email address
     * @return the newly created User object
     */
    public static User registerUser(String username, String password, String email) {
        // 问题：没有检查用户名是否已存在
        User user = new User(username, password, email);
        user.setId(usersDb.size() + 1);
        usersDb.put(username, user);
        return user;
    }
    
    /**
     * Authenticates a user by verifying the provided username and password.
     * 
     * <p>This method checks if the given username exists in the simulated user database. If so,
     * it compares the stored password to the provided one using a direct equality check. If the
     * credentials match, the corresponding User object is returned; otherwise, null is returned.</p>
     *
     * @param username the username of the user attempting to authenticate
     * @param password the plain text password for authentication
     * @return the authenticated User if the credentials match; null if authentication fails
     */
    public static User authenticateUser(String username, String password) {
        if (usersDb.containsKey(username)) {
            User user = usersDb.get(username);
            // 问题：直接比较明文密码
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
