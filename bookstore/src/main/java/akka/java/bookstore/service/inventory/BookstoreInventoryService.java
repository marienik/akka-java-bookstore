package akka.java.bookstore.service.inventory;

import org.springframework.stereotype.Service;

@Service
public class BookstoreInventoryService implements InventoryService {

    @Override
    public void add(int productId, int quantity) {

        System.out.println("product id :" + productId);
        System.out.println("quantity :" + quantity);
    }

    @Override
    public void Remove(int productId, int quantity) {

        System.out.println("product id :" + productId);
        System.out.println("quantity :" + quantity);
    }
}
