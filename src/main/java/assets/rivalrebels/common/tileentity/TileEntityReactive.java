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

public class TileEntityReactive extends TileEntityMachineBase
{
	int	cooldown	= 0;
	
	public TileEntityReactive()
	{
		pInM = 1;
	}
	
	@Override
	public float powered(float power, float distance)
	{
		int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if (metadata > 0)
		{
			if (cooldown <= 0)
			{
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata - 1, 2);
				cooldown = 10;
				return power - 1;
			}
			cooldown--;
		}
		return power;
	}
}
