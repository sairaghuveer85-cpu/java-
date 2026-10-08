/**
 * Represents a book selected by the customer in the shopping cart.
 * 
 * Demonstrates:
 * - Composition (CartItem has-a Book reference)
 * - Encapsulation (private fields, getters, calculation method)
 */
public class CartItem {
    // Composition: CartItem contains a reference to a Book object
    private Book book;
    private int quantity;

    // Constructor to initialize cart item
    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    // Getter for the Book object
    public Book getBook() {
        return book;
    }

    // Getter for the quantity
    public int getQuantity() {
        return quantity;
    }

    // Method to add more quantity to this cart item
    public void addQuantity(int additionalQuantity) {
        this.quantity += additionalQuantity;
    }

    // Calculates the total price for this item: price * quantity
    public double getTotal() {
        return book.getPrice() * quantity;
    }
}
