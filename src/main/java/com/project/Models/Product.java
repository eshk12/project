package com.project.Models;

public class Product extends BaseEntitie {
    private String name;
    private int quantity;
    private int price;

    public Product() {}
    public Product(int id, String name, int quantity, int price, boolean deleted) {
        super(id, deleted);
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String name, int quantity, int price, boolean deleted) {
        super(deleted);
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public boolean objectIsEmpty() {
        if (isEmpty(this.name) || this.quantity <= 0 || this.price <= 0) {
            return true;
        }
        return false;
    }

    public boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
