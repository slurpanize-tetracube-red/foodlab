package tetracubered.slurpanize.foodhouse.setup

import tetracubered.slurpanize.foodhouse.setup.data.respositories.FoodhouseRepository
import tetracubered.slurpanize.foodhouse.setup.payloads.SetupStatusResponse
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FoodhouseSetupServices(
    private val foodhouseRepository: FoodhouseRepository
) {

    suspend fun getSetupStatus(): SetupStatusResponse {
        val foodhouse = this.foodhouseRepository.findAnyFoodhouse()
        val exists = foodhouse != null
        return SetupStatusResponse(exists)
    }
}