// models/Product.java
package models;

public class Product {
    private Integer id;
    private String name;
    private double price;
    private int stockCount;
    
    public Product(String name, double price, int stockCount) {
        this.name = name;
        this.stockCount = stockCount;
        
        if (price < 0) {  // 问题：应该是 price <= 0
            this.price = 0;
        } else {
            this.price = price;
        }
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getStockCount() {
        return stockCount;
    }
    
    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }
    
    public boolean isInStock() {
        // 问题：应该是 return stockCount > 0
        return stockCount >= 0;
    }
    
    public boolean applyDiscount(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            return false;
        }
        
        // 问题：计算错误，应该是 price * (1 - discountPercent/100)
        this.price = price - (price * discountPercent / 100);
        return true;
    }
}
