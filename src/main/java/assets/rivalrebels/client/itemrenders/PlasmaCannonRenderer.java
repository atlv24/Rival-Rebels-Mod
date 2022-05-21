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
import assets.rivalrebels.client.model.ModelRod;
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;

public class PlasmaCannonRenderer implements IItemRenderer
{
	// ModelPlasmaCannon md;
	ModelRod		md2;
	ModelRod		md3;
	
	ModelFromObj	model;
	
	public PlasmaCannonRenderer()
	{
		// md = new ModelPlasmaCannon();
		md2 = new ModelRod();
		md2.rendersecondcap = false;
		md3 = new ModelRod();
		try
		{
			model = ModelFromObj.readObjFile("m.obj");
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
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.1f, 0f, 0f);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etplasmacannon);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5f, 0.2f, -0.03f);
		GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(0.03125f, 0.03125f, 0.03125f);
		GL11.glPushMatrix();
		
		model.render();
		if (item.getEnchantmentTagList() != null)
		{
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
			model.render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
		}
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.ethydrod);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5f, 0.2f, -0.03f);
		GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
		GL11.glPushMatrix();
		GL11.glRotatef(225, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-0.5f, 0.5f, 0.0f);
		GL11.glScalef(0.25f, 0.5f, 0.25f);
		md2.render();
		if (item.getEnchantmentTagList() != null)
		{
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
			md2.render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5f, 0.2f, -0.03f);
		GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
		GL11.glPushMatrix();
		GL11.glRotatef(247.5f, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-0.175f, 0.1f, 0.0f);
		GL11.glScalef(0.25f, 0.5f, 0.25f);
		md3.render();
		if (item.getEnchantmentTagList() != null)
		{
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
			md3.render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
