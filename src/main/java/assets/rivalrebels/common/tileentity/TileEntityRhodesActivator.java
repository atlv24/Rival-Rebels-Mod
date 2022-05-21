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
package assets.rivalrebels.common.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.block.autobuilds.BlockRhodesScaffold;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityRhodesActivator extends TileEntityMachineBase
{
	int charge = 0;
	public TileEntityRhodesActivator()
	{
		pInM = 40;
		pInR = 20;
	}
	
	@Override
	public float powered(float power, float distance)
	{
		if (!worldObj.isRemote)
		{
			if (charge == 100)
			{
				// all 4 main charge points are valid
				boolean buildrhodes = true;
				boolean buildrhodes1 = true;
				boolean buildrhodes2 = true;
				int x = xCoord;
				int y = yCoord;
				int z = zCoord;
				for (int i = 0; i < 31*9; i++)
				{
					byte b = BlockRhodesScaffold.binimg[i];
					int fy = 2-(i/9);
					int fx1 = -10+(i%9);
					int fx2 = +7-(i%9);
					Block s1 = worldObj.getBlock(x+fx1, y+fy, z);
					Block s2 = worldObj.getBlock(x+fx2, y+fy, z);
					if (b == 1 && (s1 != RivalRebels.conduit || s2 != RivalRebels.conduit))
					{
						buildrhodes = false;
						break;
					}
					if (b == 2 && (s1 != RivalRebels.rhodesactivator || s2 != RivalRebels.rhodesactivator))
					{
						buildrhodes = false;
						break;
					}
				}
				if (!buildrhodes)
				for (int i = 0; i < 31*9; i++)
				{
					byte b = BlockRhodesScaffold.binimg[i];
					int fy = 2-(i/9);
					int fx1 = -10+(i%9);
					int fx2 = +7-(i%9);
					fy *= 2;
					fx1 *= 2;
					fx2 *= 2;
					Block s1 = worldObj.getBlock(x+fx1, y+fy, z);
					Block s2 = worldObj.getBlock(x+fx2, y+fy, z);
					if (b == 1 && (s1 != RivalRebels.conduit || s2 != RivalRebels.conduit))
					{
						buildrhodes1 = false;
						break;
					}
					if (b == 2 && (s1 != RivalRebels.rhodesactivator || s2 != RivalRebels.rhodesactivator))
					{
						buildrhodes1 = false;
						break;
					}
				}
				if (!buildrhodes && !buildrhodes1)
				for (int i = 0; i < 31*9; i++)
				{
					byte b = BlockRhodesScaffold.binimg[i];
					int fy = 2-(i/9);
					int fx1 = -10+(i%9);
					int fx2 = +7-(i%9);
					fy *= 3;
					fx1 *= 3;
					fx2 *= 3;
					Block s1 = worldObj.getBlock(x+fx1, y+fy, z);
					Block s2 = worldObj.getBlock(x+fx2, y+fy, z);
					if (b == 1 && (s1 != RivalRebels.conduit || s2 != RivalRebels.conduit))
					{
						buildrhodes2 = false;
						break;
					}
					if (b == 2 && (s1 != RivalRebels.rhodesactivator || s2 != RivalRebels.rhodesactivator))
					{
						buildrhodes2 = false;
						break;
					}
				}
				if (buildrhodes)
				{
					for (int i = 0; i < 31*9; i++)
					{
						byte b = BlockRhodesScaffold.binimg[i];
						if (b == 1)
						{
							int fy = 2-(i/9);
							int fx1 = -10+(i%9);
							int fx2 = +7-(i%9);
							worldObj.setBlock(x+fx1, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx2, y+fy, z, Blocks.air);
						}
					}
					EntityRhodes er = new EntityRhodes(worldObj, x-1f, y-13, z, 1);
					if (zCoord > this.z) er.bodyyaw = 180;
					worldObj.spawnEntityInWorld(er);
				}
				else if (buildrhodes1)
				{
					for (int i = 0; i < 31*9; i++)
					{
						byte b = BlockRhodesScaffold.binimg[i];
						if (b == 1)
						{
							int fy = 2-(i/9);
							int fx1 = -10+(i%9);
							int fx2 = +7-(i%9);
							fy *= 2;
							fx1 *= 2;
							fx2 *= 2;
							worldObj.setBlock(x+fx1, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx2, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx1+1, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx2+1, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx1, y+fy+1, z, Blocks.air);
							worldObj.setBlock(x+fx2, y+fy+1, z, Blocks.air);
							worldObj.setBlock(x+fx1+1, y+fy+1, z, Blocks.air);
							worldObj.setBlock(x+fx2+1, y+fy+1, z, Blocks.air);
						}
					}
					EntityRhodes er = new EntityRhodes(worldObj, x-2f, y-26, z, 2);
					if (zCoord > this.z) er.bodyyaw = 180;
					worldObj.spawnEntityInWorld(er);
				}
				else if (buildrhodes2)
				{
					for (int i = 0; i < 31*9; i++)
					{
						byte b = BlockRhodesScaffold.binimg[i];
						if (b == 1)
						{
							int fy = 2-(i/9);
							int fx1 = -10+(i%9);
							int fx2 = +7-(i%9);
							fy *= 3;
							fx1 *= 3;
							fx2 *= 3;
							worldObj.setBlock(x+fx1, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx2, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx1+1, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx2+1, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx1+2, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx2+2, y+fy, z, Blocks.air);
							worldObj.setBlock(x+fx1, y+fy+1, z, Blocks.air);
							worldObj.setBlock(x+fx2, y+fy+1, z, Blocks.air);
							worldObj.setBlock(x+fx1+1, y+fy+1, z, Blocks.air);
							worldObj.setBlock(x+fx2+1, y+fy+1, z, Blocks.air);
							worldObj.setBlock(x+fx1+2, y+fy+1, z, Blocks.air);
							worldObj.setBlock(x+fx2+2, y+fy+1, z, Blocks.air);
							worldObj.setBlock(x+fx1, y+fy+2, z, Blocks.air);
							worldObj.setBlock(x+fx2, y+fy+2, z, Blocks.air);
							worldObj.setBlock(x+fx1+1, y+fy+2, z, Blocks.air);
							worldObj.setBlock(x+fx2+1, y+fy+2, z, Blocks.air);
							worldObj.setBlock(x+fx1+2, y+fy+2, z, Blocks.air);
							worldObj.setBlock(x+fx2+2, y+fy+2, z, Blocks.air);
						}
					}
					EntityRhodes er = new EntityRhodes(worldObj, x-3f, y-39, z, 3);
					if (zCoord > this.z) er.bodyyaw = 180;
					worldObj.spawnEntityInWorld(er);
				}
				else if (worldObj.getBlock(x, y+1, z) == RivalRebels.conduit
					&& worldObj.getBlock(x-1, y, z) == RivalRebels.steel
					&& worldObj.getBlock(x+1, y, z) == RivalRebels.steel
					&& worldObj.getBlock(x-1, y+1, z) == RivalRebels.steel
					&& worldObj.getBlock(x+1, y+1, z) == RivalRebels.steel
					&& worldObj.getBlock(x-1, y+3, z) == RivalRebels.steel
					&& worldObj.getBlock(x+1, y+3, z) == RivalRebels.steel
					&& worldObj.getBlock(x, y+3, z) == RivalRebels.steel
					&& worldObj.getBlock(x, y+2, z) == RivalRebels.conduit
					&& worldObj.getBlock(x-1, y+2, z) == RivalRebels.steel
					&& worldObj.getBlock(x+1, y+2, z) == RivalRebels.steel)
				{
					worldObj.setBlock(x, y+1, z, Blocks.air);
					worldObj.setBlock(x, y+2, z, Blocks.air);
					worldObj.setBlock(x, y+3, z, Blocks.air);
					EntityRhodes er = new EntityRhodes(worldObj, x+0.5f, y+2.5f, z+0.5f, 0.0666666666666f);
					er.wakeX = x;
					er.wakeY = y;
					er.wakeZ = z;
					if (zCoord > this.z) er.bodyyaw = 180;
					worldObj.spawnEntityInWorld(er);
				}
				else if (worldObj.getBlock(x, y+1, z) == RivalRebels.conduit
					&& worldObj.getBlock(x-1, y, z) == RivalRebels.steel
					&& worldObj.getBlock(x+1, y, z) == RivalRebels.steel
					&& worldObj.getBlock(x-1, y+1, z) == RivalRebels.steel
					&& worldObj.getBlock(x+1, y+1, z) == RivalRebels.steel
					&& worldObj.getBlock(x-1, y+2, z) == RivalRebels.steel
					&& worldObj.getBlock(x+1, y+2, z) == RivalRebels.steel
					&& worldObj.getBlock(x, y+2, z) == RivalRebels.steel)
				{
					worldObj.setBlock(x, y+1, z, Blocks.air);
					worldObj.setBlock(x, y+2, z, Blocks.air);
					EntityRhodes er = new EntityRhodes(worldObj, x+0.5f, y+1.5f, z+0.5f, 0.0333333333333f);
					er.wakeX = x;
					er.wakeY = y;
					er.wakeZ = z;
					if (zCoord > this.z) er.bodyyaw = 180;
					worldObj.spawnEntityInWorld(er);
				}
				return power*0.5f;
			}
			else
			{
				charge++;
				return 0;
			}
		}
		return power;
	}
}