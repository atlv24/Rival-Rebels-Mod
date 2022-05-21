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
package assets.rivalrebels.client.guihelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;

public class GuiScroll extends GuiButton
{
	/** Scroll limit */
	public int		limit;
	
	/** Current scroll amount (from the top down) */
	public int		scroll;
	
	/** Keeps if the scroll is active */
	public boolean	pressed	= false;
	
	public GuiScroll(int par1, int par2, int par3, int par4)
	{
		super(par1, par2, par3, 5, 11, "");
		this.limit = par4;
		pressed = false;
	}
	
	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
	{
		this.mouseDragged(par1Minecraft, par2, par3);
		if (scroll > limit) scroll = limit;
		if (scroll < 0) scroll = 0;
		par1Minecraft.renderEngine.bindTexture(RivalRebels.guitbutton);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int state = 0;
		if (pressed || mousePressed(par1Minecraft, par2, par3)) state = 11;
		this.drawTexturedModalRect(this.xPosition, this.yPosition + scroll, 0, state, this.width, this.height);
	}
	
	/**
	 * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
	 */
	@Override
	protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
	{
		if (Mouse.isButtonDown(0))
		{
			if (mousePressed(par1Minecraft, par2, par3)) pressed = true;
			if (pressed) scroll = par3 - yPosition - 5;
		}
		else
		{
			pressed = false;
		}
	}
	
	@Override
	public void mouseReleased(int par2, int par3)
	{
		pressed = false;
	}
	
	public int getScroll()
	{
		return scroll;
	}
	
	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent e).
	 */
	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
	{
		return this.enabled && this.visible && par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + limit + this.height;
	}
}
