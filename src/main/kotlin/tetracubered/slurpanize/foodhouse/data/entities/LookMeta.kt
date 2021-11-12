package tetracubered.slurpanize.foodhouse.data.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "foodhouse", name = "look_meta")
data class LookMeta(
    @Id
    @Column(name = "id")
    val id: UUID? = null,

    @JoinColumn(name = "foodhouse_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Foodhouse::class)
    val foodhouse: Foodhouse? = null,

    @JoinColumn(name = "feature_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = LookMetaFeature::class)
    val lookMetaFeature: LookMetaFeature? = null,

    @Column(name = "feature_value", nullable = false)
    val featureValue: String? = null
)