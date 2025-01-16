/*
 * Copyright 2025 The BoardGameWork Authors
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

package tools.aqua.bgw.main.view

import kotlin.random.Random
import tools.aqua.bgw.components.uicomponents.*
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

internal class UIScene :
    MenuScene(width = 500, background = ColorVisual(Color(255, 0, 0)).apply { transparency = 0.5 }) {
  val combo =
      ComboBox<TestObject>(
              width = 1920,
              height = 50,
              items =
                  listOf(
                      TestObject("Test1"),
                      TestObject("Test2"),
                      TestObject("Test3"),
                      TestObject("Test4")),
              prompt = "Select an item",
              font = Font(20.0, java.awt.Color(0x551100), "Staatliches", Font.FontWeight.NORMAL),
              formatFunction = { it.name })
          .apply {}

  private val toggleGroup =
      ToggleGroup().apply {
        // onSelected = { button -> println("Selected: ${button.text}") }
        // onDeselected = { button -> println("Deselected: ${button.text}") }
      }

  private val toggle =
      ToggleButton(
              posX = 1000,
              posY = 500,
              width = 300,
              height = 100,
              text = "Toggle",
              font = Font(20.0, Color.BLACK, "JetBrainsMono", Font.FontWeight.EXTRA_BOLD),
              visual = ColorVisual(Color.GREEN),
              alignment = Alignment.CENTER,
          )
          .apply {
            onMouseClicked = {
              Application.updateSome()
              Application.hideMenuScene(500)
            }
          }

  private val toggle2 =
      ToggleButton(
              posX = 1000,
              posY = 600,
              width = 100,
              height = 100,
              text = "Indeterminate",
              font = Font(20.0, Color.BLACK, "JetBrainsMono", Font.FontWeight.EXTRA_BOLD),
              visual = ColorVisual(Color.ORANGE))
          .apply { onMouseClicked = { Application.showGameScene(Application.cardLayoutScene) } }

  private val checkBox =
      CheckBox(
              posX = 1000,
              posY = 700,
              width = 100,
              height = 100,
              text = "Check",
              font = Font(20.0, Color.BLACK, "JetBrainsMono", Font.FontWeight.EXTRA_BOLD),
              visual = ColorVisual(Color.RED),
          )
          .apply {
            onCheckedChanged = { checked -> println("Checked: $checked") }

            onIndeterminateChanged = { indeterminate -> println("Indeterminate: $indeterminate") }
          }

  private val progress =
      ProgressBar(
              posX = 80, posY = 80, width = 800, height = 50, progress = 0.5, barColor = Color.BLUE)
          .apply {
            onMouseClicked = { this.progress = Random.nextDouble(0.0, 1.0) }
            scaleY = 2.0
            onProgressed = { newValue -> println("Progressed to $newValue") }
          }

  private val textfield =
      TextField(
              posX = 80,
              posY = 180,
              width = 800,
              height = 50,
              text = "Testbox",
              font = Font(20.0, Color.WHITE, "Rubik", Font.FontWeight.SEMI_BOLD),
              prompt = "Enter text here",
          )
          .apply {
            visual = ColorVisual(Color.RED)

            onTextChanged = { text -> println(text) }
          }

  private val passwordfield =
      PasswordField(
              posX = 80,
              posY = 250,
              width = 800,
              height = 50,
              text = "Passwortbox",
              font = Font(20.0, Color.BLACK, "JetBrainsMono", Font.FontWeight.EXTRA_BOLD),
              prompt = "Enter password here",
          )
          .apply {
            visual = ColorVisual(Color.LIGHT_GRAY)

            onTextChanged = { text -> println(text) }
          }

  private val textarea =
      TextArea(
              posX = 80,
              posY = 320,
              width = 800,
              height = 600,
              text = "Test\nArea",
              font = Font(20.0, Color.BLACK, "JetBrainsMono", Font.FontWeight.EXTRA_BOLD),
              prompt = "Enter text here",
          )
          .apply {
            visual = ColorVisual(Color.BLUE)

            onTextChanged = { text -> println(text) }
          }

  private val color =
      ColorPicker(
              posX = 500,
              posY = 50,
              width = 100,
              height = 50,
              initialColor = Color(255, 0, 0),
          )
          .apply {
            visual = ColorVisual(Color.LIGHT_GRAY)

            onColorSelected = { color -> println("Selected color: ${color.toHex()}") }
          }

  init {
    addComponents(
        combo, progress, textfield, passwordfield, textarea, color, toggle, toggle2, checkBox)

    combo.select(2)

    println("UIScene initialized")
  }

  inner class TestObject(val name: String) {}
}
