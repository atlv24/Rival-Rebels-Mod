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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.NuclearExplosion;

public class EntityBomb extends EntityThrowable
{
	public int	ticksInAir	= 0;
	public int timeleft = 20;
	public boolean exploded = false;
	public boolean hit = false;
	
	public EntityBomb(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.5F);
	}
	
	public EntityBomb(World par1World, double x, double y, double z, float yaw, float pitch)
	{
		this(par1World);
		setLocationAndAngles(x, y, z, yaw, pitch);
		yOffset = 0.0F;
		motionX = -(-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
	}
	public EntityBomb(World par1World, double x, double y,double z, double mx, double my, double mz)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setPosition(x+mx*1.4f,y+my*1.4f,z+mz*1.4f);
		yOffset = 0.0F;
		setAnglesMotion(mx, my, mz);
	}
	
	public EntityBomb(World par1World, EntityPlayer entity2, float par3)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setLocationAndAngles(entity2.posX, entity2.posY + entity2.getEyeHeight(), entity2.posZ, entity2.rotationYaw, entity2.rotationPitch);
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		setThrowableHeading(motionX, motionY, motionZ, 2.5f, 0.1f);
	}
	
	public void setAnglesMotion(double mx, double my, double mz)
	{
		motionX = mx;
		motionY = my;
		motionZ = mz;
		prevRotationYaw = rotationYaw = (float) (Math.atan2(mx, mz) * 180.0D / Math.PI);
		prevRotationPitch = rotationPitch = (float) (Math.atan2(my, MathHelper.sqrt_double(mx * mx + mz * mz)) * 180.0D / Math.PI);
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		if (ticksInAir == - 100) explode(false);
		++this.ticksInAir;
		
		if (exploded)
		{
			motionY = 0;
			if (hit) motionY = 1;
			motionX = 0;
			motionZ = 0;
			timeleft--;
			if (timeleft < 0) setDead();
			ticksExisted++;
		}
		else
		{
			Vec3 var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var15, var2);
			var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			
			if (var3 != null)
			{
				var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
			}
			
			if (!this.worldObj.isRemote)
			{
				Entity var4 = null;
				List var5 = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
				double var6 = 0.0D;
				Iterator var8 = var5.iterator();
				
				while (var8.hasNext())
				{
					Entity var9 = (Entity) var8.next();
					
					if (var9.canBeCollidedWith())
					{
						float var10 = 0.3F;
						AxisAlignedBB var11 = var9.boundingBox.expand(var10, var10, var10);
						MovingObjectPosition var12 = var11.calculateIntercept(var15, var2);
						
						if (var12 != null)
						{
							double var13 = var15.distanceTo(var12.hitVec);
							
							if (var13 < var6 || var6 == 0.0D)
							{
								var4 = var9;
								var6 = var13;
							}
						}
					}
				}
				
				if (var4 != null)
				{
					var3 = new MovingObjectPosition(var4);
				}
			}
			
			if (var3 != null)
			{
				this.onImpact(var3);
			}
			
			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			
			for (this.rotationPitch = (float) (Math.atan2(this.motionY, var16) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
			{
				;
			}
			
			while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
			{
				this.prevRotationPitch += 360.0F;
			}
			
			while (this.rotationYaw - this.prevRotationYaw < -180.0F)
			{
				this.prevRotationYaw -= 360.0F;
			}
			
			while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
			{
				this.prevRotationYaw += 360.0F;
			}
			
			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float var17 = 0.95f;
			float var18 = this.getGravityVelocity();
			
			this.motionX *= var17;
			this.motionY *= var17;
			this.motionZ *= var17;
			this.motionY -= var18;
		}
		this.setPosition(this.posX, this.posY, this.posZ);
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
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}
	
	@Override
	protected void onImpact(MovingObjectPosition var1)
	{
		if (var1.entityHit != null)
		{
			var1.entityHit.attackEntityFrom(RivalRebelsDamageSource.rocket, (var1.entityHit instanceof EntityPlayer ? 20 : 300));
			explode(true);
		}
		else
		{
			explode(false);
		}
	}
	
	@Override
	protected float getGravityVelocity()
	{
		return 0.1F;
	}

	public void explode(boolean b)
	{
		exploded = true;
		hit = b;
		ticksExisted = 0;
		if (Math.random() > 0.8f) RivalRebelsSoundPlayer.playSound(this, 23, 0, 20, 0.4f + (float)Math.random() * 0.3f);
		if (!worldObj.isRemote && !b)
		{
			int r = 2;
			for (int x = -r; x <= r; x++)
			{
				for (int y = -r; y <= r; y++)
				{
					for (int z = -r; z <= r; z++)
					{
						worldObj.setBlockToAir((int)(posX+x), (int)(Math.max(posY, r+1)+y), (int)(posZ+z));
					}
				}
			}
		}
	}
}
