package tetracubered.slurpanize.foodhouse.coworker

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tetracubered.slurpanize.foodhouse.data.respositories.CoworkerRepository
import tetracubered.slurpanize.foodhouse.enumerations.FoodhouseAreas
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CoworkerServices(
    private val coworkerRepository: CoworkerRepository
) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CoworkerRepository::class.java)
    }

    suspend fun checkIfFounderInitialized(): Boolean {
        logger.info("Checking if coworkers exists as founder")
        return this.coworkerRepository.existsCoworkerForArea(FoodhouseAreas.FOUNDER)
    }
}