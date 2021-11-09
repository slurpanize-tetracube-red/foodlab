package tetracubered.slurpanize.foodhouse.setup.data.respositories

import io.smallrye.mutiny.coroutines.awaitSuspending
import org.hibernate.reactive.mutiny.Mutiny
import tetracubered.slurpanize.foodhouse.setup.data.entities.Foodhouse
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FoodhouseRepository(
    private val rxSessionFactory: Mutiny.SessionFactory
) {

    suspend fun findAnyFoodhouse(): Foodhouse? {
        val rxSession = this.rxSessionFactory.openSession().awaitSuspending()
        val foodhouse = rxSession.find(Foodhouse::class.java)
            .awaitSuspending()
            .firstOrNull()
        rxSession.flush().awaitSuspending()
        rxSession.close().awaitSuspending()
        return foodhouse
    }
}