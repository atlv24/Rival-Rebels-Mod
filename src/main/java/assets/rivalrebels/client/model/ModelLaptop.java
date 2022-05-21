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

public class ModelLaptop
{
	TextureVertice	t1		= new TextureVertice(0.03125f * 1, 0.03125f * 0);
	TextureVertice	t2		= new TextureVertice(0.03125f * 10, 0.03125f * 0);
	TextureVertice	t3		= new TextureVertice(0.03125f * 0, 0.03125f * 1);
	TextureVertice	t4		= new TextureVertice(0.03125f * 1, 0.03125f * 1);
	TextureVertice	t5		= new TextureVertice(0.03125f * 15, 0.03125f * 1);
	TextureVertice	t6		= new TextureVertice(0.03125f * 16, 0.03125f * 1);
	TextureVertice	t7		= new TextureVertice(0.03125f * 30, 0.03125f * 1);
	TextureVertice	t8		= new TextureVertice(0.03125f * 0, 0.03125f * 10);
	TextureVertice	t9		= new TextureVertice(0.03125f * 1, 0.03125f * 10);
	TextureVertice	t10		= new TextureVertice(0.03125f * 15, 0.03125f * 10);
	TextureVertice	t11		= new TextureVertice(0.03125f * 16, 0.03125f * 10);
	TextureVertice	t12		= new TextureVertice(0.03125f * 30, 0.03125f * 10);
	TextureVertice	t13		= new TextureVertice(0.03125f * 1, 0.03125f * 11);
	TextureVertice	t14		= new TextureVertice(0.03125f * 15, 0.03125f * 11);
	
	TextureVertice	t1t		= new TextureVertice(0.03125f * 2, 0.03125f * 11);
	TextureVertice	t2t		= new TextureVertice(0.03125f * 16, 0.03125f * 11);
	TextureVertice	t3t		= new TextureVertice(0.03125f * 0, 0.03125f * 13);
	TextureVertice	t4t		= new TextureVertice(0.03125f * 2, 0.03125f * 13);
	TextureVertice	t5t		= new TextureVertice(0.03125f * 16, 0.03125f * 13);
	TextureVertice	t6t		= new TextureVertice(0.03125f * 18, 0.03125f * 13);
	TextureVertice	t7t		= new TextureVertice(0.03125f * 32, 0.03125f * 13);
	TextureVertice	t8t		= new TextureVertice(0.03125f * 0, 0.03125f * 22);
	TextureVertice	t9t		= new TextureVertice(0.03125f * 2, 0.03125f * 22);
	TextureVertice	t10t	= new TextureVertice(0.03125f * 16, 0.03125f * 22);
	TextureVertice	t11t	= new TextureVertice(0.03125f * 18, 0.03125f * 22);
	TextureVertice	t12t	= new TextureVertice(0.03125f * 32, 0.03125f * 22);
	TextureVertice	t13t	= new TextureVertice(0.03125f * 2, 0.03125f * 24);
	TextureVertice	t14t	= new TextureVertice(0.03125f * 16, 0.03125f * 24);
	
	Vertice			v1		= new Vertice(0.4375f, 0f, 0.5625f);
	Vertice			v2		= new Vertice(0.4375f, 0f, 0);
	Vertice			v3		= new Vertice(-0.4375f, 0f, 0);
	Vertice			v4		= new Vertice(-0.4375f, 0f, 0.5625f);
	
	Vertice			v5		= new Vertice(0.4375f, 0.0625f, 0.5625f);
	Vertice			v6		= new Vertice(0.4375f, 0.0625f, 0);
	Vertice			v7		= new Vertice(-0.4375f, 0.0625f, 0);
	Vertice			v8		= new Vertice(-0.4375f, 0.0625f, 0.5625f);
	
	Vertice			v9		= new Vertice(0.4375f, 0.125f, 0.5625f);
	Vertice			v10		= new Vertice(0.4375f, 0.125f, 0);
	Vertice			v11		= new Vertice(-0.4375f, 0.125f, 0);
	Vertice			v12		= new Vertice(-0.4375f, 0.125f, 0.5625f);
	
	public void renderModel(float turn)
	{
		GL11.glPushMatrix();
		RenderHelper.addFace(v11, v12, v9, v10, t4t, t9t, t10t, t5t);
		RenderHelper.addFace(v12, v4, v1, v9, t9t, t13t, t14t, t10t);
		RenderHelper.addFace(v11, v3, v4, v12, t4t, t3t, t8t, t9t);
		RenderHelper.addFace(v10, v2, v3, v11, t5t, t2t, t1t, t4t);
		RenderHelper.addFace(v9, v1, v2, v10, t10t, t11t, t6t, t5t);
		RenderHelper.addFace(v2, v1, v4, v3, t6t, t11t, t12t, t7t);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0.125f, 0);
		GL11.glRotatef(turn, 0.1875f, 0, 0);
		RenderHelper.addFace(v5, v6, v7, v8, t4, t9, t10, t5);
		RenderHelper.addFace(v8, v4, v1, v5, t9, t13, t14, t10);
		RenderHelper.addFace(v7, v3, v4, v8, t4, t3, t8, t9);
		RenderHelper.addFace(v6, v2, v3, v7, t5, t2, t1, t4);
		RenderHelper.addFace(v5, v1, v2, v6, t10, t11, t6, t5);
		RenderHelper.addFace(v2, v1, v4, v3, t6, t11, t12, t7);
		GL11.glPopMatrix();
	}
	
	TextureVertice	t111	= new TextureVertice(0, 0);
	TextureVertice	t222	= new TextureVertice(1, 0);
	TextureVertice	t333	= new TextureVertice(1, 1);
	TextureVertice	t444	= new TextureVertice(0, 1);
	Vertice			v1v		= new Vertice(0.375f, 0f, 0.5f);
	Vertice			v2v		= new Vertice(0.375f, 0f, 0.0625f);
	Vertice			v3v		= new Vertice(-0.375f, 0f, 0.0625f);
	Vertice			v4v		= new Vertice(-0.375f, 0f, 0.5f);
	
	public void renderScreen(float turn)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0.125f, 0);
		GL11.glRotatef(turn, 0.1875f, 0, 0);
		RenderHelper.addFace(v2v, v1v, v4v, v3v, t333, t222, t111, t444);
		GL11.glPopMatrix();
	}
}
