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
import net.minecraft.block.BlockFalling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityRoddiskLeader;
import assets.rivalrebels.common.entity.EntityRoddiskOfficer;
import assets.rivalrebels.common.entity.EntityRoddiskRebel;
import assets.rivalrebels.common.entity.EntityRoddiskRegular;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLandMine extends BlockFalling
{
	public BlockLandMine()
	{
		super();
	}
	
	@Override
	public Item getItemDropped(int i, Random r, int j)
	{
		return Item.getItemFromBlock(RivalRebels.alandmine);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		if (this == RivalRebels.landmine)
		{
			return Blocks.grass.getBlockColor();
		}
		return 0xFFFFFF;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Returns the color this block should be rendered. Used by leaves.
	 */
	public int getRenderColor(int par1)
	{
		return Blocks.grass.getRenderColor(par1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
	 * when first determining what to render.
	 */
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		if (this == RivalRebels.landmine)
		{
			return Blocks.grass.colorMultiplier(par1IBlockAccess, par2, par3, par4);
		}
		return 0xFFFFFF;
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par1World.getBlockMetadata(par2, par3, par4) == 1)
		{
			par1World.setBlock(par2, par3, par4, Blocks.air);
			if (!par1World.isRemote) par1World.createExplosion(null, par2, par3 + 2.5f, par4, RivalRebels.landmineExplodeSize, true);
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		float f = 0.01F;
		return AxisAlignedBB.getBoundingBox(par2, par3, par4, par2 + 1, par3 + 1 - f, par4 + 1);
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return AxisAlignedBB.getBoundingBox(par2, par3, par4, par2 + 1, par3 + 1, par4 + 1);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int par2, int par3, int par4, Entity entity)
	{
		if (entity instanceof EntityPlayer || entity instanceof EntityAnimal || entity instanceof EntityMob || entity instanceof EntityRoddiskRegular || entity instanceof EntityRoddiskRebel || entity instanceof EntityRoddiskOfficer || entity instanceof EntityRoddiskLeader)
		{
			world.setBlockMetadataWithNotify(par2, par3, par4, 1, 6);
			world.scheduleBlockUpdate(par2, par3, par4, this, 5);
			RivalRebelsSoundPlayer.playSound(world, 11, 1, par2, par3, par4, 3, 2);
		}
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, net.minecraft.world.Explosion explosion)
	{
		if (!world.isRemote) world.createExplosion(null, x, y + 2.5f, z, RivalRebels.landmineExplodeSize, true);
	}
	
	@Override
	public final IIcon getIcon(IBlockAccess world, int x, int y, int z, int s)
	{
		if (this == RivalRebels.landmine) return Blocks.grass.getIcon(world, x, y, z, s);
		Block[] n = new Block[6];
		n[0] = world.getBlock(x + 1, y, z);
		n[1] = world.getBlock(x - 1, y, z);
		n[2] = world.getBlock(x, y + 1, z);
		n[3] = world.getBlock(x, y - 1, z);
		n[4] = world.getBlock(x, y, z + 1);
		n[5] = world.getBlock(x, y, z - 1);
		
		int popularity1 = 0;
		int popularity2 = 0;
		Block mode = Blocks.grass;
		Block array_item = null;
		for (int i = 0; i < 6; i++)
		{
			array_item = n[i];
			if (array_item == null || !array_item.isOpaqueCube() || array_item == RivalRebels.landmine || array_item == RivalRebels.alandmine || array_item == RivalRebels.mario || array_item == RivalRebels.amario || array_item == RivalRebels.quicksand || array_item == RivalRebels.aquicksand) continue;
			for (int j = 0; j < n.length; j++)
			{
				if (array_item == n[j]) popularity1++;
				if (popularity1 >= popularity2)
				{
					mode = array_item;
					popularity2 = popularity1;
				}
			}
			popularity1 = 0;
		}
		return mode.getIcon(world, x, y, z, s);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		Block[] n = new Block[6];
		n[0] = world.getBlock(x + 1, y, z);
		n[1] = world.getBlock(x - 1, y, z);
		n[2] = world.getBlock(x, y + 1, z);
		n[3] = world.getBlock(x, y - 1, z);
		n[4] = world.getBlock(x, y, z + 1);
		n[5] = world.getBlock(x, y, z - 1);
		
		int popularity1 = 0;
		int popularity2 = 0;
		Block mode = Blocks.grass;
		Block array_item = null;
		for (int i = 0; i < 6; i++)
		{
			array_item = n[i];
			if (array_item == null || !array_item.isOpaqueCube() || array_item == RivalRebels.landmine || array_item == RivalRebels.alandmine || array_item == RivalRebels.mario || array_item == RivalRebels.amario || array_item == RivalRebels.quicksand || array_item == RivalRebels.aquicksand) continue;
			for (int j = 0; j < n.length; j++)
			{
				if (array_item == n[j]) popularity1++;
				if (popularity1 >= popularity2)
				{
					mode = array_item;
					popularity2 = popularity1;
				}
			}
			popularity1 = 0;
		}
		if (mode == Blocks.grass) world.setBlock(x, y, z, RivalRebels.landmine);
	}
	
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		return Blocks.grass.getIcon(side, meta);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player)
    {
        return new ItemStack(RivalRebels.alandmine);
    }
	
	/**
	 * Called when the falling block entity for this block hits the ground and turns back into a block
	 */
	public void onFinishFalling(World par1World, int x, int y, int z, int par5)
	{
		if (!par1World.isRemote) par1World.createExplosion(null, x, y + 2.5f, z, RivalRebels.landmineExplodeSize, true);
		// new Explosion(par1World, x + 0.5, y + 2.5, z + 0.5, RivalRebels.landmineExplodeSize, false, false, RivalRebelsDamageSource.landmine, "landmine.explode");
	}
}