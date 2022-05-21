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
public class GuiDial extends GuiButton
{
	protected int		degree;
	protected int		maxdegreelimit	= 360;
	protected int		mindegreelimit	= 0;
	protected boolean	pressed;
	
	public GuiDial(int id, int x, int y, int minDegree, int maxDegree, int startDegree, boolean respectLimits, String par6Str)
	{
		super(id, x, y, 11, 11, par6Str);
		if (respectLimits)
		{
			maxdegreelimit = maxDegree;
			mindegreelimit = minDegree;
		}
		degree = startDegree;
	}
	
	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
	{
		this.mouseDragged(par1Minecraft, par2, par3);
		if (degree > maxdegreelimit) degree = maxdegreelimit;
		if (degree < mindegreelimit) degree = mindegreelimit;
		par1Minecraft.renderEngine.bindTexture(RivalRebels.guitbutton);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int state = 0;
		if (pressed || mousePressed(par1Minecraft, par2, par3)) state = 11;
		GL11.glPushMatrix();
		GL11.glTranslatef(this.xPosition + (width / 2f), this.yPosition + (height / 2f), 0);
		GL11.glRotatef(degree, 0, 0, 1);
		GL11.glTranslatef(-(this.xPosition + (width / 2f)), -(this.yPosition + (height / 2f)), 0);
		this.drawTexturedModalRect(this.xPosition, this.yPosition, 65, state, this.width, this.height);
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
