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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.command.CommandHotPotato;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.NuclearExplosion;
import assets.rivalrebels.common.explosion.TsarBomba;

public class EntityHotPotato extends EntityThrowable
{
	public int	ticksExisted	= 0;
	public int round = 0;
	public int nextx = 0;
	public int nexty = 0;
	public int nextz = 0;
	public boolean dorounds = false;
	public int charges = RivalRebels.tsarBombaStrength + 9;
	
	public EntityHotPotato(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.5F);
	}
	
	public EntityHotPotato(World par1World, int x, int y, int z, int count)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setPosition(x+0.5f, y+0.5f, z+0.5f);
		yOffset = 0.0F;
		round = count;
		nextx = x;
		nexty = y;
		nextz = z;
		dorounds = true;
	}
	
	public EntityHotPotato(World worldObj, double px, double py, double pz, double f, double g, double h)
	{
		super(worldObj);
		setSize(0.5F, 0.5F);
		setPosition(px, py, pz);
		motionX = f;
		motionY = g;
		motionZ = h;
		yOffset = 0.0F;
		round = 1;
		nextx = (int)px;
		nexty = (int)py;
		nextz = (int)pz;
		ticksExisted = 1;
		dorounds = true;
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
		if (ticksExisted == -100) explode();
		++this.ticksExisted;
		if (ticksExisted < 2 && dorounds)
		{
			RivalRebelsSoundPlayer.playSound(worldObj, 14, 0, posX, posY, posZ, 100);
			motionX = 0;
			motionY = 0;
			motionZ = 0;
			setSize(0.5F, 0.5F);
			setPosition(nextx+0.5f, nexty+0.5f, nextz+0.5f);
			yOffset = 0.0F;
			worldObj.setBlock(nextx, nexty-400, nextz, RivalRebels.jump);
			setPosition(posX, posY, posZ);
			return;
		}
		
		if (!worldObj.isRemote)
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
			Entity var4 = null;
			List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
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
			
			if (var3 != null)
			{
				this.onImpact(var3);
			}
			
			if (worldObj.getBlock((int) posX, (int)posY, (int)posZ) == Blocks.water)
			{
				motionY += 0.06f;
			}
		}
		
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		if (posY < 0) setDead();
		
		if (this.ridingEntity==null)
		{
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
		
		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.05F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.05F;
		}
		float var17 = 0.98f;
		float var18 = this.getGravityVelocity();
		
		this.motionX *= var17;
		this.motionY *= var17;
		this.motionZ *= var17;
		this.motionY -= var18;
		this.setPosition(this.posX, this.posY, this.posZ);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("charge", charges);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		charges = nbt.getInteger("charge");
		if (charges == 0) charges = RivalRebels.tsarBombaStrength + 9;
		prevRotationYaw = rotationYaw = nbt.getFloat("rot");
	}
	
	@Override
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}
	
	@Override
	protected void onImpact(MovingObjectPosition var1)
	{
		if (var1.entityHit == null)
		{
			Block b = worldObj.getBlock(var1.blockX, var1.blockY, var1.blockZ);
			if (b == RivalRebels.jump || b == Blocks.ice)
			{
				motionY = Math.max(-motionY, 0.2f);
				return;
			}
			if (worldObj.rand.nextInt(10)!=0)
			{
				motionY = Math.max(-motionY, 0.2f);
				return;
			}
		}
		explode();
	}
	
	@Override
	protected float getGravityVelocity()
	{
		return 0.03F;
	}

	public void explode()
	{
		if (!worldObj.isRemote)
		{
			TsarBomba tsar = new TsarBomba((int)posX, (int)posY, (int)posZ, worldObj, charges);
			EntityTsarBlast tsarblast = new EntityTsarBlast(worldObj, (int)posX, (int)posY, (int)posZ, tsar, charges);
			worldObj.spawnEntityInWorld(tsarblast);
			ticksExisted = 0;
			round = round - 1;
			CommandHotPotato.roundinprogress = false;
			if (round <= 0) this.setDead();
		}
	}
}
