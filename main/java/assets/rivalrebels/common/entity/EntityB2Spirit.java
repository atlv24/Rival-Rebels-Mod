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
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;

public class EntityB2Spirit extends Entity
{
	private int					ticksUntilBomb	= 0;
	private TileEntityLaptop	t;
	private double				tx				= 0;
	private double				ty				= 0;
	private double				tz				= 0;
	public int					health;
	public int 					bomb	= 1;
	public boolean 				carpet	= false;
	public static float 		radius = 80;
	public static float 		range = 20;
	public EntityRhodes			rhodeswing = null;
	
	public EntityB2Spirit(World par1World)
	{
		super(par1World);
		setSize(30F, 4F);
		ignoreFrustumCheck = true;
		boundingBox.setBounds(-10, -3, -10, 10, 4, 10);
		health = RivalRebels.b2spirithealth;
		yOffset = 0.0F;
	}
	
	public EntityB2Spirit(World par1World, double x, double y, double z, double x1, double y1, double z1, TileEntityLaptop tel, boolean dropall, boolean c)
	{
		this(par1World);
		carpet = c;
		tx = x;
		ty = y;
		tz = z;
		t = tel;
		if (t != null)
		{
			t.canBomb = false;
			bomb = carpet?t.b2carpet:t.b2spirit;
			if (bomb == 0)
			{
				carpet = !carpet;
				bomb = carpet?t.b2carpet:t.b2spirit;
				if (bomb == 0)
				{
					setDead();
				}
			}
		}
		if (!dropall && bomb > 1) bomb = 1;
		if (!worldObj.isRemote) startBombRun(tz-z1, x1-tx);
	}
	
	public EntityB2Spirit(EntityRhodes r)
	{
		this(r.worldObj);
		ticksUntilBomb = 1000000;
		rhodeswing = r;
		posX = r.posX - r.motionX * 500;
		posY = 120;
		posZ = r.posZ - r.motionZ * 500;
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
		return false;
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		
		if (Math.random() > 0.8f) RivalRebelsSoundPlayer.playSound(this, 8, 0, 4.5f, 1.3f);
		
		if (rhodeswing != null)
		{
			motionX = rhodeswing.posX - posX;
			motionY = rhodeswing.posY - posY;
			motionZ = rhodeswing.posZ - posZ;
			double t = Math.sqrt(motionX*motionX+motionY*motionY+motionZ*motionZ);
			motionX /= t;
			motionY /= t;
			motionZ /= t;
			rotationYaw = rhodeswing.rotationYaw;
			rotationPitch = (float) (Math.min(t,90.0));
			if (t < 25.0)
			{
				rhodeswing.b2energy = 8000;
				rhodeswing.freeze = false;
				setDead();
			}
		}
		
		if (ticksUntilBomb <= range && ticksUntilBomb >= -range && !worldObj.isRemote)
		{
			dropNuke();
		}
		ticksUntilBomb--;
		if (ticksUntilBomb <= -radius && !worldObj.isRemote)
		{
			bomb--;
			if (t != null)
			{
				if (carpet) t.b2carpet--;
				else t.b2spirit--;
				t.refreshTasks();
			}
			if (bomb > 0)
			{
				startBombRun(Math.random()-0.5, Math.random()-0.5);
			}
			else
			{
				setDead();
			}
		}
		
		if (!this.worldObj.isRemote)
		{
			List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			Iterator var8 = var5.iterator();
			
			while (var8.hasNext())
			{
				Entity var9 = (Entity) var8.next();
				
				if (var9 instanceof EntityRocket)
				{
					((EntityRocket) var9).explode(null);
				}
				
				if (var9 instanceof EntityPlasmoid)
				{
					((EntityPlasmoid) var9).explode();
				}
				
				if (var9 instanceof EntityLaserBurst)
				{
					((EntityLaserBurst) var9).setDead();
					this.attackEntityFrom(DamageSource.generic, 6);
				}
			}
		}
		
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		if (rhodeswing == null)
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
			
			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		}
		this.setPosition(this.posX, this.posY, this.posZ);
	}
	
	public void dropNuke()
	{
		if (carpet)
		{
			if (Math.random() > 0.8f) RivalRebelsSoundPlayer.playSound(this, 8, 2, 4.5f, 1.0f);
			worldObj.spawnEntityInWorld(new EntityBomb(worldObj, posX+Math.random()*4-2, posY - 3, posZ+Math.random()*4-2, rotationYaw, rotationPitch + (float)(90*Math.random())));
		}
		else if (ticksUntilBomb == 0)
		{
			worldObj.spawnEntityInWorld(new EntityB83(worldObj, posX+Math.random()*4-2, posY - 3, posZ+Math.random()*4-2, rotationYaw, rotationPitch + (float)(90*Math.random())));
		}
	}
	Entity rhodes = null;
	public void startBombRun(double x, double z)
	{
		//if (rhodes != null && rhodes.isDead) rhodes = null;
		/*if (rhodes == null)
		{
			Entity er = rhodes;
			Iterator iter = worldObj.loadedEntityList.iterator();
			double d = 1600;
			while(iter.hasNext())
			{
				Entity e = (Entity) iter.next();
				if (e instanceof EntityRhodes)
				{
					double xx = (e.posX-tx);
					double zz = (e.posZ-tz);
					double dd = xx*xx+zz*zz;
					if (dd < d)
					{
						d = dd;
						er = e;
					}
				}
			}
			rhodes = er;
		}*/
		if (rhodes != null)
		{
			tx = rhodes.posX;
			ty = rhodes.posY;
			tz = rhodes.posZ;
			x = -rhodes.motionX;
			z = -rhodes.motionZ;
		}
		double dist = 1.0/Math.sqrt(x*x + z*z);
		x *= dist;
		z *= dist;
		motionX = -x;
		motionZ = -z;
		setPosition(tx + x*radius, ty+60, tz + z*radius);
		prevRotationYaw = rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
		ticksUntilBomb = (int)radius;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setBoolean("carpet", carpet);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		carpet = par1NBTTagCompound.getBoolean("carpet");
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
		if (!this.isDead && !this.worldObj.isRemote)
		{
			this.health -= par2;
			//System.out.println(par2 + " " + health);
			if (this.health <= 0)
			{
				this.setDead();
				this.worldObj.createExplosion((Entity) null, this.posX, this.posY, this.posZ, 6.0F, true);
				worldObj.spawnEntityInWorld(new EntityB2Frag(worldObj, this, 0));
				worldObj.spawnEntityInWorld(new EntityB2Frag(worldObj, this, 1));
				EntityZombie pz = new EntityZombie(worldObj);
				pz.setPosition(posX, posY, posZ);
				worldObj.spawnEntityInWorld(pz);
				RivalRebelsSoundPlayer.playSound(this, 0, 0, 30, 1);
				if (t != null)
				{
					if (carpet) t.b2carpet--;
					else t.b2spirit--;
					if (t.b2carpet < 0) t.b2carpet = 0;
					if (t.b2spirit < 0) t.b2spirit = 0;
				}
			}
		}
		
		return true;
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	public void setDead()
	{
		if (t != null) t.canBomb = true;
		super.setDead();
	}
}
