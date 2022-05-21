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
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCustomButton extends net.minecraft.client.gui.GuiButton
{
	Rectangle			bbox;
	Vector				tbox;
	ResourceLocation	resloc;
	boolean				toggleable;
	public boolean		isPressed	= false;
	public boolean		wasPressed	= false;
	public boolean		mouseDown	= false;
	
	public GuiCustomButton(int id, Rectangle rec, ResourceLocation rl, Vector uv, boolean isToggle)
	{
		super(id, rec.xMin, rec.yMin, rec.xMax - rec.xMin, rec.yMax - rec.yMin, "");
		bbox = rec;
		tbox = uv;
		resloc = rl;
		toggleable = isToggle;
	}
	
	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
	{
		boolean current = Mouse.isButtonDown(0) && bbox.isVecInside(new Vector(par2, par3));
		wasPressed = false;
		if (toggleable && current && !mouseDown)
		{
			mouseDown = true;
			isPressed = !isPressed;
			wasPressed = true;
		}
		else if (toggleable && !current)
		{
			mouseDown = false;
		}
		else if (!toggleable && current)
		{
			isPressed = true;
		}
		else if (!toggleable && !current)
		{
			isPressed = false;
		}
		
		if (isPressed)
		{
			par1Minecraft.renderEngine.bindTexture(resloc);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			drawTexturedModalRect(bbox.xMin, bbox.yMin, tbox.x, tbox.y, bbox.xMax - bbox.xMin, bbox.yMax - bbox.yMin);
		}
	}
}
