package tetracubered.slurpanize.foodhouse.setup.data.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "foodhouse", name = "areas")
data class Area(
    @Id
    @Column(name = "id")
    val id: UUID? = null,

    @Column(name = "name", nullable = false)
    val name: String? = null
)