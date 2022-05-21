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
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelBlastSphere;
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;
import assets.rivalrebels.common.entity.EntityB2Spirit;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRhodes extends Render
{
    public static ResourceLocation texture;
    public static ResourceLocation flametex;
    public static IModelCustom head;
    public static IModelCustom torso;
    public static IModelCustom flag;
    public static IModelCustom upperarm;
    public static IModelCustom lowerarm;
    public static IModelCustom flamethrower;
    public static IModelCustom rocketlauncher;
    public static IModelCustom thigh;
    public static IModelCustom shin;
    private static IModelCustom booster;
    private static IModelCustom flame;
    private static IModelCustom laser;
    public static IModelCustom ffhead;
    public static IModelCustom fftorso;
    public static IModelCustom ffupperarm;
    public static IModelCustom fflowerarm;
    public static IModelCustom ffthigh;
    public static IModelCustom ffshin;
	private ModelBlastSphere modelsphere;
	public static ModelFromObj	md;
	public static ModelFromObj	b2jet;
	public static String[] texfolders = {
			"blocks/",
			"entity/",
			"items/",
			"flags/",
	};
    
	public RenderRhodes()
	{
    	texture = new ResourceLocation(RivalRebels.MODID, "textures/entity/rhodes.png");
    	flametex = new ResourceLocation(RivalRebels.MODID, "textures/entity/flame.png");
    	head = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/head.obj"));
    	torso = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/torso.obj"));
    	flag = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/flag.obj"));
    	upperarm = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/upperarm.obj"));
    	lowerarm = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/lowerarm.obj"));
    	flamethrower = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/flamethrower.obj"));
    	rocketlauncher = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/rocketlauncher.obj"));
    	thigh = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/thigh.obj"));
    	shin = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/shin.obj"));
    	flame = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/flame.obj"));
    	laser = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/laser.obj"));
    	booster = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/booster.obj"));
    	
    	ffhead = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/ffhead.obj"));
    	fftorso = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/fftorso.obj"));
    	ffupperarm = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/ffupperarm.obj"));
    	fflowerarm = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/fflowerarm.obj"));
    	ffthigh = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/ffthigh.obj"));
    	ffshin = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.MODID, "models/rhodes/ffshin.obj"));
    	
		modelsphere = new ModelBlastSphere();
		try
		{
			md = ModelFromObj.readObjFile("d.obj");
			b2jet = ModelFromObj.readObjFile("s.obj");
			md.scale(2.5f, 2.5f, 2.5f);
			b2jet.scale(2.5f, 2.5f, 2.5f);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*public static float atan2(float y, float x)
	{
		return atan(y/((float)Math.sqrt(x*x+y*y)+x));
		if (x>=0)
		{
			if (y>=0)
			{
				if (x>y) return atan(y/x);
				else return 90-atan(x/y);
			}
			else return 90-atan(y/x);
		}
		else
		{
			if (y>=0) return 90-atan(y/x);
			else
			{
				if (x>y) return 90-atan(y/x);
				else return atan(x/y);
			}
		}
	}
	
	public static float atan(float x)
	{
		return atan(y/((float)Math.sqrt(x*x+y*y)+x));
		if (x > 1)
		{
			float r = 1/x;
			return 180-(110.8653352702f-20.8654f*r*r)*r;
		}
		else if (x > -1)
		{
			return (110.8653352702f-20.8654f*x*x)*x;
		}
		else
		{
			float r = 1/x;
			return -180-(110.8653352702f-20.8654f*r*r)*r;
		}
	}*/
	
	public static float[] colors = {
		255/255f,     255/255f,     255/255f, //1
		125/255f,     142/255f,     180/255f, //2
		146/255f,      68/255f,      68/255f, //3
		102/255f,     102/255f,      96/255f, //4
		217/255f,     202/255f,     119/255f, //5
		176/255f,     127/255f,     250/255f, //6
		153/255f,     137/255f,      89/255f, //7
		253/255f,     178/255f,     142/255f, //8
		114/255f,     187/255f,     255/255f, //9
		251/255f,     209/255f,      97/255f, //10
		137/255f,     160/255f,     143/255f, //11
		230/255f,     150/255f,     250/255f, //12
		129/255f,     123/255f,     163/255f, //13
		211/255f,     235/255f,     113/255f, //14
		145/255f,     163/255f,     175/255f, //15
		 34/255f,      31/255f,      31/255f, //16
		255/255f,     255/255f,     255/255f, //17
	};
	
	public void renderRhodes(EntityRhodes rhodes, double x, double y, double z, float par8, float tt)
	{
		if (rhodes.health > 0)
		{
			float ptt = Math.min((rhodes.ticksSinceLastPacket + tt)/5f, 1);
			if (rhodes.ticksExisted<10) ptt = 1;
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glScalef(rhodes.scale, rhodes.scale, rhodes.scale);
			
			FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
            float f = 5F;
            float f1 = 0.016666668F * f;
            GL11.glPushMatrix();
            GL11.glTranslatef(0, 16, 0);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-f1, -f1, f1);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            Tessellator tessellator = Tessellator.instance;
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            tessellator.startDrawingQuads();
            String name = rhodes.getName();
            int color = -1;
            if (rhodes.rider != null)
            {
            	name += " - " + rhodes.rider.getDisplayName();
            	RivalRebelsPlayer rrp = RivalRebels.instance.round.rrplayerlist.getForName(rhodes.rider.getCommandSenderName());
            	if (rrp!=null)
            	{
            		switch (rrp.rrteam)
            		{
            		case OMEGA:
            			color = 0x44FF44;
            		break;
            		case SIGMA:
            			color = 0x4444FF;
            		break;
            		case NONE:
            			color = -1;
            		break;
            		}
            	}
            }
            int j = fontrenderer.getStringWidth(name) / 2;
            tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
            tessellator.addVertex((double)(-j - 1), -1, 0.0D);
            tessellator.addVertex((double)(-j - 1), 8, 0.0D);
            tessellator.addVertex((double)(j + 1), 8, 0.0D);
            tessellator.addVertex((double)(j + 1), -1, 0.0D);
            tessellator.draw();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            fontrenderer.drawString(name, -fontrenderer.getStringWidth(name) / 2, 0, color);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(true);
            fontrenderer.drawString(name, -fontrenderer.getStringWidth(name) / 2, 0, color);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
			
			
			if (rhodes.colorType == 16)
			{
				GL11.glPushMatrix();
				GL11.glRotatef(rhodes.getbodyyaw(ptt), 0, 1, 0);
				GL11.glTranslatef(0, 10f, 0);
				Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etbooster);
		    	booster.renderAll();
				Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etb2spirit);
				GL11.glPushMatrix();
				GL11.glRotatef(-90f, 1, 0, 0);
				GL11.glTranslatef(0, 4, -2);
				GL11.glScalef(2.2f, 2.2f, 2.2f);
				if (rhodes.b2energy > 0) RenderB2Spirit.shuttle.render();
				GL11.glPopMatrix();
				GL11.glPopMatrix();
			}
			else {
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glRotatef(rhodes.getbodyyaw(ptt), 0, 1, 0);
		
				float leftlegheight = 7.26756f - 15
						+ (MathHelper.cos((rhodes.getleftthighpitch(ptt)+11.99684962f)*0.01745329252f) * 7.331691240f)
						+ (MathHelper.cos((rhodes.getleftthighpitch(ptt)+rhodes.getleftshinpitch(ptt)-12.2153067f)*0.01745329252f) * 8.521366426f);
				float rightlegheight = 7.26756f - 15
						+ (MathHelper.cos((rhodes.getrightthighpitch(ptt)+11.99684962f)*0.01745329252f) * 7.331691240f)
						+ (MathHelper.cos((rhodes.getrightthighpitch(ptt)+rhodes.getrightshinpitch(ptt)-12.2153067f)*0.01745329252f) * 8.521366426f);
			
				//TORSO
				GL11.glPushMatrix();
				GL11.glColor3f(colors[rhodes.colorType*3], colors[rhodes.colorType*3+1], colors[rhodes.colorType*3+2]);
				GL11.glTranslatef(0, (leftlegheight > rightlegheight) ? leftlegheight : rightlegheight, 0);

				Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etb2spirit);
				GL11.glPushMatrix();
				GL11.glRotatef(-90f, 1, 0, 0);
				GL11.glTranslatef(0, 4, -2);
				if (rhodes.b2energy > 0) md.render();
	    		GL11.glEnable(GL11.GL_BLEND);
	    		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
	    		Minecraft.getMinecraft().renderEngine.bindTexture(flametex);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
				if (rhodes.jet && rhodes.b2energy > 0) b2jet.render();
	    		GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				
		    	torso.renderAll();

		    	
					//RIGHT UPPERARM
					GL11.glPushMatrix();
					GL11.glTranslatef(-6.4f, 0, 0);
					GL11.glRotatef(rhodes.getrightarmyaw(ptt), 0, 1, 0);
					GL11.glScalef(-1, 1, 1);
			    	upperarm.renderAll();
				    	
				    	//RIGHT LOWERARM
						GL11.glPushMatrix();
						GL11.glTranslatef(0, -1.5f, 0);
						GL11.glRotatef(rhodes.getrightarmpitch(ptt), 1, 0, 0);
				    	lowerarm.renderAll();
						GL11.glScalef(-1, 1, 1);
				    	flamethrower.renderAll();
						GL11.glPopMatrix();
				    	
					GL11.glPopMatrix();
					
					//LEFT UPPERARM
					GL11.glPushMatrix();
					GL11.glTranslatef(6.4f, 0, 0);
					GL11.glRotatef(rhodes.getleftarmyaw(ptt), 0, 1, 0);
			    	upperarm.renderAll();
		
				    	//LEFT LOWERARM
						GL11.glPushMatrix();
						GL11.glTranslatef(0, -1.5f, 0);
						GL11.glRotatef(rhodes.getleftarmpitch(ptt), 1, 0, 0);
				    	lowerarm.renderAll();
				    	rocketlauncher.renderAll();
						GL11.glPopMatrix();
				    	
					GL11.glPopMatrix();
					
					//RIGHT THIGH
					GL11.glPushMatrix();
					GL11.glTranslatef(0, -7.26756f, -0.27904f);
					GL11.glRotatef(rhodes.getrightthighpitch(ptt), 1, 0, 0);
					GL11.glScalef(-1, 1, 1);
			    	thigh.renderAll();
			    	
				    	//RIGHT SHIN
						GL11.glPushMatrix();
						GL11.glTranslatef(0, -7.17156f, -1.52395f);
						GL11.glRotatef(rhodes.getrightshinpitch(ptt), 1, 0, 0);
				    	shin.renderAll();
				    	if (rhodes.fire)
				    	{
				    		GL11.glEnable(GL11.GL_BLEND);
				    		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
				    		Minecraft.getMinecraft().renderEngine.bindTexture(flametex);
							GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
							GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
					    	flame.renderAll();
				    		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				    		GL11.glDisable(GL11.GL_BLEND);
				    	}
						GL11.glPopMatrix();
			    	
					GL11.glPopMatrix();
					
					//LEFT THIGH
					GL11.glPushMatrix();
					GL11.glTranslatef(0, -7.26756f, -0.27904f);
					GL11.glRotatef(rhodes.getleftthighpitch(ptt), 1, 0, 0);
			    	thigh.renderAll();
			    	
				    	//LEFT SHIN
						GL11.glPushMatrix();
						GL11.glTranslatef(0, -7.17156f, -1.52395f);
						GL11.glRotatef(rhodes.getleftshinpitch(ptt), 1, 0, 0);
				    	shin.renderAll();
				    	if (rhodes.fire)
				    	{
				    		GL11.glEnable(GL11.GL_BLEND);
				    		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
				    		Minecraft.getMinecraft().renderEngine.bindTexture(flametex);
					    	flame.renderAll();
				    		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
				    		GL11.glDisable(GL11.GL_BLEND);
				    	}
						GL11.glPopMatrix();
					
					GL11.glPopMatrix();
					
					//HEAD
					GL11.glPushMatrix();
					GL11.glTranslatef(0, 5.23244f, 0);
					GL11.glRotatef(rhodes.getheadpitch(ptt), 1, 0, 0);
					GL11.glRotatef(rhodes.getheadyaw(ptt), 0, 1, 0);
			    	head.renderAll();
					GL11.glEnable(GL11.GL_CULL_FACE);
		    		GL11.glEnable(GL11.GL_BLEND);
		    		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
		    		GL11.glDisable(GL11.GL_TEXTURE_2D);
		    		GL11.glColor4f(1f, 0f, 0f, 0.5f);
			    	if ((rhodes.laserOn & 1) == 1)
			    	{
			    		laser.renderAll();
			    	}
			    	if ((rhodes.laserOn & 2) == 2)
			    	{
						GL11.glScalef(1, -1, 1);
						GL11.glCullFace(GL11.GL_FRONT);
			    		laser.renderAll();
						GL11.glCullFace(GL11.GL_BACK);
			    	}
		    		GL11.glEnable(GL11.GL_TEXTURE_2D);
			    	GL11.glDisable(GL11.GL_CULL_FACE);
		    		GL11.glDisable(GL11.GL_BLEND);
					GL11.glPopMatrix();
					
				GL11.glPopMatrix();
				//TORSO
				GL11.glPushMatrix();
					GL11.glColor3f(1,1,1);
					GL11.glTranslatef(0, (leftlegheight > rightlegheight) ? leftlegheight : rightlegheight, 0);
					if (rhodes.itexfolder != -1)
					{
						try
						{
							Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(RivalRebels.MODID, "textures/" + texfolders[rhodes.itexfolder] + rhodes.itexloc + ".png"));
					    	flag.renderAll();
						}
						catch (Exception e)
						{
							
						}
					}
			    	if (rhodes.forcefield)
			    	{
				    	GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
				    	GL11.glEnable(GL11.GL_BLEND);
						GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
						GL11.glDisable(GL11.GL_LIGHTING);
			    		fftorso.renderAll();
						//RIGHT UPPERARM
						GL11.glPushMatrix();
						GL11.glTranslatef(-6.4f, 0, 0);
						GL11.glRotatef(rhodes.getrightarmyaw(ptt), 0, 1, 0);
						GL11.glScalef(-1, 1, 1);
				    	ffupperarm.renderAll();
					    	//RIGHT LOWERARM
							GL11.glPushMatrix();
							GL11.glTranslatef(0, -1.5f, 0);
							GL11.glRotatef(rhodes.getrightarmpitch(ptt), 1, 0, 0);
					    	fflowerarm.renderAll();
							GL11.glPopMatrix();
						GL11.glPopMatrix();
						//LEFT UPPERARM
						GL11.glPushMatrix();
						GL11.glTranslatef(6.4f, 0, 0);
						GL11.glRotatef(rhodes.getleftarmyaw(ptt), 0, 1, 0);
				    	ffupperarm.renderAll();
					    	//LEFT LOWERARM
							GL11.glPushMatrix();
							GL11.glTranslatef(0, -1.5f, 0);
							GL11.glRotatef(rhodes.getleftarmpitch(ptt), 1, 0, 0);
					    	fflowerarm.renderAll();
							GL11.glPopMatrix();
						GL11.glPopMatrix();
						//RIGHT THIGH
						GL11.glPushMatrix();
						GL11.glTranslatef(0, -7.26756f, -0.27904f);
						GL11.glRotatef(rhodes.getrightthighpitch(ptt), 1, 0, 0);
						GL11.glScalef(-1, 1, 1);
				    	ffthigh.renderAll();
					    	//RIGHT SHIN
							GL11.glPushMatrix();
							GL11.glTranslatef(0, -7.17156f, -1.52395f);
							GL11.glRotatef(rhodes.getrightshinpitch(ptt), 1, 0, 0);
					    	ffshin.renderAll();
							GL11.glPopMatrix();
						GL11.glPopMatrix();
						//LEFT THIGH
						GL11.glPushMatrix();
						GL11.glTranslatef(0, -7.26756f, -0.27904f);
						GL11.glRotatef(rhodes.getleftthighpitch(ptt), 1, 0, 0);
				    	ffthigh.renderAll();
					    	//LEFT SHIN
							GL11.glPushMatrix();
							GL11.glTranslatef(0, -7.17156f, -1.52395f);
							GL11.glRotatef(rhodes.getleftshinpitch(ptt), 1, 0, 0);
					    	ffshin.renderAll();
							GL11.glPopMatrix();
						GL11.glPopMatrix();
						//HEAD
						GL11.glPushMatrix();
						GL11.glTranslatef(0, 5.23244f, 0);
						GL11.glRotatef(rhodes.getheadpitch(ptt), 1, 0, 0);
						GL11.glRotatef(rhodes.getheadyaw(ptt), 0, 1, 0);
				    	ffhead.renderAll();
						GL11.glPopMatrix();
						GL11.glDisable(GL11.GL_BLEND);
						GL11.glEnable(GL11.GL_LIGHTING);
			    	}
				GL11.glPopMatrix();
			}
	    	GL11.glPopMatrix();
		}
		if (rhodes.health < 1)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glDisable(GL11.GL_CULL_FACE);
    		GL11.glEnable(GL11.GL_BLEND);
    		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
    		GL11.glDisable(GL11.GL_TEXTURE_2D);
			double elev = Math.sin((rhodes.health-tt)*-0.0314159265359)*15;
			GL11.glPushMatrix();
			GL11.glRotatef((float) (elev * 2), 0, 1, 0);
			GL11.glRotatef((float) (elev * 3), 1, 0, 0);
			modelsphere.renderModel((float) elev, 1, 0.25f, 0, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef((float) (elev * -2), 0, 1, 0);
			GL11.glRotatef((float) (elev * 4), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.2f), 1, 0.5f, 0, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef((float) (elev * -3), 1, 0, 0);
			GL11.glRotatef((float) (elev * 2), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.4f), 1, 0, 0, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef((float) (elev * -1), 0, 1, 0);
			GL11.glRotatef((float) (elev * 3), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.6f), 1, 1, 0, 1);
			GL11.glPopMatrix();
    		GL11.glEnable(GL11.GL_TEXTURE_2D);
    		GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
	}
	
	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then handing it off to a worker function which does the actual work. In all
	 * probabilty, the class Render is generic (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1, double d2, float f, float f1). But JAD is pre
	 * 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		renderRhodes((EntityRhodes) par1Entity, par2, par4, par6, par8, par9);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}