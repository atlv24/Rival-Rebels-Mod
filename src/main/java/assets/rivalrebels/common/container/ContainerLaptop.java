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
import assets.rivalrebels.common.block.crate.BlockNukeCrate;
import assets.rivalrebels.common.block.trap.BlockRemoteCharge;
import assets.rivalrebels.common.item.ItemAntenna;
import assets.rivalrebels.common.item.ItemChip;
import assets.rivalrebels.common.item.ItemRodHydrogen;
import assets.rivalrebels.common.item.ItemRodNuclear;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;

public class ContainerLaptop extends Container
{
	protected TileEntityLaptop	entity;
	
	public ContainerLaptop(InventoryPlayer inventoryPlayer, TileEntityLaptop tileEntity)
	{
		entity = tileEntity;
		addSlotToContainer(new SlotRR(entity, 0, 80, 23, 1, ItemChip.class));
		addSlotToContainer(new SlotRR(entity, 1, 50, 40, 1, ItemChip.class));
		addSlotToContainer(new SlotRR(entity, 2, 111, 40, 1, ItemChip.class));
		addSlotToContainer(new SlotRR(entity, 3, 80, 48, 1, ItemChip.class));
		addSlotToContainer(new SlotRR(entity, 4, 26, 76, 1, BlockNukeCrate.class));
		addSlotToContainer(new SlotRR(entity, 5, 44, 76, 1, BlockNukeCrate.class));
		addSlotToContainer(new SlotRR(entity, 6, 62, 76, 1, ItemRodNuclear.class).setAcceptsTimedBomb(true));
		addSlotToContainer(new SlotRR(entity, 7, 80, 76, 1, ItemRodNuclear.class).setAcceptsTimedBomb(true));
		addSlotToContainer(new SlotRR(entity, 8, 98, 76, 1, ItemRodNuclear.class).setAcceptsTimedBomb(true));
		addSlotToContainer(new SlotRR(entity, 9, 26, 94, 1, ItemAntenna.class));
		addSlotToContainer(new SlotRR(entity, 10, 44, 94, 1, BlockRemoteCharge.class));
		addSlotToContainer(new SlotRR(entity, 11, 62, 94, 1, ItemRodHydrogen.class).setAcceptsTimedBomb(true));
		addSlotToContainer(new SlotRR(entity, 12, 80, 94, 1, ItemRodHydrogen.class).setAcceptsTimedBomb(true));
		addSlotToContainer(new SlotRR(entity, 13, 98, 94, 1, ItemRodHydrogen.class).setAcceptsTimedBomb(true));
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
