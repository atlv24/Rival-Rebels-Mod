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
import net.minecraft.item.ItemStack;
import assets.rivalrebels.RivalRebels;

public class ItemRodRedstone extends ItemRod
{
	public ItemRodRedstone()
	{
		super();
		maxStackSize = 1;
		power = 300000;
		this.setMaxDamage(256);
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public boolean isItemTool(ItemStack is)
	{
		return true;
	}
	
	@Override
	public boolean isDamageable()
	{
		return true;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:al");
	}
}