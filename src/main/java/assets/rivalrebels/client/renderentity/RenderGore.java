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
import assets.rivalrebels.client.renderhelper.RenderHelper;
import assets.rivalrebels.common.entity.EntityGore;

public class RenderGore extends Render
{
	private static final ResourceLocation	player			= new ResourceLocation("textures/entity/steve.png");
	private static final ResourceLocation	creeper			= new ResourceLocation("textures/entity/creeper/creeper.png");
	private static final ResourceLocation	enderman		= new ResourceLocation("textures/entity/enderman/enderman.png");
	private static final ResourceLocation	ghast			= new ResourceLocation("textures/entity/ghast/ghast.png");
	private static final ResourceLocation	skeleton		= new ResourceLocation("textures/entity/skeleton/skeleton.png");
	private static final ResourceLocation	slime			= new ResourceLocation("textures/entity/slime/slime.png");
	private static final ResourceLocation	magmacube		= new ResourceLocation("textures/entity/slime/magmacube.png");
	private static final ResourceLocation	spider			= new ResourceLocation("textures/entity/spider/spider.png");
	private static final ResourceLocation	cavespider		= new ResourceLocation("textures/entity/spider/cave_spider.png");
	private static final ResourceLocation	zombiepigman	= new ResourceLocation("textures/entity/zombie_pigman.png");
	private static final ResourceLocation	zombie			= new ResourceLocation("textures/entity/zombie/zombie.png");
	RenderHelper							md				= new RenderHelper();
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(x, y, z);
		GL11.glRotatef(-entity.rotationYaw + 180, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
		EntityGore eg = (EntityGore) entity;
		int mob = eg.mob;
		int type = eg.type;
		double size = eg.size;
		
		if (mob == 0)
		{
			if (eg.playerSkin != null) Minecraft.getMinecraft().renderEngine.bindTexture(eg.playerSkin);
			else Minecraft.getMinecraft().renderEngine.bindTexture(player);
			if (type == 0) RenderHelper.renderBox(8, 8, 8, 0, 0, 64, 32, 16);
			else if (type == 1) RenderHelper.renderBox(4, 12, 8, 16, 16, 64, 32, 16);
			else if (type == 2) RenderHelper.renderBox(4, 12, 4, 40, 16, 64, 32, 16);
			else if (type == 3) RenderHelper.renderBox(4, 12, 4, 0, 16, 64, 32, 16);
		}
		else if (mob == 1)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(zombie);
			if (type == 0) RenderHelper.renderBox(8, 8, 8, 0, 0, 64, 64, 16);
			else if (type == 1) RenderHelper.renderBox(4, 12, 8, 16, 16, 64, 64, 16);
			else if (type == 2) RenderHelper.renderBox(4, 12, 4, 40, 16, 64, 64, 16);
			else if (type == 3) RenderHelper.renderBox(4, 12, 4, 0, 16, 64, 64, 16);
		}
		else if (mob == 2)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(zombiepigman);
			if (type == 0) RenderHelper.renderBox(8, 8, 8, 0, 0, 64, 64, 16);
			else if (type == 1) RenderHelper.renderBox(4, 12, 8, 16, 16, 64, 64, 16);
			else if (type == 2) RenderHelper.renderBox(4, 12, 4, 40, 16, 64, 64, 16);
			else if (type == 3) RenderHelper.renderBox(4, 12, 4, 0, 16, 64, 64, 16);
		}
		else if (mob == 3)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(skeleton);
			GL11.glDisable(GL11.GL_CULL_FACE);
			if (type == 0) RenderHelper.renderBox(8, 8, 8, 0, 0, 64, 32, 16);
			else if (type == 1) RenderHelper.renderBox(4, 12, 8, 16, 16, 64, 32, 16);
			else if (type == 2) RenderHelper.renderBox(2, 10, 2, 40, 16, 64, 32, 16);
			else if (type == 3) RenderHelper.renderBox(2, 10, 2, 0, 16, 64, 32, 16);
		}
		else if (mob == 4)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(enderman);
			if (type == 0)
			{
				RenderHelper.renderBox(8, 8, 8, 0, 0, 64, 32, 16);
				GL11.glTranslated(0, -0.125, 0);
				GL11.glScaled(0.875, 0.875, 0.875);
				RenderHelper.renderBox(8, 8, 8, 0, 16, 64, 32, 16);
			}
			else if (type == 1) RenderHelper.renderBox(4, 12, 8, 32, 16, 64, 32, 16);
			else if (type == 2) RenderHelper.renderBox(2, 30, 2, 56, 0, 64, 32, 16);
			else if (type == 3) RenderHelper.renderBox(2, 30, 2, 56, 0, 64, 32, 16);
		}
		else if (mob == 5)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(creeper);
			if (type == 0) RenderHelper.renderBox(8, 8, 8, 0, 0, 64, 32, 16);
			else if (type == 1) RenderHelper.renderBox(4, 12, 8, 16, 16, 64, 32, 16);
			else if (type == 3) RenderHelper.renderBox(4, 6, 4, 0, 16, 64, 32, 16);
		}
		else if (mob == 6)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(slime);
			if (type == 0)
			{
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				RenderHelper.renderBox(8, 8, 8, 0, 0, 64, 32, 16);
			}
			else if (type == 1)
			{
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				RenderHelper.renderBox(6, 6, 6, 0, 16, 64, 32, 16);
			}
		}
		else if (mob == 7)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(magmacube);
			if (type == 0) RenderHelper.renderBox(8, 8, 8, 0, 0, 64, 32, 16);
			else if (type == 1) RenderHelper.renderBox(6, 6, 6, 0, 16, 64, 32, 16);
		}
		else if (mob == 8)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(spider);
			if (type == 0) RenderHelper.renderBox(8, 8, 8, 32, 4, 64, 32, 16);
			else if (type == 1)
			{
				GL11.glRotatef(90, 0, 1, 0);
				RenderHelper.renderBox(8, 12, 10, 4, 12, 64, 32, 16);
			}
			else if (type == 3) RenderHelper.renderBox(2, 2, 16, 18, 0, 64, 32, 16);
		}
		else if (mob == 9)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(cavespider);
			GL11.glScalef(0.666f, 0.666f, 0.666f);
			if (type == 0) RenderHelper.renderBox(8, 8, 8, 32, 4, 64, 32, 16);
			else if (type == 1)
			{
				GL11.glRotatef(90, 0, 1, 0);
				RenderHelper.renderBox(8, 12, 10, 4, 12, 64, 32, 16);
			}
			else if (type == 3) RenderHelper.renderBox(2, 2, 16, 18, 0, 64, 32, 16);
		}
		else if (mob == 10)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(ghast);
			if (type == 0) RenderHelper.renderBox(16, 16, 16, 0, 0, 64, 32, 4);
			else if (type == 3) RenderHelper.renderBox(2, 14, 2, 0, 0, 64, 32, 4);
		}
		else if (mob == 11)
		{
			if (size < 1) Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.btsplash5);
			else if (size < 2) Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.btsplash1);
			else Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.btsplash3);
			if (type == 0) RenderHelper.renderBox((int) (8 * size), (int) (8 * size), (int) (8 * size), 0, 0, 64, 64, 16);
			else if (type == 1) RenderHelper.renderBox((int) (4 * size), (int) (12 * size), (int) (8 * size), 0, 0, 64, 64, 16);
			else if (type == 2) RenderHelper.renderBox((int) (4 * size), (int) (12 * size), (int) (4 * size), 0, 0, 64, 64, 16);
			else if (type == 3) RenderHelper.renderBox((int) (4 * size), (int) (12 * size), (int) (4 * size), 0, 0, 64, 64, 16);
		}
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
