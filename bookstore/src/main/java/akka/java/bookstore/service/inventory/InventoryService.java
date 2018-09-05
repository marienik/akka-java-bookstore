package akka.java.bookstore.service.inventory;

public interface InventoryService {

    void add(int productId, int quantity);
    void Remove(int productId, int quantity);
}
