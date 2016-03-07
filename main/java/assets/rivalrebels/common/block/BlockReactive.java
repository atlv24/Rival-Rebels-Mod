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
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import assets.rivalrebels.common.tileentity.TileEntityReactive;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockReactive extends BlockContainer
{
	public BlockReactive()
	{
		super(Material.iron);
	}
	
	@Override
	public void breakBlock(World world, int i, int j, int k, Block l, int s)
	{
		super.breakBlock(world, i, j, k, l, s);
		if (s < 15 && s >= 0)
		{
			world.setBlock(i, j, k, this, s + 1, 2);
		}
	}
	
	@Override
	public boolean hasTileEntity()
	{
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		return (15 - par1IBlockAccess.getBlockMetadata(par2, par3, par4)) * 1118481;
	}
	
	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	IIcon	icon;
	@SideOnly(Side.CLIENT)
	IIcon	icontop;
	
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		if (side == 0 || side == 1) return icontop;
		return icon;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		icon = iconregister.registerIcon("RivalRebels:cf");
		icontop = iconregister.registerIcon("RivalRebels:cn");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int var)
	{
		return new TileEntityReactive();
	}
}