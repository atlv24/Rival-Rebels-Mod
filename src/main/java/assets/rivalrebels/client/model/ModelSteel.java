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

import assets.rivalrebels.client.renderhelper.Vertice;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSteel
{
	Tessellator	tessellator	= Tessellator.instance;
	float		s			= 0.5F;
	
	Vertice		v1			= new Vertice(s, s, s);
	Vertice		v2			= new Vertice(s, s, -s);
	Vertice		v3			= new Vertice(-s, s, -s);
	Vertice		v4			= new Vertice(-s, s, s);
	
	Vertice		v5			= new Vertice(s, -s, s);
	Vertice		v6			= new Vertice(s, -s, -s);
	Vertice		v7			= new Vertice(-s, -s, -s);
	Vertice		v8			= new Vertice(-s, -s, s);
	
	public void renderModel()
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		tessellator.startDrawingQuads();
		addVertex(v1, 0, 0, true);
		addVertex(v5, 1, 0, true);
		addVertex(v8, 1, 1, true);
		addVertex(v4, 0, 1, true);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		addVertex(v4, 0, 0, true);
		addVertex(v8, 1, 0, true);
		addVertex(v7, 1, 1, true);
		addVertex(v3, 0, 1, true);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		addVertex(v3, 0, 0, true);
		addVertex(v7, 1, 0, true);
		addVertex(v6, 1, 1, true);
		addVertex(v2, 0, 1, true);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		addVertex(v2, 0, 0, true);
		addVertex(v6, 1, 0, true);
		addVertex(v5, 1, 1, true);
		addVertex(v1, 0, 1, true);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		addVertex(v4, 0, 0, true);
		addVertex(v3, 1, 0, true);
		addVertex(v2, 1, 1, true);
		addVertex(v1, 0, 1, true);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		addVertex(v5, 0, 0, true);
		addVertex(v6, 1, 0, true);
		addVertex(v7, 1, 1, true);
		addVertex(v8, 0, 1, true);
		tessellator.draw();
		
		GL11.glPopMatrix();
	}
	
	private void addVertex(Vertice v, double t, double t2, boolean offset)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.addVertexWithUV(v.x * 0.999, v.y * 0.999, v.z * 0.999, t, t2);
	}
}
