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
package assets.rivalrebels.client.model;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTrollFlag
{
	public void renderModel(World world, double x, double y, double z, int metadata)
	{
		Tessellator var5 = Tessellator.instance;
		double var18 = 0.05;
		
		if (metadata == 5)
		{
			var5.startDrawingQuads();
			var5.addVertexWithUV(var18, 1, 1, 0, 0);
			var5.addVertexWithUV(var18, 0, 1, 0, 1);
			var5.addVertexWithUV(var18, 0, 0, 1, 1);
			var5.addVertexWithUV(var18, 1, 0, 1, 0);
			var5.draw();
			var5.startDrawingQuads();
			var5.addVertexWithUV(var18, 1, 0, 1, 0);
			var5.addVertexWithUV(var18, 0, 0, 1, 1);
			var5.addVertexWithUV(var18, 0, 1, 0, 1);
			var5.addVertexWithUV(var18, 1, 1, 0, 0);
			var5.draw();
		}
		
		if (metadata == 4)
		{
			var5.startDrawingQuads();
			var5.addVertexWithUV(1 - var18, 0, 1, 1, 1);
			var5.addVertexWithUV(1 - var18, 1, 1, 1, 0);
			var5.addVertexWithUV(1 - var18, 1, 0, 0, 0);
			var5.addVertexWithUV(1 - var18, 0, 0, 0, 1);
			var5.draw();
			var5.startDrawingQuads();
			var5.addVertexWithUV(1 - var18, 0, 0, 0, 1);
			var5.addVertexWithUV(1 - var18, 1, 0, 0, 0);
			var5.addVertexWithUV(1 - var18, 1, 1, 1, 0);
			var5.addVertexWithUV(1 - var18, 0, 1, 1, 1);
			var5.draw();
		}
		
		if (metadata == 3)
		{
			var5.startDrawingQuads();
			var5.addVertexWithUV(1, 0, var18, 1, 1);
			var5.addVertexWithUV(1, 1, var18, 1, 0);
			var5.addVertexWithUV(0, 1, var18, 0, 0);
			var5.addVertexWithUV(0, 0, var18, 0, 1);
			var5.draw();
			var5.startDrawingQuads();
			var5.addVertexWithUV(0, 0, var18, 0, 1);
			var5.addVertexWithUV(0, 1, var18, 0, 0);
			var5.addVertexWithUV(1, 1, var18, 1, 0);
			var5.addVertexWithUV(1, 0, var18, 1, 1);
			var5.draw();
		}
		
		if (metadata == 2)
		{
			var5.startDrawingQuads();
			var5.addVertexWithUV(1, 1, 1 - var18, 0, 0);
			var5.addVertexWithUV(1, 0, 1 - var18, 0, 1);
			var5.addVertexWithUV(0, 0, 1 - var18, 1, 1);
			var5.addVertexWithUV(0, 1, 1 - var18, 1, 0);
			var5.draw();
			var5.startDrawingQuads();
			var5.addVertexWithUV(0, 1, 1 - var18, 1, 0);
			var5.addVertexWithUV(0, 0, 1 - var18, 1, 1);
			var5.addVertexWithUV(1, 0, 1 - var18, 0, 1);
			var5.addVertexWithUV(1, 1, 1 - var18, 0, 0);
			var5.draw();
		}
	}
}
