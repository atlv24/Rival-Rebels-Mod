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
package assets.rivalrebels.common.round;

public enum RivalRebelsTeam
{
	NONE(0),
	OMEGA(1),
	SIGMA(2);
	
	public int	id	= 0;
	
	RivalRebelsTeam(int i)
	{
		id = i;
	}
	
	public static RivalRebelsTeam getForID(int i)
	{
		switch (i)
		{
			case 1:
				return OMEGA;
			case 2:
				return SIGMA;
		}
		return NONE;
	}
}
