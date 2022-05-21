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

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPassiveFire extends EntityInanimate
{
	private int		xTile;
	private int		yTile;
	private int		zTile;
	private World	world;
	private int		timer	= 0;
	private int		inTile;
	private int		inData;
	private boolean	inGround;
	
	public Entity	shootingEntity;
	private int		ticksInAir;
	private double	damage;
	
	public EntityPassiveFire(World par1World)
	{
		super(par1World);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		world = par1World;
		inTile = 0;
		inData = 0;
		inGround = false;
		ticksInAir = 0;
		damage = 2D;
		setSize(0.1F, 0.1F);
	}
	
	public EntityPassiveFire(World par1World, double par2, double par4, double par6)
	{
		super(par1World);
		world = par1World;
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inData = 0;
		inGround = false;
		ticksInAir = 0;
		damage = 2D;
		setSize(0.1F, 0.1F);
		setPosition(par2, par4, par6);
		yOffset = 0.0F;
	}
	
	public EntityPassiveFire(World par1World, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5)
	{
		super(par1World);
		world = par1World;
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inData = 0;
		inGround = false;
		ticksInAir = 0;
		damage = 2D;
		shootingEntity = par2EntityLiving;
		posY = (par2EntityLiving.posY + par2EntityLiving.getEyeHeight()) - 0.1D;
		double d = par3EntityLiving.posX - par2EntityLiving.posX;
		double d1 = (par3EntityLiving.posY + par3EntityLiving.getEyeHeight()) - 0.7D - posY;
		double d2 = par3EntityLiving.posZ - par2EntityLiving.posZ;
		double d3 = MathHelper.sqrt_double(d * d + d2 * d2);
		
		if (d3 < 9.9999999999999995E-008D)
		{
			return;
		}
		else
		{
			float f = (float) ((Math.atan2(d2, d) * 180D) / Math.PI) - 90F;
			float f1 = (float) (-((Math.atan2(d1, d3) * 180D) / Math.PI));
			double d4 = d / d3;
			double d5 = d2 / d3;
			setLocationAndAngles(par2EntityLiving.posX + d4, posY, par2EntityLiving.posZ + d5, f, f1);
			yOffset = 0.0F;
			float f2 = (float) d3 * 0.2F;
			setVelocity(d, d1 + f2, d2);
			return;
		}
		
	}
	
	public EntityPassiveFire(World par1World, Entity entity, float par3)
	{
		super(par1World);
		world = par1World;
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inData = 0;
		inGround = false;
		ticksInAir = 0;
		damage = 2D;
		shootingEntity = entity;
		setSize(0.1f, 0.1f);
		setLocationAndAngles(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
		rotationYaw = (rotationYaw + 25) % 360;
		posX -= MathHelper.cos((rotationYaw / 180F) * (float) Math.PI) * 0.16F;
		posY -= 0.2D;
		posZ -= MathHelper.sin((rotationYaw / 180F) * (float) Math.PI) * 0.16F;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = -MathHelper.sin((rotationYaw / 180F) * (float) Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float) Math.PI);
		motionZ = MathHelper.cos((rotationYaw / 180F) * (float) Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float) Math.PI);
		motionY = -MathHelper.sin((rotationPitch / 180F) * (float) Math.PI);
		setVelocity(motionX, motionY, motionZ);
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
		return (par1 <= 16);
	}
	
	public EntityPassiveFire(World world, int x, int y, int z, int mx, int my, int mz)
	{
		super(world);
		this.world = world;
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inData = 0;
		inGround = false;
		ticksInAir = 0;
		damage = 2D;
		setSize(0.3F, 0.3F);
		setPosition(x, y, z);
		motionX = mx;
		motionY = my;
		motionZ = mz;
		yOffset = 0.0F;
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	@Override
	public void setVelocity(double par1, double par3, double par5)
	{
		motionX = par1 + (worldObj.rand.nextFloat() - 0.5) / 50;
		motionY = par3 + (worldObj.rand.nextFloat() - 0.5) / 50;
		motionZ = par5 + (worldObj.rand.nextFloat() - 0.5) / 50;
	}
	
	@Override
	public void onUpdate()
	{
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		super.onUpdate();
		
		if (ticksInAir > 7)
		{
			this.setDead();
		}
		
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float var17 = 0.4F;
		float var18 = -0.02F;
		
		if (this.isInWater())
		{
			setDead();
		}
		
		this.motionX *= var17;
		this.motionY *= var17;
		this.motionZ *= var17;
		this.motionY -= var18;
		this.setPosition(this.posX, this.posY, this.posZ);
		ticksInAir++;
	}
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setShort("xTile", (short) xTile);
		par1NBTTagCompound.setShort("yTile", (short) yTile);
		par1NBTTagCompound.setShort("zTile", (short) zTile);
		par1NBTTagCompound.setByte("inTile", (byte) inTile);
		par1NBTTagCompound.setByte("inData", (byte) inData);
		par1NBTTagCompound.setByte("inGround", (byte) (inGround ? 1 : 0));
		par1NBTTagCompound.setDouble("damage", damage);
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		xTile = par1NBTTagCompound.getShort("xTile");
		yTile = par1NBTTagCompound.getShort("yTile");
		zTile = par1NBTTagCompound.getShort("zTile");
		inTile = par1NBTTagCompound.getByte("inTile") & 0xff;
		inData = par1NBTTagCompound.getByte("inData") & 0xff;
		inGround = par1NBTTagCompound.getByte("inGround") == 1;
		
		if (par1NBTTagCompound.hasKey("damage"))
		{
			damage = par1NBTTagCompound.getDouble("damage");
		}
	}
	
	@Override
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
}