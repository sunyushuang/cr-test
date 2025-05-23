package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

public class Helpers {
    public static long getCurrentTimestamp() {
        return Instant.now().getEpochSecond();
    }
    
    public static void logEvent(String eventType, String message) {
        long timestamp = getCurrentTimestamp();
        // 问题：日志路径使用硬编码
        String logFile = "C:/logs/app.log";
        
        try {
            // 问题：没有处理文件不存在的情况
            FileWriter writer = new FileWriter(logFile, true);
            writer.write(timestamp + " - " + eventType + ": " + message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean validateEmail(String email) {
        // 问题：这是一个过于简单的邮箱验证
        return email != null && email.contains("@") && email.contains(".");
    }
}
