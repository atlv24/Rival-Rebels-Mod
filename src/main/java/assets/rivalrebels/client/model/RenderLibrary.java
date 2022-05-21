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

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderLibrary
{
	Tessellator					t		= Tessellator.instance;
	Random						random	= new Random();
	public static RenderLibrary	instance;
	
	public RenderLibrary()
	{
		
	}
	
	public void init()
	{
		instance = this;
	}
	
	public void renderModel(float x1, float y1, float z1, float x, float y, float z, float segDist, float radius, int steps, float arcRatio, float rvar, float r, float g, float b, float a)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(x1, y1, z1);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glRotated(Math.atan2(x, z) * 57.295779513 - 90, 0, 1, 0);
		GL11.glColor4f(r, g, b, a);
		double dist = Math.sqrt(x * x + z * z);
		double hdist = dist / 2f;
		double hdists = hdist * hdist;
		double rs = radius / steps;
		int segNum = (int) Math.ceil(dist / segDist) + 1;
		double[] xv = new double[segNum];
		double[] yv = new double[segNum];
		double[] zv = new double[segNum];
		
		for (int i = 1; i < segNum; i++)
		{
			double interp = (double) i / (double) segNum;
			double X = (dist * interp);
			double Y = (y * interp);
			double ihdist = (dist * interp) - hdist;
			
			Y += ((hdists - (ihdist * ihdist)) / hdists) * arcRatio;
			xv[i] = X;
			yv[i] = Y + random.nextGaussian() * rvar;
			zv[i] = random.nextGaussian() * rvar;
		}
		
		xv[0] = 0;
		yv[0] = 0;
		zv[0] = 0;
		
		for (int o = 0; o < steps; o++)
		{
			t.startDrawingQuads();
			for (int i = 1; i < segNum; i++)
			{
				double s = rs * o;
				t.addVertex(xv[i - 1], yv[i - 1] + s, zv[i - 1] - s);
				t.addVertex(xv[i - 1], yv[i - 1] + s, zv[i - 1] + s);
				t.addVertex(xv[i], yv[i] + s, zv[i] + s);
				t.addVertex(xv[i], yv[i] + s, zv[i] - s);
				
				t.addVertex(xv[i - 1], yv[i - 1] + s, zv[i - 1] + s);
				t.addVertex(xv[i - 1], yv[i - 1] - s, zv[i - 1] + s);
				t.addVertex(xv[i], yv[i] - s, zv[i] + s);
				t.addVertex(xv[i], yv[i] + s, zv[i] + s);
				
				t.addVertex(xv[i - 1], yv[i - 1] - s, zv[i - 1] - s);
				t.addVertex(xv[i - 1], yv[i - 1] + s, zv[i - 1] - s);
				t.addVertex(xv[i], yv[i] + s, zv[i] - s);
				t.addVertex(xv[i], yv[i] - s, zv[i] - s);
				
				t.addVertex(xv[i - 1], yv[i - 1] - s, zv[i - 1] + s);
				t.addVertex(xv[i - 1], yv[i - 1] - s, zv[i - 1] - s);
				t.addVertex(xv[i], yv[i] - s, zv[i] - s);
				t.addVertex(xv[i], yv[i] - s, zv[i] + s);
			}
			t.draw();
		}
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}
}