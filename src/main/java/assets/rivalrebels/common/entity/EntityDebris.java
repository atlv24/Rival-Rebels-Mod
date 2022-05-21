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
package assets.rivalrebels.common.entity;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import assets.rivalrebels.common.packet.EntityDebrisPacket;
import assets.rivalrebels.common.packet.PacketDispatcher;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class EntityDebris extends EntityInanimate
{
	public Block			block;
	public int				metadata;
	public int				ticksExisted;
	public boolean			grounded;
	public NBTTagCompound	tileEntityData;
	
	public EntityDebris(World w)
	{
		super(w);
	}

	public EntityDebris(World w, int x, int y, int z)
	{
		super(w);
		block = w.getBlock(x, y, z);
		metadata = w.getBlockMetadata(x, y, z);
		w.setBlock(x, y, z, Blocks.air);
		setSize(1F, 1F);
		yOffset = 0.5f;
		setPosition(x + 0.5f, y + 0.5f, z + 0.5f);
		prevPosX = x + 0.5f;
		prevPosY = y + 0.5f;
		prevPosZ = z + 0.5f;
	}
	public EntityDebris(World w, double x, double y, double z, double mx, double my, double mz, Block b)
	{
		super(w);
		block = b;
		metadata = 0;
		setSize(1F, 1F);
		yOffset = 0.5f;
		setPosition(x, y, z);
		prevPosX = x;
		prevPosY = y;
		prevPosZ = z;
		motionX = mx;
		motionY = my;
		motionZ = mz;
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	public void onUpdate()
	{
		if (ticksExisted == 0 && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) PacketDispatcher.packetsys.sendToAll(new EntityDebrisPacket(this));
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		++ticksExisted;
		motionY -= 0.04;
		motionX *= 0.98;
		motionY *= 0.98;
		motionZ *= 0.98;
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		
		if (!worldObj.isRemote && worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)).isOpaqueCube()) die(prevPosX, prevPosY, prevPosZ);
	}
	
	public void die(double X, double Y, double Z)
	{
		int x = MathHelper.floor_double(X);
		int y = MathHelper.floor_double(Y);
		int z = MathHelper.floor_double(Z);
		setDead();
		worldObj.setBlock(x, y, z, block, metadata, 3);
		if (block instanceof BlockFalling) ((BlockFalling) block).func_149828_a(worldObj, x, y, z, metadata);
		if (tileEntityData != null && block instanceof ITileEntityProvider)
		{
			TileEntity tileentity = worldObj.getTileEntity(x, y, z);
			if (tileentity != null)
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				tileentity.writeToNBT(nbttagcompound);
				Iterator iter = tileEntityData.func_150296_c().iterator();
				while (iter.hasNext())
				{
					String s = (String) iter.next();
					NBTBase nbtbase = tileEntityData.getTag(s);
					if (!s.equals("x") && !s.equals("y") && !s.equals("z"))
					{
						nbttagcompound.setTag(s, nbtbase.copy());
					}
				}
				tileentity.readFromNBT(nbttagcompound);
				tileentity.markDirty();
			}
		}
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setByte("Tile", (byte) Block.getIdFromBlock(block));
		nbt.setInteger("TileID", Block.getIdFromBlock(block));
		nbt.setByte("Data", (byte) metadata);
		nbt.setByte("Time", (byte) ticksExisted);
		if (tileEntityData != null) nbt.setTag("TileEntityData", tileEntityData);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		if (nbt.hasKey("TileID", 99)) block = Block.getBlockById(nbt.getInteger("TileID"));
		else block = Block.getBlockById(nbt.getByte("Tile") & 255);
		metadata = nbt.getByte("Data") & 255;
		ticksExisted = nbt.getByte("Time") & 255;
		if (nbt.hasKey("TileEntityData", 10)) tileEntityData = nbt.getCompoundTag("TileEntityData");
	}
	
	@Override
	public void addEntityCrashInfo(CrashReportCategory crash)
	{
		super.addEntityCrashInfo(crash);
		crash.addCrashSection("Immitating block ID", Integer.valueOf(Block.getIdFromBlock(block)));
		crash.addCrashSection("Immitating block data", Integer.valueOf(metadata));
	}
}