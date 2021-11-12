package tetracubered.slurpanize.foodhouse.setup.payloads

import com.fasterxml.jackson.annotation.JsonCreator
import java.util.*

data class FoodhouseCreateResponse @JsonCreator constructor(
    val id: UUID,
    val name: String
)