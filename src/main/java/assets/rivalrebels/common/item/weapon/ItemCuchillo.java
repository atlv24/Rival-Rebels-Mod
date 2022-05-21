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

import java.util.HashSet;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityCuchillo;
import assets.rivalrebels.common.entity.EntityRhodes;

public class ItemCuchillo extends ItemTool
{
	public ItemCuchillo()
	{
		super(1, ToolMaterial.IRON, new HashSet());
		maxStackSize = 5;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}
	
	@Override
	public boolean isFull3D()
	{
		return false;
	}
	
	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int i)
	{
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(RivalRebels.knife))
		{
			float f = (getMaxItemUseDuration(itemstack) - i) / 20.0F;
			f = (f * f + f * 2) * 0.3333f;
			if (f < 0.1D) return;
			if (f > 1.0F) f = 1.0F;
			if (!player.capabilities.isCreativeMode) player.inventory.decrStackSize(player.inventory.currentItem, 1);
			RivalRebelsSoundPlayer.playSound(player, 4, 3);
			if (!world.isRemote) world.spawnEntityInWorld(new EntityCuchillo(world, player, 0.5f + f));
		}
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
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ad");
	}
}