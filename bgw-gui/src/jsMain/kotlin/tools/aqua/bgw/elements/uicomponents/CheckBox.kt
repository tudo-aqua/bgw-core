package tools.aqua.bgw.elements.uicomponents

import CheckBoxData
import TextFieldData
import csstype.PropertiesBuilder
import web.cssom.*
import data.event.KeyEventAction
import data.event.internal.CheckBoxChangedEventData
import data.event.internal.SelectionChangedEventData
import data.event.internal.TextInputChangedEventData
import emotion.react.css
import kotlinx.browser.document
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import react.*
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import tools.aqua.bgw.builder.ReactConverters.toKeyEventData
import tools.aqua.bgw.builder.ReactConverters.toMouseEventData
import tools.aqua.bgw.builder.VisualBuilder
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.elements.*
import tools.aqua.bgw.event.JCEFEventDispatcher
import tools.aqua.bgw.handlers
import web.dom.Element
import web.html.InputType

external interface CheckBoxProps : Props {
    var data: CheckBoxData
}

fun PropertiesBuilder.cssBuilderIntern(componentViewData: CheckBoxData) {
    cssBuilder(componentViewData)
    display = Display.flex
    alignItems = AlignItems.center
    justifyItems = JustifyItems.flexStart
    gap = 10.rem
}

val CheckBox = FC<CheckBoxProps> { props ->
    bgwCheckBox {
        id = props.data.id
        className = ClassName("textField")
        css {
            cssBuilderIntern(props.data)
        }

        bgwVisuals {
            className = ClassName("visuals")
            +VisualBuilder.build(props.data.visual)
        }

        input {
            type = InputType.checkbox
            id = props.data.id + "--checkbox"
            checked = props.data.isChecked

            useEffect(listOf(props.data.isChecked, props.data.isIndeterminate, props.data.allowIndeterminate)) {
                document.getElementById(props.data.id + "--checkbox")?.let {
                    (it as HTMLInputElement).indeterminate = if(!props.data.isChecked && props.data.allowIndeterminate) props.data.isIndeterminate else false
                }
            }

            css {
                width = 20.rem
                height = 20.rem
                maxWidth = 20.rem
                zIndex = integer(1)
            }
            onChange = {
                JCEFEventDispatcher.dispatchEvent(CheckBoxChangedEventData(!props.data.isChecked).apply { id = props.data.id })
            }
        }

        label {
            className = ClassName("text")
            htmlFor = props.data.id + "--checkbox"
            +props.data.text

            css {
                fontBuilder(props.data)
                alignmentBuilder(props.data)
                display = Display.flex
                width = 100.pct
                height = 100.pct
                position = Position.relative
            }
        }

        onContextMenu = {
            it.preventDefault()
            JCEFEventDispatcher.dispatchEvent(it.toMouseEventData(id))
        }
        onClick = { JCEFEventDispatcher.dispatchEvent(it.toMouseEventData(id)) }
        onKeyDown = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.PRESS)) }
        onKeyUp = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.RELEASE)) }
    }
}

inline val bgwCheckBox: IntrinsicType<HTMLAttributes<Element>>
    get() = "bgw_checkbox".unsafeCast<IntrinsicType<HTMLAttributes<Element>>>()