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

public class Triangle
{
	public Vertice			v1;
	public Vertice			v2;
	public Vertice			v3;
	public TextureVertice	t1;
	public TextureVertice	t2;
	public TextureVertice	t3;
	
	public Triangle(Vertice V1, Vertice V2, Vertice V3, TextureVertice T1, TextureVertice T2, TextureVertice T3)
	{
		v1 = V1;
		v2 = V2;
		v3 = V3;
		t1 = T1;
		t2 = T2;
		t3 = T3;
	}
}
