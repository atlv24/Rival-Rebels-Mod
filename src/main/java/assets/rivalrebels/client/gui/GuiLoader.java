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
import net.minecraft.inventory.IInventory;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.container.ContainerLoader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLoader extends GuiContainer
{
	private IInventory	upperChestInventory;
	private IInventory	lowerChestInventory;
	
	/**
	 * window height is calculated with this values, the more rows, the heigher
	 */
	private int			inventoryRows	= 0;
	
	public GuiLoader(IInventory par1IInventory, IInventory par2IInventory)
	{
		super(new ContainerLoader(par1IInventory, par2IInventory));
		this.upperChestInventory = par1IInventory;
		this.lowerChestInventory = par2IInventory;
		this.allowUserInput = false;
		short var3 = 222;
		int var4 = var3 - 108;
		this.inventoryRows = par2IInventory.getSizeInventory() / 9;
		this.ySize = var4 + this.inventoryRows * 18;
		this.xSize = 256;
	}
	
	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		GL11.glPushMatrix();
		GL11.glRotated(-13, 0, 0, 1);
		fontRendererObj.drawString("Loader", 165, 237, 0x444444);
		GL11.glPopMatrix();
		int mousex = par1;
		int mousey = par2;
		int posx = (width - xSize) / 2;
		int posy = (height - ySize) / 2;
		int coordx = posx + 92;
		int coordy = posy + 202;
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
	
	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.guitloader);
		this.drawTexturedModalRect(width / 2 - 128, height / 2 - 103, 0, 0, 256, 210);
	}
}
