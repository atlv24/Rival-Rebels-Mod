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
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.HashSet;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityB83;
import assets.rivalrebels.common.entity.EntityHackB83;
import assets.rivalrebels.common.entity.EntityRocket;
import assets.rivalrebels.common.explosion.Explosion;

public class ItemHackM202 extends ItemTool
{
	public ItemHackM202()
	{
		super(1, ToolMaterial.EMERALD, new HashSet());
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public int getItemEnchantability()
	{
		return 100;
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer p)
	{
		p.inventory.mainInventory[p.inventory.currentItem] = null;
		if (!par2World.isRemote)
		{
			par2World.spawnEntityInWorld(new EntityHackB83(par2World, p.posX, p.posY, p.posZ, -p.rotationYawHead, p.rotationPitch, par1ItemStack.getEnchantmentTagList() != null));
		}
		RivalRebelsSoundPlayer.playSound(p, 23, 2, 0.4f);
		new Explosion(par2World, p.posX, p.posY, p.posZ, 2, true, false, RivalRebelsDamageSource.flare);
		return par1ItemStack;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:bg");
	}
}
