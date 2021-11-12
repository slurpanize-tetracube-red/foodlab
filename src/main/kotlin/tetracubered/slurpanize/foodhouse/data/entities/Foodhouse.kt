package tetracubered.slurpanize.foodhouse.data.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "foodhouse", name = "foodhouses")
data class Foodhouse(
    @field:Id
    @field:Column(name = "id")
    val id: UUID? = null,

    @field:Column(name = "name", nullable = false)
    val name: String? = null,

    @field:Column(name = "description")
    val description: String? = null
)