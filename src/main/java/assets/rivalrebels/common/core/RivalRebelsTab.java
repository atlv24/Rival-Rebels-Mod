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
package assets.rivalrebels.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import assets.rivalrebels.RivalRebels;

public class RivalRebelsTab extends CreativeTabs
{
	private String	name;
	private int		icon;
	
	public RivalRebelsTab(String name, int icon)
	{
		super(name);
		this.name = name;
		this.icon = icon;
	}
	
	@Override
	public Item getTabIconItem()
	{
		if (icon == 0) return RivalRebels.nuclearelement;
		else return RivalRebels.hydrod;
	}
	
	@Override
	public String getTranslatedTabLabel()
	{
		return this.name;
	}
	
	@Override
	public String getTabLabel()
	{
		return this.name;
	}
}
