import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * A Library contains a collection of books where each book title is associated with its availability status.
 * The Library allows adding, removing, checking out, and returning books, as well as checking book availability.
 */
public class Library extends Building {

    private Hashtable<String, Boolean> collection;
    private boolean hasElevator;

    /**
     * Constructs a new Library object with the specified attributes.
     *
     * @param name       the name of the library
     * @param address    the address of the library
     * @param nFloors    the number of floors in the library
     * @param hasElevator whether the library has an elevator
     */
    public Library(String name, String address, int nFloors, boolean hasElevator) {
        super(name, address, nFloors);
        this.collection = new Hashtable<>();
        this.hasElevator = hasElevator;
        System.out.println("You have built a library!");
    }

    /**
     * Overloaded constructor that defaults to a single floor and no elevator.
     *
     * @param name    the name of the library
     * @param address the address of the library
     */
    public Library(String name, String address) {
        this(name, address, 1, false); // Default to 1 floor, no elevator
    }

    /**
     * Displays available options for actions that can be performed in the library.
     */
    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println(" + addTitle(title) \n + removeTitle(title) \n + checkOut(title) \n + returnBook(title) \n + printCollection()");
    }

    /**
     * Moves to the specified floor in the library. If the library has an elevator,
     * it allows non-adjacent floor access.
     *
     * @param floorNum the floor number to navigate to
     * @throws RuntimeException if the floor transition is not valid without an elevator
     */
    @Override
    public void goToFloor(int floorNum) {
        if (hasElevator || Math.abs(this.activeFloor - floorNum) == 1) {
            super.goToFloor(floorNum);
        } else {
            throw new RuntimeException("This library does not have an elevator. You can only move to adjacent floors.");
        }
    }

    /**
     * Adds a book title to the library collection if it is not already present.
     *
     * @param title the title of the book to add
     * @throws RuntimeException if the title already exists in the collection
     */
    public void addTitle(String title) {
        if (this.containsTitle(title)) {
            throw new RuntimeException("Title already in collection");
        } else {
            this.collection.put(title, true);
        }
    }

    /**
     * Overloaded method to add multiple book titles to the library collection at once.
     *
     * @param titles the list of book titles to add
     */
    public void addTitle(ArrayList<String> titles) {
        for (String title : titles) {
            if (!this.collection.containsKey(title)) {
                this.collection.put(title, true);
            }
        }
    }

    /**
     * Removes a book title from the library collection if it is present.
     *
     * @param title the title of the book to remove
     * @return the title of the book that was removed
     * @throws RuntimeException if the title is not in the collection
     */
    public String removeTitle(String title) {
        if (!this.containsTitle(title)) {
            throw new RuntimeException("Title not in collection. Can't be removed");
        } else {
            this.collection.remove(title);
            return title;
        }
    }

    /**
     * Checks out a book by setting its availability status to false if the book is available.
     *
     * @param title the title of the book to check out
     * @throws RuntimeException if the title is not available (already checked out)
     */
    public void checkOut(String title) {
        if (isAvailable(title)) {
            this.collection.replace(title, false);
        } else {
            throw new RuntimeException("Title has been borrowed and is not available to be checked out.");
        }
    }

    /**
     * Returns a book by setting its availability status to true if it is in the collection.
     *
     * @param title the title of the book to return
     * @throws RuntimeException if the title is not in the collection
     */
    public void returnBook(String title) {
        if (!this.collection.containsKey(title)) {
            throw new RuntimeException("Title wasn't checked out from this library. Wrong library perhaps?");
        } else {
            this.collection.replace(title, true);
        }
    }

    /**
     * Checks if a given title exists in the library collection.
     *
     * @param title the title of the book to check
     * @return true if the title exists in the collection; false otherwise
     */
    public boolean containsTitle(String title) {
        return this.collection.containsKey(title);
    }

    /**
     * Checks if a book is available for checkout.
     *
     * @param title the title of the book to check
     * @return true if the book is available; false otherwise
     * @throws RuntimeException if the title is not in the collection
     */
    public boolean isAvailable(String title) {
        if (!containsTitle(title)) {
            throw new RuntimeException("Title is not available in the library");
        } else {
            return this.collection.get(title);
        }
    }

    /**
     * Prints the entire collection of books in a table format showing title and availability.
     */
    public void printCollection() {
        // Print the header row
        System.out.printf("%-40s %-10s%n", "Book Title", "Available");
        System.out.println("---------------------------------------- ----------");

        // Print each book-availability status pair
        for (Map.Entry<String, Boolean> entry : this.collection.entrySet()) {
            System.out.printf("%-40s %-10s%n", entry.getKey(), entry.getValue());
        }
    }

    /**
     * Main method to test the Library class functionality.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Library Neilson = new Library("Neilson", "7 Neilson Drive", 4, true);

        // Enter the library and display options
        Neilson.enter();
        Neilson.showOptions();

        // Add books
        Neilson.addTitle("Hunger Games");
        Neilson.addTitle("Harry Potter");
        Neilson.addTitle("Fifty Shades of Grey");
        Neilson.addTitle("R for Data Science");
        Neilson.addTitle("The Miracle in Cell No 7");
        Neilson.addTitle("10 things I love about you");
        Neilson.addTitle("The Outsiders");

        // Remove books
        try {
            Neilson.removeTitle("Hunger Games");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Check out books
        try {
            Neilson.checkOut("Fifty Shades of Grey");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Neilson.checkOut("R for Data Science");

        // Return Book
        try {
            Neilson.returnBook("Fifty Shades of Grey");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Check if available
        Neilson.isAvailable("10 things I love about you");

        // Print the collection
        Neilson.printCollection();

        // Test overloaded methods
        Library smallLibrary = new Library("Neighborhood Library", "222 Elm St"); // Using overloaded constructor
        ArrayList<String> newBooks = new ArrayList<>();
        newBooks.add("To Kill a Mockingbird");
        newBooks.add("The Great Gatsby");
        smallLibrary.addTitle(newBooks); // Using overloaded addTitle
    }
}
