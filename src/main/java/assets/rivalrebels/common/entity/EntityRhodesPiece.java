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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.Explosion;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.packet.RhodesPiecePacket;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;

public class EntityRhodesPiece extends Entity
{
	protected int health;
	private float myaw;
	private float mpitch;
	public float scale = 1.0f;
	public int color = 0;
	
	public EntityRhodesPiece(World w)
	{
		super(w);
		setSize(4F, 2F);
		boundingBox.setBounds(-1.5, -1.5, -1.5, 1.5, 1.5, 1.5);
	}
	
	public EntityRhodesPiece(World w, double x, double y, double z, float s, int c)
	{
		this(w);
		setPosition(x, y, z);
		scale = s;
		color = c;
		myaw = (float) (rand.nextGaussian()*20);
		mpitch = (float) (rand.nextGaussian()*20);
		motionX = (float) (rand.nextGaussian()*0.75);
		motionY = (float) Math.abs(rand.nextGaussian()*0.75);
		motionZ = (float) (rand.nextGaussian()*0.75);
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.boundingBox;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}
	
	@Override
	public boolean canBePushed()
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
		if (ticksExisted == 1) PacketDispatcher.packetsys.sendToAll(new RhodesPiecePacket(this));
		ticksExisted++;
		if (worldObj.rand.nextInt(Math.max(getMaxAge()*(RivalRebels.rhodesPromode?1:30) - ticksExisted, RivalRebels.rhodesPromode?100:1))==0)
		{
			setDead();
		}
		lastTickPosX = posX;
		lastTickPosY = posY;
		lastTickPosZ = posZ;
		motionX *= 0.999f;
		motionY *= 0.999f;
		motionZ *= 0.999f;
		myaw *= 0.98f;
		mpitch *= 0.98f;
		rotationYaw += myaw;
		rotationPitch += mpitch;
		if (isCollidedVertically)
		{
			rotationPitch = Math.round(rotationPitch / 90f)*90;
			motionX *= 0.7f;
			motionZ *= 0.7f;
		}
		else
		{
			motionY -= 0.1;
		}
		moveEntity(motionX, motionY, motionZ);
	}
	
	public int getMaxAge()
	{
		return 100;
	}
	
	@Override
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		super.attackEntityFrom(par1DamageSource, par2);
		if (!isDead && !worldObj.isRemote)
		{
			health -= par2;
			if (health <= 0)
			{
				setDead();
				new Explosion(worldObj, posX, posY, posZ, 6, true, true, RivalRebelsDamageSource.rocket);
				RivalRebelsSoundPlayer.playSound(this, 0, 0, 30, 1);
			}
		}
		
		return true;
	}
	
	@Override
	protected void entityInit()
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		health = nbt.getInteger("health");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("health", health);
	}
}
