package tetracubered.slurpanize.foodhouse.setup.data.respositories

import io.smallrye.mutiny.coroutines.awaitSuspending
import org.hibernate.reactive.mutiny.Mutiny
import tetracubered.slurpanize.foodhouse.setup.data.entities.Foodhouse
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FoodhouseRepository(
    private val rxSessionFactory: Mutiny.SessionFactory
) {

    suspend fun findAnyFoodhouse(): Foodhouse? {
        val rxSession = this.rxSessionFactory.openSession().awaitSuspending()
        val foodhouse = rxSession.createQuery(
            """
            from Foodhouse
        """.trimIndent(),
            Foodhouse::class.java
        )
            .setMaxResults(1)
            .singleResultOrNull
            .awaitSuspending()
        rxSession.flush().awaitSuspending()
        rxSession.close().awaitSuspending()
        return foodhouse
    }

    suspend fun save(foodhouse: Foodhouse) {
        val dbFoodhouse = foodhouse.copy(id = UUID.randomUUID())
        val rxSession = this.rxSessionFactory.openSession().awaitSuspending()
        rxSession.merge(dbFoodhouse).awaitSuspending()
        rxSession.flush().awaitSuspending()
        rxSession.close().awaitSuspending()
    }
}