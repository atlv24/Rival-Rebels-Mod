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
// Copyrighted Rodolian Material
package assets.rivalrebels.client.model;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.client.renderhelper.RenderHelper;
import assets.rivalrebels.client.renderhelper.TextureVertice;
import assets.rivalrebels.client.renderhelper.Vertice;

public class ModelNukeCloud
{
	private float[]	topx		= { 0.7f, 1.2f, 1.2f, 1.2f, 1.75f, 2.5f, 3f, 3.5f, 3.5f, 3f, 2.25f, 2f, 1.75f, 1.25f, 0f };
	private float[]	topy		= { 6.5f, 5f, 6f, 7f, 7.1f, 7.25f, 7.5f, 8.25f, 9f, 9.25f, 9.4f, 9.75f, 10.1f, 10.25f, 10.25f };
	private float[]	bottomx		= { 1.22f, 1.12f, 1.03f, 0.95f, 0.88f, 0.82f, 0.77f, 0.73f, 0.71f, 0.7f, 0.7f };
	private float[]	bottomy		= { -4f, -3f, -2f, -1f, 0f, 1f, 2f, 3.25f, 4.5f, 5.5f, 6.5f };
	
	private int		segments	= 18;
	private float	deg			= (float) Math.PI * 2f / segments;
	private float	sin			= (float) Math.sin(deg);
	private float	cos			= (float) Math.cos(deg);
	private float	add			= 360 / segments;
	
	public void renderTop()
	{
		for (float i = 0; i < segments; i++)
		{
			GL11.glPushMatrix();
			GL11.glRotatef(add * i, 0, 1, 0);
			for (int f = 1; f < topx.length; f++)
			{
				TextureVertice t1 = new TextureVertice((1f / segments) * (i - 1), (1f / topx.length) * f);
				TextureVertice t2 = new TextureVertice((1f / segments) * (i - 1), (1f / topx.length) * (f - 1));
				TextureVertice t3 = new TextureVertice((1f / segments) * i, (1f / topx.length) * (f - 1));
				TextureVertice t4 = new TextureVertice((1f / segments) * i, (1f / topx.length) * f);
				RenderHelper.addFace(new Vertice(0f, topy[f], topx[f]),
						new Vertice(0f, topy[f - 1], topx[f - 1]),
						new Vertice(topx[f - 1] * sin, topy[f - 1], topx[f - 1] * cos),
						new Vertice(topx[f] * sin, topy[f], topx[f] * cos), t1, t2, t3, t4);
			}
			GL11.glPopMatrix();
		}
	}
	
	public void renderBottom()
	{
		for (float i = 0; i < segments; i++)
		{
			GL11.glPushMatrix();
			GL11.glRotatef(add * i, 0, 1, 0);
			for (int f = 1; f < bottomx.length; f++)
			{
				TextureVertice t1 = new TextureVertice((1f / segments) * (i - 1), (1f / bottomx.length) * f);
				TextureVertice t2 = new TextureVertice((1f / segments) * (i - 1), (1f / bottomx.length) * (f - 1));
				TextureVertice t3 = new TextureVertice((1f / segments) * i, (1f / bottomx.length) * (f - 1));
				TextureVertice t4 = new TextureVertice((1f / segments) * i, (1f / bottomx.length) * f);
				RenderHelper.addFace(new Vertice(0f, bottomy[f], bottomx[f]),
						new Vertice(0f, bottomy[f - 1], bottomx[f - 1]),
						new Vertice(bottomx[f - 1] * sin, bottomy[f - 1], bottomx[f - 1] * cos),
						new Vertice(bottomx[f] * sin, bottomy[f], bottomx[f] * cos), t1, t2, t3, t4);
			}
			GL11.glPopMatrix();
		}
	}
}
