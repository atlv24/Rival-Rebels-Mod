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
import assets.rivalrebels.client.model.ModelAntimatterBombBlast;
import assets.rivalrebels.client.model.ModelBlastRing;
import assets.rivalrebels.client.model.ModelBlastSphere;
import assets.rivalrebels.client.model.ModelTsarBlast;
import assets.rivalrebels.common.entity.EntityAntimatterBombBlast;
import assets.rivalrebels.common.entity.EntityTsarBlast;

public class RenderAntimatterBombBlast extends Render
{
	private ModelBlastSphere modelsphere;
	private ModelBlastRing modelring;
	private ModelAntimatterBombBlast modelabomb;
	
	public RenderAntimatterBombBlast()
	{
		modelsphere = new ModelBlastSphere();
		modelabomb = new ModelAntimatterBombBlast();
		modelring = new ModelBlastRing();
	}
	
	@Override
	public void doRender(Entity var1, double x, double y, double z, float var8, float var9)
	{
		EntityAntimatterBombBlast tsar = (EntityAntimatterBombBlast) var1;
		tsar.time++;
		double radius = (((tsar.motionX * 10) - 1) * ((tsar.motionX * 10) - 1) * 2) + RivalRebels.tsarBombaStrength;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glScalef(RivalRebels.shroomScale,RivalRebels.shroomScale,RivalRebels.shroomScale);
		GL11.glColor3f(0.0f, 0.0f, 0.2f);
		float size = (tsar.time % 100) * 2.0f;
		modelring.renderModel(size, 64, 6f, 2f, 0f, 0f, 0f, (float)x, (float)y, (float)z);
		GL11.glPopMatrix();
		if (tsar.time < 60)
		{
			double elev = tsar.time / 5f;
			GL11.glTranslated(x, y + elev, z);
			modelsphere.renderModel(tsar.time, 1, 1, 1, 1);
		}
		else
		{
			//double elev = Math.sin(tsar.time * 0.1f) * 5.0f + 60.0f;
			//double noisy = 5.0f;
			//double hnoisy = noisy * 0.5f;
			GL11.glTranslated(x, y, z);
			GL11.glScaled(radius * 0.06f, radius * 0.06f, radius * 0.06f);
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etantimatterblast);
			modelabomb.render();
			/*modelsphere.renderModel(50.0f, 0.0f, 0.0f, 0.0f, 1.0f, false);
			GL11.glPushMatrix();
			//GL11.glTranslated(Math.random() * noisy - hnoisy, Math.random() * noisy - hnoisy, Math.random() * noisy - hnoisy);
			GL11.glRotatef((float) (elev * 2), 0, 1, 0);
			GL11.glRotatef((float) (elev * 3), 1, 0, 0);
			modelsphere.renderModel((float) elev, 0.2f, 0.6f, 1, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			//GL11.glTranslated(Math.random() * noisy - hnoisy, Math.random() * noisy - hnoisy, Math.random() * noisy - hnoisy);
			GL11.glRotatef((float) (elev * -2), 0, 1, 0);
			GL11.glRotatef((float) (elev * 4), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.2f), 0.6f, 0.2f, 1, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			//GL11.glTranslated(Math.random() * noisy - hnoisy, Math.random() * noisy - hnoisy, Math.random() * noisy - hnoisy);
			GL11.glRotatef((float) (elev * -3), 1, 0, 0);
			GL11.glRotatef((float) (elev * 2), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.4f), 0.4f, 0, 1, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			//GL11.glTranslated(Math.random() * noisy - hnoisy, Math.random() * noisy - hnoisy, Math.random() * noisy - hnoisy);
			GL11.glRotatef((float) (elev * -1), 0, 1, 0);
			GL11.glRotatef((float) (elev * 3), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.6f), 0, 0.4f, 1, 1);
			GL11.glPopMatrix();*/
			///summon rivalrebels.rivalrebelsentity51 ~ ~-2 ~ {charge:5}
		}
		GL11.glPopMatrix();
		if (RivalRebels.antimatterFlash)
		{
			int ran = (int) (Math.random() * 10f - 5f);
			for (int i = 0; i < ran; i++)
			{
				GL11.glPopMatrix();
			}
			for (int i = -5; i < 0; i++)
			{
				GL11.glPushMatrix();
			}
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			GL11.glScaled(Math.random(), Math.random(), Math.random());
			GL11.glRotated(Math.random() * 360.0f, Math.random(), Math.random(), Math.random());
			GL11.glTranslated(Math.random() * 10.0f - 5.0f, Math.random() * 10.0f - 5.0f, Math.random() * 10.0f - 5.0f);
			modelsphere.renderModel(tsar.time, (float)Math.random(), (float)Math.random(), (float)Math.random(), 1);
		}
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
