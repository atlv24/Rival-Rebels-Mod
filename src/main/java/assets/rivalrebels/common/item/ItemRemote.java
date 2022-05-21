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
package assets.rivalrebels.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.block.trap.BlockRemoteCharge;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;

public class ItemRemote extends Item
{
	int	RCpX;
	int	RCpY;
	int	RCpZ;
	
	public ItemRemote()
	{
		super();
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (player.worldObj.getBlock(RCpX, RCpY + 1, RCpZ) == RivalRebels.remotecharge && player.isSneaking())
		{
			player.swingItem();
			RivalRebelsSoundPlayer.playSound(player, 22, 3);
			BlockRemoteCharge.explode(world, RCpX, RCpY + 1, RCpZ);
		}
		return item;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (((player.capabilities.isCreativeMode && world.getBlock(x, y + 1, z) == Blocks.air || player.inventory.hasItem(Item.getItemFromBlock(RivalRebels.remotecharge)) && world.getBlock(x, y + 1, z) == Blocks.air)) && !player.isSneaking())
		{
			RivalRebelsSoundPlayer.playSound(player, 22, 2);
			player.swingItem();
			if (!world.isRemote)
			{
				player.addChatMessage(new ChatComponentText("§7[§4Orders§7] §cShift-click (Sneak) to detonate."));
			}
			RCpX = x;
			RCpY = y;
			RCpZ = z;
			player.inventory.consumeInventoryItem(Item.getItemFromBlock(RivalRebels.remotecharge));
			world.setBlock(x, y + 1, z, RivalRebels.remotecharge);
			return false;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:am");
	}
}