import java.sql.*;
import java.util.*;

// User Class
abstract class User {
    String name;
    String phone;
    String email;
    String username;
    String password;

    public User(String name, String phone, String email, String username, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}

// Customer Class
class Customer extends User {
    String address;

    public Customer(String name, String phone, String email, String username, String password, String address) {
        super(name, phone, email, username, password);
        this.address = address;
    }
}

// OrderStatus Enum
enum OrderStatus {
    CREATED, PROCESSING, SHIPPED, DELIVERED
}

// Food Class
class Food {
    String name;
    double price;
    List<String> ingredients;
    Date expirationDate;

    public Food(String name, double price, List<String> ingredients, Date expirationDate) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.expirationDate = expirationDate;
    }
}

// Accessory Class
class Accessory {
    String name;
    double price;
    List<String> materials;

    public Accessory(String name, double price, List<String> materials) {
        this.name = name;
        this.price = price;
        this.materials = materials;
    }
}

// Order Class
class Order {
    List<Food> foodList;
    List<Accessory> accessoryList;
    Customer customer;
    Date orderDate;
    OrderStatus status;
    double deliveryFee;

    public Order(Customer customer, double deliveryFee) {
        this.foodList = new ArrayList<>();
        this.accessoryList = new ArrayList<>();
        this.customer = customer;
        this.orderDate = new Date();
        this.status = OrderStatus.CREATED;
        this.deliveryFee = deliveryFee;
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public void addAccessory(Accessory accessory) {
        accessoryList.add(accessory);
    }

    public double calculateTotal() {
        double total = deliveryFee;
        for (Food food : foodList) {
            total += food.price;
        }
        for (Accessory accessory : accessoryList) {
            total += accessory.price;
        }
        return total;
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }
}

// Service Layer
class CustomerService {
    public void createCustomer(String name, String phone, String email, String username, String password, String address) {
        // Logic to add a new customer
        Customer customer = new Customer(name, phone, email, username, password, address);
        // Save customer to database (to be implemented in Repository Layer)
        System.out.println("Customer created: " + customer.name);
    }
}

class FoodService {
    public void addFood(String name, double price, List<String> ingredients, Date expirationDate) {
        // Logic to add a new food item
        Food food = new Food(name, price, ingredients, expirationDate);
        // Save food to database (to be implemented in Repository Layer)
        System.out.println("Food added: " + food.name);
    }

    public void listFoods() {
        // Logic to retrieve and list all foods (to be implemented in Repository Layer)
        System.out.println("Listing all foods...");
    }
}

class AccessoryService {
    public void addAccessory(String name, double price, List<String> materials) {
        // Logic to add a new accessory item
        Accessory accessory = new Accessory(name, price, materials);
        // Save accessory to database (to be implemented in Repository Layer)
        System.out.println("Accessory added: " + accessory.name);
    }

    public void listAccessories() {
        // Logic to retrieve and list all accessories (to be implemented in Repository Layer)
        System.out.println("Listing all accessories...");
    }
}

class OrderService {
    public void createOrder(Customer customer, double deliveryFee) {
        // Logic to create a new order
        Order order = new Order(customer, deliveryFee);
        // Save order to database (to be implemented in Repository Layer)
        System.out.println("Order created for customer: " + customer.name);
    }

    public void addFoodToOrder(Order order, Food food) {
        order.addFood(food);
        System.out.println("Food added to order: " + food.name);
    }

    public void addAccessoryToOrder(Order order, Accessory accessory) {
        order.addAccessory(accessory);
        System.out.println("Accessory added to order: " + accessory.name);
    }

    public void updateOrderStatus(Order order, OrderStatus status) {
        order.updateStatus(status);
        System.out.println("Order status updated to: " + status);
    }
}

// Main Class to Test Services
public class PetStoreApp {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        FoodService foodService = new FoodService();
        AccessoryService accessoryService = new AccessoryService();
        OrderService orderService = new OrderService();

        // Create a customer
        customerService.createCustomer("Ivan Petrov", "0888123456", "ivan@example.com", "ivanp", "password", "Sofia");

        // Add foods and accessories
        foodService.addFood("Dog Food", 20.5, List.of("Meat", "Rice"), new Date());
        accessoryService.addAccessory("Dog Collar", 15.0, List.of("Leather", "Metal"));

        // Create an order
        Customer customer = new Customer("Ivan Petrov", "0888123456", "ivan@example.com", "ivanp", "password", "Sofia");
        Order order = new Order(customer, 10.0);
        Food food = new Food("Dog Food", 20.5, List.of("Meat", "Rice"), new Date());
        Accessory accessory = new Accessory("Dog Collar", 15.0, List.of("Leather", "Metal"));
        orderService.createOrder(customer, 10.0);
        orderService.addFoodToOrder(order, food);
        orderService.addAccessoryToOrder(order, accessory);

        // Update order status
        orderService.updateOrderStatus(order, OrderStatus.SHIPPED);
    }
}
