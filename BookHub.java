import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main application class for BOOK HUB - Online Book Store.
 * 
 * Demonstrates:
 * - OOP Concepts: Classes, Objects, Encapsulation, Composition
 * - Data Structure: ArrayList for dynamic in-memory collections
 * - Control Flow: Loops (while, for), Conditionals (if-else, switch)
 * - Input Validation: Graceful error handling for user input
 */
public class BookHub {
    // In-memory collections
    private ArrayList<Book> books;
    private ArrayList<CartItem> cart;
    private Scanner scanner;

    // Constructor to initialize lists and load inventory
    public BookHub() {
        books = new ArrayList<Book>();
        cart = new ArrayList<CartItem>();
        scanner = new Scanner(System.in);
        loadBooks();
    }

    /**
     * Loads the store inventory into the books list.
     * Contains exactly 22 books across 5 genres.
     */
    public void loadBooks() {
        // Russian Classics (5 books)
        books.add(new Book("The Idiot", "Russian Classics", 299));
        books.add(new Book("Crime and Punishment", "Russian Classics", 349));
        books.add(new Book("The Underground Man", "Russian Classics", 249));
        books.add(new Book("The Brothers Karamazov", "Russian Classics", 399));
        books.add(new Book("White Nights", "Russian Classics", 199));

        // Dystopian (4 books)
        books.add(new Book("1984", "Dystopian", 299));
        books.add(new Book("Fahrenheit 451", "Dystopian", 279));
        books.add(new Book("Animal Farm", "Dystopian", 199));
        books.add(new Book("The Handmaid's Tale", "Dystopian", 349));

        // World Classics (4 books)
        books.add(new Book("The Iliad", "World Classics", 399));
        books.add(new Book("The Stranger", "World Classics", 249));
        books.add(new Book("Metamorphosis", "World Classics", 199));
        books.add(new Book("The Trial", "World Classics", 279));

        // Philosophy (1 book)
        books.add(new Book("Meditations", "Philosophy", 229));

        // History & Politics (8 books)
        books.add(new Book("The Anatomy of Fascism", "History & Politics", 399));
        books.add(new Book("Long Walk to Freedom", "History & Politics", 449));
        books.add(new Book("India Becomes Independent", "History & Politics", 329));
        books.add(new Book("Discovering India", "History & Politics", 349));
        books.add(new Book("The Anarchy", "History & Politics", 499));
        books.add(new Book("Inglorious Empire", "History & Politics", 399));
        books.add(new Book("Violence of Green Revolution", "History & Politics", 350));
        books.add(new Book("Biopiracy", "History & Politics", 299));
    }

    /**
     * Displays all available books in a tabular format.
     */
    public void viewBooks() {
        System.out.println("\n=======================================================================");
        System.out.printf("%-4s %-32s %-24s %-8s\n", "No.", "Book Title", "Genre", "Price");
        System.out.println("=======================================================================");
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            System.out.printf("%-4d %-32s %-24s ₹%.0f\n", (i + 1), b.getTitle(), b.getGenre(), b.getPrice());
        }
        System.out.println("=======================================================================");
    }

    /**
     * Allows browsing books filtered by a specific genre.
     */
    public void browseGenre() {
        System.out.println("\nAvailable Genres:");
        System.out.println("1. Russian Classics");
        System.out.println("2. Dystopian");
        System.out.println("3. World Classics");
        System.out.println("4. Philosophy");
        System.out.println("5. History & Politics");

        int genreChoice = readInt("\nEnter genre: ");
        String selectedGenre = "";

        switch (genreChoice) {
            case 1:
                selectedGenre = "Russian Classics";
                break;
            case 2:
                selectedGenre = "Dystopian";
                break;
            case 3:
                selectedGenre = "World Classics";
                break;
            case 4:
                selectedGenre = "Philosophy";
                break;
            case 5:
                selectedGenre = "History & Politics";
                break;
            default:
                System.out.println("Invalid genre choice! Returning to menu.");
                return;
        }

        System.out.println();
        for (Book b : books) {
            if (b.getGenre().equalsIgnoreCase(selectedGenre)) {
                System.out.println(b.getTitle());
            }
        }
    }

    /**
     * Searches for books matching a given title query (case-insensitive).
     */
    public void searchBook() {
        System.out.print("\nEnter book name: ");
        String query = scanner.nextLine().trim();

        if (query.isEmpty()) {
            System.out.println("Search term cannot be empty!");
            return;
        }

        boolean found = false;
        System.out.println("\nResult:");
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(query.toLowerCase())) {
                b.display();
                System.out.println("----------------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found matching \"" + query + "\".");
        }
    }

    /**
     * Allows customer to select a book by number and specify quantity.
     */
    public void addToCart() {
        viewBooks();

        int bookNum = readInt("\nEnter book number: ");
        if (bookNum < 1 || bookNum > books.size()) {
            System.out.println("Invalid book number! Please choose a number from 1 to " + books.size() + ".");
            return;
        }

        int quantity = readInt("Enter quantity: ");
        if (quantity <= 0) {
            System.out.println("Invalid quantity! Quantity must be greater than 0.");
            return;
        }

        Book selectedBook = books.get(bookNum - 1);

        // Check if book is already in cart; if so, update its quantity
        boolean exists = false;
        for (CartItem item : cart) {
            if (item.getBook().getTitle().equalsIgnoreCase(selectedBook.getTitle())) {
                item.addQuantity(quantity);
                exists = true;
                break;
            }
        }

        if (!exists) {
            cart.add(new CartItem(selectedBook, quantity));
        }

        System.out.println("\nSuccessfully added " + quantity + " copy/copies of \"" + selectedBook.getTitle() + "\" to your cart.");
    }

    /**
     * Displays all items currently in the cart and the total amount.
     */
    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        System.out.println("\n================ YOUR CART ================\n");
        double grandTotal = 0;
        for (CartItem item : cart) {
            System.out.printf("%-28s  x%-3d   ₹%.0f\n", item.getBook().getTitle(), item.getQuantity(), item.getTotal());
            grandTotal += item.getTotal();
        }
        System.out.println("\n-------------------------------------------");
        System.out.printf("Total: ₹%.0f\n", grandTotal);
        System.out.println("===========================================");
    }

    /**
     * Processes checkout, displays summary, and empties the cart.
     */
    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("\nCart is empty.");
            return;
        }

        viewCart();
        System.out.println("\nOrder placed successfully!");
        System.out.println("Thank you for shopping at Book Hub.");
        cart.clear();
    }

    /**
     * Helper method to read a valid integer from the user.
     * Prevents program crashing on invalid text inputs.
     */
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Main method running the menu-driven application loop.
     */
    public static void main(String[] args) {
        BookHub app = new BookHub();
        boolean running = true;

        while (running) {
            System.out.println("\n========================================");
            System.out.println("              BOOK HUB");
            System.out.println("         Online Book Store");
            System.out.println("========================================");
            System.out.println("1. View All Books");
            System.out.println("2. Browse by Genre");
            System.out.println("3. Search Book");
            System.out.println("4. Add Book to Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.println("========================================");

            int choice = app.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    app.viewBooks();
                    break;
                case 2:
                    app.browseGenre();
                    break;
                case 3:
                    app.searchBook();
                    break;
                case 4:
                    app.addToCart();
                    break;
                case 5:
                    app.viewCart();
                    break;
                case 6:
                    app.checkout();
                    break;
                case 7:
                    System.out.println("\nThank you for visiting Book Hub. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please select an option between 1 and 7.");
            }
        }
    }
}
