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
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Mouse;

import assets.rivalrebels.client.gui.GuiTray;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDropdownOption extends net.minecraft.client.gui.GuiButton
{
	Rectangle		bbox;
	public boolean	isPressed	= false;
	public boolean	mouseDown	= false;
	public String	text		= "";
	public GuiTray	t;
	
	public GuiDropdownOption(int id, Vector p, int l, int n, String text, GuiTray tray)
	{
		super(id, p.x, p.y + n * 10, l, (n + 1) * 10, text);
		bbox = new Rectangle(p.x, p.y + n * 10, l, (n + 1) * 10);
		this.text = text;
		t = tray;
	}
	
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
	{
		boolean inside = bbox.isVecInside(new Vector(par2, par3));
		boolean current = Mouse.isButtonDown(0) && inside;
		boolean on = t.r.hasWepReqs();
		if (current && !mouseDown && on) isPressed = !isPressed;
		int color = 0x999999;
		if (on)
		{
			int team = 0;
			if (t.r.chestContents[6] != null && t.r.chestContents[6].stackTagCompound != null)
			{
				team = t.r.chestContents[6].stackTagCompound.getInteger("team");
			}
			if (team == 0) color = 0xffff55;
			if (team == 1) color = 0x55ff55;
			if (team == 2) color = 0x5555ff;
		}
		if (inside && on) color = 0xffffff;
		drawString(Minecraft.getMinecraft().fontRenderer, StatCollector.translateToLocal(text), bbox.xMin + 1, bbox.yMin + 1, color);
		mouseDown = current;
	}
}
