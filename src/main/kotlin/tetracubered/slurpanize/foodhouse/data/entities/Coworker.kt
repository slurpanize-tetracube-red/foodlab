package tetracubered.slurpanize.foodhouse.data.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "foodhouse", name = "coworkers")
data class Coworker(
    @Id
    @Column(name = "id")
    val id: UUID? = null,

    @Column(name = "name_ref", nullable = false, unique = true)
    val referenceName: String? = null,

    @Column(name = "full_name", nullable = false)
    val fullName: String? = null,

    @JoinColumn(name = "area_id", nullable = false)
    @ManyToOne(targetEntity = Area::class, fetch = FetchType.LAZY)
    val area: Area? = null,

    @JoinColumn(name = "foodhouse_id", nullable = false)
    @ManyToOne(targetEntity = Foodhouse::class, fetch = FetchType.LAZY)
    val foodhouse: Foodhouse? = null
)