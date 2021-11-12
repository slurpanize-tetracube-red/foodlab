package tetracubered.slurpanize.foodhouse.data.respositories

import io.smallrye.mutiny.coroutines.awaitSuspending
import org.hibernate.reactive.mutiny.Mutiny
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tetracubered.slurpanize.foodhouse.data.entities.Coworker
import tetracubered.slurpanize.foodhouse.enumerations.FoodhouseAreas
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CoworkerRepository(
    private val rxSessionFactory: Mutiny.SessionFactory
) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CoworkerRepository::class.java)
    }

    suspend fun existsCoworkerForArea(foodhouseArea: FoodhouseAreas): Boolean {
        logger.info("Checking if coworkers exists for the area $foodhouseArea")
        val rxSession = this.rxSessionFactory.openSession().awaitSuspending()
        val exists = rxSession.createQuery(
            """
            from Coworker c
            where c.area.name = :area
        """.trimIndent(),
            Coworker::class.java
        )
            .setParameter("area", foodhouseArea)
            .resultList
            .awaitSuspending()
            .size > 0
        rxSession.flush().awaitSuspending()
        rxSession.close().awaitSuspending()
        return exists
    }
}