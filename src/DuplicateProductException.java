/**
 * Thrown when someone tries to add a product using an ID that
 * already exists in the inventory. A custom exception makes the
 * error easy to catch specifically, instead of catching a generic Exception.
 */
public class DuplicateProductException extends Exception {
    public DuplicateProductException(String message) {
        super(message);
    }
}
