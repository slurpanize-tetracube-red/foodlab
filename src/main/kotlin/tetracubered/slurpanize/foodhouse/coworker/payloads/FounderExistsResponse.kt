package tetracubered.slurpanize.foodhouse.coworker.payloads

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class FounderExistsResponse @JsonCreator constructor(
    @JsonProperty("exists")
    val exists: Boolean
)