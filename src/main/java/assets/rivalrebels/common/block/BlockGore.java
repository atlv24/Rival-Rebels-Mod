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

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.common.entity.EntityBlood;
import assets.rivalrebels.common.entity.EntityGoo;
import assets.rivalrebels.common.tileentity.TileEntityGore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGore extends BlockContainer
{
	public BlockGore()
	{
		super(Material.cake);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (player.capabilities.isCreativeMode)
		{
			int meta = world.getBlockMetadata(x, y, z) + 1;
			if (meta >= 6) meta = 0;
			world.setBlockMetadataWithNotify(x, y, z, meta, 3);
			return true;
		}
		return false;
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (world.getBlock(x, y - 1, z) == Blocks.air && world.getBlockMetadata(x, y, z) < 2) world.spawnEntityInWorld(new EntityBlood(world, x + Math.random(), y + 0.9f, z + Math.random()));
		else if (world.getBlock(x, y - 1, z) == Blocks.air && world.getBlockMetadata(x, y, z) < 4) world.spawnEntityInWorld(new EntityGoo(world, x + Math.random(), y + 0.9f, z + Math.random()));
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}
	
	@Override
	public void updateTick(World world, int xx, int yy, int zz, Random par5Random)
	{
		if (world.isBlockNormalCubeDefault(xx, yy - 1, zz, false) || world.isBlockNormalCubeDefault(xx + 1, yy, zz, false) || world.isBlockNormalCubeDefault(xx - 1, yy, zz, false) || world.isBlockNormalCubeDefault(xx, yy, zz + 1, false) || world.isBlockNormalCubeDefault(xx, yy, zz - 1, false) || world.isBlockNormalCubeDefault(xx, yy + 1, zz, false))
		{
			world.setBlock(xx, yy, zz, Blocks.air);
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity)
	{
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var)
	{
		return new TileEntityGore();
	}
	
	@SideOnly(Side.CLIENT)
	IIcon	icon;
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
	
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		if (meta == 0) return icon;
		if (meta == 1) return icon2;
		if (meta == 2) return icon3;
		if (meta == 3) return icon4;
		if (meta == 4) return icon5;
		if (meta == 5) return icon6;
		else
		{
			return icon;
		}
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		icon = iconregister.registerIcon("RivalRebels:br");
		icon2 = iconregister.registerIcon("RivalRebels:bs");
		icon3 = iconregister.registerIcon("RivalRebels:bt");
		icon4 = iconregister.registerIcon("RivalRebels:bu");
		icon5 = iconregister.registerIcon("RivalRebels:bv");
		icon6 = iconregister.registerIcon("RivalRebels:bw");
	}
}