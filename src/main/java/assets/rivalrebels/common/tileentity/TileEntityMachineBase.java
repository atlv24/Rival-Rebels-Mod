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

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityMachineBase extends TileEntity
{
	public float	pInM		= 0;
	public float	pInR		= 0;
	public float	edist		= 0;
	public float	decay		= 0;
	public float	powerGiven	= 0;
	public int		x			= 0;
	public int		y			= 0;
	public int		z			= 0;
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (pInR > 0) pInR = powered(pInR, edist);
		pInR -= decay;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		x = nbt.getInteger("rx");
		y = nbt.getInteger("ry");
		z = nbt.getInteger("rz");
		edist = nbt.getFloat("edist");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("rx", x);
		nbt.setInteger("ry", y);
		nbt.setInteger("rz", z);
		nbt.setFloat("edist", edist);
	}
	
	@Override
	public void invalidate()
	{
		super.invalidate();
		TileEntity connectedTo = worldObj.getTileEntity(x, y, z);
		if (connectedTo != null && connectedTo instanceof TileEntityReactor) ((TileEntityReactor)connectedTo).machines.remove(this);
	}
	
	abstract public float powered(float power, float distance);
}
