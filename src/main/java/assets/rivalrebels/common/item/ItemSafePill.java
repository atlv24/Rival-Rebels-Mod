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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;

public class ItemSafePill extends Item
{
	public ItemSafePill()
	{
		super();
		maxStackSize = 6;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		player.setItemInUse(item, getMaxItemUseDuration(item));
		if (!world.isRemote)
		{
			player.addChatMessage(new ChatComponentText("§7[§6Status§7]§e Regenerating..."));
			RivalRebelsSoundPlayer.playSound(player, 15, 1);
			RivalRebelsSoundPlayer.playSound(player, 28, 18);
			world.playSoundAtEntity(player, "mob.magmacube.jump", 1.0F, 1.0F);
			world.playSoundAtEntity(player, "mob.ghast.scream", 1.0F, 1.0F);
			((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.blindness.id, 10, 20));
			player.getFoodStats().addStats(10, 200);
			player.heal(10);
			player.inventory.consumeInventoryItem(item.getItem());
		}
		return item;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.eat;
	}
	
	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 32;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ak");
	}
}