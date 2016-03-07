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
package assets.rivalrebels.common.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityNuclearBlast;
import assets.rivalrebels.common.entity.EntityPlasmoid;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.entity.EntityTsarBlast;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityPlasmaExplosion extends TileEntity
{
	public float	size		= 0;
	float			increment	= 0.3f;
	float			prevsize	= 0;
	
	public TileEntityPlasmaExplosion()
	{
		
	}
	
	/**
	 * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count ticks and creates a new spawn inside its implementation.
	 */
	@Override
	public void updateEntity()
	{
		prevsize = size;
		size += increment;
		if (prevsize == 0)
		{
			RivalRebelsSoundPlayer.playSound(worldObj, 16, 0, xCoord, yCoord, zCoord, 4);
		}
		if (size > 3.1f)
		{
			size = 0f;
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
			this.invalidate();
		}
		
		double fsize = Math.sin(size) * 5.9 * 2;
		double fsqr = fsize * fsize;
		List l = this.worldObj.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(xCoord - fsize + 0.5, yCoord - fsize + 0.5, zCoord - fsize + 0.5, xCoord + fsize + 0.5, yCoord + fsize + 0.5, zCoord + fsize + 0.5));
		for (int i = 0; i < l.size(); i++)
		{
			Entity e = (Entity) l.get(i);
			double var15 = e.posX - xCoord;
			double var17 = e.posY + e.getEyeHeight() - yCoord + 1.5f;
			double var19 = e.posZ - zCoord;
			double dist = 0.5f/(MathHelper.sqrt_double(var15*var15+var17*var17+var19*var19)+0.01f);
			if (dist <= 0.5f && !(e instanceof EntityNuclearBlast) && !(e instanceof EntityPlasmoid) && !(e instanceof EntityTsarBlast) && !(e instanceof EntityRhodes))
			{
				e.attackEntityFrom(RivalRebelsDamageSource.plasmaexplosion, 2);
				e.motionX += var15*dist;
				e.motionY += var17*dist;
				e.motionZ += var19*dist;
			}
		}
		super.updateEntity();
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(xCoord - 2, yCoord - 2, zCoord - 2, xCoord + 3, yCoord + 3, zCoord + 3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 16384.0D;
	}
}
