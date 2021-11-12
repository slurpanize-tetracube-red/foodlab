package tetracubered.slurpanize.foodhouse.setup.payloads

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class FoodhouseCreateResponse @JsonCreator constructor(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("name")
    val name: String
)