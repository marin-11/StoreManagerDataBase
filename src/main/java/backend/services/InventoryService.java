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
}