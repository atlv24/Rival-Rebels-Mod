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
package assets.rivalrebels.client.tileentityrender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelFast3D;
import assets.rivalrebels.client.model.ModelLaptop;
import assets.rivalrebels.client.model.ModelReactor;
import assets.rivalrebels.client.model.RenderLibrary;
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.common.tileentity.TileEntityMachineBase;
import assets.rivalrebels.common.tileentity.TileEntityReactor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityReactorRenderer extends TileEntitySpecialRenderer
{
	private ModelReactor	mr;
	private ModelLaptop		ml;
	private ModelFromObj	mo;
	
	public TileEntityReactorRenderer()
	{
		mr = new ModelReactor();
		ml = new ModelLaptop();
		try
		{
			mo = ModelFromObj.readObjFile("a.obj");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void renderAModelAt(TileEntityReactor tile, double d, double d1, double d2, float f)
	{
		GL11.glEnable(GL11.GL_LIGHTING);
		int var9 = tile.getBlockMetadata();
		short var11 = 0;
		if (var9 == 2)
		{
			var11 = 180;
		}
		
		if (var9 == 3)
		{
			var11 = 0;
		}
		
		if (var9 == 4)
		{
			var11 = -90;
		}
		
		if (var9 == 5)
		{
			var11 = 90;
		}
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.1875F, (float) d2 + 0.5F);
		GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etlaptop);
		ml.renderModel((float) -tile.slide);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etscreen);
		ml.renderScreen((float) -tile.slide);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.5F, (float) d2 + 0.5F);
		GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etreactor);
		mr.renderModel();
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etelectrode);
		GL11.glTranslatef(0, 2, -0.125f);
		GL11.glScalef(0.2f, 0.2f, 0.2f);
		mo.render();
		GL11.glPopMatrix();
		for (int i = 0; i < tile.machines.getSize(); i++)
		{
			TileEntityMachineBase temb = (TileEntityMachineBase) tile.machines.get(i);
			if (temb.powerGiven > 0)
			{
				float radius = (temb.powerGiven * temb.powerGiven) / 40000;
				radius += 0.03;
				int steps = 2;
				if (radius > 0.05) steps++;
				if (radius > 0.10) steps++;
				if (radius > 0.15) steps++;
				if (radius > 0.25) radius = 0.25f;
				// if (steps == 2 && temb.worldObj.rand.nextInt(5) != 0) return;
				RenderLibrary.instance.renderModel((float) d + 0.5f, (float) d1 + 2.5f, (float) d2 + 0.5f, temb.xCoord - tile.xCoord, temb.yCoord - tile.yCoord - 2.5f, temb.zCoord - tile.zCoord, 0.5f, radius, steps, (temb.edist / 2), 0.1f, 0.45f, 0.45f, 0.5f, 0.5f);
			}
		}
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		renderAModelAt((TileEntityReactor) tileentity, d, d1, d2, f);
	}
}