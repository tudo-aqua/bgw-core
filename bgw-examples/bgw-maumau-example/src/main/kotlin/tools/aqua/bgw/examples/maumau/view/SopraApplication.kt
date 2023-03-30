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

import tools.aqua.bgw.components.gamecomponentviews.CardView
import tools.aqua.bgw.components.layoutviews.GridPane
import tools.aqua.bgw.core.BoardGameApplication

class SopraApplication : BoardGameApplication("SoPra Game") {

  // val viewController = MauMauViewController()

  val field: GridPane<CardView> =
      GridPane<CardView>(
              posX = 200,
              posY = 200,
              columns = 1,
              rows = 1,
          )
          .apply {}

  val menu: MenuScene = MenuScene()

  val gameScene: GameScene = GameScene()

  init {

    showMenuScene(menu)
    showGameScene(gameScene)


    //gameScene.apply { onKeyPressed = { println("gamesScene") } }

    menu.helloLabel.onMouseClicked = { hideMenuScene() }
  }
}
