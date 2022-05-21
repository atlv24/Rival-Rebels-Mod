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

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.entity.EntityFlameBall2;

public class RenderFlameBlue extends Render
{
	public void renderFlame(EntityFlameBall2 ell, double x, double y, double z, float yaw, float pitch)
	{
		if (ell.ticksExisted < 3) return;
		GL11.glPushMatrix();
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		// GL11.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL14.glBlendEquation(GL14.GL_FUNC_ADD);
		GL11.glColor4f(1f, 1f, 1f, 1f);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etflameblue);
		
		GL11.glPushMatrix();
		float X = (ell.sequence % 4) / 4f;
		float Y = (ell.sequence - (ell.sequence % 4)) / 16f;
		float size = 0.070f * ell.ticksExisted;
		size *= size;
		if (size >= 0.3) size = 0.3f;
		Tessellator t = Tessellator.instance;
		GL11.glTranslated(x, y, z);
		GL11.glRotatef(180 - Minecraft.getMinecraft().thePlayer.rotationYaw, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(90 - Minecraft.getMinecraft().thePlayer.rotationPitch, 1.0F, 0.0F, 0.0F);
		GL11.glPushMatrix();
		GL11.glRotatef(ell.rotation, 0.0F, 1.0F, 0.0F);
		t.startDrawingQuads();
		t.setNormal(0.0F, 1.0F, 0.0F);
		t.addVertexWithUV(-size, 0, -size, X, Y);
		t.addVertexWithUV(size, 0, -size, X + 0.25f, Y);
		t.addVertexWithUV(size, 0, size, X + 0.25f, Y + 0.25f);
		t.addVertexWithUV(-size, 0, size, X, Y + 0.25f);
		t.draw();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRender(Entity entityLaserLink, double x, double y, double z, float yaw, float pitch)
	{
		renderFlame((EntityFlameBall2) entityLaserLink, x, y, z, yaw, pitch);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
