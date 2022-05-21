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
import net.minecraft.entity.Entity;
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
import assets.rivalrebels.common.entity.EntityPlasmoid;

public class ItemPlasmaCannon extends ItemTool
{
	public ItemPlasmaCannon()
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
		return 64;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(RivalRebels.hydrod) || RivalRebels.infiniteAmmo)
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
			if (!par3EntityPlayer.capabilities.isCreativeMode && !RivalRebels.infiniteAmmo)
			{
				int item = -1;
				for (int i = 0; i < 36; i++)
				{
					if (par3EntityPlayer.inventory.mainInventory[i] != null)
					{
						if (par3EntityPlayer.inventory.mainInventory[i].getItem() == RivalRebels.hydrod)
						{
							item = i;
						}
					}
				}
				if (item != -1)
				{
					par3EntityPlayer.inventory.mainInventory[item].damageItem(1, par3EntityPlayer);
					if (par3EntityPlayer.inventory.mainInventory[item].getItemDamage() == par3EntityPlayer.inventory.mainInventory[item].getMaxDamage())
					{
						par3EntityPlayer.inventory.decrStackSize(item, 1);
						par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(RivalRebels.emptyrod, 1));
					}
					par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
				}
				else
				{
					return par1ItemStack;
				}
			}
			RivalRebelsSoundPlayer.playSound(par3EntityPlayer, 16, 1, 0.25f);
		}
		else if (!par2World.isRemote)
		{
			par3EntityPlayer.addChatMessage(new ChatComponentText("§cOut of Hydrogen"));
		}
		return par1ItemStack;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack item, World world, EntityPlayer player, int i)
	{
		if (!player.worldObj.isRemote)
		{
			float f = (getMaxItemUseDuration(item) - i) / 20.0F;
			f = (f * f + f * 2) * 0.3333f;
			if (f > 1.0F) f = 1.0F;
			f+=0.2f;
			RivalRebelsSoundPlayer.playSound(player, 16, 2);
			Entity entity = new EntityPlasmoid(player.worldObj, player, f+0.5f, item.getEnchantmentTagList() != null);
			player.worldObj.spawnEntityInWorld(entity);
		}
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ao");
	}
}
