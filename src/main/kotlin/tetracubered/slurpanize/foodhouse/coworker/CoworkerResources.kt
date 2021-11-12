package tetracubered.slurpanize.foodhouse.coworker

import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.tags.Tags
import tetracubered.slurpanize.foodhouse.coworker.payloads.FounderExistsResponse
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Tags(
    value = [
        Tag(
            name = "Foodhouse Coworkers operations",
            description = "The operations included in resource are useful to make safe checks on people that work in Foodhouse"
        )
    ]
)
@Path("/coworker")
class CoworkerResources(
    private val coworkerServices: CoworkerServices
) {

    @Operation(
        description = "Checks if founder coworker exists",
        summary = "Checks if there is the founder has been initialized in foodhouse"
    )
    @APIResponses(
        value = [
            APIResponse(
                description = "Safe check for founder initialization status",
                responseCode = "200",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON,
                        schema = Schema(implementation = FounderExistsResponse::class)
                    )
                ]
            )
        ]
    )
    @GET
    @Path("/founder-exists")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun founderExists(): Response {
        val founderExists = this.coworkerServices.checkIfFounderInitialized()
        val founderExistsResponse = FounderExistsResponse(founderExists)
        return Response.ok(founderExistsResponse)
            .build()
    }
}