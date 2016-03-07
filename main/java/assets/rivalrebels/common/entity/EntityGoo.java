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

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityGoo extends EntityInanimate
{
	private boolean	isGore	= true;
	
	public EntityGoo(World par1World)
	{
		super(par1World);
		setSize(0.25F, 0.25F);
	}
	
	public EntityGoo(World par1World, EntityGore bloodEmitter)
	{
		super(par1World);
		setSize(0.25F, 0.25F);
		setLocationAndAngles(bloodEmitter.posX, bloodEmitter.posY, bloodEmitter.posZ, 0, 0);
		setPosition(posX, posY, posZ);
		setThrowableHeading(0.1f);
		isGore = true;
	}
	
	public EntityGoo(World par1World, double x, double y, double z)
	{
		super(par1World);
		setSize(0.25F, 0.25F);
		setLocationAndAngles(x, y, z, 0, 0);
		setPosition(posX, posY, posZ);
		setThrowableHeading(0f);
		isGore = false;
	}
	
	public void setThrowableHeading(float force)
	{
		motionX = rand.nextGaussian() * force;
		motionY = rand.nextGaussian() * force;
		motionZ = rand.nextGaussian() * force;
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		++ticksExisted;
		
		Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3, vec31);
		vec3 = Vec3.createVectorHelper(posX, posY, posZ);
		vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		
		if (movingobjectposition != null || isInWater() || (ticksExisted == 20 && isGore)) setDead();
		
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		
		motionX *= 0.99F;
		motionY *= 0.99F;
		motionZ *= 0.99F;
		motionY -= 0.03F;
		setPosition(posX, posY, posZ);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double par1)
	{
		return par1 < 256;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}
	
	@Override
	public void entityInit()
	{
	}
}
