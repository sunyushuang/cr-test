// Main.java
import models.User;
import models.Product;
import services.AuthService;
import services.ProductService;

public class Main {
    /**
     * Application entry point.
     * 
     * <p>Initializes the application by displaying the current environment configuration, registering and authenticating a new user, adding a product, and listing available products.</p>
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("应用启动，当前环境: " + Config.ENVIRONMENT);
        
        // 注册新用户
        User newUser = AuthService.registerUser("user1", "password", "user1@example.com");
        System.out.println("注册用户: " + newUser.getUsername());
        
        // 认证用户
        User user = AuthService.authenticateUser("user1", "password");
        if (user != null) {
            System.out.println("认证成功: " + user.getUsername());
        }
        
        // 添加产品
        Product product = ProductService.addProduct("手机", 3999.99, 10);
        System.out.println("添加产品: " + product.getName() + ", 价格: " + product.getPrice());
        
        // 获取产品列表
        java.util.List<Product> products = ProductService.getProducts();
        System.out.println("产品数量: " + products.size());
    }
}
