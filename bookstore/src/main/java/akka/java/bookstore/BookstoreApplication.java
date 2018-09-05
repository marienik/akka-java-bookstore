package akka.java.bookstore;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.java.bookstore.actors.OrderActor;
import akka.java.bookstore.extension.SpringExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("akka.java.bookstore.config")
public class BookstoreApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(BookstoreApplication.class, args);
        SpringExtension springExtension = context.getBean(SpringExtension.class);

        ActorSystem actorSystem = context.getBean(ActorSystem.class);

        ActorRef inventoryActor =  actorSystem.actorOf(springExtension.props("inventoryActor", null),
                "inventory-actor");

        ActorRef orderActor =  actorSystem.actorOf(springExtension.props("orderActor", inventoryActor),
                "order-actor");

        orderActor.tell(new OrderActor.OrderCreated(1234, 2), ActorRef.noSender());

    }
}
