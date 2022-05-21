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
package assets.rivalrebels.client.guihelper;

public class Vector
{
	public int	x, y;
	
	public Vector(int X, int Y)
	{
		x = X;
		y = Y;
	}
	
	public boolean isInsideRectangle(Rectangle rec)
	{
		if (x >= rec.xMin && x <= rec.xMax && y >= rec.yMin && y <= rec.yMax) return true;
		return false;
	}
}
