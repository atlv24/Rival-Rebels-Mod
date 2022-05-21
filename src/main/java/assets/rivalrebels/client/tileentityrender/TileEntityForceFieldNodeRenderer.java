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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.renderhelper.RenderHelper;
import assets.rivalrebels.common.noise.RivalRebelsCellularNoise;
import assets.rivalrebels.common.tileentity.TileEntityForceFieldNode;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityForceFieldNodeRenderer extends TileEntitySpecialRenderer
{
	public static int				frames	= 28;
	public static int[]			id		= new int[frames];
	RenderHelper	model;
	
	public TileEntityForceFieldNodeRenderer()
	{
		model = new RenderHelper();
		id = genTexture(28, 28, frames);
	}
	
	int	count	= 0;
	
	public void renderAModelAt(TileEntityForceFieldNode tile, double x, double y, double z, float f)
	{
		if (tile.pInR <= 0 || !RivalRebels.goodRender) return;
		
		count++;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		
		if (tile.getBlockMetadata() == 2)
		{
			GL11.glRotatef(90, 0, 1, 0);
		}
		
		if (tile.getBlockMetadata() == 3)
		{
			GL11.glRotatef(-90, 0, 1, 0);
		}
		
		if (tile.getBlockMetadata() == 4)
		{
			GL11.glRotatef(180, 0, 1, 0);
		}
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id[(int) ((getTime() / 100) % frames)]);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0, 0, 0.5f);
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		
		tess.addVertexWithUV(-0.0625f, 3.5f, 0f, 0, 0);
		tess.addVertexWithUV(-0.0625f, -3.5f, 0f, 0, 1);
		tess.addVertexWithUV(-0.0625f, -3.5f, 35f, 5, 1);
		tess.addVertexWithUV(-0.0625f, 3.5f, 35f, 5, 0);
		tess.draw();
		
		tess.startDrawingQuads();
		tess.addVertexWithUV(0.0625f, -3.5f, 0f, 0, 1);
		tess.addVertexWithUV(0.0625f, 3.5f, 0f, 0, 0);
		tess.addVertexWithUV(0.0625f, 3.5f, 35f, 5, 0);
		tess.addVertexWithUV(0.0625f, -3.5f, 35f, 5, 1);
		tess.draw();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && RivalRebels.optiFineWarn)
		{
			FMLNetworkHandler.openGui(Minecraft.getMinecraft().thePlayer, RivalRebels.instance, 24, tile.getWorldObj(), 0, 0, 0);
			// Minecraft.getMinecraft().thePlayer.openGui(RivalRebels.instance, 24, tile.getWorldObj(), 0, 0, 0);
			RivalRebels.optiFineWarn = false;
		}
	}
	
	private int[] genTexture(int xs, int zs, int ys)
	{
		int[] ids = new int[ys];
		RivalRebelsCellularNoise.refresh3D();
		int size = xs * zs * 4;
		byte red = (byte) 0xBB;
		byte grn = (byte) 0x88;
		byte blu = (byte) 0xFF;
		for (int i = 0; i < ys; i++)
		{
			ByteBuffer bb = BufferUtils.createByteBuffer(size);
			bb.order(ByteOrder.nativeOrder());
			for (int x = 0; x < xs; x++)
			{
				for (int z = 0; z < zs; z++)
				{
					bb.put(red);
					bb.put(grn);
					bb.put(blu);
					bb.put((byte) ((RivalRebelsCellularNoise.noise((double) x / (double) xs, (double) z / (double) zs, (double) i / (double) ys) + 1) * 127));
				}
			}
			bb.flip();
			int id = GL11.glGenTextures();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, xs, zs, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, bb);
			ids[i] = id;
		}
		return ids;
	}
	
	protected float lerp(float f1, float f2, float f3)
	{
		return f1 * f3 + f2 * (1 - f3);
	}
	
	public static long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		renderAModelAt((TileEntityForceFieldNode) tileentity, d, d1, d2, f);
	}
}