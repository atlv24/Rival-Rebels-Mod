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
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;

public class ItemExPill extends Item
{
	public ItemExPill()
	{
		super();
		maxStackSize = 6;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		RivalRebelsSoundPlayer.playSound(player, 15, 0);
		RivalRebelsSoundPlayer.playSound(player, 28, 18, 1.0f, 0.6f);
		player.setItemInUse(item, getMaxItemUseDuration(item));
		if (!world.isRemote)
		{
			int rand = world.rand.nextInt(100);
			if (rand >= 40)
			{
				player.addChatMessage(new ChatComponentText("§7[§6Status§7]§e The experiment turned out a success."));
				world.playSoundAtEntity(player, "mob.magmacube.jump", 1.0F, 1.0F);
				world.playSoundAtEntity(player, "mob.ghast.scream", 1.0F, 1.0F);
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.blindness.id, 30, 20));
				player.getFoodStats().addStats(20, 200);
				player.heal(20);
			}
			else if (rand >= 30)
			{
				player.addChatMessage(new ChatComponentText("§7[§6Status§7]§e Begrüßen Sie den Uber-Soldat."));
				world.playSoundAtEntity(player, "mob.magmacube.jump", 1.0F, 1.0F);
				world.playSoundAtEntity(player, "mob.ghast.scream", 1.0F, 1.0F);
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.blindness.id, 30, 20));
				player.getFoodStats().addStats(20, 200);
				player.heal(20);
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.resistance.id, 450, 20));
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.damageBoost.id, 500, 20));
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.jump.id, 300, 2));
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.digSpeed.id, 500, 02));
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 550, 2));
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 800, 20));
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.fireResistance.id, 800, 20));
			}
			else if (rand >= 20)
			{
				player.addChatMessage(new ChatComponentText("§7[§6Status§7]§e The test subject has perished."));
				world.playSoundAtEntity(player, "mob.magmacube.jump", 1.0F, 1.0F);
				world.playSoundAtEntity(player, "mob.ghast.scream", 1.0F, 1.0F);
				player.attackEntityFrom(RivalRebelsDamageSource.cyanide, 2000);
			}
			else
			{
				player.addChatMessage(new ChatComponentText("§7[§6Status§7]§e Unexpected results have occurred."));
				world.playSoundAtEntity(player, "mob.magmacube.jump", 1.0F, 1.0F);
				world.playSoundAtEntity(player, "mob.ghast.scream", 1.0F, 1.0F);
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.confusion.id, 1500, 20));
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.blindness.id, 1500, 20));
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1500, 20));
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 1500, 20));
				((EntityLivingBase) player).addPotionEffect(new PotionEffect(Potion.weakness.id, 1500, 20));
			}
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
		itemIcon = iconregister.registerIcon("RivalRebels:ai");
	}
}