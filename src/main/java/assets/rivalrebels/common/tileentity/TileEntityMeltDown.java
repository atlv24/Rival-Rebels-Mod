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
import net.minecraft.util.Vec3;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityNuclearBlast;
import assets.rivalrebels.common.entity.EntityPlasmoid;
import assets.rivalrebels.common.entity.EntityRhodes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityMeltDown extends TileEntity
{
	public float	size		= 0;
	float			increment	= 0.075f;
	float			prevsize	= 0;
	
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
		if (size > 9.3f)
		{
			size = 0f;
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
			this.invalidate();
		}
		
		double fsize = Math.sin(size) * 5.9;
		fsize *= 2;
		List l = this.worldObj.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(xCoord - fsize + 0.5, yCoord - fsize + 0.5, zCoord - fsize + 0.5, xCoord + fsize + 0.5, yCoord + fsize + 0.5, zCoord + fsize + 0.5));
		for (int i = 0; i < l.size(); i++)
		{
			Entity e = (Entity) l.get(i);
			double var13 = e.getDistance(xCoord, yCoord, zCoord) / fsize;
			
			if (var13 <= 1.0D)
			{
				double var15 = e.posX - xCoord;
				double var17 = e.posY + e.getEyeHeight() - yCoord;
				double var19 = e.posZ - zCoord;
				double var33 = MathHelper.sqrt_double(var15 * var15 + var17 * var17 + var19 * var19);
				
				if (var33 != 0.0D)
				{
					var15 /= var33;
					var17 /= var33;
					var19 /= var33;
					double var32 = worldObj.getBlockDensity(Vec3.createVectorHelper(xCoord, yCoord, zCoord), e.boundingBox);
					double var34 = (1.0D - var13) * var32;
					if (!(e instanceof EntityNuclearBlast) && !(e instanceof EntityPlasmoid) && !(e instanceof EntityRhodes))
					{
						e.attackEntityFrom(RivalRebelsDamageSource.plasmaexplosion, (int) ((var34 * var34 + var34) / 16.0D * fsize + 1.0D));
						e.motionX += var15 * var34 * 4;
						e.motionY += var17 * var34 * 4;
						e.motionZ += var19 * var34 * 4;
					}
				}
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
