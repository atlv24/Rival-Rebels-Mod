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

import java.util.Iterator;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.guihelper.GuiButton;
import assets.rivalrebels.client.guihelper.GuiScroll;
import assets.rivalrebels.common.packet.JoinTeamPacket;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.packet.ResetPacket;
import assets.rivalrebels.common.round.RivalRebelsClass;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class GuiSpawn extends GuiScreen
{
	private final int	xSizeOfTexture	= 256;
	private final int	ySizeOfTexture	= 256;
	private int			posX;
	private int			posY;
	private GuiButton	classButton;
	private GuiButton	resetButton;
	private GuiButton	omegaButton;
	private GuiButton	sigmaButton;
	private GuiScroll	omegaScroll;
	private GuiScroll	sigmaScroll;
	private GuiScroll	playerScroll;
	private GuiScroll	gameScroll;
	private boolean		prevClick		= true;
	private RivalRebelsClass rrclass = RivalRebelsClass.NONE;
	
	public GuiSpawn(RivalRebelsClass rrc)
	{
		posX = (width - xSizeOfTexture) / 2;
		posY = (height - ySizeOfTexture) / 2;
		rrclass = rrc;
	}
	
	@Override
	public void initGui()
	{
		posX = (this.width - xSizeOfTexture) / 2;
		posY = (this.height - ySizeOfTexture) / 2;
		this.buttonList.clear();
		
		classButton = new GuiButton(0, posX + 188, posY + 102, 60, 11, "RivalRebels.spawn.class");
		resetButton = new GuiButton(1, posX + 188, posY + 119, 60, 11, "RivalRebels.spawn.reset");
		omegaButton = new GuiButton(2, posX + 35, posY + 237, 60, 11, "RivalRebels.spawn.joinomega");
		sigmaButton = new GuiButton(3, posX + 160, posY + 237, 60, 11, "RivalRebels.spawn.joinsigma");
		omegaScroll = new GuiScroll(4, posX + 118, posY + 140, 80);
		sigmaScroll = new GuiScroll(5, posX + 243, posY + 140, 80);
		playerScroll = new GuiScroll(6, posX + 154, posY + 103, 16);
		gameScroll = new GuiScroll(7, posX + 243, posY + 66, 16);
		RivalRebelsPlayer nw = RivalRebels.round.rrplayerlist.getForName(Minecraft.getMinecraft().thePlayer.getCommandSenderName());
		resetButton.enabled = nw.resets > 0 && !nw.isreset;
		omegaButton.enabled = nw.rrteam == RivalRebelsTeam.NONE || nw.rrteam == RivalRebelsTeam.OMEGA;
		sigmaButton.enabled = nw.rrteam == RivalRebelsTeam.NONE || nw.rrteam == RivalRebelsTeam.SIGMA;
		classButton.enabled = nw.isreset;
		this.buttonList.add(classButton);
		this.buttonList.add(resetButton);
		this.buttonList.add(omegaButton);
		this.buttonList.add(sigmaButton);
		this.buttonList.add(omegaScroll);
		this.buttonList.add(sigmaScroll);
		this.buttonList.add(playerScroll);
		this.buttonList.add(gameScroll);
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	@Override
	public void handleKeyboardInput()
	{
		
	}
	
	@Override
	public void updateScreen()
	{
	}
	
	@Override
	public void drawScreen(int x, int y, float d)
	{
		RivalRebelsPlayer nw = RivalRebels.round.rrplayerlist.getForName(Minecraft.getMinecraft().thePlayer.getCommandSenderName());
		classButton.enabled = nw.isreset;
		omegaButton.enabled = nw.rrteam == RivalRebelsTeam.NONE || nw.rrteam == RivalRebelsTeam.OMEGA;
		sigmaButton.enabled = nw.rrteam == RivalRebelsTeam.NONE || nw.rrteam == RivalRebelsTeam.SIGMA;
		resetButton.enabled = nw.resets > 0 && !nw.isreset;
		Tessellator tessellator = Tessellator.instance;
		float f = 0.00390625F;
		drawDefaultBackground();
		drawGradientRect(posX, posY, posX + xSizeOfTexture, posY + ySizeOfTexture, 0xFF000000, 0xFF000000); // 0xFF587075, 0xFF041010);
		drawPanel(posX + 10, posY + 142, 80, omegaScroll.getScroll(), omegaScroll.limit, RivalRebelsTeam.OMEGA);
		drawPanel(posX + 135, posY + 142, 80, sigmaScroll.getScroll(), sigmaScroll.limit, RivalRebelsTeam.SIGMA);
		drawPanel(posX + 10, posY + 68, 228, 50, gameScroll.getScroll(), gameScroll.limit, RivalRebels.round.getMotD() + "\nMod by Rodolphito. \nVisit www.RivalRebels.com for official downloads.");
		drawGradientRect(posX + 6, posY + 99, posX + 161, posY + 131, 0xFF000000, 0xFF000000);
		drawPanel(posX + 10, posY + 105, 50, playerScroll.getScroll(), playerScroll.limit, new String[] { rrclass.name }, new int[] { rrclass.color });
		
		Random rand = new Random();
		GL11.glColor3f(1F, 1F, 1F);
		this.mc.renderEngine.bindTexture(RivalRebels.guitspawn);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(posX, posY + ySizeOfTexture, zLevel, 0, ySizeOfTexture * f);
		tessellator.addVertexWithUV(posX + xSizeOfTexture, posY + ySizeOfTexture, zLevel, xSizeOfTexture * f, ySizeOfTexture * f);
		tessellator.addVertexWithUV(posX + xSizeOfTexture, posY, zLevel, xSizeOfTexture * f, 0);
		tessellator.addVertexWithUV(posX, posY, zLevel, 0, 0);
		tessellator.draw();
		
		if (RivalRebels.banner != null)
		{
			this.mc.renderEngine.bindTexture(RivalRebels.banner);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(posX + 3, posY + 61, zLevel, 0, 1);
			tessellator.addVertexWithUV(posX + 253, posY + 61, zLevel, 1, 1);
			tessellator.addVertexWithUV(posX + 253, posY + 3, zLevel, 1, 0);
			tessellator.addVertexWithUV(posX + 3, posY + 3, zLevel, 0, 0);
			tessellator.draw();
		}
		
		super.drawScreen(x, y, d);
		
		fontRendererObj.drawString("" + RivalRebels.round.getOmegaWins(), posX + 9, posY + 239, 0xFFFFFF);
		fontRendererObj.drawString("" + RivalRebels.round.getSigmaWins(), posX + 134, posY + 239, 0xFFFFFF);
		
		if (resetButton.mousePressed(mc, x, y) && resetButton.enabled)
		{
			drawGradientRect(x, y, x + 120, y + 20, 0xaa111111, 0xaa111111);
			float scalefactor = 0.666f;
			GL11.glScalef(scalefactor, scalefactor, scalefactor);
			fontRendererObj.drawSplitString(LanguageRegistry.instance().getStringLocalization("RivalRebels.spawn.resetwarning"), (int) ((x + 2) / scalefactor), (int) ((y + 2) / scalefactor), (int) (116 / scalefactor), 0xFF0000);
			GL11.glScalef(1 / scalefactor, 1 / scalefactor, 1 / scalefactor);
		}
		
		if (Mouse.isButtonDown(0) && !prevClick)
		{
			if (classButton.mousePressed(mc, x, y)) this.mc.displayGuiScreen(new GuiClass(rrclass));
			if (resetButton.mousePressed(mc, x, y))
			{
				this.mc.displayGuiScreen(new GuiClass(rrclass));
				PacketDispatcher.packetsys.sendToServer(new ResetPacket());
			}
			if (omegaButton.mousePressed(mc, x, y))
			{
				PacketDispatcher.packetsys.sendToServer(new JoinTeamPacket(RivalRebelsTeam.OMEGA,rrclass));
				this.mc.displayGuiScreen(null);
			}
			if (sigmaButton.mousePressed(mc, x, y))
			{
				PacketDispatcher.packetsys.sendToServer(new JoinTeamPacket(RivalRebelsTeam.SIGMA,rrclass));
				this.mc.displayGuiScreen(null);
			}
		}
		prevClick = Mouse.isButtonDown(0);
	}
	
	protected void drawPanel(int x, int y, int height, int scroll, int scrolllimit, RivalRebelsTeam team)
	{
		RivalRebelsPlayer[] nlist = new RivalRebelsPlayer[RivalRebels.round.rrplayerlist.getSize()];
		RivalRebelsPlayer[] list = RivalRebels.round.rrplayerlist.getArray();
		
		int index = 0;
		for (int i = 0; i < nlist.length; i++)
		{
			if (isOnline(list[i].username) && list[i].rrteam.equals(team))
			{
				nlist[index] = list[i];
				index++;
			}
		}
		for (int i = 0; i < nlist.length; i++)
		{
			if (!isOnline(list[i].username) && list[i].rrteam.equals(team))
			{
				nlist[index] = list[i];
				index++;
			}
		}
		if (index == 0) return;
		int dist = (int) (-((float) scroll / (float) scrolllimit) * ((index * 10) - height));
		boolean shouldScroll = index * 10 > height;
		for (int i = 0; i < nlist.length; i++)
		{
			if (nlist[i] == null) break;
			int Y = dist + (i * 10);
			if (!shouldScroll) Y -= dist;
			if (Y > -9 && Y < height + 9)
			{
				int color = nlist[i].rrclass.color;
				int r = (color & 0xFF0000) >> 16;
				int g = (color & 0xFF00) >> 8;
				int b = (color & 0xFF);
				if (!isOnline(nlist[i].username))
				{
					r /= 2;
					g /= 2;
					b /= 2;
				}
				color = (r << 16) | (g << 8) | b;
				drawString(fontRendererObj, nlist[i].username, x, y + Y, color);
			}
		}
		// RivalRebelsPlayer[] list = RivalRebels.rrplayerlist.getArray();
		// int num = 0;
		// for (int i = 0; i < list.length; i++)
		// {
		// if (list[i].online && list[i].rrteam.equals(team)) num++;
		// }
		// if (num == 0) return;
		// int dist = (int) (-((float) scroll / (float) scrolllimit) * ((num * 10) - height));
		// boolean shouldScroll = num * 10 > height;
		// for (int i = 0; i < list.length; i++)
		// {
		// if (list[i].online && list[i].rrteam.equals(team))
		// {
		// RivalRebelsPlayer rrplayer = (RivalRebelsPlayer) list[i];
		// int Y = dist + (i * 10);
		// if (!shouldScroll) Y -= dist;
		// if (Y > -9 && Y < height + 9)
		// drawString(fontRenderer, rrplayer.username, x, y + (int) Y, rrplayer.rrclass.color);
		// }
		// }
	}
	
	protected void drawPanel(int x, int y, int height, int scroll, int scrolllimit, String[] display, int[] color)
	{
		int dist = (int) (-((float) scroll / (float) scrolllimit) * (((display.length) * 10) - height));
		boolean shouldScroll = (display.length) * 10 > height;
		for (int i = 0; i < display.length; i++)
		{
			int Y = dist + (i * 10);
			if (!shouldScroll) Y -= dist;
			if (Y > -9 && Y < height + 9) drawString(fontRendererObj, StatCollector.translateToLocal(display[i]), x, y + Y, color[i]);
		}
	}
	
	protected void drawPanel(int x, int y, int width, int height, int scroll, int scrolllimit, String display)
	{
		int length = 10;
		int dist = (int) (-((float) scroll / (float) scrolllimit) * (((length) * 10) - height));
		float scalefactor = 0.6666f;
		GL11.glScalef(scalefactor, scalefactor, scalefactor);
		fontRendererObj.drawSplitString(display, (int) (x * 1.5), (int) ((y + dist) * 1.5), (int) (width * 1.5), 0xffffff);
		GL11.glScalef(1 / scalefactor, 1 / scalefactor, 1 / scalefactor);
	}
	
	protected boolean isOnline(String user)
	{
		if (Minecraft.getMinecraft().getNetHandler() == null || Minecraft.getMinecraft().getNetHandler().playerInfoList == null) return false;
		Iterator iter = Minecraft.getMinecraft().getNetHandler().playerInfoList.iterator();
		while (iter.hasNext())
		{
			if (user.equals(((GuiPlayerInfo)iter.next()).name)) return true;
		}
		return false;
	}
}
