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

import java.util.ArrayList;
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
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.AntimatterBomb;
import assets.rivalrebels.common.explosion.TachyonBomb;
import assets.rivalrebels.common.explosion.TsarBomba;

public class EntityTachyonBombBlast extends EntityInanimate
{
	public TachyonBomb	tsar		= null;
	public double		radius;
	public int			time		= 0;
	
	public EntityTachyonBombBlast(World par1World)
	{
		super(par1World);
		ignoreFrustumCheck = true;
	}
	
	public EntityTachyonBombBlast(World par1World, float x, float y, float z, TachyonBomb tsarBomba, int rad)
	{
		super(par1World);
		ignoreFrustumCheck = true;
		tsar = tsarBomba;
		radius = rad;
		motionX = Math.sqrt(radius - RivalRebels.tsarBombaStrength) / 10;
		setPosition(x, y, z);
	}
	
	public EntityTachyonBombBlast(World par1World, double x, double y, double z, float rad)
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
		super.onUpdate();
		
		if (worldObj.rand.nextInt(30) == 0)
		{
			worldObj.playSoundAtEntity(this, "ambient.weather.thunder", 10.0F, 0.5F);
		}
		else
		{
			if (worldObj.rand.nextInt(30) == 0) RivalRebelsSoundPlayer.playSound(this, 13, 0, 100, 0.8f);
		}
		
		ticksExisted++;
		
		if (!worldObj.isRemote)
		{
			if (tsar == null && ticksExisted > 1200) setDead();
			if (ticksExisted % 20 == 0) updateEntityList();
			if (ticksExisted < 1200 && ticksExisted % 5 == 0) pushAndHurtEntities();
			for (int i = 0; i < RivalRebels.tsarBombaSpeed * 2; i++)
			{
				if (tsar != null)
				{
					tsar.update(this);
					/*if (tsar.update())
					{
						tsar = null;
					}*/
				}
				else
				{
					return;
				}
			}
		}
	}
	
	List<Entity> entitylist = new ArrayList<Entity>();
	
	public void updateEntityList()
	{
		entitylist.clear();
		double ldist = radius*radius;
		for (int i = 0; i < worldObj.loadedEntityList.size(); i++)
		{
			Entity e = (Entity) worldObj.loadedEntityList.get(i);
			double dist = e.getDistanceSq(posX,posY,posZ);
			if (dist < ldist)
			{
				if ((e instanceof EntityPlayer && ((EntityPlayer) e).capabilities.isCreativeMode) || e instanceof EntityNuclearBlast || e instanceof EntityTachyonBombBlast || e == this) continue;
				entitylist.add(e);
			}
		}
	}
	
	public void pushAndHurtEntities()
	{
		List<Entity> remove = new ArrayList<Entity>();
		float invrad = 1.0f / (float) radius;
		for (Entity e : entitylist)
		{
			if (e.isDead || e.isEntityInvulnerable())
			{
				remove.add(e);
				continue;
			}
			float dx = (float) (e.posX - posX);
			float dy = (float) (e.posY - posY);
			float dz = (float) (e.posZ - posZ);
			float dist = MathHelper.sqrt_float(dx * dx + dy * dy + dz * dz);
			float rsqrt = 1.0f / (dist + 0.0001f);
			dx *= rsqrt;
			dy *= rsqrt;
			dz *= rsqrt;
			double f = 40.0f * (1.0f - dist * invrad) * ((e instanceof EntityB83 || e instanceof EntityHackB83) ? -1.0f : 1.0f);
			if (e instanceof EntityRhodes)
			{
				e.attackEntityFrom(RivalRebelsDamageSource.nuclearblast, (int) (radius*f*0.025f));
			}
			else
			{
				e.attackEntityFrom(RivalRebelsDamageSource.nuclearblast, (int) (f * f * 2.0f * radius + 20.0f));
				e.motionX -= dx * f;
				e.motionY -= dy * f;
				e.motionZ -= dz * f;
			}
		}
		for (Entity e : remove)
		{
			entitylist.remove(e);
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		motionX = nbt.getFloat("size");
		radius = nbt.getFloat("radius");
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setFloat("size", (float) motionX);
		nbt.setFloat("radius", (float) radius);
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
	
	public EntityTachyonBombBlast setTime()
	{
		ticksExisted = 920;
		return this;
	}
}
