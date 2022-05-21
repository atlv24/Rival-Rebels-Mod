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

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPropulsionFX extends EntityThrowable
{
	private int	ticksInAir;
	
	public EntityPropulsionFX(World par1World)
	{
		super(par1World);
		ticksInAir = 0;
		setSize(0.1F, 0.1F);
		
	}
	
	public EntityPropulsionFX(World par1World, double par2, double par4, double par6)
	{
		super(par1World);
		par4 += -0.2;
		ticksInAir = 0;
		setSize(0.5F, 0.5F);
		setPosition(par2, par4, par6);
		yOffset = 0.0F;
	}
	
	public EntityPropulsionFX(World world2, double x, double y, double z, double mX, double mY, double mZ)
	{
		super(world2);
		ticksInAir = 0;
		setSize(0.5F, 0.5F);
		setPosition(x, y, z);
		setVelocity(mX, mY, mZ);
		yOffset = 0.0F;
	}
	
	@Override
	public void setVelocity(double mX, double mY, double mZ)
	{
		motionX = mX;
		motionY = mY;
		motionZ = mZ;
	}
	
	@Override
	public int getBrightnessForRender(float par1)
	{
		return 1000;
	}
	
	@Override
	public float getBrightness(float par1)
	{
		return 1000F;
	}
	
	@Override
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		ticksInAir++;
		if ((ticksInAir >= 5 && worldObj.rand.nextInt(2) == 1) || this.inWater)
		{
			setDead();
		}
		posY += 0.005;
		posX += (worldObj.rand.nextDouble() - 0.5) * 0.07;
		posY += (worldObj.rand.nextDouble() - 0.5) * 0.07;
		posZ += (worldObj.rand.nextDouble() - 0.5) * 0.07;
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		setPosition(posX, posY, posZ);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound var1)
	{
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound var1)
	{
	}
	
	@Override
	protected float getGravityVelocity()
	{
		return 0F;
	}
	
	@Override
	protected void onImpact(MovingObjectPosition var1)
	{
	}
}