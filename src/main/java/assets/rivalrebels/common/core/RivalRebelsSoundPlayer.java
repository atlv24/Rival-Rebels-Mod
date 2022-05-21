/*******************************************************************************
 * Copyright (c) 2012, 2016 Rodol Phito.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Mozilla Public License Version 2.0
 * which accompanies this distribution, and is available at
 * https://www.mozilla.org/en-US/MPL/2.0/
 *
 * Rival Rebels Mod. All code, art, and design by Rodol Phito.
 *
 * http://RivalRebels.com/
 *******************************************************************************/
package assets.rivalrebels.common.core;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class RivalRebelsSoundPlayer
{
	static String[]		directory	=
									{
			// artillery 0
			"aa",
			// autobuild 1
			"ab",
			// blaster 2
			"ac",
			// crate 3
			"ad",
			// cuchillo 4
			"ae",
			// disk 5
			"af",
			// diskhigh 6
			"af.a",
			// disklow 7
			"af.b",
			// fire 8
			"ag",
			// grenade 9
			"ah",
			// gui 10
			"ai",
			// landmine 11
			"aj",
			// laptop 12
			"ak",
			// mendeleev13
			"al",
			// nuke 14
			"am",
			// pill 15
			"an",
			// plasma 16
			"ao",
			// pliers 17
			"ap",
			// precursor18
			"aq",
			// printer 19
			"ar",
			// quicksand20
			"as",
			// reactor 21
			"at",
			// remote 22
			"au",
			// rocket 23
			"av",
			// rod 24
			"aw",
			// tesla 25
			"ax",
			// timedbomb26
			"ay",
			// toaster 27
			"az",
			// voice 28
			"ba"
									};
	
	static String[][]	number		=
									{
									// artillery
			{ "a",
			"b",
			"c",
			"d" },
			// autobuild
			{ "a" },
			// blaster
			{ "a",
			"b",
			"c",
			"d" },
			// crate
			{ "a" },
			// cuchillo
			{ "a",
			"b",
			"c",
			"d",
			"e" },
			// disk
			{ "a",
			"b",
			"c" },
			// diskhigh
			{ "a",
			"b",
			"c",
			"d" },
			// disklow
			{ "a",
			"b",
			"c",
			"d" },
			// fire
			{ "a",
			"b",
			"c" },
			// grenade
			{ "a",
			"b",
			"c",
			"d" },
			// gui
			{ "a",
			"b",
			"c",
			"d",
			"e",
			"f",
			"g",
			"h",
			"i" },
			// landmine
			{ "a",
			"b" },
			// laptop
			{ "a",
			"b",
			"c" },
			// mendeleev
			{ "a",
			"b" },
			// nuke
			{ "a" },
			// pill
			{ "a",
			"b" },
			// plasma
			{ "a",
			"b",
			"c" },
			// pliers
			{ "a" },
			// precursor
			{ "a",
			"b" },
			// printer
			{ "a",
			"b",
			"c" },
			// quicksand
			{ "a" },
			// reactor
			{ "a",
			"b",
			"c",
			"d" },
			// remote
			{ "a",
			"b",
			"c",
			"d" },
			// rocket
			{ "a",
			"b",
			"c",
			"d",
			"e" },
			// rod
			{ "a",
			"b",
			"c",
			"d",
			"e" },
			// tesla
			{ "a",
			"b" },
			// timedbomb
			{ "a",
			"b",
			"c" },
			// toaster
			{ "a" },
			// voice
			{ "a",
			"b",
			"c",
			"d",
			"e",
			"f",
			"g",
			"h",
			"i",
			"j",
			"k",
			"l",
			"m",
			"n",
			"o",
			"p",
			"q",
			"r",
			"s"}
									};
	
	public static boolean playSound(World world, int dir, int num, double x, double y, double z, float volume, float pitch)
	{
		if (world != null && dir >= 0 && dir < directory.length && num >= 0 && num < number[dir].length)
		{
			String sound = "rivalrebels:" + dir + "." + num;
			world.playSoundEffect(x, y, z, sound, volume, pitch);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean playSound(World world, int dir, int num, double x, double y, double z, float volume)
	{
		return playSound(world, dir, num, x, y, z, volume, 1);
	}
	
	public static boolean playSound(World world, int dir, int num, double x, double y, double z)
	{
		return playSound(world, dir, num, x, y, z, 1, 1);
	}
	
	public static boolean playSound(Entity entity, int dir, int num, float volume, float pitch)
	{
		if (entity != null)
		{
			return playSound(entity.worldObj, dir, num, entity.posX, entity.posY, entity.posZ, volume, pitch);
		}
		else
		{
			return false;
		}
	}
	
	public static boolean playSound(Entity entity, int dir, int num, float volume)
	{
		return playSound(entity, dir, num, volume, 1);
	}
	
	public static boolean playSound(Entity entity, int dir, int num)
	{
		return playSound(entity, dir, num, 1, 1);
	}
}