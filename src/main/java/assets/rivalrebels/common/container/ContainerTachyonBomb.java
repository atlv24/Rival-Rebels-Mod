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
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import assets.rivalrebels.common.block.trap.BlockTimedBomb;
import assets.rivalrebels.common.item.ItemAntenna;
import assets.rivalrebels.common.item.ItemChip;
import assets.rivalrebels.common.item.ItemFuse;
import assets.rivalrebels.common.item.ItemRodHydrogen;
import assets.rivalrebels.common.item.ItemRodNuclear;
import assets.rivalrebels.common.item.ItemRodRedstone;
import assets.rivalrebels.common.tileentity.TileEntityAntimatterBomb;
import assets.rivalrebels.common.tileentity.TileEntityTachyonBomb;
import assets.rivalrebels.common.tileentity.TileEntityTsarBomba;

public class ContainerTachyonBomb extends Container
{
	protected TileEntityTachyonBomb	entity;
	
	public ContainerTachyonBomb(InventoryPlayer inventoryPlayer, TileEntityTachyonBomb tileEntity)
	{
		entity = tileEntity;
		addSlotToContainer(new SlotRR(entity, 0, 18, 48, 1, ItemFuse.class));
		addSlotToContainer(new SlotRR(entity, 1, 40, 59, 1, ItemAntenna.class));
		addSlotToContainer(new SlotRR(entity, 2, 40, 37, 1, ItemAntenna.class));
		for (int i = 0; i <= 3; i++)
		{
			addSlotToContainer(new SlotRR(entity, i + 3, 62 + i * 18, 19, 1, ItemRodNuclear.class).setAcceptsTrollface(true));
			addSlotToContainer(new SlotRR(entity, i + 7, 62 + i * 18, 37, 1, ItemRodNuclear.class).setAcceptsTrollface(true));
			addSlotToContainer(new SlotRR(entity, i + 11, 62 + i * 18, 59, 1, ItemRodHydrogen.class).setAcceptsTrollface(true));
			addSlotToContainer(new SlotRR(entity, i + 15, 62 + i * 18, 77, 1, ItemRodHydrogen.class).setAcceptsTrollface(true));
		}
		addSlotToContainer(new SlotRR(entity, 19, 138, 48, 1, BlockTimedBomb.class));
		addSlotToContainer(new SlotRR(entity, 20, 98, 99, 1, ItemChip.class));
		bindPlayerInventory(inventoryPlayer);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return entity.isUseableByPlayer(player);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 119 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 175));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);
		
		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			
			if (par2 <= 19)
			{
				if (!this.mergeItemStack(var5, 19, this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(var5, 0, 19, false))
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
}
