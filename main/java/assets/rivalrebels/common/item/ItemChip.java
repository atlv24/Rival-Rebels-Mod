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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.block.autobuilds.BlockAutoTemplate;
import assets.rivalrebels.common.command.CommandHotPotato;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.packet.TextPacket;
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
	public boolean onItemUseFirst(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int m, float hitX, float hitZ, float hitY)
	{
		player.swingItem();
		if (!world.isRemote)
		{
			if (world.getBlock(x, y, z) == RivalRebels.buildrhodes)
			{
				world.setBlock(x-1, y, z, RivalRebels.steel);
				world.setBlock(x+1, y, z, RivalRebels.steel);
				world.setBlock(x, y+1, z, RivalRebels.conduit);
				world.setBlock(x-1, y+1, z, RivalRebels.steel);
				world.setBlock(x+1, y+1, z, RivalRebels.steel);
				world.setBlock(x, y+2, z, RivalRebels.steel);
				world.setBlock(x-1, y+2, z, RivalRebels.steel);
				world.setBlock(x+1, y+2, z, RivalRebels.steel);
				if (world.getBlock(x, y-1, z) == RivalRebels.buildrhodes)
				{
					world.setBlock(x, y, z, RivalRebels.conduit);
					world.setBlock(x, y-1, z, RivalRebels.rhodesactivator);
					world.setBlock(x-1, y-1, z, RivalRebels.steel);
					world.setBlock(x+1, y-1, z, RivalRebels.steel);
				}
				else
				{
					world.setBlock(x, y, z, RivalRebels.rhodesactivator);
				}
				return true;
			}
		}
		return false;
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