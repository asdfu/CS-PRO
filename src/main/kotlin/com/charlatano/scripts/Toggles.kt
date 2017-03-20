/*
 * Charlatano: Free and open-source (FOSS) cheat for CS:GO/CS:CO
 * Copyright (C) 2017 - Thomas G. P. Nappo, Jonathan Beaudoin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.charlatano.scripts

import com.charlatano.game.*
import com.charlatano.scripts.*
import com.charlatano.settings.*
import com.charlatano.utils.*
import org.jire.arrowhead.keyPressed

// Start in "ON" position
public var toggleAIM = 1
public var toggleRCS = 1
public var toggleESP = 1
public var toggleBunnyHop = 1

// Start in "OFF" position
public var toggleTrigger = -1
public var toggleFlash = -1

fun Toggles() = every(5) {
	if (keyPressed(TOGGLE_KEY_AIM)) {
		toggleAIM *= -1
		Thread.sleep(250)
	}
	if (keyPressed(TOGGLE_KEY_RCS)) {
		toggleRCS *= -1
		Thread.sleep(250)
	}
	if (keyPressed(TOGGLE_KEY_ESP)) {
		toggleESP *= -1
		Thread.sleep(250)
	}
	if (keyPressed(TOGGLE_KEY_BUNNYHOP)) {
		toggleBunnyHop *= -1
		Thread.sleep(250)
	}
	if (keyPressed(TOGGLE_KEY_TRIGGER)) {
		toggleTrigger *= -1
		Thread.sleep(250)
	}
	if (keyPressed(TOGGLE_KEY_FLASH)) {
		toggleFlash *= -1
		Thread.sleep(250)
	}
	return@every
}