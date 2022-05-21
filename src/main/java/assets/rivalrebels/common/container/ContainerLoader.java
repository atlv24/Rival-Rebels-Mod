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
package assets.rivalrebels.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLoader extends Container
{
	private IInventory	lowerLoaderInventory;
	private IInventory	upperLoaderInventory;
	
	public ContainerLoader(IInventory par1IInventory, IInventory par2IInventory)
	{
		this.lowerLoaderInventory = par2IInventory;
		this.upperLoaderInventory = par1IInventory;
		addSlots();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.lowerLoaderInventory.isUseableByPlayer(par1EntityPlayer);
	}
	
	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);
		
		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			
			if (par2 < 60)
			{
				if (!this.mergeItemStack(var5, 60, this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(var5, 0, 60, false))
			{
				return null;
			}
			
			if (var5.stackSize == 0)
			{
				var4.putStack((ItemStack) null);
			}
			else
			{
				var4.onSlotChanged();
			}
		}
		
		return var3;
	}
	
	public void clearSlots()
	{
		this.inventorySlots.clear();
	}
	
	public void addSlots()
	{
		int var4;
		int var5;
		
		for (var4 = 0; var4 < 6; ++var4)
		{
			for (var5 = 0; var5 < 2; ++var5)
			{
				this.addSlotToContainer(new Slot(lowerLoaderInventory, var5 + var4 * 2, 10 + var5 * 18, 73 + var4 * 18));
			}
		}
		
		for (var4 = 0; var4 < 6; ++var4)
		{
			for (var5 = 0; var5 < 2; ++var5)
			{
				this.addSlotToContainer(new Slot(lowerLoaderInventory, 12 + var5 + var4 * 2, 212 + var5 * 18, 73 + var4 * 18));
			}
		}
		
		for (var4 = 0; var4 < 4; ++var4)
		{
			for (var5 = 0; var5 < 9; ++var5)
			{
				this.addSlotToContainer(new Slot(lowerLoaderInventory, 24 + var5 + var4 * 9, 48 + var5 * 18, 48 + var4 * 18));
			}
		}
		
		for (var4 = 0; var4 < 3; ++var4)
		{
			for (var5 = 0; var5 < 9; ++var5)
			{
				this.addSlotToContainer(new Slot(upperLoaderInventory, var5 + var4 * 9 + 9, 48 + var5 * 18, 127 + var4 * 18));
			}
		}
		
		for (var4 = 0; var4 < 9; ++var4)
		{
			this.addSlotToContainer(new Slot(upperLoaderInventory, var4, 48 + var4 * 18, 183));
		}
	}
	
	/**
	 * Callback for when the crafting gui is closed.
	 */
	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
	}
}
