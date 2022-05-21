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
import assets.rivalrebels.common.explosion.AntimatterBomb;
import assets.rivalrebels.common.explosion.NuclearExplosion;
import assets.rivalrebels.common.explosion.TsarBomba;

public class EntityAntimatterBomb extends EntityThrowable
{
	public int	ticksInAir	= 0;
	public int aoc = 0;
	public boolean hasTrollface;
	
	public EntityAntimatterBomb(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.5F);
	}
	
	public EntityAntimatterBomb(World par1World, double x, double y, double z, float yaw, float pitch, int charges, boolean troll)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setLocationAndAngles(x, y, z, yaw, pitch);
		yOffset = 0.0F;
		prevRotationYaw = rotationYaw = yaw;
		prevRotationPitch = rotationPitch = pitch;
		aoc = charges;
		hasTrollface = troll;
		if (!RivalRebels.nukedrop && !par1World.isRemote)
		{
			explode();
		}
	}
	
	public EntityAntimatterBomb(World worldObj, float px, float py, float pz, float f, float g, float h)
	{
		this(worldObj);
		setPosition(px, py, pz);
		yOffset = 0.0F;
		motionX = f;
		motionY = g;
		motionZ = h;
		aoc = 5;
		hasTrollface = true;
	}
	public EntityAntimatterBomb(World par1World, double x, double y,double z, double mx, double my, double mz, int charges)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setPosition(x,y,z);
		yOffset = 0.0F;
		aoc = charges;
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
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		
		if (!worldObj.isRemote)
		{
			if (ticksInAir == - 100) explode();
			++this.ticksInAir;
			
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
		nbt.setInteger("charge", aoc);
		nbt.setBoolean("troll", hasTrollface);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		aoc = nbt.getInteger("charge");
		hasTrollface = nbt.getBoolean("troll");
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
			Material m = b.getMaterial();
			if (b == RivalRebels.jump || b == Blocks.ice)
			{
				motionY = Math.max(-motionY, 0.2f);
				return;
			}
			if (hasTrollface && worldObj.rand.nextInt(10)!=0)
			{
				motionY = Math.max(-motionY, 0.2f);
				return;
			}
			else if (!hasTrollface && (m == Material.leaves || m == Material.clay || m == Material.ground || m == Material.plants || m == Material.cake || m == Material.circuits || m == Material.cactus || m == Material.cloth || m == Material.craftedSnow || m == Material.glass || m == Material.grass || m == Material.sand || m == Material.snow || m == Material.wood || m == Material.vine || m == Material.water || m == Material.sponge || m == Material.ice))
			{
				worldObj.setBlockToAir(var1.blockX, var1.blockY, var1.blockZ);
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
			AntimatterBomb tsar = new AntimatterBomb((int)posX, (int)posY, (int)posZ, worldObj, (int) ((RivalRebels.tsarBombaStrength + (aoc * aoc)) * 0.8f));
			EntityAntimatterBombBlast tsarblast = new EntityAntimatterBombBlast(worldObj, (int)posX, (int)posY, (int)posZ, tsar, RivalRebels.tsarBombaStrength + (aoc * aoc));
			worldObj.spawnEntityInWorld(tsarblast);
			this.setDead();
		}
	}
}
