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

public class Rectangle
{
	public int	xMin, xMax, yMin, yMax;
	
	public Rectangle(int x, int y, int w, int h)
	{
		xMin = x;
		xMax = x + w;
		yMin = y;
		yMax = y + h;
	}
	
	public boolean isVecInside(Vector vec)
	{
		if (vec.x >= xMin && vec.x <= xMax && vec.y >= yMin && vec.y <= yMax) return true;
		return false;
	}
}
