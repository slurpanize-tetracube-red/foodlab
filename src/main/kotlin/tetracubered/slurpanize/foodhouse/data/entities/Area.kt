package tetracubered.slurpanize.foodhouse.data.entities

import tetracubered.slurpanize.foodhouse.enumerations.FoodhouseAreas
import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "foodhouse", name = "areas")
data class Area(
    @Id
    @Column(name = "id")
    val id: UUID? = null,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    val name: FoodhouseAreas? = null
)