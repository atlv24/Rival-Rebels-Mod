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
import assets.rivalrebels.client.model.ModelObjective;
import assets.rivalrebels.common.tileentity.TileEntityOmegaObjective;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityOmegaObjectiveRenderer extends TileEntitySpecialRenderer
{
	private ModelObjective	loaderModel;
	
	public TileEntityOmegaObjectiveRenderer()
	{
		loaderModel = new ModelObjective();
	}
	
	/**
	 * Renders the TileEntity for the chest at a position.
	 */
	public void renderLoaderAt(TileEntityOmegaObjective par1TileEntityLoader, double par2, double par4, double par6, float par8)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2 + 0.5F, (float) par4 + 0.5F, (float) par6 + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etomegaobj);
		
		GL11.glRotatef(90, 1, 0, 0);
		loaderModel.renderA();
		GL11.glRotatef(-90, 1, 0, 0);
		GL11.glRotatef(90, 0, 0, 1);
		loaderModel.renderB((float) par1TileEntityLoader.slide, 96f / 256f, 44f / 128f, 0.125f, 0.84375f);
		GL11.glRotatef(-90, 0, 0, 1);
		loaderModel.renderB((float) par1TileEntityLoader.slide, 32f / 256f, 44f / 128f, 0.625f, 0.84375f);
		GL11.glRotatef(90, 0, 1, 0);
		loaderModel.renderB((float) par1TileEntityLoader.slide, 96f / 256f, 108f / 128f, 0.625f, 0.84375f);
		GL11.glRotatef(90, 0, 1, 0);
		loaderModel.renderB((float) par1TileEntityLoader.slide, 160f / 256f, 44f / 128f, 0.625f, 0.84375f);
		GL11.glRotatef(90, 0, 1, 0);
		loaderModel.renderB((float) par1TileEntityLoader.slide, 224f / 256f, 108f / 128f, 0.625f, 0.84375f);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glRotatef(-90, 0, 0, 1);
		loaderModel.renderB((float) par1TileEntityLoader.slide, 224f / 256f, 44f / 128f, 0.625f, 0.84375f);
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderLoaderAt((TileEntityOmegaObjective) par1TileEntity, par2, par4, par6, par8);
	}
}
