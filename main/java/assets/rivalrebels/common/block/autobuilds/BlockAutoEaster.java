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
package assets.rivalrebels.common.block.autobuilds;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAutoEaster extends BlockAutoTemplate
{
	public BlockAutoEaster()
	{
		super();
	}
	
	@Override
	public void build(World par1World, int x, int y, int z)
	{
		super.build(par1World, x, y, z);
		int h = 0;
		placeBlockCarefully(par1World, x, y, z, Blocks.air);
		placeBlockCarefully(par1World, x + 1, y + h, z, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 2, y + h, z, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 3, y + h, z, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 4, y + h, z, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 1, y + h, z + 1, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 2, y + h, z + 1, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 3, y + h, z + 1, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 4, y + h, z + 1, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 1, y + h, z + 2, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 2, y + h, z + 2, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 3, y + h, z + 2, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 4, y + h, z + 2, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 1, y + h, z + 3, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 2, y + h, z + 3, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 3, y + h, z + 3, RivalRebels.jump);
		placeBlockCarefully(par1World, x + 4, y + h, z + 3, RivalRebels.jump);
		h = h + 1;
		placeBlockCarefully(par1World, x + 1, y + h, z, Blocks.snow);
		placeBlockCarefully(par1World, x + 2, y + h, z, Blocks.snow);
		placeBlockCarefully(par1World, x + 3, y + h, z, Blocks.snow);
		placeBlockCarefully(par1World, x + 4, y + h, z, Blocks.snow);
		placeBlockCarefully(par1World, x + 1, y + h, z + 1, Blocks.snow);
		placeBlockCarefully(par1World, x + 2, y + h, z + 1, Blocks.snow);
		placeBlockCarefully(par1World, x + 3, y + h, z + 1, Blocks.snow);
		placeBlockCarefully(par1World, x + 4, y + h, z + 1, Blocks.snow);
		placeBlockCarefully(par1World, x + 1, y + h, z + 2, Blocks.snow);
		placeBlockCarefully(par1World, x + 2, y + h, z + 2, Blocks.snow);
		placeBlockCarefully(par1World, x + 3, y + h, z + 2, Blocks.snow);
		placeBlockCarefully(par1World, x + 4, y + h, z + 2, Blocks.snow);
		placeBlockCarefully(par1World, x + 1, y + h, z + 3, Blocks.snow);
		placeBlockCarefully(par1World, x + 2, y + h, z + 3, Blocks.snow);
		placeBlockCarefully(par1World, x + 3, y + h, z + 3, Blocks.snow);
		placeBlockCarefully(par1World, x + 4, y + h, z + 3, Blocks.snow);
		h = h + 1;
		placeBlockCarefully(par1World, x + 1, y + h, z, Blocks.snow);
		placeBlockCarefully(par1World, x + 2, y + h, z, Blocks.snow);
		placeBlockCarefully(par1World, x + 3, y + h, z, Blocks.snow);
		placeBlockCarefully(par1World, x + 4, y + h, z, Blocks.snow);
		placeBlockCarefully(par1World, x + 1, y + h, z + 1, Blocks.snow);
		placeBlockCarefully(par1World, x + 2, y + h, z + 1, Blocks.snow);
		placeBlockCarefully(par1World, x + 3, y + h, z + 1, Blocks.snow);
		placeBlockCarefully(par1World, x + 4, y + h, z + 1, Blocks.snow);
		placeBlockCarefully(par1World, x + 1, y + h, z + 2, Blocks.snow);
		placeBlockCarefully(par1World, x + 2, y + h, z + 2, Blocks.snow);
		placeBlockCarefully(par1World, x + 3, y + h, z + 2, Blocks.snow);
		placeBlockCarefully(par1World, x + 4, y + h, z + 2, Blocks.snow);
		placeBlockCarefully(par1World, x + 1, y + h, z + 3, Blocks.snow);
		placeBlockCarefully(par1World, x + 2, y + h, z + 3, Blocks.snow);
		placeBlockCarefully(par1World, x + 3, y + h, z + 3, Blocks.snow);
		placeBlockCarefully(par1World, x + 4, y + h, z + 3, Blocks.snow);
		h = h + 1;
		placeBlockCarefully(par1World, x + 1, y + h, z, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 2, y + h, z, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 3, y + h, z, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 4, y + h, z, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 1, y + h, z + 1, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 2, y + h, z + 1, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 3, y + h, z + 1, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 4, y + h, z + 1, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 1, y + h, z + 2, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 2, y + h, z + 2, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 3, y + h, z + 2, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 4, y + h, z + 2, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 1, y + h, z + 3, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 2, y + h, z + 3, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 3, y + h, z + 3, Blocks.pumpkin);
		placeBlockCarefully(par1World, x + 4, y + h, z + 3, Blocks.pumpkin);
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
		icon1 = iconregister.registerIcon("RivalRebels:ah"); // BOTTOM
		icon2 = iconregister.registerIcon("RivalRebels:ai"); // TOP
		icon3 = iconregister.registerIcon("RivalRebels:ah"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:ah"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:ah"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:ah"); // SIDE E
	}
}