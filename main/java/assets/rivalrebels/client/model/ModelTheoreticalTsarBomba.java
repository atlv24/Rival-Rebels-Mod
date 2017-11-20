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

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.renderhelper.RenderHelper;
import assets.rivalrebels.client.renderhelper.TextureVertice;
import assets.rivalrebels.client.renderhelper.Vertice;
import assets.rivalrebels.client.tileentityrender.TileEntityTsarBombaRenderer;

public class ModelTheoreticalTsarBomba
{
	private float[]	tsarx		= { 0.5f, 0.5f, 0.875f, 1f, 1f, 0.875f, 0.5f, 0f };
	private float[]	tsary		= { -5f, -3.5f, -2f, -1f, 1f, 2f, 2.75f, 3f };
	private float[]	tsart		= { 1f, 0.8125f, 0.625f, 0.5f, 0.25f, 0.125f, 0.03125f, 0f };
	
	private int		segments	= 20;
	private float	deg			= (float) Math.PI * 2f / segments;
	private float	sin			= (float) Math.sin(deg);
	private float	cos			= (float) Math.cos(deg);
	private float	add			= 360 / segments;
	
	public void render()
	{
		GL11.glPushMatrix();
		GL11.glScalef(RivalRebels.nukeScale,RivalRebels.nukeScale,RivalRebels.nukeScale);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.ettheoreticaltsarshell1);
		for (float i = 0; i < segments; i++)
		{
			GL11.glPushMatrix();
			GL11.glRotatef(add * i, 0, 1, 0);
			for (int f = 1; f < tsarx.length; f++)
			{
				TextureVertice t1 = new TextureVertice((1f / segments) * i, tsart[f]);
				TextureVertice t2 = new TextureVertice((1f / segments) * i, tsart[f - 1]);
				TextureVertice t3 = new TextureVertice((1f / segments) * (i + 1), tsart[f - 1]);
				TextureVertice t4 = new TextureVertice((1f / segments) * (i + 1), tsart[f]);
				RenderHelper.addFace(new Vertice(0f, tsary[f], tsarx[f]),
						new Vertice(0f, tsary[f - 1], tsarx[f - 1]),
						new Vertice(tsarx[f - 1] * sin, tsary[f - 1], tsarx[f - 1] * cos),
						new Vertice(tsarx[f] * sin, tsary[f], tsarx[f] * cos), t1, t2, t3, t4);
			}
			GL11.glPopMatrix();
		}
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.ettheoreticaltsarshell2);
		for (float i = 0; i < segments; i++)
		{
			GL11.glPushMatrix();
			GL11.glRotatef(add * i, 0, 1, 0);
			GL11.glScalef(0.85f,0.95f,0.85f);
			for (int f = 1; f < tsarx.length; f++)
			{
				TextureVertice t1 = new TextureVertice((1f / segments) * i, tsart[f]);
				TextureVertice t2 = new TextureVertice((1f / segments) * i, tsart[f - 1]);
				TextureVertice t3 = new TextureVertice((1f / segments) * (i + 1), tsart[f - 1]);
				TextureVertice t4 = new TextureVertice((1f / segments) * (i + 1), tsart[f]);
				RenderHelper.addFace(new Vertice(0f, tsary[f], tsarx[f]),
						new Vertice(0f, tsary[f - 1], tsarx[f - 1]),
						new Vertice(tsarx[f - 1] * sin, tsary[f - 1], tsarx[f - 1] * cos),
						new Vertice(tsarx[f] * sin, tsary[f], tsarx[f] * cos), t1, t2, t3, t4);
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
		
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.ettsarfins);
		
		GL11.glPushMatrix();
		
		TextureVertice t5 = new TextureVertice(70f / 256f, 0f);
		TextureVertice t6 = new TextureVertice(134f / 256f, 0f);
		TextureVertice t7 = new TextureVertice(134f / 256f, 64f / 256f);
		TextureVertice t8 = new TextureVertice(70 / 256f, 64f / 256f);
		
		RenderHelper.addFace(new Vertice(0.5f, -5f, 0.5f),
				new Vertice(-0.5f, -5f, 0.5f),
				new Vertice(-0.5f, -5f, -0.5f),
				new Vertice(0.5f, -5f, -0.5f), t5, t6, t7, t8);
		
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		
		TextureVertice t1 = new TextureVertice(0f, 0f);
		TextureVertice t2 = new TextureVertice(70f / 256f, 0f);
		TextureVertice t3 = new TextureVertice(70f / 256f, 96f / 256f);
		TextureVertice t4 = new TextureVertice(0, 96f / 256f);
		
		RenderHelper.addFace(new Vertice(0f, -5f, -1.4f),
				new Vertice(0f, -5f, -0.5f),
				new Vertice(0f, -3.5f, -0.5f),
				new Vertice(0f, -3.5f, -1.4f), t1, t2, t3, t4);
		
		GL11.glRotatef(120, 0, 1, 0);
		RenderHelper.addFace(new Vertice(0f, -5f, -1.4f),
				new Vertice(0f, -5f, -0.5f),
				new Vertice(0f, -3.5f, -0.5f),
				new Vertice(0f, -3.5f, -1.4f), t1, t2, t3, t4);
		
		GL11.glRotatef(120, 0, 1, 0);
		RenderHelper.addFace(new Vertice(0f, -5f, -1.4f),
				new Vertice(0f, -5f, -0.5f),
				new Vertice(0f, -3.5f, -0.5f),
				new Vertice(0f, -3.5f, -1.4f), t1, t2, t3, t4);

		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
