package tetracubered.slurpanize.foodhouse.setup

import io.netty.handler.codec.http.HttpResponseStatus
import tetracubered.slurpanize.foodhouse.setup.data.entities.Foodhouse
import tetracubered.slurpanize.foodhouse.setup.data.respositories.FoodhouseRepository
import tetracubered.slurpanize.foodhouse.setup.payloads.FoodhouseCreateRequest
import tetracubered.slurpanize.foodhouse.setup.payloads.SetupStatusResponse
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.ClientErrorException

@ApplicationScoped
class FoodhouseSetupServices(
    private val foodhouseRepository: FoodhouseRepository
) {

    suspend fun getSetupStatus(): SetupStatusResponse {
        val exists = this.foodhouseRepository.findAnyFoodhouse() != null
        return SetupStatusResponse(exists)
    }

    suspend fun createFoodhouse(foodhouseCreateRequest: FoodhouseCreateRequest): Result<SetupStatusResponse> {
        val exists = this.foodhouseRepository.findAnyFoodhouse() != null
        if (exists) {
            return Result.failure(
                ClientErrorException(
                    "There is another Foodhouse already configured",
                    HttpResponseStatus.CONFLICT.code()
                )
            )
        }

        val foodhouse = Foodhouse(
            name = foodhouseCreateRequest.name,
            description = foodhouseCreateRequest.description
        )
        this.foodhouseRepository.save(foodhouse)
        return Result.success(SetupStatusResponse(setupCompleted = true))
    }
}