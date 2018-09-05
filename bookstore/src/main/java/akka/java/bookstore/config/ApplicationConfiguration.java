package akka.java.bookstore.config;

import akka.actor.ActorSystem;
import akka.java.bookstore.extension.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Configuration
@Lazy
@ComponentScan(basePackages = { "akka.java.bookstore.service",
        "akka.java.bookstore.actors", "akka.java.bookstore.extension" })
public class ApplicationConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;

    @Bean
    public ActorSystem actorSystem() {

        ActorSystem system = ActorSystem
                .create("bookstore", akkaConfiguration());

        // Initialize the application context in the Akka Spring Extension
        springExtension.initialize(applicationContext);
        return system;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
}
