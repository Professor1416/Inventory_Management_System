import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Superclass ElectronicDevice
class ElectronicDevice {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String brand;
    private String model;

    // Constructor
    public ElectronicDevice(int id, String name, int quantity, double price, String brand, String model) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.model = model;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("| %-3d | %-20s | %-8d | %-8.2f |  %-10s |  %-15s |",
                id, name, quantity, price, brand, model);
    }
}

// Inventory class
class ElectronicInventory {
    private List<ElectronicDevice> devices;

    // Constructor
    public ElectronicInventory() {
        devices = new ArrayList<>();
    }

    // Method to add a device to the inventory
    public void addDevice(ElectronicDevice device) {
        devices.add(device);
    }

    // Method to remove a device from the inventory
    public void removeDevice(int id) {
        if (devices.isEmpty()) {
            System.out.println("Inventory is empty. No devices to remove.");
            return;
        }
        boolean removed = false;
        for (ElectronicDevice device : devices) {
            if (device.getId() == id) {
                devices.remove(device);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Device with ID " + id + " not found.");
        } else {
            System.out.println("Device removed successfully.");
        }
    }

    // Method to update the quantity of a device
    public void updateQuantity(int id, int newQuantity) {
        for (ElectronicDevice device : devices) {
            if (device.getId() == id) {
                device.setQuantity(newQuantity);
                return;
            }
        }
        System.out.println("Device with ID " + id + " not found.");
    }

    // Method to search for a device by its ID
    public ElectronicDevice searchDevice(int id) {
        for (ElectronicDevice device : devices) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }

    // Method to display all devices in the inventory
    public void displayDevices() {
        if (devices.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("| ID  | Name                | Quantity | Price($)  | Brand       | Model               |");
            System.out.println("-------------------------------------------------------------------------------------");
            for (ElectronicDevice device : devices) {
                System.out.println(device);
            }
            System.out.println("-------------------------------------------------------------------------------------");
        }
    }

    // Method to search for devices by name
    public List<ElectronicDevice> searchDevicesByName(String name) {
        return devices.stream()
                .filter(device -> device.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Method to filter devices by price range
    public List<ElectronicDevice> filterDevicesByPrice(double minPrice, double maxPrice) {
        return devices.stream()
                .filter(device -> device.getPrice() >= minPrice && device.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // Method to filter devices by quantity range
    public List<ElectronicDevice> filterDevicesByQuantity(int minQuantity, int maxQuantity) {
        return devices.stream()
                .filter(device -> device.getQuantity() >= minQuantity && device.getQuantity() <= maxQuantity)
                .collect(Collectors.toList());
    }

    // Method to get all devices in the inventory
    public List<ElectronicDevice> getDevices() {
        return devices;
    }
}

// Admin class
class Admin {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    // Method to authenticate admin
    public static boolean authenticate(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }
}

// Main class
public class ElectronicInventoryManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Admin login
        System.out.println("Admin Login");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Authenticate admin
        if (!Admin.authenticate(username, password)) {
            System.out.println("Invalid username or password. Exiting...");
            return;
        }

        // Create an instance of ElectronicInventory
        ElectronicInventory electronicInventory = new ElectronicInventory();

        // Display menu and get user choice
        int choice;
        do {
            System.out.println("\nElectronic Inventory Management System");
            System.out.println("1. Add Device");
            System.out.println("2. Remove Device");
            System.out.println("3. Update Quantity");
            System.out.println("4. Search Device by Name");
            System.out.println("5. Filter Devices by Price Range");
            System.out.println("6. Filter Devices by Quantity Range");
            System.out.println("7. Display All Devices");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter device ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter device name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter device quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter device price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    electronicInventory.addDevice(new ElectronicDevice(id, name, quantity, price, brand, model));
                    System.out.println("Device added successfully.");
                    break;
                case 2:
                    System.out.print("Enter device ID to remove: ");
                    int removeId = scanner.nextInt();
                    electronicInventory.removeDevice(removeId);
                    break;
                case 3:
                    System.out.print("Enter device ID to update quantity: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    electronicInventory.updateQuantity(updateId, newQuantity);
                    break;
                case 4:
                    System.out.print("Enter device name to search: ");
                    String searchName = scanner.nextLine();
                    List<ElectronicDevice> searchResults = electronicInventory.searchDevicesByName(searchName);
                    if (searchResults.isEmpty()) {
                        System.out.println("No device found with name '" + searchName + "'.");
                    } else {
                        System.out.println("Search results:");
                        for (ElectronicDevice device : searchResults) {
                            System.out.println(device);
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter minimum price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    List<ElectronicDevice> priceFilterResults = electronicInventory.filterDevicesByPrice(minPrice, maxPrice);
                    if (priceFilterResults.isEmpty()) {
                        System.out.println("No device found within the price range $" + minPrice + " - $" + maxPrice + ".");
                    } else {
                        System.out.println("Devices within price range $" + minPrice + " - $" + maxPrice + ":");
                        for (ElectronicDevice device : priceFilterResults) {
                            System.out.println(device);
                        }
                    }
                    break;
                case 6:
                    System.out.print("Enter minimum quantity: ");
                    int minQuantity = scanner.nextInt();
                    System.out.print("Enter maximum quantity: ");
                    int maxQuantity = scanner.nextInt();
                    List<ElectronicDevice> quantityFilterResults = electronicInventory.filterDevicesByQuantity(minQuantity, maxQuantity);
                    if (quantityFilterResults.isEmpty()) {
                        System.out.println("No device found within the quantity range " + minQuantity + " - " + maxQuantity + ".");
                    } else {
                        System.out.println("Devices within quantity range " + minQuantity + " - " + maxQuantity + ":");
                        for (ElectronicDevice device : quantityFilterResults) {
                            System.out.println(device);
                        }
                    }
                    break;
                case 7:
                    electronicInventory.displayDevices();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        } while (choice != 8);

        // Close scanner
        scanner.close();
    }
}
