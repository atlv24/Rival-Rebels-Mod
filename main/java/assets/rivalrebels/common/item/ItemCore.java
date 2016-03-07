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

import net.minecraft.item.Item;
import assets.rivalrebels.RivalRebels;

public class ItemCore extends Item
{
	public float	timemult	= 0;
	
	public ItemCore()
	{
		super();
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
}