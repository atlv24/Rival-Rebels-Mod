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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityForceFieldNode extends TileEntityMachineBase
{
	public String			username	= null;
	public RivalRebelsTeam	rrteam		= null;
	public int				level		= 0;
	
	public TileEntityForceFieldNode()
	{
		pInM = 345;
		pInR = 345;
	}
	
	@Override
	public void updateEntity()
	{
		if (pInR > 0) pInR = powered(pInR, edist);
		else turnOff();
		pInR -= decay;
	}
	
	public void turnOff()
	{
		if (level > 0)
		{
			int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
			
			level--;
			for (int y = 0; y < 7; y++)
			{
				switch (meta)
				{
					case 2:
						if (worldObj.getBlock(xCoord, yCoord + (y - 3), zCoord - level - 1) == RivalRebels.forcefield)
						{
							worldObj.setBlock(xCoord, yCoord + (y - 3), zCoord - level - 1, Blocks.air, 0, 3);
						}
					break;
					
					case 3:
						if (worldObj.getBlock(xCoord, yCoord + (y - 3), zCoord + level + 1) == RivalRebels.forcefield)
						{
							worldObj.setBlock(xCoord, yCoord + (y - 3), zCoord + level + 1, Blocks.air, 0, 3);
						}
					break;
					
					case 4:
						if (worldObj.getBlock(xCoord - level - 1, yCoord + (y - 3), zCoord) == RivalRebels.forcefield)
						{
							worldObj.setBlock(xCoord - level - 1, yCoord + (y - 3), zCoord, Blocks.air, 0, 3);
						}
					break;
					
					case 5:
						if (worldObj.getBlock(xCoord + level + 1, yCoord + (y - 3), zCoord) == RivalRebels.forcefield)
						{
							worldObj.setBlock(xCoord + level + 1, yCoord + (y - 3), zCoord, Blocks.air, 0, 3);
						}
					break;
				}
			}
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		float t = 0.0625f;
		float l = 35f;
		float h = 3.5f;
		switch (meta)
		{
			case 2: return AxisAlignedBB.getBoundingBox(xCoord + 0.5f - t, yCoord + 0.5f - h, zCoord - l, xCoord + 0.5f + t, yCoord + 0.5f + h, zCoord);
			case 3: return AxisAlignedBB.getBoundingBox(xCoord + 0.5f - t, yCoord + 0.5f - h, zCoord + 1f, xCoord + 0.5f + t, yCoord + 0.5f + h, zCoord + 1f + l);
			case 4: return AxisAlignedBB.getBoundingBox(xCoord - l, yCoord + 0.5f - h, zCoord + 0.5f - t, xCoord, yCoord + 0.5f + h, zCoord + 0.5f + t);
			case 5: return AxisAlignedBB.getBoundingBox(xCoord + 1f, yCoord + 0.5f - h, zCoord + 0.5f - t, xCoord + 1f + l, yCoord + 0.5f + h, zCoord + 0.5f + t);
			default: return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 16384.0D;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		rrteam = RivalRebelsTeam.getForID(par1NBTTagCompound.getInteger("rrteam"));
		if (rrteam == RivalRebelsTeam.NONE) rrteam = null;
		if (rrteam == null) username = par1NBTTagCompound.getString("username");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		if (rrteam != null) par1NBTTagCompound.setInteger("rrteam", rrteam.ordinal());
		if (username != null) par1NBTTagCompound.setString("username", username);
	}
	
	@Override
	public float powered(float power, float distance)
	{
		float hits = (float) Math.random();
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		
		double randomness = 0.1;
		float thickness = 0.5f;
		float length = 35f;
		float height = 3.52f;
		double speed = 2;
		
		if (meta == 2)
		{
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord + 0.5f - thickness, yCoord + 0.5f - height, zCoord - length, xCoord + 0.5f + thickness, yCoord + 0.5f + height, zCoord);
			List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(null, aabb);
			Iterator<?> iter = list.iterator();
			while (iter.hasNext())
			{
				Entity e = (Entity) iter.next();
				boolean shouldContinue = true;
				if (e instanceof EntityPlayer && e != null)
				{
					EntityPlayer p = (EntityPlayer) e;
					RivalRebelsPlayer player = RivalRebels.round.rrplayerlist.getForName(p.getCommandSenderName());
					if (p.getCommandSenderName().equals(username) || (player != null && player.rrteam == rrteam))
					{
						shouldContinue = false;
						hits++;
						p.setPositionAndUpdate(p.posX + (p.posX > (xCoord + 0.5) ? -2 : 2), p.posY, p.posZ);
					}
				}
				if (shouldContinue && e != null)
				{
					double cpx = xCoord + 0.5f - e.posX;
					double cpy = e.posY + e.getEyeHeight() - e.posY;
					double cpz = e.posZ - e.posZ;
					
					double dist = Math.sqrt(cpx * cpx + cpy * cpy + cpz * cpz) / speed;
					
					cpx /= dist;
					cpy /= dist;
					cpz /= dist;
					
					cpx += worldObj.rand.nextGaussian() * randomness;
					cpy += worldObj.rand.nextGaussian() * randomness;
					cpz += worldObj.rand.nextGaussian() * randomness;
					
					e.motionX = -cpx - e.motionX;
					e.motionY = -cpy - e.motionY;
					e.motionZ = -cpz - e.motionZ;
					RivalRebelsSoundPlayer.playSound(e, 10, 7, 1, 2f);
					if (e.boundingBox != null) hits += e.boundingBox.getAverageEdgeLength();
				}
			}
			if (level < length)
			{
				placeBlockCarefully(worldObj, xCoord, yCoord, (int) (zCoord - length - 1), RivalRebels.reactive);
				for (int y = 0; y < 7; y++)
				{
					if (worldObj.getBlock(xCoord, yCoord + (y - 3), zCoord - level - 1) != RivalRebels.forcefield)
					{
						worldObj.setBlock(xCoord, yCoord + (y - 3), zCoord - level - 1, RivalRebels.forcefield, meta, 3);
						hits++;
					}
				}
				level++;
			}
		}
		
		if (meta == 3)
		{
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord + 0.5f - thickness, yCoord + 0.5f - height, zCoord + 1f, xCoord + 0.5f + thickness, yCoord + 0.5f + height, zCoord + 1f + length);
			List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(null, aabb);
			Iterator<?> iter = list.iterator();
			while (iter.hasNext())
			{
				Entity e = (Entity) iter.next();
				boolean shouldContinue = true;
				if (e instanceof EntityPlayer && e != null)
				{
					EntityPlayer p = (EntityPlayer) e;
					RivalRebelsPlayer player = RivalRebels.round.rrplayerlist.getForName(p.getCommandSenderName());
					if (p.getCommandSenderName().equals(username) || (player != null && player.rrteam == rrteam))
					{
						shouldContinue = false;
						hits++;
						p.setPositionAndUpdate(p.posX + (p.posX > (xCoord + 0.5) ? -2 : 2), p.posY, p.posZ);
					}
				}
				if (shouldContinue && e != null)
				{
					double cpx = xCoord + 0.5f - e.posX;
					double cpy = e.posY + e.getEyeHeight() - e.posY;
					double cpz = e.posZ - e.posZ;
					
					double dist = Math.sqrt(cpx * cpx + cpy * cpy + cpz * cpz) / speed;
					
					cpx /= dist;
					cpy /= dist;
					cpz /= dist;
					
					cpx += worldObj.rand.nextGaussian() * randomness;
					cpy += worldObj.rand.nextGaussian() * randomness;
					cpz += worldObj.rand.nextGaussian() * randomness;
					
					e.motionX = -cpx - e.motionX;
					e.motionY = -cpy - e.motionY;
					e.motionZ = -cpz - e.motionZ;
					RivalRebelsSoundPlayer.playSound(e, 10, 7, 1, 2f);
					if (e.boundingBox != null) hits += e.boundingBox.getAverageEdgeLength();
				}
			}
			if (level < length)
			{
				placeBlockCarefully(worldObj, xCoord, yCoord, (int) (zCoord + length + 1), RivalRebels.reactive);
				for (int y = 0; y < 7; y++)
				{
					if (worldObj.getBlock(xCoord, yCoord + (y - 3), zCoord + level + 1) != RivalRebels.forcefield)
					{
						worldObj.setBlock(xCoord, yCoord + (y - 3), zCoord + level + 1, RivalRebels.forcefield, meta, 3);
						hits++;
					}
				}
				level++;
			}
		}
		
		if (meta == 4)
		{
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord - length, yCoord + 0.5f - height, zCoord + 0.5f - thickness, xCoord, yCoord + 0.5f + height, zCoord + 0.5f + thickness);
			List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(null, aabb);
			Iterator<?> iter = list.iterator();
			while (iter.hasNext())
			{
				Entity e = (Entity) iter.next();
				boolean shouldContinue = true;
				if (e instanceof EntityPlayer && e != null)
				{
					EntityPlayer p = (EntityPlayer) e;
					RivalRebelsPlayer player = RivalRebels.round.rrplayerlist.getForName(p.getCommandSenderName());
					if (p.getCommandSenderName().equals(username) || (player != null && player.rrteam == rrteam))
					{
						shouldContinue = false;
						hits++;
						p.setPositionAndUpdate(p.posX, p.posY, p.posZ + (p.posZ > (zCoord + 0.5) ? -2 : 2));
					}
				}
				if (shouldContinue && e != null)
				{
					double cpx = e.posX - e.posX;
					double cpy = e.posY + e.getEyeHeight() - e.posY;
					double cpz = zCoord + 0.5f - e.posZ;
					
					double dist = Math.sqrt(cpx * cpx + cpy * cpy + cpz * cpz) / speed;
					
					cpx /= dist;
					cpy /= dist;
					cpz /= dist;
					
					cpx += worldObj.rand.nextGaussian() * randomness;
					cpy += worldObj.rand.nextGaussian() * randomness;
					cpz += worldObj.rand.nextGaussian() * randomness;
					
					e.motionX = -cpx - e.motionX;
					e.motionY = -cpy - e.motionY;
					e.motionZ = -cpz - e.motionZ;
					RivalRebelsSoundPlayer.playSound(e, 10, 7, 1, 2f);
					if (e.boundingBox != null) hits += e.boundingBox.getAverageEdgeLength();
				}
			}
			if (level < length)
			{
				placeBlockCarefully(worldObj, (int) (xCoord - length - 1), yCoord, zCoord, RivalRebels.reactive);
				for (int y = 0; y < 7; y++)
				{
					if (worldObj.getBlock(xCoord - level - 1, yCoord + (y - 3), zCoord) != RivalRebels.forcefield)
					{
						worldObj.setBlock(xCoord - level - 1, yCoord + (y - 3), zCoord, RivalRebels.forcefield, meta, 3);
						hits++;
					}
				}
				level++;
			}
		}
		
		if (meta == 5)
		{
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord + 1f, yCoord + 0.5f - height, zCoord + 0.5f - thickness, xCoord + 1f + length, yCoord + 0.5f + height, zCoord + 0.5f + thickness);
			List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(null, aabb);
			Iterator<?> iter = list.iterator();
			while (iter.hasNext())
			{
				Entity e = (Entity) iter.next();
				boolean shouldContinue = true;
				if (e instanceof EntityPlayer && e != null)
				{
					EntityPlayer p = (EntityPlayer) e;
					RivalRebelsPlayer player = RivalRebels.round.rrplayerlist.getForName(p.getCommandSenderName());
					if (p.getCommandSenderName().equals(username) || (player != null && player.rrteam == rrteam))
					{
						shouldContinue = false;
						hits++;
						p.setPositionAndUpdate(p.posX, p.posY, p.posZ + (p.posZ > (zCoord + 0.5) ? -2 : 2));
					}
				}
				if (shouldContinue && e != null)
				{
					double cpx = e.posX - e.posX;
					double cpy = e.posY + e.getEyeHeight() - e.posY;
					double cpz = zCoord + 0.5f - e.posZ;
					
					double dist = Math.sqrt(cpx * cpx + cpy * cpy + cpz * cpz) / speed;
					
					cpx /= dist;
					cpy /= dist;
					cpz /= dist;
					
					cpx += worldObj.rand.nextGaussian() * randomness;
					cpy += worldObj.rand.nextGaussian() * randomness;
					cpz += worldObj.rand.nextGaussian() * randomness;
					
					e.motionX = -cpx - e.motionX;
					e.motionY = -cpy - e.motionY;
					e.motionZ = -cpz - e.motionZ;
					RivalRebelsSoundPlayer.playSound(e, 10, 7, 1, 2f);
					if (e.boundingBox != null) hits += e.boundingBox.getAverageEdgeLength();
				}
			}
			if (level < length)
			{
				placeBlockCarefully(worldObj, (int) (xCoord + length + 1), yCoord, zCoord, RivalRebels.reactive);
				for (int y = 0; y < 7; y++)
				{
					if (worldObj.getBlock(xCoord + level + 1, yCoord + (y - 3), zCoord) != RivalRebels.forcefield)
					{
						worldObj.setBlock(xCoord + level + 1, yCoord + (y - 3), zCoord, RivalRebels.forcefield, meta, 3);
						hits++;
					}
				}
				level++;
			}
		}
		return (power - (hits * 16));
	}
	
	public void placeBlockCarefully(World world, int i, int j, int z, Block blockID)
	{
		if (world.getBlock(i, j, z) != RivalRebels.reactive && world.getBlock(i, j, z) != RivalRebels.fshield && world.getBlock(i, j, z) != RivalRebels.omegaobj && world.getBlock(i, j, z) != RivalRebels.sigmaobj)
		{
			world.setBlock(i, j, z, blockID);
		}
	}
}

