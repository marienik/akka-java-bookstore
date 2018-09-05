package akka.java.bookstore.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import org.springframework.beans.factory.annotation.Autowired;
import akka.java.bookstore.service.inventory.InventoryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("inventoryActor")
@Scope("prototype")
public class InventoryActor extends AbstractActor {

    public InventoryActor() {
    }

    static public Props props() {
        return Props.create(InventoryActor.class, () -> new InventoryActor());
    }

    @Autowired
    private InventoryService bookstoreInventoryService;

    static public class DecreaseQuantity {

        public final int productId;
        public final int quantity;

        public DecreaseQuantity(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    static public class IncreaseQuantity {

        public final int productId;
        public final int quantity;

        public IncreaseQuantity(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(DecreaseQuantity.class, dq -> {
                    bookstoreInventoryService.Remove(dq.productId, dq.quantity);
                    })
                .match(IncreaseQuantity.class, iq -> {
                    bookstoreInventoryService.Remove(iq.productId, iq.quantity);
                    })
                .build();
        }
}
