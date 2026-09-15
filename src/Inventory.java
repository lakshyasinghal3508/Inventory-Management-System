import java.util.ArrayList;
import java.util.List;

/**
 * Holds the collection of products and implements every inventory
 * operation: add, search, update, delete, stock changes, reports.
 * Main.java is only responsible for talking to the user; all the
 * real logic and validation lives here.
 */
public class Inventory {

    private List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    /** Adds a new product. Rejects duplicate IDs. */
    public void addProduct(Product product) throws DuplicateProductException {
        if (findById(product.getId()) != null) {
            throw new DuplicateProductException("A product with ID '" + product.getId() + "' already exists.");
        }
        products.add(product);
    }

    /** Returns the product with this exact ID, or null if not found. */
    public Product findById(String id) {
        for (Product p : products) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Searches by ID first; if that fails, searches by name (partial,
     * case-insensitive match) and returns all matches.
     */
    public List<Product> search(String term) {
        List<Product> results = new ArrayList<>();
        Product exact = findById(term);
        if (exact != null) {
            results.add(exact);
            return results;
        }
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(term.toLowerCase())) {
                results.add(p);
            }
        }
        return results;
    }

    /** Returns all products (used for displaying the full inventory). */
    public List<Product> getAllProducts() {
        return products;
    }

    /** Updates the editable fields of a product identified by ID. */
    public void updateProduct(String id, String newName, String newCategory,
                               Double newPrice, Integer newMinStockLevel) {
        Product p = findById(id);
        if (p == null) {
            throw new IllegalArgumentException("No product found with ID '" + id + "'.");
        }
        if (newName != null) p.setName(newName);
        if (newCategory != null) p.setCategory(newCategory);
        if (newPrice != null) p.setPrice(newPrice);
        if (newMinStockLevel != null) p.setMinStockLevel(newMinStockLevel);
    }

    /** Deletes a product by ID. Returns true if something was removed. */
    public boolean deleteProduct(String id) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        products.remove(p);
        return true;
    }

    /** Increases stock for an existing product. */
    public void addStock(String id, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to add must be greater than zero.");
        }
        Product p = findById(id);
        if (p == null) {
            throw new IllegalArgumentException("No product found with ID '" + id + "'.");
        }
        p.setQuantity(p.getQuantity() + amount);
    }

    /** Decreases stock (a sale). Rejects selling more than what's in stock. */
    public void removeStock(String id, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to remove must be greater than zero.");
        }
        Product p = findById(id);
        if (p == null) {
            throw new IllegalArgumentException("No product found with ID '" + id + "'.");
        }
        if (amount > p.getQuantity()) {
            throw new IllegalArgumentException(
                    "Cannot remove " + amount + " units - only " + p.getQuantity() + " in stock.");
        }
        p.setQuantity(p.getQuantity() - amount);
    }

    /** Returns all products at or below their minimum stock level. */
    public List<Product> getLowStockProducts() {
        List<Product> lowStock = new ArrayList<>();
        for (Product p : products) {
            if (p.isLowStock()) {
                lowStock.add(p);
            }
        }
        return lowStock;
    }

    /** Total value of the entire inventory (sum of price x quantity for every product). */
    public double getTotalInventoryValue() {
        double total = 0;
        for (Product p : products) {
            total += p.getStockValue();
        }
        return total;
    }

    /** Replaces the current product list - used when loading from file. */
    public void setAllProducts(List<Product> newProducts) {
        this.products = newProducts;
    }
}
