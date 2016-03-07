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
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class EntityLaptop extends EntityInanimate
{
	public double	slide	= 0;
	double			test	= Math.PI;
	
	public EntityLaptop(World par1World)
	{
		super(par1World);
		this.setSize(1F, 0.6F);
		boundingBox.setBounds(-0.21875, 0, -0.28125, 0.21875, 0.125, 0.28125);
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.boundingBox;
	}
	
	/**
	 * returns the bounding box for this entity
	 */
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}
	
	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	@Override
	public boolean canBePushed()
	{
		return true;
	}
	
	public EntityLaptop(World par1World, float x, float y, float z, float yaw)
	{
		super(par1World);
		setPosition(x, y, z);
		rotationYaw = yaw;
		boundingBox.setBounds(-0.21875, 0, -0.28125, 0.21875, 0.125, 0.28125);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		slide = (Math.cos(test) + 1) * 45;
		
		List players = worldObj.playerEntities;
		Iterator iter = players.iterator();
		boolean i = false;
		while (iter.hasNext())
		{
			EntityPlayer player = (EntityPlayer) iter.next();
			if (player.getDistanceSq(posX + 0.5f, posY + 0.5f, posZ + 0.5f) <= 9)
			{
				i = true;
			}
		}
		if (i)
		{
			if (slide < 89.995) test += 0.05;
		}
		else
		{
			if (slide > 0.004) test -= 0.05;
		}
	}
	
	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		if (player.isSneaking() && !player.worldObj.isRemote)
		{
			FMLNetworkHandler.openGui(player, RivalRebels.instance, 0, player.worldObj, 0, 0, 0);
			// player.openGui(RivalRebels.instance, RivalRebels.rrchestGuiID, player.worldObj, 0, 0, 0);
		}
		if (!player.isSneaking() && player.inventory.addItemStackToInventory(new ItemStack(RivalRebels.controller)))
		{
			player.swingItem();
			setDead();
		}
		return true;
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		
	}
}
