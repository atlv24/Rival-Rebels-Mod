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

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import assets.rivalrebels.RivalRebels;

public class BlackList
{
	public static Block[]	tesla			=
											{
											RivalRebels.omegaobj,
											RivalRebels.sigmaobj,
											RivalRebels.fshield,
											Blocks.bedrock,
											Blocks.water,
											Blocks.flowing_water,
											Blocks.lava,
											Blocks.flowing_lava,
											Blocks.glass,
											Blocks.obsidian,
											Blocks.glass_pane,
											Blocks.stained_glass,
											Blocks.stained_glass_pane,
											RivalRebels.nuclearBomb,
											RivalRebels.tsarbombablock,
											RivalRebels.loader,
											RivalRebels.reactor,
											RivalRebels.forcefieldnode,
											RivalRebels.conduit,
											RivalRebels.forcefield,
											RivalRebels.meltdown,
											RivalRebels.ffreciever,
											};
	public static Block[]	explosion		=
											{
											RivalRebels.fshield,
											RivalRebels.nuclearBomb,
											RivalRebels.tsarbombablock,
											RivalRebels.omegaobj,
											RivalRebels.sigmaobj,
											RivalRebels.forcefieldnode,
											RivalRebels.reactor,
											RivalRebels.loader,
											RivalRebels.meltdown,
											RivalRebels.forcefield,
											RivalRebels.ffreciever,
											};
	public static Block[]	plasmaExplosion	=
											{
											RivalRebels.omegaobj,
											RivalRebels.sigmaobj,
											RivalRebels.tsarbombablock,
											RivalRebels.nuclearBomb,
											RivalRebels.reactor,
											RivalRebels.loader,
											RivalRebels.controller,
											RivalRebels.meltdown,
											RivalRebels.forcefield,
											RivalRebels.ffreciever,
											};
	public static Block[]	autobuild		=
											{
											RivalRebels.fshield,
											RivalRebels.nuclearBomb,
											RivalRebels.tsarbombablock,
											RivalRebels.omegaobj,
											RivalRebels.sigmaobj,
											RivalRebels.forcefieldnode,
											RivalRebels.reactive,
											RivalRebels.conduit,
											RivalRebels.reactor,
											RivalRebels.loader,
											RivalRebels.meltdown,
											RivalRebels.forcefield,
											RivalRebels.ffreciever,
											Blocks.bedrock,
											};
	
	public static boolean tesla(Block block)
	{
		for (int i = 0; i < tesla.length; i++)
		{
			if (tesla[i] == block) return true;
		}
		return false;
	}
	
	public static boolean explosion(Block block)
	{
		for (int i = 0; i < explosion.length; i++)
		{
			if (explosion[i] == block) return true;
		}
		return false;
	}
	
	public static boolean plasmaExplosion(Block block)
	{
		for (int i = 0; i < plasmaExplosion.length; i++)
		{
			if (plasmaExplosion[i] == block) return true;
		}
		return false;
	}
	
	public static boolean autobuild(Block block)
	{
		for (int i = 0; i < autobuild.length; i++)
		{
			if (autobuild[i] == block) return true;
		}
		return false;
	}
}