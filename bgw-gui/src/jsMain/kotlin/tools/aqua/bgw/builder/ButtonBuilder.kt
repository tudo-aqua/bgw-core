package tools.aqua.bgw.builder

import ComponentView
import react.ReactElement
import react.create
import tools.aqua.bgw.elements.ReactButton
import tools.aqua.bgw.randomHexColor

object ButtonBuilder {
    fun build(componentView: ComponentView): ReactElement<*> {
        return ReactButton.create {
            id = componentView.id
            color = randomHexColor()
        }
    }
}