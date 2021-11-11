package tetracubered.slurpanize.foodhouse.setup.data.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "foodhouse", name = "look_meta_features")
data class LookMetaFeature(
    @Id
    @Column(name = "id")
    val id: UUID? = null,

    @Column(name = "name", nullable = false)
    val name: String? = null,

    @Column(name = "required", nullable = false)
    val required: Boolean? = null
)