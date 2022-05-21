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

public enum RivalRebelsRank
{
	REGULAR(0,13),
	REBEL(1,17),
	OFFICER(2,16),
	LEADER(3,3),
	REP(4,5);
	public int	id	= 0;
	public int	snf	= 0;
	
	RivalRebelsRank(int i, int s)
	{
		id = i;
		snf = s;
	}
	
	public static RivalRebelsRank getForID(int i)
	{
		switch (i)
		{
			case 1:
				return REBEL;
			case 2:
				return OFFICER;
			case 3:
				return LEADER;
			case 4:
				return REP;
		}
		return REGULAR;
	}
}