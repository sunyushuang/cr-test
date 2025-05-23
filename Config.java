import java.util.HashMap;
import java.util.Map;

public class Config {
    // 环境变量
    public static final String ENVIRONMENT = System.getenv("APP_ENV") != null 
            ? System.getenv("APP_ENV") : "development";
    
    // 数据库配置
    public static final Map<String, Object> DB_CONFIG = new HashMap<String, Object>() {{
        put("host", "localhost");
        put("port", 3306);
        put("user", "root");
        // 问题：硬编码的数据库密码
        put("password", "admin123");
        put("database", "app_db");
    }};
    
    // API配置
    public static final Map<String, Object> API_CONFIG = new HashMap<String, Object>() {{
        put("host", "0.0.0.0");  // 问题：应该限制为localhost用于开发环境
        put("port", 5000);
        put("debug", true);  // 问题：生产环境不应该开启debug模式
    }};
}
