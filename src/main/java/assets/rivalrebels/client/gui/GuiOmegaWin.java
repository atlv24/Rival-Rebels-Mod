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

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;

public class GuiOmegaWin extends GuiScreen
{
	public GuiOmegaWin()
	{
	}
	
	@Override
	public void initGui()
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
		Tessellator tessellator = Tessellator.instance;
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("RivalRebels.omegawin.subtitle"), (this.width / 2), (this.height / 2 - 120), 0xffffff);
		float scalefactor = 4f;
		GL11.glScalef(scalefactor, scalefactor, scalefactor);
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("RivalRebels.omegawin.title"), (int) ((this.width / 2) / scalefactor), (int) ((this.height / 2 - 100) / scalefactor), 0xffffff);
		GL11.glScalef(1 / scalefactor, 1 / scalefactor, 1 / scalefactor);
		
		fontRendererObj.drawStringWithShadow("Omega: " + RivalRebels.round.getOmegaWins(), (this.width / 2) - 60, (this.height / 2 + 70), 0x44FF44);
		fontRendererObj.drawStringWithShadow("Sigma: " + RivalRebels.round.getSigmaWins(), (this.width / 2) + 10, (this.height / 2 + 70), 0x4444FF);
	}
}
