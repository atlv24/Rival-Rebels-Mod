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
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;

public class EntityRoddiskOfficer extends EntityInanimate
{
	public EntityPlayer	shooter;
	
	public EntityRoddiskOfficer(World par1World)
	{
		super(par1World);
	}
	
	public EntityRoddiskOfficer(World par1World, EntityPlayer par2EntityLiving, float par3)
	{
		super(par1World);
		this.shooter = par2EntityLiving;
		this.setSize(0.5F, 0.5F);
		this.boundingBox.setBounds(-0.4, -0.0625, -0.4, 0.4, 0.0625, 0.4);
		this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
		this.posX -= (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.posY -= 0.1;
		this.posZ -= (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
		this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
		this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
		this.setHeading(this.motionX, this.motionY, this.motionZ, par3 * 1.5F, 1.0F);
	}
	
	public void setHeading(double par1, double par3, double par5, float par7, float par8)
	{
		float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= var9;
		par3 /= var9;
		par5 /= var9;
		par1 += this.rand.nextGaussian() * 0.005 * par8;
		par3 += this.rand.nextGaussian() * 0.005 * par8;
		par5 += this.rand.nextGaussian() * 0.005 * par8;
		par1 *= par7;
		par3 *= par7;
		par5 *= par7;
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;
		float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, var10) * 180.0D / Math.PI);
	}
	
	@Override
	protected void entityInit()
	{
		
	}
	
	@Override
	public void onUpdate()
	{
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		
		if (ticksExisted > 100 && shooter == null && !worldObj.isRemote)
		{
			//worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, new ItemStack(RivalRebels.roddisk)));
			setDead();
			RivalRebelsSoundPlayer.playSound(this, 5, 0);
		}
		if (ticksExisted >= 120 && !worldObj.isRemote && shooter != null)
		{
			EntityItem ei = new EntityItem(worldObj, shooter.posX, shooter.posY, shooter.posZ, new ItemStack(RivalRebels.roddisk));
			worldObj.spawnEntityInWorld(ei);
			setDead();
			RivalRebelsSoundPlayer.playSound(this, 6, 1);
		}
		if (ticksExisted == 10)
		{
			RivalRebelsSoundPlayer.playSound(this, 6, 0);
		}
		
		int radius = 2;
		int nx = MathHelper.floor_double(posX - radius - 1.0D);
		int px = MathHelper.floor_double(posX + radius + 1.0D);
		int ny = MathHelper.floor_double(posY - radius - 1.0D);
		int py = MathHelper.floor_double(posY + radius + 1.0D);
		int nz = MathHelper.floor_double(posZ - radius - 1.0D);
		int pz = MathHelper.floor_double(posZ + radius + 1.0D);
		List par9 = worldObj.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(nx, ny, nz, px, py, pz));
		
		for (int var11 = 0; var11 < par9.size(); ++var11)
		{
			Entity var31 = (Entity) par9.get(var11);
			if (var31 instanceof EntityArrow)
			{
				var31.setDead();
			}
		}
		
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
			List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double var6 = 0.0D;
			Iterator var8 = var5.iterator();
			
