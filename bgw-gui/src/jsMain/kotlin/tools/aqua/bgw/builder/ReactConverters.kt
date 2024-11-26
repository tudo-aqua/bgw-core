package tools.aqua.bgw.builder

import ID
import data.event.*
import data.event.internal.*
import tools.aqua.bgw.DragEndEvent
import tools.aqua.bgw.DragMultiEvent
import tools.aqua.bgw.DragStartEvent
import tools.aqua.bgw.event.KeyCode
import tools.aqua.bgw.event.MouseButtonType
import  react.dom.events.MouseEvent as ReactMouseEvent
import  react.dom.events.KeyboardEvent as ReactKeyEvent
import react.dom.events.DragEvent as ReactDragEvent

object ReactConverters {
    fun ReactMouseEvent<*, *>.toMouseEnteredData(targetID: ID?): MouseEnteredEventData {
        return MouseEnteredEventData(clientX, clientY).apply { this.id = targetID }
    }

    fun ReactMouseEvent<*, *>.toMouseExitedData(targetID: ID?): MouseExitedEventData {
        return MouseExitedEventData(clientX, clientY).apply { this.id = targetID }
    }

    fun ReactMouseEvent<*, *>.toMouseEventData(targetID: ID?): MouseEventData {
        return MouseEventData(
            when (button as Int) {
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

    fun ReactKeyEvent<*>.toKeyEventData(targetID: ID?, action: KeyEventAction): KeyEventData {
        return KeyEventData(
            toKeyCode(),
            key,
            ctrlKey,
            shiftKey,
            altKey,
            action
        ).apply { this.id = targetID }
    }

    private fun ReactKeyEvent<*>.toKeyCode(): KeyCode {
        KeyCode.values().forEach {
            if (it.name == this.key) return it
        }
        return KeyCode.UNDEFINED
    }

    fun ReactDragEvent<*>.toDragEventData(targetID: ID?, action: DragEventAction): EventData {
        return when(action) {
            DragEventAction.START -> DragGestureStartedEventData().apply { this.id = targetID }
            DragEventAction.DROP -> {
                val id = dataTransfer.getData("text")
                DragDroppedEventData(targetID ?: "").apply { this.id = id }
            }
            DragEventAction.END -> TODO()
            DragEventAction.ENTER -> TODO()
            DragEventAction.EXIT -> TODO()
        }
    }

    fun DragEndEvent.toDragEventData(): EventData {
        val droppedOn = over?.id
        val elementDragged = active?.id

        return DragDroppedEventData(droppedOn ?: "").apply { this.id = elementDragged }
    }

    fun DragEndEvent.toDragEndedEventData(): EventData {
        val droppedOn = over
        val elementDragged = active?.id
        return DragGestureEndedEventData(droppedOn != null).apply { this.id = elementDragged }
    }

    fun DragStartEvent.toDragStartedEventData(): EventData {
        val element = active?.id
        return DragGestureStartedEventData().apply { this.id = element }
    }

    fun DragMultiEvent.toDragMoveEventData(): EventData {
        return DragGestureMovedEventData().apply { this.id = active?.id }
    }

    fun DragMultiEvent.toDragEnteredEventData(): EventData {
        val element = over?.id
        if(element != null) {
            return DragGestureEnteredEventData(element).apply { this.id = active?.id }
        }

        return DragGestureEnteredEventData("").apply { this.id = active?.id }
    }
}

