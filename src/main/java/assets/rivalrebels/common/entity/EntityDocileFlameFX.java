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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import assets.rivalrebels.RivalRebels;

public class EntityDocileFlameFX extends EntityFX
{
	public int	sequence;
	
	public EntityDocileFlameFX(World par1World, EntityPlayer entity)
	{
		super(par1World, entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
		setSize(0.1f, 0.1f);
		setLocationAndAngles(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
		rotationYaw = (rotationYaw + 25) % 360;
		posX -= MathHelper.cos((rotationYaw / 180F) * (float) Math.PI) * 0.16F;
		posY -= 0.2D;
		posZ -= MathHelper.sin((rotationYaw / 180F) * (float) Math.PI) * 0.16F;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = -MathHelper.sin((rotationYaw / 180F) * (float) Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float) Math.PI);
		motionZ = MathHelper.cos((rotationYaw / 180F) * (float) Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float) Math.PI);
		motionY = -MathHelper.sin((rotationPitch / 180F) * (float) Math.PI);
		setVelocity(motionX, motionY, motionZ);
	}
	
	@Override
	public void onUpdate()
	{
		prevPosX = lastTickPosX = posX;
		prevPosY = lastTickPosY = posY;
		prevPosZ = lastTickPosZ = posZ;
		
		if ((ticksExisted >= 8 && worldObj.rand.nextInt(2) == 1) || this.inWater) setDead();
		
		ticksExisted++;
		sequence++;
		
		float airFriction = 0.99F;
		motionX *= airFriction;
		motionY *= airFriction;
		motionZ *= airFriction;
		motionY += 0.05;
		
		moveEntity(motionX, motionY, motionZ);
		setPosition(posX, posY, posZ);
	}
	
	@Override
	public int getBrightnessForRender(float par1)
	{
		return 1000;
	}
	
	@Override
	public float getBrightness(float par1)
	{
		return 1000;
	}
	
	@Override
	public void renderParticle(Tessellator t, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etflameball);
		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL14.glBlendEquation(GL14.GL_FUNC_ADD);
		float f10 = 0.03F * particleScale;
		float f11 = (float) (prevPosX + (posX - prevPosX) * par2 - interpPosX);
		float f12 = (float) (prevPosY + (posY - prevPosY) * par2 - interpPosY);
		float f13 = (float) (prevPosZ + (posZ - prevPosZ) * par2 - interpPosZ);
		t.setColorRGBA_F(1, 1, 1, 1);
		float X = (sequence % 4) / 4f;
		float Y = (sequence - (sequence % 4)) / 16f;
		t.addVertexWithUV(f11 - par3 * f10 - par6 * f10, f12 - par4 * f10, f13 - par5 * f10 - par7 * f10, X + 0.25f, Y + 0.25f);
		t.addVertexWithUV(f11 - par3 * f10 + par6 * f10, f12 + par4 * f10, f13 - par5 * f10 + par7 * f10, X + 0.25f, Y);
		t.addVertexWithUV(f11 + par3 * f10 + par6 * f10, f12 + par4 * f10, f13 + par5 * f10 + par7 * f10, X, Y);
		t.addVertexWithUV(f11 + par3 * f10 - par6 * f10, f12 - par4 * f10, f13 + par5 * f10 - par7 * f10, X, Y + 0.25f);
	}
}
