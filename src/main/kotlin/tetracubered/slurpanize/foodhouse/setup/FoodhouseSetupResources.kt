package tetracubered.slurpanize.foodhouse.setup

import org.eclipse.microprofile.config.ConfigProvider
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.tags.Tags
import tetracubered.slurpanize.foodhouse.setup.payloads.SetupStatusResponse
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Tags(
    value = [
        Tag(
            name = "Setup Foodhouse",
            description = "Operations to help the setup of new foodhouse or to get the setup status"
        )
    ]
)
@Path("/foodhouse/setup")
class FoodhouseSetupResources(
    private val foodhouseSetupServices: FoodhouseSetupServices
) {

    @Operation(description = "Get setup status", summary = "Returns the setup status useful to start wizard on fronted")
    @APIResponses(
        value = [
            APIResponse(
                description = "Description of actual setup status",
                responseCode = "200",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON,
                        schema = Schema(implementation = SetupStatusResponse::class)
                    )
                ]
            )
        ]
    )
    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun setupStatus(): Response =
        Response.ok(
            this.foodhouseSetupServices.getSetupStatus()
        )
            .build()
}