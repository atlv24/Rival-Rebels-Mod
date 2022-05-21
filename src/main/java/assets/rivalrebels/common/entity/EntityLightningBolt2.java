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
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityLightningBolt2 extends EntityWeatherEffect
{
	/**
	 * Declares which state the lightning bolt is in. Whether it's in the air, hit the ground, etc.
	 */
	private int	lightningState;
	
	/**
	 * A random long that is used to change the vertex of the lightning rendered in RenderLightningBolt
	 */
	public long	boltVertex	= 0L;
	
	/**
	 * Determines the time before the EntityLightningBolt2 is destroyed. It is a random integer decremented over time.
	 */
	private int	boltLivingTime;
	
	public EntityLightningBolt2(World par1World, double par2, double par4, double par6)
	{
		super(par1World);
		this.setLocationAndAngles(par2, par4, par6, 0.0F, 0.0F);
		this.lightningState = 2;
		this.boltVertex = this.rand.nextLong();
		this.boltLivingTime = this.rand.nextInt(3) + 1;
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		if (this.lightningState == 2)
		{
			this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
			this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
		}
		
		--this.lightningState;
		
		if (this.lightningState < 0)
		{
			if (this.boltLivingTime == 0)
			{
				this.setDead();
			}
			else if (this.lightningState < -this.rand.nextInt(10))
			{
				--this.boltLivingTime;
				this.lightningState = 1;
				this.boltVertex = this.rand.nextLong();
			}
		}
		
		if (this.lightningState >= 0)
		{
			double var6 = 3.0D;
			List var7 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(this.posX - var6, this.posY - var6, this.posZ - var6, this.posX + var6, this.posY + 6.0D + var6, this.posZ + var6));
			Iterator var4 = var7.iterator();
			
			while (var4.hasNext())
			{
				Entity var5 = (Entity) var4.next();
			}
			
			this.worldObj.lastLightningBolt = 2;
		}
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
	}
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
	}
}
