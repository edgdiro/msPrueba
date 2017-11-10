package pe.infast.security.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import pe.infast.security.repository.UserRepository;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableReactiveMongoRepositories(basePackageClasses = UserRepository.class)
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
public class MongoConfig   extends AbstractReactiveMongoConfiguration {

    private final Environment environment;

    public MongoConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public MongoClient reactiveMongoClient() {

        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "db_security";
    }

}