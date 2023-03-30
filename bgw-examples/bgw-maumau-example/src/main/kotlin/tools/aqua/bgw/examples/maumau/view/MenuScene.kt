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

import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

class MenuScene : MenuScene(500, 500) {

  val helloLabel =
      Label(
          width = 500,
          height = 500,
          posX = 0,
          posY = 0,
          text = "Hello, this is a MenuScene",
          font = Font(size = 20))

  init {
    background = ColorVisual(108, 168, 59)
    addComponents(helloLabel)
  }
}
