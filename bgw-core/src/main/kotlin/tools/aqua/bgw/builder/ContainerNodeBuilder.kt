/*
 *    Copyright 2021 The BoardGameWork Authors
 *    SPDX-License-Identifier: Apache-2.0
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package tools.aqua.bgw.builder

import javafx.scene.layout.Pane
import javafx.scene.layout.Region
import tools.aqua.bgw.components.ComponentView
import tools.aqua.bgw.components.container.*
import tools.aqua.bgw.components.gamecomponentviews.GameComponentView
import tools.aqua.bgw.core.Scene

/**
 * ContainerNodeBuilder.
 * Factory for all BGW containers.
 */
internal class ContainerNodeBuilder {
	companion object {
		/**
		 * Switches between Containers.
		 */
		internal fun buildContainer(
			scene: Scene<out ComponentView>,
			container: GameComponentContainer<out GameComponentView>
		): Region =
			Pane().apply {
				container.observableComponents.setGUIListenerAndInvoke (listOf()) { oldValue, newValue ->
					children.clear()
					//remove removed components from componentsMap
					(oldValue - newValue).forEach{ scene.componentsMap.remove(it) }
					//rebuild container with cached or newly build components
					container.observableComponents.forEach {
						if (it in oldValue) {
							children.add(scene.componentsMap[it])
						} else {
							children.add(NodeBuilder.build(scene, it))
						}
					}
				}
			}

		/**
		 * Builds [Area].
		 */
		@Suppress("UNUSED_PARAMETER")
		private fun buildArea(container: GameComponentContainer<*>): Pane = Pane()

		/**
		 * Builds [CardStack].
		 */
		@Suppress("UNUSED_PARAMETER")
		private fun buildCardStack(container: GameComponentContainer<*>): Pane = Pane()

		/**
		 * Builds [Satchel].
		 */
		@Suppress("UNUSED_PARAMETER")
		private fun buildSatchel(container: GameComponentContainer<*>): Pane = Pane()

		/**
		 * Builds [LinearLayout].
		 */
		@Suppress("UNUSED_PARAMETER")
		private fun buildLinearLayout(container: GameComponentContainer<*>): Pane = Pane()
	}
}