package app.checkout.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class HelperUtil {

    private HelperUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Generate unique order number
     * Format: ORD-YYYYMMDD-XXXXXX
     */
    public static String generateOrderNumber() {
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomStr = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return String.format("ORD-%s-%s", dateStr, randomStr);
    }

    /**
     * Generate unique transaction ID
     * Format: TRX-timestamp-XXXXXXXX
     */
    public static String generateTransactionId() {
        return String.format("TRX-%d-%s",
                System.currentTimeMillis(),
                UUID.randomUUID().toString().substring(0, 8).toUpperCase()
        );
    }

    /**
     * Generate virtual account number
     * Format: VA-XXXXXXXX
     */
    public static String generateVirtualAccount() {
        return String.format("VA-%s", UUID.randomUUID().toString().substring(0, 8).toUpperCase());
    }

    /**
     * Calculate percentage
     */
    public static double calculatePercentage(double value, double total) {
        if (total == 0) {
            return 0;
        }
        return (value / total) * 100;
    }

    /**
     * Format currency to Rupiah
     */
    public static String formatCurrency(BigDecimal amount) {
        return String.format("Rp %.2f", amount);
    }

    /**
     * Calculate subtotal
     */
    public static double calculateSubtotal(int quantity, double price) {
        return quantity * price;
    }

    /**
     * Truncate string to maximum length
     */
    public static String truncate(String value, int maxLength) {
        if (value == null) {
            return null;
        }
        return value.length() > maxLength ? value.substring(0, maxLength) + "..." : value;
    }

    /**
     * Check if string is null or empty
     */
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Get current timestamp
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * Format date time
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Format date time with custom pattern
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}