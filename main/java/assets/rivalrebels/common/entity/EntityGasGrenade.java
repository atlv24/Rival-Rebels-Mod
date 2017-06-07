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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityGasGrenade extends EntityInanimate
{
	private boolean	inGround	= false;
	
	public Entity	shootingEntity;
	private int		ticksInAir	= 0;
	
	public EntityGasGrenade(World par1World)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
	}
	
	public EntityGasGrenade(World par1World, double par2, double par4, double par6)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setPosition(par2, par4, par6);
		yOffset = 0.0F;
	}
	
	public EntityGasGrenade(World par1World, double x, double y,double z, double mx, double my, double mz)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setPosition(x,y,z);
		yOffset = 0.0F;
		setAnglesMotion(mx, my, mz);
	}
	
	public void setAnglesMotion(double mx, double my, double mz)
	{
		motionX = mx;
		motionY = my;
		motionZ = mz;
		prevRotationYaw = rotationYaw = (float) (Math.atan2(mx, mz) * 180.0D / Math.PI);
		prevRotationPitch = rotationPitch = (float) (Math.atan2(my, MathHelper.sqrt_double(mx * mx + mz * mz)) * 180.0D / Math.PI);
	}
	
	public EntityGasGrenade(World par1World, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5)
	{
		super(par1World);
		shootingEntity = par2EntityLiving;
		
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
			setArrowHeading(var6, var8 + var20, var10, par4, par5);
		}
	}
	
	public EntityGasGrenade(World par1World, EntityPlayer player, float par3)
	{
		super(par1World);
		shootingEntity = player;
		
		setSize(0.5F, 0.5F);
		setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
		posX -= (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		posY -= 0.10000000149011612D;
		posZ -= (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
		setArrowHeading(motionX, motionY, motionZ, par3 * 1.5F, 1.0F);
	}
	
	@Override
	protected void entityInit()
	{
		dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}
	
	/**
	 * Uses the provided coordinates as a heading and determines the velocity from it with the set force and random variance. Args: x, y, z, force, forceVariation
	 */
	public void setArrowHeading(double par1, double par3, double par5, float par7, float par8)
	{
		float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= var9;
		par3 /= var9;
		par5 /= var9;
		par1 += rand.nextGaussian() * 0.007499999832361937D * par8;
		par3 += rand.nextGaussian() * 0.007499999832361937D * par8;
		par5 += rand.nextGaussian() * 0.007499999832361937D * par8;
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
	@SideOnly(Side.CLIENT)
	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
	{
		setPosition(par1, par3, par5);
		setRotation(par7, par8);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	public void setVelocity(double par1, double par3, double par5)
	{
		motionX = par1;
		motionY = par3;
		motionZ = par5;
		
		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
		{
			float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
			prevRotationYaw = rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
			prevRotationPitch = rotationPitch = (float) (Math.atan2(par3, var7) * 180.0D / Math.PI);
			prevRotationPitch = rotationPitch;
			prevRotationYaw = rotationYaw;
			setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
		}
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
		{
			float var1 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			prevRotationYaw = rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
			prevRotationPitch = rotationPitch = (float) (Math.atan2(motionY, var1) * 180.0D / Math.PI);
		}
		++ticksInAir;
		Vec3 var17 = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 var3 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition var4 = worldObj.func_147447_a(var17, var3, false, true, false);
		var17 = Vec3.createVectorHelper(posX, posY, posZ);
		var3 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		
		if (var4 != null)
		{
			var3 = Vec3.createVectorHelper(var4.hitVec.xCoord, var4.hitVec.yCoord, var4.hitVec.zCoord);
		}
		
		Entity var5 = null;
		List var6 = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double var7 = 0.0D;
		Iterator var9 = var6.iterator();
		
		if (!worldObj.isRemote)
		{
			while (var9.hasNext())
			{
				Entity var10 = (Entity) var9.next();
				
				if (var10.canBeCollidedWith() && (var10 != shootingEntity || ticksInAir >= 5))
				{
					AxisAlignedBB var12 = var10.boundingBox.expand(0.3f, 0.3f, 0.3f);
					MovingObjectPosition var13 = var12.calculateIntercept(var17, var3);
					
					if (var13 != null)
					{
						double var14 = var17.distanceTo(var13.hitVec);
						
						if (var14 < var7 || var7 == 0.0D)
						{
							var5 = var10;
							var7 = var14;
						}
					}
				}
			}
		}
		
		if (var5 != null)
		{
			var4 = new MovingObjectPosition(var5);
		}
		
		float var20;
		
		if (var4 != null)
		{
			pop();
			setDead();
		}
		
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		var20 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
		
		for (rotationPitch = (float) (Math.atan2(motionY, var20) * 180.0D / Math.PI); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
		{
			;
		}
		
		while (rotationPitch - prevRotationPitch >= 180.0F)
		{
			prevRotationPitch += 360.0F;
		}
		
		while (rotationYaw - prevRotationYaw < -180.0F)
		{
			prevRotationYaw -= 360.0F;
		}
		
		while (rotationYaw - prevRotationYaw >= 180.0F)
		{
			prevRotationYaw += 360.0F;
		}
		
		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		rotationPitch = rotationPitch + 90;
		if (rotationPitch <= -270)
		{
			rotationPitch = 90;
		}
		float var23 = 0.9999F;
		
		if (isInWater())
		{
			for (int var26 = 0; var26 < 4; ++var26)
			{
				float var27 = 0.25F;
				worldObj.spawnParticle("bubble", posX - motionX * var27, posY - motionY * var27, posZ - motionZ * var27, motionX, motionY, motionZ);
			}
			
			var23 = 0.8F;
		}
		
		motionX *= var23;
		motionY *= var23;
		motionZ *= var23;
		motionY -= 0.03f;
		setPosition(posX, posY, posZ);
		func_145775_I();
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}
	
	/**
	 * If returns false, the item will not inflict any damage against entities.
	 */
	@Override
	public boolean canAttackWithItem()
	{
		return false;
	}
	
	public void func_70243_d(boolean par1)
	{
		byte var2 = dataWatcher.getWatchableObjectByte(16);
		
		if (par1)
		{
			dataWatcher.updateObject(16, Byte.valueOf((byte) (var2 | 1)));
		}
		else
		{
			dataWatcher.updateObject(16, Byte.valueOf((byte) (var2 & -2)));
		}
	}
	
	public boolean func_70241_g()
	{
		byte var1 = dataWatcher.getWatchableObjectByte(16);
		return (var1 & 1) != 0;
	}
	
	private void pop()
	{
		RivalRebelsSoundPlayer.playSound(this, 9, 1);
		for (int x = -4; x <= 4; x++)
		{
			for (int y = -4; y <= 4; y++)
			{
				for (int z = -4; z <= 4; z++)
				{
					if (worldObj.getBlock((int) posX + x, (int) posY + y, (int) posZ + z) == Blocks.air)
					{
						worldObj.setBlock((int) posX + x, (int) posY + y, (int) posZ + z, RivalRebels.toxicgas);
					}
				}
			}
		}
	}
}
