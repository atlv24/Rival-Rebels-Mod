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
package assets.rivalrebels.client.renderentity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import assets.rivalrebels.common.entity.EntityLaserBurst;

public class RenderLaserBurst extends Render
{
	static float	red		= 1F;
	static float	green	= 0.0F;
	static float	blue	= 0.0F;
	
	public void renderLaserBurst(EntityLaserBurst ell, double x, double y, double z, float yaw, float pitch)
	{
		float radius = 0.12F;
		int distance = 4;
		Tessellator tessellator = Tessellator.instance;
		
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glTranslatef((float) x, (float) y, (float) z);
		// Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.btsteel);
		// RenderHelper.drawPoint(new Vertice(0, 0, 0), 1);
		
		GL11.glRotatef(ell.rotationYaw, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-ell.rotationPitch, 1.0F, 0.0F, 0.0F);
		
		for (float o = 0; o <= radius; o += radius / 8)
		{
			float color = 1f - (o * 8.333f);
			if (color < 0) color = 0;
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(red, color, color, 1f);
			tessellator.addVertex(0 + o, 0 - o, 0);
			tessellator.addVertex(0 + o, 0 + o, 0);
			tessellator.addVertex(0 + o, 0 + o, 0 + distance);
			tessellator.addVertex(0 + o, 0 - o, 0 + distance);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(red, color, color, 1f);
			tessellator.addVertex(0 - o, 0 - o, 0);
			tessellator.addVertex(0 + o, 0 - o, 0);
			tessellator.addVertex(0 + o, 0 - o, 0 + distance);
			tessellator.addVertex(0 - o, 0 - o, 0 + distance);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(red, color, color, 1f);
			tessellator.addVertex(0 - o, 0 + o, 0);
			tessellator.addVertex(0 - o, 0 - o, 0);
			tessellator.addVertex(0 - o, 0 - o, 0 + distance);
			tessellator.addVertex(0 - o, 0 + o, 0 + distance);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(red, color, color, 1f);
			tessellator.addVertex(0 + o, 0 + o, 0);
			tessellator.addVertex(0 - o, 0 + o, 0);
			tessellator.addVertex(0 - o, 0 + o, 0 + distance);
			tessellator.addVertex(0 + o, 0 + o, 0 + distance);
			tessellator.draw();
		}
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRender(Entity entityLaserBurst, double x, double y, double z, float yaw, float pitch)
	{
		this.renderLaserBurst((EntityLaserBurst) entityLaserBurst, x, y, z, yaw, pitch);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
