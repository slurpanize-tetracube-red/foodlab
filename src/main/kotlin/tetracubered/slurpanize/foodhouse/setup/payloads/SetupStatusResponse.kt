package tetracubered.slurpanize.foodhouse.setup.payloads

import com.fasterxml.jackson.annotation.JsonProperty

data class SetupStatusResponse(
    @JsonProperty("setupCompleted")
    val setupCompleted: Boolean
)