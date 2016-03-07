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

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;

public class ItemRodNuclear extends ItemRod
{
	public ItemRodNuclear()
	{
		super();
		power = 3000000;
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
	{
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			Random rand = new Random();
			if (player.inventory.getStackInSlot(player.inventory.currentItem) != null)
			{
				if (player.inventory.getStackInSlot(player.inventory.currentItem).getItem() == RivalRebels.nuclearelement && rand.nextInt(16) == 0 && !player.capabilities.isCreativeMode)
				{
					player.attackEntityFrom(RivalRebelsDamageSource.radioactivepoisoning, rand.nextInt(4));
				}
			}
		}
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:av");
	}
}