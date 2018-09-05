package akka.java.bookstore.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("stockActor")
@Scope("prototype")
public class StockActor extends AbstractActor {

    static public Props props(ActorRef inventoryActor) {
        return Props.create(StockActor.class, () -> new StockActor(inventoryActor));
    }
    static public class BookAdded {
        public final int productId;
        public final int quantity;

        public BookAdded(int productId, int quantity)
        {
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    private final ActorRef inventoryActor;

    public StockActor(ActorRef inventoryActor) {
        this.inventoryActor = inventoryActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StockActor.BookAdded.class, bookadd -> {
                    inventoryActor.tell(new InventoryActor.IncreaseQuantity(bookadd.productId, bookadd.quantity), getSelf());
                })
                .build();
    }
}
