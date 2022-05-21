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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityRoddiskLeader;
import assets.rivalrebels.common.entity.EntityRoddiskOfficer;
import assets.rivalrebels.common.entity.EntityRoddiskRebel;
import assets.rivalrebels.common.entity.EntityRoddiskRegular;
import assets.rivalrebels.common.explosion.Explosion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRemoteCharge extends Block
{
	public BlockRemoteCharge()
	{
		super(Material.cloth);
		setTickRandomly(true);
		float f = 0.0625F;
		float f1 = (1 + 0 * 2) / 16F;
		float f2 = 0.5F;
		setBlockBounds(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
	}
	
	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}
	
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int i = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float f = 0.0625F;
		float f1 = (1 + i * 2) / 16F;
		float f2 = 0.5F;
		setBlockBounds(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
	}
	
	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		int i = par1World.getBlockMetadata(par2, par3, par4);
		float f = 0.0625F;
		float f1 = (1 + i * 2) / 16F;
		float f2 = 0.5F;
		return AxisAlignedBB.getBoundingBox(par2 + f1, par3, par4 + f, (par2 + 1) - f, (par3 + f2) - f, (par4 + 1) - f);
	}
	
	/**
	 * How many world ticks before ticking
	 */
	@Override
	public int tickRate(World par1World)
	{
		return 1;
	}
	
	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		int i = par1World.getBlockMetadata(par2, par3, par4);
		float f = 0.0625F;
		float f1 = (1 + i * 2) / 16F;
		float f2 = 0.5F;
		return AxisAlignedBB.getBoundingBox(par2 + f1, par3, par4 + f, (par2 + 1) - f, par3 + f2, (par4 + 1) - f);
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
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two adjacent blocks and also whether the player can attach torches, redstone wire,
	 * etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int l)
	{
		explode(world, x, y, z);
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, net.minecraft.world.Explosion explosion)
	{
		explode(world, x, y, z);
	}
	
	public boolean	boom	= false;
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (world.getBlock(x + 1, y, z) == Blocks.fire || world.getBlock(x - 1, y, z) == Blocks.fire || world.getBlock(x, y + 1, z) == Blocks.fire || world.getBlock(x, y - 1, z) == Blocks.fire || world.getBlock(x, y, z + 1) == Blocks.fire || world.getBlock(x, y, z - 1) == Blocks.fire)
		{
			explode(world, x, y, z);
		}
		
		if (boom)
		{
			explode(world, x, y, z);
			boom = false;
		}
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityRoddiskRegular || entity instanceof EntityRoddiskRebel || entity instanceof EntityRoddiskOfficer || entity instanceof EntityRoddiskLeader)
		{
			explode(world, x, y, z);
		}
	}
	
	public static void explode(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.air);
		new Explosion(world, x + 0.5f, y + 0.5f, z + 0.5f, RivalRebels.chargeExplodeSize, false, false, RivalRebelsDamageSource.charge);
		RivalRebelsSoundPlayer.playSound(world, 22, 0, x, y, z, 1f, 0.3f);
	}
	
	@SideOnly(Side.CLIENT)
	IIcon	icon1;
	@SideOnly(Side.CLIENT)
	IIcon	icon2;
	@SideOnly(Side.CLIENT)
	IIcon	icon3;
	@SideOnly(Side.CLIENT)
	IIcon	icon4;
	@SideOnly(Side.CLIENT)
	IIcon	icon5;
	@SideOnly(Side.CLIENT)
	IIcon	icon6;
	
	@SideOnly(Side.CLIENT)
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		if (side == 0) return icon1;
		if (side == 1) return icon2;
		if (side == 2) return icon3;
		if (side == 3) return icon4;
		if (side == 4) return icon5;
		if (side == 5) return icon6;
		return icon1;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		icon1 = iconregister.registerIcon("RivalRebels:ag"); // BOTTOM
		icon2 = iconregister.registerIcon("RivalRebels:ag"); // TOP
		icon3 = iconregister.registerIcon("RivalRebels:af"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:af"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:af"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:af"); // SIDE E
	}
	
	/**
	 * Called when the falling block entity for this block hits the ground and turns back into a block
	 */
	public void onFinishFalling(World par1World, int par2, int par3, int par4, int par5)
	{
		explode(par1World, par2, par3, par4);
	}
}