			while (var8.hasNext())
			{
				Entity var9 = (Entity) var8.next();
				
				if (var9 instanceof EntityRoddiskRegular || var9 instanceof EntityRoddiskRebel)
				{
					var9.setDead();
					EntityItem ei = new EntityItem(worldObj, var9.posX, var9.posY, var9.posZ, new ItemStack(RivalRebels.roddisk));
					worldObj.spawnEntityInWorld(ei);
				}
				else if (var9 instanceof EntityRoddiskOfficer)
				{
					if (motionX + motionY + motionZ >= var9.motionX + var9.motionY + var9.motionZ)
					{
						var9.setDead();
					}
					else
					{
						setDead();
					}
					EntityItem ei = new EntityItem(worldObj, var9.posX, var9.posY, var9.posZ, new ItemStack(RivalRebels.roddisk));
					worldObj.spawnEntityInWorld(ei);
				}
				else if (var9.canBeCollidedWith() && var9 != this.shooter)
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
			worldObj.spawnParticle("explode", var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord, motionX * 0.1, motionY * 0.1, motionZ * 0.1);
			worldObj.spawnParticle("explode", var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord, motionX * 0.1, motionY * 0.1, motionZ * 0.1);
			worldObj.spawnParticle("explode", var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord, motionX * 0.1, motionY * 0.1, motionZ * 0.1);
			worldObj.spawnParticle("explode", var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord, motionX * 0.1, motionY * 0.1, motionZ * 0.1);
			
			if (var3.entityHit != null)
			{
				RivalRebelsSoundPlayer.playSound(this, 5, 1);
				if (var3.entityHit instanceof EntityPlayer && shooter instanceof EntityPlayer && var3.entityHit != shooter)
				{
					EntityPlayer entityPlayerHit = (EntityPlayer) var3.entityHit;
					ItemStack armorSlots[] = entityPlayerHit.inventory.armorInventory;
					for (int i = 0; i < 4; i++)
					{
						if (armorSlots[i] != null)
						{
							armorSlots[i].damageItem(20, entityPlayerHit);
							entityPlayerHit.attackEntityFrom(RivalRebelsDamageSource.tron, 1);
						}
						else
						{
							entityPlayerHit.attackEntityFrom(RivalRebelsDamageSource.tron, 10);
						}
					}
				}
				else
				{
					var3.entityHit.attackEntityFrom(RivalRebelsDamageSource.tron, 10);
					if (var3.entityHit instanceof EntitySkeleton)
					{
						var3.entityHit.setDead();
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 0, 3));
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 1, 3));
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 2, 3));
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 2, 3));
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 3, 3));
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 3, 3));
					}
					if (var3.entityHit instanceof EntityZombie && !(var3.entityHit instanceof EntityPigZombie))
					{
						var3.entityHit.setDead();
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 0, 1));
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 1, 1));
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 2, 1));
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 2, 1));
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 3, 1));
						this.worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 3, 1));
					}
				}
			}
			else if (worldObj.getBlock(var3.blockX, var3.blockY, var3.blockZ) == RivalRebels.flare)
			{
				RivalRebels.flare.onBlockDestroyedByPlayer(worldObj, var3.blockX, var3.blockY, var3.blockZ, 0);
			}
			else if (worldObj.getBlock(var3.blockX, var3.blockY, var3.blockZ) == RivalRebels.landmine || worldObj.getBlock(var3.blockX, var3.blockY, var3.blockZ) == RivalRebels.alandmine)
			{
				RivalRebels.landmine.onEntityCollidedWithBlock(worldObj, var3.blockX, var3.blockY, var3.blockZ, this);
			}
			else
			{
				Block block = worldObj.getBlock(var3.blockX, var3.blockY, var3.blockZ);
				if (block == Blocks.glass || block == Blocks.glass_pane)
				{
					worldObj.setBlock(var3.blockX, var3.blockY, var3.blockZ, Blocks.air);
				}
				RivalRebelsSoundPlayer.playSound(this, 5, 2);
				
				if (var3.sideHit == 4 || var3.sideHit == 5) this.motionX *= -1;
				if (var3.sideHit == 0 || var3.sideHit == 1) this.motionY *= -1;
				if (var3.sideHit == 2 || var3.sideHit == 3) this.motionZ *= -1;
			}
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
		
		if (shooter != null)
		{
			motionX += (shooter.posX - posX) * 0.01f;
			motionY += ((shooter.posY + 1.62) - posY) * 0.01f;
			motionZ += (shooter.posZ - posZ) * 0.01f;
		}
		motionX *= 0.995f;
		motionY *= 0.995f;
		motionZ *= 0.995f;
		
		this.setPosition(this.posX, this.posY, this.posZ);
	}
	
	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		if (ticksExisted < 10 || player != shooter) return false;
		if (player.inventory.addItemStackToInventory(new ItemStack(RivalRebels.roddisk)))
		{
			setDead();
			RivalRebelsSoundPlayer.playSound(this, 6, 1);
		}
		return true;
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
	public void readEntityFromNBT(NBTTagCompound var1)
	{
		
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound var1)
	{
		
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.boundingBox;
	}
	
	/**
	 * returns the bounding box for this entity
	 */
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}
	
	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	@Override
	public boolean canBePushed()
	{
		return true;
	}
}
