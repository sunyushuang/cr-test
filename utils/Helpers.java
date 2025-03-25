package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

public class Helpers {
    /**
     * Returns the current timestamp in seconds since the Unix epoch.
     *
     * @return the current epoch time in seconds
     */
    public static long getCurrentTimestamp() {
        return Instant.now().getEpochSecond();
    }
    
    /**
     * Logs an event message with the current timestamp.
     *
     * <p>This method obtains the current epoch timestamp, prefixes the event type and message with it,
     * and appends the resulting entry to the log file located at "C:/logs/app.log".</p>
     *
     * @param eventType the category of the event (e.g., "INFO", "ERROR")
     * @param message a descriptive message for the event
     */
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
    
    /**
     * Validates an email address using a simple check.
     *
     * <p>This method verifies that the provided email string is not null and contains both '@'
     * and '.' characters. Note that this basic validation may not cover all edge cases defined by RFC standards.</p>
     *
     * @param email the email address to validate
     * @return true if the email address contains both '@' and '.', false otherwise
     */
    public static boolean validateEmail(String email) {
        // 问题：这是一个过于简单的邮箱验证
        return email != null && email.contains("@") && email.contains(".");
    }
}
