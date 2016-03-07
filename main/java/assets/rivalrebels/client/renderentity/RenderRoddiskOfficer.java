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
import org.lwjgl.opengl.GL12;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelDisk;
import assets.rivalrebels.common.entity.EntityRoddiskOfficer;

public class RenderRoddiskOfficer extends Render
{
	int					er	= 0;
	private ModelDisk	model;
	
	public RenderRoddiskOfficer()
	{
		model = new ModelDisk();
	}
	
	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
	{
		er += 13.46;
		EntityRoddiskOfficer erd = (EntityRoddiskOfficer) var1;
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etdisk2);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) var2, (float) var4, (float) var6);
		GL11.glRotatef(erd.rotationPitch, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(erd.rotationYaw - 90.0f + er, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(0.4f, 0.4f, 0.4f);
		GL11.glPushMatrix();
		
		model.render();
		
		GL11.glPopMatrix();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
