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
package assets.rivalrebels.common.block.machine;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockForceField extends Block
{
	public BlockForceField()
	{
		super(Material.glass);
		setBlockBounds(0.0F, 0.0F, 0.4375f, 1.0F, 1.0F, 1.0F - 0.4375f);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}
	
	public int idDropped(int i, Random random)
	{
		return 0;
	}
	
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float var6 = 0.4375f;
		
		if (var5 == 4)
		{
			setBlockBounds(0.0F, 0.0F, var6, 1.0F, 1.0F, 1.0F - var6);
		}
		
		if (var5 == 5)
		{
			setBlockBounds(0.0F, 0.0F, var6, 1.0F, 1.0F, 1.0F - var6);
		}
		
		if (var5 == 2)
		{
			setBlockBounds(var6, 0.0F, 0.0F, 1.0F - var6, 1.0F, 1.0F);
		}
		
		if (var5 == 3)
		{
			setBlockBounds(var6, 0.0F, 0.0F, 1.0F - var6, 1.0F, 1.0F);
		}
	}
	
	// @Override
	// public void onBlockAdded(World world, int x, int y, int z)
	// {
	// world.scheduleBlockUpdate(x, y, z, blockID, world.rand.nextInt(10)+10);
	// }
	//
	// @Override
	// public void updateTick(World par1World, int x, int y, int z, Random par5Random)
	// {
	// if (par1World.rand.nextInt(20) == 0) par1World.setBlock(x, y, z, 0);
	// else par1World.scheduleBlockUpdate(x, y, z, blockID, par1World.rand.nextInt(10)+10);
	// }
	
	/**
	 * Sets the block's bounds for rendering it as an item
	 */
	@Override
	public void setBlockBoundsForItemRender()
	{
		setBlockBounds(0.0F, 0.0F, 0.4375f, 1.0F, 1.0F, 1.0F - 0.4375f);
	}
	
	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		int var5 = par1World.getBlockMetadata(par2, par3, par4);
		float var6 = 0.4375f;
		
		if (var5 == 4)
		{
			setBlockBounds(0.0F, 0.0F, var6, 1.0F, 1.0F, 1.0F - var6);
		}
		
		if (var5 == 5)
		{
			setBlockBounds(0.0F, 0.0F, var6, 1.0F, 1.0F, 1.0F - var6);
		}
		
		if (var5 == 2)
		{
			setBlockBounds(var6, 0.0F, 0.0F, 1.0F - var6, 1.0F, 1.0F);
		}
		
		if (var5 == 3)
		{
			setBlockBounds(var6, 0.0F, 0.0F, 1.0F - var6, 1.0F, 1.0F);
		}
		
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		int var5 = par1World.getBlockMetadata(par2, par3, par4);
		float var6 = 0.4375f;
		
		if (var5 == 4)
		{
			setBlockBounds(0.0F, 0.0F, var6, 1.0F, 1.0F, 1.0F - var6);
		}
		
		if (var5 == 5)
		{
			setBlockBounds(0.0F, 0.0F, var6, 1.0F, 1.0F, 1.0F - var6);
		}
		
		if (var5 == 2)
		{
			setBlockBounds(var6, 0.0F, 0.0F, 1.0F - var6, 1.0F, 1.0F);
		}
		
		if (var5 == 3)
		{
			setBlockBounds(var6, 0.0F, 0.0F, 1.0F - var6, 1.0F, 1.0F);
		}
		
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
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
	
	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return RivalRebels.goodRender ? -1 : 0;
	}
	
	@SideOnly(Side.CLIENT)
	IIcon	icon;
	
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		return icon;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		icon = iconregister.registerIcon("RivalRebels:di");
	}
}