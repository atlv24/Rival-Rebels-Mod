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
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelLaptop;
import assets.rivalrebels.common.entity.EntityLaptop;

public class RenderLaptop extends Render
{
	ModelLaptop	ml;
	
	public RenderLaptop()
	{
		ml = new ModelLaptop();
	}
	
	@Override
	public void doRender(Entity var1, double d, double d1, double d2, float var8, float var9)
	{
		GL11.glEnable(GL11.GL_LIGHTING);
		EntityLaptop tile = (EntityLaptop) var1;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glRotatef(180 - var1.rotationYaw, 0, 1, 0);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etlaptop);
		ml.renderModel((float) -tile.slide);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etubuntu);
		ml.renderScreen((float) -tile.slide);
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
