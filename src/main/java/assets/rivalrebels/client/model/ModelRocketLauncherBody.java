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

public class ModelRocketLauncherBody
{
	float			m			= 0.3125f;
	float			l			= 0.4375f;
	float			o			= 0.5f;
	float			n			= 1.5f;
	
	Vertice			llauncher1	= new Vertice(-m, o, n);
	Vertice			llauncher2	= new Vertice(-l, l, n);
	Vertice			llauncher3	= new Vertice(-o, m, n);
	Vertice			llauncher4	= new Vertice(-o, -m, n);
	Vertice			llauncher5	= new Vertice(-l, -l, n);
	Vertice			llauncher6	= new Vertice(-m, -o, n);
	Vertice			llauncher7	= new Vertice(m, -o, n);
	Vertice			llauncher8	= new Vertice(l, -l, n);
	Vertice			llauncher9	= new Vertice(o, -m, n);
	Vertice			llauncher10	= new Vertice(o, m, n);
	Vertice			llauncher11	= new Vertice(l, l, n);
	Vertice			llauncher12	= new Vertice(m, o, n);
	
	Vertice			rlauncher1	= new Vertice(-m, o, -n);
	Vertice			rlauncher2	= new Vertice(-l, l, -n);
	Vertice			rlauncher3	= new Vertice(-o, m, -n);
	Vertice			rlauncher4	= new Vertice(-o, -m, -n);
	Vertice			rlauncher5	= new Vertice(-l, -l, -n);
	Vertice			rlauncher6	= new Vertice(-m, -o, -n);
	Vertice			rlauncher7	= new Vertice(m, -o, -n);
	Vertice			rlauncher8	= new Vertice(l, -l, -n);
	Vertice			rlauncher9	= new Vertice(o, -m, -n);
	Vertice			rlauncher10	= new Vertice(o, m, -n);
	Vertice			rlauncher11	= new Vertice(l, l, -n);
	Vertice			rlauncher12	= new Vertice(m, o, -n);
	
	float			a			= 0f;
	float			b			= 0.09375f;
	float			c			= 0.1875f;
	float			d			= 0.5f;
	float			e			= 0.59375f;
	float			f			= 0.6875f;
	float			g			= 1f;
	float			h			= 0.125f;
	float			i			= 0.625f;
	
	TextureVertice	l1f			= new TextureVertice(i, f);
	TextureVertice	l1s			= new TextureVertice(i, c);
	TextureVertice	l2			= new TextureVertice(i, b);
	TextureVertice	l3f			= new TextureVertice(i, a);
	TextureVertice	l3s			= new TextureVertice(g, c);
	TextureVertice	l4			= new TextureVertice(g, d);
	TextureVertice	l5			= new TextureVertice(g, e);
	TextureVertice	l6f			= new TextureVertice(g, f);
	TextureVertice	l6s			= new TextureVertice(h, g);
	TextureVertice	l7			= new TextureVertice(h, f);
	TextureVertice	l8			= new TextureVertice(h, e);
	TextureVertice	l9			= new TextureVertice(h, d);
	TextureVertice	l10			= new TextureVertice(h, c);
	TextureVertice	l11			= new TextureVertice(h, b);
	TextureVertice	l12f		= new TextureVertice(h, a);
	TextureVertice	l12s		= new TextureVertice(i, g);
	
	TextureVertice	r1f			= new TextureVertice(g, f);
	TextureVertice	r1s			= new TextureVertice(g, c);
	TextureVertice	r2			= new TextureVertice(g, b);
	TextureVertice	r3f			= new TextureVertice(g, a);
	TextureVertice	r3s			= new TextureVertice(i, c);
	TextureVertice	r4			= new TextureVertice(i, d);
	TextureVertice	r5			= new TextureVertice(i, e);
	TextureVertice	r6f			= new TextureVertice(i, f);
	TextureVertice	r6s			= new TextureVertice(d, g);
	TextureVertice	r7			= new TextureVertice(d, f);
	TextureVertice	r8			= new TextureVertice(d, e);
	TextureVertice	r9			= new TextureVertice(d, d);
	TextureVertice	r10			= new TextureVertice(d, c);
	TextureVertice	r11			= new TextureVertice(d, b);
	TextureVertice	r12f		= new TextureVertice(d, a);
	TextureVertice	r12s		= new TextureVertice(g, g);
	
	float			p			= 0.125f;
	float			q			= 0.1093755f;
	float			r			= 0.078125f;
	float			k			= 0.34375f;
	float			t			= 0.125f;
	float			u			= 0.625f;
	
