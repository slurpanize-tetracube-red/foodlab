package tetracubered.slurpanize.foodhouse.setup.payloads

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class FoodhouseCreateRequest @JsonCreator constructor(
    @field:NotNull
    @field:NotEmpty
    @JsonProperty("name")
    var name: String? = null,

    @field:NotEmpty
    @JsonProperty("description")
    var description: String? = null
)