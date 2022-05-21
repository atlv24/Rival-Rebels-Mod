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
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButton extends net.minecraft.client.gui.GuiButton
{
	public GuiButton(int par1, int par2, int par3, String par4Str)
	{
		this(par1, par2, par3, 60, 11, par4Str);
	}
	
	public GuiButton(int par1, int par2, int par3, int par4, int par5, String par6Str)
	{
		super(par1, par2, par3, par4, par5, par6Str);
	}
	
	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
	{
		if (this.visible)
		{
			FontRenderer fontrenderer = par1Minecraft.fontRenderer;
			par1Minecraft.renderEngine.bindTexture(RivalRebels.guitbutton);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
			int k = this.getHoverState(this.field_146123_n);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 5, k * 11, this.width, this.height);
			this.mouseDragged(par1Minecraft, par2, par3);
			int l = 0xffffff;
			
			if (!this.enabled)
			{
				l = 0xcccccc;
			}
			else if (this.field_146123_n)
			{
				l = 0x88e8ff;
			}
			
			this.drawCenteredString(fontrenderer, StatCollector.translateToLocal(this.displayString), this.xPosition + this.width / 2, this.yPosition + (this.height - 7) / 2, l);
		}
	}
}
