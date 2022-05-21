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
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelNuclearBomb;
import assets.rivalrebels.client.model.ModelTsarBomba;
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.common.entity.EntityAntimatterBomb;
import assets.rivalrebels.common.entity.EntityB83;
import assets.rivalrebels.common.entity.EntityNuke;
import assets.rivalrebels.common.entity.EntityTsar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAntimatterBomb extends Render
{
    public static IModelCustom bomb;
	
	public RenderAntimatterBomb()
	{
    	bomb = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/t.obj"));
	}
	
	public void renderB83(EntityAntimatterBomb b83, double x, double y, double z, float par8, float par9)
	{
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glScalef(RivalRebels.nukeScale,RivalRebels.nukeScale,RivalRebels.nukeScale);
		GL11.glRotatef(b83.rotationYaw - 90.0f, 0.0F, 1.0F, 0.0F);
		//GL11.glRotatef(90.0f, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(b83.rotationPitch, 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etantimatterbomb);
    	bomb.renderAll();
		GL11.glPopMatrix();
	}
	
	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then handing it off to a worker function which does the actual work. In all
	 * probabilty, the class Render is generic (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1, double d2, float f, float f1). But JAD is pre
	 * 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		renderB83((EntityAntimatterBomb) par1Entity, par2, par4, par6, par8, par9);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}