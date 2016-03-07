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
import assets.rivalrebels.client.model.ModelLoader;
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.common.tileentity.TileEntityLoader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityLoaderRenderer extends TileEntitySpecialRenderer
{
	private ModelLoader		loaderModel;
	private ModelFromObj	tube;
	
	public TileEntityLoaderRenderer()
	{
		loaderModel = new ModelLoader();
		try
		{
			tube = ModelFromObj.readObjFile("l.obj");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Renders the TileEntity for the chest at a position.
	 */
	public void renderLoaderAt(TileEntityLoader tile, double par2, double par4, double par6, float par8)
	{
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2 + 0.5F, (float) par4 + 0.5F, (float) par6 + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etloader);
		int var9 = tile.getBlockMetadata();
		short var11 = 0;
		if (var9 == 2)
		{
			var11 = 90;
		}
		
		if (var9 == 3)
		{
			var11 = -90;
		}
		
		if (var9 == 4)
		{
			var11 = 180;
		}
		
		if (var9 == 5)
		{
			var11 = 0;
		}
		
		GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
		loaderModel.renderA();
		loaderModel.renderB((float) tile.slide);
		GL11.glPopMatrix();
		for (int i = 0; i < tile.machines.getSize(); i++)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) par2 + 0.5F, (float) par4 + 0.5F, (float) par6 + 0.5F);
			int xdif = tile.machines.get(i).xCoord - tile.xCoord;
			int zdif = tile.machines.get(i).zCoord - tile.zCoord;
			GL11.glRotated(-90 + (Math.atan2(xdif, zdif) / Math.PI) * 180, 0, 1, 0);
			GL11.glTranslatef(-1f, -0.40f, 0);
			Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.ettube);
			GL11.glScaled(0.5, 0.15, 0.15);
			int dist = (int) Math.sqrt((xdif * xdif) + (zdif * zdif));
			for (int d = 0; d < dist; d++)
			{
				GL11.glTranslatef(2, 0, 0);
				tube.render();
			}
			GL11.glPopMatrix();
		}
	}
	
	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderLoaderAt((TileEntityLoader) par1TileEntity, par2, par4, par6, par8);
	}
}
