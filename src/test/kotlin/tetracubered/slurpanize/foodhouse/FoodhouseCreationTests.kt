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
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import tetracubered.slurpanize.foodhouse.setup.data.respositories.FoodhouseRepository
import tetracubered.slurpanize.foodhouse.setup.payloads.FoodhouseCreateRequest

@QuarkusTest
class FoodhouseCreationTests {

    @InjectMock
    lateinit var mockedFoodhouseRepository: FoodhouseRepository

    @Test
    fun createFoodhouseBadRequest() {
        val request = FoodhouseCreateRequest(null, null)
        RestAssured.given()
            .body(request)
            .contentType(ContentType.JSON)
            .`when`().post("/foodhouse/setup/create")
            .then()
            .statusCode(400)
            .contentType(ContentType.JSON)
            .extract()
    }

    @ExperimentalCoroutinesApi
    @DelicateCoroutinesApi
    @Test
    fun createFoodhouseConflictRequest() {
        val request = FoodhouseCreateRequest(MockDataset.foodhouse.name, MockDataset.foodhouse.description)
        GlobalScope.async {
            `when`(mockedFoodhouseRepository.findAnyFoodhouse())
                .thenReturn(MockDataset.foodhouse)
            RestAssured.given()
                .body(request)
                .contentType(ContentType.JSON)
                .`when`().post("/foodhouse/setup/create")
                .then()
                .statusCode(409)
                .contentType(ContentType.JSON)
        }
            .asUni()
            .await()
            .indefinitely()
    }
}