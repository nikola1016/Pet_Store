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

// Enum for Order Status
enum OrderStatus {
    CREATED, PROCESSING, SHIPPED, DELIVERED
}


// Food Class
class Food {
    String name;
    double price;
    List<Material> ingredients;
    String expirationDate;

    public Food(String name, double price, String expirationDate) {
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(Material ingredient, int quantity) {
        ingredient.setQuantity(quantity);
        ingredients.add(ingredient);
    }
}

// Accessory Class
class Accessory {
    String name;
    double price;
    List<Material> components;

    public Accessory(String name, double price) {
        this.name = name;
        this.price = price;
        this.components = new ArrayList<>();
    }

    public void addComponent(Material component, int quantity) {
        component.setQuantity(quantity);
        components.add(component);
    }
}

// Material Class
class Material {
    String name;
    double price;
    int quantity;

    public Material(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

// Main Class to Run Project
public class Pet_Store {
    public static void main(String[] args) {
       
        Material meat = new Material("Meat", 15);
        Food dogFood = new Food("Premium Dog Food", 50, "2025-12-31");
        dogFood.addIngredient(meat, 2);

        Accessory leash = new Accessory("Leash", 30);
        Material nylon = new Material("Nylon", 5);
        leash.addComponent(nylon, 1);

        Customer customer = new Customer("Ivan Petrov", "0888123456", "ivan@email.com", "ivanp", "12345", "Sofia, Bulgaria");
        Order order = new Order(customer, 10);
        order.addFood(dogFood);
        order.addAccessory(leash);

        System.out.println("Order for: " + customer.name);
        System.out.println("Total: " + order.calculateTotal() + " BGN");
        System.out.println("Status: " + order.status);
    }
}
