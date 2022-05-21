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
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL14;

import assets.rivalrebels.RivalRebels;

public class RenderBullet extends Render
{
	/**
	 * Have the icon index (in items.png) that will be used to render the image. Currently, eggs and snowballs uses this classes.
	 */
	private String	path;
	
	public RenderBullet(String par1)
	{
		path = par1;
	}
	
	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then handing it off to a worker function which does the actual work. In all
	 * probabilty, the class Render is generic (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1, double d2, float f, float f1). But JAD is pre
	 * 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		if (par1Entity.ticksExisted > 1)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) par2, (float) par4, (float) par6);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			if (path == "flame") Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etflame);
			if (path == "fire") Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etfire);
			Tessellator var10 = Tessellator.instance;
			this.func_77026_a(var10);
			GL11.glPopMatrix();
		}
	}
	
	private void func_77026_a(Tessellator par1Tessellator)
	{
		float var7 = 1.0F;
		float var8 = 0.5F;
		float var9 = 0.25F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		par1Tessellator.startDrawingQuads();
		par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
		par1Tessellator.addVertexWithUV((0.0F - var8), (0.0F - var9), 0.0D, 0, 0);
		par1Tessellator.addVertexWithUV((var7 - var8), (0.0F - var9), 0.0D, 1, 0);
		par1Tessellator.addVertexWithUV((var7 - var8), (var7 - var9), 0.0D, 1, 1);
		par1Tessellator.addVertexWithUV((0.0F - var8), (var7 - var9), 0.0D, 0, 1);
		par1Tessellator.draw();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
