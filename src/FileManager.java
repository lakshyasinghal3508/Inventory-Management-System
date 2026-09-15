import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and writing the product list to a text file.
 * This is the only class that knows about the file's location or format;
 * Inventory and Main never open files directly.
 */
public class FileManager {

    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    /** Writes every product to the file, one per line, overwriting old contents. */
    public void save(List<Product> products) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Product p : products) {
                writer.write(p.toFileLine());
                writer.write(System.lineSeparator());
            }
        }
    }

    /**
     * Reads the file and rebuilds the product list.
     * If the file does not exist yet (first run), returns an empty list
     * instead of throwing an error.
     */
    public List<Product> load() throws IOException {
        List<Product> products = new ArrayList<>();
        java.io.File file = new java.io.File(filePath);
        if (!file.exists()) {
            return products;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;
                try {
                    products.add(Product.fromFileLine(line));
                } catch (Exception e) {
                    // Skip a corrupted line instead of crashing the whole load.
                    System.out.println("Warning: skipped invalid data on line " + lineNumber + ".");
                }
            }
        }
        return products;
    }
}
