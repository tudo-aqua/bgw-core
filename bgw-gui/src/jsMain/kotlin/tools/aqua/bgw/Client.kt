package tools.aqua.bgw


import SceneData
import kotlinx.browser.document
import kotlinx.serialization.decodeFromString
import mapper
import org.w3c.dom.WebSocket
import react.*
import react.dom.client.createRoot
import tools.aqua.bgw.builder.NodeBuilder
import tools.aqua.bgw.elements.App
import kotlin.math.floor
import kotlin.random.Random

var webSocket : WebSocket? = null

fun main() {
    val container = document.createElement("div")
    container.id = "root"
    document.body!!.appendChild(container)
    webSocket = WebSocket("ws://localhost:8080/ws")
    webSocket?.onopen = { println("Connected to Server via WebSocket!") }
    val root = createRoot(container)
    webSocket?.onmessage = { event ->
        println("Received: ${event.data}")
        val scene = mapper.decodeFromString<SceneData>(event.data.toString())
        println("Decoded: $scene")
        val sceneComponents = scene.components.map { NodeBuilder.build(it) }
        println("Built: $sceneComponents")
        root.render(App.create { data = scene })
    }
}

fun List<ReactElement<*>>.toFC() = FC<Props> { appendChildren(this@toFC) }
fun ChildrenBuilder.appendChildren(components: List<ReactElement<*>>) = components.forEach { +it }

fun randomHexColor(): String {
    val chars = "0123456789ABCDEF"
    var color = "#"
    repeat(6) {
        color += chars[floor(Random.nextDouble() * 16).toInt()]
    }
    return color
}