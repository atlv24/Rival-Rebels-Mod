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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSmartCamo extends Block
{
	public BlockSmartCamo()
	{
		super(Material.iron);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (world.getBlock(x + 1, y, z) == Blocks.snow_layer || world.getBlock(x - 1, y, z) == Blocks.snow_layer || world.getBlock(x, y, z - 1) == Blocks.snow_layer || world.getBlock(x, y, z + 1) == Blocks.snow_layer)
		{
			world.setBlock(x, y, z, RivalRebels.camo3);
		}
		else
		{
			if (world.getBlock(x, y - 1, z) == Blocks.grass || world.getBlock(x, y - 1, z) == Blocks.dirt)
			{
				world.setBlock(x, y, z, RivalRebels.camo1);
			}
			else
			{
				if (world.getBlock(x, y - 1, z) == Blocks.sand || world.getBlock(x, y - 1, z) == Blocks.sandstone)
				{
					world.setBlock(x, y, z, RivalRebels.camo2);
				}
				else
				{
					if (world.getBlock(x, y - 1, z) == Blocks.stone || world.getBlock(x, y - 1, z) == Blocks.gravel || world.getBlock(x, y - 1, z) == Blocks.bedrock || world.getBlock(x, y - 1, z) == Blocks.cobblestone)
					{
						world.setBlock(x, y, z, RivalRebels.camo3);
					}
					else
					{
						if (world.getBlock(x, y - 1, z) == RivalRebels.camo2 || world.getBlock(x + 1, y, z) == RivalRebels.camo2 || world.getBlock(x - 1, y, z) == RivalRebels.camo2 || world.getBlock(x, y, z + 1) == RivalRebels.camo2 || world.getBlock(x, y, z - 1) == RivalRebels.camo2 || world.getBlock(x, y + 1, z) == RivalRebels.camo2)
						{
							world.setBlock(x, y, z, RivalRebels.camo2);
						}
						else
						{
							if (world.getBlock(x, y - 1, z) == RivalRebels.camo3 || world.getBlock(x + 1, y, z) == RivalRebels.camo3 || world.getBlock(x - 1, y, z) == RivalRebels.camo3 || world.getBlock(x, y, z + 1) == RivalRebels.camo3 || world.getBlock(x, y, z - 1) == RivalRebels.camo3 || world.getBlock(x, y + 1, z) == RivalRebels.camo3)
							{
								world.setBlock(x, y, z, RivalRebels.camo3);
							}
							else
							{
								world.setBlock(x, y, z, RivalRebels.camo1);
							}
						}
					}
				}
			}
		}
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
		icon = iconregister.registerIcon("RivalRebels:bq");
	}
}