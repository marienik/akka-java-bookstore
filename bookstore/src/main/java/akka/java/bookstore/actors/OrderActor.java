package akka.java.bookstore.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("orderActor")
@Scope("prototype")
public class OrderActor extends AbstractActor {

    static public Props props(ActorRef inventoryActor) {
        return Props.create(OrderActor.class, () -> new OrderActor(inventoryActor));
    }
    static public class OrderCreated {
        public final int productId;
        public final int quantity;

        public OrderCreated(int productId, int quantity)
        {
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    private final ActorRef inventoryActor;

    public OrderActor(ActorRef inventoryActor) {
        this.inventoryActor = inventoryActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(OrderCreated.class, ordcrt -> {
                    inventoryActor.tell(new InventoryActor.DecreaseQuantity(ordcrt.productId, ordcrt.quantity), getSelf());
                })
                .build();
    }
}
