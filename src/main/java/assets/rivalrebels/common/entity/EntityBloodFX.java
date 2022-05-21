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
package assets.rivalrebels.common.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityBloodFX extends EntityFX
{
	boolean	isBlood;
	
	public EntityBloodFX(World w, double x, double y, double z, boolean b)
	{
		this(w, x, y, z, w.rand.nextGaussian() * 0.1, w.rand.nextGaussian() * 0.1, w.rand.nextGaussian() * 0.1, b);
	}
	
	public EntityBloodFX(World w, double x, double y, double z, double r, double g, double b, boolean bl)
	{
		super(w, x, y, z, r, g, b);
		
		posX = x;
		posY = y;
		posZ = z;
		motionX = r;
		motionY = g;
		motionZ = b;
		particleGravity = 0.75F;
		particleMaxAge = 20;
		isBlood = bl;
	}
	
	public EntityBloodFX(World w, EntityGore g, boolean b)
	{
		this(w, g.posX, g.posY, g.posZ, b);
	}
	
	@Override
	public void renderParticle(Tessellator t, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(isBlood ? RivalRebels.etblood : RivalRebels.etgoo);
		float f10 = 0.1F * this.particleScale;
		
		float f11 = (float) (prevPosX + (posX - prevPosX) * par2 - interpPosX);
		float f12 = (float) (prevPosY + (posY - prevPosY) * par2 - interpPosY);
		float f13 = (float) (prevPosZ + (posZ - prevPosZ) * par2 - interpPosZ);
		t.setColorRGBA_F(1, 1, 1, 1);
		t.addVertexWithUV(f11 - par3 * f10 - par6 * f10, f12 - par4 * f10, f13 - par5 * f10 - par7 * f10, 1, 1);
		t.addVertexWithUV(f11 - par3 * f10 + par6 * f10, f12 + par4 * f10, f13 - par5 * f10 + par7 * f10, 1, 0);
		t.addVertexWithUV(f11 + par3 * f10 + par6 * f10, f12 + par4 * f10, f13 + par5 * f10 + par7 * f10, 0, 0);
		t.addVertexWithUV(f11 + par3 * f10 - par6 * f10, f12 - par4 * f10, f13 + par5 * f10 - par7 * f10, 0, 1);
	}
}
