package backend.services;

import backend.repositories.InventoryRepo;
import models.Inventory;

import java.util.List;

public class InventoryService {

    private final InventoryRepo repo;

    public InventoryService(InventoryRepo repo) {
        this.repo = repo;
    }

    public List<Inventory> getAllInventory() {
        return repo.getAllItems();
    }

    public void updateItem(int itemId, String name, double price, int stock, double discount) {

        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (price < 0 || stock < 0) {
            throw new IllegalArgumentException("Price/Stock must be positive");
        }

        repo.updateItem(itemId, name, price, stock, discount);
    }
}