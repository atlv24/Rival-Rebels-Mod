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
package assets.rivalrebels.client.model;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.client.renderhelper.RenderHelper;
import assets.rivalrebels.client.renderhelper.TextureVertice;
import assets.rivalrebels.client.renderhelper.Vertice;

public class ModelDisk
{
	TextureVertice	t1			= new TextureVertice(0.03125f * 0, 0.03125f * 0);
	TextureVertice	t2			= new TextureVertice(0.03125f * 11, 0.03125f * 0);
	TextureVertice	t3			= new TextureVertice(0.03125f * 17, 0.03125f * 0);
	TextureVertice	t4			= new TextureVertice(0.03125f * 0, 0.03125f * 6);
	TextureVertice	t5			= new TextureVertice(0.03125f * 11, 0.03125f * 6);
	TextureVertice	t6			= new TextureVertice(0.03125f * 0, 0.03125f * 7);
	TextureVertice	t7			= new TextureVertice(0.03125f * 11, 0.03125f * 7);
	TextureVertice	t8			= new TextureVertice(0.03125f * 17, 0.03125f * 7);
	TextureVertice	t9			= new TextureVertice(0.03125f * 0, 0.03125f * 8);
	TextureVertice	t10			= new TextureVertice(0.03125f * 11, 0.03125f * 8);
	
	Vertice			v1			= new Vertice(0.45f, -0.03125f, 0f);
	Vertice			v2			= new Vertice(0.45f, 0.03125f, 0f);
	Vertice			v3			= new Vertice(0.65f, 0.0625f, 0f);
	Vertice			v4			= new Vertice(1.00f, 0f, 0f);
	Vertice			v5			= new Vertice(0.65f, -0.0625f, 0f);
	
	private int		numOfSegs	= 32;
	private float	deg			= (float) Math.PI * 2 / numOfSegs;
	private float	cosdeg		= (float) Math.cos(deg);
	private float	sindeg		= (float) Math.sin(deg);
	
	Vertice			v6			= new Vertice(0.45f * cosdeg, -0.03125f, 0.45f * sindeg);
	Vertice			v7			= new Vertice(0.45f * cosdeg, 0.03125f, 0.45f * sindeg);
	Vertice			v8			= new Vertice(0.65f * cosdeg, 0.0625f, 0.65f * sindeg);
	Vertice			v9			= new Vertice(1.00f * cosdeg, 0f, 1.00f * sindeg);
	Vertice			v10			= new Vertice(0.65f * cosdeg, -0.0625f, 0.65f * sindeg);
	
	public void render()
	{
		for (float i = 0; i < 360; i += 360 / numOfSegs)
		{
			GL11.glPushMatrix();
			GL11.glRotatef(i, 0, 1, 0);
			RenderHelper.addFace(v2, v1, v6, v7, t4, t9, t10, t5);
			if (i == 0 || i == 16)
			{
				RenderHelper.addFace(v3, v2, v7, v8, t2, t7, t8, t3);
				RenderHelper.addFace(v4, v3, v8, v9, t2, t7, t8, t3);
				RenderHelper.addFace(v5, v4, v9, v10, t2, t7, t8, t3);
				RenderHelper.addFace(v1, v5, v10, v6, t2, t7, t8, t3);
			}
			else
			{
				RenderHelper.addFace(v3, v2, v7, v8, t6, t1, t2, t7);
				RenderHelper.addFace(v4, v3, v8, v9, t6, t1, t2, t7);
				RenderHelper.addFace(v5, v4, v9, v10, t1, t6, t7, t2);
				RenderHelper.addFace(v1, v5, v10, v6, t1, t6, t7, t2);
			}
			GL11.glPopMatrix();
		}
	}
}
