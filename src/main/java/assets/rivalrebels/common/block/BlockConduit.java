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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.common.tileentity.TileEntityConduit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockConduit extends BlockContainer
{
	public BlockConduit()
	{
		super(Material.iron);
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
	{
		world.setBlock(x, y, z, this, new Random().nextInt(9) + 1, 0x3);
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityConduit();
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
	IIcon	icon7;
	@SideOnly(Side.CLIENT)
	IIcon	icon8;
	@SideOnly(Side.CLIENT)
	IIcon	icon9;
	
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		if (meta == 1) return icon1;
		if (meta == 2) return icon2;
		if (meta == 3) return icon3;
		if (meta == 4) return icon4;
		if (meta == 5) return icon5;
		if (meta == 6) return icon6;
		if (meta == 7) return icon7;
		if (meta == 8) return icon8;
		if (meta == 9) return icon9;
		return icon1;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		icon1 = iconregister.registerIcon("RivalRebels:co");
		icon2 = iconregister.registerIcon("RivalRebels:cp");
		icon3 = iconregister.registerIcon("RivalRebels:cq");
		icon4 = iconregister.registerIcon("RivalRebels:cr");
		icon5 = iconregister.registerIcon("RivalRebels:cs");
		icon6 = iconregister.registerIcon("RivalRebels:ct");
		icon7 = iconregister.registerIcon("RivalRebels:cu");
		icon8 = iconregister.registerIcon("RivalRebels:cv");
		icon9 = iconregister.registerIcon("RivalRebels:cw");
	}
}