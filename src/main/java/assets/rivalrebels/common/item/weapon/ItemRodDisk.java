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
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityRoddiskLeader;
import assets.rivalrebels.common.entity.EntityRoddiskOfficer;
import assets.rivalrebels.common.entity.EntityRoddiskRebel;
import assets.rivalrebels.common.entity.EntityRoddiskRegular;
import assets.rivalrebels.common.entity.EntityRoddiskRep;
import assets.rivalrebels.common.round.RivalRebelsPlayer;

public class ItemRodDisk extends Item
{
	public ItemRodDisk()
	{
		super();
		this.maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 300;
	}
	
	boolean pass = false;
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!pass)
		{
			par3EntityPlayer.addChatMessage(new ChatComponentText("Password?"));
			pass = true;
		}
		par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
		if (RivalRebels.round.rrplayerlist.getForName(par3EntityPlayer.getCommandSenderName()).rrrank.id > 1) RivalRebelsSoundPlayer.playSound(par3EntityPlayer, 6, 2);
		else RivalRebelsSoundPlayer.playSound(par3EntityPlayer, 7, 2);
		return par1ItemStack;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack item, World world, EntityPlayer player, int i)
	{
		if (!world.isRemote)
		{
			if (!player.capabilities.isCreativeMode) player.inventory.consumeInventoryItem(this);
			int rank = RivalRebels.round.rrplayerlist.getForName(player.getCommandSenderName()).rrrank.id;
			//float f = (getMaxItemUseDuration(item) - i) / 20.0F;
			//f = (f * f + f * 2) * 0.33333f;
			//if (f > 1.0F) f = 1.0F;
			//f *= 1.0f-rank*0.1f;
			//f += 0.3f;
			if (rank == 0)
			{
				Entity entity = new EntityRoddiskRegular(world, player, 1);
				world.spawnEntityInWorld(entity);
				RivalRebelsSoundPlayer.playSound(player, 7, 3);
			}
			else if (rank == 1)
			{
				Entity entity = new EntityRoddiskRebel(world, player, 1.1f);
				world.spawnEntityInWorld(entity);
				RivalRebelsSoundPlayer.playSound(player, 7, 3);
			}
			else if (rank == 2)
			{
				Entity entity = new EntityRoddiskOfficer(world, player, 1.2f);
				world.spawnEntityInWorld(entity);
				RivalRebelsSoundPlayer.playSound(player, 6, 3);
			}
			else if (rank == 3)
			{
				Entity entity = new EntityRoddiskLeader(world, player, 4f);
				world.spawnEntityInWorld(entity);
				RivalRebelsSoundPlayer.playSound(player, 6, 3);
			}
			else if (rank == 4)
			{
				Entity entity = new EntityRoddiskRep(world, player, 4f);
				world.spawnEntityInWorld(entity);
				RivalRebelsSoundPlayer.playSound(player, 6, 3);
			}
		}
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:as");
	}
}
