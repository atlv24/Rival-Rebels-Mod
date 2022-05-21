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

public class BlockAutoForceField extends BlockAutoTemplate
{
	public BlockAutoForceField()
	{
		super();
	}
	
	@Override
	public void build(World world, int x, int y, int z)
	{
		super.build(world, x, y, z);
		if (!world.isRemote)
		{
			int r = 2;
			int h = 6;
			
			for (int y1 = 0; y1 <= h; y1++)
			{
				for (int x1 = -r; x1 <= r; x1++)
				{
					for (int z1 = -r; z1 <= r; z1++)
					{
						if (((Math.abs(x1) == r || Math.abs(z1) == r) && (y1 != 3 || (Math.abs(x1) != 0 && Math.abs(z1) != 0))) || y1 == 0 || y1 == h)
						{
							placeBlockCarefully(world, x + x1, y + y1, z + z1, RivalRebels.reactive);
						}
						else
						{
							placeBlockCarefully(world, x + x1, y + y1, z + z1, Blocks.air, 0, 2);
						}
					}
				}
				if (y1 != 3)
				{
					placeBlockCarefully(world, x + r + 1, y + y1, z, RivalRebels.reactive);
					placeBlockCarefully(world, x - r - 1, y + y1, z, RivalRebels.reactive);
					placeBlockCarefully(world, x, y + y1, z + r + 1, RivalRebels.reactive);
					placeBlockCarefully(world, x, y + y1, z - r - 1, RivalRebels.reactive);
				}
				else
				{
					for (int z1 = -r; z1 <= r; z1++)
					{
						placeBlockCarefully(world, x, y + y1, z + z1, RivalRebels.conduit);
					}
					
					for (int x1 = -r; x1 <= r; x1++)
					{
						placeBlockCarefully(world, x + x1, y + y1, z, RivalRebels.conduit);
					}
				}
			}
		}
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
		icon3 = iconregister.registerIcon("RivalRebels:db"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:db"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:db"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:db"); // SIDE E
	}
}