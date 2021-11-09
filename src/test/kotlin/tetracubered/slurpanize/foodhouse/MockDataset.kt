package tetracubered.slurpanize.foodhouse

import tetracubered.slurpanize.foodhouse.setup.data.entities.Foodhouse
import java.util.*

class MockDataset {

    companion object {
        val foodhouse: Foodhouse = Foodhouse(UUID.randomUUID(), "Grill'em", "Great grill food truck")
    }
}