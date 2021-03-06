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
public var toggleTrigger = 1
public var toggleFlash = 1

// Start in "OFF" position
public var toggleRage = -1


fun Toggles() = every(5) {
	var wait = -1;
	if (keyPressed(0x12)) { // Hold alt key + other key(s)
		if (keyPressed(0x69) && keyPressed(0x68) && keyPressed(0x67)) { // 7 & 8 & 9 // WILL NOT TOGGLE TO RAGE SETTINGS
			toggleRage *= -1
			wait = 1;
		}
		if (keyPressed(TOGGLE_KEY_AIM)) {
			toggleAIM *= -1
			wait = 1;
		}
		if (keyPressed(TOGGLE_KEY_RCS)) {
			toggleRCS *= -1
			wait = 1;
		}
		if (keyPressed(TOGGLE_KEY_ESP)) {
			toggleESP *= -1
			wait = 1;
		}
		if (keyPressed(TOGGLE_KEY_BUNNYHOP)) {
			toggleBunnyHop *= -1
			wait = 1;
		}
		if (keyPressed(TOGGLE_KEY_TRIGGER)) {
			toggleTrigger *= -1
			wait = 1;
		}
		if (keyPressed(TOGGLE_KEY_FLASH)) {
			toggleFlash *= -1
			wait = 1;
		}
		if (wait == 1)
		{
			Thread.sleep(250)
			wait = -1;
		}
	}
	return@every
}