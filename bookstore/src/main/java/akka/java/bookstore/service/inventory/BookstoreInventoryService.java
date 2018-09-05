package akka.java.bookstore.service.inventory;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookstoreInventoryService implements InventoryService {

    Map<Integer, Integer> inventory = new HashMap<Integer, Integer>() {
        {
            put(1234, 10);
            put(2345, 20);
        }
    };

    @Override
    public void add(int productId, int quantity) {

        System.out.println("productId : " + productId);

        int value = inventory.get(productId);

        System.out.println("current value before add : " + value);

        inventory.put(productId, value + quantity);

        System.out.println("Final value  after add : " + inventory.get(productId));
    }

    @Override
    public void Remove(int productId, int quantity) {

        System.out.println("productId : " + productId);

        int value = inventory.get(productId);

        System.out.println("current value before remove : " + value);

        inventory.put(productId, value - quantity);

        System.out.println("Final value  after remove : " + inventory.get(productId));
    }
}
