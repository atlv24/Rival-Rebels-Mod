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
import assets.rivalrebels.client.model.ModelBlastRing;
import assets.rivalrebels.client.model.ModelBlastSphere;
import assets.rivalrebels.client.model.ModelTsarBlast;
import assets.rivalrebels.common.entity.EntityTachyonBombBlast;
import assets.rivalrebels.common.entity.EntityTsarBlast;

public class RenderTachyonBombBlast extends Render
{
	private ModelTsarBlast		model;
	private ModelBlastSphere	modelsphere;
	
	public RenderTachyonBombBlast()
	{
		model = new ModelTsarBlast();
		modelsphere = new ModelBlastSphere();
	}
	
	@Override
	public void doRender(Entity var1, double x, double y, double z, float var8, float var9)
	{
		EntityTachyonBombBlast tsar = (EntityTachyonBombBlast) var1;
		tsar.time++;
		double radius = (((tsar.motionX * 10) - 1) * ((tsar.motionX * 10) - 1) * 2) + RivalRebels.tsarBombaStrength;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		if (tsar.time < 60)
		{
			double elev = tsar.time / 5f;
			GL11.glTranslated(x, y + elev, z);
			modelsphere.renderModel(tsar.time * RivalRebels.shroomScale, 1, 1, 1, 1);
		}
		else if (tsar.time < 600 && radius - RivalRebels.tsarBombaStrength > 9)
		{
			double elev = (tsar.time - 60f) / 32f + 10.0f;
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			GL11.glScalef(RivalRebels.shroomScale*2.0f,RivalRebels.shroomScale,RivalRebels.shroomScale*2.0f);
			GL11.glRotatef((float) (elev * 2), 0, 1, 0);
			GL11.glRotatef((float) (elev * 3), 1, 0, 0);
			modelsphere.renderModel((float) elev, 1, 0.25f, 0, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			GL11.glScalef(RivalRebels.shroomScale*2.0f,RivalRebels.shroomScale,RivalRebels.shroomScale*2.0f);
			GL11.glRotatef((float) (elev * -2), 0, 1, 0);
			GL11.glRotatef((float) (elev * 4), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.2f), 1, 0.5f, 0, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glTranslated(x, y + elev * 4, z);
			GL11.glScalef(RivalRebels.shroomScale,RivalRebels.shroomScale*3.0f,RivalRebels.shroomScale);
			GL11.glRotatef((float) (elev * -3), 1, 0, 0);
			GL11.glRotatef((float) (elev * 2), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.4f), 1, 0, 0, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glTranslated(x, y + elev * 4, z);
			GL11.glScalef(RivalRebels.shroomScale,RivalRebels.shroomScale*3.0f,RivalRebels.shroomScale);
			GL11.glRotatef((float) (elev * -1), 0, 1, 0);
			GL11.glRotatef((float) (elev * 3), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.6f), 1, 1, 0, 1);
			GL11.glPopMatrix();
		}
		else
		{
			float elev = (tsar.time - (radius - RivalRebels.tsarBombaStrength > 9 ? 600f : 0f)) / 8f;
			RenderNuclearBlast.model.renderModel(RivalRebels.shroomScale * (elev) * 1.0f, 32, 2, 0.5f, 0, 0, 0, (float) x, (float) (y +  2.0f), (float) z);
			RenderNuclearBlast.model.renderModel(RivalRebels.shroomScale * (elev) * 1.1f, 32, 2, 0.5f, 0, 0, 0, (float) x, (float) (y +  6.0f), (float) z);
			RenderNuclearBlast.model.renderModel(RivalRebels.shroomScale * (elev) * 1.2f, 32, 2, 0.5f, 0, 0, 0, (float) x, (float) (y + 10.0f), (float) z);
			RenderNuclearBlast.model.renderModel(RivalRebels.shroomScale * (elev) * 1.3f, 32, 2, 0.5f, 0, 0, 0, (float) x, (float) (y + 14.0f), (float) z);
			RenderNuclearBlast.model.renderModel(RivalRebels.shroomScale * (elev) * 1.4f, 32, 2, 0.5f, 0, 0, 0, (float) x, (float) (y + 18.0f), (float) z);
			RenderNuclearBlast.model.renderModel(RivalRebels.shroomScale * (elev) * 1.5f, 32, 2, 0.5f, 0, 0, 0, (float) x, (float) (y + 22.0f), (float) z);
			RenderNuclearBlast.model.renderModel(RivalRebels.shroomScale * (elev) * 1.6f, 32, 2, 0.5f, 0, 0, 0, (float) x, (float) (y + 26.0f), (float) z);
			RenderNuclearBlast.model.renderModel(RivalRebels.shroomScale * (elev) * 1.7f, 32, 2, 0.5f, 0, 0, 0, (float) x, (float) (y + 30.0f), (float) z);
			Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.ettsarflame);
			GL11.glTranslated(x, y + 10 + ((tsar.motionX - 0.1d) * 14.14213562), z);
			GL11.glScalef(RivalRebels.shroomScale,RivalRebels.shroomScale,RivalRebels.shroomScale);
			float horizontal = elev * 0.025f + 1.0f;
			GL11.glScalef((float) (horizontal * radius * 0.116f), (float) (radius * 0.065f), (float) (horizontal * radius * 0.116f));
			GL11.glScalef(0.8f, 0.8f, 0.8f);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			model.render();
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
