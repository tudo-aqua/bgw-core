package tools.aqua.bgw.elements.uicomponents

import ComboBoxData
import csstype.*
import data.event.KeyEventAction
import emotion.react.css
import org.w3c.dom.HTMLInputElement
import react.FC
import react.IntrinsicType
import react.Props
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import tools.aqua.bgw.builder.ReactConverters.toKeyEventData
import tools.aqua.bgw.builder.ReactConverters.toMouseEventData
import tools.aqua.bgw.builder.VisualBuilder
import tools.aqua.bgw.components.uicomponents.ComboBox
import tools.aqua.bgw.elements.*
import tools.aqua.bgw.event.JCEFEventDispatcher

external interface ComboBoxProps : Props {
    var data: ComboBoxData
}

fun PropertiesBuilder.cssBuilderIntern(componentViewData: ComboBoxData) {
    cssBuilder(componentViewData)
}

val ComboBox = FC<ComboBoxProps> { props ->
    bgwComboBox {
        id = props.data.id
        className = ClassName("comboBox")
        css {
            cssBuilderIntern(props.data)
        }

        bgwVisuals {
            className = ClassName("visuals")
            VisualBuilder.build(props.data.visual).forEach {
                +it
            }
        }

        select {
            placeholder = props.data.prompt
            defaultValue = if(props.data.selectedItem == null) "" else props.data.selectedItem.toString()
            css {
                fontBuilder(props.data)
                comboBoxBuilder(props.data)
                placeholder {
                    fontBuilder(props.data)
                    opacity = number(0.5)
                }
                position = Position.absolute
            }
            props.data.items.forEach {
                option {
                    value = it.lowercase()
                    +it
                }
            }
        }

        onClick = { JCEFEventDispatcher.dispatchEvent(it.toMouseEventData(id)) }
        onKeyDown = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.PRESS)) }
        onKeyUp = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.RELEASE)) }
        onKeyPress = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.TYPE)) }
    }
}

inline val bgwComboBox: IntrinsicType<HTMLAttributes<HTMLInputElement>>
    get() = "bgw_combo_box".unsafeCast<IntrinsicType<HTMLAttributes<HTMLInputElement>>>()