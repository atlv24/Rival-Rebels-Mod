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

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.explosion.NuclearExplosion;

public class EntityB83NoShroom extends EntityB83
{
	public int	ticksInAir	= 0;
	
	public EntityB83NoShroom(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.5F);
	}
	
	public EntityB83NoShroom(World par1World, double x, double y, double z, double mx, double my, double mz)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setPosition(x, y, z);
		yOffset = 0.0F;
		setThrowableHeading(mx, my, mz, 5, 1);
	}
	
	@Override
	public void onUpdate()
	{
		motionX *= 0.99f/0.9f;
		motionY *= 0.99f/0.9f;
		motionZ *= 0.99f/0.9f;
		super.onUpdate();
	}
	
	public void explode()
	{
		new NuclearExplosion(worldObj, (int) posX, (int) posY, (int) posZ, RivalRebels.b83Strength/2);
		EntitySphereBlast etb = new EntitySphereBlast(worldObj, posX, posY, posZ, RivalRebels.b83Strength * 1.333333333f);
		etb.time = -920;
		worldObj.spawnEntityInWorld(etb);
		this.setDead();
	}
}
