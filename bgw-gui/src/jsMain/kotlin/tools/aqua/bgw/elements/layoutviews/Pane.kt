package tools.aqua.bgw.elements.layoutviews

import ComponentViewData
import LayoutViewData
import PaneData
import csstype.*
import data.event.KeyEventAction
import emotion.react.css
import org.w3c.dom.HTMLDivElement
import react.*
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import tools.aqua.bgw.builder.NodeBuilder
import tools.aqua.bgw.builder.ReactConverters.toKeyEventData
import tools.aqua.bgw.builder.ReactConverters.toMouseEventData
import tools.aqua.bgw.builder.VisualBuilder
import tools.aqua.bgw.elements.bgwContents
import tools.aqua.bgw.elements.bgwVisuals
import tools.aqua.bgw.elements.cssBuilder
import tools.aqua.bgw.event.JCEFEventDispatcher
import tools.aqua.bgw.handlers

external interface PaneProps : Props {
    var data : PaneData
}

fun PropertiesBuilder.cssBuilderIntern(componentViewData: PaneData) {
    cssBuilder(componentViewData)
}

val Pane = FC<PaneProps> { props ->
    val (data, setData) = useState(props.data)

    useEffect {
        handlers[props.data.id] = { newData ->
            if(newData is PaneData) {
                println("Updating Pane ${props.data.id}")
                setData(newData)
            }
        }
    }
    
    bgwPane {
        tabIndex = 0
        id = data.id
        className = ClassName("pane")
        css {
            cssBuilderIntern(data)
        }

        bgwVisuals {
            className = ClassName("visuals")
            VisualBuilder.build(data.visual).forEach {
                +it
            }
        }

        bgwContents {
            className = ClassName("components")
            data.components.forEach {
                +NodeBuilder.build(it)
            }
        }

        onClick = { JCEFEventDispatcher.dispatchEvent(it.toMouseEventData(id)) }
        onKeyDown = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.PRESS)) }
        onKeyUp = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.RELEASE)) }
        onKeyPress = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.TYPE)) }
    }
}

inline val bgwPane: IntrinsicType<HTMLAttributes<HTMLDivElement>>
    get() = "bgw_pane".unsafeCast<IntrinsicType<HTMLAttributes<HTMLDivElement>>>()