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

package tools.aqua.bgw.main

import PropData
import jsonMapper
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import tools.aqua.bgw.application.Config
import tools.aqua.bgw.core.Frontend
import tools.aqua.bgw.main.examples.ExampleApplication
import tools.aqua.bgw.main.view.Application
import java.io.File

internal fun main() {
  if(!Config.USE_SOCKETS || Config.GENERATE_SAMPLES) {
    ExampleApplication.showGameScene(ExampleApplication.exampleUIScene)
    val jsonData = Json.encodeToString(ExampleApplication.exampleUIScene.map)
    File("build/examples").mkdirs()
    File("build/examples/bgwSamples.json").writeText(jsonData)
  } else {
    Application.show()
  }
}
