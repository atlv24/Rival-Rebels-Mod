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
import assets.rivalrebels.client.renderhelper.TextureFace;
import assets.rivalrebels.client.renderhelper.TextureVertice;
import assets.rivalrebels.client.renderhelper.Vertice;

public class ModelRocketLauncherHandle
{
	TextureFace	handleside		= new TextureFace(
										new TextureVertice(0f / 64f, 11f / 32f),
										new TextureVertice(0f / 64f, 18f / 32f),
										new TextureVertice(4f / 64f, 18f / 32f),
										new TextureVertice(4f / 64f, 11f / 32f));
	
	TextureFace	handlefront		= new TextureFace(
										new TextureVertice(4f / 64f, 11f / 32f),
										new TextureVertice(4f / 64f, 18f / 32f),
										new TextureVertice(7f / 64f, 18f / 32f),
										new TextureVertice(7f / 64f, 11f / 32f));
	
	TextureFace	handlebottom	= new TextureFace(
										new TextureVertice(4f / 64f, 18f / 32f),
										new TextureVertice(0f / 64f, 18f / 32f),
										new TextureVertice(0f / 64f, 21f / 32f),
										new TextureVertice(4f / 64f, 21f / 32f));
	
	TextureFace	bottomside		= new TextureFace(
										new TextureVertice(49f / 64f, 10f / 32f),
										new TextureVertice(46f / 64f, 13f / 32f),
										new TextureVertice(36f / 64f, 13f / 32f),
										new TextureVertice(36f / 64f, 10f / 32f));
	
	TextureFace	bottomfront		= new TextureFace(
										new TextureVertice(46f / 64f, 13f / 32f),
										new TextureVertice(50f / 64f, 13f / 32f),
										new TextureVertice(50f / 64f, 17f / 32f),
										new TextureVertice(46f / 64f, 17f / 32f));
	
	TextureFace	bottombottom	= new TextureFace(
										new TextureVertice(46f / 64f, 17f / 32f),
										new TextureVertice(36f / 64f, 17f / 32f),
										new TextureVertice(36f / 64f, 13f / 32f),
										new TextureVertice(46f / 64f, 13f / 32f));
	
	TextureFace	bottomback		= new TextureFace(
										new TextureVertice(33f / 64f, 13f / 32f),
										new TextureVertice(36f / 64f, 13f / 32f),
										new TextureVertice(36f / 64f, 17f / 32f),
										new TextureVertice(33f / 64f, 17f / 32f));
	
	Vertice		vht1			= new Vertice(11f, 0f, 2f);
	Vertice		vht2			= new Vertice(15f, 0f, 2f);
	Vertice		vht3			= new Vertice(15f, 0f, -2f);
	Vertice		vht4			= new Vertice(11f, 0f, -2f);
	Vertice		vhb1			= new Vertice(7f, -7f, 2f);
	Vertice		vhb2			= new Vertice(11f, -7f, 2f);
	Vertice		vhb3			= new Vertice(11f, -7f, -2f);
	Vertice		vhb4			= new Vertice(7f, -7f, -2f);
	Vertice		vbt1			= new Vertice(8f, 3f, 2f);
	Vertice		vbt2			= new Vertice(23f, 3f, 2f);
	Vertice		vbt3			= new Vertice(23f, 3f, -2f);
	Vertice		vbt4			= new Vertice(8f, 3f, -2f);
	Vertice		vbb1			= new Vertice(8f, 0f, 2f);
	Vertice		vbb2			= new Vertice(20f, 0f, 2f);
	Vertice		vbb3			= new Vertice(20f, 0f, -2f);
	Vertice		vbb4			= new Vertice(8f, 0f, -2f);
	
	public void render()
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glPushMatrix();
		GL11.glScaled(1.3, 1, 1);
		// bottom
		RenderHelper.addFace(vbt3, vbt4, vbt1, vbt2, bottombottom);
		RenderHelper.addFace(vbb1, vbt1, vbt4, vbb4, bottomfront);
		RenderHelper.addFace(vbb3, vbt3, vbt2, vbb2, bottomback);
		RenderHelper.addFace(vbt2, vbb2, vbb1, vbt1, bottomside);
		RenderHelper.addFace(vbt3, vbb3, vbb4, vbt4, bottomside);
		RenderHelper.addFace(vbb3, vbb4, vbb1, vbb2, bottombottom);
		GL11.glPopMatrix();
		
		// handle
		RenderHelper.addFace(vht4, vhb4, vhb1, vht1, handlefront);
		RenderHelper.addFace(vht2, vhb2, vhb3, vht3, handlefront);
		RenderHelper.addFace(vht1, vhb1, vhb2, vht2, handleside);
		RenderHelper.addFace(vht3, vhb3, vhb4, vht4, handleside);
		RenderHelper.addFace(vhb2, vhb1, vhb4, vhb3, handlebottom);
		GL11.glPopMatrix();
	}
}
