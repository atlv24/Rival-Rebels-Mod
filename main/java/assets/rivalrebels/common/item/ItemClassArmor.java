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
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import assets.rivalrebels.RivalRebels;

public class ItemClassArmor extends ItemArmor

{
	private static final int	maxDamageArray[]	= { 0, 0, 0, 0 };
	
	/**
	 * Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
	 */
	public final int			armorType;
	
	/** Holds the amount of damage that the armor reduces at full durability. */
	public final int			damageReduceAmount;
	
	/**
	 * Used on RenderPlayer to select the correspondent armor to be rendered on the player: 0 is cloth, 1 is chain, 2 is iron, 3 is diamond and 4 is gold.
	 */
	public final int			renderIndex;
	
	public int					team;
	public int					stateclass;
	
	/** The EnumArmorMaterial used for this ItemArmor */
	private final ArmorMaterial	material;
	
	public ItemClassArmor(ArmorMaterial par2EnumArmorMaterial, int par3, int par4, int team, int stateclass)
	{
		super(par2EnumArmorMaterial, par3, par4);
		material = par2EnumArmorMaterial;
		armorType = par4;
		renderIndex = par3;
		damageReduceAmount = material.getDamageReductionAmount(par4);
		setCreativeTab(RivalRebels.rrarmortab);
		setMaxDamage(par2EnumArmorMaterial.getDurability(par4));
		maxStackSize = 1;
		this.team = team;
		this.stateclass = stateclass;
		for (int i = 0; i < maxDamageArray.length; i++)
		{
			maxDamageArray[i] = material.getDamageReductionAmount(i);
		}
	}
	
	/**
	 * Returns the 'max damage' factor array for the armor, each piece of armor have a durability factor (that gets multiplied by armor material factor)
	 */
	static int[] getMaxDamageArray()
	{
		return maxDamageArray;
	}
	
	@Override
	public String getArmorTexture(ItemStack item, Entity entity, int slot, String type)
	{
		Item itemstack = item.getItem();
		if (itemstack == RivalRebels.orebelhelmet) return "rivalrebels:textures/armors/l.png";
		if (itemstack == RivalRebels.orebelchest) return "rivalrebels:textures/armors/l.png";
		if (itemstack == RivalRebels.orebelpants) return "rivalrebels:textures/armors/k.png";
		if (itemstack == RivalRebels.orebelboots) return "rivalrebels:textures/armors/l.png";
		if (itemstack == RivalRebels.onukerhelmet) return "rivalrebels:textures/armors/i.png";
		if (itemstack == RivalRebels.onukerchest) return "rivalrebels:textures/armors/i.png";
		if (itemstack == RivalRebels.onukerpants) return "rivalrebels:textures/armors/k.png";
		if (itemstack == RivalRebels.onukerboots) return "rivalrebels:textures/armors/i.png";
		if (itemstack == RivalRebels.ointelhelmet) return "rivalrebels:textures/armors/g.png";
		if (itemstack == RivalRebels.ointelchest) return "rivalrebels:textures/armors/g.png";
		if (itemstack == RivalRebels.ointelpants) return "rivalrebels:textures/armors/k.png";
		if (itemstack == RivalRebels.ointelboots) return "rivalrebels:textures/armors/g.png";
		if (itemstack == RivalRebels.ohackerhelmet) return "rivalrebels:textures/armors/e.png";
		if (itemstack == RivalRebels.ohackerchest) return "rivalrebels:textures/armors/e.png";
		if (itemstack == RivalRebels.ohackerpants) return "rivalrebels:textures/armors/k.png";
		if (itemstack == RivalRebels.ohackerboots) return "rivalrebels:textures/armors/e.png";
		if (itemstack == RivalRebels.srebelhelmet) return "rivalrebels:textures/armors/m.png";
		if (itemstack == RivalRebels.srebelchest) return "rivalrebels:textures/armors/m.png";
		if (itemstack == RivalRebels.srebelpants) return "rivalrebels:textures/armors/n.png";
		if (itemstack == RivalRebels.srebelboots) return "rivalrebels:textures/armors/m.png";
		if (itemstack == RivalRebels.snukerhelmet) return "rivalrebels:textures/armors/j.png";
		if (itemstack == RivalRebels.snukerchest) return "rivalrebels:textures/armors/j.png";
		if (itemstack == RivalRebels.snukerpants) return "rivalrebels:textures/armors/n.png";
		if (itemstack == RivalRebels.snukerboots) return "rivalrebels:textures/armors/j.png";
		if (itemstack == RivalRebels.sintelhelmet) return "rivalrebels:textures/armors/h.png";
		if (itemstack == RivalRebels.sintelchest) return "rivalrebels:textures/armors/h.png";
		if (itemstack == RivalRebels.sintelpants) return "rivalrebels:textures/armors/n.png";
		if (itemstack == RivalRebels.sintelboots) return "rivalrebels:textures/armors/h.png";
		if (itemstack == RivalRebels.shackerhelmet) return "rivalrebels:textures/armors/f.png";
		if (itemstack == RivalRebels.shackerchest) return "rivalrebels:textures/armors/f.png";
		if (itemstack == RivalRebels.shackerpants) return "rivalrebels:textures/armors/n.png";
		if (itemstack == RivalRebels.shackerboots) return "rivalrebels:textures/armors/f.png";
		return "";
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		String str = "";
		if (team == 0) str = "o";
		if (team == 1) str = "s";
		if (stateclass == 0) str = "r" + str;
		if (stateclass == 1) str = "n" + str;
		if (stateclass == 2) str = "i" + str;
		if (stateclass == 3) str = "h" + str;
		if (armorType == 0) itemIcon = iconregister.registerIcon("rivalrebels:" + str + "h");
		if (armorType == 1) itemIcon = iconregister.registerIcon("rivalrebels:" + str + "c");
		if (armorType == 2) itemIcon = iconregister.registerIcon("rivalrebels:" + str + "p");
		if (armorType == 3) itemIcon = iconregister.registerIcon("rivalrebels:" + str + "b");
	}
}