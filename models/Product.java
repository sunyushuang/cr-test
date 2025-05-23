// models/Product.java
package models;

public class Product {
    private Integer id;
    private String name;
    private double price;
    private int stockCount;
    
    /**
     * Constructs a new Product with the specified name, price, and stock count.
     * <p>
     * If the provided price is negative, the product's price is set to 0.
     * </p>
     *
     * @param name the product name
     * @param price the product price; if a negative value is provided, it defaults to 0
     * @param stockCount the quantity of the product in stock
     */
    public Product(String name, double price, int stockCount) {
        this.name = name;
        this.stockCount = stockCount;
        
        if (price < 0) {  // 问题：应该是 price <= 0
            this.price = 0;
        } else {
            this.price = price;
        }
    }
    
    /**
     * Returns the product's identifier.
     *
     * @return the product id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Sets the product's unique identifier.
     *
     * @param id the unique identifier to assign to the product
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Returns the name of the product.
     *
     * @return the product's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Updates the product's name.
     *
     * @param name the new name for the product
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the current price of the product.
     *
     * @return the product's price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Updates the product's price.
     *
     * <p>This method directly assigns the provided value to the product's price without any validation.
     *
     * @param price the new price value for the product
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Retrieves the current stock count of the product.
     *
     * @return the number of items currently in stock
     */
    public int getStockCount() {
        return stockCount;
    }
    
    /**
     * Sets the available stock count for the product.
     *
     * @param stockCount the new number of items available in stock
     */
    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }
    
    /**
     * Determines whether the product is in stock.
     * <p>
     * A product is considered in stock if its stock count is zero or greater.
     *
     * @return {@code true} if the product's stock count is non-negative, indicating availability.
     */
    public boolean isInStock() {
        // 问题：应该是 return stockCount > 0
        return stockCount >= 0;
    }
    
    /**
     * Applies a discount to the product's price.
     *
     * <p>The discount is applied only if the provided percentage is between 0 and 100 (inclusive). When valid,
     * the product's price is reduced by the corresponding percentage. If the discount percentage is out of range,
     * no change is made to the price and the method returns {@code false}.
     *
     * @param discountPercent the discount percentage to apply (0 to 100 inclusive)
     * @return {@code true} if the discount was applied; {@code false} otherwise
     */
    public boolean applyDiscount(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            return false;
        }
        
        // 问题：计算错误，应该是 price * (1 - discountPercent/100)
        this.price = price - (price * discountPercent / 100);
        return true;
    }
}
