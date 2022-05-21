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

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.BlackList;

public class EntityPlasmoid extends EntityInanimate
{
	private EntityPlayer	thrower;
	public int				rotation	= 45;
	public int				size		= 3;
	public float			slide		= 0;
	boolean gravity = false;
	
	public EntityPlasmoid(World par1World)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		size = 3;
	}
	
	public EntityPlasmoid(World par1World, double par2, double par4, double par6)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setPosition(par2, par4, par6);
		yOffset = 0.0F;
		size = 3;
	}
	
	public EntityPlasmoid(World par1World, EntityPlayer par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5)
	{
		super(par1World);
		thrower = par2EntityLiving;
		
		posY = par2EntityLiving.posY + par2EntityLiving.getEyeHeight() - 0.10000000149011612D;
		double var6 = par3EntityLiving.posX - par2EntityLiving.posX;
		double var8 = par3EntityLiving.posY + par3EntityLiving.getEyeHeight() - 0.699999988079071D - posY;
		double var10 = par3EntityLiving.posZ - par2EntityLiving.posZ;
		double var12 = MathHelper.sqrt_double(var6 * var6 + var10 * var10);
		
		if (var12 >= 1.0E-7D)
		{
			float var14 = (float) (Math.atan2(var10, var6) * 180.0D / Math.PI) - 90.0F;
			float var15 = (float) (-(Math.atan2(var8, var12) * 180.0D / Math.PI));
			double var16 = var6 / var12;
			double var18 = var10 / var12;
			setLocationAndAngles(par2EntityLiving.posX + var16, posY, par2EntityLiving.posZ + var18, var14, var15);
			yOffset = 0.0F;
			float var20 = (float) var12 * 0.2F;
			setAccurateHeading(var6, var8 + var20, var10, par4, par5);
		}
		size = 3;
	}
	
	public EntityPlasmoid(World par1World, EntityPlayer par2EntityLiving, float par3, boolean drop)
	{
		super(par1World);
		par3 *= (gravity ? 3 : 1);
		gravity = drop;
		thrower = par2EntityLiving;
		setSize(0.5F, 0.5F);
		setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
		posX -= (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		posY -= 0.0D;
		posZ -= (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
		setAccurateHeading(motionX, motionY, motionZ, par3 * 1.5F, 1.0F);
	}
	
	public EntityPlasmoid(World worldObj, double px, double py, double pz, double x, double y, double z, float d)
	{
		super(worldObj);
		setSize(0.5F, 0.5F);
		double f = d/MathHelper.sqrt_double(x*x+y*y+z*z);
		setPosition(px+x*f, py+y*f, pz+z*f);
		yOffset = 0.0F;
		size = 3;
		motionX = x;
		motionY = y;
		motionZ = z;
		float var10 = MathHelper.sqrt_double(x * x + z * z);
		prevRotationYaw = rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
		prevRotationPitch = rotationPitch = (float) (Math.atan2(y, var10) * 180.0D / Math.PI);
	}

	public void setAccurateHeading(double par1, double par3, double par5, float par7, float par8)
	{
		float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= var9;
		par3 /= var9;
		par5 /= var9;
		par1 *= par7;
		par3 *= par7;
		par5 *= par7;
		motionX = par1;
		motionY = par3;
		motionZ = par5;
		float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		prevRotationYaw = rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
		prevRotationPitch = rotationPitch = (float) (Math.atan2(par3, var10) * 180.0D / Math.PI);
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
		if (ticksExisted == 0)
		{
			rotation = worldObj.rand.nextInt(360);
			slide = worldObj.rand.nextInt(21) - 10;
		}
		if (gravity)
		{
			motionY -= 0.05f;
		}
		++ticksExisted;
		rotation += (int) slide;
		slide *= 0.9;
		if (ticksExisted >= RivalRebels.plasmoidDecay * (gravity ? 3 : 1)) explode();
		
		Vec3 vec31 = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec3 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition mop = worldObj.func_147447_a(vec31, vec3, false, true, false);
		vec31 = Vec3.createVectorHelper(posX, posY, posZ);
		if (mop != null) vec3 = Vec3.createVectorHelper(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
		else vec3 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double d0 = Double.MAX_VALUE;
		for (int i = 0; i < list.size(); ++i)
		{
			Entity entity = (Entity) list.get(i);
			if (entity.canBeCollidedWith() && (entity != thrower || ticksExisted >= 5))
			{
				MovingObjectPosition mop1 = entity.boundingBox.expand(0.5f, 0.5f, 0.5f).calculateIntercept(vec31, vec3);
				if (mop1 != null)
				{
					double d1 = vec31.squareDistanceTo(mop1.hitVec);
					if (d1 < d0)
					{
						mop = new MovingObjectPosition(entity, mop1.hitVec);
						d0 = d1;
					}
				}
			}
		}
		if (mop != null) explode();
		
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		float var16 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
		for (rotationPitch = (float) (Math.atan2(motionY, var16) * 180.0D / Math.PI); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
			;
		while (rotationPitch - prevRotationPitch >= 180.0F)
			prevRotationPitch += 360.0F;
		while (rotationYaw - prevRotationYaw < -180.0F)
			prevRotationYaw -= 360.0F;
		while (rotationYaw - prevRotationYaw >= 180.0F)
			prevRotationYaw += 360.0F;
		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		setPosition(posX, posY, posZ);
	}
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		
	}
	
	protected void explode()
	{
		if (!worldObj.isRemote)
		{
			setDead();
			Block block = Blocks.stone;
			int i = -1;
			while ((block.isOpaqueCube() || BlackList.plasmaExplosion(block)) && i < 4)
			{
				++i;
				block = worldObj.getBlock((int) (posX - motionX * i), (int) (posY - motionY * i), (int) (posZ - motionZ * i));
			}
			worldObj.setBlock((int) (posX - motionX * i), (int) (posY - motionY * i), (int) (posZ - motionZ * i), RivalRebels.plasmaexplosion);
		}
	}
	
	@Override
	protected void entityInit()
	{
	}
}

/*
 * package RivalRebels.Common.Entity; import net.minecraft.block.Block; import net.minecraft.entity.Entity; import net.minecraft.entity.player.EntityPlayer; import net.minecraft.init.Blocks; import
 * net.minecraft.nbt.NBTTagCompound; import net.minecraft.world.World; import RivalRebels.Common.Core.BlackList; import RivalRebels.Common.Core.RivalRebels; public class EntityPlasmoid extends
 * EntityPhysics { public float rotation = 0; public EntityPlasmoid(World par1World, EntityPlayer shooter, float par3) { super(par1World, shooter, par3); }
 * @Override public int getBrightnessForRender(float par1) { return 1000; }
 * @Override public float getBrightness(float par1) { return 1000F; }
 * @Override public boolean isInRangeToRenderDist(double par1) { return true; } protected double getGravity() { return 0; } protected double getFriction() { return 0.99; } protected double
 * getWaterFriction() { return 0.8; } protected double getWidth() { return 0.5; } protected double getVOff() { return 0.0; } protected double getFOff() { return 0.0; } protected double getSOff() {
 * return 0.0; } protected void onImpactBlock(Block block, int x, int y, int z, int s, int m, double vx, double vy, double vz) { explode(); } protected void onImpactEntity(Entity entity, int subHit,
 * Object hitInfo, double vx, double vy, double vz) { explode(); }
 * @Override protected int getMaxAge() { return 100; }
 * @Override protected void onEnterWater() { }
 * @Override protected void onDecay() { explode(); }
 * @Override protected boolean onFirstTick() { return false; }
 * @Override protected void entityInit() { }
 * @Override protected void readEntityFromNBT(NBTTagCompound p_70037_1_) { }
 * @Override protected void writeEntityToNBT(NBTTagCompound p_70014_1_) { } protected boolean onTick() { rotation += 0.3f; return false; }
 * @Override protected void onTickInWater() { } protected void explode() { if (!worldObj.isRemote) { setDead(); Block block = Blocks.stone; int i = -1; while ((block.isBlockNormalCube() ||
 * BlackList.plasmaExplosion(block)) && i < 4) { ++i; worldObj.getBlock((int) (posX - motionX * i), (int) (posY - motionY * i), (int) (posZ - motionZ * i)); } worldObj.setBlock((int) (posX - motionX *
 * i), (int) (posY - motionY * i), (int) (posZ - motionZ * i), RivalRebels.plasmaexplosion); } } }
 */
