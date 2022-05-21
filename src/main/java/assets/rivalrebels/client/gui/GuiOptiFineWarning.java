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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.guihelper.GuiButton;

public class GuiOptiFineWarning extends GuiScreen
{
	private final int	xSizeOfTexture	= 256;
	private final int	ySizeOfTexture	= 256;
	private int			posX;
	private int			posY;
	private GuiButton	ok;
	private boolean		prevclick;
	private int			num				= 0;
	private int			count			= 0;
	
	public GuiOptiFineWarning()
	{
	}
	
	@Override
	public void initGui()
	{
		posX = (width - xSizeOfTexture) / 2;
		posY = (height - ySizeOfTexture) / 2;
		buttonList.clear();
		mc = Minecraft.getMinecraft();
		fontRendererObj = mc.fontRenderer;
		ok = new GuiButton(0, posX + 96, posY + 203, 60, 11, "RivalRebels.class.done");
		buttonList.add(ok);
	}
	
	@Override
	public void handleKeyboardInput()
	{
		
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	@Override
	public void updateScreen()
	{
		
	}
	
	@Override
	public void drawScreen(int x, int y, float d)
	{
		count++;
		if (count == 60)
		{
			num = 1 - num;
			count = 0;
		}
		Tessellator tessellator = Tessellator.instance;
		float f = 0.00390625F;
		if (num == 0) this.mc.renderEngine.bindTexture(RivalRebels.guitwarning0);
		if (num == 1) this.mc.renderEngine.bindTexture(RivalRebels.guitwarning1);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(posX, posY + ySizeOfTexture, zLevel, 0, ySizeOfTexture * f);
		tessellator.addVertexWithUV(posX + xSizeOfTexture, posY + ySizeOfTexture, zLevel, xSizeOfTexture * f, ySizeOfTexture * f);
		tessellator.addVertexWithUV(posX + xSizeOfTexture, posY, zLevel, xSizeOfTexture * f, 0);
		tessellator.addVertexWithUV(posX, posY, zLevel, 0, 0);
		tessellator.draw();
		// drawCenteredString(fontRenderer, "Please deinstall OptiFine to view the Rival Rebels Force-Fields properly.", (int) (this.width / 2), (int) (this.height / 2 - 120), 0xffffff);
		float scalefactor = 4f;
		GL11.glScalef(scalefactor, scalefactor, scalefactor);
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("RivalRebels.WARNING"), (int) ((this.width / 2) / scalefactor), (int) ((this.height / 2 - 100) / scalefactor), 0xffffff);
		GL11.glScalef(1 / scalefactor, 1 / scalefactor, 1 / scalefactor);
		fontRendererObj.drawSplitString("OptiFine glitches the Force-Field render! Deinstall OptiFine to render it properly.", posX + 72, posY + 160, 128, 0xffffff);
		super.drawScreen(x, y, d);
		
		if (Mouse.isButtonDown(0) && !prevclick)
		{
			if (ok.mousePressed(mc, x, y))
			{
				this.mc.displayGuiScreen(null);
			}
		}
		prevclick = Mouse.isButtonDown(0);
	}
}
