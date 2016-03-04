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
			"b" },
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
			"r" }
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
/*
 * { "0.0": {"category": "ambient","sounds": [{"name": "aa/a1"},"aa/a1","aa/a2"]}, "0.1": {"category": "ambient","sounds": [{"name": "aa/b1"},"aa/b1","aa/b2","aa/b3"]}, "0.2": {"category":
 * "ambient","sounds": [{"name": "aa/c"},"aa/c"]}, "0.3": {"category": "ambient","sounds": [{"name": "aa/d1"},"aa/d1","aa/d2","aa/d3"]}, "1.0": {"category": "ambient","sounds": [{"name":
 * "ab/a1"},"ab/a1","ab/a2"]}, "2.0": {"category": "ambient","sounds": [{"name": "ac/a"},"ac/a"]}, "2.1": {"category": "ambient","sounds": [{"name": "ac/b1"},"ac/b1","ac/b2","ac/b3"]}, "2.2":
 * {"category": "ambient","sounds": [{"name": "ac/c"},"ac/c"]}, "2.3": {"category": "ambient","sounds": [{"name": "ac/d"},"ac/d"]}, "3.0": {"category": "ambient","sounds": [{"name":
 * "ad/a1"},"ad/a1","ad/a2","ad/a3","ad/a4"]}, "4.0": {"category": "ambient","sounds": [{"name": "ae/a1"},"ae/a1","ae/a2","ae/a3"]}, "4.1": {"category": "ambient","sounds": [{"name":
 * "ae/b1"},"ae/b1","ae/b2","ae/b3","ae/b4"]}, "4.2": {"category": "ambient","sounds": [{"name": "ae/c1"},"ae/c1","ae/c2","ae/c3"]}, "4.3": {"category": "ambient","sounds": [{"name":
 * "ae/d1"},"ae/d1","ae/d2","ae/d3"]}, "4.4": {"category": "ambient","sounds": [{"name": "ae/e"},"ae/e"]}, "5.0": {"category": "ambient","sounds": [{"name": "af/a"},"af/a"]}, "5.1": {"category":
 * "ambient","sounds": [{"name": "af/b1"},"af/b1","af/b2","af/b3","af/b4"]}, "5.2": {"category": "ambient","sounds": [{"name": "af/c"},"af/c"]}, "6.0": {"category": "ambient","sounds": [{"name":
 * "af/a/a"}]}, "6.1": {"category": "ambient","sounds": [{"name": "af/a/b"}]}, "6.2": {"category": "ambient","sounds": [{"name": "af/a/c"}]}, "6.3": {"category": "ambient","sounds": [{"name":
 * "af/a/d"}]}, "7.0": {"category": "ambient","sounds": [{"name": "af/b/a"}]}, "7.1": {"category": "ambient","sounds": [{"name": "af/b/b"}]}, "7.2": {"category": "ambient","sounds": [{"name":
 * "af/b/c"}]}, "7.3": {"category": "ambient","sounds": [{"name": "af/b/d"}]}, "8.0": {"category": "ambient","sounds": [{"name": "ag/a1"},"ag/a1","ag/a2"]}, "8.1": {"category": "ambient","sounds":
 * [{"name": "ag/b"}]}, "9.0": {"category": "ambient","sounds": [{"name": "ah/a1"},"ah/a1","ah/a2"]}, "9.1": {"category": "ambient","sounds": [{"name": "ah/b"}]}, "9.2": {"category":
 * "ambient","sounds": [{"name": "ah/c"}]}, "9.3": {"category": "ambient","sounds": [{"name": "ah/d1"},"ah/d1","ah/d2","ah/d3"]}, "10.0": {"category": "ambient","sounds": [{"name": "ai/a"}]}, "10.1":
 * {"category": "ambient","sounds": [{"name": "ai/b"}]}, "10.2": {"category": "ambient","sounds": [{"name": "ai/c"}]}, "10.3": {"category": "ambient","sounds": [{"name": "ai/d"}]}, "10.4":
 * {"category": "ambient","sounds": [{"name": "ai/e"}]}, "10.5": {"category": "ambient","sounds": [{"name": "ai/f"}]}, "10.6": {"category": "ambient","sounds": [{"name": "ai/g"}]}, "10.7":
 * {"category": "ambient","sounds": [{"name": "ai/h"}]}, "10.8": {"category": "ambient","sounds": [{"name": "ai/i"}]}, "11.0": {"category": "ambient","sounds": [{"name": "aj/a"}]}, "11.1":
 * {"category": "ambient","sounds": [{"name": "aj/b"}]}, "12.0": {"category": "ambient","sounds": [{"name": "ak/a"}]}, "12.1": {"category": "ambient","sounds": [{"name": "ak/b"}]}, "12.2":
 * {"category": "ambient","sounds": [{"name": "ak/c"}]}, "13.0": {"category": "ambient","sounds": [{"name": "al/a"}]}, "13.1": {"category": "ambient","sounds": [{"name": "al/b"}]}, "14.0":
 * {"category": "ambient","sounds": [{"name": "am/a"}]}, "15.0": {"category": "ambient","sounds": [{"name": "an/a1"},"an/a1","an/a2"]}, "15.1": {"category": "ambient","sounds": [{"name":
 * "an/b1"},"an/b1","an/b2"]}, "16.0": {"category": "ambient","sounds": [{"name": "ao/a"}]}, "16.1": {"category": "ambient","sounds": [{"name": "ao/b"}]}, "16.2": {"category": "ambient","sounds":
 * [{"name": "ao/c"}]}, "17.0": {"category": "ambient","sounds": [{"name": "ap/a1"},"ap/a1","ap/a2","ap/a3","ap/a4"]}, "18.0": {"category": "ambient","sounds": [{"name": "aq/a"}]}, "18.1":
 * {"category": "ambient","sounds": [{"name": "aq/b"}]}, "19.0": {"category": "ambient","sounds": [{"name": "ar/a"}]}, "19.1": {"category": "ambient","sounds": [{"name": "ar/b"}]}, "19.2":
 * {"category": "ambient","sounds": [{"name": "ar/c"}]}, "20.0": {"category": "ambient","sounds": [{"name": "as/a1"},"as/a1","as/a2"]}, "21.0": {"category": "ambient","sounds": [{"name": "at/a"}]},
 * "21.1": {"category": "ambient","sounds": [{"name": "at/b"}]}, "21.2": {"category": "ambient","sounds": [{"name": "at/c"}]}, "21.3": {"category": "ambient","sounds": [{"name": "at/d"}]}, "22.0":
 * {"category": "ambient","sounds": [{"name": "au/a"}]}, "22.1": {"category": "ambient","sounds": [{"name": "au/b1"},"au/b1","au/b2"]}, "22.2": {"category": "ambient","sounds": [{"name": "au/c"}]},
 * "22.3": {"category": "ambient","sounds": [{"name": "au/d"}]}, "23.0": {"category": "ambient","sounds": [{"name": "av/a1"},"av/a1","av/a2"]}, "23.1": {"category": "ambient","sounds": [{"name":
 * "av/b"}]}, "23.2": {"category": "ambient","sounds": [{"name": "av/c1"},"av/c1","av/c2"]}, "23.3": {"category": "ambient","sounds": [{"name": "av/d"}]}, "23.4": {"category": "ambient","sounds":
 * [{"name": "av/e"}]}, "24.0": {"category": "ambient","sounds": [{"name": "aw/a"}]}, "24.1": {"category": "ambient","sounds": [{"name": "aw/b"}]}, "24.2": {"category": "ambient","sounds": [{"name":
 * "aw/c"}]}, "24.3": {"category": "ambient","sounds": [{"name": "aw/d1"},"aw/d1","aw/d2"]}, "24.4": {"category": "ambient","sounds": [{"name": "aw/e"}]}, "25.0": {"category": "ambient","sounds":
 * [{"name": "ax/a"}]}, "25.1": {"category": "ambient","sounds": [{"name": "ax/b1"},"ax/b1","ax/b2","ax/b3","ax/b4"]}, "26.0": {"category": "ambient","sounds": [{"name": "ay/a1"},"ay/a1","ay/a2"]},
 * "26.1": {"category": "ambient","sounds": [{"name": "ay/b"}]}, "26.2": {"category": "ambient","sounds": [{"name": "ay/c"}]}, "27.0": {"category": "ambient","sounds": [{"name":
 * "az/a1"},"az/a1","az/a2"]}, "28.0": {"category": "ambient","sounds": [{"name": "ba/a1"},"ba/a1","ba/a2"]}, "28.1": {"category": "ambient","sounds": [{"name": "ba/b"}]}, "28.2": {"category":
 * "ambient","sounds": [{"name": "ba/c"}]}, "28.3": {"category": "ambient","sounds": [{"name": "ba/d"}]}, "28.4": {"category": "ambient","sounds": [{"name": "ba/e"}]}, "28.5": {"category":
 * "ambient","sounds": [{"name": "ba/f"}]}, "28.6": {"category": "ambient","sounds": [{"name": "ba/g"}]}, "28.7": {"category": "ambient","sounds": [{"name": "ba/h"}]}, "28.8": {"category":
 * "ambient","sounds": [{"name": "ba/i"}]}, "28.9": {"category": "ambient","sounds": [{"name": "ba/j"}]}, "28.10": {"category": "ambient","sounds": [{"name": "ba/k"}]}, "28.11": {"category":
 * "ambient","sounds": [{"name": "ba/l"}]}, "28.12": {"category": "ambient","sounds": [{"name": "ba/m"}]}, "28.13": {"category": "ambient","sounds": [{"name": "ba/n"}]}, "28.14": {"category":
 * "ambient","sounds": [{"name": "ba/o"}]}, "28.15": {"category": "ambient","sounds": [{"name": "ba/p"}]}, "28.16": {"category": "ambient","sounds": [{"name": "ba/q"}]}, "28.17": {"category":
 * "ambient","sounds": [{"name": "ba/r"}]} }
 */