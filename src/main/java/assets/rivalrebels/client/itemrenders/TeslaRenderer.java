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
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;

public class TeslaRenderer implements IItemRenderer
{
	ModelFromObj	tesla;
	ModelFromObj	dynamo;
	int				spin	= 0;
	
	public TeslaRenderer()
	{
		try
		{
			tesla = ModelFromObj.readObjFile("i.obj");
			dynamo = ModelFromObj.readObjFile("j.obj");
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
	
	public int getDegree(ItemStack item)
	{
		if (item.stackTagCompound == null) return 0;
		else return item.stackTagCompound.getInteger("dial");
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if (!item.isItemEnchanted())
		{
			GL11.glEnable(GL11.GL_LIGHTING);
			int degree = getDegree(item);
			spin += 5 + (degree / 36f);
			Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.ettesla);
			GL11.glPushMatrix();
			GL11.glTranslatef(0.8f, 0.5f, -0.03f);
			GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(0.12f, 0.12f, 0.12f);
			// GL11.glTranslatef(0.3f, 0.05f, -0.1f);
			
			tesla.render();
			GL11.glRotatef(spin, 1.0F, 0.0F, 0.0F);
			dynamo.render();
			
			GL11.glPopMatrix();
		}
		else
		{
			if (type != ItemRenderType.ENTITY) GL11.glPopMatrix();
			GL11.glPushMatrix();
			Tessellator t = Tessellator.instance;
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glScalef(1.01f, 1.01f, 1.01f);
			GL11.glRotatef(45.0f, 0, 1, 0);
			GL11.glRotatef(10.0f, 0, 0, 1);
			GL11.glScalef(0.6f, 0.2f, 0.2f);
			GL11.glTranslatef(-0.99f, 0.5f, 0.0f);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_CULL_FACE);
			t.startDrawingQuads();
			t.addVertexWithUV(-1, -1, -1, 0, 0);
			t.addVertexWithUV(-1, 1, -1, 1, 0);
			t.addVertexWithUV(-1, 1, 1, 1, 1);
			t.addVertexWithUV(-1, -1, 1, 0, 1);
			t.addVertexWithUV(1, -1, -1, 0, 0);
			t.addVertexWithUV(1, -1, 1, 0, 1);
			t.addVertexWithUV(1, 1, 1, 1, 1);
			t.addVertexWithUV(1, 1, -1, 1, 0);
			t.addVertexWithUV(-1, -1, -1, 0, 0);
			t.addVertexWithUV(-1, -1, 1, 0, 1);
			t.addVertexWithUV(1, -1, 1, 3, 1);
			t.addVertexWithUV(1, -1, -1, 3, 0);
			t.addVertexWithUV(-1, 1, -1, 0, 0);
			t.addVertexWithUV(1, 1, -1, 3, 0);
			t.addVertexWithUV(1, 1, 1, 3, 1);
			t.addVertexWithUV(-1, 1, 1, 0, 1);
			t.addVertexWithUV(-1, -1, -1, 0, 0);
			t.addVertexWithUV(1, -1, -1, 3, 0);
			t.addVertexWithUV(1, 1, -1, 3, 1);
			t.addVertexWithUV(-1, 1, -1, 0, 1);
			t.addVertexWithUV(-1, -1, 1, 0, 0);
			t.addVertexWithUV(-1, 1, 1, 0, 1);
			t.addVertexWithUV(1, 1, 1, 3, 1);
			t.addVertexWithUV(1, -1, 1, 3, 0);
			t.addVertexWithUV(-1, -1, -1, 0, 0);
			t.addVertexWithUV(-1, 1, -1, 1, 0);
			t.addVertexWithUV(-1, 1, 1, 1, 1);
			t.addVertexWithUV(-1, -1, 1, 0, 1);
			t.addVertexWithUV(1, -1, -1, 0, 0);
			t.addVertexWithUV(1, -1, 1, 0, 1);
			t.addVertexWithUV(1, 1, 1, 1, 1);
			t.addVertexWithUV(1, 1, -1, 1, 0);
			t.addVertexWithUV(-1, -1, -1, 0, 0);
			t.addVertexWithUV(-1, -1, 1, 0, 1);
			t.addVertexWithUV(1, -1, 1, 3, 1);
			t.addVertexWithUV(1, -1, -1, 3, 0);
			t.addVertexWithUV(-1, 1, -1, 0, 0);
			t.addVertexWithUV(1, 1, -1, 3, 0);
			t.addVertexWithUV(1, 1, 1, 3, 1);
			t.addVertexWithUV(-1, 1, 1, 0, 1);
			t.addVertexWithUV(-1, -1, -1, 0, 0);
			t.addVertexWithUV(1, -1, -1, 3, 0);
			t.addVertexWithUV(1, 1, -1, 3, 1);
			t.addVertexWithUV(-1, 1, -1, 0, 1);
			t.addVertexWithUV(-1, -1, 1, 0, 0);
			t.addVertexWithUV(-1, 1, 1, 0, 1);
			t.addVertexWithUV(1, 1, 1, 3, 1);
			t.addVertexWithUV(1, -1, 1, 3, 0);
			t.draw();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glPopMatrix();
			if (type != ItemRenderType.ENTITY) GL11.glPushMatrix();
		}
	}
}
