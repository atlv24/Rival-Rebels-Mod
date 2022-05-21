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
// package RivalRebels.Client.Gui;
//
// import java.io.ByteArrayOutputStream;
// import java.io.DataOutputStream;
// import java.io.IOException;
//
// import RivalRebels.Client.GuiHelper.GuiButton;
// import RivalRebels.Common.Container.ContainerLaptop;
// import RivalRebels.Common.Core.RivalRebels;
// import RivalRebels.Common.Core.RivalRebelsClientPacketHandler;
// import RivalRebels.Common.TileEntity.TileEntityLaptop;
// import cpw.mods.fml.common.FMLCommonHandler;
// import cpw.mods.fml.common.network.PacketDispatcher;
// import cpw.mods.fml.common.registry.LanguageRegistry;
// import cpw.mods.fml.relauncher.Side;
// import cpw.mods.fml.relauncher.SideOnly;
// import net.minecraft.inventory.Container;
// import net.minecraft.network.packet.Packet250CustomPayload;
// import net.minecraft.client.Minecraft;
// import net.minecraft.client.gui.inventory.GuiContainer;
// import net.minecraft.entity.player.InventoryPlayer;
// import net.minecraft.util.StatCollector;
// import net.minecraft.util.StringTranslate;
//
// import org.lwjgl.opengl.GL11;
//
// @SideOnly(Side.CLIENT)
// public class GuiLaptop extends GuiContainer
// {
// ContainerLaptop container;
// TileEntityLaptop lt;
// GuiButton button;
//
// public GuiLaptop(Container container)
// {
// super(container);
// container = (ContainerLaptop) container;
// }
//
// public GuiLaptop(InventoryPlayer inventoryPlayer, TileEntityLaptop tileEntity)
// {
// super(new ContainerLaptop(inventoryPlayer, tileEntity));
// ySize = 206;
// lt = tileEntity;
// }
//
// public void initGui()
// {
// int x = (width - xSize) / 2;
// int y = (height - ySize) / 2;
// this.buttonList.clear();
//
// button = new GuiButton(0, x+131, y+89, 16, 16, "");
// buttonList.add(button);
// }
//
// @Override
// protected void drawGuiContainerForegroundLayer(int par1, int par2)
// {
// super.drawGuiContainerForegroundLayer(par1, par2);
// int posX = (width - xSize) / 2;
// int posY = (height - ySize) / 2;
// Minecraft.getMinecraft().fontRenderer.drawString(StatCollector.translateToLocal("RivalRebels.controller.B83"), posX + 118, posY + 11, 0xffffff);
// Minecraft.getMinecraft().fontRenderer.drawString(StatCollector.translateToLocal("RivalRebels.controller.b2spirit"), posX + 25, posY + 11, 0xffffff);
// }
//
// @Override
// protected void drawGuiContainerBackgroundLayer(float par1, int x, int y)
// {
// GL11.glColor3f(1, 1, 1);
// Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.guilaptopnuke);
// int posX = (width - xSize) / 2;
// int posY = (height - ySize) / 2;
// this.drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
// if (lt.hasChips()) this.drawTexturedModalRect(posX + 135, posY + 79, 248, 0, 8, 8);
// if (button.mousePressed(mc, x, y))
// {
// Side side = FMLCommonHandler.instance().getEffectiveSide();
// if (side == Side.CLIENT)
// {
// ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
// DataOutputStream outputStream = new DataOutputStream(bos);
// try
// {
// outputStream.writeInt(5);
// outputStream.writeInt(2);
// outputStream.writeInt(lt.xCoord);
// outputStream.writeInt(lt.yCoord);
// outputStream.writeInt(lt.zCoord);
// }
// catch (Exception ex)
// {
// ex.printStackTrace();
// }
// finally
// {
// try
// {
// if (outputStream != null) outputStream.close();
// }
// catch (IOException error)
// {
//
// }
// }
// Packet250CustomPayload packet = new Packet250CustomPayload();
// packet.channel = "RodolRivalRebels";
// packet.data = bos.toByteArray();
// packet.length = bos.size();
// PacketDispatcher.sendPacketToServer(packet);
// }
// }
// }
// }

