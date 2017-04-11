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

@file:JvmName("Charlatano")

package com.charlatano

import com.charlatano.game.CSGO
import com.charlatano.overlay.Overlay
import com.charlatano.scripts.*
import com.charlatano.scripts.aim.safeAim
import com.charlatano.scripts.aim.writeAim
import com.charlatano.scripts.esp.esp
import com.charlatano.settings.*
import com.charlatano.utils.Dojo
import java.io.File
import java.io.FileReader
import java.util.*

const val SETTINGS_DIRECTORY = "settings"
const val SETTINGS_RAGE_DIRECTORY = "rage"

fun main(args: Array<String>) {
	System.setProperty("kotlin.compiler.jar", "kotlin-compiler.jar")
	
	loadSettings()
	
	CSGO.initalize()
	
	bunnyHop()
	rcs()
	esp()
	writeAim()
	safeAim()
	boneTrigger()
	reducedFlash()
	bombTimer()
	Toggles()
	
	Thread.sleep(10_000) // wait a bit to catch everything
	System.gc() // then cleanup
	
	val scanner = Scanner(System.`in`)
	while (!Thread.interrupted()) {
		when (scanner.nextLine()) {
			"exit", "quit", "e", "q" -> System.exit(0)
			"reload", "r" -> loadSettings()
			"rage" -> loadRage()
			"reset" -> resetToggles()
		}
	}
}

private fun loadSettings() {
	File(SETTINGS_DIRECTORY).listFiles().forEach {
		FileReader(it).use {
			Dojo.script(it
					.readLines()
					.joinToString("\n"))
		}
	}
	
	toggleRage = -1
	
	val needsOverlay = ENABLE_BOMB_TIMER or (ENABLE_ESP and (SKELETON_ESP or BOX_ESP))
	if (Overlay.hwnd == null && needsOverlay) Overlay.open()
}

private fun loadRage() {
	File(SETTINGS_RAGE_DIRECTORY).listFiles().forEach {
		FileReader(it).use {
			Dojo.script(it
					.readLines()
					.joinToString("\n"))
		}
	}
	
	toggleRage = 1
	
	val needsOverlay = ENABLE_BOMB_TIMER or (ENABLE_ESP and (SKELETON_ESP or BOX_ESP))
	if (Overlay.hwnd == null && needsOverlay) Overlay.open()
}

private fun resetToggles() {
	toggleAIM = 1
	toggleRCS = 1
	toggleESP = 1
	toggleBunnyHop = 1
	toggleRage = -1
	toggleTrigger = -1
	toggleFlash = -1
}