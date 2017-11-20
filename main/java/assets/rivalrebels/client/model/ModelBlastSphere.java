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

public class ModelBlastSphere
{
	Vertice	vx	= new Vertice(1, 0, 0).normalize();
	Vertice	vy	= new Vertice(0, 1, 0).normalize();
	Vertice	vz	= new Vertice(0, 0, 1).normalize();
	Vertice	vxy	= new Vertice(0.5f, 0.5f, 0).normalize();
	Vertice	vyz	= new Vertice(0, 0.5f, 0.5f).normalize();
	Vertice	vxz	= new Vertice(0.5f, 0, 0.5f).normalize();
	Vertice	vx1	= new Vertice(0.75f, 0.25f, 0).normalize();
	Vertice	vx2	= new Vertice(0.5f, 0.25f, 0.25f).normalize();
	Vertice	vx3	= new Vertice(0.75f, 0, 0.25f).normalize();
	Vertice	vy1	= new Vertice(0, 0.75f, 0.25f).normalize();
	Vertice	vy2	= new Vertice(0.25f, 0.5f, 0.25f).normalize();
	Vertice	vy3	= new Vertice(0.25f, 0.75f, 0).normalize();
	Vertice	vz1	= new Vertice(0.25f, 0, 0.75f).normalize();
	Vertice	vz2	= new Vertice(0.25f, 0.25f, 0.5f).normalize();
	Vertice	vz3	= new Vertice(0, 0.25f, 0.75f).normalize();
	
	public void renderModel(float size, float red, float green, float blue, float alpha)
	{
		renderModel(size, red, green, blue, alpha, true);
	}
	
	public void renderModel(float size, float red, float green, float blue, float alpha, boolean blend)
	{
		GL11.glPushMatrix();
		GL11.glColor4f(red, green, blue, alpha);
		GL11.glScalef(size, size, size);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		if (blend)
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		}
		for (int i = 0; i < 2; i++)
		{
			GL11.glRotatef(i * 180, 0, 0, 1);
			for (int p = 0; p < 4; p++)
			{
				GL11.glPushMatrix();
				GL11.glRotatef(p * 90, 0, 1, 0);
				
				RenderHelper.addTri(vy, vy1, vy3);
				RenderHelper.addTri(vy1, vyz, vy2);
				RenderHelper.addTri(vy3, vy2, vxy);
				RenderHelper.addTri(vy1, vy2, vy3);
				
				RenderHelper.addTri(vx, vx1, vx3);
				RenderHelper.addTri(vx1, vxy, vx2);
				RenderHelper.addTri(vx3, vx2, vxz);
				RenderHelper.addTri(vx1, vx2, vx3);
				
				RenderHelper.addTri(vz, vz1, vz3);
				RenderHelper.addTri(vz1, vxz, vz2);
				RenderHelper.addTri(vz3, vz2, vyz);
				RenderHelper.addTri(vz1, vz2, vz3);
				
				RenderHelper.addTri(vyz, vz2, vy2);
				RenderHelper.addTri(vxy, vy2, vx2);
				RenderHelper.addTri(vxz, vx2, vz2);
				RenderHelper.addTri(vx2, vy2, vz2);
				
				GL11.glPopMatrix();
			}
		}
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
}
