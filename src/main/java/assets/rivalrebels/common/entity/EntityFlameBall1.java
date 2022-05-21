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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.tileentity.TileEntityReciever;

public class EntityFlameBall1 extends EntityInanimate
{
	public int		sequence;
	public float	rotation;
	public float	motionr;
	
	public EntityFlameBall1(World par1World)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		rotation = (float) (Math.random() * 360);
		motionr = (float) (Math.random() - 0.5f) * 5;
	}
	
	public EntityFlameBall1(World par1World, double par2, double par4, double par6)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setPosition(par2, par4, par6);
		rotation = (float) (Math.random() * 360);
		motionr = (float) (Math.random() - 0.5f) * 5;
	}
	
	public EntityFlameBall1(World par1World, EntityPlayer player, float par3)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setPosition(player.posX, player.posY + player.getEyeHeight(), player.posZ);
		motionX = (-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI));
		motionZ = (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI));
		motionY = (-MathHelper.sin(player.rotationPitch / 180.0F * (float) Math.PI));
		posX -= (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * 0.2F);
		posY -= 0.13;
		posZ -= (MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI) * 0.2F);
		motionX *= par3;
		motionY *= par3;
		motionZ *= par3;
		rotation = (float) (Math.random() * 360);
		motionr = (float) (Math.random() - 0.5f) * 5;
		// Side side = FMLCommonHandler.instance().getEffectiveSide();
		// if (side == Side.SERVER)
		// {
		// ByteArrayOutputStream bos = new ByteArrayOutputStream(9);
		// DataOutputStream outputStream = new DataOutputStream(bos);
		// try
		// {
		// outputStream.writeInt(24);
		// outputStream.writeInt(entityId);
		// outputStream.writeByte((byte)mode);
		// }
		// catch (Exception ex)
		// {
		// ex.printStackTrace();
		// }
		// finally
		// {
		// try
		// {
		// if (outputStream != null) outputStream.close();
		// }
		// catch (IOException error)
		// {
		//
		// }
		// }
		// Packet250CustomPayload packet = new Packet250CustomPayload();
		// packet.channel = "RodolRivalRebels";
		// packet.data = bos.toByteArray();
		// packet.length = bos.size();
		// PacketDispatcher.sendPacketToAllPlayers(packet);
		// }
	}
	
	public EntityFlameBall1(World par1World, TileEntityReciever ter, float f)
	{
		super(par1World);
		rotationYaw = (float) (180 - ter.yaw);
		rotationPitch = (float) (-ter.pitch);
		setSize(0.5F, 0.5F);
		setPosition(ter.xCoord + ter.xO + 0.5, ter.yCoord + 0.75, ter.zCoord + ter.zO + 0.5);
		motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
		motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
		motionX *= f;
		motionY *= f;
		motionZ *= f;
		rotation = (float) (Math.random() * 360);
		motionr = (float) (Math.random() - 0.5f) * 5;
	}
	
	public EntityFlameBall1(World world, double x, double y, double z, double mx, double my, double mz)
	{
		super(world);
		setSize(0.5F, 0.5F);
		setPosition(x, y, z);
		motionX = mx;
		motionY = my;
		motionZ = mz;
		rotation = (float) (Math.random() * 360);
		motionr = (float) (Math.random() - 0.5f) * 5;
	}
	
	@Override
	public void onUpdate()
	{
		lastTickPosX = posX;
		lastTickPosY = posY;
		lastTickPosZ = posZ;
		super.onUpdate();
		ticksExisted++;
		if (ticksExisted > 5) sequence++;
		if (sequence > 15/* > RivalRebels.flamethrowerDecay */) setDead();
		
		Vec3 start = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 end = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition mop = worldObj.rayTraceBlocks(start, end);
		start = Vec3.createVectorHelper(posX, posY, posZ);
		end = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		
		if (mop != null) end = Vec3.createVectorHelper(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
		
		Entity e = null;
		List var5 = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double var6 = 0.0D;
		Iterator var8 = var5.iterator();
		
		if (!worldObj.isRemote)
		{
			while (var8.hasNext())
			{
				Entity var9 = (Entity) var8.next();
				
				if (var9.canBeCollidedWith())
				{
					float var10 = 0.3F;
					AxisAlignedBB var11 = var9.boundingBox.expand(var10, var10, var10);
					MovingObjectPosition var12 = var11.calculateIntercept(start, end);
					
					if (var12 != null)
					{
						double var13 = start.distanceTo(var12.hitVec);
						
						if (var13 < var6 || var6 == 0.0D)
						{
							e = var9;
							var6 = var13;
						}
					}
				}
			}
		}
		
		if (e != null)
		{
			mop = new MovingObjectPosition(e);
		}
		
		if (mop != null && ticksExisted >= 5)
		{
			fire();
			setDead();
			if (mop.entityHit != null)
			{
				mop.entityHit.setFire(3);
				mop.entityHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 12);
				if (mop.entityHit != null && mop.entityHit instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer) mop.entityHit;
					ItemStack armorSlots[] = player.inventory.armorInventory;
					int i = worldObj.rand.nextInt(4);
					if (armorSlots[i] != null && !worldObj.isRemote)
					{
						armorSlots[i].damageItem(8, player);
					}
				}
			}
			else if (mop.typeOfHit == MovingObjectType.ENTITY)
			{
				worldObj.spawnEntityInWorld(new EntityLightningLink(worldObj, this, this.getDistanceToEntity(mop.entityHit)));
				if (mop.entityHit instanceof EntityPlayer)
				{
					EntityPlayer entityPlayerHit = (EntityPlayer) mop.entityHit;
					ItemStack armorSlots[] = entityPlayerHit.inventory.armorInventory;
					int i = worldObj.rand.nextInt(4);
					if (armorSlots[i] != null)
					{
						armorSlots[i].damageItem(44, entityPlayerHit);
					}
					entityPlayerHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 250 / ((int) mop.entityHit.getDistanceToEntity(this) + 1));
				}
				else if (mop.entityHit instanceof EntityB2Spirit)
				{
					mop.entityHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 250 / ((int) mop.entityHit.getDistanceToEntity(this) + 1));
				}
				else if (mop.entityHit.canAttackWithItem())
				{
					mop.entityHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 250 / ((int) mop.entityHit.getDistanceToEntity(this) + 1));
				}
			}
		}
		
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		
		rotation += motionr;
		motionr *= 1.06f;
		
		if (isInWater()) setDead();
		float airFriction = 0.97F;
		float gravity = 0.01F;
		motionX *= airFriction;
		motionY *= airFriction;
		motionZ *= airFriction;
		motionY -= gravity;
		setPosition(posX, posY, posZ);
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
	protected void entityInit()
	{
	}
	
	@Override
	public int getBrightnessForRender(float par1)
	{
		return 1000;
	}
	
	@Override
	public float getBrightness(float par1)
	{
		return 1000;
	}
	
	@Override
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}
	
	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}
	
	@Override
	public boolean canAttackWithItem()
	{
		return false;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass)
	{
		return true;
	}
	
	private void fire()
	{
		if (!worldObj.isRemote)
		{
			for (int x = -1; x < 2; x++)
			{
				for (int y = -1; y < 2; y++)
				{
					for (int z = -1; z < 2; z++)
					{
						Block id = worldObj.getBlock((int) posX + x, (int) posY + y, (int) posZ + z);
						if (id == Blocks.air || id == Blocks.snow || id == Blocks.ice) worldObj.setBlock((int) posX + x, (int) posY + y, (int) posZ + z, Blocks.fire);
						else if (id == Blocks.leaves || id == Blocks.leaves2) worldObj.setBlock((int) posX + x, (int) posY + y, (int) posZ + z, Blocks.fire);
						else if (id == Blocks.grass && worldObj.rand.nextInt(5) == 0) worldObj.setBlock((int) posX + x, (int) posY + y, (int) posZ + z, Blocks.dirt);
						else if (id == RivalRebels.flare) RivalRebels.flare.onBlockDestroyedByPlayer(worldObj, (int) posX + x, (int) posY + y, (int) posZ + z, 0);
					}
				}
			}
		}
	}
	// private int xTile;
	// private int yTile;
	// private int zTile;
	// private World world;
	// private int timer = 0;
	// private int inTile;
	// private int inData;
	// private boolean inGround;
	//
	// public Entity shootingEntity;
	// private int ticksInAir;
	// private double damage;
	//
	// public EntityFlameBall(World par1World)
	// {
	// super(par1World);
	// xTile = -1;
	// yTile = -1;
	// zTile = -1;
	// world = par1World;
	// inTile = 0;
	// inData = 0;
	// inGround = false;
	// ticksInAir = 0;
	// damage = 2D;
	// setSize(0.1F, 0.1F);
	//
	// }
	//
	// public EntityFlameBall(World par1World, double par2, double par4, double par6)
	// {
	// super(par1World);
	// world = par1World;
	// xTile = -1;
	// yTile = -1;
	// zTile = -1;
	// inTile = 0;
	// inData = 0;
	// inGround = false;
	// ticksInAir = 0;
	// damage = 2D;
	// setSize(0.5F, 0.5F);
	// setPosition(par2, par4, par6);
	// yOffset = 0.0F;
	// }
	//
	// public EntityFlameBall(World par1World, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5)
	// {
	// super(par1World);
	// world = par1World;
	// xTile = -1;
	// yTile = -1;
	// zTile = -1;
	// inTile = 0;
	// inData = 0;
	// inGround = false;
	// ticksInAir = 0;
	// damage = 2D;
	// shootingEntity = par2EntityLiving;
	// posY = (par2EntityLiving.posY + par2EntityLiving.getEyeHeight()) - 0.1D;
	// double d = par3EntityLiving.posX - par2EntityLiving.posX;
	// double d1 = (par3EntityLiving.posY + par3EntityLiving.getEyeHeight()) - 0.7D - posY;
	// double d2 = par3EntityLiving.posZ - par2EntityLiving.posZ;
	// double d3 = MathHelper.sqrt_double(d * d + d2 * d2);
	//
	// if (d3 < 9.9999999999999995E-008D)
	// {
	// return;
	// }
	// else
	// {
	// float f = (float) ((Math.atan2(d2, d) * 180D) / Math.PI) - 90F;
	// float f1 = (float) (-((Math.atan2(d1, d3) * 180D) / Math.PI));
	// double d4 = d / d3;
	// double d5 = d2 / d3;
	// setLocationAndAngles(par2EntityLiving.posX + d4, posY, par2EntityLiving.posZ + d5, f, f1);
	// yOffset = 0.0F;
	// float f2 = (float) d3 * 0.2F;
	// setVelocity(d, d1 + f2, d2);
	// return;
	// }
	//
	// }
	//
	// public EntityFlameBall(World par1World, EntityPlayer player, float par3)
	// {
	// super(par1World);
	// world = par1World;
	// xTile = -1;
	// yTile = -1;
	// zTile = -1;
	// inTile = 0;
	// inData = 0;
	// inGround = false;
	// ticksInAir = 0;
	// damage = 2D;
	// shootingEntity = player;
	// setSize(0.5F, 0.5F);
	// setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
	// posX -= MathHelper.cos((rotationYaw / 180F) * (float) Math.PI) * 0.16F;
	// posY -= 0.10000000149011612D;
	// posZ -= MathHelper.sin((rotationYaw / 180F) * (float) Math.PI) * 0.16F;
	// setPosition(posX, posY, posZ);
	// yOffset = 0.0F;
	// motionX = -MathHelper.sin((rotationYaw / 180F) * (float) Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float) Math.PI);
	// motionZ = MathHelper.cos((rotationYaw / 180F) * (float) Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float) Math.PI);
	// motionY = -MathHelper.sin((rotationPitch / 180F) * (float) Math.PI);
	// setVelocity(motionX, motionY, motionZ);
	// }
	//
	// @Override
	// public int getBrightnessForRender(float par1)
	// {
	// return 1000;
	// }
	//
	// @Override
	// public float getBrightness(float par1)
	// {
	// return 1000F;
	// }
	//
	// @Override
	// public boolean isInRangeToRenderVec3D(Vec3 par1Vec3)
	// {
	// return true;
	// }
	//
	// @Override
	// public boolean isInRangeToRenderDist(double par1)
	// {
	// return true;
	// }
	//
	// public EntityFlameBall(World world, int x, int y, int z, int mx, int my, int mz)
	// {
	// super(world);
	// this.world = world;
	// xTile = -1;
	// yTile = -1;
	// zTile = -1;
	// inTile = 0;
	// inData = 0;
	// inGround = false;
	// ticksInAir = 0;
	// damage = 2D;
	// setSize(0.5F, 0.5F);
	// setPosition(x, y, z);
	// motionX = mx;
	// motionY = my;
	// motionZ = mz;
	// yOffset = 0.0F;
	// }
	//
	// @Override
	// protected void entityInit()
	// {
	// }
	//
	// /**
	// * Sets the velocity to the args. Args: x, y, z
	// */
	// @Override
	// public void setVelocity(double par1, double par3, double par5)
	// {
	// motionX = par1;
	// motionY = par3;
	// motionZ = par5;
	//
	// if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
	// {
	// float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
	// prevRotationYaw = rotationYaw = (float) ((Math.atan2(par1, par5) * 180D) / Math.PI);
	// prevRotationPitch = rotationPitch = (float) ((Math.atan2(par3, f) * 180D) / Math.PI);
	// prevRotationPitch = rotationPitch;
	// prevRotationYaw = rotationYaw;
	// setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
	// }
	// }
	//
	// @Override
	// public void onUpdate()
	// {
	// this.lastTickPosX = this.posX;
	// this.lastTickPosY = this.posY;
	// this.lastTickPosZ = this.posZ;
	// super.onUpdate();
	//
	// if (ticksInAir > 0 && !this.inWater)
	// {
	// for (int i1 = 0; i1 < 2; i1++)
	// {
	// if (!worldObj.isRemote)
	// {
	// EntityPropulsionFX entitypfx = new EntityPropulsionFX(worldObj, posX, posY, posZ);
	// worldObj.spawnEntityInWorld(entitypfx);
	// }
	// }
	// }
	//
	// if (ticksInAir > RivalRebels.flamethrowerDecay)
	// {
	// this.setDead();
	// }
	//
	// if (this.inGround)
	// {
	// int var1 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
	// this.motionX *= (this.rand.nextFloat() * 0.2F);
	// this.motionY *= (this.rand.nextFloat() * 0.2F);
	// this.motionZ *= (this.rand.nextFloat() * 0.2F);
	// this.ticksInAir = 0;
	// }
	// else
	// {
	// ++this.ticksInAir;
	// }
	//
	// Vec3 var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
	// Vec3 var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
	// MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var15, var2);
	// var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
	// var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
	//
	// if (var3 != null)
	// {
	// var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
	// }
	//
	// Entity var4 = null;
	// List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
	// double var6 = 0.0D;
	// Iterator var8 = var5.iterator();
	//
	// if (!this.worldObj.isRemote)
	// {
	// while (var8.hasNext())
	// {
	// Entity var9 = (Entity) var8.next();
	//
	// if (var9.canBeCollidedWith() && (var9 != this.shootingEntity || this.ticksInAir >= 5))
	// {
	// float var10 = 0.3F;
	// AxisAlignedBB var11 = var9.boundingBox.expand(var10, var10, var10);
	// MovingObjectPosition var12 = var11.calculateIntercept(var15, var2);
	//
	// if (var12 != null)
	// {
	// double var13 = var15.distanceTo(var12.hitVec);
	//
	// if (var13 < var6 || var6 == 0.0D)
	// {
	// var4 = var9;
	// var6 = var13;
	// }
	// }
	// }
	// }
	// }
	//
	// if (var4 != null)
	// {
	// var3 = new MovingObjectPosition(var4);
	// }
	//
	// if (var3 != null)
	// {
	// this.fire();
	// this.setDead();
	// if (var3.entityHit != null)
	// {
	// var3.entityHit.setFire(3);
	// var3.entityHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 12);
	// if (var3.entityHit != null && var3.entityHit instanceof EntityPlayer)
	// {
	// EntityPlayer player = (EntityPlayer) var3.entityHit;
	// ItemStack armorSlots[] = player.inventory.armorInventory;
	// int i = worldObj.rand.nextInt(4);
	// if (armorSlots[i] != null && !worldObj.isRemote)
	// {
	// armorSlots[i].damageItem(8, player);
	// }
	// }
	// }
	// }
	//
	// this.posX += this.motionX;
	// this.posY += this.motionY;
	// this.posZ += this.motionZ;
	// float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
	// this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
	//
	// for (this.rotationPitch = (float) (Math.atan2(this.motionY, var16) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
	// {
	// ;
	// }
	//
	// while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
	// {
	// this.prevRotationPitch += 360.0F;
	// }
	//
	// while (this.rotationYaw - this.prevRotationYaw < -180.0F)
	// {
	// this.prevRotationYaw -= 360.0F;
	// }
	//
	// while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
	// {
	// this.prevRotationYaw += 360.0F;
	// }
	//
	// this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
	// this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
	// float var17 = 0.99F;
	// float var18 = 0.01F;
	//
	// if (this.isInWater())
	// {
	// setDead();
	// }
	//
	// this.motionX *= var17;
	// this.motionY *= var17;
	// this.motionZ *= var17;
	// this.motionY -= var18;
	// this.setPosition(this.posX, this.posY, this.posZ);
	// }
	//
	// /**
	// * (abstract) Protected helper method to write subclass entity data to NBT.
	// */
	// @Override
	// public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	// {
	// par1NBTTagCompound.setShort("xTile", (short) xTile);
	// par1NBTTagCompound.setShort("yTile", (short) yTile);
	// par1NBTTagCompound.setShort("zTile", (short) zTile);
	// par1NBTTagCompound.setByte("inTile", (byte) inTile);
	// par1NBTTagCompound.setByte("inData", (byte) inData);
	// par1NBTTagCompound.setByte("inGround", (byte) (inGround ? 1 : 0));
	// par1NBTTagCompound.setDouble("damage", damage);
	// }
	//
	// /**
	// * (abstract) Protected helper method to read subclass entity data from NBT.
	// */
	// @Override
	// public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	// {
	// xTile = par1NBTTagCompound.getShort("xTile");
	// yTile = par1NBTTagCompound.getShort("yTile");
	// zTile = par1NBTTagCompound.getShort("zTile");
	// inTile = par1NBTTagCompound.getByte("inTile") & 0xff;
	// inData = par1NBTTagCompound.getByte("inData") & 0xff;
	// inGround = par1NBTTagCompound.getByte("inGround") == 1;
	//
	// if (par1NBTTagCompound.hasKey("damage"))
	// {
	// damage = par1NBTTagCompound.getDouble("damage");
	// }
	// }
	//
	// @Override
	// public float getShadowSize()
	// {
	// return 0.0F;
	// }
	//
	// /**
	// * If returns false, the item will not inflict any damage against entities.
	// */
	// @Override
	// public boolean canAttackWithItem()
	// {
	// return false;
	// }
	//
	// private void fire()
	// {
	// if (!worldObj.isRemote)
	// {
	// for (int x = -1; x < 2; x++)
	// {
	// for (int y = -1; y < 2; y++)
	// {
	// for (int z = -1; z < 2; z++)
	// {
	// if (world.getBlockId((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z) == 0 || world.getBlockId((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z) == Block.snow.blockID
	// || world.getBlockId((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z) == Block.ice.blockID)
	// {
	// world.setBlock((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z, Block.fire.blockID);
	// }
	// else if (world.getBlockId((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z) == Block.sand.blockID && worldObj.rand.nextInt(200) == 0)
	// {
	// world.setBlock((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z, Block.glass.blockID);
	// }
	// else if (world.getBlockId((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z) == Block.glass.blockID && worldObj.rand.nextInt(150) == 0)
	// {
	// world.setBlock((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z, Block.obsidian.blockID);
	// }
	// else if (world.getBlockId((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z) == Block.stone.blockID && worldObj.rand.nextInt(200) == 0)
	// {
	// world.setBlock((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z, Block.netherrack.blockID);
	// }
	// else if (world.getBlockId((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z) == Block.blockIron.blockID && worldObj.rand.nextInt(300) == 0)
	// {
	// world.setBlock((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z, Block.lavaMoving.blockID);
	// }
	// else if (world.getBlockId((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z) == Block.leaves.blockID)
	// {
	// world.setBlock((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z, Block.fire.blockID);
	// }
	// else if (world.getBlockId((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z) == Block.grass.blockID && worldObj.rand.nextInt(5) == 0)
	// {
	// world.setBlock((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z, Block.dirt.blockID);
	// }
	// else if (world.getBlockId((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z) == RivalRebels.flare.blockID)
	// {
	// RivalRebels.flare.onBlockDestroyedByPlayer(worldObj, (int) this.posX + x, (int) this.posY + y, (int) this.posZ + z, 0);
	// }
	// }
	// }
	// }
	// }
	// }
}