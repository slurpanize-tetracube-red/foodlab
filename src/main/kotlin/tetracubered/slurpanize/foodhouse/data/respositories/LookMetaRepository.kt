package tetracubered.slurpanize.foodhouse.data.respositories

import io.smallrye.mutiny.coroutines.awaitSuspending
import org.hibernate.reactive.mutiny.Mutiny
import tetracubered.slurpanize.foodhouse.data.entities.LookMeta
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class LookMetaRepository(
    private val rxSessionFactory: Mutiny.SessionFactory
) {

    suspend fun getAllByFoodhouse(foodhouseId: UUID): List<LookMeta> {
        val rxSession = this.rxSessionFactory.openSession().awaitSuspending()
        val lookMeta = rxSession.createQuery(
            """
            from LookMeta lm
            left join fetch lm.foodhouse
            left join fetch lm.lookMetaFeature
            where lm.foodhouse.id = :id
        """.trimIndent(),
            LookMeta::class.java
        )
            .setParameter("id", foodhouseId)
            .resultList
            .awaitSuspending()
        rxSession.flush().awaitSuspending()
        rxSession.close().awaitSuspending()
        return lookMeta
    }
}