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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
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

public class EntityLaserBurst extends EntityInanimate
{
	private EntityPlayer	shooter;
	
	public EntityLaserBurst(World par1World)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
	}
	
	public EntityLaserBurst(World par1World, EntityPlayer player)
	{
		super(par1World);
		shooter = player;
		setSize(0.5F, 0.5F);
		setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
		posX -= (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.2F);
		posY -= 0.12D;
		posZ -= (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.2F);
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
		setAccurateHeading(motionX, motionY, motionZ, 4F, 0.075f);
	}
	
	public EntityLaserBurst(World par1World, EntityPlayer player, boolean accurate)
	{
		super(par1World);
		shooter = player;
		setSize(0.5F, 0.5F);
		setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
		posX -= (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.2F);
		posY -= 0.12D;
		posZ -= (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.2F);
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
		setAccurateHeading(motionX, motionY, motionZ, 4F * (float)Math.random() + 1.0F, accurate?0.001F:0.075F);
	}
	public EntityLaserBurst(World par1World, double x, double y,double z, double mx, double my, double mz)
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
	
	public EntityLaserBurst(World par1World, double x, double y, double z, double mx, double my, double mz, EntityPlayer player)
	{
		super(par1World);
		shooter = player;
		setSize(0.5F, 0.5F);
		setPosition(x, y, z);
		yOffset = 0.0F;
		motionX = mx;
		motionZ = mz;
		motionY = my;
	}
	
	public void setAccurateHeading(double par1, double par3, double par5, float par7, float par8)
	{
		float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= var9;
		par3 /= var9;
		par5 /= var9;
		par1 += rand.nextGaussian() * par8;
		par3 += rand.nextGaussian() * par8;
		par5 += rand.nextGaussian() * par8;
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
		
		++ticksExisted;
		if (ticksExisted > 60) setDead();
		
		Vec3 var15 = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 var2 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition var3 = worldObj.rayTraceBlocks(var15, var2);
		var15 = Vec3.createVectorHelper(posX, posY, posZ);
		var2 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		
		if (var3 != null)
		{
			var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
		}
		
		if (!worldObj.isRemote)
		{
			Entity var4 = null;
			List var5 = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
			double var6 = 0.0D;
			Iterator var8 = var5.iterator();
			
			while (var8.hasNext())
			{
				Entity var9 = (Entity) var8.next();
				
				if (var9.canBeCollidedWith() && var9 != shooter)
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
			if (var3.entityHit == null)
			{
				Block blockID = worldObj.getBlock(var3.blockX, var3.blockY, var3.blockZ);
				if (blockID == Blocks.tnt)
				{
					if (!worldObj.isRemote)
					{
						EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldObj, (var3.blockX + 0.5F), (var3.blockY + 0.5F), (var3.blockZ + 0.5F), shooter);
						entitytntprimed.fuse = worldObj.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
						worldObj.spawnEntityInWorld(entitytntprimed);
						worldObj.setBlockToAir(var3.blockX, var3.blockY, var3.blockZ);
					}
				}
				if (blockID == RivalRebels.remotecharge)
				{
					RivalRebels.remotecharge.onBlockDestroyedByExplosion(worldObj, var3.blockX, var3.blockY, var3.blockZ, null);
				}
				if (blockID == RivalRebels.timedbomb)
				{
					RivalRebels.timedbomb.onBlockDestroyedByExplosion(worldObj, var3.blockX, var3.blockY, var3.blockZ, null);
				}
				setDead();
			}
			else
			{
				if (var3.entityHit instanceof EntityPlayer && shooter != var3.entityHit)
				{
					EntityPlayer player = (EntityPlayer) var3.entityHit;
					
					ItemStack armorSlots[] = player.inventory.armorInventory;
					int i = worldObj.rand.nextInt(4);
					if (armorSlots[i] != null && !worldObj.isRemote)
					{
						armorSlots[i].damageItem(24, player);
					}
					player.attackEntityFrom(RivalRebelsDamageSource.laserburst, 16);
					if (player.getHealth() < 3 && player.isEntityAlive())
					{
						player.attackEntityFrom(RivalRebelsDamageSource.laserburst, 2000000);
						player.deathTime = 0;
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 0, 0));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 1, 0));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 2, 0));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 2, 0));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 3, 0));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 3, 0));
					}
					setDead();
				}
				else if ((var3.entityHit instanceof EntityLivingBase
						&& !(var3.entityHit instanceof EntityAnimal)
						&& !(var3.entityHit instanceof EntityBat)
						&& !(var3.entityHit instanceof EntityVillager)
						&& !(var3.entityHit instanceof EntitySquid)))
				{
					EntityLivingBase entity = (EntityLivingBase) var3.entityHit;
					entity.attackEntityFrom(RivalRebelsDamageSource.laserburst, 6);
					if (entity.getHealth() < 3)
					{
						int legs = -1;
						int arms = -1;
						int mobs = -1;
						entity.setDead();
						RivalRebelsSoundPlayer.playSound(this, 2, 1, 4);
						if (entity instanceof EntityZombie && !(entity instanceof EntityPigZombie))
						{
							legs = 2;
							arms = 2;
							mobs = 1;
						}
						else if (entity instanceof EntityPigZombie)
						{
							legs = 2;
							arms = 2;
							mobs = 2;
						}
						else if (entity instanceof EntitySkeleton)
						{
							legs = 2;
							arms = 2;
							mobs = 3;
						}
						else if (entity instanceof EntityEnderman)
						{
							legs = 2;
							arms = 2;
							mobs = 4;
						}
						else if (entity instanceof EntityCreeper)
						{
							legs = 4;
							arms = 0;
							mobs = 5;
						}
						else if (entity instanceof EntitySlime && !(entity instanceof EntityMagmaCube))
						{
							legs = 0;
							arms = 0;
							mobs = 6;
						}
						else if (entity instanceof EntityMagmaCube)
						{
							legs = 0;
							arms = 0;
							mobs = 7;
						}
						else if (entity instanceof EntitySpider && !(entity instanceof EntityCaveSpider))
						{
							legs = 8;
							arms = 0;
							mobs = 8;
						}
						else if (entity instanceof EntityCaveSpider)
						{
							legs = 8;
							arms = 0;
							mobs = 9;
						}
						else if (entity instanceof EntityGhast)
						{
							legs = 9;
							arms = 0;
							mobs = 10;
						}
						else
						{
							legs = (int) (entity.boundingBox.getAverageEdgeLength() * 2);
							arms = (int) (entity.boundingBox.getAverageEdgeLength() * 2);
							mobs = 11;
						}
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 0, mobs));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 1, mobs));
						for (int i = 0; i < arms; i++)
							worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 2, mobs));
						for (int i = 0; i < legs; i++)
							worldObj.spawnEntityInWorld(new EntityGore(worldObj, var3.entityHit, 3, mobs));
					}
					setDead();
				}
				else if((var3.entityHit instanceof EntityRhodesHead
					  || var3.entityHit instanceof EntityRhodesLeftLowerArm
				      || var3.entityHit instanceof EntityRhodesLeftLowerLeg
				      || var3.entityHit instanceof EntityRhodesLeftUpperArm
				      || var3.entityHit instanceof EntityRhodesLeftUpperLeg
				      || var3.entityHit instanceof EntityRhodesRightLowerArm
				      || var3.entityHit instanceof EntityRhodesRightLowerLeg
				      || var3.entityHit instanceof EntityRhodesRightUpperArm
				      || var3.entityHit instanceof EntityRhodesRightUpperLeg
				      || var3.entityHit instanceof EntityRhodesTorso))
				{
					var3.entityHit.attackEntityFrom(RivalRebelsDamageSource.laserburst, 6);
				}
			}
		}
		
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		float var16 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
		
		for (rotationPitch = (float) (Math.atan2(motionY, var16) * 180.0D / Math.PI); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
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
	
	@Override
	protected void entityInit()
	{
	}
}
