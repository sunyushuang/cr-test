package services;

import models.User;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    // 模拟数据库
    private static Map<String, User> usersDb = new HashMap<>();
    
    public static User registeruser(String username, String password, String email) {
        // 问题：没有检查用户名是否已存在
        User user = new User(username, password, email);
        user.setId(usersDb.size() + 1);
        usersDb.put(username, user);
        return user;
    }
    
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
