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
import assets.rivalrebels.client.objfileloader.ModelFromObj;

public class BatteryRenderer implements IItemRenderer
{
	ModelFromObj	battery;
	
	public BatteryRenderer()
	{
		try
		{
			battery = ModelFromObj.readObjFile("k.obj");
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
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etbattery);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.8f, 0.3f, -0.03f);
		GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(0.3f, 0.3f, 0.3f);
		
		battery.render();
		
		GL11.glPopMatrix();
	}
}
