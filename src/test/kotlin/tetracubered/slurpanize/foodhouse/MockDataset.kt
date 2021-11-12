package tetracubered.slurpanize.foodhouse

import tetracubered.slurpanize.foodhouse.data.entities.Area
import tetracubered.slurpanize.foodhouse.data.entities.Coworker
import tetracubered.slurpanize.foodhouse.data.entities.Foodhouse
import tetracubered.slurpanize.foodhouse.enumerations.FoodhouseAreas
import java.util.*

class MockDataset {

    companion object {
        val foodhouse: Foodhouse = Foodhouse(UUID.randomUUID(), "Grill'em", "Great grill food truck")
        val founder: Coworker = Coworker(
            UUID.randomUUID(),
            "dave_ref",
            "Davide Di Domenico",
            Area(
                UUID.randomUUID(),
                FoodhouseAreas.FOUNDER
            ),
            foodhouse
        )
    }
}