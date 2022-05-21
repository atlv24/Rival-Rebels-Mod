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

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.client.renderhelper.TextureVertice;
import assets.rivalrebels.client.renderhelper.Vertice;

public class ModelReactor
{
	float			xoff	= 1;
	float			yoff	= 1;
	float			zoff	= 1;
	
	TextureVertice	t1		= new TextureVertice(0.015625f * 0, 0.015625f * 0);
	TextureVertice	t2		= new TextureVertice(0.015625f * 16, 0.015625f * 0);
	TextureVertice	t3		= new TextureVertice(0.015625f * 32, 0.015625f * 0);
	TextureVertice	t4		= new TextureVertice(0.015625f * 39, 0.015625f * 0);
	TextureVertice	t5		= new TextureVertice(0.015625f * 41, 0.015625f * 0);
	TextureVertice	t6		= new TextureVertice(0.015625f * 44, 0.015625f * 0);
	TextureVertice	t7		= new TextureVertice(0.015625f * 32, 0.015625f * 1);
	TextureVertice	t8		= new TextureVertice(0.015625f * 41, 0.015625f * 3);
	TextureVertice	t9		= new TextureVertice(0.015625f * 44, 0.015625f * 3);
	TextureVertice	t10		= new TextureVertice(0.015625f * 39, 0.015625f * 14);
	TextureVertice	t11		= new TextureVertice(0.015625f * 41, 0.015625f * 14);
	TextureVertice	t12		= new TextureVertice(0.015625f * 32, 0.015625f * 15);
	TextureVertice	t13		= new TextureVertice(0.015625f * 0, 0.015625f * 16);
	TextureVertice	t14		= new TextureVertice(0.015625f * 16, 0.015625f * 16);
	TextureVertice	t15		= new TextureVertice(0.015625f * 17, 0.015625f * 16);
	TextureVertice	t16		= new TextureVertice(0.015625f * 31, 0.015625f * 16);
	TextureVertice	t17		= new TextureVertice(0.015625f * 32, 0.015625f * 16);
	TextureVertice	t18		= new TextureVertice(0.015625f * 39, 0.015625f * 16);
	TextureVertice	t19		= new TextureVertice(0.015625f * 16, 0.015625f * 23);
	TextureVertice	t20		= new TextureVertice(0.015625f * 17, 0.015625f * 23);
	TextureVertice	t21		= new TextureVertice(0.015625f * 31, 0.015625f * 23);
	TextureVertice	t22		= new TextureVertice(0.015625f * 32, 0.015625f * 23);
	TextureVertice	t23		= new TextureVertice(0.015625f * 16, 0.015625f * 30);
	TextureVertice	t24		= new TextureVertice(0.015625f * 32, 0.015625f * 30);
	
	TextureVertice	t1v		= new TextureVertice(0.015625f * 52.5f, 0.015625f * 0.5f);
	TextureVertice	t2v		= new TextureVertice(0.015625f * 63.5f, 0.015625f * 11.5f);
	TextureVertice	t3v		= new TextureVertice(0.015625f * 52.5f, 0.015625f * 22.5f);
	TextureVertice	t4v		= new TextureVertice(0.015625f * 41.5f, 0.015625f * 11.5f);
	TextureVertice	t5v		= new TextureVertice(0.015625f * 2f, 0.015625f * 18);
	TextureVertice	t6v		= new TextureVertice(0.015625f * 14f, 0.015625f * 18f);
	TextureVertice	t7v		= new TextureVertice(0.015625f * 2f, 0.015625f * 30f);
	TextureVertice	t8v		= new TextureVertice(0.015625f * 14f, 0.015625f * 30f);
	TextureVertice	t9v		= new TextureVertice(0.015625f * 1f, 0.015625f * 34f);
	TextureVertice	t10v	= new TextureVertice(0.015625f * 15f, 0.015625f * 34f);
	TextureVertice	t11v	= new TextureVertice(0.015625f * 1f, 0.015625f * 48f);
	TextureVertice	t12v	= new TextureVertice(0.015625f * 15f, 0.015625f * 48f);
	
	float			s		= 0.5f;
	
	Vertice			v1		= new Vertice(s, -s, s);
	Vertice			v2		= new Vertice(s, -s, -s);
	Vertice			v3		= new Vertice(-s, -s, -s);
	Vertice			v4		= new Vertice(-s, -s, s);
	
