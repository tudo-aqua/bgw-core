package tools.aqua.bgw.builder

import ID
import data.event.MouseEventData
import tools.aqua.bgw.event.MouseButtonType
import  react.dom.events.MouseEvent as ReactMouseEvent
import tools.aqua.bgw.event.MouseEvent

object ReactConverters {
    fun ReactMouseEvent<*,*>.toMouseEventData(targetID: ID?): MouseEventData {
        return MouseEventData(
            when (button) {
                0 -> MouseButtonType.LEFT_BUTTON
                1 -> MouseButtonType.MOUSE_WHEEL
                2 -> MouseButtonType.RIGHT_BUTTON
                3, 4 -> MouseButtonType.OTHER
                else -> MouseButtonType.UNSPECIFIED
            },
            clientX,
            clientY
        ).apply { this.id = targetID }
    }
}

