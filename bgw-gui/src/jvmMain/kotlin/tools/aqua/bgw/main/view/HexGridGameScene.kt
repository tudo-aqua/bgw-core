package tools.aqua.bgw.main.view

import VisualMapper
import tools.aqua.bgw.components.ComponentView
import tools.aqua.bgw.components.container.HexagonGrid
import tools.aqua.bgw.components.container.Satchel
import tools.aqua.bgw.components.gamecomponentviews.HexagonView
import tools.aqua.bgw.components.gamecomponentviews.TokenView
import tools.aqua.bgw.components.layoutviews.CameraPane
import tools.aqua.bgw.components.layoutviews.Pane
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.core.HexOrientation
import tools.aqua.bgw.event.KeyCode
import tools.aqua.bgw.event.MouseButtonType
import tools.aqua.bgw.style.BorderRadius
import tools.aqua.bgw.util.BidirectionalMap
import tools.aqua.bgw.visual.*
import kotlin.random.Random

internal class HexGridGameScene : BoardGameScene() {
    private val hexGrid = HexagonGrid<HexagonView>(
        width = 500,
        height = 500,
        posX = 0,
        posY = 0,
        coordinateSystem = HexagonGrid.CoordinateSystem.OFFSET,
        visual = Visual.EMPTY,
        orientation = HexOrientation.POINTY_TOP
    ).apply {
        onMouseClicked = {
            placeOnHexGrid(HexagonView(visual = ColorVisual(Color(0, 0, 255)), size = 40))
        }
    }

     private val satchel = Satchel<HexagonView>(
        posX = 1100,
        posY = 0,
        width = 100,
        height = 100,
        visual = ImageVisual("https://static.vecteezy.com/system/resources/previews/010/256/326/non_2x/premium-flat-icon-of-game-bag-vector.jpg")
    ).apply {
        dropAcceptor = {
            it.draggedComponent is HexagonView
        }
        onDragDropped = {
            placeInSatchel(it.draggedComponent as HexagonView)
        }
    }

    private val singleHex = HexagonView(
        posX = 1100,
        posY = 0,
        visual = ColorVisual.BLUE,
        size = 50
    ).apply {
        isDraggable = true
    }

    val centerDot = Label(
        posX = 1200 / 2 - 2,
        posY = 800 / 2 - 2,
        width = 4,
        height = 4,
        visual = ColorVisual.RED.copy().apply {
            style.borderRadius = BorderRadius.FULL
        }
    )

    val paneDot = Label(
        posX = -3,
        posY = -3,
        width = 6,
        height = 6,
        visual = ColorVisual.BLUE.copy().apply {
            style.borderRadius = BorderRadius.FULL
        }
    )

    val paneDot2 = Label(
        posX = 200 - 3,
        posY = 100 - 3,
        width = 6,
        height = 6,
        visual = ColorVisual.MAGENTA.copy().apply {
            style.borderRadius = BorderRadius.FULL
        }
    )

    private val targetPane = Pane<ComponentView>(
        width = 1920,
        height = 1080,
        posX = 0,
        posY = 0
    ).apply {
        add(hexGrid)
        add(paneDot)
        add(paneDot2)
    }

    private val cameraPane = CameraPane(
        width = 1200,
        height = 800,
        target = targetPane,
        posX = 0,
        posY = 0,
        visual = ColorVisual.BLUE
    ).apply {
        interactive = true
    }

    val panButton = Button(
        posX = 5,
        posY = 1020,
        width = 100,
        height = 50,
        text = "Pan By",
        visual = ColorVisual(Color(0, 255, 0))
    ).apply {
        onMouseClicked = {
            cameraPane.panBy(200, 100)
        }
    }

    val panZeroButton = Button(
        posX = 105,
        posY = 1020,
        width = 100,
        height = 50,
        text = "Pan",
        visual = ColorVisual(Color(0, 125, 0))
    ).apply {
        onMouseClicked = {
            cameraPane.pan(0, 0, smooth = false)
        }
    }

    val zoomButton = Button(
        posX = 205,
        posY = 1020,
        width = 100,
        height = 50,
        text = "Change",
        visual = ColorVisual(Color(0, 63, 0))
    ).apply {
        onMouseClicked = {
            cameraPane.panMouseButton = MouseButtonType.MOUSE_WHEEL
            cameraPane.visual = ColorVisual.YELLOW
        }
    }

    private val hexPointy = HexagonView(posX = 800, posY = 200, visual = ColorVisual(Color(255, 0, 0)), size = 50, orientation = HexOrientation.POINTY_TOP)
    private val hexFlat = HexagonView(posX = 900, posY = 200, visual = ColorVisual(Color(0, 255, 0)), size = 50, orientation = HexOrientation.FLAT_TOP)

    fun placeOnHexGrid(hexagon: HexagonView) {
        hexGrid[10,0] = HexagonView(visual = ColorVisual(Color(255, 0, 0)), size = 50, orientation = HexOrientation.POINTY_TOP)
        targetPane.width = hexGrid.width
        targetPane.height = hexGrid.height
    }

    fun placeInSatchel(hexagon: HexagonView) {
        hexGrid.remove(hexagon)
        satchel.add(hexagon)
    }

    val hexMap = BidirectionalMap<Pair<Int, Int>, HexagonView>()

    fun buildHexGrid() {
        for (q in -5..5) {
            for (r in -5..5) {
                if (q + r >= -5 && q + r <= 5) {
                    if(hexMap.containsForward(Pair(q,r))) {
                        hexGrid[q,r] = hexMap[Pair(q,r)]!! as HexagonView
                        continue
                    }
                    val hexagon = HexagonView(
                        visual = ColorVisual.LIGHT_GRAY,
                        size = 40
                    ).apply {
                        dropAcceptor = {
                            it.draggedComponent is HexagonView
                        }

                        onDragDropped = {
                            hexGrid[q,r] = it.draggedComponent as HexagonView
                            satchel.remove(it.draggedComponent)
                        }

                        onDragGestureEntered = {
                            visual = ColorVisual.GRAY
                        }

                        onDragGestureExited = {
                            visual = ColorVisual.LIGHT_GRAY
                        }
                    }
                    hexGrid[q,r] = hexagon
                    hexMap[Pair(q,r)] = hexagon
                }
            }
        }

        targetPane.width = hexGrid.width
        targetPane.height = hexGrid.height
    }

    fun refreshHexGrid() {
        buildHexGrid()
    }

    init {
        buildHexGrid()

        addComponents(cameraPane, panButton, panZeroButton, zoomButton, centerDot)
//        repeat(20) {
//            val hexagon = HexagonView(posX = 800, posY = 800, visual = ImageVisual("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwc4YbxNBYXWRkgqzh9tbaSQh2Uy-f4e1Nl0teHHWFisub3gxv4rxn1eFjgVUUMASaNSg&usqp=CAU"), size = 40, orientation = HexOrientation.FLAT_TOP).apply {
//                isDraggable = true
//                onKeyPressed = {
//                    if(it.keyCode == KeyCode.Q) {
//                        rotation -= 60.0
//                    } else if(it.keyCode == KeyCode.E) {
//                        rotation += 60.0
//                    }
//                }
//            }
//            satchel.add(hexagon)
//        }
    }
}
