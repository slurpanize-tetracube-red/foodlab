package tetracubered.slurpanize.foodhouse

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.mockito.InjectMock
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.smallrye.mutiny.coroutines.asUni
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import tetracubered.slurpanize.foodhouse.data.respositories.FoodhouseRepository
import tetracubered.slurpanize.foodhouse.setup.payloads.SetupStatusResponse

@QuarkusTest
class SetupStatusTest {

    @InjectMock
    lateinit var mockedFoodhouseRepository: FoodhouseRepository

    @Test
    fun setupStatusShouldBeZero() {
        val response = RestAssured.given()
            .`when`().get("/foodhouse/setup/status")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .extract()
            .`as`(SetupStatusResponse::class.java)
        Assertions.assertEquals(response.setupCompleted, false)
    }

    @DelicateCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun setupStatusShouldBeCompleted() {
        val response = GlobalScope.async {
            Mockito.`when`(mockedFoodhouseRepository.findAnyFoodhouse())
                .thenReturn(MockDataset.foodhouse)
            RestAssured.given()
                .`when`().get("/foodhouse/setup/status")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .`as`(SetupStatusResponse::class.java)
        }
            .asUni()
            .await()
            .indefinitely()
        Assertions.assertEquals(response.setupCompleted, true)
    }
}