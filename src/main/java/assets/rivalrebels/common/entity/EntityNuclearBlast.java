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
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.explosion.NuclearExplosion;

public class EntityNuclearBlast extends EntityInanimate
{
	public int	ticksExisted;
	int			time;
	int			Strength;
	
	public EntityNuclearBlast(World par1World)
	{
		super(par1World);
		ignoreFrustumCheck = true;
		ticksExisted = 0;
		time = 0;
		setSize(0.5F, 0.5F);
	}
	
	public EntityNuclearBlast(World par1World, double par2, double par4, double par6, int s, boolean hasTroll)
	{
		super(par1World);
		ignoreFrustumCheck = true;
		ticksExisted = 0;
		time = 0;
		motionY = Strength = s;
		if (hasTroll)
		{
			motionX = 1;
		}
		else
		{
			motionX = 0;
		}
		setSize(0.5F, 0.5F);
		setPosition(par2, par4, par6);
		yOffset = 0.0F;
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
		if (!worldObj.isRemote)
		{
			if (ticksExisted == 0)
			{
				worldObj.createExplosion(null, posX, posY - 5, posZ, 4, true);
			}
			if (ticksExisted % 20 == 0 && ticksExisted > 60)
			{
				time++;
				if (time <= Strength)
				{
					new NuclearExplosion(worldObj, (int) posX, (int) posY - 5, (int) posZ, (time * time) / 2 + RivalRebels.nuclearBombStrength);
				}
			}
			if (ticksExisted % 2 == 0 && ticksExisted < 400) pushAndHurtEntities();
		}
		if (ticksExisted < 30)
		{
			worldObj.playSoundEffect(posX, posY + ticksExisted - 5, posZ, "random.explode", 4.0f, worldObj.rand.nextFloat() * 0.1f + 0.9f);
		}
		if (ticksExisted % 3 == 0 && ticksExisted < 40 && ticksExisted > 30)
		{
			for (int i = 0; i < 21; i++)
			{
				worldObj.playSoundEffect(posX + Math.sin(i) * (i / 0.5), posY + 17, posZ + Math.cos(i) * (i / 0.5), "explode", 4.0f, worldObj.rand.nextFloat() + 1.0f);
			}
		}
		if (ticksExisted < 600)
		{
			if (ticksExisted % 5 == worldObj.rand.nextInt(5))
			{
				Iterator i = worldObj.playerEntities.iterator();
				while (i.hasNext())
				{
					EntityPlayer p = (EntityPlayer) i.next();
					worldObj.playSoundAtEntity(p, "ambient.weather.thunder", 10.0F, 0.50F);
					worldObj.playSoundAtEntity(p, "random.explode", 5.0F, 0.10F);
				}
			}
		}
		else
		{
			setDead();
		}
		
		ticksExisted++;
	}
	
	private void pushAndHurtEntities()
	{
		int radius = Strength * RivalRebels.nuclearBombStrength * 1;
		if (radius > 80) radius = 80;
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
					if (!(var31 instanceof EntityNuclearBlast) && !(var31 instanceof EntityTsarBlast))
					{
						if (var31 instanceof EntityFallingBlock) var31.setDead();
						else
						{
							if (var31 instanceof EntityPlayer && ((EntityPlayer) var31).capabilities.isCreativeMode) continue;
							var31.attackEntityFrom(RivalRebelsDamageSource.nuclearblast, 16 * radius);
							var31.motionX -= var15 * 8;
							var31.motionY -= var17 * 8;
							var31.motionZ -= var19 * 8;
						}
					}
				}
			}
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound var1)
	{
		ticksExisted = var1.getInteger("ticksExisted");
		time = var1.getInteger("time");
		motionY = Strength = var1.getInteger("charges");
		motionX = var1.getBoolean("troll") ? 1.0f : 0.0f;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound var1)
	{
		var1.setInteger("ticksExisted", ticksExisted);
		var1.setInteger("time", time);
		var1.setInteger("charges", Strength);
	}
	
	@Override
	protected void entityInit()
	{
	}
}