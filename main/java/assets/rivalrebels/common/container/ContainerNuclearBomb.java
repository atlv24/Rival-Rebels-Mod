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
import assets.rivalrebels.common.item.ItemChip;
import assets.rivalrebels.common.item.ItemFuse;
import assets.rivalrebels.common.item.ItemRodNuclear;
import assets.rivalrebels.common.tileentity.TileEntityNuclearBomb;

public class ContainerNuclearBomb extends Container
{
	protected TileEntityNuclearBomb	tileEntity;
	
	public ContainerNuclearBomb(InventoryPlayer inventoryPlayer, TileEntityNuclearBomb te)
	{
		tileEntity = te;
		addSlotToContainer(new SlotRR(tileEntity, 0, 16, 34, 1, ItemFuse.class));
		for (int i = 0; i <= 4; i++)
		{
			addSlotToContainer(new SlotRR(tileEntity, i + 1, 38 + i * 18, 25, 1, ItemRodNuclear.class).setAcceptsTrollface(true));
			addSlotToContainer(new SlotRR(tileEntity, i + 6, 38 + i * 18, 43, 1, ItemRodNuclear.class).setAcceptsTrollface(true));
		}
		addSlotToContainer(new SlotRR(tileEntity, 11, 133, 34, 1, BlockTimedBomb.class));
		addSlotToContainer(new SlotRR(tileEntity, 12, 152, 34, 1, ItemChip.class));
		bindPlayerInventory(inventoryPlayer);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tileEntity.isUseableByPlayer(player);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 139));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		return null;
	}
}
