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
package assets.rivalrebels.client.gui;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import scala.swing.event.Key;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.renderentity.RenderRhodes;
import assets.rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.item.weapon.ItemBinoculars;
import assets.rivalrebels.common.item.weapon.ItemCamera;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RivalRebelsRenderOverlay
{
	public int		tic	= 0;
	public boolean	r	= false;
	public EntityRhodes rhodes = null;
	public long counter = 0;
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void eventHandler(RenderGameOverlayEvent event)
	{
		if (rhodes != null)
		{
			renderRhodes(event, Minecraft.getMinecraft().thePlayer, rhodes);
		}
		renderItems(event);
	}
	private void renderItems(RenderGameOverlayEvent event)
	{
		if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			ItemStack item = player.inventory.getCurrentItem();
			if (item == null) return;
			if (item.getItem() instanceof ItemBinoculars) renderBinoculars(item, event, player); 
		}
	}

	float beep = 0.0f;
	
	private void renderRhodes(RenderGameOverlayEvent event, EntityPlayer player, EntityRhodes rhodes)
	{
		if (event.type == ElementType.HOTBAR)
		{
			counter--;
			if (counter <= 0)
			{
				counter = 0;
				RivalRebels.rrro.rhodes = null;
			}
			/*for (int i = 0; i < 65536; i++)
			{
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, i);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			}*/
			Tessellator t = Tessellator.instance;
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glEnable(GL11.GL_BLEND);
			FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
			int w = event.resolution.getScaledWidth();
			int h = event.resolution.getScaledHeight();
			
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Minecraft.getMinecraft().getTextureManager().bindTexture(RivalRebels.guirhodesline);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
			t.startDrawingQuads();
			t.addVertexWithUV(0, h, -90, 0, 1);
			t.addVertexWithUV(w, h, -90, 1, 1);
			t.addVertexWithUV(w, 0, -90, 1, 0);
			t.addVertexWithUV(0, 0, -90, 0, 0);
			t.draw();
			
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Minecraft.getMinecraft().getTextureManager().bindTexture(RivalRebels.guirhodesout);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.333f);
			t.startDrawingQuads();
			t.addVertexWithUV(0, h, -90, 0, 1);
			t.addVertexWithUV(w, h, -90, 1, 1);
			t.addVertexWithUV(w, 0, -90, 1, 0);
			t.addVertexWithUV(0, 0, -90, 0, 0);
			t.draw();
			
			if (Keyboard.isKeyDown(Keyboard.KEY_H))
			{
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				Minecraft.getMinecraft().getTextureManager().bindTexture(RivalRebels.guirhodeshelp);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				t.startDrawingQuads();
				t.addVertexWithUV(w*0.25f, h*0.75f, -90, 0, 1);
				t.addVertexWithUV(w*0.75f, h*0.75f, -90, 1, 1);
				t.addVertexWithUV(w*0.75f, h*0.25f, -90, 1, 0);
				t.addVertexWithUV(w*0.25f, h*0.25f, -90, 0, 0);
				t.draw();
			}
			
			if (rhodes.itexfolder > -1 && rhodes.itexfolder < 4)
			{
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(RivalRebels.MODID, "textures/" + RenderRhodes.texfolders[rhodes.itexfolder] + rhodes.itexloc + ".png"));
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0f);
				t.startDrawingQuads();
				float s = 8;
				float wl = w*0.5f;
				float hl = h*0.05f;
				t.addVertexWithUV(wl-s, hl+s, -90, 0, 1);
				t.addVertexWithUV(wl+s, hl+s, -90, 1, 1);
				t.addVertexWithUV(wl+s, hl-s, -90, 1, 0);
				t.addVertexWithUV(wl-s, hl-s, -90, 0, 0);
				t.draw();
			}
			GL11.glDisable(GL11.GL_BLEND);

			String disp = "Rival Rebels";
			fr.drawString(disp, (int) (w * 0.05), (int) (h * 0.05), 0xffffff, false);
			disp = "Robot: " + rhodes.getName();
			fr.drawString(disp, (int) (w * 0.05), (int) (h * 0.1), 0xffffff, false);
			disp = RivalRebels.reactor.getLocalizedName() + ": " + rhodes.health;
			float val = (rhodes.health / (float) RivalRebels.rhodesHealth);
			fr.drawString(disp, (int) (w * 0.05), (int) (h * 0.15), (((int)((1-val)*255)&255)<<16) | (((int)(val*255)&255)<<8), false);
			float yaw = (player.rotationYaw + 360000) % 360;
			disp = (yaw >= 315 || yaw < 45) ? StatCollector.translateToLocal("RivalRebels.binoculars.south") : (yaw >= 45 && yaw < 135) ? StatCollector.translateToLocal("RivalRebels.binoculars.west") : (yaw >= 135 && yaw < 225) ? StatCollector.translateToLocal("RivalRebels.binoculars.north") : (yaw >= 225 && yaw < 315) ? StatCollector.translateToLocal("RivalRebels.binoculars.east") : "Whut";
			fr.drawString(disp, (int) (w * 0.05), (int) (h * 0.2), 0xffffff, false);
			
			disp = StatCollector.translateToLocal(RivalRebels.einsten.getUnlocalizedName()+".name") + ": " + rhodes.energy;
			fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.05), (rhodes.laserOn>0)?0xff3333:0xffffff, false);
			disp = "Jet: " + rhodes.energy;
			fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.1), RivalRebels.proxy.spacebar()?0x6666ff:0xffffff, false);
			disp = RivalRebels.forcefieldnode.getLocalizedName() + ": " + rhodes.energy;
			fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.15), rhodes.forcefield?0xBB88FF:0xffffff, false);
			disp = StatCollector.translateToLocal(RivalRebels.seekm202.getUnlocalizedName()+".name") + ": " + rhodes.rocketcount;
			fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.2), 0xffffff, false);
			disp = (rhodes.plasma?"Plasma: " : (StatCollector.translateToLocal(RivalRebels.fuel.getUnlocalizedName()+".name") + ": ")) + rhodes.flamecount;
			fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.25), 0xffffff, false);
			disp = RivalRebels.nuclearBomb.getLocalizedName()+": " + rhodes.nukecount;
			fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.3), 0xffffff, false);
			disp = "Guard";
			fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.35), Keyboard.isKeyDown(Keyboard.KEY_G) ? 0xffff00 : 0xffffff, false);
			disp = rhodes.getName() + " " + RivalRebels.controller.getLocalizedName() + ": H";
			fr.drawString(disp, (int) (w * 0.05), (int) (h * 0.95), Keyboard.isKeyDown(Keyboard.KEY_H) ? 0xffff00 : 0xffffff, false);
			if (rhodes.forcefield)
			{
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
		    	GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.7f);
				t.startDrawingQuads();
				t.addVertexWithUV(0, h, -90, 0, h*0.003f);
				t.addVertexWithUV(w, h, -90, w*0.003f, h*0.003f);
				t.addVertexWithUV(w, 0, -90, w*0.003f, 0);
				t.addVertexWithUV(0, 0, -90, 0, 0);
				t.draw();
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_LIGHTING);
			}
		}
	}
	
	private void renderBinoculars(ItemStack item, RenderGameOverlayEvent event, EntityPlayer player)
	{
		if (Mouse.isButtonDown(1))
		{
			if (event.isCancelable()) event.setCanceled(true);
			if (event.type == ElementType.HOTBAR)
			{
				Tessellator t = Tessellator.instance;
				tic++;
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(false);
				Minecraft.getMinecraft().getTextureManager().bindTexture(RivalRebels.guibinoculars);
				FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
				int w = event.resolution.getScaledWidth();
				int h = event.resolution.getScaledHeight();
				
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				t.startDrawingQuads();
				t.addVertexWithUV(0, h, -90, 0, 1);
				t.addVertexWithUV(w, h, -90, 1, 1);
				t.addVertexWithUV(w, 0, -90, 1, 0);
				t.addVertexWithUV(0, 0, -90, 0, 0);
				t.draw();
				
				Minecraft.getMinecraft().getTextureManager().bindTexture(RivalRebels.guibinocularsoverlay);
				GL11.glColor4f(0.333F, 0.333F, 0.333F, 0.5F);
				t.startDrawingQuads();
				t.addVertexWithUV(0, h, -90, 0, 1);
				t.addVertexWithUV(w, h, -90, 1, 1);
				t.addVertexWithUV(w, 0, -90, 1, 0);
				t.addVertexWithUV(0, 0, -90, 0, 0);
				t.draw();
				
				// GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				// GL11.glDisable(GL11.GL_BLEND);
				// GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				// t.startDrawing(GL11.GL_LINES);
				// t.addVertex(1, 100, -90);
				// t.addVertex(100, 100, -90);
				// t.addVertex(100, 1, -90);
				// t.addVertex(1, 1, -90);
				// t.draw();
				// GL11.glEnable(GL11.GL_BLEND);
				Block id = player.worldObj.getBlock(ItemBinoculars.tx, ItemBinoculars.ty, ItemBinoculars.tz);
				String disp = "X";
				if (id != null) disp = StatCollector.translateToLocal(id.getUnlocalizedName()+".name");
				fr.drawString(disp, (int) ((w * 0.50) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.18), 0x00ff00, false);
				if (!ItemBinoculars.tooFar) disp = "(" + ItemBinoculars.tx + ", " + ItemBinoculars.ty + ", " + ItemBinoculars.tz + ")";
				else disp = "";
				fr.drawString(disp, (int) ((w * 0.50) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.13), 0x00ff00, false);
				disp = "";
				if (tic % 30 == 0) r = !r;
				//if (nbt.getInteger("ty") != -1 && nbt.getInteger("cooldowntime") > 0) disp = ">" + nbt.getInteger("tx") + ", " + nbt.getInteger("ty") + ", " + nbt.getInteger("tz") + "<";
				//else if (r) disp = ">                    <";
				//fr.drawString(disp, (int) ((w * 0.50) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.85), 0xff0000, false);
				disp = "LTD RR";
				fr.drawString(disp, (int) ((w * 0.50) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.80), 0xffffff, false);
				disp = ((int)ItemBinoculars.distblock) + "m";
				fr.drawString(disp, (int) ((w * 0.637) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.205), 0xffffff, false);
				float yaw = (player.rotationYaw + 360000) % 360;
				disp = (yaw >= 315 || yaw < 45) ? StatCollector.translateToLocal("RivalRebels.binoculars.south") : (yaw >= 45 && yaw < 135) ? StatCollector.translateToLocal("RivalRebels.binoculars.west") : (yaw >= 135 && yaw < 225) ? StatCollector.translateToLocal("RivalRebels.binoculars.north") : (yaw >= 225 && yaw < 315) ? StatCollector.translateToLocal("RivalRebels.binoculars.east") : "Whut";
				fr.drawString(disp, (int) ((w * 0.370) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.205), 0xffffff, false);
				if (ItemBinoculars.tooFar) fr.drawString(StatCollector.translateToLocal("RivalRebels.controller.range"), (int) ((w * 0.5) - (fr.getStringWidth(StatCollector.translateToLocal("RivalRebels.controller.range")) / 2f)), (int) (h * 0.85), 0xff0000, false);
				else if (ItemBinoculars.tooClose) fr.drawString(StatCollector.translateToLocal("RivalRebels.nextbattle.no"), (int) ((w * 0.5) - (fr.getStringWidth(StatCollector.translateToLocal("RivalRebels.nextbattle.no")) / 2f)), (int) (h * 0.85), 0xff0000, false);
				//else if (dist2 < 40)
				//{
				//	disp = StatCollector.translateToLocal("RivalRebels.nextbattle.no");
				//	fr.drawString(disp, (int) ((w * 0.5) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.90), 0xff0000, false);
				//	disp = (team == RivalRebelsTeam.OMEGA ? RivalRebels.omegaobj.getLocalizedName() : RivalRebels.omegaobj.getLocalizedName());
				//	fr.drawString(disp, (int) ((w * 0.5) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.94), 0xff0000, false);
				//}
				else if (ItemBinoculars.ready) fr.drawString(StatCollector.translateToLocal("RivalRebels.binoculars.target"), (int) ((w * 0.5) - (fr.getStringWidth(StatCollector.translateToLocal("RivalRebels.binoculars.target")) / 2f)), (int) (h * 0.85), 0xff0000, false);
				
				fr.drawString(StatCollector.translateToLocal("RivalRebels.message.use")+" "+StatCollector.translateToLocal("RivalRebels.sneak")+" B-83 x2", (int) (w * 0.05), (int) (h * 0.95), 0xff0000, false);
				fr.drawString("Press C to select bomb type", (int) (w * 0.60), (int) (h * 0.95), 0xff0000, false);
				
				if ((ItemBinoculars.tasks > 0 || ItemBinoculars.carpet > 0) && ItemBinoculars.dist < 10f)
				{
					Minecraft.getMinecraft().getTextureManager().bindTexture(ItemBinoculars.c?RivalRebels.guicarpet:RivalRebels.ittaskb83);
					
					GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
					float col = 1.0f - ItemBinoculars.dist / 10f;
					GL11.glColor4f(col, col, col, 1.0f);
					t.startDrawingQuads();
					t.addVertexWithUV(w * 0.72, h * 0.85 + 16, -90, 0, 1);
					t.addVertexWithUV(w * 0.72 + 16, h * 0.85 + 16, -90, 1, 1);
					t.addVertexWithUV(w * 0.72 + 16, h * 0.85, -90, 1, 0);
					t.addVertexWithUV(w * 0.72, h * 0.85, -90, 0, 0);
					t.draw();
					
					disp = "x" + ItemBinoculars.tasks;
					fr.drawString(disp, (int) (w * 0.76), (int) (h * 0.85), ItemBinoculars.c?0xffff00:0xff0000, false);
					disp = "x" + ItemBinoculars.carpet;
					fr.drawString(disp, (int) (w * 0.76), (int) (h * 0.9), ItemBinoculars.c?0xff0000:0xffff00, false);
					disp = StatCollector.translateToLocal("RivalRebels.tacticalnuke.name");
					if (!r) fr.drawString(disp, (int) ((w * 0.5) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.71), 0x00ff00, false);
				}
				else if ((ItemBinoculars.tasks > 0 || ItemBinoculars.carpet > 0) && ItemBinoculars.hasLaptop)
				{
					GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
					disp = RivalRebels.controller.getLocalizedName() + " " + StatCollector.translateToLocal("RivalRebels.controller.range");
					fr.drawString(disp, (int) (w * 0.63), (int) (h * 0.87), 0xffff00, false);
				}
			}
		}
	}
}