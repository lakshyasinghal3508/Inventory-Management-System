import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point of the program. Displays the menu, reads user input,
 * and calls the appropriate Inventory method. All input validation
 * errors are caught here and shown as friendly messages.
 */
public class Main {

    private static final String DATA_FILE = "data/products.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        FileManager fileManager = new FileManager(DATA_FILE);

        // Try to load existing data automatically when the program starts.
        try {
            List<Product> loaded = fileManager.load();
            inventory.setAllProducts(loaded);
            if (!loaded.isEmpty()) {
                System.out.println("Loaded " + loaded.size() + " product(s) from " + DATA_FILE);
            }
        } catch (IOException e) {
            System.out.println("Could not load existing data: " + e.getMessage());
        }

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1": addProduct(scanner, inventory); break;
                    case "2": displayAll(inventory); break;
                    case "3": searchProduct(scanner, inventory); break;
                    case "4": updateProduct(scanner, inventory); break;
                    case "5": deleteProduct(scanner, inventory); break;
                    case "6": addStock(scanner, inventory); break;
                    case "7": removeStock(scanner, inventory); break;
                    case "8": displayLowStock(inventory); break;
                    case "9": showTotalValue(inventory); break;
                    case "10": saveData(fileManager, inventory); break;
                    case "11": loadData(fileManager, inventory); break;
                    case "12":
                        running = false;
                        System.out.println("Exiting. Remember to save (option 10) if you made changes!");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a number from 1 to 12.");
                }
            } catch (DuplicateProductException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
        System.out.println("1.  Add a new product");
        System.out.println("2.  Display all products");
        System.out.println("3.  Search for a product");
        System.out.println("4.  Update product information");
        System.out.println("5.  Delete a product");
        System.out.println("6.  Add stock");
        System.out.println("7.  Remove/sell stock");
        System.out.println("8.  Display low-stock products");
        System.out.println("9.  Calculate total inventory value");
        System.out.println("10. Save product information");
        System.out.println("11. Load saved product information");
        System.out.println("12. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addProduct(Scanner scanner, Inventory inventory) throws DuplicateProductException {
        System.out.print("Product ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Product name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();

        double price = readDouble(scanner, "Price: ");
        int quantity = readInt(scanner, "Quantity: ");
        int minStock = readInt(scanner, "Minimum stock level: ");

        Product product = new Product(id, name, category, price, quantity, minStock);
        inventory.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private static void displayAll(Inventory inventory) {
        List<Product> all = inventory.getAllProducts();
        if (all.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }
        printProductTableHeader();
        for (Product p : all) {
            System.out.println(p);
        }
    }

    private static void searchProduct(Scanner scanner, Inventory inventory) {
        System.out.print("Enter product ID or name to search: ");
        String term = scanner.nextLine().trim();
        List<Product> results = inventory.search(term);
        if (results.isEmpty()) {
            System.out.println("No matching product found.");
            return;
        }
        printProductTableHeader();
        for (Product p : results) {
            System.out.println(p);
        }
    }

    private static void updateProduct(Scanner scanner, Inventory inventory) {
        System.out.print("Enter product ID to update: ");
        String id = scanner.nextLine().trim();
        if (inventory.findById(id) == null) {
            System.out.println("No product found with ID '" + id + "'.");
            return;
        }

        System.out.print("New name (leave blank to keep current): ");
        String name = scanner.nextLine().trim();
        System.out.print("New category (leave blank to keep current): ");
        String category = scanner.nextLine().trim();
        System.out.print("New price (leave blank to keep current): ");
        String priceInput = scanner.nextLine().trim();
        System.out.print("New minimum stock level (leave blank to keep current): ");
        String minStockInput = scanner.nextLine().trim();

        Double newPrice = null;
        Integer newMinStock = null;
        if (!priceInput.isEmpty()) newPrice = parseDoubleOrThrow(priceInput);
        if (!minStockInput.isEmpty()) newMinStock = parseIntOrThrow(minStockInput);

        inventory.updateProduct(
                id,
                name.isEmpty() ? null : name,
                category.isEmpty() ? null : category,
                newPrice,
                newMinStock
        );
        System.out.println("Product updated successfully.");
    }

    private static void deleteProduct(Scanner scanner, Inventory inventory) {
        System.out.print("Enter product ID to delete: ");
        String id = scanner.nextLine().trim();
        boolean removed = inventory.deleteProduct(id);
        System.out.println(removed ? "Product deleted." : "No product found with ID '" + id + "'.");
    }

    private static void addStock(Scanner scanner, Inventory inventory) {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine().trim();
        int amount = readInt(scanner, "Amount to add: ");
        inventory.addStock(id, amount);
        System.out.println("Stock updated.");
    }

    private static void removeStock(Scanner scanner, Inventory inventory) {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine().trim();
        int amount = readInt(scanner, "Amount to remove/sell: ");
        inventory.removeStock(id, amount);
        System.out.println("Stock updated.");
    }

    private static void displayLowStock(Inventory inventory) {
        List<Product> lowStock = inventory.getLowStockProducts();
        if (lowStock.isEmpty()) {
            System.out.println("No products are low on stock.");
            return;
        }
        printProductTableHeader();
        for (Product p : lowStock) {
            System.out.println(p);
        }
    }

    private static void showTotalValue(Inventory inventory) {
        System.out.printf("Total inventory value: $%.2f%n", inventory.getTotalInventoryValue());
    }

    private static void saveData(FileManager fileManager, Inventory inventory) throws IOException {
        fileManager.save(inventory.getAllProducts());
        System.out.println("Data saved to " + DATA_FILE);
    }

    private static void loadData(FileManager fileManager, Inventory inventory) throws IOException {
        List<Product> loaded = fileManager.load();
        inventory.setAllProducts(loaded);
        System.out.println("Loaded " + loaded.size() + " product(s) from " + DATA_FILE);
    }

    private static void printProductTableHeader() {
        System.out.printf("%-8s %-20s %-15s %-11s %-10s %-10s %s%n",
                "ID", "Name", "Category", "Price", "Quantity", "MinStock", "Status");
    }

    // ---- Input-reading helpers with validation ----

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Value cannot be negative. Try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number. Try again.");
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value < 0) {
                    System.out.println("Value cannot be negative. Try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid whole number. Try again.");
            }
        }
    }

    private static double parseDoubleOrThrow(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'" + input + "' is not a valid price.");
        }
    }

    private static int parseIntOrThrow(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'" + input + "' is not a valid whole number.");
        }
    }
}
