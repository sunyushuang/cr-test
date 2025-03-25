// services/ProductService.java
package services;

import models.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    // 模拟数据库
    private static Map<Integer, Product> productsDb = new HashMap<>();
    
    /**
     * Creates a new Product with the specified name, price, and stock count, assigns it a unique sequential ID, and adds it to the product database.
     *
     * @param name the name of the product
     * @param price the price of the product
     * @param stockCount the initial stock count for the product
     * @return the newly created Product instance
     */
    public static Product addProduct(String name, double price, int stockCount) {
        Product product = new Product(name, price, stockCount);
        int id = productsDb.size() + 1;
        product.setId(id);
        productsDb.put(id, product);
        return product;
    }
    
    /**
     * Retrieves a list of all products stored in the simulated database.
     *
     * @return a new list containing all Product objects.
     */
    public static List<Product> getProducts() {
        return new ArrayList<>(productsDb.values());
    }
    
    /**
     * Retrieves a product from the simulated database by its unique identifier.
     *
     * @param productId the unique identifier of the product to retrieve
     * @return the product associated with the provided identifier, or {@code null} if no such product exists
     */
    public static Product getProductById(int productId) {
        return productsDb.get(productId);
    }
    
    /**
     * Updates the stock count for the product identified by the specified ID.
     *
     * <p>If a product with the given ID is found, its stock count is updated to the new value.
     * Note that the method does not validate whether the new stock value is non-negative.
     *
     * @param productId the unique identifier of the product to update
     * @param newStock the new stock count to be set
     * @return true if the product exists and its stock count was updated; false otherwise
     */
    public static boolean updateProductStock(int productId, int newStock) {
        Product product = getProductById(productId);
        if (product != null) {
            // 问题：没有验证 newStock 是否为非负数
            product.setStockCount(newStock);
            return true;
        }
        return false;
    }
}
