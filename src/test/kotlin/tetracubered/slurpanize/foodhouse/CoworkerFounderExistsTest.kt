package tetracubered.slurpanize.foodhouse

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.mockito.InjectMock
import io.restassured.RestAssured
import io.smallrye.mutiny.coroutines.asUni
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import tetracubered.slurpanize.foodhouse.coworker.payloads.FounderExistsResponse
import tetracubered.slurpanize.foodhouse.data.respositories.CoworkerRepository
import tetracubered.slurpanize.foodhouse.enumerations.FoodhouseAreas

@QuarkusTest
class CoworkerFounderExistsTest {

    @InjectMock
    lateinit var mockedCoworkerRepository: CoworkerRepository

    @DelicateCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun createFoodhouseNotExists() {
        val response = GlobalScope.async {
            Mockito.`when`(mockedCoworkerRepository.existsCoworkerForArea(FoodhouseAreas.FOUNDER))
                .thenReturn(false)
            RestAssured.given()
                .`when`().get("/coworker/founder-exists")
                .then()
                .statusCode(200)
                .extract()
                .`as`(FounderExistsResponse::class.java)
        }
            .asUni()
            .await()
            .indefinitely()
        Assertions.assertFalse(response.exists)
    }

    @DelicateCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun createFoodhouseExists() {
        val response = GlobalScope.async {
            Mockito.`when`(mockedCoworkerRepository.existsCoworkerForArea(FoodhouseAreas.FOUNDER))
                .thenReturn(true)
            RestAssured.given()
                .`when`().get("/coworker/founder-exists")
                .then()
                .statusCode(200)
                .extract()
                .`as`(FounderExistsResponse::class.java)
        }
            .asUni()
            .await()
            .indefinitely()
        Assertions.assertTrue(response.exists)
    }
}