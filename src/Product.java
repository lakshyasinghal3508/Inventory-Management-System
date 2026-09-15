/**
 * Represents a single product in the inventory.
 * This class only stores data and enforces basic rules about that data
 * (encapsulation) - it does not know about menus, files, or other products.
 */
public class Product {

    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private int minStockLevel;

    public Product(String id, String name, String category, double price, int quantity, int minStockLevel) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (minStockLevel < 0) {
            throw new IllegalArgumentException("Minimum stock level cannot be negative.");
        }

        this.id = id.trim();
        this.name = name.trim();
        this.category = (category == null || category.trim().isEmpty()) ? "Uncategorized" : category.trim();
        this.price = price;
        this.quantity = quantity;
        this.minStockLevel = minStockLevel;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getMinStockLevel() { return minStockLevel; }

    // Setters (with the same validation as the constructor)
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        this.name = name.trim();
    }

    public void setCategory(String category) {
        this.category = (category == null || category.trim().isEmpty()) ? "Uncategorized" : category.trim();
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    public void setMinStockLevel(int minStockLevel) {
        if (minStockLevel < 0) {
            throw new IllegalArgumentException("Minimum stock level cannot be negative.");
        }
        this.minStockLevel = minStockLevel;
    }

    /** Total value of this product's current stock (price x quantity). */
    public double getStockValue() {
        return price * quantity;
    }

    /** True if the current quantity has fallen to or below the minimum stock level. */
    public boolean isLowStock() {
        return quantity <= minStockLevel;
    }

    /**
     * Converts this product into a single line of text for file storage.
     * Format: id,name,category,price,quantity,minStockLevel
     */
    public String toFileLine() {
        return id + "," + name + "," + category + "," + price + "," + quantity + "," + minStockLevel;
    }

    /**
     * Rebuilds a Product from a line previously created by toFileLine().
     * Throws an exception if the line is not in the expected format.
     */
    public static Product fromFileLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Corrupted data line: " + line);
        }
        String id = parts[0];
        String name = parts[1];
        String category = parts[2];
        double price = Double.parseDouble(parts[3]);
        int quantity = Integer.parseInt(parts[4]);
        int minStockLevel = Integer.parseInt(parts[5]);
        return new Product(id, name, category, price, quantity, minStockLevel);
    }

    @Override
    public String toString() {
        return String.format("%-8s %-20s %-15s $%-10.2f %-10d %-10d %s",
                id, name, category, price, quantity, minStockLevel,
                isLowStock() ? "LOW STOCK" : "");
    }
}
