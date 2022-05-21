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
package assets.rivalrebels.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockForceShield extends Block
{
	public BlockForceShield()
	{
		super(Material.iron);
	}
	
	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}
	
	boolean	Destroy	= false;
	
	@Override
	public void breakBlock(World world, int i, int j, int k, Block l, int s)
	{
		Block id = world.getBlock(i, j, k);
		if (!Destroy && id != RivalRebels.fshield && id != RivalRebels.omegaobj && id != RivalRebels.sigmaobj && id != RivalRebels.reactive)
		{
			world.setBlock(i, j, k, this);
		}
		Destroy = false;
	}
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int m, EntityPlayer player)
	{
		if (!world.isRemote && player.capabilities.isCreativeMode && player.isSneaking())
		{
			Destroy = true;
			world.setBlock(x, y, z, Blocks.air);
		}
		else
		{
			Destroy = false;
			world.setBlock(x, y, z, this);
		}
	}
	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two adjacent blocks and also whether the player can attach torches, redstone wire,
	 * etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public int getMobilityFlag()
	{
		return 2;
	}
	
	@SideOnly(Side.CLIENT)
	IIcon	icon1;
	IIcon	icon2;
	
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		if (side == 0 || side == 1) return icon2;
		return icon1;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		icon1 = iconregister.registerIcon("RivalRebels:ao");
		icon2 = iconregister.registerIcon("RivalRebels:ap");
	}
}