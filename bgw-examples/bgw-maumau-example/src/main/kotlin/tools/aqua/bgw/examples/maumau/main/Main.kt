/*
 * Copyright 2022-2023 The BoardGameWork Authors
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tools.aqua.bgw.examples.maumau.main

import javafx.scene.control.Button
import tools.aqua.bgw.components.ComponentView
import tools.aqua.bgw.components.container.HexagonGrid
import tools.aqua.bgw.components.gamecomponentviews.HexagonView
import tools.aqua.bgw.components.layoutviews.CameraPane
import tools.aqua.bgw.components.layoutviews.Pane
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.BoardGameApplication
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.event.KeyCode
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.CompoundVisual
import tools.aqua.bgw.visual.ImageVisual

/** Entry point. */
fun main() {
  // MauMauViewController()
  Application.show()
}
// TODO: Take this out
object Application : BoardGameApplication() {
  val imageVisual = ImageVisual(CARDS_FILE, 130, 200, 260, 200)
  //val imageVisual = ColorVisual.GREEN
  val imageVisual2 = ImageVisual(CARDS_FILE, 130, 200, 0, 0)
  val imageVisual3 = ImageVisual(CARDS_FILE, 130, 200, 260, 400)
  val imageVisual4 = ImageVisual(CARDS_FILE, 130, 200, 260, 600)

  var count = 0

  val gameScene =
      object : BoardGameScene() {
        val pane = Pane<ComponentView>(width = 4000, height = 4000)
        val hexagonGrid = HexagonGrid<HexagonView>()

        val cameraPane =
            CameraPane(width = 1920, height = 1080, target = pane).apply { interactive = true }

        init {
          onKeyPressed = {
            when (it.keyCode) {
              KeyCode.A -> cameraPane.isHorizontalLocked = !cameraPane.isHorizontalLocked
              KeyCode.B -> cameraPane.isVerticalLocked = !cameraPane.isVerticalLocked
              else -> {}
            }
          }

          addTestComponents()
          pane.add(hexagonGrid)
          addComponents(cameraPane)
        }

        fun addTestComponents() {
          fun flip(visual: ColorVisual) =
              if (visual == ColorVisual.WHITE) ColorVisual.BLACK else ColorVisual.WHITE
          var visual = ColorVisual.BLACK

          for (x in 0 until 4000 step 100) {
            val tmp = visual
            for (y in 0 until 4000 step 100) {
              hexagonGrid[x / 100, y / 100] =
                  HexagonView(visual = CompoundVisual(ColorVisual.BLACK, imageVisual2, visual))
                      .apply {
                        onMouseClicked = {
                          val addVisual = when(count) {
                            0 -> imageVisual
                            1 -> imageVisual2
                            2 -> imageVisual3
                            3 -> imageVisual4
                            else -> imageVisual
                          }
                          val visual = this.visual
                          if (visual is CompoundVisual) {
                            visual.childrenProperty.add(addVisual)
                          }
                          hexagonGrid.components.forEach {
                            val visual = it.visual
                            if (visual is CompoundVisual) {
                              visual.childrenProperty.add(addVisual)
                            }
                          }
                          count = (count + 1) % 4
                          println(count)
                        }
                      }



              /*
              pane.add(Label(
                width = 100,
                height = 100,
                visual = CompoundVisual(ColorVisual.BLACK, imageVisual2, visual),
                posX = x,
                posY = y
              ).apply {
                onMouseClicked = {
                  val visual = this.visual
                  if(visual is CompoundVisual) {
                    visual.childrenProperty.add(imageVisual)
                  }
                  pane.components.filter { it.posY == this.posY }.forEach {
                    val visual = it.visual
                    if(visual is CompoundVisual) {
                      visual.childrenProperty.add(imageVisual)
                    }
                  }
                }
              })*/
              visual = flip(visual)
            }
            visual = flip(tmp)
          }
        }
      }

  init {
    showGameScene(gameScene)
  }
}
