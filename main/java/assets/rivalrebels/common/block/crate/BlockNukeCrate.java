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
package assets.rivalrebels.common.block.crate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.packet.TextPacket;
import assets.rivalrebels.common.tileentity.TileEntityNukeCrate;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNukeCrate extends BlockContainer
{
	public BlockNukeCrate()
	{
		super(Material.wood);
	}
	
	public int determineOrientation(World world, int x, int y, int z, EntityPlayer entity)
	{
		int orientation = 0;
		if (this == RivalRebels.nukeCrateTop)
		{
			if (world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateBottom)
			{
				orientation = 0;
			}
			else if (world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateBottom)
			{
				orientation = 1;
			}
			else if (world.getBlock(x, y, z + 1) == RivalRebels.nukeCrateBottom)
			{
				orientation = 2;
			}
			else if (world.getBlock(x, y, z - 1) == RivalRebels.nukeCrateBottom)
			{
				orientation = 3;
			}
			else if (world.getBlock(x + 1, y, z) == RivalRebels.nukeCrateBottom)
			{
				orientation = 4;
			}
			else if (world.getBlock(x - 1, y, z) == RivalRebels.nukeCrateBottom)
			{
				orientation = 5;
			}
		}
		if (this == RivalRebels.nukeCrateBottom)
		{
			if (world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateTop)
			{
				orientation = 1;
			}
			else if (world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateTop)
			{
				orientation = 0;
			}
			else if (world.getBlock(x, y, z + 1) == RivalRebels.nukeCrateTop)
			{
				orientation = 3;
			}
			else if (world.getBlock(x, y, z - 1) == RivalRebels.nukeCrateTop)
			{
				orientation = 2;
			}
			else if (world.getBlock(x + 1, y, z) == RivalRebels.nukeCrateTop)
			{
				orientation = 5;
			}
			else if (world.getBlock(x - 1, y, z) == RivalRebels.nukeCrateTop)
			{
				orientation = 4;
			}
		}
		return orientation;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block par5)
	{
		world.setBlockMetadataWithNotify(x, y, z, determineOrientation(world, x, y, z, null), 0);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		world.setBlockMetadataWithNotify(x, y, z, determineOrientation(world, x, y, z, null), 0);
		
		if (world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateBottom)
		{
			onNeighborBlockChange(world, x, y + 1, z, this);
		}
		else if (world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateBottom)
		{
			onNeighborBlockChange(world, x, y - 1, z, this);
		}
		else if (world.getBlock(x, y, z + 1) == RivalRebels.nukeCrateBottom)
		{
			onNeighborBlockChange(world, x, y, z + 1, this);
		}
		else if (world.getBlock(x, y, z - 1) == RivalRebels.nukeCrateBottom)
		{
			onNeighborBlockChange(world, x, y, z - 1, this);
		}
		else if (world.getBlock(x + 1, y, z) == RivalRebels.nukeCrateBottom)
		{
			onNeighborBlockChange(world, x + 1, y, z, this);
		}
		else if (world.getBlock(x - 1, y, z) == RivalRebels.nukeCrateBottom)
		{
			onNeighborBlockChange(world, x - 1, y, z, this);
		}
		else if (world.getBlock(x, y + 1, z) == Blocks.lava)
		{
			world.setBlock(x, y, z, Blocks.air);
			world.createExplosion(null, x, y, z, 3, false);
		}
		else if (world.getBlock(x, y - 1, z) == Blocks.lava)
		{
			world.setBlock(x, y, z, Blocks.air);
			world.createExplosion(null, x, y, z, 3, false);
		}
		else if (world.getBlock(x, y, z + 1) == Blocks.lava)
		{
			world.setBlock(x, y, z, Blocks.air);
			world.createExplosion(null, x, y, z, 3, false);
		}
		else if (world.getBlock(x, y, z - 1) == Blocks.lava)
		{
			world.setBlock(x, y, z, Blocks.air);
			world.createExplosion(null, x, y, z, 3, false);
		}
		else if (world.getBlock(x + 1, y, z) == Blocks.lava)
		{
			world.setBlock(x, y, z, Blocks.air);
			world.createExplosion(null, x, y, z, 3, false);
		}
		else if (world.getBlock(x - 1, y, z) == Blocks.lava)
		{
			world.setBlock(x, y, z, Blocks.air);
			world.createExplosion(null, x, y, z, 3, false);
		}
	}
	
	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (this == RivalRebels.nukeCrateTop)
		{
			if (par5EntityPlayer.inventory.getCurrentItem() != null)
			{
				if (par5EntityPlayer.inventory.getCurrentItem().getItem() == RivalRebels.pliers)
				{
					int orientation;
					if (	world.getBlock(x + 1, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x + 1, y - 1, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x + 1, y, z, Blocks.air);
						world.setBlock(x, y - 1, z, Blocks.air);
						world.setBlock(x + 1, y - 1, z, RivalRebels.antimatterbombblock);
						world.setBlockMetadataWithNotify(x + 1, y - 1, z, 4, 1);
						return true;
					}
					else if (world.getBlock(x - 1, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x - 1, y - 1, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x - 1, y, z, Blocks.air);
						world.setBlock(x, y - 1, z, Blocks.air);
						world.setBlock(x - 1, y - 1, z, RivalRebels.antimatterbombblock);
						world.setBlockMetadataWithNotify(x - 1, y - 1, z, 5, 1);
						return true;
					}
					else if (world.getBlock(x, y, z + 1) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y - 1, z + 1) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x, y, z + 1, Blocks.air);
						world.setBlock(x, y - 1, z, Blocks.air);
						world.setBlock(x, y - 1, z + 1, RivalRebels.antimatterbombblock);
						world.setBlockMetadataWithNotify(x, y - 1, z + 1, 2, 1);
						return true;
					}
					else if (world.getBlock(x, y, z - 1) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y - 1, z - 1) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x, y, z - 1, Blocks.air);
						world.setBlock(x, y - 1, z, Blocks.air);
						world.setBlock(x, y - 1, z - 1, RivalRebels.antimatterbombblock);
						world.setBlockMetadataWithNotify(x, y - 1, z - 1, 3, 1);
						return true;
					}
					if (	world.getBlock(x + 1, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x + 1, y + 1, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y + 1, z, Blocks.air);
						world.setBlock(x + 1, y + 1, z, Blocks.air);
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x + 1, y, z, RivalRebels.tachyonbombblock);
						world.setBlockMetadataWithNotify(x + 1, y, z, 4, 1);
						return true;
					}
					else if (world.getBlock(x - 1, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x - 1, y + 1, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y + 1, z, Blocks.air);
						world.setBlock(x - 1, y + 1, z, Blocks.air);
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x - 1, y, z, RivalRebels.tachyonbombblock);
						world.setBlockMetadataWithNotify(x - 1, y, z, 5, 1);
						return true;
					}
					else if (world.getBlock(x, y, z + 1) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y + 1, z + 1) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y + 1, z, Blocks.air);
						world.setBlock(x, y + 1, z + 1, Blocks.air);
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x, y, z + 1, RivalRebels.tachyonbombblock);
						world.setBlockMetadataWithNotify(x, y, z + 1, 2, 1);
						return true;
					}
					else if (world.getBlock(x, y, z - 1) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y + 1, z - 1) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y + 1, z, Blocks.air);
						world.setBlock(x, y + 1, z - 1, Blocks.air);
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x, y, z - 1, RivalRebels.tachyonbombblock);
						world.setBlockMetadataWithNotify(x, y, z - 1, 3, 1);
						return true;
					}
					else if (world.getBlock(x + 1, y, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x + 2, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x + 3, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x + 1, y - 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x + 2, y - 1, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x + 3, y - 1, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x + 1, y, z, Blocks.air);
						world.setBlock(x + 2, y, z, Blocks.air);
						world.setBlock(x + 3, y, z, Blocks.air);
						world.setBlock(x, y - 1, z, Blocks.air);
						world.setBlock(x + 1, y - 1, z, RivalRebels.tsarbombablock);
						world.setBlockMetadataWithNotify(x + 1, y - 1, z, 4, 1);
						world.setBlock(x + 2, y - 1, z, Blocks.air);
						world.setBlock(x + 3, y - 1, z, Blocks.air);
						return true;
					}
					else if (world.getBlock(x - 1, y, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x - 2, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x - 3, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x - 1, y - 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x - 2, y - 1, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x - 3, y - 1, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x - 1, y, z, Blocks.air);
						world.setBlock(x - 2, y, z, Blocks.air);
						world.setBlock(x - 3, y, z, Blocks.air);
						world.setBlock(x, y - 1, z, Blocks.air);
						world.setBlock(x - 1, y - 1, z, RivalRebels.tsarbombablock);
						world.setBlockMetadataWithNotify(x - 1, y - 1, z, 5, 1);
						world.setBlock(x - 2, y - 1, z, Blocks.air);
						world.setBlock(x - 3, y - 1, z, Blocks.air);
						return true;
					}
					else if (world.getBlock(x, y, z + 1) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y, z + 2) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y, z + 3) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y - 1, z + 1) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y - 1, z + 2) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y - 1, z + 3) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x, y, z + 1, Blocks.air);
						world.setBlock(x, y, z + 2, Blocks.air);
						world.setBlock(x, y, z + 3, Blocks.air);
						world.setBlock(x, y - 1, z, Blocks.air);
						world.setBlock(x, y - 1, z + 1, RivalRebels.tsarbombablock);
						world.setBlockMetadataWithNotify(x, y - 1, z + 1, 2, 1);
						world.setBlock(x, y - 1, z + 2, Blocks.air);
						world.setBlock(x, y - 1, z + 3, Blocks.air);
						return true;
					}
					else if (world.getBlock(x, y, z - 1) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y, z - 2) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y, z - 3) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y - 1, z - 1) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y - 1, z - 2) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y - 1, z - 3) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x, y, z - 1, Blocks.air);
						world.setBlock(x, y, z - 2, Blocks.air);
						world.setBlock(x, y, z - 3, Blocks.air);
						world.setBlock(x, y - 1, z, Blocks.air);
						world.setBlock(x, y - 1, z - 1, RivalRebels.tsarbombablock);
						world.setBlockMetadataWithNotify(x, y - 1, z - 1, 3, 1);
						world.setBlock(x, y - 1, z - 2, Blocks.air);
						world.setBlock(x, y - 1, z - 3, Blocks.air);
						return true;
					}
					else if (world.getBlock(x + 1, y, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x + 2, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x + 3, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x + 1, y + 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x + 2, y + 1, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x + 3, y + 1, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x + 1, y + 1, z, Blocks.air);
						world.setBlock(x + 2, y, z, Blocks.air);
						world.setBlock(x + 3, y, z, Blocks.air);
						world.setBlock(x, y + 1, z, Blocks.air);
						world.setBlock(x + 1, y, z, RivalRebels.theoreticaltsarbombablock);
						world.setBlockMetadataWithNotify(x + 1, y, z, 4, 1);
						world.setBlock(x + 2, y + 1, z, Blocks.air);
						world.setBlock(x + 3, y + 1, z, Blocks.air);
						return true;
					}
					else if (world.getBlock(x - 1, y, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x - 2, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x - 3, y, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x - 1, y + 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x - 2, y + 1, z) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x - 3, y + 1, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x - 1, y + 1, z, Blocks.air);
						world.setBlock(x - 2, y, z, Blocks.air);
						world.setBlock(x - 3, y, z, Blocks.air);
						world.setBlock(x, y + 1, z, Blocks.air);
						world.setBlock(x - 1, y, z, RivalRebels.theoreticaltsarbombablock);
						world.setBlockMetadataWithNotify(x - 1, y, z, 5, 1);
						world.setBlock(x - 2, y + 1, z, Blocks.air);
						world.setBlock(x - 3, y + 1, z, Blocks.air);
						return true;
					}
					else if (world.getBlock(x, y, z + 1) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y, z + 2) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y, z + 3) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y + 1, z + 1) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y + 1, z + 2) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y + 1, z + 3) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x, y + 1, z + 1, Blocks.air);
						world.setBlock(x, y, z + 2, Blocks.air);
						world.setBlock(x, y, z + 3, Blocks.air);
						world.setBlock(x, y + 1, z, Blocks.air);
						world.setBlock(x, y, z + 1, RivalRebels.theoreticaltsarbombablock);
						world.setBlockMetadataWithNotify(x, y, z + 1, 2, 1);
						world.setBlock(x, y + 1, z + 2, Blocks.air);
						world.setBlock(x, y + 1, z + 3, Blocks.air);
						return true;
					}
					else if (world.getBlock(x, y, z - 1) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y, z - 2) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y, z - 3) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y + 1, z - 1) == RivalRebels.nukeCrateTop &&
							world.getBlock(x, y + 1, z - 2) == RivalRebels.nukeCrateBottom &&
							world.getBlock(x, y + 1, z - 3) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z, Blocks.air);
						world.setBlock(x, y + 1, z - 1, Blocks.air);
						world.setBlock(x, y, z - 2, Blocks.air);
						world.setBlock(x, y, z - 3, Blocks.air);
						world.setBlock(x, y + 1, z, Blocks.air);
						world.setBlock(x, y, z - 1, RivalRebels.theoreticaltsarbombablock);
						world.setBlockMetadataWithNotify(x, y, z - 1, 3, 1);
						world.setBlock(x, y + 1, z - 2, Blocks.air);
						world.setBlock(x, y + 1, z - 3, Blocks.air);
						return true;
					}
					else if (world.getBlock(x, y + 1, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y + 1, z, Blocks.air);
						orientation = 0;
					}
					else if (world.getBlock(x, y - 1, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y - 1, z, Blocks.air);
						orientation = 1;
					}
					else if (world.getBlock(x, y, z + 1) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z + 1, Blocks.air);
						orientation = 2;
					}
					else if (world.getBlock(x, y, z - 1) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x, y, z - 1, Blocks.air);
						orientation = 3;
					}
					else if (world.getBlock(x + 1, y, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x + 1, y, z, Blocks.air);
						orientation = 4;
					}
					else if (world.getBlock(x - 1, y, z) == RivalRebels.nukeCrateBottom)
					{
						world.setBlock(x - 1, y, z, Blocks.air);
						orientation = 5;
					}
					else
					{
						return false;
					}
					world.setBlock(x, y, z, RivalRebels.nuclearBomb, orientation, 0x02);
					return true;
				}
				else if (!world.isRemote)
				{
					PacketDispatcher.packetsys.sendTo(new TextPacket("RivalRebels.Orders RivalRebels.message.use " + RivalRebels.pliers.getUnlocalizedName() + ".name"), (EntityPlayerMP) par5EntityPlayer);
				}
			}
			else if (!world.isRemote)
			{
				PacketDispatcher.packetsys.sendTo(new TextPacket("RivalRebels.Orders RivalRebels.message.use " + RivalRebels.pliers.getUnlocalizedName() + ".name"), (EntityPlayerMP) par5EntityPlayer);
			}
		}
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
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
	public TileEntity createNewTileEntity(World var1, int var)
	{
		return new TileEntityNukeCrate();
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
		if (this == RivalRebels.nukeCrateTop) icon = iconregister.registerIcon("RivalRebels:ay");
		if (this == RivalRebels.nukeCrateBottom) icon = iconregister.registerIcon("RivalRebels:ax");
	}
}