	TextureVertice	ls1			= new TextureVertice((t - r) / 2f, k + p * 2);
	TextureVertice	ls2			= new TextureVertice((t - q) / 2f, k + q * 2);
	TextureVertice	ls3			= new TextureVertice((t - p) / 2f, k + r * 2);
	TextureVertice	ls4			= new TextureVertice((t - p) / 2f, k - r * 2);
	TextureVertice	ls5			= new TextureVertice((t - q) / 2f, k - q * 2);
	TextureVertice	ls6			= new TextureVertice((t - r) / 2f, k - p * 2);
	TextureVertice	ls7			= new TextureVertice((t + r) / 2f, k - p * 2);
	TextureVertice	ls8			= new TextureVertice((t + q) / 2f, k - q * 2);
	TextureVertice	ls9			= new TextureVertice((t + p) / 2f, k - r * 2);
	TextureVertice	ls10		= new TextureVertice((t + p) / 2f, k + r * 2);
	TextureVertice	ls11		= new TextureVertice((t + q) / 2f, k + q * 2);
	TextureVertice	ls12		= new TextureVertice((t + r) / 2f, k + p * 2);
	
	TextureVertice	rs1			= new TextureVertice((u - r) / 2f + 0.25f, k + p * 2);
	TextureVertice	rs2			= new TextureVertice((u - q) / 2f + 0.25f, k + q * 2);
	TextureVertice	rs3			= new TextureVertice((u - p) / 2f + 0.25f, k + r * 2);
	TextureVertice	rs4			= new TextureVertice((u - p) / 2f + 0.25f, k - r * 2);
	TextureVertice	rs5			= new TextureVertice((u - q) / 2f + 0.25f, k - q * 2);
	TextureVertice	rs6			= new TextureVertice((u - r) / 2f + 0.25f, k - p * 2);
	TextureVertice	rs7			= new TextureVertice((u + r) / 2f + 0.25f, k - p * 2);
	TextureVertice	rs8			= new TextureVertice((u + q) / 2f + 0.25f, k - q * 2);
	TextureVertice	rs9			= new TextureVertice((u + p) / 2f + 0.25f, k - r * 2);
	TextureVertice	rs10		= new TextureVertice((u + p) / 2f + 0.25f, k + r * 2);
	TextureVertice	rs11		= new TextureVertice((u + q) / 2f + 0.25f, k + q * 2);
	TextureVertice	rs12		= new TextureVertice((u + r) / 2f + 0.25f, k + p * 2);
	
	public void render()
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		RenderHelper.addFace(llauncher1, llauncher12, rlauncher12, rlauncher1, l1f, l12s, r12s, r1f);
		RenderHelper.addFace(llauncher2, llauncher1, rlauncher1, rlauncher2, l2, l1s, r1s, r2);
		RenderHelper.addFace(llauncher3, llauncher2, rlauncher2, rlauncher3, l3f, l2, r2, r3f);
		RenderHelper.addFace(llauncher4, llauncher3, rlauncher3, rlauncher4, l4, l3s, r3s, r4);
		RenderHelper.addFace(llauncher5, llauncher4, rlauncher4, rlauncher5, l5, l4, r4, r5);
		RenderHelper.addFace(llauncher6, llauncher5, rlauncher5, rlauncher6, l6f, l5, r5, r6f);
		RenderHelper.addFace(llauncher7, llauncher6, rlauncher6, rlauncher7, l7, l6s, r6s, r7);
		RenderHelper.addFace(llauncher8, llauncher7, rlauncher7, rlauncher8, l8, l7, r7, r8);
		RenderHelper.addFace(llauncher9, llauncher8, rlauncher8, rlauncher9, l9, l8, r8, r9);
		RenderHelper.addFace(llauncher10, llauncher9, rlauncher9, rlauncher10, l10, l9, r9, r10);
		RenderHelper.addFace(llauncher11, llauncher10, rlauncher10, rlauncher11, l11, l10, r10, r11);
		RenderHelper.addFace(llauncher12, llauncher11, rlauncher11, rlauncher12, l12f, l11, r11, r12f);
		
		RenderHelper.addFace(llauncher1, llauncher2, llauncher11, llauncher12, ls6, ls5, ls8, ls7); // left side
		RenderHelper.addFace(llauncher2, llauncher3, llauncher10, llauncher11, ls5, ls4, ls9, ls8);
		RenderHelper.addFace(llauncher3, llauncher4, llauncher9, llauncher10, ls4, ls3, ls10, ls9);
		RenderHelper.addFace(llauncher4, llauncher5, llauncher8, llauncher9, ls3, ls2, ls11, ls10);
		RenderHelper.addFace(llauncher5, llauncher6, llauncher7, llauncher8, ls2, ls1, ls12, ls11);
		
		RenderHelper.addFace(rlauncher2, rlauncher1, rlauncher12, rlauncher11, rs8, rs7, rs6, rs5); // right side
		RenderHelper.addFace(rlauncher3, rlauncher2, rlauncher11, rlauncher10, rs9, rs8, rs5, rs4);
		RenderHelper.addFace(rlauncher4, rlauncher3, rlauncher10, rlauncher9, rs10, rs9, rs4, rs3);
		RenderHelper.addFace(rlauncher5, rlauncher4, rlauncher9, rlauncher8, rs11, rs10, rs3, rs2);
		RenderHelper.addFace(rlauncher6, rlauncher5, rlauncher8, rlauncher7, rs12, rs11, rs2, rs1);
		GL11.glPopMatrix();
	}
}
