package mapper

import EventData
import MouseEventData
import tools.aqua.bgw.event.Event
import tools.aqua.bgw.event.MouseEvent

object EventMapper {
    fun map(event: Event) : EventData {
        return when (event) {
            is MouseEvent -> MouseEventData(
                id = event.id,
                button = event.button,
                posX = event.posX.toDouble(),
                posY = event.posY.toDouble()
            )
            else -> {
                throw IllegalArgumentException("Event type not supported")
            }
        }
    }
}