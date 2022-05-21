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

public class BlockAutoTower extends BlockAutoTemplate
{
	public BlockAutoTower()
	{
		super();
	}
	
	@Override
	public void build(World world, int x, int y, int z)
	{
		super.build(world, x, y, z);
		for (int y1 = 0; y1 <= 16; y1++)
		{
			placeBlockCarefully(world, x + 1, y + y1, z, RivalRebels.steel);
			placeBlockCarefully(world, x - 1, y + y1, z, RivalRebels.steel);
			placeBlockCarefully(world, x, y + y1, z + 1, RivalRebels.steel);
			placeBlockCarefully(world, x, y + y1, z - 1, RivalRebels.steel);
			placeBlockCarefully(world, x - 1, y + y1, z + 1, RivalRebels.steel);
			placeBlockCarefully(world, x + 1, y + y1, z - 1, RivalRebels.steel);
			placeBlockCarefully(world, x + 1, y + y1, z + 1, RivalRebels.steel);
			placeBlockCarefully(world, x - 1, y + y1, z - 1, RivalRebels.steel);
			placeBlockCarefully(world, x, y + y1, z, Blocks.air);
			
			if (y1 <= 1)
			{
				placeBlockCarefully(world, x + 1, y + y1, z, Blocks.air);
				placeBlockCarefully(world, x - 1, y + y1, z, Blocks.air);
				placeBlockCarefully(world, x, y + y1, z + 1, Blocks.air);
				placeBlockCarefully(world, x, y + y1, z - 1, Blocks.air);
			}
			else if (y1 == 8)
			{
				placeBlockCarefully(world, x - 1, y + y1, z + 1, Blocks.netherrack);
				placeBlockCarefully(world, x + 1, y + y1, z - 1, Blocks.netherrack);
				placeBlockCarefully(world, x + 1, y + y1, z + 1, Blocks.netherrack);
				placeBlockCarefully(world, x - 1, y + y1, z - 1, Blocks.netherrack);
			}
			else if (y1 == 11)
			{
				placeBlockCarefully(world, x - 1, y + y1, z + 1, Blocks.netherrack);
				placeBlockCarefully(world, x + 1, y + y1, z - 1, Blocks.netherrack);
				placeBlockCarefully(world, x + 1, y + y1, z + 1, Blocks.netherrack);
				placeBlockCarefully(world, x - 1, y + y1, z - 1, Blocks.netherrack);
			}
			else if (y1 == 9)
			{
				placeBlockCarefully(world, x - 1, y + y1, z + 1, Blocks.air);
				placeBlockCarefully(world, x + 1, y + y1, z - 1, Blocks.air);
				placeBlockCarefully(world, x + 1, y + y1, z + 1, Blocks.air);
				placeBlockCarefully(world, x - 1, y + y1, z - 1, Blocks.air);
			}
			else if (y1 == 10)
			{
				placeBlockCarefully(world, x - 1, y + y1, z, Blocks.air);
				placeBlockCarefully(world, x + 1, y + y1, z, Blocks.air);
				placeBlockCarefully(world, x, y + y1, z + 1, Blocks.air);
				placeBlockCarefully(world, x, y + y1, z - 1, Blocks.air);
			}
			else if (y1 == 12)
			{
				placeBlockCarefully(world, x - 1, y + y1, z + 1, Blocks.air);
				placeBlockCarefully(world, x + 1, y + y1, z - 1, Blocks.air);
				placeBlockCarefully(world, x + 1, y + y1, z + 1, Blocks.air);
				placeBlockCarefully(world, x - 1, y + y1, z - 1, Blocks.air);
			}
			else if (y1 == 16)
			{
				placeBlockCarefully(world, x - 1, y + y1, z + 1, Blocks.log);
				placeBlockCarefully(world, x - 1, y + y1 + 1, z + 1, Blocks.redstone_torch);
				placeBlockCarefully(world, x + 1, y + y1 + 1, z - 1, Blocks.redstone_torch);
				placeBlockCarefully(world, x + 1, y + y1 + 1, z + 1, Blocks.redstone_torch);
				placeBlockCarefully(world, x - 1, y + y1 + 1, z - 1, Blocks.redstone_torch);
			}
		}
		placeBlockCarefully(world, x, y - 1, z, RivalRebels.jump);
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
		icon3 = iconregister.registerIcon("RivalRebels:cy"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:cy"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:cy"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:cy"); // SIDE E
	}
}