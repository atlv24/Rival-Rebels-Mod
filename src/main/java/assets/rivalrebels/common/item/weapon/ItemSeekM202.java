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
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityB83;
import assets.rivalrebels.common.entity.EntityHackB83;
import assets.rivalrebels.common.entity.EntityRocket;
import assets.rivalrebels.common.entity.EntitySeekB83;
import assets.rivalrebels.common.explosion.Explosion;

public class ItemSeekM202 extends ItemTool
{
	public ItemSeekM202()
	{
		super(1, ToolMaterial.EMERALD, new HashSet());
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
	
	/**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 90;
    }
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		if (p.capabilities.isCreativeMode || p.inventory.hasItem(RivalRebels.rocket) || RivalRebels.infiniteAmmo)
		{
			p.setItemInUse(i, getMaxItemUseDuration(i));
			if (!p.capabilities.isCreativeMode && !RivalRebels.infiniteAmmo)
			{
				p.inventory.consumeInventoryItem(RivalRebels.rocket);
			}
			RivalRebelsSoundPlayer.playSound(p, 23, 2, 0.4f);
			if (!w.isRemote)
			{
				if (i.getEnchantmentTagList() == null)
				{
					w.spawnEntityInWorld(new EntitySeekB83(w, p, 0.1F));
				}
				else
				{
					w.spawnEntityInWorld(new EntitySeekB83(w, p, 0.1F, 15.0f));
					w.spawnEntityInWorld(new EntitySeekB83(w, p, 0.1F, 7.5f));
					w.spawnEntityInWorld(new EntitySeekB83(w, p, 0.1F));
					w.spawnEntityInWorld(new EntitySeekB83(w, p, 0.1F, -7.5f));
					w.spawnEntityInWorld(new EntitySeekB83(w, p, 0.1F, -15.0f));
				}
			}
		}
		else if (!w.isRemote)
		{
			p.addChatMessage(new ChatComponentText("§cOut of ammunition"));
		}
		return i;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:bh");
	}
}
