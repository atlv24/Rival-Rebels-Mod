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

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsTeam;

public class ItemChip extends Item
{
	public ItemChip()
	{
		super();
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int count, boolean flag)
	{
		if (item.getTagCompound() == null) item.stackTagCompound = new NBTTagCompound();
		if (RivalRebels.round.isStarted() && !item.getTagCompound().getBoolean("isReady") && entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			item.getTagCompound().setString("username", player.getCommandSenderName());
			item.getTagCompound().setInteger("team", RivalRebels.round.rrplayerlist.getForName(player.getCommandSenderName()).rrteam.ordinal());
			item.getTagCompound().setBoolean("isReady", true);
		}
	}
	
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
	{
		if (item.stackTagCompound != null)
		{
			list.add(RivalRebelsTeam.getForID(item.stackTagCompound.getInteger("team")).name());
			list.add(item.stackTagCompound.getString("username"));
		}
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:bd");
	}
}