package assets.rivalrebels.client.gui;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.guihelper.GuiCustomButton;
import assets.rivalrebels.client.guihelper.GuiDropdownOption;
import assets.rivalrebels.client.guihelper.GuiRotor;
import assets.rivalrebels.client.guihelper.Rectangle;
import assets.rivalrebels.client.guihelper.Vector;
import assets.rivalrebels.client.tileentityrender.TileEntityRecieverRenderer;
import assets.rivalrebels.common.container.ContainerReciever;
import assets.rivalrebels.common.packet.ADSClosePacket;
import assets.rivalrebels.common.packet.ADSWeaponPacket;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.tileentity.TileEntityReciever;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTray extends GuiContainer
{
	ContainerReciever			container;
	public TileEntityReciever	r;
	private float				xSize_lo;
	private float				ySize_lo;
	GuiRotor					range;
	GuiCustomButton				chip;
	GuiCustomButton				players;
	GuiCustomButton				mobs;
	GuiDropdownOption			select1;
	
	public GuiTray(Container container)
	{
		super(container);
		this.container = (ContainerReciever) container;
	}
	
	public GuiTray(InventoryPlayer inventoryPlayer, TileEntityReciever tileEntity)
	{
		super(new ContainerReciever(inventoryPlayer, tileEntity));
		ySize = 206;
		r = tileEntity;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		buttonList.clear();
		range = new GuiRotor(0, x + 93 - 16, y + 92 - 16, r.yawLimit, "Range");
		chip = new GuiCustomButton(1, new Rectangle(x + 94, y + 10, 19, 19), RivalRebels.guitray, new Vector(237, 10), true);
		chip.isPressed = r.kTeam;
		players = new GuiCustomButton(2, new Rectangle(x + 94, y + 28, 19, 19), RivalRebels.guitray, new Vector(237, 28), true);
		players.isPressed = r.kPlayers;
		mobs = new GuiCustomButton(3, new Rectangle(x + 94, y + 46, 19, 19), RivalRebels.guitray, new Vector(237, 46), true);
		mobs.isPressed = r.kMobs;
		select1 = new GuiDropdownOption(4, new Vector(119 + x, 8 + y), 45, 0, "RivalRebels.ads.dragon", this);
		buttonList.add(range);
		buttonList.add(chip);
		buttonList.add(players);
		buttonList.add(mobs);
		buttonList.add(select1);
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = par1;
		this.ySize_lo = par2;
	}
	
	@Override
	protected void keyTyped(char par1, int par2)
	{
		if (par2 == 1)
		{
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
			PacketDispatcher.packetsys.sendToServer(new ADSClosePacket(r.xCoord, r.yCoord, r.zCoord, mobs.isPressed, chip.isPressed, players.isPressed, range.getDegree()*2));
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		super.drawGuiContainerForegroundLayer(par1, par2);
		
		if (select1.isPressed)
		{
			PacketDispatcher.packetsys.sendToServer(new ADSWeaponPacket(r.xCoord, r.yCoord, r.zCoord, 1));
		}
		select1.isPressed = false;
		
		int mousex = par1;
		int mousey = par2;
		int posx = (width - xSize) / 2;
		int posy = (height - ySize) / 2;
		int coordx = posx + 53;
		int coordy = posy + 194;
		int widthx = 72;
		int widthy = 8;
		if (mousex > coordx && mousey > coordy && mousex < coordx + widthx && mousey < coordy + widthy)
		{
			mousex -= posx;
			mousey -= posy;
			drawGradientRect(mousex, mousey, mousex + fontRendererObj.getStringWidth("rivalrebels.com") + 3, mousey + 12, 0xaa111111, 0xaa111111);
			fontRendererObj.drawString("rivalrebels.com", mousex + 2, mousey + 2, 0xFFFFFF);
			if (Desktop.isDesktopSupported() && !buttondown && Mouse.isButtonDown(0))
			{
				try
				{
					Desktop.getDesktop().browse(new URI("http://rivalrebels.com"));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (URISyntaxException e)
				{
					e.printStackTrace();
				}
			}
		}
		buttondown = Mouse.isButtonDown(0);
	}
	
	boolean		buttondown;
	
	static int	spinfac	= 0;
	
	public void drawADS(int x, int y, int scale, float px, float py)
	{
		spinfac += 1;
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y - 40, 50);
		GL11.glScalef(-scale, scale, scale);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotated(20, 1.0F, 0.0F, 0.0F);
		if (!r.hasWeapon)
		{
			GL11.glTranslatef(0, 0, -0.5f);
			GL11.glRotatef(spinfac, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0, 0, 0.5f);
		}
		// GL11.glRotated(Math.sin(spinfac/(40 * Math.PI)) * 10, 1.0F, 0.0F, 0.0F);
		
		// entity.rotationPitch = -((float)Math.atan((double)(py / 40.0F))) * 40.0F;
		// entity.rotationYawHead = (float)Math.atan((double)(px / 40.0F)) * 40.0F;
		// + (Math.sin(spinfac/(40 * Math.PI)) * 10)
		// - (spinfac * 0.5)
		
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etreciever);
		GL11.glRotated(180, 0, 1, 0);
		// GL11.glRotated((spinfac * 0.5), 0, 1, 0);
		GL11.glTranslated(0, -0.5 * 1.5, (-0.5 - 0.34) * -1.5);
		TileEntityRecieverRenderer.base.render();
		if (r.hasWeapon)
		{
			GL11.glTranslated(0, 0.5 * 1.5, (-0.5 - 0.34) * 1.5);
			GL11.glRotated(-Math.atan(px / 40.0F) * 40.0F, 0, 1, 0);
			TileEntityRecieverRenderer.arm.render();
			GL11.glRotated(Math.atan(py / 40.0F) * 40.0F + 20, 1, 0, 0);
			Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etadsdragon);
			TileEntityRecieverRenderer.adsdragon.render();
		}
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor3f(1, 1, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.guitray);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		if (r.pInR > 0)
		{
			drawTexturedModalRect(x + 104, y + 68, 248, 0, 8, 8);
		}
		
		Minecraft.getMinecraft().fontRenderer.drawString(StatCollector.translateToLocal("RivalRebels.ads.tray"), x + 25, y + 66, 0xffffff);
		drawADS(guiLeft + 51, guiTop + 75, 30, guiLeft + 51 - this.xSize_lo, guiTop + 25 - this.ySize_lo);
	}
}