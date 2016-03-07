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
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.guihelper.GuiCustomButton;
import assets.rivalrebels.client.guihelper.GuiScroll;
import assets.rivalrebels.client.guihelper.Rectangle;
import assets.rivalrebels.client.guihelper.Vector;
import assets.rivalrebels.common.container.ContainerReactor;
import assets.rivalrebels.common.item.ItemCore;
import assets.rivalrebels.common.item.ItemRod;
import assets.rivalrebels.common.noise.RivalRebelsSimplexNoise;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.packet.ReactorGUIPacket;
import assets.rivalrebels.common.tileentity.TileEntityReactor;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiReactor extends GuiContainer
{
	private IInventory				upperChestInventory;
	private IInventory				lowerChestInventory;
	private static DecimalFormat	df					= new DecimalFormat("0.0");
	private float					frame				= 0;
	private float					resolution			= 4f;
	private TileEntityReactor		ter;
	private Block[]					machineslist		= { RivalRebels.forcefieldnode, RivalRebels.reactive };
	private Block[]					machines			= { null, null, null, null };
	private boolean[]				onmachines			= new boolean[machines.length];
	private boolean[]				enabledmachines		= new boolean[machineslist.length];
	private boolean[]				prevenabledmachines	= new boolean[machineslist.length];
	private GuiScroll				scroll;
	private GuiCustomButton			power;
	private GuiCustomButton			eject;
	private float					melttick			= 30;
	
	public GuiReactor(IInventory par1IInventory, IInventory par2IInventory)
	{
		super(new ContainerReactor(par1IInventory, par2IInventory));
		this.ter = (TileEntityReactor) par2IInventory;
		this.upperChestInventory = par1IInventory;
		this.lowerChestInventory = par2IInventory;
		this.ySize = 200;
		RivalRebelsSimplexNoise.refresh();
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		scroll = new GuiScroll(0, posX + 236, posY + 127, 60);
		power = new GuiCustomButton(1, new Rectangle(posX + 70, posY + 164, 22, 22), RivalRebels.guittokamak, new Vector(212, 0), true);
		eject = new GuiCustomButton(2, new Rectangle(posX + 164, posY + 164, 22, 22), RivalRebels.guittokamak, new Vector(234, 0), false);
		power.isPressed = ter.on;
		this.buttonList.add(scroll);
		this.buttonList.add(power);
		this.buttonList.add(eject);
	}
	
	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		GL11.glPushMatrix();
		GL11.glScalef(1.25f, 1f, 1f);
		if (inventorySlots instanceof ContainerReactor)
		{
			((ContainerReactor) inventorySlots).core.locked = ter.on;
			((ContainerReactor) inventorySlots).fuel.locked = ter.fuel != null && ter.on;
		}
		fontRendererObj.drawString("ToKaMaK", 10, 8, 0x444444);
		GL11.glPopMatrix();
		fontRendererObj.drawString("Teslas: " + df.format(ter.getPower() - ter.consumed), 120, 8, 0xffffff);
		fontRendererObj.drawString("Output/t: " + df.format(ter.lasttickconsumed), 140, 18, 0xffffff);
		
		int mousex = par1;
		int mousey = par2;
		int posx = (width - xSize) / 2;
		int posy = (height - ySize) / 2;
		int coordx = posx + 53;
		int coordy = posy + 191;
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
	boolean	buttondown2;
	
	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		if (ter.isInvalid()) Minecraft.getMinecraft().currentScreen = null;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.guittokamak);
		this.drawTexturedModalRect(width / 2 - 89, height / 2 - 103, 0, 0, 212, 208);
		
		float s = (float) scroll.scroll / (float) scroll.limit;
		int off = (int) Math.floor((machineslist.length - 2) * s);
		if (off < 0) off = 0;
		int X = 89 + width / 2;
		int Y = 00 + height / 2;
		for (int i = 0; i < 4; i++)
		{
			if (off + i >= machineslist.length)
			{
				machines[i] = null;
				continue;
			}
			boolean current = new Rectangle(X, Y, 15, 15).isVecInside(new Vector(par2, par3));
			if (off + i < machineslist.length && off + i >= 0)
			{
				machines[i] = machineslist[off + i];
				onmachines[i] = enabledmachines[off + i];
				if (Mouse.isButtonDown(0) && current) enabledmachines[off + i] = !prevenabledmachines[off + i];
				else prevenabledmachines[off + i] = enabledmachines[off + i];
			}
			else
			{
				machines[i] = null;
				onmachines[i] = false;
			}
			Y += 18;
		}
		
		drawDock();
		long time = System.currentTimeMillis();
		// 1f, 1f, 1f, 0.7f, 0f, 1f, HYDROGEN
		// 1f, 0.8f, 0f, 1f, 0f, 0f REDSTONE
		if (ter.on && ter.fuel != null && ter.core != null)
		{
			float radius = 10;
			if (ter.fuel.getTagCompound() != null) radius += (((((ItemRod) ter.fuel.getItem()).power * ((ItemCore) ter.core.getItem()).timemult) - ter.fuel.getTagCompound().getInteger("fuelLeft")) / (((ItemRod) ter.fuel.getItem()).power * ((ItemCore) ter.core.getItem()).timemult)) * 30;
			melttick = 30;
			float brightness = 0;
			if (ter.core.getItem() == RivalRebels.core1) brightness = -0.4f;
			if (ter.core.getItem() == RivalRebels.core2) brightness = -0.25f;
			if (ter.core.getItem() == RivalRebels.core3) brightness = -0.1f;
			if (ter.fuel.getItem() == RivalRebels.nuclearelement) drawNoiseSphere(0.9f, 1f, 0.1f, 0f, 1f, 0.1f, frame, 4, (int) radius, (int) (50 - radius), resolution, 0.02f, brightness);
			if (ter.fuel.getItem() == RivalRebels.hydrod) drawNoiseSphere(1f, 1f, 1f, 0.7f, 0f, 1f, frame, 4, (int) radius, (int) (50 - radius), resolution, 0.02f, brightness);
			if (ter.fuel.getItem() == RivalRebels.redrod) drawNoiseSphere(1f, 0.8f, 0f, 1f, 0f, 0f, frame, 4, (int) radius, (int) (50 - radius), resolution, 0.02f, brightness);
		}
		else if (ter.melt || (ter.on && ter.fuel == null))
		{
			if (melttick > 1) melttick -= 0.03f;
			drawNoiseSphere(1f, 1f, 1f, 0f, 0f, 0f, frame, 4, (int) (20 + Math.sin(frame / melttick) * 20), 10, resolution, 0.02f, 0);
		}
		else if (ter.fuel != null && ter.core != null)
		{
			// drawInfographic(resolution, 15, 8, 5, 20, 0.666f, 0.25f, 0.32f);
		}
		long elapsed = System.currentTimeMillis() - time;
		if (elapsed > 30)
		{
			if (resolution == 0.25) resolution = 0.125f;
			if (resolution == 0.5) resolution = 0.25f;
			if (resolution == 1) resolution = 0.5f;
			if (resolution == 2) resolution = 1;
			if (resolution == 4) resolution = 2;
		}
		frame += 0.75f + (ter.lasttickconsumed / 100);
		if (!buttondown2 && Mouse.isButtonDown(0))
		{
			if (power.mousePressed(mc, par2, par3))
			{
				PacketDispatcher.packetsys.sendToServer(new ReactorGUIPacket(ter.xCoord,ter.yCoord,ter.zCoord,(byte) 0));
			}
			if (eject.mousePressed(mc, par2, par3))
			{
				PacketDispatcher.packetsys.sendToServer(new ReactorGUIPacket(ter.xCoord,ter.yCoord,ter.zCoord,(byte) 1));
			}
		}
		power.isPressed = ter.on;
		buttondown2 = Mouse.isButtonDown(0);
	}
	
	protected void drawDock()
	{
		int X = 89 + width / 2;
		int Y = 00 + height / 2;
		for (int i = 0; i < 4; i++)
		{
			if (machines[i] == null) return;
			
			Block display = machines[i];
			int meta = 2;
			String toppath = "Minecraft:textures/blocks/" + display.getIcon(1, meta).getIconName() + ".png";
			String lsidepath = "Minecraft:textures/blocks/" + display.getIcon(4, meta).getIconName() + ".png";
			String rsidepath = "Minecraft:textures/blocks/" + display.getIcon(2, meta).getIconName() + ".png";
			
			if (toppath.contains("RivalRebels:"))
			{
				toppath = "RivalRebels:textures/blocks/" + display.getBlockTextureFromSide(1).getIconName().split("RivalRebels:")[1] + ".png";
			}
			
			if (lsidepath.contains("RivalRebels:"))
			{
				lsidepath = "RivalRebels:textures/blocks/" + display.getBlockTextureFromSide(4).getIconName().split("RivalRebels:")[1] + ".png";
			}
			
			if (rsidepath.contains("RivalRebels:"))
			{
				rsidepath = "RivalRebels:textures/blocks/" + display.getBlockTextureFromSide(2).getIconName().split("RivalRebels:")[1] + ".png";
			}
			Tessellator tessellator = Tessellator.instance;
			
			GL11.glEnable(GL11.GL_BLEND);
			float alpha = 0.5f;
			if (onmachines[i]) alpha = 1;
			GL11.glColor4f(1F, 1F, 1F, alpha);
			this.mc.renderEngine.bindTexture(new ResourceLocation(toppath));
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(X + 1, Y + 3.5, zLevel, 0, 1);
			tessellator.addVertexWithUV(X + 8, Y + 7, zLevel, 0, 0);
			tessellator.addVertexWithUV(X + 15, Y + 3.5, zLevel, 1, 0);
			tessellator.addVertexWithUV(X + 8, Y, zLevel, 1, 1);
			tessellator.draw();
			GL11.glColor4f(0.666F, 0.666F, 0.666F, alpha);
			this.mc.renderEngine.bindTexture(new ResourceLocation(lsidepath));
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(X + 1, Y + 3.5, zLevel, 0, 0);
			tessellator.addVertexWithUV(X + 1, Y + 12.5, zLevel, 0, 1);
			tessellator.addVertexWithUV(X + 8, Y + 16, zLevel, 1, 1);
			tessellator.addVertexWithUV(X + 8, Y + 7, zLevel, 1, 0);
			tessellator.draw();
			GL11.glColor4f(0.5F, 0.5F, 0.5F, alpha);
			this.mc.renderEngine.bindTexture(new ResourceLocation(rsidepath));
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(X + 15, Y + 12.5, zLevel, 1, 1);
			tessellator.addVertexWithUV(X + 15, Y + 3.5, zLevel, 1, 0);
			tessellator.addVertexWithUV(X + 8, Y + 7, zLevel, 0, 0);
			tessellator.addVertexWithUV(X + 8, Y + 16, zLevel, 0, 1);
			tessellator.draw();
			
			GL11.glDisable(GL11.GL_BLEND);
			
			Y += 18;
		}
	}
	
	/**
	 * Method that draws the noise sphere.
	 * 
	 * @param red
	 *            Vein Color Red
	 * @param grn
	 *            Vein Color Blue
	 * @param blu
	 *            Vein Color Green
	 * @param red1
	 *            Fade Color Red
	 * @param grn1
	 *            Fade Color Blue
	 * @param blu1
	 *            Fade Color Green
	 * @param frame
	 *            Time Value
	 * @param o
	 *            Octaves of Noise
	 * @param radius
	 *            Radius of inner Sphere
	 * @param outer
	 *            Radius of Corona
	 * @param resolution
	 *            Resolution in pixels of Noise Sphere
	 * @param sscale
	 *            Noise Scale
	 */
	
	protected void drawNoiseSphere(float red, float grn, float blu, float red1, float grn1, float blu1, float frame, int o, int radius, int outer, float resolution, float sscale, float startcol)
	{
		Tessellator t = Tessellator.instance;
		ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		GL11.glPushMatrix();
		GL11.glPointSize(scaledresolution.getScaleFactor() / resolution);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		t.startDrawing(GL11.GL_POINTS);
		radius *= resolution;
		int outerR = (int) (radius + (outer * resolution));
		int maxdist = outerR - radius;
		float hheight = (height / 2) - 45;
		float hwidth = (width / 2);
		float rradius = radius * radius;
		for (int x = 1 - outerR; x < outerR; x++)
		{
			int xs = x * x;
			double X = (double) x / (double) resolution + hwidth;
			for (int y = 1 - outerR; y < outerR; y++)
			{
				int ys = y * y + xs;
				double Y = (double) y / (double) resolution + hheight;
				double fdist = Math.sqrt(ys);
				if (fdist >= radius && fdist < outerR)
				{
					float v = 0;
					float a = 1f;
					float s = sscale;
					for (int e = 0; e < o; e++)
					{
						v += (float) ((1 + RivalRebelsSimplexNoise.noise(X * s, Y * s, frame * s)) / 2) * a;
						s *= 2;
						a /= 2;
					}
					v *= 1f - (fdist - radius) / maxdist;
					t.setColorRGBA_F(lerp(red, red1, v), lerp(grn, grn1, v), lerp(blu, blu1, v), v);
					t.addVertex(X, Y, 4);
				}
				else
				{
					double Z = Math.sqrt(rradius - ys) / resolution;
					float v = startcol;
					float a = 1f;
					float s = sscale;
					for (int e = 0; e < o; e++)
					{
						v += (float) ((1 + RivalRebelsSimplexNoise.noise(X * s, Y * s, Z * s, frame * s)) / 2) * a;
						s *= 2;
						a /= 2;
					}
					
					t.setColorRGBA_F(lerp(red, red1, v), lerp(grn, grn1, v), lerp(blu, blu1, v), v);
					t.addVertex(X, Y, 4);
				}
			}
		}
		t.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	
	protected float lerp(float f1, float f2, float f3)
	{
		return f1 * f3 + f2 * (1 - f3);
	}
	
	protected void drawInfographic(float resolution, int radius, int sep, int width1, int width2, float outerRatio, float innerRatio1, float innerRatio2)
	{
		Tessellator t = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glPointSize(4 / resolution);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		t.startDrawing(GL11.GL_POINTS);
		radius *= resolution;
		sep *= resolution;
		width1 *= resolution;
		width2 *= resolution;
		innerRatio1 *= outerRatio;
		innerRatio2 *= outerRatio;
		innerRatio2 += outerRatio;
		outerRatio *= Math.PI * 2;
		innerRatio1 *= Math.PI * 2;
		innerRatio2 *= Math.PI * 2;
		int midR1 = (radius + width1);
		int midR2 = (midR1 + sep);
		int outerR = (midR2 + width2);
		float hheight = (height / 2);
		float hwidth = (width / 2);
		for (int x = 1 - outerR; x < outerR; x++)
		{
			int xs = x * x;
			double X = (double) x / (double) resolution;
			for (int y = 1 - outerR; y < outerR; y++)
			{
				int ys = y * y;
				double fdist = Math.sqrt(xs + ys);
				if (fdist >= radius && fdist < midR1)
				{
					double Y = (double) y / (double) resolution;
					double angle = Math.PI + Math.atan2(X, Y);
					if (angle <= outerRatio)
					{
						if (angle <= innerRatio1)
						{
							t.setColorRGBA_F(0.25f, 0.25f, 1, 1);
						}
						else
						{
							t.setColorRGBA_F(0.75f, 0.75f, 1, 1);
						}
					}
					else
					{
						if (angle <= innerRatio2)
						{
							t.setColorRGBA_F(1, 0.25f, 0.25f, 1);
						}
						else
						{
							t.setColorRGBA_F(1, 0.75f, 0.75f, 1);
						}
					}
					t.addVertex(hwidth + X, hheight + Y - 45, 4);
				}
				else if (fdist >= midR2 && fdist < outerR)
				{
					double Y = (double) y / (double) resolution;
					double angle = Math.PI + Math.atan2(X, Y);
					if (angle <= outerRatio)
					{
						t.setColorRGBA_F(0, 0, 1, 1);
					}
					else
					{
						t.setColorRGBA_F(1, 0, 0, 1);
					}
					t.addVertex(hwidth + X, hheight + Y - 45, 4);
				}
			}
		}
		t.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
}