	Vertice			v5		= new Vertice(s, s, s);
	Vertice			v6		= new Vertice(s, s, -s);
	Vertice			v7		= new Vertice(-s, s, -s);
	Vertice			v8		= new Vertice(-s, s, s);
	
	Vertice			v9		= new Vertice(0.4375f, 0.8125f, 0.4375f);
	Vertice			v10		= new Vertice(0.4375f, 0.8125f, -0.4375f);
	Vertice			v11		= new Vertice(-0.4375f, 0.8125f, -0.4375f);
	Vertice			v12		= new Vertice(-0.4375f, 0.8125f, 0.4375f);
	
	Vertice			v13		= new Vertice(0.5f, 0.8125f, 0.5f);
	Vertice			v14		= new Vertice(0.5f, 0.8125f, -0.5f);
	Vertice			v15		= new Vertice(-0.5f, 0.8125f, -0.5f);
	Vertice			v16		= new Vertice(-0.5f, 0.8125f, 0.5f);
	
	public void renderModel()
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		addFace(v13, v14, v15, v16, t14, t2, t3, t17);
		addFace(v9, v5, v6, v10, t7, t4, t18, t12);
		addFace(v10, v6, v7, v11, t15, t19, t22, t16);
		addFace(v11, v7, v8, v12, t7, t4, t18, t12);
		addFace(v12, v8, v5, v9, t21, t24, t23, t20);
		
		addFace(v5, v1, v2, v6, t2, t14, t13, t1);
		addFace(v6, v2, v3, v7, t2, t14, t13, t1);
		addFace(v7, v3, v4, v8, t2, t14, t13, t1);
		addFace(v8, v4, v1, v5, t2, t14, t13, t1);
		addFace(v2, v1, v4, v3, t1v, t2v, t3v, t4v);
		xoff = 0.9375f;
		addFace(v5, v1, v2, v6, t1v, t2v, t3v, t4v);
		xoff = 1f;
		zoff = 0.9375f;
		addFace(v6, v2, v3, v7, t1v, t2v, t3v, t4v);
		xoff = 0.9375f;
		zoff = 1f;
		addFace(v7, v3, v4, v8, t1v, t2v, t3v, t4v);
		xoff = 1f;
		zoff = 0.9375f;
		addFace(v8, v4, v1, v5, t1v, t2v, t3v, t4v);
		zoff = 1f;
		
		xoff = yoff = zoff = 0.6875F;
		addFace(v2, v1, v4, v3, t5v, t6v, t8v, t7v);
		addFace(v5, v1, v2, v6, t5v, t6v, t8v, t7v);
		addFace(v6, v2, v3, v7, t5v, t6v, t8v, t7v);
		addFace(v7, v3, v4, v8, t5v, t6v, t8v, t7v);
		addFace(v8, v4, v1, v5, t5v, t6v, t8v, t7v);
		addFace(v5, v6, v7, v8, t5v, t6v, t8v, t7v);
		
		xoff = yoff = zoff = 0.8125F;
		addFace(v2, v1, v4, v3, t9v, t10v, t12v, t11v);
		addFace(v5, v1, v2, v6, t9v, t10v, t12v, t11v);
		addFace(v6, v2, v3, v7, t9v, t10v, t12v, t11v);
		addFace(v7, v3, v4, v8, t9v, t10v, t12v, t11v);
		addFace(v8, v4, v1, v5, t9v, t10v, t12v, t11v);
		addFace(v5, v6, v7, v8, t9v, t10v, t12v, t11v);
		xoff = yoff = zoff = 1F;
		GL11.glPopMatrix();
	}
	
	private void addFace(Vertice v1, Vertice v2, Vertice v3, Vertice v4, TextureVertice t1, TextureVertice t2, TextureVertice t3, TextureVertice t4)
	{
		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		addVertice(v1, t1);
		addVertice(v2, t2);
		addVertice(v3, t3);
		addVertice(v4, t4);
		t.draw();
	}
	
	private void addVertice(Vertice v, TextureVertice t)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.addVertexWithUV(v.x * xoff, v.y * yoff, v.z * zoff, t.x, t.y);
	}
}
