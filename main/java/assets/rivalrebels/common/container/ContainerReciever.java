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
import assets.rivalrebels.common.block.crate.BlockWeapons;
import assets.rivalrebels.common.item.ItemBattery;
import assets.rivalrebels.common.item.ItemChip;
import assets.rivalrebels.common.item.ItemFuel;
import assets.rivalrebels.common.tileentity.TileEntityReciever;

public class ContainerReciever extends Container
{
	protected TileEntityReciever	entity;
	
	public ContainerReciever(InventoryPlayer inventoryPlayer, TileEntityReciever tileEntity)
	{
		entity = tileEntity;
		addSlotToContainer(new SlotRR(entity, 0, 8, 76, 64, ItemFuel.class));
		addSlotToContainer(new SlotRR(entity, 1, 26, 76, 64, ItemFuel.class));
		addSlotToContainer(new SlotRR(entity, 2, 44, 76, 64, ItemFuel.class));
		addSlotToContainer(new SlotRR(entity, 3, 8, 94, 16, ItemBattery.class));
		addSlotToContainer(new SlotRR(entity, 4, 26, 94, 16, ItemBattery.class));
		addSlotToContainer(new SlotRR(entity, 5, 44, 94, 16, ItemBattery.class));
		addSlotToContainer(new SlotRR(entity, 6, 116, 94, 1, ItemChip.class));
		addSlotToContainer(new SlotRR(entity, 7, 134, 94, 1, BlockWeapons.class));
		addSlotToContainer(new SlotRR(entity, 8, 152, 94, 1, BlockWeapons.class));
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
		return null;
	}
}
