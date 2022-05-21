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
import assets.rivalrebels.client.renderhelper.Vertice;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelRocketLauncherTube
{
	Vertice	vy1		= new Vertice(0, 0, 0);
	Vertice	vpx1	= new Vertice(0.5f, 0, 0);
	Vertice	vnx1	= new Vertice(-0.5f, 0, 0);
	Vertice	vpz1	= new Vertice(0, 0, 0.5f);
	Vertice	vnz1	= new Vertice(0, 0, -0.5f);
	Vertice	vpxpz1	= new Vertice(0.3535533f, 0, 0.3535533f);
	Vertice	vpxnz1	= new Vertice(0.3535533f, 0, -0.3535533f);
	Vertice	vnxpz1	= new Vertice(-0.3535533f, 0, 0.3535533f);
	Vertice	vnxnz1	= new Vertice(-0.3535533f, 0, -0.3535533f);
	Vertice	vpx2	= new Vertice(0.5f, 2, 0);
	Vertice	vnx2	= new Vertice(-0.5f, 2, 0);
	Vertice	vpz2	= new Vertice(0, 2, 0.5f);
	Vertice	vnz2	= new Vertice(0, 2, -0.5f);
	Vertice	vpxpz2	= new Vertice(0.3535533f, 2, 0.3535533f);
	Vertice	vpxnz2	= new Vertice(0.3535533f, 2, -0.3535533f);
	Vertice	vnxpz2	= new Vertice(-0.3535533f, 2, 0.3535533f);
	Vertice	vnxnz2	= new Vertice(-0.3535533f, 2, -0.3535533f);
	float	tx1		= 0;
	float	tx2		= 0.28125f;
	float	tx4		= 0.5625f;
	float	tx5		= 0.65625f;
	float	ty1		= 0;
	float	ty2		= 0.09375f;
	
	public void render()
	{
		GL11.glPushMatrix();
		
		RenderHelper.addFace(vpx1, vpx2, vpxpz2, vpxpz1, tx1, tx2, ty1, ty2);
		RenderHelper.addFace(vpxpz1, vpxpz2, vpz2, vpz1, tx1, tx2, ty1, ty2);
		RenderHelper.addFace(vpz1, vpz2, vnxpz2, vnxpz1, tx1, tx2, ty1, ty2);
		RenderHelper.addFace(vnxpz1, vnxpz2, vnx2, vnx1, tx1, tx2, ty1, ty2);
		RenderHelper.addFace(vnx1, vnx2, vnxnz2, vnxnz1, tx1, tx2, ty1, ty2);
		RenderHelper.addFace(vnxnz1, vnxnz2, vnz2, vnz1, tx1, tx2, ty1, ty2);
		RenderHelper.addFace(vnz1, vnz2, vpxnz2, vpxnz1, tx1, tx2, ty1, ty2);
		RenderHelper.addFace(vpxnz1, vpxnz2, vpx2, vpx1, tx1, tx2, ty1, ty2);
		
		RenderHelper.addFace(vpx1, vpxpz1, vpz1, vy1, tx4, tx5, ty1, ty2);
		RenderHelper.addFace(vpz1, vnxpz1, vnx1, vy1, tx4, tx5, ty1, ty2);
		RenderHelper.addFace(vnx1, vnxnz1, vnz1, vy1, tx4, tx5, ty1, ty2);
		RenderHelper.addFace(vnz1, vpxnz1, vpx1, vy1, tx4, tx5, ty1, ty2);
		
		GL11.glPopMatrix();
	}
}