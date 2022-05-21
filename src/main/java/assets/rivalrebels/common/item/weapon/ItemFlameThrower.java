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

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.HashSet;

import org.lwjgl.input.Keyboard;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityFlameBall;
import assets.rivalrebels.common.entity.EntityFlameBall1;
import assets.rivalrebels.common.entity.EntityFlameBall2;
import assets.rivalrebels.common.entity.EntityFlameBallGreen;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ItemFlameThrower extends ItemTool
{
	// HashMap<String, Entity> entities;
	public ItemFlameThrower()
	{
		super(1, ToolMaterial.EMERALD, new HashSet());
		maxStackSize = 1;
		// entities = new HashMap<String, Entity>();
		setCreativeTab(RivalRebels.rralltab);
	}
	
	//
	// @Override
	// public boolean isFull3D()
	// {
	// return true;
	// }
	
	// @Override
	// public EnumAction getItemUseAction(ItemStack par1ItemStack)
	// {
	// return EnumAction.bow;
	// }
	//
	// @Override
	// public int getMaxItemUseDuration(ItemStack par1ItemStack)
	// {
	// return 1024;
	// }
	//
	// @Override
	// public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	// {
	// if (player.capabilities.isCreativeMode || player.inventory.hasItem(RivalRebels.fuel) || RivalRebels.infiniteAmmo)
	// {
	// player.setItemInUse(item, getMaxItemUseDuration(item));
	// if (!player.capabilities.isCreativeMode && !RivalRebels.infiniteAmmo)
	// {
	// player.inventory.consumeInventoryItem(RivalRebels.fuel);
	// }
	// if (!world.isRemote)
	// {
	// EntityMagnet em = (EntityMagnet) entities.get(player.username);
	// if (em != null) em.setDead();
	// em = new EntityMagnet(world, player);
	// world.spawnEntityInWorld(em);
	// entities.put(player.username, em);
	// em.isBeingShot = true;
	// }
	// }
	// else if (!world.isRemote)
	// {
	// player.addChatMessage(new ChatComponentText("§cOut of batteries");
	// }
	// return item;
	// }
	//
	// @Override
	// public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
	// {
	// EntityMagnet em = (EntityMagnet) entities.get(player.username);
	// if (em == null) return;
	// em.shootingEntity = player;
	// // if (player.worldObj.isRemote && player.username.equals(Minecraft.getMinecraft().thePlayer.username))
	// // {
	// // em.rotationPitch = Minecraft.getMinecraft().thePlayer.rotationPitch;
	// // em.rotationYaw = Minecraft.getMinecraft().thePlayer.rotationYaw;
	// // em.posX = Minecraft.getMinecraft().thePlayer.posX;
	// // em.posY = Minecraft.getMinecraft().thePlayer.posY;
	// // em.posZ = Minecraft.getMinecraft().thePlayer.posZ;
	// // }
	// }
	//
	// @Override
	// public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count)
	// {
	// EntityMagnet em = (EntityMagnet) entities.get(player.username);
	// if (em == null) return;
	// em.isBeingShot = false;
	// em.setDead();
	// entities.remove(player.username);
	// }
	
	// public ItemFlameThrower(int par1)
	// {
	// super(par1);
	// maxStackSize = 1;
	// setCreativeTab(RivalRebels.instance.rralltab);
	// }
	//
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
		return 64;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(RivalRebels.fuel) || RivalRebels.infiniteAmmo)
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
			if (!par3EntityPlayer.capabilities.isCreativeMode && !RivalRebels.infiniteAmmo)
			{
				par3EntityPlayer.inventory.consumeInventoryItem(RivalRebels.fuel);
				if (getMode(par1ItemStack) != 2) par3EntityPlayer.inventory.consumeInventoryItem(RivalRebels.fuel);
				if (getMode(par1ItemStack) != 2) par3EntityPlayer.inventory.consumeInventoryItem(RivalRebels.fuel);
				if (getMode(par1ItemStack) == 0) par3EntityPlayer.inventory.consumeInventoryItem(RivalRebels.fuel);
				if (getMode(par1ItemStack) == 0) par3EntityPlayer.inventory.consumeInventoryItem(RivalRebels.fuel);
			}
			if (par1ItemStack.isItemEnchanted() && !par2World.isRemote)
			{
				par2World.spawnEntityInWorld(new EntityFlameBallGreen(par2World, par3EntityPlayer, (float) (Math.random() + 1.0f)));
			}
		}
		else if (!par2World.isRemote)
		{
			par3EntityPlayer.addChatMessage(new ChatComponentText("§cOut of fuel"));
		}
		if (message && par2World.isRemote)
		{
			par3EntityPlayer.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("RivalRebels.Orders")+" "+StatCollector.translateToLocal("RivalRebels.message.use")+" [R]."));
			message = false;
		}
		return par1ItemStack;
	}
	boolean message = true;
	
	@Override
	public void onUsingTick(ItemStack item, EntityPlayer entity, int par4)
	{
		World world = entity.worldObj;
		if (!world.isRemote)
		{
			if (entity.inventory.getCurrentItem() != null)
			{
				if (entity.inventory.getCurrentItem().getItem() == this)
				{
					if (world.rand.nextInt(10) == 0 && !entity.isInWater())
					{
						RivalRebelsSoundPlayer.playSound(entity, 8, 0, 0.03f);
						if (world.rand.nextInt(3) == 0 && !entity.isInWater())
						{
							RivalRebelsSoundPlayer.playSound(entity, 8, 1, 0.1f);
						}
					}
					if (!item.isItemEnchanted())
					{
						switch (getMode(item))
						{
							case 0:
								for (int i = 0; i < 4; i++) world.spawnEntityInWorld(new EntityFlameBall2(world, entity, (float) (Math.random() + 0.5f)));
							break;
							case 1:
								world.spawnEntityInWorld(new EntityFlameBall1(entity.worldObj, entity, 1));
							break;
							case 2:
								world.spawnEntityInWorld(new EntityFlameBall(world, entity, 1));
							break;
						}
					}
				}
			}
		}
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
	{
		if (item.getTagCompound() == null) item.stackTagCompound = new NBTTagCompound();
		if (!item.getTagCompound().getBoolean("isReady"))
		{
			item.getTagCompound().setBoolean("isReady", true);
			item.getTagCompound().setInteger("mode", 2);
		}
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			if (player.inventory.getCurrentItem() != null)
			{
				if (player.inventory.getCurrentItem().getItem() == this)
				{
					if (world.rand.nextInt(10) == 0 && !player.isInWater())
					{
						RivalRebelsSoundPlayer.playSound(player, 8, 0, 0.03f);
					}
				}
			}
		}
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.CLIENT) openGui(item, entity);
	}
	
	public void openGui(ItemStack item, Entity entity)
	{
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		if ((RivalRebels.altRkey?Keyboard.isKeyDown(Keyboard.KEY_F):Keyboard.isKeyDown(Keyboard.KEY_R)) && item == ((EntityPlayer) entity).inventory.getCurrentItem() && Minecraft.getMinecraft().currentScreen == null)
		{
			RivalRebels.proxy.flamethrowerGui(getMode(item));
		}
	}
	
	public int getMode(ItemStack item)
	{
		if (item.stackTagCompound == null) return 0;
		else return item.stackTagCompound.getInteger("mode");
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ae");
	}
}
