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
import assets.rivalrebels.common.entity.EntityBomb;
import assets.rivalrebels.common.entity.EntityRocket;

public class ItemRPG extends ItemTool
{
	public ItemRPG()
	{
		super(1, ToolMaterial.EMERALD, new HashSet());
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
		return 0x00090;
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(RivalRebels.rocket) || RivalRebels.infiniteAmmo)
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
			if (!par2World.isRemote && !par3EntityPlayer.capabilities.isCreativeMode && !RivalRebels.infiniteAmmo)
			{
				par3EntityPlayer.inventory.consumeInventoryItem(RivalRebels.rocket);
			}
			if (par1ItemStack.getEnchantmentTagList() == null) RivalRebelsSoundPlayer.playSound(par3EntityPlayer, 23, 2, 0.4f);
			else RivalRebelsSoundPlayer.playSound(par3EntityPlayer, 10, 4, 1.0f);
			if (!par2World.isRemote)
			{
				if (par1ItemStack.getEnchantmentTagList() == null) par2World.spawnEntityInWorld(new EntityRocket(par2World, (EntityPlayer) par3EntityPlayer, 0.1F));
				else par2World.spawnEntityInWorld(new EntityBomb(par2World, (EntityPlayer) par3EntityPlayer, 0.1F));
			}
		}
		else if (!par2World.isRemote)
		{
			par3EntityPlayer.addChatMessage(new ChatComponentText("§cOut of ammunition"));
		}
		return par1ItemStack;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:aq");
	}
}
