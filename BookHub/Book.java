/**
 * Represents a single book in the Book Hub store.
 * 
 * Demonstrates:
 * - Encapsulation (private fields, public constructor, public getters)
 * - Object-Oriented state management
 */
public class Book {
    // Private attributes (Encapsulation)
    private String title;
    private String genre;
    private double price;

    // Constructor to initialize book attributes
    public Book(String title, String genre, double price) {
        this.title = title;
        this.genre = genre;
        this.price = price;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    // Display book details in simple format
    public void display() {
        System.out.println(title);
        System.out.println(genre);
        System.out.printf("₹%.0f\n", price);
    }
}
