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

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelAstroBlasterBack;
import assets.rivalrebels.client.model.ModelAstroBlasterBarrel;
import assets.rivalrebels.client.model.ModelAstroBlasterBody;
import assets.rivalrebels.client.model.ModelAstroBlasterHandle;
import assets.rivalrebels.client.model.ModelRod;
import assets.rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;

public class AstroBlasterRenderer implements IItemRenderer
{
	ModelAstroBlasterBarrel	md1;
	ModelAstroBlasterHandle	md2;
	ModelAstroBlasterBody	md3;
	ModelAstroBlasterBack	md4;
	ModelRod				md5;
	float					pullback		= 0;
	float					rotation		= 0;
	boolean					isreloading		= false;
	int						stage			= 0;
	int						spin			= 0;
	int						time			= 1;
	int						reloadcooldown	= 0;
	
	public AstroBlasterRenderer()
	{
		md1 = new ModelAstroBlasterBarrel();
		md2 = new ModelAstroBlasterHandle();
		md3 = new ModelAstroBlasterBody();
		md4 = new ModelAstroBlasterBack();
		md5 = new ModelRod();
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
		spin++;
		if (item.getRepairCost() >= 1)
		{
			spin += item.getRepairCost() / 2.2;
		}
		spin %= 628;
		if (reloadcooldown > 0) reloadcooldown--;
		if (item.getRepairCost() > 20 && reloadcooldown == 0) isreloading = true;
		if (isreloading)
		{
			if (stage == 0) if (pullback < 0.3) pullback += 0.03;
			else stage = 1;
			if (stage == 1) if (rotation < 90) rotation += 4.5;
			else stage = 2;
			if (stage == 2) if (pullback > 0) pullback -= 0.03;
			else
			{
				stage = 0;
				isreloading = false;
				reloadcooldown = 60;
				rotation = 0;
			}
			
		}
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef(0.4f, 0.35f, -0.03f);
		GL11.glRotatef(-55, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0f, -0.05f, 0.05f);
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, 0.9f, 0f);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.eteinstenbarrel);
		md1.render();
		if (item.getEnchantmentTagList() != null && item.getEnchantmentTagList().tagCount() > 0)
		{
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
			md1.render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0.22f, -0.025f, 0f);
		GL11.glRotatef(90, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(0.03125f, 0.03125f, 0.03125f);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.eteinstenhandle);
		md2.render();
		if (item.getEnchantmentTagList() != null && item.getEnchantmentTagList().tagCount() > 0)
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
		
		// GL11.glPushMatrix();
		// GL11.glTranslatef(0f, 0.8f, 0f);
		// GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
		// GL11.glScaled(0.9, 4.5, 0.9);
		// md3.render(0.2f, 0.3f, 0.3f, 0.3f, 1f);
		// GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, 0.2f, 0f);
		GL11.glScaled(0.85, 0.85, 0.85);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.eteinstenback);
		md4.render();
		if (item.getEnchantmentTagList() != null && item.getEnchantmentTagList().tagCount() > 0)
		{
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
			md4.render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, -pullback, 0f);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.12f, 0.1f, 0.12f);
		GL11.glRotatef(pullback * 270, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(0.3f, 0.7f, 0.3f);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etredrod);
		md5.render();
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.12f, 0.1f, 0.12f);
		GL11.glRotatef(pullback * 270, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(0.3f, 0.7f, 0.3f);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etredrod);
		md5.render();
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.12f, 0.1f, -0.12f);
		GL11.glRotatef(pullback * 270, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(0.3f, 0.7f, 0.3f);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etredrod);
		md5.render();
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0.12f, 0.1f, -0.12f);
		GL11.glRotatef(pullback * 270, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(0.3f, 0.7f, 0.3f);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etredrod);
		md5.render();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glTranslatef(0, 0.25f, 0);
		float segmentDistance = 0.1f;
		float distance = 0.5f;
		float radius = 0.01F;
		Random rand = new Random();
		Tessellator tessellator = Tessellator.instance;
		
		double AddedX = 0;
		double AddedZ = 0;
		double prevAddedX = 0;
		double prevAddedZ = 0;
		// double angle = 0;
		for (float AddedY = distance; AddedY >= 0; AddedY -= segmentDistance)
		{
			prevAddedX = AddedX;
			prevAddedZ = AddedZ;
			AddedX = (rand.nextFloat() - 0.5) * 0.1f;
			AddedZ = (rand.nextFloat() - 0.5) * 0.1f;
			double dist = Math.sqrt(AddedX * AddedX + AddedZ * AddedZ);
			if (dist != 0)
			{
				double tempAddedX = AddedX / dist;
				double tempAddedZ = AddedZ / dist;
				if (Math.abs(tempAddedX) < Math.abs(AddedX))
				{
					AddedX = tempAddedX;
				}
				if (Math.abs(tempAddedZ) < Math.abs(AddedZ))
				{
					AddedZ = tempAddedZ;
				}
				// angle = Math.atan2(tempAddedX, tempAddedZ);
			}
			if (AddedY <= 0)
			{
				AddedX = AddedZ = 0;
			}
			
			for (float o = 0; o <= radius; o += radius / 2f)
			{
				tessellator.startDrawingQuads();
				tessellator.setColorRGBA_F(1, 0, 0, 1);
				tessellator.addVertex(AddedX + o, AddedY, AddedZ - o);
				tessellator.addVertex(AddedX + o, AddedY, AddedZ + o);
				tessellator.addVertex(prevAddedX + o, AddedY + segmentDistance, prevAddedZ + o);
				tessellator.addVertex(prevAddedX + o, AddedY + segmentDistance, prevAddedZ - o);
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setColorRGBA_F(1, 0, 0, 1);
				tessellator.addVertex(AddedX - o, AddedY, AddedZ - o);
				tessellator.addVertex(AddedX + o, AddedY, AddedZ - o);
				tessellator.addVertex(prevAddedX + o, AddedY + segmentDistance, prevAddedZ - o);
				tessellator.addVertex(prevAddedX - o, AddedY + segmentDistance, prevAddedZ - o);
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setColorRGBA_F(1, 0, 0, 1);
				tessellator.addVertex(AddedX - o, AddedY, AddedZ + o);
				tessellator.addVertex(AddedX - o, AddedY, AddedZ - o);
				tessellator.addVertex(prevAddedX - o, AddedY + segmentDistance, prevAddedZ - o);
				tessellator.addVertex(prevAddedX - o, AddedY + segmentDistance, prevAddedZ + o);
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setColorRGBA_F(1, 0, 0, 1);
				tessellator.addVertex(AddedX + o, AddedY, AddedZ + o);
				tessellator.addVertex(AddedX - o, AddedY, AddedZ + o);
				tessellator.addVertex(prevAddedX - o, AddedY + segmentDistance, prevAddedZ + o);
				tessellator.addVertex(prevAddedX + o, AddedY + segmentDistance, prevAddedZ + o);
				tessellator.draw();
			}
			// GL11.glPushMatrix();
			// GL11.glRotatef(90f, 0.0F, 0.0F, 1.0F);
			// GL11.glRotatef((float) angle, 0.0F, 1.0F, 0.0F);
			// float o = 0.075f;
			// float s = 0.1f;
			// tessellator.startDrawingQuads();
			// tessellator.setColorRGBA_F(1, 0, 0, 1);
			// tessellator.addVertex( + o, AddedY, - o);
			// tessellator.addVertex( + o, AddedY, + o);
			// tessellator.addVertex( + o, AddedY + s, + o);
			// tessellator.addVertex( + o, AddedY + s, - o);
			// tessellator.draw();
			// tessellator.startDrawingQuads();
			// tessellator.setColorRGBA_F(1, 0, 0, 1);
			// tessellator.addVertex( - o, AddedY, - o);
			// tessellator.addVertex( + o, AddedY, - o);
			// tessellator.addVertex( + o, AddedY + s, - o);
			// tessellator.addVertex( - o, AddedY + s, - o);
			// tessellator.draw();
			// tessellator.startDrawingQuads();
			// tessellator.setColorRGBA_F(1, 0, 0, 1);
			// tessellator.addVertex( - o, AddedY, + o);
			// tessellator.addVertex( - o, AddedY, - o);
			// tessellator.addVertex( - o, AddedY + s, - o);
			// tessellator.addVertex( - o, AddedY + s, + o);
			// tessellator.draw();
			// tessellator.startDrawingQuads();
			// tessellator.setColorRGBA_F(1, 0, 0, 1);
			// tessellator.addVertex( + o, AddedY, + o);
			// tessellator.addVertex( - o, AddedY, + o);
			// tessellator.addVertex( - o, AddedY + s, + o);
			// tessellator.addVertex( + o, AddedY + s, + o);
			// tessellator.draw();
			// GL11.glPopMatrix();
		}
		
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, 0.8f, 0f);
		GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(spin, 0.0F, 1.0F, 0.0F);
		GL11.glScaled(0.9, 4.1, 0.9);
		md3.render((float) (0.22f + (Math.sin(spin / 10) * 0.005)), 0.5f, 0f, 0f, 1f);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, 0.8f, 0f);
		GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(-spin, 0.0F, 1.0F, 0.0F);
		GL11.glScaled(0.9, 4.1, 0.9);
		md3.render((float) (0.22f + (Math.cos(-spin / 15) * 0.005)), 0.5f, 0f, 0f, 1f);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();
	}
}
