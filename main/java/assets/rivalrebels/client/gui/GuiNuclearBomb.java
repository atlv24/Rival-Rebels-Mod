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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.container.ContainerNuclearBomb;
import assets.rivalrebels.common.tileentity.TileEntityNuclearBomb;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiNuclearBomb extends GuiContainer
{
	ContainerNuclearBomb	container;
	TileEntityNuclearBomb	nuclearbomb;
	
	public GuiNuclearBomb(Container par1Container)
	{
		super(par1Container);
		container = (ContainerNuclearBomb) par1Container;
	}
	
	public GuiNuclearBomb(InventoryPlayer inventoryPlayer, TileEntityNuclearBomb tileEntity)
	{
		super(new ContainerNuclearBomb(inventoryPlayer, tileEntity));
		nuclearbomb = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		super.drawGuiContainerForegroundLayer(par1, par2);
		int seconds = (nuclearbomb.Countdown / 20);
		int millis = (nuclearbomb.Countdown % 20) * 3;
		String milli;
		if (millis < 10)
		{
			milli = "0" + millis;
		}
		else
		{
			milli = "" + millis;
		}
		if (nuclearbomb.Countdown % 20 >= 10)
		{
			fontRendererObj.drawString(StatCollector.translateToLocal("RivalRebels.tsar.timer") + ": -" + seconds + ":" + milli, 80, 6, 0x000000);
		}
		else
		{
			fontRendererObj.drawString(StatCollector.translateToLocal("RivalRebels.tsar.timer") + ": -" + seconds + ":" + milli, 80, 6, 0xFF0000);
		}
		fontRendererObj.drawString(StatCollector.translateToLocal("RivalRebels.nuke.name"), 8, 6, 0xffffff);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 0xffffff);
		if (nuclearbomb.hasExplosive && nuclearbomb.hasFuse)
		{
			fontRendererObj.drawString(StatCollector.translateToLocal("RivalRebels.tsar.armed"), 80, ySize - 96 + 2, 0xffffff);
		}
		else
		{
			if (!nuclearbomb.hasTrollface)
			{
				fontRendererObj.drawString(nuclearbomb.AmountOfCharges * 2.5 + " " + StatCollector.translateToLocal("RivalRebels.tsar.megatons"), 80, ySize - 96 + 2, 0xffffff);
			}
			else
			{
				fontRendererObj.drawString("Umad bro?", 80, ySize - 96 + 2, 0xffffff);
			}
		}
		
		int mousex = par1;
		int mousey = par2;
		int posx = (width - xSize) / 2;
		int posy = (height - ySize) / 2;
		int coordx = posx + 53;
		int coordy = posy + 158;
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
	
	boolean	buttondown;
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor3f(1, 1, 1);
		if (nuclearbomb.AmountOfCharges != 0) GL11.glColor3f((nuclearbomb.AmountOfCharges * 0.1F), 1 - (nuclearbomb.AmountOfCharges * 0.1F), 0);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.guitnuke);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, 81);
		GL11.glColor3f(1, 1, 1);
		this.drawTexturedModalRect(x, y + 81, 0, 81, xSize, ySize - 81);
	}
}
