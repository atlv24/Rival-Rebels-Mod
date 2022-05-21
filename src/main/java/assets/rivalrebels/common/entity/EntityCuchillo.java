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
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCuchillo extends EntityInanimate
{
	public Entity	shootingEntity;
	private boolean	inGround;
	private int		ticksInGround;
	
	public EntityCuchillo(World par1World)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
	}
	
	public EntityCuchillo(World par1World, EntityPlayer player, float par3)
	{
		super(par1World);
		shootingEntity = player;
		setSize(0.5F, 0.5F);
		setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
		posX -= (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		posY -= 0.1f;
		posZ -= (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI)) * par3;
		motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI)) * par3;
		motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI)) * par3;
	}
	public EntityCuchillo(World par1World, double x, double y,double z, double mx, double my, double mz)
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
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		ticksExisted++;
		if (!inGround)
		{
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
				if (entity.canBeCollidedWith() && (ticksExisted >= 10 || entity != shootingEntity))
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
			
			if (mop != null)
			{
				if (!worldObj.isRemote)
				{
					if (mop.entityHit != null)
					{
						mop.entityHit.attackEntityFrom(RivalRebelsDamageSource.cuchillo, 7);
						setDead();
					}
					else
					{
						Block block = worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
						Material hit = block.getMaterial();
						if (block == Blocks.glass || block == Blocks.glass_pane || block == Blocks.stained_glass || block == Blocks.stained_glass_pane)
						{
							worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.air);
							RivalRebelsSoundPlayer.playSound(this, 4, 0, 5F, 0.3F);
							return;
						}
						else if (hit == Material.rock)
						{
							worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, new ItemStack(RivalRebels.knife, 1)));
							setDead();
						}
						else
						{
							inGround = true;
						}
					}
				}
			}
			else
			{
				posX += motionX;
				posY += motionY;
				posZ += motionZ;
				rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
				while (rotationYaw - prevRotationYaw < -180.0F)
					prevRotationYaw -= 360.0F;
				while (rotationYaw - prevRotationYaw >= 180.0F)
					prevRotationYaw += 360.0F;
				rotationPitch -= 30;
				rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
				
				float friction = 0.98f;
				
				if (isInWater())
				{
					for (int var26 = 0; var26 < 4; ++var26)
						worldObj.spawnParticle("bubble", posX - motionX * 0.25F, posY - motionY * 0.25F, posZ - motionZ * 0.25F, motionX, motionY, motionZ);
					friction = 0.8F;
				}
				motionX *= friction;
				motionY *= friction;
				motionZ *= friction;
				motionY -= 0.026F;
				setPosition(posX, posY, posZ);
			}
		}
		else
		{
			motionX *= 0.2f;
			motionY *= 0.2f;
			motionZ *= 0.2f;
			ticksInGround++;
			if (ticksInGround == 60)
			{
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, new ItemStack(RivalRebels.knife, 1)));
				setDead();
			}
		}
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
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
	{
		if (!worldObj.isRemote && inGround)
		{
			if (!par1EntityPlayer.capabilities.isCreativeMode) par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(RivalRebels.knife, 1));
			worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			setDead();
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}
	
	@Override
	public boolean canAttackWithItem()
	{
		return false;
	}
}

/*
 * package RivalRebels.Common.Entity; import net.minecraft.block.Block; import net.minecraft.entity.Entity; import net.minecraft.entity.item.EntityItem; import
 * net.minecraft.entity.player.EntityPlayer; import net.minecraft.item.ItemStack; import net.minecraft.nbt.NBTTagCompound; import net.minecraft.world.World; import RivalRebels.Common.Core.RivalRebels;
 * import RivalRebels.Common.Core.RivalRebelsDamageSource; import cpw.mods.fml.relauncher.Side; import cpw.mods.fml.relauncher.SideOnly; public class EntityCuchillo extends EntityPhysics { boolean
 * frozen = false; public EntityCuchillo(World par1World, EntityPlayer player, float par3) { super(par1World, player, par3); }
 * @Override protected void entityInit() { }
 * @Override public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) { }
 * @Override public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) { }
 * @Override public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) { if (!worldObj.isRemote && frozen) { if (!par1EntityPlayer.capabilities.isCreativeMode)
 * par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(RivalRebels.knife, 1)); worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) *
 * 2.0F); setDead(); } }
 * @Override
 * @SideOnly(Side.CLIENT) public float getShadowSize() { return 0.0F; }
 * @Override public boolean canAttackWithItem() { return false; } private void pop() { if (!worldObj.isRemote) worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, new
 * ItemStack(RivalRebels.knife, 1))); }
 * @Override protected double getGravity() { return -0.05; }
 * @Override protected double getFriction() { return 0.95; }
 * @Override protected double getWaterFriction() { return 0.8; }
 * @Override protected int getMaxAge() { return 150; }
 * @Override protected double getWidth() { return 0.3; }
 * @Override protected double getVOff() { return 0; }
 * @Override protected double getFOff() { return 0; }
 * @Override protected double getSOff() { return 0; }
 * @Override protected void onImpactBlock(Block block, int x, int y, int z, int s, int m, double vx, double vy, double vz) { frozen = true; }
 * @Override protected void onImpactEntity(Entity entity, int subHit, Object hitInfo, double vx, double vy, double vz) { entity.attackEntityFrom(RivalRebelsDamageSource.cuchillo, 10); }
 * @Override protected void onEnterWater() { }
 * @Override protected boolean onFirstTick() { return false; }
 * @Override protected boolean onTick() { return frozen; }
 * @Override protected void onTickInWater() { }
 * @Override protected void onDecay() { } }
 */