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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.HashSet;

import org.lwjgl.input.Keyboard;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityRaytrace;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ItemTesla extends ItemTool
{
	public ItemTesla()
	{
		super(1, ToolMaterial.EMERALD, new HashSet());
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public int getItemEnchantability()
	{
		return 100;
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
		return 20;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		int degree = getDegree(item);
		float chance = Math.abs(degree - 90) / 90f;
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(RivalRebels.battery) || RivalRebels.infiniteAmmo)
		{
			if (!player.capabilities.isCreativeMode && !RivalRebels.infiniteAmmo)
			{
				player.inventory.consumeInventoryItem(RivalRebels.battery);
				if (chance > 0.33333) player.inventory.consumeInventoryItem(RivalRebels.battery);
				if (chance > 0.66666) player.inventory.consumeInventoryItem(RivalRebels.battery);
			}
			player.setItemInUse(item, getMaxItemUseDuration(item));
		}
		else if (!world.isRemote)
		{
			player.addChatMessage(new ChatComponentText("§cOut of batteries"));
		}
		if (message && world.isRemote)
		{
			player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("RivalRebels.Orders")+" "+StatCollector.translateToLocal("RivalRebels.message.use")+" [R]."));
			message = false;
		}
		return item;
	}
	boolean message = true;
	
	@Override
	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
	 * update it's contents.
	 */
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
	{
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		if ((RivalRebels.altRkey?Keyboard.isKeyDown(Keyboard.KEY_F):Keyboard.isKeyDown(Keyboard.KEY_R)) && item == ((EntityPlayer) entity).inventory.getCurrentItem() && Minecraft.getMinecraft().currentScreen == null)
		{
			RivalRebels.proxy.teslaGui(getDegree(item));
		}
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		if (player.isWet() && !player.capabilities.isCreativeMode)
		{
			player.attackEntityFrom(RivalRebelsDamageSource.electricity, 2);
		}
		World world = player.worldObj;
		if (player.worldObj.rand.nextInt(10) == 0) RivalRebelsSoundPlayer.playSound(player, 25, 1);
		
		int degree = getDegree(stack);
		float chance = Math.abs(degree - 90) / 90f;
		if (degree - 90 > 0) chance /= 10f;
		
		float dist = 7 + (1 - (degree / 180f)) * 73;
		
		float randomness = degree / 720f;
		
		int num = (degree / 25) + 1;
		
		if (!world.isRemote) for (int i = 0; i < num; i++)
			world.spawnEntityInWorld(new EntityRaytrace(world, player, dist, randomness, chance, !stack.isItemEnchanted()));
	}
	
	public int getDegree(ItemStack item)
	{
		if (item.stackTagCompound == null) return 0;
		else return item.stackTagCompound.getInteger("dial") + 90;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ax");
	}
}
