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
package assets.rivalrebels.common.block.trap;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;

public class BlockRadioactiveSand extends Block
{
	public BlockRadioactiveSand()
	{
		super(Material.sand);
	}
	
	@Override
	public void onEntityWalking(World world, int par2, int par3, int par4, Entity entity)
	{
		if (world.rand.nextInt(2) == 0)
		{
			entity.attackEntityFrom(RivalRebelsDamageSource.radioactivepoisoning, world.rand.nextInt(2));
		}
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		world.spawnParticle("reddust", x + random.nextFloat(), y + 1.1 + random.nextFloat() * 0.1, z + random.nextFloat(), 0.3F, 6F, 0.5F);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		return Blocks.sand.getIcon(side, meta);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		
	}
}