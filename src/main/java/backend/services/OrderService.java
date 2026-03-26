package backend.services;

import backend.repositories.OrderRepo;

public class OrderService {

    private final OrderRepo repo;

    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public void createOrder(int itemId, int userId, int amount) {
        repo.createOrder(itemId, userId, amount);
    }

    public void deleteOrder(int orderId) {
        repo.deleteOrder(orderId);
    }

    public OrderService getOrderService(){
        return getOrderService();
    }

}