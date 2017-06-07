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

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.HashSet;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityLaserBurst;

public class ItemAstroBlaster extends ItemTool
{
	boolean	isA	= true;
	
	public ItemAstroBlaster()
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
		return 2000;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(RivalRebels.redrod) || RivalRebels.infiniteAmmo)
		{
			if (player.worldObj.isRemote) item.setRepairCost(1);
			player.setItemInUse(item, getMaxItemUseDuration(item));
			RivalRebelsSoundPlayer.playSound(player, 12, 0, 0.7f, 0.7f);
		}
		else if (!world.isRemote)
		{
			player.addChatMessage(new ChatComponentText("§cNot enough redstone rods"));
		}
		return item;
	}
	
	@Override
	public void onUsingTick(ItemStack is, EntityPlayer player, int count)
	{
		if (count < 1980 && !player.worldObj.isRemote)
		{
			
			if (!player.capabilities.isCreativeMode && !RivalRebels.infiniteAmmo)
			{
				int item = -1;
				for (int i = 0; i < 36; i++)
				{
					if (player.inventory.mainInventory[i] != null)
					{
						if (player.inventory.mainInventory[i].getItem() == RivalRebels.redrod)
						{
							item = i;
						}
					}
				}
				if (item != -1)
				{
					player.inventory.mainInventory[item].damageItem(1, player);
					if (player.inventory.mainInventory[item].getItemDamage() == player.inventory.mainInventory[item].getMaxDamage())
					{
						player.inventory.decrStackSize(item, 1);
						player.inventory.addItemStackToInventory(new ItemStack(RivalRebels.emptyrod, 1));
					}
				}
				else
				{
					return;
				}
			}
			
			if (isA) RivalRebelsSoundPlayer.playSound(player, 2, 2, 0.5f, 0.3f);
			else RivalRebelsSoundPlayer.playSound(player, 2, 3, 0.4f, 1.7f);
			
			isA = !isA;
			player.worldObj.spawnEntityInWorld(new EntityLaserBurst(player.worldObj, player, is.getEnchantmentTagList() != null && is.getEnchantmentTagList().tagCount() > 0));
		}
		else if (player.worldObj.isRemote)
		{
			is.setRepairCost((2000 - count) + 1);
		}
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack item, World world, EntityPlayer player, int par4)
	{
		if (world.isRemote) item.setRepairCost(0);
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ab");
	}
}