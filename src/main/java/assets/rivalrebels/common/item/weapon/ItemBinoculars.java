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
package assets.rivalrebels.common.item.weapon;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.packet.LaptopEngagePacket;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;

public class ItemBinoculars extends Item
{
	private static ArrayList<TileEntityLaptop>	ltel	= new ArrayList<TileEntityLaptop>();
	static public int tx = -1;
	static public int ty = -1;
	static public int tz = -1;
	static public int lx = -1;
	static public int ly = -1;
	static public int lz = -1;
	static public int tasks = 0;
	static public int carpet = 0;
	static public float dist = 0;
	static public float distblock = 0;
	static public boolean hasLaptop = false;
	static public boolean tooClose = false;
	static public boolean tooFar = true;
	static public boolean ready = false;
	static public boolean c = false;
	static public boolean sc = false;
	static public int slot = -1;
	
	public ItemBinoculars()
	{
		super();
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 200;
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}
	
	float	zoom		= 30f;
	float 	fovset		= 0f;
	float	senset		= 0f;
	boolean	zoomed		= false;
	boolean	prevzoomed	= false;
	boolean	prevmclick;
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (entity instanceof EntityPlayer && side == Side.CLIENT && entity == Minecraft.getMinecraft().thePlayer)
		{
			EntityPlayer player = ((EntityPlayer) entity);
			boolean strike = isMousePressed() && !prevmclick;
			c ^= Keyboard.isKeyDown(Keyboard.KEY_C) && !sc;
			sc = Keyboard.isKeyDown(Keyboard.KEY_C);
			prevzoomed = zoomed;
			zoomed = ((Mouse.isButtonDown(1) && (player.inventory.getCurrentItem() == item || zoomed))) && !GameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindDrop) && Minecraft.getMinecraft().currentScreen == null;
			if (zoomed)
			{
				if (!prevzoomed)
				{
					fovset = Minecraft.getMinecraft().gameSettings.fovSetting;
					senset = Minecraft.getMinecraft().gameSettings.mouseSensitivity;
				}
				slot = -1;
				lx = -1;
				ly = -1;
				lz = -1;
				distblock = 130;
				hasLaptop = false;
				tasks = 0;
				carpet = 0;
				dist = 6;
				tooFar = true;
				tooClose = false;
				tx = -1;
				ty = -1;
				tz = -1;
				ready = false;
				for (int i = 0; i < 9; i++) if (player.inventory.mainInventory[i] == item)
				{
					player.inventory.currentItem = i;
					slot = i;
					break;
				}
				if (slot != -1)
				{
					zoom += (Mouse.getDWheel() * 0.01f);
					if (zoom < 10) zoom = 10;
					if (zoom > 67) zoom = 67;
					Minecraft.getMinecraft().gameSettings.fovSetting = zoom + (Minecraft.getMinecraft().gameSettings.fovSetting - zoom) * 0.85f;
					Minecraft.getMinecraft().gameSettings.mouseSensitivity = senset * MathHelper.sqrt_float(zoom) * 0.1f;
					
					if (Mouse.isButtonDown(1))
					{
						double cospitch = MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI);
						double sinpitch = MathHelper.sin(player.rotationPitch / 180.0F * (float) Math.PI);
						double cosyaw = MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI);
						double sinyaw = MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI);
						
						double dx = (-sinyaw * cospitch) * 130;
						double dz = (cosyaw * cospitch) * 130;
						double dy = (-sinpitch) * 130;
						
						Vec3 var17 = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
						Vec3 var3 = Vec3.createVectorHelper(player.posX + dx, player.posY + dy, player.posZ + dz);
						MovingObjectPosition MOP = world.rayTraceBlocks(var17, var3, false);
						
						TileEntityLaptop t = null;
						Iterator iter = ltel.iterator();
						double d = 100;
						while (iter.hasNext())
						{
							t = (TileEntityLaptop) iter.next();
							if (t.b2spirit > 0 || t.b2carpet > 0)
							{
								hasLaptop = true;
								double temp = player.getDistanceSq(t.xCoord + 0.5, t.yCoord + 0.5, t.zCoord + 0.5);
								if (temp < d)
								{
									d = temp;
									lx = t.xCoord;
									ly = t.yCoord;
									lz = t.zCoord;
								}
							}
						}
						if (MOP != null)
						{
							tx = MOP.blockX;
							ty = MOP.blockY;
							tz = MOP.blockZ;
							distblock = (float) player.getDistance(tx+0.5f, ty+0.5f, tz+0.5f);
							tooFar = false;
							if (t != null)
							{
								tasks = t.b2spirit;
								carpet = t.b2carpet;
								dist = (float) Math.sqrt(d);
								int XX = 11;
								int ZZ = 10;
								if (t.rrteam == RivalRebelsTeam.OMEGA)
								{
									XX = (MOP.blockX - RivalRebels.round.oObjx);
									ZZ = (MOP.blockZ - RivalRebels.round.oObjz);
								}
								if (t.rrteam == RivalRebelsTeam.SIGMA)
								{
									XX = (MOP.blockX - RivalRebels.round.sObjx);
									ZZ = (MOP.blockZ - RivalRebels.round.sObjz);
								}
								tooClose = (tx-lx)*(tx-lx)+(tz-lz)*(tz-lz) < 625;
								if (!tooClose && XX*XX+ZZ*ZZ > 200)
								{
									ready = true;
									if (strike)
									{
										PacketDispatcher.packetsys.sendToServer(new LaptopEngagePacket(tx, ty, tz, lx, ly, lz, c));
									}
								}
							}
						}
					}
				}
			}
			else if (prevzoomed)
			{
				Minecraft.getMinecraft().gameSettings.fovSetting = fovset;
				Minecraft.getMinecraft().gameSettings.mouseSensitivity = senset;
			}
			prevmclick = isMousePressed();
		}
	}
	
	public boolean isMousePressed()
	{
		return ((RivalRebels.altRkey?Keyboard.isKeyDown(Keyboard.KEY_F):Keyboard.isKeyDown(Keyboard.KEY_R)) && RivalRebels.rtarget) || (Mouse.isButtonDown(0) && RivalRebels.lctarget);
	}
	
	public static void add(TileEntityLaptop tel)
	{
		if (!ltel.contains(tel))
		{
			ltel.add(tel);
		}
	}
	
	public static void remove(TileEntityLaptop tel)
	{
		ltel.remove(tel);
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:bb");
	}
}
