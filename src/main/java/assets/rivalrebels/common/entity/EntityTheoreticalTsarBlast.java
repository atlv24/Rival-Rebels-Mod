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

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.TsarBomba;

public class EntityTheoreticalTsarBlast extends EntityTsarBlast
{
	public EntityTheoreticalTsarBlast(World par1World)
	{
		super(par1World);
		ignoreFrustumCheck = true;
	}
	
	public EntityTheoreticalTsarBlast(World par1World, float x, float y, float z, TsarBomba tsarBomba, int rad)
	{
		super(par1World);
		ignoreFrustumCheck = true;
		tsar = tsarBomba;
		radius = rad;
		motionX = Math.sqrt(radius - RivalRebels.tsarBombaStrength) / 10;
		setPosition(x, y, z);
	}
}
