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
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelBlastSphere;
import assets.rivalrebels.client.model.ModelTsarBlast;
import assets.rivalrebels.common.entity.EntitySphereBlast;
import assets.rivalrebels.common.entity.EntityTsarBlast;

public class RenderSphereBlast extends Render
{
	private ModelBlastSphere	modelsphere;
	
	public RenderSphereBlast()
	{
		modelsphere = new ModelBlastSphere();
	}
	
	@Override
	public void doRender(Entity var1, double x, double y, double z, float var8, float var9)
	{
		EntitySphereBlast tsar = (EntitySphereBlast) var1;
		tsar.time++;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		double elev = ((MathHelper.sin(tsar.time/40f)+1.5f)*10);
		GL11.glTranslated(x, y + elev, z);
		GL11.glPushMatrix();
		GL11.glRotatef((float) (elev * 2), 0, 1, 0);
		GL11.glRotatef((float) (elev * 3), 1, 0, 0);
		modelsphere.renderModel((float) elev, 1, 0.25f, 0, 1f);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef((float) (elev * -2), 0, 1, 0);
		GL11.glRotatef((float) (elev * 4), 0, 0, 1);
		modelsphere.renderModel((float) (elev - 0.2f), 1, 0.5f, 0, 1f);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef((float) (elev * -3), 1, 0, 0);
		GL11.glRotatef((float) (elev * 2), 0, 0, 1);
		modelsphere.renderModel((float) (elev - 0.4f), 1, 0, 0, 1f);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef((float) (elev * -1), 0, 1, 0);
		GL11.glRotatef((float) (elev * 3), 0, 0, 1);
		modelsphere.renderModel((float) (elev - 0.6f), 1, 1, 0, 1);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
