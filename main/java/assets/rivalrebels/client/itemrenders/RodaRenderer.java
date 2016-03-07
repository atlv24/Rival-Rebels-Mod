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
package assets.rivalrebels.client.itemrenders;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;
import assets.rivalrebels.common.noise.RivalRebelsCellularNoise;

public class RodaRenderer implements IItemRenderer
{
	ModelFromObj	model;
	//ModelFromObj	model2;
	
	public RodaRenderer()
	{
		try
		{
			model = ModelFromObj.readObjFile("e.obj");
			//model2 = model.copy().pushNormal();
		}
		catch (Exception e)
		{
			System.err.println("Please make sure the model files are in the correct directory.");
		}
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		if (type == ItemRenderType.FIRST_PERSON_MAP || type == ItemRenderType.EQUIPPED || type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED_FIRST_PERSON) return true;
		return false;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glEnable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etrust);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5f, 0.5f, -0.03f);
		GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(0.35f, 0.35f, 0.35f);
		if (type != ItemRenderType.EQUIPPED_FIRST_PERSON) GL11.glScalef(-1, 1, 1);
		GL11.glTranslatef(0.2f, -0.55f, 0.1f);
		
		model.render();
		GL11.glPushMatrix();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glDisable(GL11.GL_LIGHTING);
		model.render();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();
	}
}
