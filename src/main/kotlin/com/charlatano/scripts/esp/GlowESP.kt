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

package com.charlatano.scripts.esp

import com.charlatano.game.CSGO.csgoEXE
import com.charlatano.game.Color
import com.charlatano.game.entities
import com.charlatano.game.forEntities
import com.charlatano.game.entity.*
import com.charlatano.game.me
import com.charlatano.settings.*
import com.charlatano.utils.every
import org.jire.arrowhead.keyPressed
import com.charlatano.scripts.*

public var hp = 0

internal fun glowEsp() = every(4) {
	if (!GLOW_ESP || toggleESP < 0) return@every
	
	forEntities {
		val entity = it.entity
		if (entity <= 0 || me == entity) return@forEntities
		
		val glowAddress = it.glowAddress
		if (glowAddress <= 0) return@forEntities
		
		when (it.type) {
			EntityType.CCSPlayer -> {
				if (entity.dead() || (!SHOW_DORMANT && entity.dormant())) return@forEntities
				
				var hp = (entity.health() * 2 + 5).toInt()
				val team = me.team() == entity.team()
				
				if (SHOW_ENEMIES && !team) {
					if (!SPECIAL_GLOW_ESP_COLORS) {
						glowAddress.glow(ENEMY_COLOR)
						entity.chams(ENEMY_COLOR)
					}
					else {
						if (entity.spotted()) {
							glowAddress.glow(Color(hp, 224, 255-hp))
							entity.chams(Color(hp, 224, 255-hp))
						}
						if (entity.spottedNoMask()) {
							glowAddress.glow(Color(hp, 128, 255-hp))
							entity.chams(Color(hp, 128, 255-hp))
						}
						else {
							glowAddress.glow(Color(hp, 0, 255-hp))
							entity.chams(Color(hp, 0, 255-hp))
						}
					}
				} else if (SHOW_TEAM && team) {
					if (!SPECIAL_GLOW_ESP_COLORS) {
						glowAddress.glow(ENEMY_COLOR)
						entity.chams(ENEMY_COLOR)
					}
					else {
						glowAddress.glow(Color(255-hp, 255, 255-hp))
						entity.chams(Color(255-hp, 255, 255-hp))
					}
				}
			}
			EntityType.CPlantedC4, EntityType.CC4 -> if (SHOW_BOMB) {
				glowAddress.glow(BOMB_COLOR)
				entity.chams(BOMB_COLOR)
			}
			else ->
				if (SHOW_WEAPONS && it.type.weapon) glowAddress.glow(WEAPON_COLOR)
				else if (SHOW_GRENADES && it.type.grenade) glowAddress.glow(GRENADE_COLOR)
		}
	}
}

private fun Entity.glow(color: Color) {
	csgoEXE[this + 0x4] = color.red / 255F
	csgoEXE[this + 0x8] = color.green / 255F
	csgoEXE[this + 0xC] = color.blue / 255F
	csgoEXE[this + 0x10] = color.alpha.toFloat()
	csgoEXE[this + 0x24] = true
}

private fun Entity.chams(color: Color) {
	if (COLOR_MODELS) {
		csgoEXE[this + 0x70] = color.red.toByte()
		csgoEXE[this + 0x71] = color.green.toByte()
		csgoEXE[this + 0x72] = color.blue.toByte()
		csgoEXE[this + 0x73] = color.alpha.toByte()
	}
}