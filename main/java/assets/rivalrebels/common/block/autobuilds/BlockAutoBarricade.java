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

public class BlockAutoBarricade extends BlockAutoTemplate
{
	public BlockAutoBarricade()
	{
		super();
	}
	
	@Override
	public void build(World par1World, int x, int y, int z)
	{
		super.build(par1World, x, y, z);
		
		placeBlockCarefully(par1World, x, y, z, Blocks.air);
		placeBlockCarefully(par1World, x + 1, y, z + 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x + 1, y, z - 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x - 1, y, z - 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x - 1, y, z + 1, RivalRebels.reactive);
		
		placeBlockCarefully(par1World, x + 1, y + 1, z + 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x + 1, y + 1, z - 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x - 1, y + 1, z - 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x - 1, y + 1, z + 1, RivalRebels.reactive);
		
		placeBlockCarefully(par1World, x + 1, y + 2, z + 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x + 1, y + 2, z - 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x - 1, y + 2, z - 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x - 1, y + 2, z + 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x, y + 2, z + 1, RivalRebels.steel);
		placeBlockCarefully(par1World, x + 1, y + 2, z, RivalRebels.steel);
		placeBlockCarefully(par1World, x, y + 2, z - 1, RivalRebels.steel);
		placeBlockCarefully(par1World, x - 1, y + 2, z, RivalRebels.steel);
		placeBlockCarefully(par1World, x, y + 2, z + 2, RivalRebels.reactive);
		placeBlockCarefully(par1World, x + 2, y + 2, z, RivalRebels.reactive);
		placeBlockCarefully(par1World, x, y + 2, z - 2, RivalRebels.reactive);
		placeBlockCarefully(par1World, x - 2, y + 2, z, RivalRebels.reactive);
		
		placeBlockCarefully(par1World, x + 1, y + 3, z + 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x + 1, y + 3, z - 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x - 1, y + 3, z - 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x - 1, y + 3, z + 1, RivalRebels.reactive);
		placeBlockCarefully(par1World, x, y + 3, z + 1, RivalRebels.steel);
		placeBlockCarefully(par1World, x + 1, y + 3, z, RivalRebels.steel);
		placeBlockCarefully(par1World, x, y + 3, z - 1, RivalRebels.steel);
		placeBlockCarefully(par1World, x - 1, y + 3, z, RivalRebels.steel);
		placeBlockCarefully(par1World, x, y + 3, z - 2, RivalRebels.ffreciever, 2, 3);
		placeBlockCarefully(par1World, x, y + 3, z + 2, RivalRebels.ffreciever, 3, 3);
		placeBlockCarefully(par1World, x - 2, y + 3, z, RivalRebels.ffreciever, 4, 3);
		placeBlockCarefully(par1World, x + 2, y + 3, z, RivalRebels.ffreciever, 5, 3);
		
		// int h = 1;
		// int s = 1;
		// placeBlockCarefully(par1World, x, y, z, 0);
		// placeBlockCarefully(par1World, x + s, y + h, z + s, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x + s, y + h, z - s, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x - s, y + h, z - s, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x - s, y + h, z + s, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x + (2 * s), y + h, z + (2 * s), RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x + (2 * s), y + h, z - (2 * s), RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x - (2 * s), y + h, z - (2 * s), RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x - (2 * s), y + h, z + (2 * s), RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x + s, y + h + s, z, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x + s, y + h - s, z, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x - s, y + h - s, z, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x - s, y + h + s, z, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x + (2 * s), y + h + (2 * s), z, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x + (2 * s), y + h - (2 * s), z, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x - (2 * s), y + h - (2 * s), z, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x - (2 * s), y + h + (2 * s), z, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x, y + h + s, z + s, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x, y + h - s, z - s, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x, y + h + s, z - s, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x, y + h - s, z + s, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x, y + h + (2 * s), z + (2 * s), RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x, y + h - (2 * s), z - (2 * s), RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x, y + h + (2 * s), z - (2 * s), RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x, y + h - (2 * s), z + (2 * s), RivalRebels.smartcamo);
		// h = 2;
		// placeBlockCarefully(par1World, x + s, y + h, z, RivalRebels.reactive);
		// placeBlockCarefully(par1World, x + s, y + h, z - s, RivalRebels.reactive);
		// placeBlockCarefully(par1World, x + s, y + h, z + s, RivalRebels.reactive);
		// placeBlockCarefully(par1World, x - s, y + h, z, RivalRebels.reactive);
		// placeBlockCarefully(par1World, x - s, y + h, z - s, RivalRebels.reactive);
		// placeBlockCarefully(par1World, x - s, y + h, z + s, RivalRebels.reactive);
		// placeBlockCarefully(par1World, x, y + h, z + s, RivalRebels.reactive);
		// placeBlockCarefully(par1World, x, y + h, z - s, RivalRebels.reactive);
		// h = 4;
		// s = 2;
		// placeBlockCarefully(par1World, x + s, y + h, z, RivalRebels.conduit);
		// placeBlockCarefully(par1World, x - s, y + h, z, RivalRebels.conduit);
		// placeBlockCarefully(par1World, x, y + h, z + s, RivalRebels.conduit);
		// placeBlockCarefully(par1World, x, y + h, z - s, RivalRebels.conduit);
		// h = 2;
		// placeBlockCarefully(par1World, x + s, y + h, z, RivalRebels.conduit);
		// placeBlockCarefully(par1World, x - s, y + h, z, RivalRebels.conduit);
		// placeBlockCarefully(par1World, x, y + h, z + s, RivalRebels.conduit);
		// placeBlockCarefully(par1World, x, y + h, z - s, RivalRebels.conduit);
		// placeBlockCarefully(par1World, x + s, y + h, z + s, RivalRebels.flare);
		// placeBlockCarefully(par1World, x - s, y + h, z - s, RivalRebels.flare);
		// placeBlockCarefully(par1World, x - s, y + h, z + s, RivalRebels.flare);
		// placeBlockCarefully(par1World, x + s, y + h, z - s, RivalRebels.flare);
		// h = 1;
		// s = 3;
		// placeBlockCarefully(par1World, x, y - h, z, RivalRebels.smartcamo);
		// placeBlockCarefully(par1World, x + s, y - h, z + 1, RivalRebels.jump);
		// placeBlockCarefully(par1World, x - s, y - h, z - 1, RivalRebels.jump);
		// placeBlockCarefully(par1World, x + s, y - h, z - 1, RivalRebels.jump);
		// placeBlockCarefully(par1World, x - s, y - h, z + 1, RivalRebels.jump);
		// placeBlockCarefully(par1World, x - 1, y - h, z + s, RivalRebels.jump);
		// placeBlockCarefully(par1World, x - 1, y - h, z - s, RivalRebels.jump);
		// placeBlockCarefully(par1World, x + 1, y - h, z + s, RivalRebels.jump);
		// placeBlockCarefully(par1World, x + 1, y - h, z - s, RivalRebels.jump);
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
		icon1 = iconregister.registerIcon("RivalRebels:cz"); // BOTTOM
		icon2 = iconregister.registerIcon("RivalRebels:da"); // TOP
		icon3 = iconregister.registerIcon("RivalRebels:cx"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:cx"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:cx"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:cx"); // SIDE E
	}
}