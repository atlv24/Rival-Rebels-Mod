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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelNukeCrate
{
	float			s	= 0.5F;
	Vertice			v1	= new Vertice(s, s, s);
	Vertice			v2	= new Vertice(s, s, -s);
	Vertice			v3	= new Vertice(-s, s, -s);
	Vertice			v4	= new Vertice(-s, s, s);
	Vertice			v5	= new Vertice(s, -s, s);
	Vertice			v6	= new Vertice(s, -s, -s);
	Vertice			v7	= new Vertice(-s, -s, -s);
	Vertice			v8	= new Vertice(-s, -s, s);
	TextureVertice	t1	= new TextureVertice(0, 0);
	TextureVertice	t2	= new TextureVertice(1, 0);
	TextureVertice	t3	= new TextureVertice(1, 1);
	TextureVertice	t4	= new TextureVertice(0, 1);
	
	public void renderModelA()
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		RenderHelper.addFace(v4, v8, v5, v1, t1, t2, t3, t4);
		RenderHelper.addFace(v3, v7, v8, v4, t1, t2, t3, t4);
		RenderHelper.addFace(v2, v6, v7, v3, t1, t2, t3, t4);
		RenderHelper.addFace(v1, v5, v6, v2, t1, t2, t3, t4);
		GL11.glPopMatrix();
	}
	
	public void renderModelB()
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		RenderHelper.addFace(v1, v2, v3, v4, t1, t2, t3, t4);
		RenderHelper.addFace(v8, v7, v6, v5, t1, t2, t3, t4);
		GL11.glPopMatrix();
	}
}
