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

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.TsarBomba;

public class EntitySphereBlast extends EntityTsarBlast
{
	public EntitySphereBlast(World par1World)
	{
		super(par1World);
		ignoreFrustumCheck = true;
	}
	
	public EntitySphereBlast(World par1World, double x, double y, double z, float rad)
	{
		super(par1World);
		ignoreFrustumCheck = true;
		radius = rad;
		motionX = Math.sqrt(rad - RivalRebels.tsarBombaStrength) / 10;
		setPosition(x, y, z);
	}
	
	@Override
	public void onUpdate()
	{
		if (worldObj.rand.nextInt(10) == 0)
		{
			worldObj.playSoundAtEntity(this, "ambient.weather.thunder", 10.0F, 0.50F);
		}
		else
		{
			if (worldObj.rand.nextInt(5) == 0) RivalRebelsSoundPlayer.playSound(this, 26, 0, 100, 0.7f);
		}
		
		if (rand.nextBoolean()&&rand.nextBoolean()) pushAndHurtEntities();
		
		ticksExisted++;
		
		if (ticksExisted > 400) setDead();
	}
	
	@Override
	public void pushAndHurtEntities()
	{
		int var3 = MathHelper.floor_double(posX - radius - 1.0D);
		int var4 = MathHelper.floor_double(posX + radius + 1.0D);
		int var5 = MathHelper.floor_double(posY - radius - 1.0D);
		int var28 = MathHelper.floor_double(posY + radius + 1.0D);
		int var7 = MathHelper.floor_double(posZ - radius - 1.0D);
		int var29 = MathHelper.floor_double(posZ + radius + 1.0D);
		List var9 = worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(var3, var5, var7, var4, var28, var29));
		Vec3 var30 = Vec3.createVectorHelper(posX, posY, posZ);
		
		for (int var11 = 0; var11 < var9.size(); ++var11)
		{
			Entity var31 = (Entity) var9.get(var11);
			if (var31 instanceof EntityLivingBase)
			{
				if (var31 instanceof EntityPlayer && ((EntityPlayer) var31).capabilities.isCreativeMode) continue;
				
				double var13 = var31.getDistance(posX, posY, posZ) / radius;
				
				if (var13 <= 1.0D)
				{
					double var15 = var31.posX - posX;
					double var17 = var31.posY + var31.getEyeHeight() - posY;
					double var19 = var31.posZ - posZ;
					double var33 = MathHelper.sqrt_double(var15 * var15 + var17 * var17 + var19 * var19);
					
					if (var33 != 0.0D)
					{
						var15 /= var33;
						var17 /= var33;
						var19 /= var33;
						double var34 = (1.0D - var13);
						var31.attackEntityFrom(RivalRebelsDamageSource.nuclearblast, (int) ((var34 * var34 + var34) * 20 * radius + 20) * 200);
						var31.motionX -= var15 * var34 * 8;
						var31.motionY -= var17 * var34 * 8;
						var31.motionZ -= var19 * var34 * 8;
					}
				}
			}
			if (var31 instanceof EntityRhodes)
			{
				var31.attackEntityFrom(RivalRebelsDamageSource.nuclearblast, 30);
			}
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		
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
	
	@Override
	protected void entityInit()
	{
		
	}
}
