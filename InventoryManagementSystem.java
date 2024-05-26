import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Superclass Item
class Item {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String expiryDate;
    private String manufacturer;

    // Constructor
    public Item(int id, String name, int quantity, double price, String expiryDate, String manufacturer) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
        this.manufacturer = manufacturer;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("| %-3d | %-20s | %-8d | %-8.2f |  %-10s |  %-15s |",
                id, name, quantity, price, expiryDate, manufacturer);
    }
}

// Inventory class
class Inventory {
    private List<Item> items;

    // Constructor
    public Inventory() {
        items = new ArrayList<>();
    }

    // Method to add an item to the inventory
    public void addItem(Item item) {
        items.add(item);
    }

    // Method to remove an item from the inventory
    public void removeItem(int id) {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty. No items to remove.");
            return;
        }
        boolean removed = false;
        for (Item item : items) {
            if (item.getId() == id) {
                items.remove(item);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Item with ID " + id + " not found.");
        } else {
            System.out.println("Item removed successfully.");
        }
    }

    // Method to update the quantity of an item
    public void updateQuantity(int id, int newQuantity) {
        for (Item item : items) {
            if (item.getId() == id) {
                item.setQuantity(newQuantity);
                return;
            }
        }
        System.out.println("Item with ID " + id + " not found.");
    }

    // Method to search for an item by its ID
    public Item searchItem(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    // Method to display all items in the inventory
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("| ID  | Name                | Quantity | Price(Rs.)    | Expiry Date            | Manufacturer            |");
            System.out.println("-------------------------------------------------------------------------------------");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println("-------------------------------------------------------------------------------------");
        }
    }
// Method to search for items by name
    public List<Item> searchItemsByName(String name) {
        return items.stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Method to filter items by price range
    public List<Item> filterItemsByPrice(double minPrice, double maxPrice) {
        return items.stream()
                .filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // Method to filter items by quantity range
    public List<Item> filterItemsByQuantity(int minQuantity, int maxQuantity) {
        return items.stream()
                .filter(item -> item.getQuantity() >= minQuantity && item.getQuantity() <= maxQuantity)
                .collect(Collectors.toList());
    }

    // Method to get all items in the inventory
    public List<Item> getItems() {
        return items;
    }
}

// Main class
public class InventoryManagementSystem {

    // Admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    public static void main(String[] args) {
        // Create an instance of Inventory
        Inventory inventory = new Inventory();

        // Scanner to get user input
        Scanner scanner = new Scanner(System.in);

        // Admin login
        boolean isAdminLoggedIn = false;
        System.out.println("Login as Admin:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            isAdminLoggedIn = true;
            System.out.println("Admin logged in successfully.");
        } else {
            System.out.println("Invalid username or password. Exiting...");
            System.exit(0);
        }

        // Display menu and get user choice
        int choice;
        do {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Quantity");
            System.out.println("4. Search Item by Name");
            System.out.println("5. Filter Items by Price Range");
            System.out.println("6. Filter Items by Quantity Range");
            System.out.println("7. Display All Items");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter item ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter item quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter item price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter expiry date (YYYY-MM-DD): ");
                    String expiryDate = scanner.nextLine();
                    System.out.print("Enter manufacturer: ");
                    String manufacturer = scanner.nextLine();
                    System.out.print("Is the item perishable? (yes/no): ");
                    String perishable = scanner.nextLine();
if (perishable.equalsIgnoreCase("yes")) {
                        inventory.addItem(new Item(id, name, quantity, price, expiryDate, manufacturer));
                    } else {
                        inventory.addItem(new Item(id, name, quantity, price, expiryDate, manufacturer));
                    }
                    System.out.println("Item added successfully.");
                    break;
                case 2:
                    System.out.print("Enter item ID to remove: ");
                    int removeId = scanner.nextInt();
                    inventory.removeItem(removeId);
                    break;
                case 3:
                    System.out.print("Enter item ID to update quantity: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    inventory.updateQuantity(updateId, newQuantity);
                    break;
                case 4:
                    System.out.print("Enter item name to search: ");
                    String searchName = scanner.nextLine();
                    List<Item> searchResults = inventory.searchItemsByName(searchName);
                    if (searchResults.isEmpty()) {
                        System.out.println("No item found with name '" + searchName + "'.");
                    } else {
                        System.out.println("Search results:");
                        for (Item item : searchResults) {
                            System.out.println(item);
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter minimum price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    List<Item> priceFilterResults = inventory.filterItemsByPrice(minPrice, maxPrice);
                    if (priceFilterResults.isEmpty()) {
                        System.out
                                .println("No item found within the price range $" + minPrice + " - $" + maxPrice + ".");
                    } else {
                        System.out.println("Items within price range $" + minPrice + " - $" + maxPrice + ":");
                        for (Item item : priceFilterResults) {
                            System.out.println(item);
                        }
                    }
                    break;
                case 6:
                    System.out.print("Enter minimum quantity: ");
                    int minQuantity = scanner.nextInt();
                    System.out.print("Enter maximum quantity: ");
                    int maxQuantity = scanner.nextInt();
                    List<Item> quantityFilterResults = inventory.filterItemsByQuantity(minQuantity, maxQuantity);
                    if (quantityFilterResults.isEmpty()) {
                        System.out.println(
                                "No item found within the quantity range " + minQuantity + " - " + maxQuantity + ".");
                    } else {
                        System.out.println("Items within quantity range " + minQuantity + " - " + maxQuantity + ":");
                        for (Item item : quantityFilterResults) {
                            System.out.println(item);
                        }
                    }
                    break;
                case 7:
                    inventory.displayItems();
                    break;
                case 8:
                    isAdminLoggedIn = false;
                    System.out.println("Admin logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        } while (isAdminLoggedIn);

        // Close scanner
        scanner.close();
    }
}