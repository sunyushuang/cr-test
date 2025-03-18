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
    
    public static Product addProduct(String name, double price, int stockCount) {
        Product product = new Product(name, price, stockCount);
        int id = productsDb.size() + 1;
        product.setId(id);
        productsDb.put(id, product);
        return product;
    }
    
    public static List<Product> getProducts() {
        return new ArrayList<>(productsDb.values());
    }
    
    public static Product getProductById(int productId) {
        return productsDb.get(productId);
    }
    
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
