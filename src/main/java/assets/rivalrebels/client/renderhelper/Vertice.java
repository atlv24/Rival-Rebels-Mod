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
package assets.rivalrebels.client.renderhelper;

public class Vertice
{
	public float	x;
	public float	y;
	public float	z;
	
	public Vertice(float X, float Y, float Z)
	{
		x = X;
		y = Y;
		z = Z;
	}
	
	public Vertice normalize()
	{
		float l = (float) Math.sqrt(x * x + y * y + z * z);
		x /= l;
		y /= l;
		z /= l;
		return this;
	}
}
