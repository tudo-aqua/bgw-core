/*
 * Copyright 2021-2024 The BoardGameWork Authors
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

@file:Suppress("unused")

package tools.aqua.bgw.event

/** Enum for all available mouse buttons. */
enum class MouseButtonType {
  /** LEFT / primary mouse button. */
  LEFT_BUTTON,

  /** RIGHT / secondary mouse button. */
  RIGHT_BUTTON,

  /** MOUSE WHEEL button. */
  MOUSE_WHEEL,

  /** All other buttons. */
  OTHER,

  /** Unspecified. */
  UNSPECIFIED
}
