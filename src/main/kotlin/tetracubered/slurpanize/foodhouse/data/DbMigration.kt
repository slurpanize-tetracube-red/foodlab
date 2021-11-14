package tetracubered.slurpanize.foodhouse.data

import io.quarkus.runtime.Startup
import io.quarkus.runtime.StartupEvent
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.flywaydb.core.Flyway
import javax.enterprise.event.Observes
import javax.inject.Singleton

@Singleton
class DbMigration {

    @ConfigProperty(name = "quarkus.datasource.reactive.url")
    lateinit var datasourceUrl: String

    @ConfigProperty(name = "quarkus.datasource.username")
    lateinit var datasourceUsername: String

    @ConfigProperty(name = "quarkus.datasource.password")
    lateinit var datasourcePassword: String

    fun runFlywayMigration(@Observes event: StartupEvent) {
        val flyway =
            Flyway.configure().dataSource("jdbc:" + datasourceUrl, datasourceUsername, datasourcePassword).load()
        flyway.migrate()
    }
}

/*
@ApplicationScoped
public class RunFlyway {

    @ConfigProperty(name = "myapp.flyway.migrate")
    boolean runMigration;

    @ConfigProperty(name = "quarkus.datasource.reactive.url")
    String datasourceUrl;
    @ConfigProperty(name = "quarkus.datasource.username")
    String datasourceUsername;
    @ConfigProperty(name = "quarkus.datasource.password")
    String datasourcePassword;

    public void runFlywayMigration(@Observes StartupEvent event) {
        if (runMigration) {
            Flyway flyway = Flyway.configure().dataSource("jdbc:" + datasourceUrl, datasourceUsername, datasourcePassword).load();
            flyway.migrate();
        }
    }
}
 */