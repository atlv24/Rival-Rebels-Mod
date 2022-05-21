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

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.entity.EntityLaptop;

public class ItemLaptop extends Item
{
	public ItemLaptop()
	{
		super();
		maxStackSize = 4;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (!par3World.isRemote)
		{
			par2EntityPlayer.addChatMessage(new ChatComponentText("Out of service"));
			Block var11 = par3World.getBlock(par4, par5, par6);
			par4 += Facing.offsetsXForSide[par7];
			par5 += Facing.offsetsYForSide[par7];
			par6 += Facing.offsetsZForSide[par7];
			double var12 = 0.0D;
			
			if (par7 == 1 && var11 != null && var11.getRenderType() == 11)
			{
				var12 = 0.5D;
			}
			
			EntityLaptop laptop = new EntityLaptop(par3World);
			
			laptop.setLocationAndAngles(par4 + 0.5f, (float) (par5 + var12), par6 + 0.5f, par2EntityPlayer.rotationYaw, 0f);
			
			par3World.spawnEntityInWorld(laptop);
			--par1ItemStack.stackSize;
			
			return true;
		}
		else
		{
			return true;
		}
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:aj");
	}
}