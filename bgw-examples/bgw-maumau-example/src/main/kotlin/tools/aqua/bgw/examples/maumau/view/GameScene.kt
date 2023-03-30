/*
 * Copyright 2023 The BoardGameWork Authors
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

package view

import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.TextField
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.core.DEFAULT_BUTTON_HEIGHT
import tools.aqua.bgw.core.DEFAULT_BUTTON_WIDTH
import tools.aqua.bgw.event.DragEvent
import tools.aqua.bgw.visual.ColorVisual
import java.awt.geom.Area

class GameScene : BoardGameScene(900, 600) {

/*
    private var isClicked = false
      Z INdex test

      val buttonOne: Button =
          Button(
                  posX = 200,
                  posY = 200,
                  width = DEFAULT_BUTTON_WIDTH, // 120   320
                  height = DEFAULT_BUTTON_HEIGHT, // 45   245
                  text = "Button 1 First",
                  visual = ColorVisual.BLUE)
              .apply { onMouseClicked = { this.toFront() } }

      val buttonTwo: Button =
          Button(
                  posX = 250,
                  posY = 180,
                  width = DEFAULT_BUTTON_WIDTH, // 120   320
                  height = DEFAULT_BUTTON_HEIGHT, // 45   245
                  text = "Button 2 Mid",
                  visual = ColorVisual.RED)
              .apply { onMouseClicked = { this.toFront() } }

      val buttonThree: Button =
          Button(
                  posX = 300,
                  posY = 160,
                  width = DEFAULT_BUTTON_WIDTH, // 120   320
                  height = DEFAULT_BUTTON_HEIGHT, // 45   245
                  text = "Button 3 Last",
                  visual = ColorVisual.YELLOW)
              .apply { onMouseClicked = { this.toFront() } }
*/


/*
    Button Coordinate Test
    val buttonCoordinates: Button =
        Button(
            posX = 300,
            posY = 160,
            width = DEFAULT_BUTTON_WIDTH, // 120   320
            height = DEFAULT_BUTTON_HEIGHT, // 45   245
            text = "Button that Prints",
            visual = ColorVisual.YELLOW
        )
            .apply {
                onMouseClicked = {
                    println(it.posX)
                    println(it.posY)
                }
            }*/

    /* test for Callback smaller bigger
    val focusButton: Button = Button(
        posX = 300,
        posY = 160,
        width = DEFAULT_BUTTON_WIDTH, // 120   320
        height = DEFAULT_BUTTON_HEIGHT, // 45   245
        text = "Button that has Event",
        visual = ColorVisual.BLUE
    ).apply {
        onMouseEntered = {
            this.height = 120.0
        }

        onMouseExited  = {
            this.height = 150.0
        }

        onKeyPressed = {
            println(" A KEY IS PRESSED ")
        }
    }*/

/*  onKey Pressed

    val buttonTwo: Button =
        Button(
            posX = 250,
            posY = 180,
            width = DEFAULT_BUTTON_WIDTH, // 120   320
            height = DEFAULT_BUTTON_HEIGHT, // 45   245
            text = "Button 2 Mid",
            visual = ColorVisual.RED)
            .apply {onKeyPressed = { println("Button Pressed")} }


    val textInput: TextField = TextField(
        posX = 250,
        posY = 180,
        text = "text Input",
    ).apply {
        onKeyPressed = { println("Text Area is pressed")}
    }
*/

    val testTextField : TextField = TextField(
        posX = 250,
        posY = 180,
        prompt = "Prompt"
    ).apply{
    }
    val buttonCoordinates: Button =
        Button(
            posX = 500,
            posY = 160,
            width = DEFAULT_BUTTON_WIDTH, // 120   320
            height = DEFAULT_BUTTON_HEIGHT, // 45   245
            text = "Button that Prints",
            visual = ColorVisual.YELLOW
        ).apply {
            onMouseClicked ={
                testTextField.isDisabled = true
                println("")
                testTextField.componentStyle
            }
        }


    init {

        background = ColorVisual(108, 168, 59)

        addComponents(testTextField, buttonCoordinates)

        /*   Z Index test
         buttonTwo.zIndex
         addComponents(buttonOne, buttonTwo, buttonThree)
         buttonOne.zIndex = 50
         buttonTwo.zIndex = -20
         buttonThree.zIndex = 100
         buttonThree.toBack()*/



        //onKeyPressed = { println("Key is Pressed")}
    }
}
