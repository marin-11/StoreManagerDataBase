package models;

public class Inventory {
    private int itemId;
    private String name;
    private double price;
    private int stock;
    private double discount;

    public Inventory(int itemId, String name, double price, int stock, double discount) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
    }

    public int getItemId() { return itemId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public double getDiscount() { return discount; }
}