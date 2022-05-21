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

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiRotor extends GuiButton
{
	protected int		degree;
	protected boolean	pressed;
	
	public GuiRotor(int id, int x, int y, int yawLimit, String par6Str)
	{
		super(id, x, y, 32, 32, par6Str);
		degree = yawLimit / 2;
	}
	
	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
	{
		this.mouseDragged(par1Minecraft, par2, par3);
		par1Minecraft.renderEngine.bindTexture(RivalRebels.guitray);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPushMatrix();
		int deg = (degree % 180);
		if (degree >= 180) deg = 180 - deg;
		if (deg < 22) deg = 22;
		degree = deg;
		this.drawTexturedModalRect(this.xPosition, this.yPosition, 224, 66, this.width, this.height * deg / (180));
		this.drawCenteredString(par1Minecraft.fontRenderer, (deg * 2) + "°", xPosition + width / 2, yPosition + height / 2 - 4, 0xffffff);
		GL11.glPopMatrix();
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
			if (pressed) degree = ((int) (Math.atan2(yPosition - par3 + (height / 2), xPosition - par2 + (width / 2)) * 180 / Math.PI) + 270) % 360;
		}
		else
		{
			pressed = false;
			// float movement = -Mouse.getDWheel() * 0.375f;
			// degree += movement;
			// degree += 360000;
			// degree %= 360;
		}
	}
	
	@Override
	public void mouseReleased(int par2, int par3)
	{
		pressed = false;
	}
	
	public int getDegree()
	{
		return degree;
	}
	
	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent e).
	 */
	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
	{
		return this.enabled && this.visible && Math.sqrt(((xPosition - par2 + (width / 2f)) * (xPosition - par2 + (width / 2f))) + ((yPosition - par3 + (height / 2f)) * (yPosition - par3 + (height / 2f)))) <= (width / 2f);
	}
}