// package RivalRebels.Common.TileEntity;
//
// import java.util.Iterator;
// import java.util.List;
//
// import cpw.mods.fml.common.FMLCommonHandler;
// import cpw.mods.fml.relauncher.Side;
// import cpw.mods.fml.relauncher.SideOnly;
//
// import RivalRebels.Client.Gui.GuiOptiFineWarning;
// import RivalRebels.Common.Core.RivalRebels;
// import RivalRebels.Common.Core.RivalRebelsPlayer;
// import RivalRebels.Common.Core.RivalRebelsSoundPlayer;
// import RivalRebels.Common.Core.RivalRebelsTeam;
//
// import net.minecraft.client.Minecraft;
// import net.minecraft.entity.Entity;
// import net.minecraft.entity.player.EntityPlayer;
// import net.minecraft.item.ItemStack;
// import net.minecraft.nbt.NBTTagCompound;
// import net.minecraft.tileentity.TileEntity;
// import net.minecraft.util.AxisAlignedBB;
// import net.minecraft.world.World;
//
// public class TileEntityForceFieldNode extends TileEntityMachineBase
// {
// public String username = null;
// public RivalRebelsTeam rrteam = null;
// public boolean first = false;
// public TileEntityForceFieldNode()
// {
// pInM = 345;
// pInR = 0;
// }
//
// public AxisAlignedBB getRenderBoundingBox()
// {
// return this.INFINITE_EXTENT_AABB;
// }
//
// @SideOnly(Side.CLIENT)
// public double getMaxRenderDistanceSquared()
// {
// return 16384.0D;
// }
//
// public void readFromNBT(NBTTagCompound par1NBTTagCompound)
// {
// super.readFromNBT(par1NBTTagCompound);
// rrteam = RivalRebelsTeam.getForID(par1NBTTagCompound.getInteger("rrteam"));
// if (rrteam == RivalRebelsTeam.NONE) rrteam = null;
// if (rrteam == null) username = par1NBTTagCompound.getString("username");
// }
//
// public void writeToNBT(NBTTagCompound par1NBTTagCompound)
// {
// super.writeToNBT(par1NBTTagCompound);
// if (rrteam != null) par1NBTTagCompound.setInteger("rrteam", rrteam.ordinal());
// if (username != null) par1NBTTagCompound.setString("username", username);
// }
//
// @Override
// public float powered(float power, float distance)
// {
// float hits = 0;
// int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
//
// double randomness = 0.1;
// float thickness = 0.5f;
// float length = 35f;
// float height = 3.52f;
// double speed = 2;
//
// if (meta == 2)
// {
// AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord+0.5f-thickness, yCoord+0.5f-height, zCoord-length, xCoord+0.5f+thickness, yCoord+0.5f+height, zCoord);
// List list = worldObj.getEntitiesWithinAABBExcludingEntity(null, aabb);
// Iterator iter = list.iterator();
// while (iter.hasNext())
// {
// Entity e = (Entity) iter.next();
// boolean shouldContinue = true;
// if (e instanceof EntityPlayer && e != null)
// {
// EntityPlayer p = (EntityPlayer)e;
// RivalRebelsPlayer player = RivalRebels.rrplayerlist.getForName(p.username);
// if (p.username.equals(username) || (player != null && player.rrteam == rrteam))
// {
// shouldContinue = false;
// hits++;
// p.setPositionAndUpdate(p.posX + (p.posX > (xCoord + 0.5) ? -2 : 2), p.posY, p.posZ);
// }
// }
// if (shouldContinue && e != null)
// {
// double cpx = xCoord + 0.5f - e.posX;
// double cpy = e.posY + e.getEyeHeight() - e.posY;
// double cpz = e.posZ - e.posZ;
//
// double dist = Math.sqrt(cpx*cpx + cpy*cpy + cpz*cpz) / speed;
//
// cpx /= dist;
// cpy /= dist;
// cpz /= dist;
//
// cpx += worldObj.rand.nextGaussian() * randomness;
// cpy += worldObj.rand.nextGaussian() * randomness;
// cpz += worldObj.rand.nextGaussian() * randomness;
//
// e.motionX = -cpx-e.motionX;
// e.motionY = -cpy-e.motionY;
// e.motionZ = -cpz-e.motionZ;
// RivalRebelsSoundPlayer.playSound(e, 10, 7, 1, 2f);
// if (e.boundingBox != null) hits += e.boundingBox.getAverageEdgeLength();
// }
// }
// for (int i = 0; i < length; i++)
// {
// for (int y = 0; y < 7; y++)
// {
// if (worldObj.getBlockId(xCoord, yCoord + (y - 3), zCoord - i - 1) != RivalRebels.forcefield.blockID)
// {
// worldObj.setBlock(xCoord, yCoord + (y - 3), zCoord - i - 1, RivalRebels.forcefield.blockID, meta, 3);
// hits++;
// }
// }
// }
// if (!first)
// {
// first = true;
// placeBlockCarefully(worldObj, xCoord, yCoord, (int) (zCoord-length-1), RivalRebels.reactive.blockID);
// }
// }
//
// if (meta == 3)
// {
// AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord+0.5f-thickness, yCoord+0.5f-height, zCoord+1f, xCoord+0.5f+thickness, yCoord+0.5f+height, zCoord+1f+length);
// List list = worldObj.getEntitiesWithinAABBExcludingEntity(null, aabb);
// Iterator iter = list.iterator();
// while (iter.hasNext())
// {
// Entity e = (Entity) iter.next();
// boolean shouldContinue = true;
// if (e instanceof EntityPlayer && e != null)
// {
// EntityPlayer p = (EntityPlayer)e;
// RivalRebelsPlayer player = RivalRebels.rrplayerlist.getForName(p.username);
// if (p.username.equals(username) || (player != null && player.rrteam == rrteam))
// {
// shouldContinue = false;
// hits++;
// p.setPositionAndUpdate(p.posX + (p.posX > (xCoord + 0.5) ? -2 : 2), p.posY, p.posZ);
// }
// }
// if (shouldContinue && e != null)
// {
// double cpx = xCoord + 0.5f - e.posX;
// double cpy = e.posY + e.getEyeHeight() - e.posY;
// double cpz = e.posZ - e.posZ;
//
// double dist = Math.sqrt(cpx*cpx + cpy*cpy + cpz*cpz) / speed;
//
// cpx /= dist;
// cpy /= dist;
// cpz /= dist;
//
// cpx += worldObj.rand.nextGaussian() * randomness;
// cpy += worldObj.rand.nextGaussian() * randomness;
// cpz += worldObj.rand.nextGaussian() * randomness;
//
// e.motionX = -cpx-e.motionX;
// e.motionY = -cpy-e.motionY;
// e.motionZ = -cpz-e.motionZ;
// RivalRebelsSoundPlayer.playSound(e, 10, 7, 1, 2f);
// if (e.boundingBox != null) hits += e.boundingBox.getAverageEdgeLength();
// }
// }
// for (int i = 0; i < length; i++)
// {
// for (int y = 0; y < 7; y++)
// {
// if (worldObj.getBlockId(xCoord, yCoord + (y - 3), zCoord + i + 1) != RivalRebels.forcefield.blockID)
// {
// worldObj.setBlock(xCoord, yCoord + (y - 3), zCoord + i + 1, RivalRebels.forcefield.blockID, meta, 3);
// hits++;
// }
// }
// }
// if (!first)
// {
// first = true;
// placeBlockCarefully(worldObj, xCoord, yCoord, (int) (zCoord+length+1), RivalRebels.reactive.blockID);
// }
// }
//
// if (meta == 4)
// {
// AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord-length, yCoord+0.5f-height, zCoord+0.5f-thickness, xCoord, yCoord+0.5f+height, zCoord+0.5f+thickness);
// List list = worldObj.getEntitiesWithinAABBExcludingEntity(null, aabb);
// Iterator iter = list.iterator();
// while (iter.hasNext())
// {
// Entity e = (Entity) iter.next();
// boolean shouldContinue = true;
// if (e instanceof EntityPlayer && e != null)
// {
// EntityPlayer p = (EntityPlayer)e;
// RivalRebelsPlayer player = RivalRebels.rrplayerlist.getForName(p.username);
// if (p.username.equals(username) || (player != null && player.rrteam == rrteam))
// {
// shouldContinue = false;
// hits++;
// p.setPositionAndUpdate(p.posX, p.posY, p.posZ + (p.posZ > (zCoord + 0.5) ? -2 : 2));
// }
// }
// if (shouldContinue && e != null)
// {
// double cpx = e.posX - e.posX;
// double cpy = e.posY + e.getEyeHeight() - e.posY;
// double cpz = zCoord + 0.5f - e.posZ;
//
// double dist = Math.sqrt(cpx*cpx + cpy*cpy + cpz*cpz) / speed;
//
// cpx /= dist;
// cpy /= dist;
// cpz /= dist;
//
// cpx += worldObj.rand.nextGaussian() * randomness;
// cpy += worldObj.rand.nextGaussian() * randomness;
// cpz += worldObj.rand.nextGaussian() * randomness;
//
// e.motionX = -cpx-e.motionX;
// e.motionY = -cpy-e.motionY;
// e.motionZ = -cpz-e.motionZ;
// RivalRebelsSoundPlayer.playSound(e, 10, 7, 1, 2f);
// if (e.boundingBox != null) hits += e.boundingBox.getAverageEdgeLength();
// }
// }
// for (int i = 0; i < length; i++)
// {
// for (int y = 0; y < 7; y++)
// {
// if (worldObj.getBlockId(xCoord - i - 1, yCoord + (y - 3), zCoord) != RivalRebels.forcefield.blockID)
// {
// worldObj.setBlock(xCoord - i - 1, yCoord + (y - 3), zCoord, RivalRebels.forcefield.blockID, meta, 3);
// hits++;
// }
// }
// }
// if (!first)
// {
// first = true;
// placeBlockCarefully(worldObj, (int) (xCoord-length-1), yCoord, zCoord, RivalRebels.reactive.blockID);
// }
// }
//
// if (meta == 5)
// {
// AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord+1f, yCoord+0.5f-height, zCoord+0.5f-thickness, xCoord+1f+length, yCoord+0.5f+height, zCoord+0.5f+thickness);
// List list = worldObj.getEntitiesWithinAABBExcludingEntity(null, aabb);
// Iterator iter = list.iterator();
// while (iter.hasNext())
// {
// Entity e = (Entity) iter.next();
// boolean shouldContinue = true;
// if (e instanceof EntityPlayer && e != null)
// {
// EntityPlayer p = (EntityPlayer)e;
// RivalRebelsPlayer player = RivalRebels.rrplayerlist.getForName(p.username);
// if (p.username.equals(username) || (player != null && player.rrteam == rrteam))
// {
// shouldContinue = false;
// hits++;
// p.setPositionAndUpdate(p.posX, p.posY, p.posZ + (p.posZ > (zCoord + 0.5) ? -2 : 2));
// }
// }
// if (shouldContinue && e != null)
// {
// double cpx = e.posX - e.posX;
// double cpy = e.posY + e.getEyeHeight() - e.posY;
// double cpz = zCoord + 0.5f - e.posZ;
//
// double dist = Math.sqrt(cpx*cpx + cpy*cpy + cpz*cpz) / speed;
//
// cpx /= dist;
// cpy /= dist;
// cpz /= dist;
//
// cpx += worldObj.rand.nextGaussian() * randomness;
// cpy += worldObj.rand.nextGaussian() * randomness;
// cpz += worldObj.rand.nextGaussian() * randomness;
//
// e.motionX = -cpx-e.motionX;
// e.motionY = -cpy-e.motionY;
// e.motionZ = -cpz-e.motionZ;
// RivalRebelsSoundPlayer.playSound(e, 10, 7, 1, 2f);
// if (e.boundingBox != null) hits += e.boundingBox.getAverageEdgeLength();
// }
// }
// for (int i = 0; i < length; i++)
// {
// for (int y = 0; y < 7; y++)
// {
// if (worldObj.getBlockId(xCoord + i + 1, yCoord + (y - 3), zCoord) != RivalRebels.forcefield.blockID)
// {
// worldObj.setBlock(xCoord + i + 1, yCoord + (y - 3), zCoord, RivalRebels.forcefield.blockID, meta, 3);
// hits++;
// }
// }
// }
// if (!first)
// {
// first = true;
// placeBlockCarefully(worldObj, (int) (xCoord+length+1), yCoord, zCoord, RivalRebels.reactive.blockID);
// }
// }
// return (power - (hits * 16));
// }
// public void placeBlockCarefully(World world, int i, int j, int z, int blockID)
// {
// if (world.getBlockId(i, j, z) != RivalRebels.reactive.blockID && world.getBlockId(i, j, z) != RivalRebels.fshield.blockID && world.getBlockId(i, j, z) != RivalRebels.omegaobj.blockID &&
// world.getBlockId(i, j, z) != RivalRebels.sigmaobj.blockID)
// {
// world.setBlock(i, j, z, blockID);
// }
// }
// }
