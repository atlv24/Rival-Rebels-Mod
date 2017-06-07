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

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.packet.EntityGorePacket;
import assets.rivalrebels.common.packet.PacketDispatcher;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityGore extends EntityInanimate
{
	protected boolean		inGround;
	public Entity			origin;
	public String			username	= "Steve";
	private int				ticksExisted;
	private boolean			isSliding	= false;
	public int				type		= 0;
	public int				mob			= -1;
	private int				slideCount	= 0;
	float					x			= 0;
	float					y			= 0;
	float					z			= 0;
	float					rotYaw		= 0;
	float					rotPitch	= 0;
	float					motionyaw	= 0;
	float					motionpitch	= 0;
	int						pitchLock	= 0;
	float					offset		= 0;
	public double			size		= 0;
	public boolean			greenblood	= false;
	// @SideOnly(Side.CLIENT)
	public ResourceLocation	playerSkin	= null;
	private int				bounces		= -1;
	
	public EntityGore(World par1World)
	{
		super(par1World);
		setSize(0.25F, 0.25F);
	}
	public EntityGore(World par1World, double x, double y,double z, double mx, double my, double mz, int Type, int Mob)
	{
		super(par1World);
		setSize(0.5F, 0.5F);
		setPosition(x,y,z);
		yOffset = 0.0F;
		setAnglesMotion(mx, my, mz);
		type = Type;
		mob = Mob;
	}
	
	public void setAnglesMotion(double mx, double my, double mz)
	{
		motionX = mx;
		motionY = my;
		motionZ = mz;
		prevRotationYaw = rotationYaw = (float) (Math.atan2(mx, mz) * 180.0D / Math.PI);
		prevRotationPitch = rotationPitch = (float) (Math.atan2(my, MathHelper.sqrt_double(mx * mx + mz * mz)) * 180.0D / Math.PI);
	}
	
	public EntityGore(World par1World, Entity toBeGibbed, int Type, int Mob)
	{
		super(par1World);
		setSize(0.25F, 0.25F);
		origin = toBeGibbed;
		isSliding = false;
		slideCount = 0;
		bounces = -1;
		pitchLock = 0;
		type = Type;
		greenblood = false;
		mob = Mob;
		if (Mob == 0)
		{
			username = ((EntityPlayer) origin).getCommandSenderName();
			setBiped(0);
		}
		else if (Mob == 1)
		{
			setBiped(0);
		}
		else if (Mob == 2)
		{
			setBiped(0);
		}
		else if (Mob == 3)
		{
			setBiped(1);
			greenblood = true;
		}
		else if (Mob == 4)
		{
			setBiped(2);
		}
		else if (Mob == 5)
		{
			setQuadruped();
			greenblood = true;
		}
		else if (Mob == 6)
		{
			setNoped();
			greenblood = true;
		}
		else if (Mob == 7)
		{
			setNoped();
		}
		else if (Mob == 8)
		{
			setOctoped(0);
		}
		else if (Mob == 9)
		{
			setOctoped(1);
			greenblood = true;
		}
		else if (Mob == 10)
		{
			setOctoped(2);
		}
		else if (Mob == 11)
		{
			size = origin.boundingBox.getAverageEdgeLength();
			if (size > 2 || size < 1) greenblood = true;
			setDefault();
		}
		
		motionyaw = (float) ((Math.random() - 0.5) * 135);
		motionpitch = (float) ((Math.random() - 0.5) * 135);
		
		setLocationAndAngles(toBeGibbed.posX + x, toBeGibbed.posY + y, toBeGibbed.posZ + z, rotYaw, rotPitch);
		setPosition(posX, posY, posZ);
		setThrowableHeading(0.3f);
	}
	
	private void setNoped()
	{
		if (type == 0)
		{
			y = origin.getEyeHeight();
			offset = 0.5f;
		}
		if (type == 1)
		{
			y = origin.getEyeHeight();
			offset = 0.25F;
		}
		rotYaw = origin.rotationYaw;
	}
	
	private void setBiped(int thin)
	{
		if (type == 0)
		{
			y = origin.getEyeHeight() + 0.20f;
			offset = 0.25f;
			rotYaw = origin.rotationYaw;
			rotPitch = origin.rotationPitch;
		}
		if (type == 1)
		{
			if (thin == 2) y = 2f;
			else y = 1.1f;
			offset = 0.125F;
			rotYaw = origin.rotationYaw;
		}
		if (type == 2)
		{
			x = (float) Math.random() - 0.5f;
			if (thin == 2) y = 1.6f;
			else y = 1.1f;
			z = (float) Math.random() - 0.5f;
			if (thin == 0) offset = 0.125F;
			else offset = 0.0625F;
			rotYaw = origin.rotationYaw;
		}
		if (type == 3)
		{
			x = (float) Math.random() - 0.5f;
			if (thin == 2) y = 0.95f;
			else y = 0.35f;
			z = (float) Math.random() - 0.5f;
			if (thin == 0) offset = 0.125F;
			else offset = 0.0625F;
			rotYaw = origin.rotationYaw;
		}
	}
	
	private void setQuadruped()
	{
		if (type == 0)
		{
			y = origin.getEyeHeight();
			offset = 0.25f;
			rotYaw = origin.rotationYaw;
			rotPitch = origin.rotationPitch;
		}
		if (type == 1)
		{
			y = 0.875f;
			offset = 0.125F;
			rotYaw = origin.rotationYaw;
		}
		if (type == 3)
		{
			x = (float) (Math.random() - 0.5);
			y = 0.25f;
			z = (float) (Math.random() - 0.5);
			offset = 0.125F;
			rotYaw = origin.rotationYaw;
		}
	}
	
	private void setOctoped(int mode)
	{
		if (mode != 2)
		{
			float scale = 1f;
			if (mode == 1) scale = 0.666f;
			if (type == 0)
			{
				y = origin.getEyeHeight();
				offset = 0.25f * scale;
				rotYaw = origin.rotationYaw;
				rotPitch = origin.rotationPitch;
			}
			if (type == 1)
			{
				x = (float) (Math.random() - 0.5f) * scale;
				y = 0.25f * scale;
				z = (float) (Math.random() - 0.5f) * scale;
				offset = 0.1F * scale;
				rotYaw = origin.rotationYaw;
			}
			if (type == 3)
			{
				x = (float) (Math.random() - 0.5f) * scale;
				y = 0.25f * scale;
				z = (float) (Math.random() - 0.5f) * scale;
				offset = 0.0625F * scale;
				rotYaw = (float) (Math.random() * 360);
				rotPitch = 25;
			}
		}
		else
		{
			if (type == 0)
			{
				y = origin.getEyeHeight();
				offset = 1.5f;
				rotYaw = origin.rotationYaw;
				rotPitch = origin.rotationPitch;
			}
			if (type == 3)
			{
				x = (float) (Math.random() - 0.5f) * 4f;
				z = (float) (Math.random() - 0.5f) * 4f;
				offset = 0.125F;
			}
		}
	}
	
	private void setDefault()
	{
		if (type == 0)
		{
			y = origin.getEyeHeight() + 0.20f;
			offset = 0.25f;
			rotYaw = origin.rotationYaw;
			rotPitch = origin.rotationPitch;
		}
		if (type == 1)
		{
			y = origin.getEyeHeight() / 2;
			offset = 0.125F;
			rotYaw = origin.rotationYaw;
		}
		if (type == 2)
		{
			x = (float) ((Math.random() - 0.5f) * size);
			y = origin.getEyeHeight() / 2;
			z = (float) ((Math.random() - 0.5f) * size);
			offset = 0.0625F;
			rotYaw = origin.rotationYaw;
		}
		if (type == 3)
		{
			x = (float) ((Math.random() - 0.5f) * size);
			y = origin.getEyeHeight() / 4;
			z = (float) ((Math.random() - 0.5f) * size);
			offset = 0.0625F;
			rotYaw = origin.rotationYaw;
		}
	}
	
	public void setThrowableHeading(float par7)
	{
		motionX = rand.nextGaussian() * par7;
		motionY = Math.abs(rand.nextGaussian() * par7);
		motionZ = rand.nextGaussian() * par7;
	}
	
	@Override
	public void onUpdate()
	{
		if (ticksExisted == 0)
		{
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if (side == Side.SERVER)
			{
				PacketDispatcher.packetsys.sendToAll(new EntityGorePacket(this));
			}
		}
		
		if (playerSkin == null && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && !username.equals("Steve"))
		{
			Iterator iter = worldObj.playerEntities.iterator();
			while (iter.hasNext())
			{
				EntityPlayer player = (EntityPlayer) iter.next();
				if (player.getCommandSenderName().equals(username))
				{
					AbstractClientPlayer acp = (AbstractClientPlayer) player;
					playerSkin = acp.getLocationSkin();
				}
			}
		}
		
		++ticksExisted;
		
		super.onUpdate();
		
		if (inGround)
		{
			inGround = false;
			motionX *= (rand.nextFloat() * 0.2F);
			motionY *= (rand.nextFloat() * 0.2F);
			motionZ *= (rand.nextFloat() * 0.2F);
		}
		if (isSliding)
		{
			slideCount++;
			if (slideCount == 140) setDead();
		}
		
		Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3, vec31);
		vec3 = Vec3.createVectorHelper(posX, posY, posZ);
		vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		
		if (movingobjectposition != null)
		{
			isSliding = true;
			posY = movingobjectposition.hitVec.yCoord + offset;
		}
		
		rotationPitch += motionpitch;
		rotationYaw += motionyaw;
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		
		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.5F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.5F;
		
		float f2 = 0.99F;
		float f3 = 0.05F;
		
		if (isInWater())
		{
			// for (int k = 0; k < 4; ++k)
			// {
			// float f4 = 0.25F;
			// worldObj.spawnParticle("bubble", posX - motionX * (double)f4, posY - motionY * (double)f4, posZ - motionZ * (double)f4, motionX, motionY, motionZ);
			// }
			
			f2 = 0.9F;
		}
		else if (!isSliding)
		{
			if (worldObj.isRemote && RivalRebels.goreEnabled && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			{
				spawnBlood();
			}
		}
		else
		{
			f2 = 0.8f;
		}
		
		motionpitch *= (double) f2;
		motionyaw *= (double) f2;
		motionX *= f2;
		motionY *= f2;
		motionZ *= f2;
		motionY -= f3;
		
		if (isSliding == true)
		{
			if (bounces == -1) bounces = worldObj.rand.nextInt(2) + 2;
			if (bounces > 0)
			{
				bounces--;
				motionY *= -((Math.random() * 0.5) + 0.35);
				isSliding = false;
				slideCount = 0;
				pitchLock = (int) ((Math.ceil((rotationPitch / 90f))) * 90);
			}
			else motionY = 0;
			motionpitch = 0;
			rotationPitch = pitchLock;
		}
		
		setPosition(posX, posY, posZ);
	}
	
	@SideOnly(Side.CLIENT)
	private void spawnBlood()
	{
		for (int i = 0; i < 3; ++i)
		{
			RivalRebels.proxy.spawnGore(worldObj, this, !greenblood);
		}
	}
	
	public static byte[] getBytesString(String str)
	{
		char[] buffer = str.toCharArray();
		byte[] bytes = new byte[buffer.length];
		for (int i = 0; i < bytes.length; i++)
		{
			bytes[i] = (byte) buffer[i];
		}
		return bytes;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setByte("Mob", (byte) mob);
		par1NBTTagCompound.setByte("Type", (byte) type);
		par1NBTTagCompound.setByte("Green", (byte) (greenblood ? 1 : 0));
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		mob = par1NBTTagCompound.getByte("Mob") & 255;
		type = par1NBTTagCompound.getByte("Type") & 255;
		greenblood = par1NBTTagCompound.getByte("Green") == 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}
	
	@Override
	public void entityInit()
	{
		
	}
}
