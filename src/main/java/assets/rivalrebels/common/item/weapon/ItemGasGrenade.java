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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityGasGrenade;

public class ItemGasGrenade extends Item
{
	public ItemGasGrenade()
	{
		super();
		maxStackSize = 6;
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
	
	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 75;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int i)
	{
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(RivalRebels.gasgrenade) || RivalRebels.infiniteGrenades)
		{
			float f = (getMaxItemUseDuration(itemstack) - i) / 20.0F;
			f = (f * f + f * 2) * 0.3333f;
			if (f > 1.0F) f = 1.0F;
			EntityGasGrenade entitysuperarrow = new EntityGasGrenade(world, player, 0.3f + f * 0.5f);
			if (!player.capabilities.isCreativeMode)
			{
				player.inventory.consumeInventoryItem(RivalRebels.gasgrenade);
			}
			RivalRebelsSoundPlayer.playSound(player, 4, 3, 1, 0.9f);
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(entitysuperarrow);
				entitysuperarrow.setPosition(entitysuperarrow.posX, entitysuperarrow.posY - 0.05, entitysuperarrow.posZ);
			}
		}
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
	{
		player.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
		world.playSoundAtEntity(player, "mob.slimeattack", 1.0F, 1.0F);
		return par1ItemStack;
		
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		int time = 75 - count;
		if (time == 15 || time == 30 || time == 45 || time == 60)
		{
			player.worldObj.playSoundAtEntity(player, "note.snare", 1.0F, 1.0F);
		}
		if (time == 75)
		{
			player.worldObj.playSoundAtEntity(player, "note.snare", 1.0F, 1.0F);
			player.addPotionEffect(new PotionEffect(Potion.poison.id, 80, 1));
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 80, 0));
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 80, 0));
			if (!player.capabilities.isCreativeMode)
			{
				player.inventory.consumeInventoryItem(RivalRebels.gasgrenade);
			}
		}
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ah");
	}
}