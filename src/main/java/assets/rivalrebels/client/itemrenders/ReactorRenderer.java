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

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelLaptop;
import assets.rivalrebels.client.model.ModelReactor;

public class ReactorRenderer implements IItemRenderer
{
	ModelReactor	mr;
	ModelLaptop		ml;
	
	public ReactorRenderer()
	{
		mr = new ModelReactor();
		ml = new ModelLaptop();
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
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 1.1875F, 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etlaptop);
		ml.renderModel(0);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etscreen);
		ml.renderScreen(0);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etreactor);
		mr.renderModel();
		GL11.glPopMatrix();
	}
}
