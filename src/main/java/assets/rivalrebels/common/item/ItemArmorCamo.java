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
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import assets.rivalrebels.RivalRebels;

public class ItemArmorCamo extends ItemArmor

{
	private static final int	maxDamageArray[]	= { 11, 16, 15, 13 };
	public final int			armorType;
	public final int			damageReduceAmount;
	public final int			renderIndex;
	public int					team;
	
	public ItemArmorCamo(ArmorMaterial par2EnumArmorMaterial, int par3, int par4, int team)
	{
		super(par2EnumArmorMaterial, par3, par4);
		armorType = par4;
		renderIndex = par3;
		damageReduceAmount = maxDamageArray[par4];
		setCreativeTab(RivalRebels.rrarmortab);
		setMaxDamage(par2EnumArmorMaterial.getDurability(par4) * 2);
		maxStackSize = 1;
		this.team = team;
	}
	
	static int[] getMaxDamageArray()
	{
		return maxDamageArray;
	}
	
	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String layer)
	{
		if (itemstack.getItem() == RivalRebels.camohat || itemstack.getItem() == RivalRebels.camoshirt || itemstack.getItem() == RivalRebels.camoshoes)
		{
			return "rivalrebels:textures/armors/c.png";
		}
		if (itemstack.getItem() == RivalRebels.camopants)
		{
			return "rivalrebels:textures/armors/d.png";
		}
		if (itemstack.getItem() == RivalRebels.camohat2 || itemstack.getItem() == RivalRebels.camoshirt2 || itemstack.getItem() == RivalRebels.camoshoes2)
		{
			return "rivalrebels:textures/armors/a.png";
		}
		if (itemstack.getItem() == RivalRebels.camopants2)
		{
			return "rivalrebels:textures/armors/b.png";
		}
		return null;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		String str = "";
		if (team == 0) str = "o";
		if (team == 1) str = "s";
		if (armorType == 0) itemIcon = iconregister.registerIcon("RivalRebels:" + str + "h");
		if (armorType == 1) itemIcon = iconregister.registerIcon("RivalRebels:" + str + "v");
		if (armorType == 2) itemIcon = iconregister.registerIcon("RivalRebels:" + str + "p");
		if (armorType == 3) itemIcon = iconregister.registerIcon("RivalRebels:" + str + "b");
	}
}