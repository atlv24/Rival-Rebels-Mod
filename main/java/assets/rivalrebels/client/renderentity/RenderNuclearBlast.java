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
package assets.rivalrebels.client.renderentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelBlastRing;
import assets.rivalrebels.client.renderhelper.Vertice;
import assets.rivalrebels.common.entity.EntityNuclearBlast;

public class RenderNuclearBlast extends Render
{
	private float	ring1			= 0;
	private float	ring2			= 0;
	private float	ring3			= 0;
	private float	height			= 0;
	static ModelBlastRing model;
	
	private int		textureCoordx	= 0;
	private int		textureCoordy	= 0;
	
	public RenderNuclearBlast()
	{
		 model = new ModelBlastRing();
	}
	
	public void renderNuclearBlast(EntityNuclearBlast enb, double x, double y, double z, float yaw, float pitch)
	{
		GL11.glPushMatrix();
		if (enb.ticksExisted == 0)
		{
			ring1 = 0;
			ring2 = 0;
			ring3 = 0;
			height = 0;
			textureCoordx = enb.worldObj.rand.nextInt(64);
		}
		
		ring1 += 0.02;
		ring2 += Math.sin(ring1) * 0.01;
		ring3 -= Math.sin(ring1) * 0.01;
		
		if (ring2 < 6)
		{
			ring2 += 0.1;
		}
		
		if (ring3 < 8)
		{
			ring3 += 0.1;
		}
		
		if (height < 8)
		{
			height += 0.1;
		}
		
		if (enb.ticksExisted < 600)
		{
			model.renderModel(RivalRebels.shroomScale * ring1 * 15, 64, 4, 0.5f, 0, 0, 0, (float) x, (float) y - 3, (float) z);
			model.renderModel(RivalRebels.shroomScale * ring2, 32, 1, 0.5f, 0, 0, 0, (float) x, (float) y + height + ring3, (float) z);
			model.renderModel(RivalRebels.shroomScale * ring3, 32, 2, 0.5f, 0, 0, 0, (float) x, (float) y + height + 7 + ring2, (float) z);
			if (enb.ticksExisted > 550)
			{
				ring2 += 0.1;
				ring3 += 0.1;
			}
		}
		else
		{
			ring1 = 0;
			ring2 = 0;
			ring3 = 0;
			height = 0;
		}
		
		textureCoordy -= 1;
		if (textureCoordy <= 0)
		{
			textureCoordy = 128;
		}
		
		float par5 = (textureCoordx + 128) / 128.0F;
		float par6 = (textureCoordx) / 128.0F;
		float par7 = (textureCoordy + 128) / 128.0F;
		float par8 = (textureCoordy) / 128.0F;
		
		GL11.glPushMatrix();
		GL11.glTranslated(x, y - 10, z);
		GL11.glScalef(RivalRebels.shroomScale,RivalRebels.shroomScale,RivalRebels.shroomScale);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F + (float) enb.motionY * 0.3F, 2.6F + (float) enb.motionY * 0.3F, 0.5F + (float) enb.motionY * 0.3F);
		if (enb.motionX == 1)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.ettroll);
		}
		else
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etradiation);
		}
		
		int size = (int) (enb.motionY);
		
		Vertice pxv1 = new Vertice(4, 0 - size * 2.5F, 0);
		Vertice nxv1 = new Vertice(-4, 0 - size * 2.5F, 0);
		Vertice pzv1 = new Vertice(0, 0 - size * 2.5F, 4);
		Vertice nzv1 = new Vertice(0, 0 - size * 2.5F, -4);
		
		Vertice pxv2 = new Vertice(2F, 1.5F - size * 2F, 0);
		Vertice nxv2 = new Vertice(-2F, 1.5F - size * 2F, 0);
		Vertice pzv2 = new Vertice(0, 1.5F - size * 2F, 2F);
		Vertice nzv2 = new Vertice(0, 1.5F - size * 2F, -2F);
		
		Vertice pxv3 = new Vertice(1.5F, 3.5F - size * 1.5F, 0);
		Vertice nxv3 = new Vertice(-1.5F, 3.5F - size * 1.5F, 0);
		Vertice pzv3 = new Vertice(0, 3.5F - size * 1.5F, 1.5F);
		Vertice nzv3 = new Vertice(0, 3.5F - size * 1.5F, -1.5F);
		
		Vertice pxv4 = new Vertice(1.5F, 6.5F - size * 1F, 0);
		Vertice nxv4 = new Vertice(-1.5F, 6.5F - size * 1F, 0);
		Vertice pzv4 = new Vertice(0, 6.5F - size * 1F, 1.5F);
		Vertice nzv4 = new Vertice(0, 6.5F - size * 1F, -1.5F);
		
		Vertice pxv5 = new Vertice(2F, 9.5F - size * 0.5F, 0);
		Vertice nxv5 = new Vertice(-2F, 9.5F - size * 0.5F, 0);
		Vertice pzv5 = new Vertice(0, 9.5F - size * 0.5F, 2F);
		Vertice nzv5 = new Vertice(0, 9.5F - size * 0.5F, -2F);
		
		Vertice pxv6 = new Vertice(3F, 11F, 0);
		Vertice nxv6 = new Vertice(-3F, 11F, 0);
		Vertice pzv6 = new Vertice(0, 11F, 3F);
		Vertice nzv6 = new Vertice(0, 11F, -3F);
		
		Vertice pxv7 = new Vertice(16F, 10F, 0);
		Vertice nxv7 = new Vertice(-16F, 10F, 0);
		Vertice pzv7 = new Vertice(0, 10F, 16F);
		Vertice nzv7 = new Vertice(0, 10F, -16F);
		
		Vertice ppv7 = new Vertice(8F, 10F, 8F);
		Vertice npv7 = new Vertice(-8F, 10F, 8F);
		Vertice pnv7 = new Vertice(8F, 10F, -8F);
		Vertice nnv7 = new Vertice(-8F, 10F, -8F);
		
		Vertice pxv8 = new Vertice(32F, 12F, 0);
		Vertice nxv8 = new Vertice(-32F, 12F, 0);
		Vertice pzv8 = new Vertice(0, 12F, 32F);
		Vertice nzv8 = new Vertice(0, 12F, -32F);
		
		Vertice ppv8 = new Vertice(22.5F, 12F, 22.5F);
		Vertice npv8 = new Vertice(-22.5F, 12F, 22.5F);
		Vertice pnv8 = new Vertice(22.5F, 12F, -22.5F);
		Vertice nnv8 = new Vertice(-22.5F, 12F, -22.5F);
		
		Vertice pxv9 = new Vertice(16F, 13F, 0);
		Vertice nxv9 = new Vertice(-16F, 13F, 0);
		Vertice pzv9 = new Vertice(0, 13F, 16F);
		Vertice nzv9 = new Vertice(0, 13F, -16F);
		
		Vertice ppv9 = new Vertice(11.5F, 13F, 11.5F);
		Vertice npv9 = new Vertice(-11.5F, 13F, 11.5F);
		Vertice pnv9 = new Vertice(11.5F, 13F, -11.5F);
		Vertice nnv9 = new Vertice(-11.5F, 13F, -11.5F);
		
		Vertice v9 = new Vertice(0F, 13F, 0F);
		
		int time = size * 10;
		
		if (enb.ticksExisted > 0 && enb.ticksExisted < 600 + time)
		{
			addFace(pxv1, nzv1, nzv2, pxv2, par5, par6, par7, par8);
			addFace(pzv1, pxv1, pxv2, pzv2, par5, par6, par7, par8);
			addFace(nxv1, pzv1, pzv2, nxv2, par5, par6, par7, par8);
			addFace(nzv1, nxv1, nxv2, nzv2, par5, par6, par7, par8);
		}
		
		if (enb.ticksExisted > 10 && enb.ticksExisted < 610 + time)
		{
			addFace(pxv2, nzv2, nzv3, pxv3, par5, par6, par7, par8);
			addFace(pzv2, pxv2, pxv3, pzv3, par5, par6, par7, par8);
			addFace(nxv2, pzv2, pzv3, nxv3, par5, par6, par7, par8);
			addFace(nzv2, nxv2, nxv3, nzv3, par5, par6, par7, par8);
		}
		
		if (enb.ticksExisted > 20 && enb.ticksExisted < 620 + time)
		{
			addFace(pxv3, nzv3, nzv4, pxv4, par5, par6, par7, par8);
			addFace(pzv3, pxv3, pxv4, pzv4, par5, par6, par7, par8);
			addFace(nxv3, pzv3, pzv4, nxv4, par5, par6, par7, par8);
			addFace(nzv3, nxv3, nxv4, nzv4, par5, par6, par7, par8);
		}
		
		if (enb.ticksExisted > 30 && enb.ticksExisted < 630 + time)
		{
			addFace(pxv4, nzv4, nzv5, pxv5, par5, par6, par7, par8);
			addFace(pzv4, pxv4, pxv5, pzv5, par5, par6, par7, par8);
			addFace(nxv4, pzv4, pzv5, nxv5, par5, par6, par7, par8);
			addFace(nzv4, nxv4, nxv5, nzv5, par5, par6, par7, par8);
		}
		
		if (enb.ticksExisted > 40 && enb.ticksExisted < 640 + time)
		{
			addFace(pxv5, nzv5, nzv6, pxv6, par5, par6, par7, par8);
			addFace(pzv5, pxv5, pxv6, pzv6, par5, par6, par7, par8);
			addFace(nxv5, pzv5, pzv6, nxv6, par5, par6, par7, par8);
			addFace(nzv5, nxv5, nxv6, nzv6, par5, par6, par7, par8);
		}
		
		if (enb.ticksExisted > 30 && enb.ticksExisted < 650 + time)
		{
			addFace(pxv6, nzv6, nzv7, pxv7, par6, par5, par8, par7);
			addFace(pzv6, pxv6, pxv7, pzv7, par6, par5, par8, par7);
			addFace(nxv6, pzv6, pzv7, nxv7, par6, par5, par8, par7);
			addFace(nzv6, nxv6, nxv7, nzv7, par6, par5, par8, par7);
		}
		
		if (enb.ticksExisted > 20 && enb.ticksExisted < 650 + time)
		{
			addFace(pzv7, ppv7, ppv8, pzv8, par6, par5, par8, par7);
			addFace(ppv7, pxv7, pxv8, ppv8, par6, par5, par8, par7);
			addFace(pxv7, pnv7, pnv8, pxv8, par6, par5, par8, par7);
			addFace(pnv7, nzv7, nzv8, pnv8, par6, par5, par8, par7);
			addFace(nzv7, nnv7, nnv8, nzv8, par6, par5, par8, par7);
			addFace(nnv7, nxv7, nxv8, nnv8, par6, par5, par8, par7);
			addFace(nxv7, npv7, npv8, nxv8, par6, par5, par8, par7);
			addFace(npv7, pzv7, pzv8, npv8, par6, par5, par8, par7);
		}
		
		if (enb.ticksExisted > 10 && enb.ticksExisted < 650 + time)
		{
			addFace(pzv8, ppv8, ppv9, pzv9, par6, par5, par8, par7);
			addFace(ppv8, pxv8, pxv9, ppv9, par6, par5, par8, par7);
			addFace(pxv8, pnv8, pnv9, pxv9, par6, par5, par8, par7);
			addFace(pnv8, nzv8, nzv9, pnv9, par6, par5, par8, par7);
			addFace(nzv8, nnv8, nnv9, nzv9, par6, par5, par8, par7);
			addFace(nnv8, nxv8, nxv9, nnv9, par6, par5, par8, par7);
			addFace(nxv8, npv8, npv9, nxv9, par6, par5, par8, par7);
			addFace(npv8, pzv8, pzv9, npv9, par6, par5, par8, par7);
			
			addFace(pxv6, pzv6, nxv6, nzv6, par6, par5, par8, par7);
			
			addTri(ppv9, v9, pzv9, par6, par5, par8, par7);
			addTri(pxv9, v9, ppv9, par6, par5, par8, par7);
			addTri(pnv9, v9, pxv9, par6, par5, par8, par7);
			addTri(nzv9, v9, pnv9, par6, par5, par8, par7);
			addTri(nnv9, v9, nzv9, par6, par5, par8, par7);
			addTri(nxv9, v9, nnv9, par6, par5, par8, par7);
			addTri(npv9, v9, nxv9, par6, par5, par8, par7);
			addTri(pzv9, v9, npv9, par6, par5, par8, par7);
			
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRender(Entity entityNuclearBlast, double x, double y, double z, float yaw, float pitch)
	{
		this.renderNuclearBlast((EntityNuclearBlast) entityNuclearBlast, x, y, z, yaw, pitch);
	}
	
	private void addFace(Vertice v1, Vertice v2, Vertice v3, Vertice v4, float par5, float par6, float par7, float par8)
	{
		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		addVertice(v1, par5, par8);
		addVertice(v2, par6, par8);
		addVertice(v3, par6, par7);
		addVertice(v4, par5, par7);
		t.draw();
	}
	
	private void addTri(Vertice v1, Vertice v2, Vertice v3, float par5, float par6, float par7, float par8)
	{
		Tessellator t = Tessellator.instance;
		t.startDrawing(6);
		addVertice(v3, par5, par8);
		addVertice(v1, par6, par8);
		addVertice(v2, par6, par7);
		t.draw();
	}
	
	private void addVertice(Vertice v, double t, double t2)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.addVertexWithUV(v.x, v.y, v.z, t, t2);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}