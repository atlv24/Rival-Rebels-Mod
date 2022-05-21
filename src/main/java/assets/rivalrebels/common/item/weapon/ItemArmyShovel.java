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
package assets.rivalrebels.common.item.weapon;

import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import assets.rivalrebels.RivalRebels;

public class ItemArmyShovel extends ItemTool
{
	private static HashSet<Block>	blocksEffectiveAgainst;
	
	public ItemArmyShovel()
	{
		super(1, ToolMaterial.IRON, blocksEffectiveAgainst);
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public boolean isDamageable()
	{
		return false;
	}
	
	@Override
	public boolean canHarvestBlock(Block par1Block, ItemStack is)
	{
		return blocksEffectiveAgainst.contains(par1Block);
	}
	
	static
	{
		blocksEffectiveAgainst = new HashSet<Block>();
		blocksEffectiveAgainst.add(Blocks.stone);
		blocksEffectiveAgainst.add(Blocks.grass);
		blocksEffectiveAgainst.add(Blocks.dirt);
		blocksEffectiveAgainst.add(Blocks.cobblestone);
		blocksEffectiveAgainst.add(Blocks.sand);
		blocksEffectiveAgainst.add(Blocks.gravel);
		blocksEffectiveAgainst.add(Blocks.snow);
		blocksEffectiveAgainst.add(Blocks.snow_layer);
		blocksEffectiveAgainst.add(Blocks.clay);
		blocksEffectiveAgainst.add(Blocks.farmland);
		blocksEffectiveAgainst.add(Blocks.soul_sand);
		blocksEffectiveAgainst.add(Blocks.mycelium);
		blocksEffectiveAgainst.add(Blocks.chest);
		blocksEffectiveAgainst.add(Blocks.redstone_ore);
		blocksEffectiveAgainst.add(Blocks.iron_ore);
		blocksEffectiveAgainst.add(Blocks.gold_ore);
		blocksEffectiveAgainst.add(Blocks.lapis_ore);
		blocksEffectiveAgainst.add(Blocks.diamond_ore);
		blocksEffectiveAgainst.add(Blocks.emerald_ore);
		blocksEffectiveAgainst.add(Blocks.redstone_block);
		blocksEffectiveAgainst.add(Blocks.iron_block);
		blocksEffectiveAgainst.add(Blocks.gold_block);
		blocksEffectiveAgainst.add(Blocks.lapis_block);
		blocksEffectiveAgainst.add(Blocks.diamond_block);
		blocksEffectiveAgainst.add(Blocks.emerald_block);
		blocksEffectiveAgainst.add(RivalRebels.barricade);
		blocksEffectiveAgainst.add(RivalRebels.reactive);
		blocksEffectiveAgainst.add(RivalRebels.conduit);
		blocksEffectiveAgainst.add(RivalRebels.tower);
		blocksEffectiveAgainst.add(RivalRebels.steel);
		blocksEffectiveAgainst.add(RivalRebels.rhodesactivator);
		blocksEffectiveAgainst.add(RivalRebels.camo1);
		blocksEffectiveAgainst.add(RivalRebels.camo2);
		blocksEffectiveAgainst.add(RivalRebels.camo3);
		blocksEffectiveAgainst.add(RivalRebels.jump);
		blocksEffectiveAgainst.add(RivalRebels.landmine);
		blocksEffectiveAgainst.add(RivalRebels.alandmine);
		blocksEffectiveAgainst.add(RivalRebels.quicksand);
		blocksEffectiveAgainst.add(RivalRebels.aquicksand);
		blocksEffectiveAgainst.add(RivalRebels.mario);
		blocksEffectiveAgainst.add(RivalRebels.amario);
		blocksEffectiveAgainst.add(RivalRebels.loader);
		blocksEffectiveAgainst.add(RivalRebels.reactor);
		blocksEffectiveAgainst.add(RivalRebels.radioactivedirt);
		blocksEffectiveAgainst.add(RivalRebels.radioactivesand);
		blocksEffectiveAgainst.add(RivalRebels.petrifiedstone1);
		blocksEffectiveAgainst.add(RivalRebels.petrifiedstone2);
		blocksEffectiveAgainst.add(RivalRebels.petrifiedstone3);
		blocksEffectiveAgainst.add(RivalRebels.petrifiedstone4);
		blocksEffectiveAgainst.add(RivalRebels.petrifiedwood);
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:aw");
	}
}