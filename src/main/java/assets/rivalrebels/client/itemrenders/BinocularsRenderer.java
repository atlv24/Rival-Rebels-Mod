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
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.objfileloader.ModelFromObj;

public class BinocularsRenderer implements IItemRenderer
{
	ModelFromObj	model;
	
	public BinocularsRenderer()
	{
		try
		{
			model = ModelFromObj.readObjFile("b.obj");
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
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etbinoculars);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5f, 0.5f, -0.03f);
		GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(0.35f, 0.35f, 0.35f);
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON && (Mouse.isButtonDown(1)))
		{
			GL11.glPopMatrix();
			return;
		}
		GL11.glTranslatef(0.6f, 0.05f, 0.3f);
		
		if (model.name.equals("")) Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§43D ERROR:§f Cannot find RivalRebels.zip or RivalRebels folder inside mods folder. Please make sure the RivalRebels mod file is named RivalRebels.zip, or visit §2www.rivalrebels.com §ffor support."));
		model.render();
		
		GL11.glPopMatrix();
	}
}
